<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="utf-8"%>
<%@ page import="com.xnradmin.core.pay.wxpay.*"%>
<%@page import="java.util.TreeMap"%>
<%@ page import="com.xnradmin.core.pay.wxpay.client.TenpayHttpClient"%>
<%@page import="java.util.SortedMap"%>
<%@page import="com.xnradmin.core.pay.wxpay.util.*"%>
<%@page import="com.xnradmin.core.pay.wxpay.xnrutil.*"%>
<%@ page import="java.io.BufferedWriter"%>
<%@ page import="java.io.BufferedOutputStream"%>
<%@ page import="java.io.OutputStream"%>
<%@page import="com.google.gson.Gson"%>
<%@ page import="com.xnradmin.po.wx.*"%>
<%@page import="com.xnradmin.core.util.*"%>
<%@page import="com.cntinker.util.*"%>
<%@ page import="org.apache.commons.logging.*"%>
<%@ page import="com.xnradmin.core.pay.wxpay.*"%>
<%@ page import="com.xnradmin.client.service.wx.*"%>
<%@ page import="com.xnradmin.vo.client.wx.*"%>
<%@ page import="com.xnradmin.core.service.mall.order.*"%>
<%@ page import="com.xnradmin.po.mall.order.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>

<%
Log log = LogFactory.getLog("wxpay");
Log exlog = LogFactory.getLog("ex");
try{
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	
	log.debug("this is wxpay - payack");
	
	log.debug("basePath:"+basePath);
	Enumeration e = request.getParameterNames();
	while(e.hasMoreElements()){
	    String k = e.nextElement().toString();
	    String v = request.getParameter(k);	    
	    log.debug("key: "+k+" | value: "+v);
	}
	
	BufferedReader br = new BufferedReader(new InputStreamReader(
			request.getInputStream()));
	StringBuffer sb = new StringBuffer();
	String currentLine = "";
	while ((currentLine = br.readLine()) != null) {
		sb.append(currentLine);
	}
	String source = sb.toString();
	log.debug(source);
	
	WXPayInfoService payService = (WXPayInfoService)SpringBase.getCtx().getBean("WXPayInfoService");
	WXPayInfoVO v = payService.findByWxUserid(1l);
	SendNotifyService notifyService = (SendNotifyService)SpringBase.getCtx().getBean("SendNotifyService");
	WXAccessTokenService service = (WXAccessTokenService) SpringBase
			.getCtx().getBean("WXAccessTokenService");
	WXAccessToken tk = service.findByWxuserId(1l, false);
			
	String tokenId = tk.getAccessToken();
	 
	String notifyUrl = "https://api.weixin.qq.com/pay/delivernotify?access_token="+ tokenId;
	
	WxUtil wx = new WxUtil(source);
	String appid = wx.getDataFromKey("AppId");
	String userOpenid = wx.getDataFromKey("OpenId");
	String transaction_id = request.getParameter("transaction_id");
	String out_trade_no = request.getParameter("out_trade_no");
	String curTime = StringHelper.toUnixTimestamp(System.currentTimeMillis()).toString();
	
	v.setDeliver_msg("ok");
	v.setDeliver_status("1");
	v.setOpenuid(userOpenid);
	v.setTransid(transaction_id);
	v.setOutTradeNo(out_trade_no);
	v.setOldTimestamp(curTime);
	
	String sign = notifyService.getSignOut(v);
	
	log.debug("sign:"+sign);
	
	
	StringBuffer o = new StringBuffer();
	// 公众平台账户的AppId；
	o.append("{");
	o.append("\"appid\":\"").append(appid).append("\"");
	// 贩买用户的OpenId，这个已经放在最终支付结果通知的PostData里了；
	o.append(",\"openid\":\"").append(userOpenid).append("\"");
	// 交易单号；
	o.append(",\"transid\":\"").append(transaction_id).append("\"");
	// 第三方订单号；
	o.append(",\"out_trade_no\":\"").append(out_trade_no).append("\"");
	// 发货时间戳，这里指的是Linux时间戳；
	o.append(",\"deliver_timestamp\":\"").append(curTime).append("\"");
	// 发货状态，1表明成功，0表明失败，失败时需要在deliver_msg填上失败原因；
	o.append(",\"deliver_status\":\"").append("1").append("\"");
	// 发货状态信息，失败时可以填上UTF8编码的错诨提示信息，比如“该商品已退款”；
	o.append(",\"deliver_msg\":\"").append("ok").append("\"");
	// 根据支付签名（paySign）生成方法中所讲的签名方式生成的，参加签名字段为：appid、appkey、openid、transid、out_trade_no、deliver_timestamp、deliver_status、deliver_msg；
	o.append(",\"app_signature\":\"").append(sign).append("\"");
	// 签名方法（不计入签名生成）；
	o.append(",\"sign_method\":\"").append("sha1").append("\"");
	o.append("}");
	
	log.debug("notifyUrl:"+notifyUrl);
	log.debug("body:"+o.toString());
	String res = HttpHelper.postXml(notifyUrl, o.toString(), "UTF-8");
	log.debug("sign res: "+res);
	log.debug("order id : "+out_trade_no);
	JSONObject jso = new JSONObject(res);
	String code = jso.get("errcode").toString();
	OrderRecordService orderService = (OrderRecordService)SpringBase.getCtx().getBean("OrderRecordService");
	OrderRecord order = orderService.findByOutOderNo(out_trade_no);
	log.debug("order is null?"+(order==null));
	log.debug("code:"+code);
	
	if(code.equals("0")){
		if(order!=null && order.getOperateStatus()!=null
				&& order.getPaymentStatus()!=null
				&& order.getOperateStatus()!=170 
				&& order.getPaymentStatus()!=165){
			order.setPaymentStatus(165);
			order.setPaymentStatusName("已支付");
			order.setOperateStatus(169);
			order.setOperateStatusName("处理中");
			order.setPaymentTime(new Timestamp(System.currentTimeMillis()));
			order.setOperateTime(new Timestamp(System.currentTimeMillis()));
			orderService.modify(order);
			log.debug("order is modify success ");
		}
	}
}catch(Exception e){
	exlog.error(StringHelper.getStackInfo(e));
}
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<