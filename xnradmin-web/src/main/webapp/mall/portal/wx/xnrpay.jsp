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
<%@ page import="com.xnradmin.core.service.mall.order.OrderRecordService"%>
<%@ page import="com.xnradmin.po.mall.order.*" %>
<%@ page import="com.xnradmin.core.service.mall.order.*"%>
<%@ include file="config.jsp"%>

<%

//=================================
//jsapi接口
//=================================
//初始化
	Log log = Log4jUtil.getLog("wxpay");
	Log exlog = Log4jUtil.getLog("ex");
	String sign = "";
	String orderNo = "";
	try{
		orderNo = request.getParameter("orderId");
		log.debug("order gen : orderNo"+orderNo);
		
		if(StringHelper.isNull(orderNo) || orderNo.equals("undefined")){
			String u = "http://www.17xnr.com/waitting.html";
			response.sendRedirect(u);
			return ;
		}
		 
		WXPayInfoService payService = (WXPayInfoService)SpringBase.getCtx().getBean("WXPayInfoService");
		OrderRecordService orderService = (OrderRecordService)SpringBase.getCtx().getBean("OrderRecordService");
		OrderRecord order = orderService.findByid(orderNo);
		
		WXPayInfoVO v = payService.findByWxUserid(1l);
		//v.setProductName(order.getId().toString());
		v.setProductName(order.getOrderNo());
		
		String total = order.getTotalPrice().toString();
		
		total = total.trim().replaceAll("\\.", "");
		Integer t = new Integer(total);
		v.setTotalFee(t.toString());
		v.setClientIp(HttpHelper.getIp(request));
		v.setOutTradeNo(order.getOrderNo());
		String rr = payService.getSignOut(v);
		//生成支付签名
		sign = payService.getSignOut(v);
		log.debug(sign);
	}catch(Exception e){
		//e.printStackTrace();
		exlog.error(StringHelper.getStackInfo(e));
		out.println("系统错误，请返回我的订单支付,<a href=\"waitting.html\">返回</a>");
	}
%>


<html>
	<head>
		<script language="javascript">
		
		 document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
			 WeixinJSBridge.invoke('getBrandWCPayRequest',<%=sign%>,function(res){
					WeixinJSBridge.log(res.err_msg);
					//alert('res.err_code:'+res.err_code +' | res.err_desc:' +res.err_desc +' | res.err_msg:'+ res.err_msg);
					if(res.err_msg=="get_brand_wcpay_request:ok"){
						//成功返回
						alert("订购成功！");
						<%
						PurchaseService purchaseService = (PurchaseService) SpringBase.getCtx()
						.getBean("PurchaseService");
						int purchase = purchaseService.save(orderNo);
						%>
						window.location.href="http://www.17xnr.com/confirm.html?orderId=<%=orderNo%>";
					}else{
						exlog.error("order error: "+res);
						alert("订购失败！");
						window.location.href="http://www.17xnr.com/waitting.html";
					}
			});
		 })
		
		</script>
	
	</head>
  <body>
    	
  </body>
</html>
