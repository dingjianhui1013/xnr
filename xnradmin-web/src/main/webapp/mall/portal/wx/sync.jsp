<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"
	import="org.apache.commons.logging.*"
%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	Log log = LogFactory.getLog("wxpay");
	log.debug("this is wxpay - sync");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
success