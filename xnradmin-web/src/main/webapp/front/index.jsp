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
			<a href="index.html">
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
					<span class="simpleCart_total"></span> (<span id="simpleCart_quantity" class="simpleCart_quantity"></span>)</div>
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
			<li class="grid"><a href="#">蔬菜水果</a>
				<div class="mepanel">
					<div class="row">
						<div class="col1 me-one">
							<h4>蔬菜类</h4>
							<ul>
								<li><a href="product.html">有机蔬菜</a></li>
								<li><a href="product.html">地方特产蔬菜</a></li>
								<li><a href="product.html">自产蔬菜</a></li>
							</ul>
						</div>
						<div class="col1 me-one">
							<h4>水果类</h4>
							<ul>
								<li><a href="product.html">有机水果</a></li>
								<li><a href="product.html">进口水果</a></li>
								<li><a href="product.html">国产水果</a></li>
							</ul>	
						</div>
					</div>
				</div>
			</li>
			<li class="grid"><a href="#">肉类禽蛋</a>
				<div class="mepanel">
					<div class="row">
						<div class="col1 me-one">
							<h4>牛羊肉</h4>
							<ul>
								<li><a href="product.html">有机牛羊肉</a></li>
								<li><a href="product.html">进口牛羊肉</a></li>
								<li><a href="product.html">精品牛羊肉</a></li>
							</ul>
						</div>
						<div class="col1 me-one">
							<h4>猪肉</h4>
							<ul>
								<li><a href="product.html">有机猪肉</a></li>
								<li><a href="product.html">进口猪肉</a></li>
								<li><a href="product.html">国产猪肉</a></li>
								<li><a href="product.html">农场自养猪肉</a></li>
							</ul>	
						</div>
						<div class="col1 me-one">
							<h4>禽类</h4>
							<ul>
								<li><a href="product.html">有机禽类</a></li>
								<li><a href="product.html">散养禽类</a></li>
							</ul>	
						</div>
						<div class="col1 me-one">
							<h4>蛋类</h4>
							<ul>
								<li><a href="product.html">有机蛋</a></li>
								<li><a href="product.html">散养蛋</a></li>
							</ul>	
						</div>
					</div>
				</div>
			</li>
			<li class="grid"><a href="#">粮油副食</a></li>
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
				 <li>
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
					  <div class="arrival-info">
						 <h4>有机山药 </h4>
						 <p>约500-750g</p>
						 <span class="disc">￥12.80/份</span>
					 </div>
					 </a>
				 </li>
				 <li>
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>
						 <div class="arrival-info">
						 <h4>有机山药 </h4>
						 <p>约500-750g</p>
						 <span class="disc">￥12.80/份</span>
					 </div>
					 </a>
				 </li>
				 <li>
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img3.jpg" alt=""/>	
						 <div class="arrival-info">
						 <h4>有机山药 </h4>
						 <p>约500-750g</p>
						 <span class="disc">￥12.80/份</span>
					 </div>
					 </a>
				 </li>
				 <li>
				    <a href="product.html"> <img src="${basePath }images/front/products/sg-img1.jpg" alt=""/>	
						 <div class="arrival-info">
						 <h4>有机山药 </h4>
						 <p>约500-750g</p>
						 <span class="disc">￥12.80/份</span>
					 </div>
					 </a>
				 </li>
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
<div class="featured">
	 <div class="container">
	 <div class="sortNavBox v-box">
		 <h3>蔬菜水果</h3>
		 <ul class="sortNavUl sortTab1">
		 	<li class="cur">特惠专区</li>
		 	<li>有机蔬菜</li>
		 	<li>有机水果</li>
		 </ul>
	 </div>
	 <div class="adBanner">
	 	<a href="#"><img src="${basePath }images/front/products/leftImg.jpg" /></a>
	 </div>
	 <div class="sortListBox sortListBox1">
	 	<div class="sortListCon" style="display:block;">
			 <div class="feature-grids">
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机山药 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						  <div class="arrival-info">
							 <h4>有机西兰花 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥22.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img3.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机栗味南瓜 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						  <div class="arrival-info">
							 <h4>有机山药</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
			 </div>
			 <div class="feature-grids secondLine-grid">
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机西兰花</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img3.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机南瓜</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机西兰花</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						<div class="arrival-info">
							 <h4>有机山药</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div> 
					 </a>
				 </div>
			 </div>
		</div>
		<div class="sortListCon">
			 <div class="feature-grids">
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机山药2 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						  <div class="arrival-info">
							 <h4>有机西兰花 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥22.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img3.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机栗味南瓜 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						  <div class="arrival-info">
							 <h4>有机山药</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
			 </div>
			 <div class="feature-grids secondLine-grid">
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机西兰花</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img3.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机南瓜</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机西兰花</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						<div class="arrival-info">
							 <h4>有机山药</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div> 
					 </a>
				 </div>
			 </div>
		</div>
		<div class="sortListCon">
			 <div class="feature-grids">
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机山药3333 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						  <div class="arrival-info">
							 <h4>有机西兰花 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥22.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img3.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机栗味南瓜 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						  <div class="arrival-info">
							 <h4>有机山药</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
			 </div>
			 <div class="feature-grids secondLine-grid">
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机西兰花</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img3.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机南瓜</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机西兰花</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						<div class="arrival-info">
							 <h4>有机山药</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div> 
					 </a>
				 </div>
			 </div>
		</div>
	 </div>
	 </div>
