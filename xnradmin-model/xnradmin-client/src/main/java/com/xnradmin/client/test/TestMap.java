/**
 *2014年9月8日 下午8:31:02
 */
package com.xnradmin.client.test;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cntinker.util.StringHelper;

/**
 * @author: liubin
 *
 */
public class TestMap {

	private static void test() throws JSONException {
		String s = "[{\"id\":\"4\",\"msgTitle\":\"我要点餐-副页\",\"sortOrder\":1,\"msgContent\":\"我要点餐-副页~~\"},{\"id\":\"2\",\"msgTitle\":\"我要点餐-首页\",\"sortOrder\":2,\"msgContent\":\"欢迎点餐！\"}]";

		JSONArray array = new JSONArray(s);
		for (int i = 0; i < array.length(); i++) {
			JSONObject js = (JSONObject) array.get(i);
			System.out.println(js);
			String id = js.getString("id");
			System.out.println("id:" + id);
		}

		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("1", 2);
		m.put("2", 1);
		m = StringHelper.sortByValue(m);

		System.out.println(m);
	}

	public static void main(String[] args) throws Exception {
		test();
	}
}
