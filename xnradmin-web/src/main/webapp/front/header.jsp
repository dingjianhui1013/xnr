<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
					<li><a href="login.jsp">登录</a></li>
					<li><a href="register.jsp">注册</a></li>
					<li><a href="personalCenter.jsp">我的订单</a></li>
				</ul>

			</div>
				<div class="clearfix"> </div>
		</div>
	</div>
</div>
<div class="logoWrap">	
	<div class="container">	
		<div class="logo">
			<a href="index.jsp">
			<h1><img src="${basePath }images/front/login_logo.png" /></h1>
			</a>
		</div>
		<div class="pull-left searchBox">
			<form action="" class="form-inline">
			<input type="text"  placeholder="请输入搜索内容" class="searchInput"/>
			<input type="button" value="搜索" class="searchBtn">
			</form>
		</div>
		 <div class="pull-right cart box_1">
			 <a href="checkout.html">
				<h3> <div class="total">
					<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
					<span class="simpleCart_total"></span> (<span id="simpleCart_quantity" class="simpleCart_quantity"></span>)
					</div>
				</h3>
			</a>
		 	<div class="clearfix"> </div>
		 </div>
	</div>
</div>
<div class="guiderBox">
	<div class="container">
		<ul class="memenu skyblue pull-left">
			<li class="active"><a href="index.html">首页</a></li>
			<c:forEach items="${ allBusinessCategorys}" var="firstBusinessCategory">
				<c:forEach items="${firstBusinessCategory }" var="first">
				<li class="grid"><a href="#"> ${ first.key.categoryName}</a>
				<div class="mepanel">
					<div class="row">
						<c:forEach items="${first.value }" var="secondBusinessCategory">
						<c:forEach items="${secondBusinessCategory }" var="second">
							<div class="col1 me-one">
								<h4> ${ second.key.categoryName} </h4>
								<ul>
									<c:forEach items="${second.value }" var="threeBusinessCategory">
									<li><a href="product.html">${threeBusinessCategory.categoryName }</a></li>
									</c:forEach>
								</ul>
							</div>
						</c:forEach>
						</c:forEach>
					</div>
				</div> 
				</c:forEach>
			</li>
			</c:forEach>
			<li class="grid"><a href="#contact">关于我们</a></li>
		</ul>
	 </div>
</div>