</div>
<!---->
<div class="adBox">
	<img src="${basePath }images/front/ad/adImg1.jpg" class="img-responsive" />
</div>
<div class="featured">
	 <div class="container">
	 <div class="sortNavBox egg-box">
		 <h3>肉类禽蛋</h3>
		 <ul class="sortNavUl sortTab2">
		 	<li class="cur">特惠专区</li>
		 	<li>禽类</li>
		 	<li>肉类</li>
		 </ul>
	 </div>
	 <div class="adBanner">
	 	<a href="#"><img src="${basePath }images/front/products/leftImg1.jpg" /></a>
	 </div>
	 <div class="sortListBox sortListBox2">
	 	<div class="sortListCon" style="display:block;">
			 <div class="feature-grids">
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机山药 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						  <div class="arrival-info">
							 <h4>有机西兰花 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥22.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img3.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机栗味南瓜 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						  <div class="arrival-info">
							 <h4>有机山药</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
			 </div>
			 <div class="feature-grids secondLine-grid">
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机西兰花</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img3.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机南瓜</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机西兰花</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						<div class="arrival-info">
							 <h4>有机山药</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div> 
					 </a>
				 </div>
			 </div>
		</div>
		<div class="sortListCon">
			 <div class="feature-grids">
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>禽类</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						  <div class="arrival-info">
							 <h4>有机西兰花 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥22.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img3.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机栗味南瓜 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						  <div class="arrival-info">
							 <h4>有机山药</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
			 </div>
			 <div class="feature-grids secondLine-grid">
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机西兰花</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img3.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机南瓜</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机西兰花</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						<div class="arrival-info">
							 <h4>有机山药</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div> 
					 </a>
				 </div>
			 </div>
		</div>
		<div class="sortListCon">
			 <div class="feature-grids">
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>肉类 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						  <div class="arrival-info">
							 <h4>有机西兰花 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥22.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img3.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机栗味南瓜 </h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						  <div class="arrival-info">
							 <h4>有机山药</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					 </a>
				 </div>
			 </div>
			 <div class="feature-grids secondLine-grid">
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机西兰花</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img3.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机南瓜</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid jewel">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img2.jpg" alt=""/>	
						 <div class="arrival-info">
							 <h4>有机西兰花</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div>
					</a>
				 </div>
				 <div class="col-md-3 feature-grid">
					 <a href="product.html"><img src="${basePath }images/front/products/sc-img1.jpg" alt=""/>	
						<div class="arrival-info">
							 <h4>有机山药</h4>
							 <p>约500-750g</p>
							 <span class="disc">￥12.80/份</span>
						 </div>
						 <div class="shrt">
							<a href="product.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
						 </div> 
					 </a>
				 </div>
			 </div>
		</div>
	 </div>
	 </div>
</div>
<!---->
<%@include file="footer.jsp"%>		 
</body>
</html>