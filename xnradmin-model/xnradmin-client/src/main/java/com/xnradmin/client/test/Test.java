/**
 *2014年9月9日 下午6:42:48
 */
package com.xnradmin.client.test;

import java.net.URLEncoder;

import org.dom4j.DocumentException;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.util.WxUtil;

/**
 * @author: liubin
 *
 */
public class Test {

	private static void test() throws IllegalAccessException, DocumentException {
		String source = "<xml><ToUserName><![CDATA[gh_7f42da2a2dc4]]></ToUserName><FromUserName><![CDATA[ohmUCj1104_W4N7h3c1EcJ3Qlsf0]]></FromUserName><CreateTime>1410258821</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[哈哈]]></Content><MsgId>6057015515291532844</MsgId></xml>";
		WxUtil wxutil = new WxUtil(source);
		System.out.println(wxutil.getDataFromKey("MsgType"));
	}
	
	private static void testString(){
		String productName = "测试商品";
		String res = "http%3A%2F%2Fadmin.17xnr.com%2Fmall%2Fportal%2Fwx%2Fdemo%2Fpayack.jsp";
		String url = "http://admin.17xnr.com/mall/portal/demo/payack.jsp";
		System.out.println(URLEncoder.encode(productName));
		System.out.println(URLEncoder.encode(url));
	}
	
	
	private static void testPath(){
		String uri = "/script/com/xnradmin/script/business/ScriptCustomerServicePhone.java";
		
		System.out.println(uri.startsWith("/script"));
	}
	public static void main(String[] args) throws Exception {
		testPath();
		;
		System.out.println(StringHelper.formartDecimalToStr(0.89f));
	}
}
