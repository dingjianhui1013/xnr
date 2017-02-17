/**
 *2014年12月7日 下午1:05:43
 */
package com.xnradmin.client.b.data;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import com.cntinker.util.HttpunitHelper;
import com.cntinker.util.StringHelper;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.service.business.commodity.BusinessWeightService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessData;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;

/**
 * @author: liubin
 *
 */
public class ProcessYSMC implements Runnable {

	private Log log = Log4jUtil.getLog("businessdata");

	private Log exLog = Log4jUtil.getLog("ex");

	private String url = "http://online.yunshanmeicai.com/preview/preview";

	private BusinessWeightService weightService = (BusinessWeightService) SpringBase
			.getCtx().getBean("BusinessWeightService");

	private BusinessGoodsService goodsService = (BusinessGoodsService) SpringBase
			.getCtx().getBean("BusinessGoodsService");

	private CommonDAO dao = (CommonDAO) SpringBase.getCtx()
			.getBean("CommonDAO");

	// 云杉美菜的statuscode ID - 1
	private String sourceId = "1";

	public void run() {
		try {
			process();
		} catch (IOException | SAXException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process() throws IOException, SAXException, JSONException {
		HttpunitHelper webLogin = new HttpunitHelper();
		WebConversation wc = webLogin.init(
				"vm/3.59.0 Webkit/1.0(Android 4.4.4; Sony/L39h)", true);

		WebResponse r = wc.getResponse(url);
		// log.debug(r.getText());


		String temp = StringHelper.spiltStr(r.getText(), "window.data",
				"console.log(data)");

		String temp2 = StringHelper.spiltStr(temp, "=", "};");

		String res = temp2 + "};";
		log.debug(res);
		res = StringHelper.convertUnicode(res);

		processDB(res);
	}

	private void processDB(String res) throws JSONException {
		JSONObject jo = new JSONObject(res);

		Iterator it = jo.keys();
		while (it.hasNext()) {
			String k = it.next().toString();
			processYsmcSubLevel1(k, jo.get(k).toString());
		}

	}

	private void processYsmcSubLevel1(String type1, String content)
			throws JSONException {
		JSONObject jo = new JSONObject(content);
		Iterator it = jo.keys();

		while (it.hasNext()) {
			String k = it.next().toString();
			processYsmcSubLevel2(type1, k, jo.get(k).toString());
		}

	}

	private void processYsmcSubLevel2(String type1, String type2, String content)
			throws JSONException {
		JSONArray data = new JSONArray(content);
		for (int i = 0; i < data.length(); i++) {
			BusinessData d = new BusinessData();
			JSONObject temp = (JSONObject) data.get(i);


			StringBuffer sb = new StringBuffer();
			sb.append("名称：").append(temp.get("name"));
			sb.append(" | 备注：").append(temp.get("level"));
			sb.append(" | 单位：").append(temp.get("format"));
			sb.append(" | 独立单位：").append(temp.get("unit"));
			sb.append(" | 品牌:").append(temp.get("own_brand"));
			sb.append(" | 品牌2: ").append(temp.get("sell_brand"));
			sb.append(" | 分类：").append(temp.get("class1"));
			sb.append(" | 主分类：").append(type1);
			sb.append(" | 子分类：").append(type2);
			sb.append(" | 单价：").append(temp.get("price"));
			sb.append(" | 总价：").append(temp.get("commodity_total_price"));

			String class1 = temp.get("own_brand") == null ? "" : temp.get(
					"own_brand").toString();
			String class2 = temp.get("sell_brand") == null ? "" : temp.get(
					"sell_brand").toString();
			String productName = temp.get("name").toString();
			String productBrand = "";
			if (!StringHelper.isNull(class1) || !StringHelper.isNull(class2)) {
				if (!StringHelper.isNull(class1))
					productBrand = class1;
				else
					productBrand = class2;
				productName = productName + "|" + productBrand;
			}
			// 单位
			if (temp.get("format") != null
					&& !StringHelper.isNull(temp.get("format").toString())) {
				productName = productName + "|" + temp.get("format").toString();
			}

			if (temp.get("level") != null
					&& !StringHelper.isNull(temp.get("level").toString())) {
				productName = productName + "|" + temp.get("level").toString();
				d.setDescription(temp.get("level").toString());
			}
			// 处理分类
			BusinessCategory catedata = processCategory(type2, type1, 1);

			String unit = temp.getString("unit").toLowerCase();

			d.setProductName(productName);
			d.setUnit(unit);
			d.setCategory(catedata.getCategoryName());
			d.setPrice(new Double(temp.get("commodity_total_price").toString()));
			d.setSinglePrice(new Double(temp.get("price").toString()));

			// 处理单位
			BusinessWeight weight = processWeight(d);

			processProduct(d, catedata, weight);
		}
	}

	/**
	 * 处理商品
	 * 
	 * @param data
	 * @param cate
	 * @param po
	 * @return BusinessGoods
	 */
	private BusinessGoods processProduct(BusinessData data,
			BusinessCategory cate, BusinessWeight po) {
		BusinessGoods goods = null;
		goods = findGoodsByName(data.getProductName());

		if (goods != null) {
			if (goods.getGoodsOriginalPrice().floatValue() == data.getPrice()
					.floatValue()){
				updateDesc(data,goods);
				return goods;
			}
		}

		goods = new BusinessGoods();
		goods.setCreateStaffId(2);
		goods.setCreateTime(new Timestamp(System.currentTimeMillis()));
		goods.setGoodsDescription(data.getDescription());
		goods.setGoodsName(data.getProductName());
		goods.setGoodsOriginalPrice(data.getPrice().floatValue());
		goods.setGoodsSinglePrice(data.getSinglePrice().floatValue());
		goods.setGoodsPreferentialPrice(data.getPrice().floatValue());
		goods.setGoodsStatus(192);
		goods.setGoodsWeight(1f);
		goods.setGoodsWeightId(po.getId());
		goods.setIsDiscountGoods(120);

		goods.setPrimaryConfigurationId(1);
		goods.setSortId(0);

		goods.setModifyStaffId(2);
		goods.setModifyTime(new Timestamp(System.currentTimeMillis()));
		goods.setGoodsCategoryId(cate.getId().toString());

		dao.save(goods);
		return goods;
	}

	private void updateDesc(BusinessData data, BusinessGoods goods) {
		if (!StringHelper.isNull(data.getDescription()))
			goods.setGoodsDescription(data.getDescription());
		dao.modify(goods);
	}

	private BusinessGoods findGoodsByName(String goodsName) {
		String hql = "from BusinessGoods where goodsName='" + goodsName + "'";
		List<BusinessGoods> r = dao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		if (r == null || r.size() <= 0)
			return null;
		return r.get(0);
	}

	/**
	 * 处理商品分类
	 * 
	 * @param name
	 * @param parentName
	 * @param typeLevel
	 * @return BusinessCategory
	 */
	private BusinessCategory processCategory(String name, String parentName,
			int typeLevel) {
		BusinessCategory c = findTypeByNameAndLevel(name, typeLevel);

		if (c != null) {
			log.debug("分类已存在：" + c.getCategoryName());
			return c;
		}

		// 处理父类
		if (typeLevel != 0 && !StringHelper.isNull(parentName)) {
			BusinessCategory parentCate = findTypeByNameAndLevel(parentName,
					typeLevel - 1);
			if (parentCate == null) {
				processCategory(parentName, null, typeLevel - 1);
			}
		}

		BusinessCategory save = new BusinessCategory();
		save.setCategoryLevel(typeLevel);
		save.setCategoryName(name);
		// 如果有父类会在上一步迭代完成，这里再次判断就必定能取到父类
		if (!StringHelper.isNull(parentName)) {
			BusinessCategory p = findTypeByNameAndLevel(parentName,
					typeLevel - 1);
			if (p != null)
				save.setCategoryParentId(p.getId());
		} else {
			save.setCategoryParentId(0);
		}
		// 对应状态-显示分类和商品
		save.setCategoryStatus(189);
		save.setCreateStaffId(2);
		save.setCreateTime(new Timestamp(System.currentTimeMillis()));
		// 对应商城
		save.setPrimaryConfigurationId(1);
		save.setSortId(0);
		save.setModifyStaffId(2);
		save.setModifyTime(new Timestamp(System.currentTimeMillis()));

		dao.save(save);
		return save;
	}

	private BusinessCategory findTypeByNameAndLevel(String name, int typeLevel) {
		String hql = "from BusinessCategory where categoryName='" + name
				+ "' and categoryLevel=" + typeLevel;
		List<BusinessCategory> r = dao
				.getEntitiesByPropertiesWithHql(hql, 0, 0);
		if (r == null || r.size() <= 0)
			return null;
		return r.get(0);
	}

	/**
	 * 处理单位
	 * 
	 * @param data
	 * @return BusinessWeight
	 */
	private BusinessWeight processWeight(BusinessData data) {
		BusinessWeight weight = findWeightByName(data.getUnit());
		if (weight != null) {
			log.debug("单位已存在：" + data.getUnit());
			return weight;
		}
		weight = new BusinessWeight();
		weight.setCreateStaffId(2);
		weight.setCreateTime(new Timestamp(System.currentTimeMillis()));
		weight.setModifyStaffId(2);
		weight.setModifyTime(new Timestamp(System.currentTimeMillis()));
		weight.setPrimaryConfigurationId(1);
		weight.setSortId(0);
		weight.setWeightName(data.getUnit());
		weight.setWeightStatus(194);
		dao.save(weight);

		return weight;
	}

	private BusinessWeight findWeightByName(String weightName) {
		String hql = "from BusinessWeight where weightName='" + weightName
				+ "'";
		List<BusinessWeight> res = dao
				.getEntitiesByPropertiesWithHql(hql, 0, 0);
		if (res == null || res.size() <= 0)
			return null;
		return res.get(0);
	}

	public static void main(String[] args) throws Exception {
		ProcessYSMC ysmc = new ProcessYSMC();
		Thread t = new Thread(ysmc);
		t.start();
	}
}
