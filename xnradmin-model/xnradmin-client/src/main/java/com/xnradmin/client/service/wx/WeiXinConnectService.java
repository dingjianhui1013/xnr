package com.xnradmin.client.service.wx;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.cntinker.util.wx.connect.MessageUtil;
import com.cntinker.util.wx.connect.WXMessage;
import com.cntinker.util.wx.connect.WXMsgType;
import com.xnradmin.client.messag.resp.Article;
import com.xnradmin.client.messag.resp.NewsMessage;
import com.xnradmin.client.messag.resp.TextMessage;
import com.xnradmin.client.messag.resp.Voice;
import com.xnradmin.client.messag.resp.VoiceMessage;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.po.wx.connect.WXurl;


@Service("weiXinConnectService")
@Transactional
public class WeiXinConnectService {
	
	@Autowired
	private FarmerService farmerService;
	
	public String processRequest(String sMsg)
	  {
	    String respMessage = null;
	    try {
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	      DocumentBuilder db = dbf.newDocumentBuilder();
	      StringReader sr = new StringReader(sMsg);
	      InputSource is = new InputSource(sr);
	      Document document = db.parse(is);
	      Element root = document.getDocumentElement();
	      NodeList nodelist2 = root.getElementsByTagName(WXMessage.TO_USER_NAMR);
	      NodeList nodelist3 = root.getElementsByTagName(WXMessage.FROM_USER_NAMR);
	      NodeList nodelist4 = root.getElementsByTagName(WXMessage.MSG_TYPE);
	      NodeList nodelist5 = root.getElementsByTagName(WXMessage.AGENT_ID);
	      String ToUserName = nodelist2.item(0).getTextContent();
	      String FromUserName = nodelist3.item(0).getTextContent();
	      String MsgType = nodelist4.item(0).getTextContent();
	      String AgentID = nodelist5.item(0).getTextContent();
	      if (WXMsgType.REQ_MESSAGE_TYPE_TEXT.equals(MsgType))
	      {
	        NodeList nodelist1 = root.getElementsByTagName(WXMessage.CONTENT);
	        String Content = nodelist1.item(0).getTextContent();
	        String message = null;
	        message = "测试文字";
	        respMessage = respText(FromUserName, ToUserName, message, AgentID, "1234567890123456");
	      }
	      if (WXMsgType.REQ_MESSAGE_TYPE_EVENT.equals(MsgType)) {
	        NodeList eventType = root.getElementsByTagName("Event");
	        String event = eventType.item(0).getTextContent();
	        if ("subscribe".equals(event))
	        {
	        	Farmer farmer = new Farmer();
	        	farmer.setUserId(FromUserName);
	        	String access_Token = WXGetTokenService.accessTokenIsOvertime();
	        	JSONObject userInformation = WeixinUtil.httpRequest(
						WXurl.WX_USERNAME_URL.replace("ACCESS_TOKEN",
								access_Token).replace("USERID",
										FromUserName), "GET", null);
	        	farmer.setUserName(userInformation.getString("name"));
	        	farmer.setHeadPortrait(userInformation.getString("avatar"));
	        	farmerService.saveFarmer(farmer);
	        }
	      }
	      if (WXMsgType.REQ_MESSAGE_TYPE_IMAGE.equals(MsgType)) {
	    	  
	    	  
	    	  
	        String picUrl = root.getElementsByTagName(WXMessage.AGENT_ID).item(0).getTextContent();
	    	
//	        String title = "测试Titile";
//	        int articleCount = 1;
//	        String description = "我就是想测试一下这个是不是好使";
//	        String url = "http://www.baidu.com";
//	        String picUrl = "http://weixin.robustsoft.cn/weixin/images/ceshi.jpg";
//	        respMessage = respNews(FromUserName, ToUserName, AgentID, "1234567890123456", title, articleCount, description, picUrl, url);
	      }
	      if (WXMsgType.REQ_MESSAGE_TYPE_VOICE.equals(MsgType))
	      {
	        String MediaId = "1zA1prOxQxKC6dQyybzpHJSuutZ8WaGNPkSy_2DOgnd6-gKHW89rUT87-QkXxuBacIzbJdXFXbdSFWmUCJTwkeA";
	        respMessage = respVoice(FromUserName, ToUserName, MediaId);
	      }
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    return respMessage; }

	  private String respText(String FromUserName, String ToUserName, String text, String AgentID, String MsgId) {
	    TextMessage textMessage = new TextMessage();
	    textMessage.setToUserName(FromUserName);
	    textMessage.setFromUserName(ToUserName);
	    textMessage.setCreateTime(new Date().getTime());
	    textMessage.setMsgType("text");
	    textMessage.setMsgId(MsgId);
	    textMessage.setContent(text);
	    textMessage.setAgentId(AgentID);
	    return MessageUtil.textMessageToXml(textMessage);
	  }

	  private String respNews(String FromUserName, String ToUserName, String AgentID, String MsgId, String titile, int articleCount, String description, String picUrl, String url) {
	    NewsMessage newsMessage = new NewsMessage();
	    newsMessage.setCreateTime(new Date().getTime());
	    newsMessage.setToUserName(FromUserName);
	    newsMessage.setFromUserName(ToUserName);
	    newsMessage.setArticleCount(articleCount);
	    List articles = new ArrayList();
	    Article article = new Article();
	    article.setTitle(titile);
	    article.setDescription(description);
	    article.setUrl(url);
	    articles.add(article);
	    article.setPicUrl(picUrl);
	    newsMessage.setArticles(articles);
	    newsMessage.setMsgType("news");
	    newsMessage.setAgentId(AgentID);
	    newsMessage.setMsgId(MsgId);
	    return MessageUtil.newsMessageToXml(newsMessage);
	  }

	  private String respVoice(String FromUserName, String ToUserName, String MediaId) {
	    VoiceMessage voiceMessage = new VoiceMessage();
	    voiceMessage.setToUserName(FromUserName);
	    voiceMessage.setFromUserName(ToUserName);
	    voiceMessage.setCreateTime(new Date().getTime());
	    voiceMessage.setMsgType("voice");
	    Voice voice = new Voice();
	    voice.setMediaId(MediaId);
	    voiceMessage.setVoice(voice);

	    return MessageUtil.voiceMessageToXml(voiceMessage);
	  }
	public JSONObject getUserId(String code)
	{
		String access_tokenString = WXGetTokenService.accessTokenIsOvertime();
		JSONObject userId = WeixinUtil.httpRequest(
				WXurl.WX_USERID_URL.replace("ACCESS_TOKEN", access_tokenString)
						.replace("CODE", code), "GET", null);
		return userId;
	}
}
