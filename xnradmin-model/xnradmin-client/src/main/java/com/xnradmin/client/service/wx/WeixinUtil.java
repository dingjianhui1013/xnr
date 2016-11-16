package com.xnradmin.client.service.wx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import com.cntinker.util.wx.connect.MyX509TrustManager;
import com.xnradmin.client.service.wx.WXGetTokenService;
import com.xnradmin.po.wx.connect.Button;
import com.xnradmin.po.wx.connect.Menu;
import com.xnradmin.po.wx.connect.ViewButton;
import com.xnradmin.po.wx.connect.WXInit;
public class WeixinUtil
{
  public static int createMenu()
  {		
	  ViewButton btn3 = new ViewButton();
	  btn3.setName("个人中心");
	  btn3.setType("view");
	  btn3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WXInit.CORPID+"&redirect_uri=http%3a%2f%2fweixin.robustsoft.cn%2fxnr%2fpage%2fwx%2fpersonalCenter%2flist.action&response_type=code&scope=SCOPE&state=STATE#wechat_redirect");
	  
	  ViewButton btn2 = new ViewButton();
	  btn2.setName("生产计划");
	  btn2.setType("view");
	  btn2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WXInit.CORPID+"&redirect_uri=http%3a%2f%2fweixin.robustsoft.cn%2fxnr%2fpage%2fwx%2foutplan%2foutplan.action&response_type=code&scope=SCOPE&state=STATE#wechat_redirect");
	  
	  ViewButton btn1 = new ViewButton();
	  btn1.setName("上传图片");
	  btn1.setType("view");
	  btn1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WXInit.CORPID+"&redirect_uri=http%3a%2f%2fweixin.robustsoft.cn%2fxnr%2fpage%2fwx%2fwxconnect%2foAuth.action&response_type=code&scope=SCOPE&state=STATE#wechat_redirect");
	  Menu menu  = new Menu();
	  menu.setButton(new Button[]{btn1,btn2,btn3});
	  String jsonMenu = JSONObject.fromObject(menu).toString();
	  int result = 0;
	  WXGetTokenService.accessTokenIsOvertime();
	  String access_tokenString = WXGetTokenService.accessTokenIsOvertime();
	  JSONObject jsonObject = httpRequest("https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_tokenString + "&agentid="+WXInit.AGENT_ID, "POST", jsonMenu);
    if ((jsonObject != null) && 
      (jsonObject.getInt("errcode") != 0)) {
      result = jsonObject.getInt("errcode");
    }
    return result;
  }

  public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr)
  {
    JSONObject jsonObject = null;
    StringBuffer buffer = new StringBuffer();
    try
    {
      TrustManager[] tm = { new MyX509TrustManager() };
      SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
      sslContext.init(null, tm, new SecureRandom());

      SSLSocketFactory ssf = sslContext.getSocketFactory();

      URL url = new URL(requestUrl);
      HttpsURLConnection httpUrlConn = (HttpsURLConnection)url
        .openConnection();
      httpUrlConn.setSSLSocketFactory(ssf);
      httpUrlConn.setDoOutput(true);
      httpUrlConn.setDoInput(true);
      httpUrlConn.setUseCaches(false);

      httpUrlConn.setRequestMethod(requestMethod);
      if ("GET".equalsIgnoreCase(requestMethod)) {
        httpUrlConn.connect();
      }
      if (outputStr != null) {
        OutputStream outputStream = httpUrlConn.getOutputStream();

        outputStream.write(outputStr.getBytes("UTF-8"));
        outputStream.close();
      }

      InputStream inputStream = httpUrlConn.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(
        inputStream, "utf-8");
      BufferedReader bufferedReader = new BufferedReader(
        inputStreamReader);
      String str = null;
      while ((str = bufferedReader.readLine()) != null) {
        buffer.append(str);
      }
      bufferedReader.close();
      inputStreamReader.close();

      inputStream.close();
      inputStream = null;
      httpUrlConn.disconnect();
      jsonObject = JSONObject.fromObject(buffer.toString());
    }
    catch (ConnectException localConnectException) {
    }
    catch (Exception localException) {
    }
    return jsonObject;
  }
}