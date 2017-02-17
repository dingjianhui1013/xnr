/**
 *2014年9月9日 下午6:47:08
 */
package com.xnradmin.script.business.test;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.cntinker.util.HttpHelper;

/**
 * @author: liubin
 *
 */
public class TestHttp {
	private static Logger log = Logger.getLogger(TestHttp.class);
	private static void testWxUserMsg() throws IOException {
		String url = "http://localhost:8080/port/wx/sync.jsp?userid=1";
		String content = "<xml><ToUserName><![CDATA[gh_7f42da2a2dc4]]></ToUserName><FromUserName><![CDATA[ohmUCj1104_W4N7h3c1EcJ3Qlsf0]]></FromUserName><CreateTime>1410258821</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[哈哈]]></Content><MsgId>6057015515291532844</MsgId></xml>";

		String res = HttpHelper.postXml(url, content, "utf-8");
		log.debug(res);
	}

	public static void main(String[] args) throws Exception {
		testWxUserMsg();
	}
}
