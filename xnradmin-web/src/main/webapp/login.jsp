<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"login.action";


	request.setAttribute("action",action);
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康源公社管理平台</title>
<link href="themes/css/login.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="js/verifyCode.js" type="text/javascript"></script>
</head>

<body>
	<div id="login">
		<div id="login_header">
			<!--<h1 class="login_logo">
				<a href="#"><img src="themes/default/images/login_logo.gif" /></a>
			</h1>
			 -->
			<div class="login_headerContent">
				<h3>用户登录</h3>
				<!-- 				
				<h2 class="login_title"><img src="themes/default/images/login_title.png" /></h2>
				 -->
			</div>
		</div>
		
		<div id="login_content">
			<div class="loginForm">
			<span class="errorTips">${errorMsg}</span>
				<form action="${action}" method="post">
					<p>
						<label>用户名：</label>
						<input type="text" name="j_username" size="20" class="login_input" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="j_password" size="20" class="login_input" />
					</p>
					<p>
						<label class="yzmLabel">验证码：</label>					
	
						<input id="validateCode" class="code" size="5" name="validateCode" 
type="text"/>  
						<span><img id="imgObj" onclick="changeImg()" src="page/vcode/vcode.action" 
alt="" width="80" height="25" /></span>
						<br/><span><a href="#" onclick="changeImg()">换一张</a></span>
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
					<!-- 
					<p>
						<a href="http://182.254.243.248:3000" target="_balnk">点我登陆小农人
redmine</a>
					</p>
					 -->
				</form>
			</div>
			<div class="login_banner"><img src="themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">				
				<div class="login_inner">
					<p></p>
					<p></p>
					<p></p>
				</div>
			</div>
			
			
		</div>
		<div id="login_footer">
			Copyright &copy; 2013.
		</div>
	</div>
</body>


</html>
