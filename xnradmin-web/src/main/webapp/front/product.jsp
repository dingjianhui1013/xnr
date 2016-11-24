<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品</title>
<%@include file="header.jsp"%>
</head>

<script type="text/javascript">


function addToCart(obj){
	var id = $(obj).attr("id");
	alert(id);
	
}


</script>


<body>
<div class="product-model">	 
	 <div class="container">
			<ol class="breadcrumb">
				  <li><a href="index.action">首页</a></li>
				  <li class="">${first}</li>
				  <li class="">${three}</li>
		 	</ol>		
			 <div class="col-md-12 product-model-sec">
			 	<c:forEach items="${productList}" var="product" varStatus="status">
					 <div class="product-grid love-grid">	
				 		<a href="/front/productDetail.action?goodsId=${product.businessGoods.id}">				
							<div class="product-img b-link-stripe b-animate-go  thickbox">
								<img src="${basePath }${product.businessGoods.goodsLogo}" class="img-responsive" alt=""/>
								<div class="b-wrapper">
									<h4 class="b-animate b-from-left  b-delay03">							
										<button class="btns"><span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span>查看详情</button>
									</h4>
								</div>
							</div>
						</a>						
						<div class="product-info simpleCart_shelfItem">
							<div class="product-info-cust prt_name">
								<h4><a href="/front/productDetail.action?goodsId=${product.businessGoods.id}">${product.businessGoods.goodsName} </a></h4>
								<p><a href="/front/productDetail.action?goodsId=${product.businessGoods.id}">农场出品，清脆爽口</a></p>
								<p class="item_price">￥${product.businessGoods.goodsOriginalPrice }/${product.businessWeight.weightName }</p>
								<div class="addCartBox">	
									<div class="addNum">						
										<span><input id="count${product.businessGoods.id }" type="text" class="item_quantity" value="1"/></span>
										<span>
											<a href="javascript:plusNum()" class="plusNum">+</a>
											<a href="javascript:minusNum()" class="minus-Num">-</a>
										</span>
									</div>
									<input type="button" id="add${product.businessGoods.id }" onclick="javascript:addToCart(this)" class="item_add" value="加入购物车">
								</div>
							</div>													
							<div class="clearfix"> </div>
						</div>
					</div>	
				</c:forEach>
			</div>

	      </div>
		</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>