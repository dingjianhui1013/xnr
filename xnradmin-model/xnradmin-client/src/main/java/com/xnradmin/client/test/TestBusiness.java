/**
 *2014年10月12日 下午11:22:03
 */
package com.xnradmin.client.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cntinker.util.HttpHelper;
import com.xnradmin.core.service.business.commodity.BusinessCategoryService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.vo.business.BusinessGoodsVO;

/**
 * @author: liubin
 * 
 */
public class TestBusiness {

	private static void testBusinessListJson() throws JSONException {
		BusinessCategoryService categoryService = (BusinessCategoryService) SpringBase
				.getCtx().getBean("BusinessCategoryService");
		BusinessGoodsService goodsService = (BusinessGoodsService) SpringBase
				.getCtx().getBean("BusinessGoodsService");
		List<BusinessCategory> categoryList = new ArrayList();
		List<BusinessGoodsVO> voList = new ArrayList();
		// 取得分类，商品所有数据
		voList = goodsService.webBusinessList(null, 0, 0, "", "",true);
		categoryList = categoryService.listAll();
		LinkedHashMap<String, ArrayList<BusinessGoodsVO>> jsonMap = new LinkedHashMap();
		// 将所有分类的商品汇总到MAP中
		for (int i = 0; voList.size() > i; i++) {
			BusinessGoodsVO vo = voList.get(i);
			// 处理主分类数据
			for (int j = 0; categoryList.size() > j; j++) {
				BusinessCategory bc = categoryList.get(j);
				if (vo.getBusinessCategory().getCategoryParentId() == bc
						.getId()) {
					vo.setBusinessParentCategory(bc);
				}
			}
			// 归纳数据，KEY为分类ID
			if (jsonMap.get(vo.getBusinessCategory().getId()) != null) {
				ArrayList<BusinessGoodsVO> a = jsonMap.get(vo
						.getBusinessCategory().getId());
				a.add(vo);
				jsonMap.put(vo.getBusinessCategory().getId().toString(), a);
			} else {
				ArrayList<BusinessGoodsVO> b = new ArrayList<BusinessGoodsVO>();
				b.add(vo);
				jsonMap.put(vo.getBusinessCategory().getId().toString(), b);
			}
		}

		// 处理分拣好的MAP
		JSONObject parentCategory = new JSONObject();
		JSONObject category = new JSONObject();
		String parentCategoryName = "";
		for (Iterator it = jsonMap.keySet().iterator(); it.hasNext();) {
			String categoryId = it.next().toString();
			ArrayList<BusinessGoodsVO> c = jsonMap.get(categoryId);
			if (c.size() > 0
					&& !parentCategoryName.equals("")
					&& !parentCategoryName.equals(c.get(0)
							.getBusinessParentCategory().getCategoryName())) {
				parentCategory.put(parentCategoryName, category);
				category = new JSONObject();
			}

			JSONArray goodsArray = new JSONArray();
			for (int i = 0; c.size() > i; i++) {
				BusinessGoodsVO vo = c.get(i);
				JSONObject goods = new JSONObject();
				goods.put("id", vo.getBusinessGoods().getId());
				goods.put("format", vo.getBusinessGoods().getGoodsWeight()
						+ vo.getBusinessWeight().getWeightName());
				goods.put("level", "松散");
				goods.put("price_unit", vo.getBusinessWeight().getWeightName());
				goods.put("weight", "1");
				goods.put("class1", vo.getBusinessParentCategory()
						.getCategoryName());
				goods.put("class2", vo.getBusinessCategory().getCategoryName());
				goods.put("unit", vo.getBusinessWeight().getWeightName());
				goods.put("name", vo.getBusinessGoods().getGoodsName());
				goods.put("subject", vo.getBusinessGoods()
						.getGoodsDescription());
				goods.put("sell_brand", "");
				goods.put("price", vo.getBusinessGoods()
						.getGoodsOriginalPrice());
				goods.put("commodity_price_id", "");
				goods.put("own_brand", "");
				goods.put("trick_base", "");
				goods.put("sold_count", 8701);
				goods.put("is_favorite", 0);
				goods.put("standard_item_num", "10");
				goods.put("commodity_total_price", vo.getBusinessGoods()
						.getGoodsOriginalPrice());
				goodsArray.put(goods);
				parentCategoryName = vo.getBusinessParentCategory()
						.getCategoryName();
			}
			if (c.size() > 0) {
				category.put(c.get(0).getBusinessCategory().getCategoryName(),
						goodsArray);
			}
			if (!it.hasNext()) {
				if (c.size() > 0 && !parentCategoryName.equals("")) {
					parentCategory.put(parentCategoryName, category);
				}
			}
		}

		System.out.println("process success : " + parentCategory.toString());
	}

