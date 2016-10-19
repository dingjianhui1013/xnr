/**
 *2014年9月14日 下午4:21:09
*/
package com.xnradmin.script.business.test;

import org.json.JSONObject;

/**
 * @author: liubin
 *
 */
public class T {

	public static void main(String[] args) throws Exception {
		String js = "{\"errcode\":0,\"errmsg\":\"ok\"}";
		JSONObject jso = new JSONObject(js);
		
		System.out.println(jso.get("errcode"));
		System.out.println(jso.get("errmsg"));
	}
}
