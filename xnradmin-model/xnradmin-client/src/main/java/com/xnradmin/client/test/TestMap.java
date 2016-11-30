/**
 *2014年9月8日 下午8:31:02
 */
package com.xnradmin.client.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cntinker.util.StringHelper;
import com.xnradmin.client.action.wx.WXConnectAction;

/**
 * @author: liubin
 *
 */
public class TestMap {
	private static Logger log = Logger.getLogger(TestMap.class);
	private static void test() throws JSONException {
		String s = "[{\"id\":\"4\",\"msgTitle\":\"我要点餐-副页\",\"sortOrder\":1,\"msgContent\":\"我要点餐-副页~~\"},{\"id\":\"2\",\"msgTitle\":\"我要点餐-首页\",\"sortOrder\":2,\"msgContent\":\"欢迎点餐！\"}]";

		JSONArray array = new JSONArray(s);
		for (int i = 0; i < array.length(); i++) {
			JSONObject js = (JSONObject) array.get(i);
			log.debug(js);
			String id = js.getString("id");
			log.debug("id:" + id);
		}

		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("1", 2);
		m.put("2", 1);
		m = StringHelper.sortByValue(m);

		log.debug(m);
	}

	public static void main(String[] args) throws Exception {
		test();
	}
}
