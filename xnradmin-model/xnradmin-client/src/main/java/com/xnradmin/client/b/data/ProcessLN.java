/**
 *2014年12月18日 下午12:40:53
 */
package com.xnradmin.client.b.data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cntinker.util.HttpHelper;
import com.cntinker.util.StringHelper;

/**
 * @author: liubin
 *
 */
public class ProcessLN {

	private static String goodsListUrl = "http://m.blueface.cn/api/goods/list";// "http://123.56.45.136/api/goods/list";//
																				// "http://m.blueface.cn/api/goods/list";

	private static String categoryList = "http://123.57.42.5/ecmobile/?url=/category";

	private static String loginUrl = "http://m.blueface.cn/api/user/login";
	private static String loginContent = "";

	private static String indexUrl = "http://m.blueface.cn/home/index";

	private static Map<String, String> cateMap = new HashMap<String, String>();

	private static String cookie = null;

	static {
		cateMap.put("0", "常购");
		cateMap.put("1", "蔬菜水果");
		cateMap.put("2", "肉禽水产");
		cateMap.put("3", "米面粮油");
		cateMap.put("4", "调料其他");
		cateMap.put("36", "禽蛋粮油");
		cateMap.put("34", "冷鲜肉类");
		cateMap.put("37", "调料干货");
		cateMap.put("16", "餐饮半成品");
		cateMap.put("25", "酒水饮料");
		cateMap.put("21", "餐饮用品用具");
		cateMap.put("35", "水产冻货");
		cateMap.put("38", "预售-勿订");
	}

	private static void processLogin() throws ClientProtocolException,
			IOException {

		/**
		 * System.out.println(System.getProperty(EnvConstant.XICHEADMIN_HOME));
		 * 
		 * System.out.println(SpringBase.getCfg().getCfgPath());
		 * System.out.println("-----------------"); String cookiedir =
		 * System.getProperty(EnvConstant.XICHEADMIN_HOME) + "/data/cookie/";
		 * System.out.println(cookiedir); if (!StringHelper.isNull(System
		 * .getProperty(EnvConstant.XICHEADMIN_HOME)) && !new
		 * File(cookiedir).exists()) { FileHelper.mkdir(cookiedir); }
		 */
		// ----------------------
		CloseableHttpClient httpclient = HttpClients.createDefault();
		;
		HttpPost httpPost = new HttpPost(loginUrl);

		httpPost.setHeader("Connection", "keep-alive");
		httpPost.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpPost.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("encoding", "UTF-8"));
		nvps.add(new BasicNameValuePair("user_name", "18610914994"));
		nvps.add(new BasicNameValuePair("password", "123456"));
		CloseableHttpResponse response = null;
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		response = httpclient.execute(httpPost);

		Header[] hList = response.getAllHeaders();
		Header h = response.getFirstHeader("Set-Cookie");
		cookie = h.getValue();
		// cookie = cookie.substring(0, cookie.indexOf(";"));

		System.out.println("----------- cookie start ------------------");

		for (Header e : hList) {
			System.out.println(e.getName() + " | " + e.getValue());
		}
		System.out.println("----------- cookie end ------------------");
		HttpPost indexPost = new HttpPost(indexUrl);

	}

	private static void testMain() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		;
		HttpPost httpPost = new HttpPost(goodsListUrl);

		httpPost.setHeader("Connection", "keep-alive");
		httpPost.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpPost.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpPost.setHeader(
				"Cookie",
				"pv=username%3D%25E7%2588%25B1%25E8%2590%25A5%25E5%2585%25BB%25E6%259C%259D%25E5%25A4%2596SOHO%25E5%25BA%2597%26name%3D%25E7%2588%25B1%25E8%2590%25A5%25E5%2585%25BB%25E6%259C%259D%25E5%25A4%2596SOHO%25E5%25BA%2597%26uid%3D1206%26mobile%3D18610914994; ev=687dd22c2bda526cd87b6be2b247b200; PHPSESSID=k0o1q4jn8hqstalnjrv04qu4h7");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("encoding", "UTF-8"));
		nvps.add(new BasicNameValuePair("t", new Long(System
				.currentTimeMillis()).toString()));
		nvps.add(new BasicNameValuePair("uid", "1206"));
		nvps.add(new BasicNameValuePair("page", "2"));

		CloseableHttpResponse response = null;
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		response = httpclient.execute(httpPost);

		Header[] hList = response.getAllHeaders();

		// cookie = cookie.substring(0, cookie.indexOf(";"));

		System.out.println("----------- cookie start ------------------");

		for (Header e : hList) {
			System.out.println(e.getName() + " | " + e.getValue());
		}
		System.out.println("----------- cookie end ------------------");
		HttpEntity entity = response.getEntity();
		// 判断响应实体是否为空
		if (entity != null) {
			String responseString = EntityUtils.toString(entity);
			System.out.println("response length:" + responseString.length());
			System.out.println("response content:"
					+ responseString.replace("\r\n", ""));
		}
	}

	private static void process() throws MalformedURLException, IOException,
			JSONException {

		String res = HttpHelper.sendGet(goodsListUrl);

		res = StringHelper.convertUnicode(res);
		JSONObject jo = new JSONObject(res);

		System.out.println(jo.get("result"));

		JSONArray data = new JSONArray(jo.get("info").toString());
		for (int i = 0; i < data.length(); i++) {

		}

	}

	private static void processCategory() throws MalformedURLException,
			IOException, JSONException {
		String res = HttpHelper.sendGet(categoryList);

		res = StringHelper.convertUnicode(res);

		JSONObject jo = new JSONObject(res);
		JSONArray data = new JSONArray(jo.get("data").toString());
		for (int i = 0; i < data.length(); i++) {
			JSONObject temp = (JSONObject) data.get(i);
			System.out.println(temp.get("id") + " | " + temp.get("name"));
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(1);
		// processLogin();
		testMain();
		//
		//System.out.println(StringHelper.convertUnicode("\u7f3a\u5c11\u53c2\u6570"));
	}
}
