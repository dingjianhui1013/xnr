/**
 *2014年9月9日 下午8:41:06
 */
package com.xnradmin.script.business.test;

import java.io.IOException;

import org.codehaus.commons.compiler.CompileException;
import org.json.JSONException;
import org.json.JSONObject;

import com.xnradmin.core.util.ScriptHelper;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.dto.ScriptDTO;

/**
 * @author: liubin
 *
 */
public class TestWXScript {

	private static void testWxUserMessage() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String content = "<xml><ToUserName><![CDATA[gh_7f42da2a2dc4]]></ToUserName><FromUserName><![CDATA[ohmUCj1104_W4N7h3c1EcJ3Qlsf0]]></FromUserName><CreateTime>1410332842</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[2]]></Content><MsgId>6057333433065760271</MsgId>webwx_msg_cli_ver_0x1</xml>";
		String wxuserid = "1";

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.wx.WXUserMessage");
		Object out = scriptHelper.executeMethod("execute", dto, wxuserid,
				content);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		System.out.println("out: " + out);
	}

	private static void testWXUserEvent() throws CompileException,
			ClassNotFoundException, IOException {
		String content = "<xml><ToUserName><![CDATA[gh_7f42da2a2dc4]]></ToUserName><FromUserName><![CDATA[ohmUCj1104_W4N7h3c1EcJ3Qlsf0]]></FromUserName><CreateTime>1410268629</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[CLICK]]></Event><EventKey><![CDATA[zx]]></EventKey></xml>";
		String wxuserid = "1";
		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.wx.WXUserMessage");
		Object out = scriptHelper.executeMethod("execute", dto, wxuserid,
				content);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		System.out.println("out: " + out);
	}
	
	
	private static void testUrl (){
		String url = "http://www.17xnr.com/index.jsp?test=temp";
		String u = getUrl(url,"uid=123");
		System.out.println(u);
	}
	
	private static String getUrl(String sourceUrl, String parameter) {
		StringBuffer sb = new StringBuffer();
		sb.append(sourceUrl);
		if (sourceUrl.endsWith("/") || sourceUrl.indexOf("?") < 0) {
			sb.append("?");
		} else {
			sb.append("&");
		}
		sb.append(parameter);
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		testWxUserMessage();
		//testUrl();
	}
}
