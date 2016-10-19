/**
 *2015年3月3日 下午3:00:28
 */
package com.xnradmin.client.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.xml.sax.SAXException;

import com.cntinker.data.HttpReturnData;
import com.cntinker.util.HttpHelper;
import com.cntinker.util.HttpunitHelper;
import com.cntinker.util.StringHelper;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

/**
 * @author: liubin
 *
 */
public class TestProcessLN {

	private static String site = "http://123.57.42.5";// "http://m.blueface.cn";
	// //http://123.57.42.5

	private static String configUrl = "/ecmobile/?url=/config";

	private static String categoryUrl = "/ecmobile/?url=/category";

	private static String searchUrl = "/ecmobile/?url=/search";

	private static String searchContent = "json=\"{\"pagination\":{\"count\":10,\"page\":2},\"filter\":{\"brand_id\":\"\",\"category_id\":\"15\",\"keywords\":\"\",\"price_range\":{\"price_min\":0,\"price_max\":0},\"sort_by\":\"price_desc\"}}\"";

	private static String content2 = "Form item: \"json\" = \"{\"pagination\":{\"count\":10,\"page\":1},\"filter\":{\"brand_id\":\"\",\"category_id\":\"1\",\"keywords\":\"\",\"price_range\":{\"price_min\":0,\"price_max\":0},\"sort_by\":\"price_desc\"}}\"";

	private static String goodsList = "/ecmobile/?url=/brand";
	private static String goodsListContent = "{\"category_id\":\"34\"}";

	// wx
	private static String login = "/api/user/login/";
	private static String loginContent = "{\"name\":\"18610914994\",\"password\":\"123456\"}";

	private static void testHttpunit() throws IOException, SAXException {
		// String u = "http://admin.17xnr.com/test/test.jsp";
		String u = site + searchUrl;
		Map<String, String> param = new HashMap<String, String>();
		param.put("json", searchContent);

		HttpunitHelper webLogin = new HttpunitHelper();
		WebConversation wc = webLogin.init(
				"vm/3.59.0 Webkit/1.0(Android 4.4.4; Sony/L39h)", true);

		WebResponse r = webLogin.getWebResponse(wc, u, param);// wc.getResponse(u);
		// System.out.println(r.getText());

		String content = r.getText();
		System.out.println("" + StringHelper.convertUnicode(content));
	}

	private static void testGoodsList() throws IOException {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Host", "2.blueface.cn\r\n");
		header.put("Content-Type", "application/x-www-form-urlencoded");
		header.put("Connection", "Keep-Alive\r\n");
		header.put("Accept-Encoding", "gzip\r\n");

		System.out.println(loginContent.length());
		String u = site + goodsList;

		HttpReturnData r = HttpHelper.postXmlData(u, goodsListContent);

		System.out.println(r.getHeader());
		System.out.println(StringHelper.convertUnicode(r.getContent()));

	}

	private static void testLogin() throws IOException {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Host", "2.blueface.cn\r\n");
		header.put("Content-Type", "application/x-www-form-urlencoded");
		header.put("Connection", "Keep-Alive\r\n");
		header.put("Accept-Encoding", "gzip\r\n");

		System.out.println(loginContent.length());
		String u = site + login;

		String r = HttpHelper.postHttpRquest(u,
				"password=123456&user_name=18610914994");

		System.out.println(StringHelper.convertUnicode(r));

	}

	private static void testConfig() throws IOException {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Host", "2.blueface.cn\r\n");
		header.put("Content-Type", "application/x-www-form-urlencoded");
		header.put("Connection", "Keep-Alive\r\n");
		header.put("Accept-Encoding", "gzip\r\n");

		System.out.println(content2.length());
		String u = site + configUrl;
		String r = HttpHelper.sendGet(u);

		System.out.println(StringHelper.convertUnicode(r));
	}

	private static void testCate() throws MalformedURLException, IOException {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Host", "2.blueface.cn\r\n");
		header.put("Content-Type", "application/x-www-form-urlencoded");
		header.put("Connection", "Keep-Alive\r\n");
		header.put("Accept-Encoding", "gzip\r\n");

		System.out.println(content2.length());
		String u = site + categoryUrl;
		String r = HttpHelper.sendGet(u);

		System.out.println(StringHelper.convertUnicode(r));
	}

	private static void testHttpHelper() throws IOException {
		// String u = "http://admin.17xnr.com/test/test.jsp";
		Map<String, String> header = new HashMap<String, String>();
		header.put("Host", "2.blueface.cn\r\n");
		header.put("Content-Type", "application/x-www-form-urlencoded");
		header.put("Connection", "Keep-Alive\r\n");
		header.put("Accept-Encoding", "gzip\r\n");

		System.out.println(content2.length());
		String u = site + searchUrl;

		System.out.println(u);
		System.out.println(content2);

		HttpReturnData r = HttpHelper.postXmlData(u, content2);

		Map<String, String> returnHeader = r.getHeader();
		Iterator<String> it = returnHeader.keySet().iterator();
		while (it.hasNext()) {
			String k = it.next();
			String v = returnHeader.get(k);
			System.out.println("header key:" + k + " | value:" + v);
		}
		System.out.println(StringHelper.convertUnicode(r.getContent()));

	}

	private static void pswdAdmin() throws IOException {
		String url = "http://123.56.45.136/user/user/login";

		String r = HttpHelper.postHttpRquest(url, "username=123&password=123");

		System.out.println(r);
	}

	public static void main(String[] args) throws Exception {
		pswdAdmin();
		// testHttpHelper();
		// testConfig();
		// testCate();

	}
}
