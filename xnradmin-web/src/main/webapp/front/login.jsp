<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	request.setAttribute("basePath",basePath);
%>
<link href="${basePath }css/front/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="${basePath }css/front/style.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="${basePath }css/front/flexslider.css" type="text/css" media="screen" />	
<!-- start menu -->
<link href="${basePath }css/front/memenu.css" rel="stylesheet" type="text/css" media="all" />
<script src="${basePath }js/front/jquery-1.11.3.min.js"></script>
<script src="${basePath }js/front/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath }js/front/common.js"></script>
<script type="application/x-javascript"> 
	addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<script src="${basePath }js/front/simpleCart.min.js"> </script>
<script type="text/javascript" src="${basePath }js/front/memenu.js"></script>
</head>
<body> 
<!--header-->	
<div class="logoWrap">	
	<div class="container">	
		<div class="logo">
			<a href="/front/index.action">
			<h1><img src="${basePath }images/front/login_logo.png" /></h1>
			</a>
		</div>
		<div class="pull-left welcomeBox">康源公社欢迎您登录</div>
	</div>
</div>
<!--head//-->
<div class="loginBoxWrap">
	<div class="container">
		<div class="col-sm-6">
			<img src="${basePath }images/front/loginBigImg.png" />
		</div>
		<div class="loginBox col-sm-6">
	         <form class="form-horizontal" method="post" action="/front/login.action">
	              <h4 class="loginBoxTit">账户登录</h4>
	              <div class="form-group">
	                <label for="inputEmail3" class="col-sm-2 control-label">手机号：</label>
	                <div class="col-sm-10">
	                  <input type="" name="userName" class="form-control" id="inputEmail3" placeholder="请输入手机号/邮箱">
	                </div>
	              </div>
	              <div class="form-group">
	                <label for="inputPassword3" class="col-sm-2 control-label">密码：</label>
	                <div class="col-sm-10">
	                  <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
	                  <p style="color: red;font-size: 10px;padding-top:15px">${message }</p>
	                </div>
	              </div>
	              <div class="form-group">
	                <div class="col-sm-offset-2 col-sm-10">
	                  <div class="checkbox">
	                    <label>
	                      <input type="checkbox"> 记住我
	                    </label>
	                   <!--  <a class="pull-right forgotPwd" href="forgotPwd.jsp">忘记密码</a> -->
	                  </div>
	                </div>
	              </div>
	              <div class="form-group btnBox">
	                <div class="col-sm-offset-2 col-sm-10 loginBtn">
	                  <input type="submit" class="btn btn-primary" value="登 录">
	                  <a href="register.jsp" class="regLinkBox">没有账号，马上注册</a>
	                </div>
	              </div>
	        </form>
		</div>
	</div>
</div>
<!---->
<%@include file="footer.jsp"%>		 
</body>
</html>