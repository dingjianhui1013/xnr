package com.xnradmin.client.service.wx;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.cntinker.util.wx.connect.Text;
import com.cntinker.util.wx.connect.TextMessageF;
import com.cntinker.util.wx.connect.WXMessage;
import com.cntinker.util.wx.connect.WXMsgType;
import com.xnradmin.client.messag.resp.Article;
import com.xnradmin.client.messag.resp.NewsMessage;
import com.xnradmin.client.messag.resp.TextMessage;
import com.xnradmin.client.messag.resp.Voice;
import com.xnradmin.client.messag.resp.VoiceMessage;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.po.wx.connect.WXfInit;
import com.xnradmin.po.wx.connect.WXurl;


@Service("weiXinConnectService")
@Transactional
public class WeiXinConnectService {
	
	private static Logger log = Logger.getLogger(WeiXinConnectService.class);
	@Autowired
	private FarmerService farmerService;
	@Autowired
	private WXFarmerImageService wXFarmerImageService;
	@Autowired
	private BusinessGoodsService businessGoodsService;
	/***
	 * 服务号解析用户发消息XML
	 * @return
	 */
	public String processRequest() {
		HttpServletRequest request = ServletActionContext.getRequest();
        String respMessage = null;
        try {
            Map<String, String> requestMap = MessageUtil.parseXml(request); // xml请求解析
            String FromUserName = requestMap.get("FromUserName"); // 发送方帐号（open_id）
            String ToUserName = requestMap.get("ToUserName"); // 公众帐号
            String MsgType = requestMap.get("MsgType"); // 消息类型
            if (WXMsgType.REQ_MESSAGE_TYPE_TEXT.equals(MsgType))
  	      {
  	        String Content = requestMap.get("Content"); // 接收用户发送的文本消息内容
  	        String message = null;
  	        if(Content.startsWith("t"))
  	        {
  	        	String messages = Content.substring(1, Content.length());
  	        	String type=null;
  	        	String remarks=null;
  	        	if(messages.indexOf("tm")!=-1)
  	        	{
  	        		String m[]=messages.split("tm");
  	  	        	type=m[0];
  	  	        	remarks=m[1];
  	        	}else
  	        	{
  	  	        	type=messages;
  	        	}
  	        	log.debug("****************");
  	        		log.debug("type:"+type);
  	        		log.debug("remarks:"+remarks);
  	        	log.debug("****************");
//  	        	Map<String, Integer> index_count = wXFarmerImageService.read(FromUserName, type);
  	        	Map<String, Integer> index_count = wXFarmerImageService.findImageByUserId(FromUserName, type,remarks);
  	        	Iterator iter =index_count.entrySet().iterator(); 
  	        	while (iter.hasNext()) { 
  		        	Map.Entry entry = (Map.Entry) iter.next(); 
  		        	String key = (String)entry.getKey(); 
  		        	int val = (int)entry.getValue(); 
  		        	log.debug("****************");
  	        			log.debug("key:"+key);
  	        		log.debug("****************");
  		        	if(key.equals("0"))
  		        	{
  		        		if(val!=0)
  		        		{
  		        			message = "照片分类成功,剩余"+val+"张图片未分类";
  		        		}else{
  		        			message = "照片分类成功";
  		        		}
  		        	}else if(key.equals("1"))
  		        	{
  		        		message = "照片分类失败,请按照提示回复";
  		        	}else if(key.equals("2"))
  		        	{
  		        		message = "您上传的照片已经全部分类，无需重复提交！";
  		        	}
  		        	
  	        	}
  	        	respMessage = respfText(FromUserName, ToUserName, message, "1234567890123456");
  	        }else if(Content.equals("产品")){
  	        	String types = farmerService.getFenleiByUserId(FromUserName);
	  	    	List<BusinessGoods> list = businessGoodsService.getTypeNameById(types);
  	        	StringBuffer me = new StringBuffer();
	  	    	for (BusinessGoods businessGoods : list) {
	  				me.append(businessGoods.getGoodsName()+"\n");
	  			}
	  	    	message="您签约的产品有：\n"+me.toString();
  	        }else
  	        {
  		        message = "温馨提示：\n上传图片可直接回复图片或选择菜单上传"
  		        		+ "\n上传生产计划请选择菜单进行上传"
  		        		+ "\n查看个人信息请选择菜单进行查看";
  	        }
  	        respMessage = respfText(FromUserName, ToUserName, message, "1234567890123456");
  	      }
  	      if (WXMsgType.REQ_MESSAGE_TYPE_EVENT.equals(MsgType)) {
  	    	String event = requestMap.get("Event");
  	        if ("subscribe".equals(event))
  	        {
  	        	String message = null;
  	        	Farmer farmer = new Farmer();
  	        	farmer.setUserId(FromUserName);
  	        	String access_Token = WXFGetTokenService.accessTokenIsOvertime();
  	        	JSONObject userInformation = WeixinUtil.httpRequest(
  						WXurl.WXF_USERNAME_URL.replace("ACCESS_TOKEN",
  								access_Token).replace("OPENID",
  										FromUserName), "GET", null);
  	        	if(userInformation.toString().indexOf("40001")!=-1)
  	  		{
  	        	access_Token = WXFGetTokenService.getAccessToken();
  	  			WXFGetTokenService.writeAccessToken(access_Token, new Date().getTime(), ServletActionContext.getRequest());
  	  			userInformation = WeixinUtil.httpRequest(WXurl.WXF_USERNAME_URL.replace("ACCESS_TOKEN",access_Token).replace("OPENID",
										FromUserName), "GET", null);
  	  		}
  	        	log.debug("********************");
    			log.debug("userInformation::"+userInformation);
    			log.debug("********************");
  	        	farmer.setUserName(userInformation.getString("nickname"));
  	        	farmer.setHeadPortrait(userInformation.getString("headimgurl").substring(0, userInformation.getString("headimgurl").length()-1)+"64");
  	        	farmer.setStatus("0");
  	        	farmerService.saveFarmer(farmer);
  	        	message = "欢迎关注康源公社服务号\n"
  	        			+ "温馨提示：\n上传图片可直接回复图片或选择菜单上传"
   		        		+ "\n上传生产计划请选择菜单进行上传"
   		        		+ "\n查看个人信息请选择菜单进行查看";
  	        	respMessage = respfText(FromUserName, ToUserName, message, "1234567890123456");
  	        }
  	        if("unsubscribe".equals(event))
  	        {
  	        	Farmer farmer = new Farmer();
  	        	farmer.setUserName(FromUserName);
  	        	farmerService.delFarmer(farmer);
  	        }
  	      }
  	      if (WXMsgType.REQ_MESSAGE_TYPE_IMAGE.equals(MsgType)) {
  	        String picUrl = requestMap.get(WXMessage.PICURL);
  	        String status = farmerService.getStatus(FromUserName);
  	        String message = null;
  	        if(status.equals("0"))
  	        {
  	        	message ="点击上方文字进行填写信息";
  	        	String access_tokenF = WXFGetTokenService.accessTokenIsOvertime();
  	        	String messages = "<a href=\""+WXfInit.SERVICEURLW+"/xnr/page/wx/farmer/farmerExamine.action?farmerId="+FromUserName+"\">请前往页面填写审核信息。</a>";
  	        	Text text = new Text();
	  	  		text.setContent(messages);
	  	  		TextMessageF textMessageF =new TextMessageF();
	  	  		textMessageF.setTouser(FromUserName);
	  	  		textMessageF.setMsgtype("text");
	  	  		textMessageF.setText(text);
	  	  		String outputStr = JSONObject.fromObject(textMessageF).toString();
  	        	JSONObject jsons =  WeixinUtil.httpRequest(WXurl.WXF_MESSARW_TO_USER.replace("ACCESS_TOKEN", access_tokenF), "POST", outputStr);
  	        }else if(status.equals("2"))
  	        {
  	        	message = "审核资料已被拒绝，请等待审核！请前往链接进行修改。";
  	        	String access_tokenF = WXFGetTokenService.accessTokenIsOvertime();
  	        	String messages = "<a href=\""+WXfInit.SERVICEURLW+"/xnr/page/wx/farmer/farmerExamineEdit.action?farmerId="+FromUserName+"\">请前往页面填写审核信息。</a>";
  	        	Text text = new Text();
	  	  		text.setContent(messages);
	  	  		TextMessageF textMessageF =new TextMessageF();
	  	  		textMessageF.setTouser(FromUserName);
	  	  		textMessageF.setMsgtype("text");
	  	  		textMessageF.setText(text);
	  	  		String outputStr = JSONObject.fromObject(textMessageF).toString();
  	        	JSONObject jsons =  WeixinUtil.httpRequest(WXurl.WXF_MESSARW_TO_USER.replace("ACCESS_TOKEN", access_tokenF), "POST", outputStr);
  	        }
  	        	else if(status.equals("3"))
  	        {
  	        	message = "审核资料已提交，请等待审核！";
  	        }
  	        else
  	        {
//  	    	wXFarmerImageService.create(FromUserName, picUrl);
	  	    	wXFarmerImageService.save(FromUserName, picUrl);
	  	    	String types = farmerService.getFenleiByUserId(FromUserName);
	  	    	List<BusinessGoods> list = businessGoodsService.getTypeNameById(types);
	  	    	StringBuffer me = new StringBuffer();
	  	    	for (BusinessGoods businessGoods : list) {
	  				me.append("t"+businessGoods.getId()+businessGoods.getGoodsName()+"tm“照片备注：可以为空”\n");
	  			}
	  	    	message = "请按顺序回复上传图片分类：\n"+me.toString();
  	        }
  	    	respMessage = respfText(FromUserName, ToUserName, message, "1234567890123456");
  	      }
  	    }
  	    catch (Exception e) {
  	      e.printStackTrace();
  	    }
        return respMessage;
    }
	/***
	 * 企业号解析用户发消息XML
	 * @param sMsg
	 * @return
	 */
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
	      NodeList nodelist6 = root.getElementsByTagName(WXMessage.PICURL);
	      String ToUserName = nodelist2.item(0).getTextContent();
	      String FromUserName = nodelist3.item(0).getTextContent();
	      String MsgType = nodelist4.item(0).getTextContent();
	      String AgentID = nodelist5.item(0).getTextContent();
	      if (WXMsgType.REQ_MESSAGE_TYPE_TEXT.equals(MsgType))
	      {
	        NodeList nodelist1 = root.getElementsByTagName(WXMessage.CONTENT);
	        String Content = nodelist1.item(0).getTextContent();
	        String message = null;
	        if(Content.indexOf("t")!=-1)
	        {
	        	String type=Content.substring(1, Content.length());
	        	Map<String, Integer> index_count = wXFarmerImageService.read(FromUserName, type);
	        	Iterator iter =index_count.entrySet().iterator(); 
	        	while (iter.hasNext()) { 
		        	Map.Entry entry = (Map.Entry) iter.next(); 
		        	String key = (String)entry.getKey(); 
		        	int val = (int)entry.getValue(); 
		        	if(key.equals("0"))
		        	{
		        		if(val!=0)
		        		{
		        			message = "照片分类成功,剩余"+val+"张图片未分类";
		        		}else{
		        			message = "照片分类成功";
		        		}
		        	}else if(key.equals("1"))
		        	{
		        		message = "照片分类失败,请按照提示回复";
		        	}else if(key.equals("2"))
		        	{
		        		message = "您上传的照片已经全部分类，无需重复提交！";
		        	}
		        	
	        	}
	        }else
	        {
		        message = "温馨提示：\n上传图片可直接回复图片或选择菜单上传"
		        		+ "\n上传生产计划请选择菜单进行上传"
		        		+ "\n查看个人信息请选择菜单进行查看";
	        }
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
	        String picUrl = root.getElementsByTagName(WXMessage.PICURL).item(0).getTextContent();
	    	wXFarmerImageService.create(FromUserName, picUrl);
	    	String types = farmerService.getFenleiByUserId(FromUserName);
	    	List<BusinessGoods> list = businessGoodsService.getTypeNameById(types);
	    	StringBuffer me = new StringBuffer();
	    	for (BusinessGoods businessGoods : list) {
				me.append("t"+businessGoods.getId()+businessGoods.getGoodsName()+"\n");
			}
	    	String message = "请按顺序回复上传图片分类：\n"+me.toString();
	    	respMessage = respText(FromUserName, ToUserName, message, AgentID, "1234567890123456");
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
	private String respfText(String FromUserName, String ToUserName, String text, String MsgId) {
	    TextMessage textMessage = new TextMessage();
	    textMessage.setToUserName(FromUserName);
	    textMessage.setFromUserName(ToUserName);
	    textMessage.setCreateTime(new Date().getTime());
	    textMessage.setMsgType("text");
	    textMessage.setMsgId(MsgId);
	    textMessage.setContent(text);
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
	public static String emoji(int hexEmoji) {  
        return String.valueOf(Character.toChars(hexEmoji));  
    }
}
