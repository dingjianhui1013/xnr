<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	request.setAttribute("basePath",basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>支付成功页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${basePath }css/front/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="${basePath }css/front/style.css" rel="stylesheet" type="text/css" media="all" />	
<link rel="stylesheet" href="${basePath }css/front/flexslider.css" type="text/css" media="screen" />

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
					<li><a href="<%=basePath%>/front/index.action">欢迎光临康源公社！</a></li>|
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
					<c:if test="${empty user}">
						<li><a href="<%=basePath%>/front/login.jsp">登录</a></li>|
					</c:if>
					<c:if test="${!empty user}">
						<li class="top_link">用户名:<a href="<%=basePath%>/front/personalCenter.action">${user.userName}</a></li>|
						<li><a href="<%=basePath%>/front/exit.action">退出</a></li>|
					</c:if>
					<li><a href="<%=basePath%>/front/register.jsp">注册</a></li>|
					<li><a href="<%=basePath%>/front/personalCenter.action?flag=myorder">订单中心</a></li>
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
		<div class="pull-left welcomeBox">康源公社欢迎您再次购买!</div>
	</div>
</div>	
<!---->
<div class="container contentWrap">
    	<div class="row regSuccessWrap">
          <div class="regtipsBox">
          	<p><span class="redColor">您已支付成功!</span></p>
          	<p>再去别处逛逛吧！</p>
           <div class="text-center tipsBtnBox">
                <a href="${basePath }front/index.action" class="btn btn-default">首页</a>
            </div>
          </div>
      	</div>
    </div>
<%@include file="footer.jsp"%>	
</body>
</html>