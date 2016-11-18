<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="header.jsp"%>
</head>
<body> 
<!--header-->	
<div class="logoWrap">	
	<div class="container">	
		<div class="logo">
			<a href="index.jsp">
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
	         <form class="form-horizontal">
	              <h4 class="loginBoxTit">账户登录</h4>
	              <div class="form-group">
	                <label for="inputEmail3" class="col-sm-2 control-label">手机号：</label>
	                <div class="col-sm-10">
	                  <input type="email" class="form-control" id="inputEmail3" placeholder="请输入手机号/邮箱">
	                </div>
	              </div>
	              <div class="form-group">
	                <label for="inputPassword3" class="col-sm-2 control-label">密码：</label>
	                <div class="col-sm-10">
	                  <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
	                </div>
	              </div>
	              <div class="form-group">
	                <div class="col-sm-offset-2 col-sm-10">
	                  <div class="checkbox">
	                    <label>
	                      <input type="checkbox"> 记住我
	                    </label>
	                    <a class="pull-right forgotPwd" href="forgotPwd.jsp">忘记密码</a>
	                  </div>
	                </div>
	              </div>
	              <div class="form-group btnBox">
	                <div class="col-sm-offset-2 col-sm-10 loginBtn">
	                  <input type="button" class="btn btn-primary" value="登 录">
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