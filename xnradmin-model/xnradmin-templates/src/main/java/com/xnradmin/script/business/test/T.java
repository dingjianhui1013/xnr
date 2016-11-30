/**
 *2014年9月14日 下午4:21:09
*/
package com.xnradmin.script.business.test;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.xnradmin.core.util.WxUtil;

/**
 * @author: liubin
 *
 */
public class T {
	private static Logger log = Logger.getLogger(T.class);
	public static void main(String[] args) throws Exception {
		String js = "{\"errcode\":0,\"errmsg\":\"ok\"}";
		JSONObject jso = new JSONObject(js);
		
		log.debug(jso.get("errcode"));
		log.debug(jso.get("errmsg"));
	}
}
