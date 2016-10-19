<%@page import="com.xnradmin.client.task.CheckPassedOrder"%>
<%@page import="com.xnradmin.core.sms.examples.SmsExample"%>
<%@page import="com.xnradmin.core.sms.examples.StatusListener"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>
<%
	//检测支付超时的订单,修改状态为过期订单
	CheckPassedOrder.getInstance().updateStatus();
%>