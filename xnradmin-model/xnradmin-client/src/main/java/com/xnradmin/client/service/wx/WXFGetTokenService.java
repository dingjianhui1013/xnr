package com.xnradmin.client.service.wx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.mysql.jdbc.StringUtils;
import com.xnradmin.po.wx.connect.WXInit;
import com.xnradmin.po.wx.connect.WXfInit;
import com.xnradmin.po.wx.connect.WXurl;

public class WXFGetTokenService {

	public static String accessTokenIsOvertime()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String fileName=request.getSession().getServletContext().getRealPath("/WEB-INF/classes/wx/AccessTokenF.xml");
		SAXReader saxReader = new SAXReader();
		File inputFile = new File(fileName);
		String accessToken  = "";
        Document document;
		try {
			document = saxReader.read(inputFile);
			Element root = document.getRootElement();
	        Element atElement = (Element) root.selectSingleNode("/xml/AccessToken");
	        Element etElement = (Element) root.selectSingleNode("/xml/AccessExpires");
	        accessToken = atElement.getText();
	        String expiresTime = etElement.getText();
	        long nowTime = new Date().getTime();
	        if(StringUtils.isNullOrEmpty(accessToken)||StringUtils.isNullOrEmpty(expiresTime))
	        {
	        	accessToken = getAccessToken();
	        	long time = new Date().getTime();
	        	writeAccessToken(accessToken, time, request);
	        }else if(!StringUtils.isNullOrEmpty(accessToken)||!StringUtils.isNullOrEmpty(expiresTime))
	        { 
	        	long expirest = Long.parseLong(expiresTime);
	        	if(nowTime>expirest)
	        	{
	        		accessToken = getAccessToken();
	        		long time = new Date().getTime();
	        		writeAccessToken(accessToken, time, request);
	        	}
	        }
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return accessToken;
	}
	public static String getAccessToken()
	{
//		 JSONObject access_token = WeixinUtil.httpRequest("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+WXInit.CORPID+"&corpsecret="+WXInit.CORPSECRET, "GET", null);
		 JSONObject access_token = WeixinUtil.httpRequest(WXurl.WXF_ACCESS_TOKEN_URL.replace("APPID", WXfInit.APPID).replace("APPSECRET", WXfInit.APPSECRET),"GET",null);
		 String access_tokenString = access_token.getString("access_token");
		 return access_tokenString;
	}
	public static void writeAccessToken(String accessToken,long time,HttpServletRequest request)
	{
		String fileName=request.getSession().getServletContext().getRealPath("/WEB-INF/classes/wx/AccessTokenF.xml");
		SAXReader saxReader = new SAXReader();
		File inputFile = new File(fileName);
        try {
        	String expiresTime = (time+7200000)+"";
			Document document = saxReader.read(inputFile);
			Element root = document.getRootElement();
			Element e = (Element) root.selectSingleNode("/xml/AccessToken");
			Element e1 = (Element) root.selectSingleNode("/xml/AccessExpires");
			e.setText(accessToken);
			e1.setText(expiresTime);
			XMLWriter output = new XMLWriter(new FileWriter(new File(fileName))); //
			output.write(document);
			output.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
