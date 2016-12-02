/**
 *2014年8月14日 下午6:30:00
 */
package com.xnradmin.core.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.json.JSONException;
import org.json.JSONObject;

import com.cntinker.util.HttpHelper;
import com.cntinker.util.XmlHelper;

/**
 * @author: liubin
 * 
 */
public class WxUtil {
	private static Logger log = Logger.getLogger(WxUtil.class);
	private volatile String content;

	public WxUtil(String content) {
		this.content = content;
	}

	public String getDataFromKey(String key) throws IllegalAccessException,
			DocumentException {
		Element root = XmlHelper.getRoot(this.content);
		return XmlHelper.getElementInfo(root, key);
	}

	private static void testXml() throws IllegalAccessException,
			DocumentException {
		String c = "<xml><ToUserName><![CDATA[gh_89a513424520]]></ToUserName><FromUserName><![CDATA[oSMtluP7xOWVd-5_axZqPlEBziHE]]></FromUserName><CreateTime>1408011424</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[unsubscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>";
		WxUtil wu = new WxUtil(c);
		log.debug(wu.getDataFromKey("ToUserName"));
		log.debug(wu.getDataFromKey("FromUserName"));
		log.debug(wu.getDataFromKey("CreateTime"));
	}

	private static void testJson() throws JSONException {
		String json = "{\"errcode\":40013,\"errmsg\":\"invalid appid\"}";
		// String json = "{\"b\":\"2\",\"a\":\"1\"}";
		// String json =
		// "{\"action\":\"software\",\"softwareType\":\"532\",\"osType\":\"531\"}";

		JSONObject obj = new JSONObject(json);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			String key = it.next().toString();
			log.debug("key:" + key);
			log.debug(" - value:" + obj.get(key));
		}
		log.debug(obj);

	}

	private static void testOut() throws JSONException, MalformedURLException,
			IOException {
		JSONObject obj = new JSONObject();
		obj.put("a", "1");
		obj.put("b", "2");
		log.debug(obj);

		// String res = HttpHelper
		// .sendGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET");
		// log.debug(res);
		Object o = obj.get("test");
		if(o==null){
			log.debug("this is null");
		}
		log.debug(o);
		
	}

	public static void main(String[] args) throws Exception {

		testJson();
		testOut();
	}
}
