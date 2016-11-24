<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	request.setAttribute("basePath",basePath);
%>
<!DOCTYPE html>
<html>
<head>
<title>注册页</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${basePath }css/front/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="${basePath }css/front/style.css" rel="stylesheet" type="text/css" media="all" />	
<link rel="stylesheet" href="${basePath }css/front/flexslider.css" type="text/css" media="screen" />
<!-- start menu -->
<link href="${basePath }css/front/memenu.css" rel="stylesheet" type="text/css" media="all" />
<link href="${basePath }css/front/form.css" rel="stylesheet" type="text/css" media="all" />
<script src="${basePath }js/front/jquery-1.11.3.min.js"></script>
<script src="${basePath }js/front/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath }js/front/common.js"></script>
<script src="${basePath }js/simpleCart.min.js"> </script>
<script type="text/javascript" src="${basePath }js/front/memenu.js"></script>
</head>
<body>
<div class="top_bg">
	<div class="container">
		<div class="header_top-sec">
			<div class="top_right">
				<ul>
					<li><a href="#">欢迎光临康源公社！</a></li>|
					<li><a href="#contact">联系我们</a></li>
				</ul>
			</div>
			<div class="top_left">
				<!--登录后显示-->
				<!-- <ul>
					<li class="top_link">用户名:<a href="mailto:info@example.com">myTest</a></li>|
					<li class="top_link"><a href="login.html">订单中心</a></li>					
				</ul> -->
				<!--登录后显示-->
				<ul>
					<li><a href="login.action">登录</a></li>
					<li><a href="register.action">注册</a></li>
				</ul>

			</div>
				<div class="clearfix"> </div>
		</div>
	</div>
</div>
<div class="logoWrap">	
	<div class="container">	
		<div class="logo">
			<a href="index.action">
			<h1><img src="${basePath }images/front/login_logo.png" /></h1>
			</a>
		</div>
		<div class="pull-left welcomeBox">康源公社欢迎您注册</div>
	</div>
</div>	
<!---->
<div class="container contentWrap">
    	<div class="row regSuccessWrap">
          <div class="regtipsBox">
          	<p>感谢加入康源公社，<span class="redColor">您已注册成功!</span></p>
          	<p>马上开启你的购物之旅吧！</p>
           <div class="text-center tipsBtnBox">
                <a href="login.jsp" class="btn btn-default">马上登录</a>
            </div>
          </div>
      	</div>
    </div>
<%@include file="footer.jsp"%>	
</body>
</html>