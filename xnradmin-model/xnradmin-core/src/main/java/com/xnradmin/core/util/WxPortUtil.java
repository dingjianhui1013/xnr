/**
 *2014年8月25日 下午7:22:06
 */
package com.xnradmin.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cntinker.util.CookieHelper;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.dto.wx.WxUserCookie;
import com.xnradmin.po.common.status.Status;

/**
 * @author: liubin
 * 
 */
public class WxPortUtil {

	/**
	 * 微信发送被动消息
	 * 
	 * @param fromUserName
	 *            - 公众号标识
	 * @param toUserName
	 *            - 微信用户标识
	 * @param createTime
	 * @param content
	 * @return String
	 */
	public static String sendReTextMsg(String fromUserName, String toUserName,
			String createTime, String content) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[" + toUserName + "]]></FromUserName>");
		sb.append("<CreateTime>" + createTime + "</CreateTime>");
		sb.append("<MsgType><![CDATA[text]]></MsgType>");
		sb.append(" <Content><![CDATA[" + content + "]]></Content>");
		sb.append(" </xml>");
		return sb.toString();
	}

	public static void setWxOpenUidCookie(HttpServletResponse response,
			String wxuseropenuid) {
		StatusService statusService = (StatusService) SpringBase.getCtx()
				.getBean("StatusService");

		Status status = statusService.findByStatusCodeOne(WxUserCookie.class,
				"1");

		CookieHelper.addCookie(response, status.getStatusName(), wxuseropenuid,
				365 * 24 * 60 * 60);
	}

	public static Cookie getWxOpenUidCookie(HttpServletRequest request) {
		StatusService statusService = (StatusService) SpringBase.getCtx()
				.getBean("StatusService");

		Status status = statusService.findByStatusCodeOne(WxUserCookie.class,
				"1");
		Cookie cookie = CookieHelper.getCookieByName(request,
				status.getStatusName());

		return cookie;

	}

	public static void main(String[] args) throws Exception {
		System.out.println("");
	}
}
