<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
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
<script src="${basePath }js/layer/layer.js"></script>
<script type="text/javascript" src="${basePath }js/front/common.js"></script>
<!-- <script type="application/x-javascript">  -->
<!--  	addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }  -->
<!-- </script> -->
<%-- <script src="${basePath }js/front/simpleCart.min.js"> </script> --%>
<script type="text/javascript" src="${basePath }js/front/memenu.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		url:"/page/wx/admin/order/shoppingCart/getTotalAndNumber.action",
		type:"POST",
		data:{"userId":'${user.id}'},
		dataType:"JSON",
		success:function(data)
		{
			$.each(data.count_number, function(key, value) { 
					$("#simpleCart_total").html(key);
					$("#simpleCart_number").html(value)
				}); 
		}
		
	});
})
</script>

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
			<a href="<%=basePath%>/front/index.action">
			<h1><img src="${basePath}images/front/login_logo.png" /></h1>
			</a>
		</div>
		<div class="pull-left searchBox">
			<form action="<%=basePath%>/front/search.action" class="form-inline" method="post">
			<input type="text"  name="search" placeholder="请输入搜索内容" class="searchInput"/>
			<input type="submit" value="搜索" class="searchBtn">
			</form>
		</div>
		 <div class="pull-right cart box_1">
			 <a href="<%=basePath%>/front/shopingCart/shoppingCart.action">
				<h3>
				<div class="total">
					<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
					<span id="simpleCart_total"></span> (<span id="simpleCart_number">0</span>)
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
			<li class="active"><a href="<%=basePath%>/front/index.action">首页</a></li>
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
									<li><a href="<%=basePath%>/front/product.action?productCategoryId=${threeBusinessCategory.id}&&first=${ first.key.categoryName}&&three=${ threeBusinessCategory.categoryName}">${threeBusinessCategory.categoryName }</a></li>
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
			<li class="grid"><a href="<%=basePath%>/front/contact.action">关于我们</a></li>
			<input type="hidden" id="userId" value="${user.id}"/>
		</ul>
	 </div>
</div>
