<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.xnradmin.core.pay.wxpay.util.TenpayUtil"%>
<%@ page import="com.xnradmin.core.pay.wxpay.util.MD5Util"%>
<%@ page import="com.xnradmin.core.pay.wxpay.RequestHandler"%>
<%@ page import="com.xnradmin.core.pay.wxpay.ResponseHandler"%>
<%@ page import="com.xnradmin.core.pay.wxpay.client.TenpayHttpClient"%>
<%@ include file="../config.jsp"%>
<% 
//=================================
//维权接口
//=================================
	//创建支付应答对象
	RequestHandler queryReq = new RequestHandler(null, null);
	queryReq.init();
	//初始化页面提交过来的参数
	ResponseHandler resHandler = new ResponseHandler(request, response);
	resHandler.setKey(APP_KEY);
	
	//判断签名
if(resHandler.isWXsign() == true) {
	//回复服务器处理成功
	System.out.print("ok");
	}
	else{
	//SHA1签名失败
	System.out.print("fail");
	}


%>