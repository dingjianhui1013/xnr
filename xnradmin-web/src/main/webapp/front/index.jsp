<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>康源公社-首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="康源公社" content="康源,公社,蔬菜,有机" />
<%@include file="header.jsp"%>
<!-- /start menu -->
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
<div class="banner">
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
		  <ol class="carousel-indicators">
		    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
		    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
		  </ol>

		  <!-- Wrapper for slides -->
		  <div class="carousel-inner" role="listbox">
		    <div class="item active">
		      <img src="${basePath }images/front/banner1.jpg" alt="...">
		      <div class="carousel-caption">
		        ...
		      </div>
		    </div>
		    <div class="item">
		      <img src="${basePath }images/front/banner2.jpg" alt="...">
		      <div class="carousel-caption">
		        ...
		      </div>
		    </div>
		  </div>

		  <!-- Controls -->
		  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
		    <span class="glyphicon glyphicon-chevron-left"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
		    <span class="glyphicon glyphicon-chevron-right"></span>
		    <span class="sr-only">Next</span>
		  </a>
		</div>	
</div>
<div class="arrivals">
	 <div class="container">	
		  <div class="sortNavBox">
		  		<h3>特惠推荐</h3>
		  </div>
		 <div class="arrival-grids">			 
			 <ul id="flexiselDemo1">
			 <c:forEach items="${indexGoods }" var="good" varStatus="status">
				 <li>
					 <a href="product.html"><img src="${basePath }${good.businessGoods.goodsLogo}" alt=""/>	
					  <div class="arrival-info">
						 <h4>${ good.businessGoods.goodsName} </h4>
						 <p>约500-750g</p>
						 <span class="disc">￥${good.businessGoods.goodsWeight }/${good.businessWeight.weightName }</span>
					 </div>
					 </a>
				 </li>
				</c:forEach>
				</ul>
				<script type="text/javascript">
				 $(window).load(function() {			
				  $("#flexiselDemo1").flexisel({
					visibleItems: 5,
					animationSpeed: 1000,
					autoPlay: true,
					autoPlaySpeed: 3000,    		
					pauseOnHover:true,
					enableResponsiveBreakpoints: true,
					responsiveBreakpoints: { 
						portrait: { 
							changePoint:480,
							visibleItems: 1
						}, 
						landscape: { 
							changePoint:640,
							visibleItems: 2
						},
						tablet: { 
							changePoint:768,
							visibleItems: 3
						}
					}
				});
				});
				</script>
				<script type="text/javascript" src="${basePath }js/front/jquery.flexisel.js"></script>			 
		  </div>
	 </div>
</div>
	 <c:set var="k" value="1"/>
	 <c:forEach items="${ allBusinessCategorys}" var="firstBusinessCategory">
	 <c:forEach items="${firstBusinessCategory }" var="first">
	 <c:set var="i" value="0"/>
	 <c:set var="j" value="0"/>
	 
	 <div class="featured">
	 <div class="container">
	 	<div class="sortNavBox v-box">
		<h3>${first.key.categoryName }</h3>
		 <ul class="sortNavUl sortTab${k }">
			<c:forEach items="${first.value }" var="secondBusinessCategory">
				<c:forEach items="${secondBusinessCategory }" var="second">
					<c:forEach items="${second.value }" var="threeBusinessCategory">
						<li <c:if test="${ i==0}">class="cur"</c:if>>${threeBusinessCategory.categoryName }</li>
						<c:set var="i" value="${i+1 }" />
					</c:forEach>
				</c:forEach>
			</c:forEach>
		</ul>
		</div>
		<div class="adBanner">
	 	<a href="#"><img src="${basePath }${first.key.categoryLogo }" /></a>
	 	</div>
	 	<div class="sortListBox sortListBox${k }">
	 	<c:forEach items="${first.value }" var="secondBusinessCategory">
				<c:forEach items="${secondBusinessCategory }" var="second">
					<c:forEach items="${second.value }" var="threeBusinessCategory" varStatus="status">
					
					 	<div class="sortListCon" <c:if test="${ j==0}">style="display:block;"</c:if>>
					 		<c:forEach items="${indexGoods }" var="good" varStatus="status">
					 			<c:if test="${status.index==0 }">
					 				<div class="feature-grids">
					 			</c:if>
					 			
					 			<c:if test="${good.businessCategory.id==threeBusinessCategory.id}">
					 			<div class="col-md-3 feature-grid jewel">
								 <a href="/front/productDetail.action?goodsId=${good.businessGoods.id}"><img src="${basePath }${good.businessGoods.goodsLogo }" alt=""/>	
							 	<div class="arrival-info">
								 <h4>${ good.businessGoods.goodsName} </h4>
								 <p>约500-750g</p>
								 <span class="disc">￥${good.businessGoods.goodsWeight }/${good.businessWeight.weightName }</span>
								 </div>
								 <div class="shrt">
									<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
							 	</div>
								 </a>
								 </div>
								 </c:if> 
								 <c:if test="${status.index==3 }">
					 				</div>
					 				<div class="feature-grids secondLine-grid">
					 			</c:if>
					 			<c:if test="${status.index==7 }">
					 				</div>
					 			</c:if>
					 		</c:forEach>
			 </div>
			 </div>
						<c:set var="j" value="${j+1 }" />
					</c:forEach>
				</c:forEach>
			</c:forEach>
	 	</div>
		</div>
		<div class="adBox">
			<img src="${basePath }${first.key.categoryHeadLogo}" class="img-responsive" />
		</div>
		 <c:set var="k" value="${k+1 }" />
	 </c:forEach>
	 </c:forEach>
	
<!---->

<!---->
<%@include file="footer.jsp"%>		 
</body>
</html>