	private static void testBusinessGwcJson() throws JSONException {
		// 取得分类，商品所有数据
		BusinessCategoryService categoryService = (BusinessCategoryService) SpringBase
				.getCtx().getBean("BusinessCategoryService");
		BusinessGoodsService goodsService = (BusinessGoodsService) SpringBase
				.getCtx().getBean("BusinessGoodsService");
		List<BusinessCategory> categoryList = new ArrayList();
		List<BusinessGoodsVO> voList = new ArrayList();
		voList = goodsService.webBusinessList(null, 0, 0, "", "",false);
		categoryList = categoryService.listAll();
		LinkedHashMap<String, ArrayList<BusinessGoodsVO>> jsonMap = new LinkedHashMap();
		// 将所有分类的商品汇总到MAP中
		for (int i = 0; voList.size() > i; i++) {
			BusinessGoodsVO vo = voList.get(i);
			// 处理主分类数据
			for (int j = 0; categoryList.size() > j; j++) {
				BusinessCategory bc = categoryList.get(j);
				if (vo.getBusinessCategory().getCategoryParentId() == bc
						.getId()) {
					vo.setBusinessParentCategory(bc);
				}
			}
			// 归纳数据，KEY为分类ID
			if (jsonMap.get(vo.getBusinessCategory().getId()) != null) {
				ArrayList<BusinessGoodsVO> a = jsonMap.get(vo
						.getBusinessCategory().getId());
				a.add(vo);
				jsonMap.put(vo.getBusinessCategory().getId().toString(), a);
			} else {
				ArrayList<BusinessGoodsVO> b = new ArrayList<BusinessGoodsVO>();
				b.add(vo);
				jsonMap.put(vo.getBusinessCategory().getId().toString(), b);
			}
		}

		// 处理分拣好的MAP
		JSONArray goodsArray = new JSONArray();
		for (Iterator it = jsonMap.keySet().iterator(); it.hasNext();) {
			String categoryId = it.next().toString();
			ArrayList<BusinessGoodsVO> c = jsonMap.get(categoryId);
			for (int i = 0; c.size() > i; i++) {
				BusinessGoodsVO vo = c.get(i);
				JSONObject goods = new JSONObject();
				goods.put("id", vo.getBusinessGoods().getId());
				goods.put("format", vo.getBusinessGoods().getGoodsWeight()
						+ vo.getBusinessWeight().getWeightName());
				goods.put("level", "");
				goods.put("price_unit", vo.getBusinessWeight().getWeightName());
				goods.put("weight", "1");
				goods.put("class1", vo.getBusinessParentCategory()
						.getCategoryName());
				goods.put("class2", vo.getBusinessCategory().getCategoryName());
				goods.put("unit", vo.getBusinessWeight().getWeightName());
				goods.put("name", vo.getBusinessGoods().getGoodsName());
				goods.put("subject", vo.getBusinessGoods()
						.getGoodsDescription());
				goods.put("sell_brand", "");
				// 计算平均价格
				BigDecimal b1 = new BigDecimal(vo.getBusinessGoods()
						.getGoodsOriginalPrice());
				BigDecimal b2 = new BigDecimal(vo.getBusinessGoods()
						.getGoodsWeight());
				goods.put("price", b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP)
						.doubleValue());
				goods.put("commodity_price_id", "");
				goods.put("own_brand", "");
				goods.put("trick_base", "");
				goods.put("sold_count", 8701);
				goods.put("is_favorite", 0);
				goods.put("standard_item_num", "10");
				goods.put("commodity_total_price", vo.getBusinessGoods()
						.getGoodsOriginalPrice());
				goodsArray.put(goods);
			}
		}
		System.out.println("process success : " + goodsArray.toString());
	}

	private static void putOrder() throws JSONException, MalformedURLException, IOException {
		// 处理分拣好的MAP
		//{"goods":[{"goodsId":"4769","goodsCount":"1"},{"goodsId":"4780","goodsCount":"1"},{"goodsId":"4031","goodsCount":"1"},{"goodsId":"3913","goodsCount":"1"}]}
		JSONObject data = new JSONObject();
		JSONArray goodsArray = new JSONArray();
		JSONObject goods = new JSONObject();
		JSONArray userRealArray = new JSONArray();
		JSONObject userReal = new JSONObject();
		
		goods.put("goodsId", "4769");
		goods.put("goodsCount", "1");
		goodsArray.put(goods);
		goods.put("goodsId", "4780");
		goods.put("goodsCount", "2");
		goodsArray.put(goods);
		goods.put("goodsId", "4031");
		goods.put("goodsCount", "1");
		goodsArray.put(goods);
		goods.put("goodsId", "3913");
		goods.put("goodsCount", "3");
		goodsArray.put(goods);
		//{"userReal":[{"userRealName":"1","userRealMobile":"2","userRealAddress":"3","payType":"197","staffId":12}]}
		
		userReal.put("userRealName", "姓名");
		userReal.put("userRealMobile", "手机");
		userReal.put("userRealAddress", "派送地址");
		userReal.put("payType", "197");
		userReal.put("coopid", "192");
		userReal.put("requiredDeliveryTime", "2015-03-05");
		userRealArray.put(userReal);
		data.put("goods",goodsArray);
		data.put("userReal",userRealArray);
		String data2 = "{\"goods\":[{\"goodsId\":\"5960\",\"goodsCount\":\"1\"}],\"userReal\":[{\"userid\":\"1\",\"userRealName\":\"12qq\",\"userRealMobile\":\"13245678911\",\"userRealAddress\":\"u798fu7199u5927u9053\",\"requiredDeliveryTime\":\"2015-03-06\",\"coopid\":\"192\",\"payType\":\"197\"}]}";
		
//		System.out.println("http://localhost/page/wx/client/web/business/orderrecord/putOrder.action");
//		System.out.println(data.toString());
		//HttpHelper.postHttpRquest("http://localhost/page/wx/client/web/business/orderrecord/putOrder.action","data="+data2.toString());
		System.out.println(HttpHelper.postXml("http://localhost/page/wx/client/web/business/orderrecord/putOrder.action", data2.toString()));
	}
	public static void main(String[] args) throws Exception {
//		testBusinessListJson();
//		testBusinessGwcJson();
		putOrder();
	}
}
