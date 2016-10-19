<%@page import="com.xnradmin.core.test.TestScript"%>
<%@page import="com.xnradmin.core.sms.examples.SmsExample"%>
<%@page import="com.xnradmin.core.sms.examples.StatusListener"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>
<%
//每5分钟获取状态报告d
 new SmsExample().sendSms(1, "13552435313", "验证码为15648");

%>