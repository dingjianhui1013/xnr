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
<script type="text/javascript">
function addToCart(id,money){
	var userId = ${user.id};
	if(userId!=null&&userId!=""){
		$("#simpleCart_total").html((Number($("#simpleCart_total").html())+money).toFixed(2));
		$("#simpleCart_number").html((Number($("#simpleCart_number").html())+1));
	$.ajax({
		type:"POST", 
		url:"/front/shopingCart/add.action",
		data:{"goodsId":id,"goodsCount":1,"clientUserId":userId,_:new Date().getTime()},
		dataType:"json",
		success:function(msg){
				alert("加入成功");
			}
		});
	}
}

</script>
<!-- /start menu -->
</head>
<body> 
<!--header-->	


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
					 <a href="/front/productDetail.action?goodsId=${good.businessGoods.id}"><img src="${basePath }${good.businessGoods.goodsLogo}" alt=""/>	
					  <div class="arrival-info">
						 <h4>${ good.businessGoods.goodsName} </h4>
						 <p>约${good.businessGoods.goodsWeight }g</p>
						 <span class="disc">￥${good.businessGoods.goodsOriginalPrice }/${good.businessWeight.weightName }</span>
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
					 		<c:set var="m" value="0"/> 
					 		<c:forEach items="${indexGoods }" var="good" varStatus="status">
					 			<c:if test="${m<8 }">
					 			<c:if test="${good.businessCategory.id==threeBusinessCategory.id}">
					 			<c:if test="${m==0 }">
					 				<div class="feature-grids">
					 			</c:if>
					 			<div class="col-md-3 feature-grid jewel">
								 <a href="/front/productDetail.action?goodsId=${good.businessGoods.id}"><img src="${basePath }${good.businessGoods.goodsLogo }" alt=""/>	
							 	<div class="arrival-info">
								 <h4>${ good.businessGoods.goodsName} </h4>
								 <p>约${good.businessGoods.goodsWeight }g</p>
								 <span class="disc">￥${good.businessGoods.goodsOriginalPrice }/${good.businessWeight.weightName }</span>
								 </div>
								 <div class="shrt">
									<a href="javascript:addToCart(${good.businessGoods.id },${good.businessGoods.goodsOriginalPrice})"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>加入购物车</a>
							 	</div>
								 </a>
								 </div>
								 <c:if test="${m==3 }">
					 				</div>
					 				<div class="feature-grids secondLine-grid">
					 			</c:if>
					 			<c:if test="${m==7 }">
					 				</div>
					 			</c:if>
					 			<c:set var="m" value="${m+1 }"/> 
					 			</c:if> 
					 			</c:if>
					 		</c:forEach>
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
