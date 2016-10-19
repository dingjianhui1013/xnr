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
<%@ include file="config.jsp"%>

<%

//=================================
//jsapi接口
//=================================
//初始化
	Log log = Log4jUtil.getLog("wxpay");
	
	
	WXPayInfoService payService = (WXPayInfoService)SpringBase.getCtx().getBean("WXPayInfoService");
	
	WXPayInfoVO v = payService.findByWxUserid(1l);
	v.setProductName("测试商品");
	v.setTotalFee("1");
	v.setClientIp(HttpHelper.getIp(request));
	String rr = payService.getSignOut(v);
	//生成支付签名
	String sign = payService.getSignOut(v);
	log.debug(sign);
%>


<html>
	<head>
		<script language="javascript">
		 document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
			 alert("init");
			 WeixinJSBridge.invoke('getBrandWCPayRequest',<%=sign%>,function(res){
					WeixinJSBridge.log(res.err_msg);
					alert('res.err_code:'+res.err_code +' | res.err_desc:' +res.err_desc +' | res.err_msg:'+ res.err_msg);
			});
		 })
		
		</script>
	</head>
  <body>
    
    
  </body>
</html>
