package com.xnradmin.core.sms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.sms.conf.SmsConfig;
import com.xnradmin.core.util.HttpClientUtil;

public class SmsService {
	/**
	 * 发送短信Get方式,无xml返回
	 * 
	 * @param sendInfoMap
	 * @return
	 */
	public static boolean sendSmsGet(Map<String, String> sendInfoMap) {
		if (sendInfoMap == null || sendInfoMap.size() <= 0) {
			return false;
		}
		String url = SmsConfig.SMS_SEND_GET_URL;
		StringBuilder sb = new StringBuilder();
		sb.append(url);
		sb.append("?");
		sb.append("UserName=" + SmsConfig.USER_NAME);
		sb.append("&UserPwd=" + SmsConfig.USER_PWD);
		sb.append("&Mobile=" + sendInfoMap.get("Mobile"));
		sb.append("&Message=" + sendInfoMap.get("Message"));

		String result = HttpClientUtil.get(sb.toString(),false);
		return result == "0" ? true : false;
	}

	/**
	 * 发送短信 post方式,无xml返回
	 * 
	 * @param sendInfoMap
	 * @return
	 */
	public static boolean sendSmsPost(Map<String, String> sendInfoMap) {
		if (sendInfoMap == null || sendInfoMap.size() <= 0) {
			return false;
		}
		String url = SmsConfig.SMS_SEND_POST_URL;
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		sb.append("<Note>");
		sb.append("<UserName>" + SmsConfig.USER_NAME + "</UserName>");
		sb.append("<UserPwd>" + SmsConfig.USER_PWD + "</UserPwd>");
		sb.append("<Mobile>" + sendInfoMap.get("Mobile") + "</Mobile>");
		sb.append("<Message>" + sendInfoMap.get("Message") + "</Message>");
		sb.append("</Note>");

		String result = HttpClientUtil.postXml(url, sb.toString(), false);
		return result == "0" ? true : false;

	}

	/**
	 * 发送短信并获得msgId（号码提交唯一值）
	 * 
	 * @param sendInfoMap
	 * @return
	 */
	public static List<Map<String, String>> postReport(
			Map<String, String> sendInfoMap) {
		if (sendInfoMap == null || sendInfoMap.size() <= 0) {
			return null;
		}
		String url = SmsConfig.SMS_REPORT_POST_URL;
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		sb.append("<Note>");
		sb.append("<UserName>" + SmsConfig.USER_NAME + "</UserName>");
		sb.append("<UserPwd>" + SmsConfig.USER_PWD + "</UserPwd>");
		sb.append("<Mobile>" + sendInfoMap.get("Mobile") + "</Mobile>");
		sb.append("<Message>" + sendInfoMap.get("Message") + "</Message>");
		sb.append("</Note>");
		System.out.println(url);
		System.out.println(sb.toString());

		String result = HttpClientUtil.postXml(url, sb.toString(), true);
		System.out.println(result);

		List<Map<String, String>> sendlist = null;
		if (result != "" && !result.equals("")) {

			Map<String, String> sendResMap;
			sendlist = new ArrayList<Map<String, String>>();
			Document doc = null;

			try {
				doc = DocumentHelper.parseText(result);
				Element root = doc.getRootElement();
				List<Element> elementList = root.elements();
				String res = root.element("Result").getText();

				if (res != "" && res.equals("0")) {
					List<Element> reportList = root.elements("Report");
					for (Element element : reportList) {
						sendResMap = new HashMap<String, String>();
						String msgID = element.element("MsgID").getText();
						String phone = element.element("Mobile").getText();
						System.out.println(msgID);
						System.out.println(phone);
						if (msgID != null && !msgID.equals("")) {
							sendResMap.put("msgID", msgID);
							sendResMap.put("phone", phone);
							sendlist.add(sendResMap);
						}
					}
				}

			} catch (DocumentException e) {
				System.out.println(e);
			}
		}
		return sendlist;
	}

	/**
	 * 获取状态报告 (每次获取500条)
	 * 
	 * @return
	 */
	public static List<Map<String, String>> showReport() {

		String url = SmsConfig.SMS_SHOW_REPORT_URL;
		StringBuilder sb = new StringBuilder();
		sb.append(url);
		sb.append("?");
		sb.append("UserName=" + SmsConfig.USER_NAME);
		sb.append("&UserPwd=" + SmsConfig.USER_PWD);
		System.out.println(url);

		String result = HttpClientUtil.get(sb.toString(), true);
		System.out.println(result);

		List<Map<String, String>> reportReslist = null;
		if (result != "" && !result.equals("")) {
			Map<String, String> reportResMap;
			reportReslist = new ArrayList<Map<String, String>>();
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(result);
				Element root = doc.getRootElement();
				List<Element> elementList = root.elements();
				String res = root.element("Result").getText();

				if (res != "" && res.equals("0")) {
					List<Element> reportList = root.elements("Report");
					for (Element element : reportList) {
						reportResMap = new HashMap<String, String>();
						String msgID = element.element("MsgID").getText();
						String phone = element.element("Mobile").getText();
						String statusTime = element.element("StatusTime").getText();
						String status=element.element("Status").getText();
						System.out.println(msgID);
						System.out.println(phone);
						System.out.println(statusTime);
						if (!StringHelper.isNull(status)) {
							reportResMap.put("msgID", msgID);
							reportResMap.put("phone", phone);
							reportResMap.put("statusTime", statusTime);
							reportResMap.put("status", status);
							reportReslist.add(reportResMap);
						}
					}
				}

			} catch (DocumentException e) {
				System.out.println(e);
			}
		}

		return reportReslist;
	}

}
