/**
 * 2014年2月10日 上午3:14:12
 */
package com.xnradmin.client.test;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cntinker.util.JsonUtil;
import com.cntinker.uuid.UUIDGener;
import com.xnradmin.client.action.wx.WXConnectAction;
import com.xnradmin.po.CommonAttach;

/**
 * @author: bin_liu
 */
public class TestJson {
	private static Logger log = Logger.getLogger(TestJson.class);
	private static void testUserReg() throws JSONException {
		String content = "{\"clientUserInfo\":[{\"userUdid\":\"11111111\"},{\"userMobile\":\"13999999999\"}],\"action\":\"userReg\"}";
		JSONObject obj = new JSONObject(content);
		JSONArray array = (JSONArray) obj.get("clientUserInfo");
		for (int i = 0; i < array.length(); i++) {
			log.debug(array.get(i));
		}
		log.debug(array);

		log.debug(JsonUtil.getJsonObjectByArray(array, "userUdid"));
		log.debug(JsonUtil.getValueByArray(array, "userUdid"));
	}

	private static void test() throws JSONException {
		JSONObject out = new JSONObject();
		out.put("action", "userReg");

		// 用户信息
		JSONArray clientUserInfo = new JSONArray();
		// 用户设备号
		JSONObject userUdid = new JSONObject();
		userUdid.put("userUdid", "11111111");
		clientUserInfo.put(userUdid);
		// 用户手机号
		JSONObject userMobile = new JSONObject();
		userMobile.put("userMobile", "13999999999");
		clientUserInfo.put(userMobile);

		out.put("clientUserInfo", clientUserInfo);
		out.put("linkid", UUIDGener.getUUID());
		out.put("valCode", "1234");
		log.debug(out.toString());
	}

	private static void testPost() throws JSONException {
		JSONObject out = new JSONObject();
		out.put("action", "userReg");

		// 用户信息
		JSONArray clientUserInfo = new JSONArray();
		// 用户设备号
		JSONObject userUdid = new JSONObject();
		userUdid.put("userUdid", "11111111");
		clientUserInfo.put(userUdid);
		// 用户手机号
		JSONObject userMobile = new JSONObject();
		userMobile.put("userMobile", "13999999999");
		clientUserInfo.put(userMobile);

		out.put("clientUserInfo", clientUserInfo);

		log.debug(out.toString());
	}

	private static void testIds() throws JSONException {
		JSONObject msg1 = new JSONObject();
		msg1.put("attach_id", "1");
		msg1.put("link", "http://www.baidu.com");
		JSONObject msg2 = new JSONObject();
		msg2.put("attach_id", "2");
		msg2.put("link", "http://www.sina.com.cn");
		
		JSONArray out = new JSONArray();
		out.put(msg1);
		out.put(msg2);
		
		log.debug(out.toString());
	}

	private static void testGoods() throws JSONException {
		JSONArray goodsArray = new JSONArray();
		JSONObject goods = new JSONObject();
		//具体内容
		goods.put("id", "2665");
		goods.put("format", "20斤");
		goods.put("level", "松散");
		goods.put("price_unit", "斤");
		goods.put("weight", "1");
		goods.put("class1", "蔬菜");
		goods.put("class2", "叶菜类");
		goods.put("unit", "斤");
		goods.put("name", "甘蓝圆白菜");
		goods.put("subject", "一袋约20斤,约12个");
		goods.put("sell_brand", "");
		goods.put("price", "0.7");
		goods.put("commodity_price_id", "13736");
		goods.put("own_brand", "");
		goods.put("trick_base", "24");
		goods.put("sold_count", "6905");
		goods.put("is_favorite", "0");
		goods.put("standard_item_num", "20");
		goods.put("commodity_total_price", "14");
		goodsArray.put(goods);
		
		goods = new JSONObject();
		goods.put("id","2572");
		goods.put("format","10斤");
		goods.put("level","松散");
		goods.put("price_unit","斤");
		goods.put("weight","1");
		goods.put("class1","蔬菜");
		goods.put("class2","叶菜类");
		goods.put("unit","斤");
		goods.put("name","甘蓝圆白菜");
		goods.put("subject","一袋约10斤);约6个");
		goods.put("sell_brand","");
		goods.put("price","0.75");
		goods.put("commodity_price_id","13819");
		goods.put("own_brand","");
		goods.put("trick_base","");
		goods.put("sold_count",8701);
		goods.put("is_favorite",0);
		goods.put("standard_item_num","10");
		goods.put("commodity_total_price",7.5);
		goodsArray.put(goods);
		//子分类信息
		JSONObject category = new JSONObject();
		category.put("叶菜类", goodsArray);
		category.put("根茎类", goodsArray);
		//categoryArray.put(category);
		//主分类
		JSONObject parentCategory = new JSONObject();
		parentCategory.put("蔬菜", category);

		log.debug(parentCategory.toString());
	}
	
	public static void main(String[] args) throws Exception {
//		testUserReg();
//		test();
//		testIds();
		testGoods();
	}
}
