/**
 *2014年11月27日 上午11:43:23
 */
package com.xnradmin.client.b.data;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.business.BusinessData;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.vo.business.BusinessDataFDLMTypeVO;

/**
 * 处理饭店联盟<br>
 * 
 * @author: liubin <br>
 */
public class ProcessFDLM implements Runnable {

	private Log log = Log4jUtil.getLog("businessdata");

	private Log exLog = Log4jUtil.getLog("ex");

	// 饭店联盟的statuscode ID - 3
	private String sourceId = "3";

	private CommonDAO dao = (CommonDAO) SpringBase.getCtx()
			.getBean("CommonDAO");

	private StatusService statusService = (StatusService) SpringBase.getCtx()
			.getBean("StatusService");

	// 细分页面
	private String mainUrl = "http://115.28.252.36/RA/category/listapp?releaseNum=";

	// 分类页面
	private String typeUrl = "http://115.28.252.36/RA/categorytype/listapp";
	
	private Set<String> goodsNameCache = new HashSet<String>();

	public void run() {

		for (int i = 0; i < 1; i++) {
			try {
				String u = mainUrl + i;

				process(u);
			} catch (IOException | JSONException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
		}

	}

	private Map<Integer, BusinessDataFDLMTypeVO> findAllType()
			throws IOException, SAXException, JSONException {
		Map<Integer, BusinessDataFDLMTypeVO> res = new HashMap<Integer, BusinessDataFDLMTypeVO>();
		HttpunitHelper webLogin = new HttpunitHelper();
		WebConversation wc = webLogin.init(
				"vm/3.59.0 Webkit/1.0(Android 4.4.4; Sony/L39h)", true);
		WebResponse r = wc.getResponse(typeUrl);

		String content = r.getText();
		JSONObject jo = new JSONObject(content);
		JSONArray data = (JSONArray) jo.get("data");
		for (int i = 0; i < data.length(); i++) {
			BusinessDataFDLMTypeVO vo = new BusinessDataFDLMTypeVO();
			JSONObject temp = (JSONObject) data.get(i);
			Integer id = new Integer(temp.get("iD").toString());
			String typeName = temp.get("type_name").toString();
			Integer pid = new Integer(temp.get("parentId").toString());
			String url = temp.get("url").toString();

			vo.setParentId(pid);
			vo.setTypeName(typeName);
			vo.setTypeid(id);
			vo.setUrl(url);

			res.put(id, vo);
		}

		return res;
	}

	private BusinessDataFDLMTypeVO getTypeByTypeid(Integer typeid)
			throws IOException, SAXException, JSONException {
		BusinessDataFDLMTypeVO v = new BusinessDataFDLMTypeVO();
		Map<Integer, BusinessDataFDLMTypeVO> lst = findAllType();
		Iterator<Integer> it = lst.keySet().iterator();
		while (it.hasNext()) {
			Integer k = (Integer) it.next();
			if (k.intValue() == typeid.intValue())
				return lst.get(k);
		}
		return null;
	}

	private void process(String url) throws IOException, SAXException,
			JSONException {

		System.out.println(url);
		HttpunitHelper webLogin = new HttpunitHelper();
		WebConversation wc = webLogin.init(
				"vm/3.59.0 Webkit/1.0(Android 4.4.4; Sony/L39h)", true);

		WebResponse r = wc.getResponse(url);
		// System.out.println(r.getText());

		String content = r.getText();

		JSONObject jo = new JSONObject(content);
		JSONArray data = (JSONArray) jo.get("data");

		List<Status> s = statusService.findByStatusCode(BusinessData.class,
				sourceId);
		Status fdlm = null;
		if (s != null && s.size() > 0)
			fdlm = s.get(0);
		if (fdlm == null) {
			exLog.error("未知的来源ID：" + sourceId);
			return;
		}

		for (int i = 0; i < data.length(); i++) {
			JSONObject temp = (JSONObject) data.get(i);
			StringBuffer sb = new StringBuffer();
			sb.append("名称：").append(temp.get("commodityName"));
			sb.append(" | 价格：").append(temp.get("price"));
			sb.append(" | 单位：").append(temp.get("unit"));
			sb.append(" | ID：").append(temp.get("iD"));
			sb.append(" | 类型ID：").append(temp.get("typeId"));
			System.out.println(sb.toString());

			BusinessData po = new BusinessData();
			po.setSourceId(fdlm.getId().toString());
			po.setSourceName(fdlm.getStatusName());
			po.setProductName(temp.get("commodityName").toString());
			po.setPrice(new Double(temp.get("price").toString()));
			po.setUnit(temp.get("unit").toString());
			po.setUpdateTime(new Timestamp(System.currentTimeMillis()));

			// BusinessDataFDLMTypeVO type = getTypeByTypeid(new
			// Integer(temp.get("iD").toString()));

			// po.setCategory(type.getTypeName());
			// po.setParentCategory(ptype.getTypeName());

			BusinessGoods goods = saveGoods(po);
			if (goods.getGoodsName().indexOf("斤") > -1)
				modifySingle(goods);
		}

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
			System.out.println("单位已存在：" + data.getUnit());
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

	private BusinessGoods findGoodsByName(String goodsName) {
		String hql = "from BusinessGoods where goodsName='" + goodsName + "'";
		List<BusinessGoods> r = dao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		if (r == null || r.size() <= 0)
			return null;
		return r.get(0);
	}

	private BusinessGoods saveGoods(BusinessData data) {
		BusinessWeight po = processWeight(data);
		BusinessGoods goods = null;
		goods = findGoodsByName(data.getProductName());

		if (goods != null) {
			if (goods.getGoodsOriginalPrice().floatValue() != data.getPrice()
					.floatValue()) {
				modify(goods, data);
				return goods;
			}
			
			return goods;
		}

		goods = new BusinessGoods();
		goods.setCreateStaffId(2);
		goods.setCreateTime(new Timestamp(System.currentTimeMillis()));
		goods.setGoodsDescription(data.getDescription());
		goods.setGoodsName(data.getProductName());
		goods.setGoodsOriginalPrice(data.getPrice().floatValue());
		goods.setGoodsSinglePrice(data.getPrice().floatValue());
		goods.setGoodsPreferentialPrice(data.getPrice().floatValue());
		goods.setGoodsPurchasePrice(data.getPrice().floatValue());
		goods.setGoodsStatus(192);
		goods.setGoodsWeight(1f);
		goods.setGoodsWeightId(po.getId());
		goods.setIsDiscountGoods(120);

		goods.setPrimaryConfigurationId(1);
		goods.setSortId(0);

		goods.setModifyStaffId(2);
		goods.setModifyTime(new Timestamp(System.currentTimeMillis()));
		goods.setGoodsCategoryId("153");

		dao.save(goods);

		
		return goods;
	}

	private void modify(BusinessGoods goods, BusinessData data) {
		goods.setModifyStaffId(2);
		goods.setModifyTime(new Timestamp(System.currentTimeMillis()));
		goods.setGoodsOriginalPrice(data.getPrice().floatValue());
		goods.setGoodsSinglePrice(data.getPrice().floatValue());
		goods.setGoodsPreferentialPrice(data.getPrice().floatValue());
		// goods.setGoodsPurchasePrice(data.getPrice().floatValue());
		dao.modify(goods);

	}

	private void modifySingle(BusinessGoods goods) {
		String gName = processGoodsName(goods.getGoodsName());
		if (StringHelper.isNull(gName))
			return;
		BusinessGoods singleGoods = findGoodsByName(gName);
		if (singleGoods == null)
			return;
		
		if(goodsNameCache.contains(singleGoods.getGoodsName()))
			return;
		else
			goodsNameCache.add(singleGoods.getGoodsName());
		
		Float f = getCountFormGoodsname(goods.getGoodsName());

		if (f == null || f.floatValue() <= 0.0f)
			return;

		Float price = goods.getGoodsOriginalPrice() / f;
		// update
		singleGoods.setModifyStaffId(2);
		singleGoods.setModifyTime(new Timestamp(System.currentTimeMillis()));
		singleGoods.setGoodsOriginalPrice(price);
		singleGoods.setGoodsSinglePrice(price);
		singleGoods.setGoodsPreferentialPrice(price);
		// goods.setGoodsPurchasePrice(price);
		dao.modify(singleGoods);
	}

	private static String processGoodsName(String goodsName) {
		String res = goodsName;
		if (res.indexOf("(") < 0 || res.indexOf(")") < 0)
			return null;
		res = goodsName.substring(0, goodsName.indexOf("("));
		return res;
	}

	private static Float getCountFormGoodsname(String goodsName) {
		String res = goodsName;
		if (res.indexOf("(") < 0 || res.indexOf(")") < 0)
			return null;
		res = goodsName.substring(goodsName.indexOf("(") + 1,
				goodsName.indexOf(")"));
		res = res.substring(0, res.indexOf("斤"));

		if (res.indexOf(",") > -1) {
			res = res.substring(res.indexOf(",") + 1);
		}

		return new Float(res);
	}

	public static void main(String[] args) {
		Thread t = new Thread(new ProcessFDLM());
		t.start();

		// System.out.println(processGoodsName("乌江鱼(活,2斤-3斤左右)"));

		/**
		 * System.out.println(getCountFormGoodsname("乌江鱼(活,2斤-3斤左右)"));
		 * System.out.println(getCountFormGoodsname("美极鲜(1.2斤)"));
		 * System.out.println(getCountFormGoodsname("黄柠檬(8斤箱装)"));
		 * System.out.println(getCountFormGoodsname("桔子(大,5斤袋装)"));
		 * System.out.println(getCountFormGoodsname("鳕鱼(44斤)"));
		 * System.out.println(getCountFormGoodsname("红豆(48.5斤)"));
		 */
	}

}
