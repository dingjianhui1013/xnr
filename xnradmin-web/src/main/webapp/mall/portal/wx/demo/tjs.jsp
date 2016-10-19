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
	WxPay wxpay = new WxPay();
	wxpay.SetAppId(APP_ID);
	wxpay.SetAppKey(APP_KEY);
	
	//当前时间 yyyyMMddHHmmss
	String currTime = TenpayUtil.getCurrTime();
	//8位日期
	String strTime = currTime.substring(8, currTime.length());
	//四位随机数
	String strRandom = TenpayUtil.buildRandom(4) + "";
	//10位序列号,可以自行调整。
	String strReq = strTime + strRandom;
	//订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
	String out_trade_no = strReq;
	
	//设置package订单参数
	wxpay.SetParameter("bank_type", "WX");
	wxpay.SetParameter("body", "测试商品");
	wxpay.SetParameter("fee_type","1");
	wxpay.SetParameter("input_charset", "UTF-8");
	wxpay.SetParameter("notify_url", NOTIFY_URL);
	wxpay.SetParameter("out_trade_no", out_trade_no);
	wxpay.SetParameter("partner", PARTNER);
	wxpay.SetParameter("total_fee", "1");
	wxpay.SetParameter("spbill_create_ip",  HttpHelper.getIp(request));
	
	wxpay.SetPartnerKey(PARTNER_KEY);
	
	
	//获取提交的商品价格
	String order_price = request.getParameter("order_price");
	//获取提交的商品名称
	String product_name = request.getParameter("product_name");
	
	
	log.debug("out_trade_no:"+out_trade_no);
	//生成支付签名
	String sign = wxpay.CreateBizPackage();
	log.debug(sign);
%>


<html>
	<head>
		<script language="javascript">
		function callpay(){
			WeixinJSBridge.invoke('getBrandWCPayRequest',<%=sign%>,function(res){
							WeixinJSBridge.log(res.err_msg);
							alert(res.err_code + res.err_desc + res.err_msg);
					});
		}
		</script>
	</head>
  <body>
    <button type="button" onclick="callpay()" >wx pay test</button>
  </body>
</html>
