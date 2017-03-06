<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情页</title>
<%@include file="header.jsp" %>
</head>
<script type="text/javascript">
function addToCart(id){
	var userId = $("#userId").val()
	if(userId!=null&&userId!=""){
		$.ajax({
			type:"POST", 
			url:"<%=basePath%>/front/shopingCart/againBuy.action",
			data:{"orderRecordId":id,"clientUserId":userId,_:new Date().getTime()},
			dataType:"json",
			success:function(msg){
				if(msg.res==0)
					{
				 		$("#simpleCart_total").html((Number($("#simpleCart_total").html())+msg.totalMoney).toFixed(2));
				 		$("#simpleCart_number").html((Number($("#simpleCart_number").html())+Number(msg.totalCount)));
						layer.msg("加入成功");
					}
				}
			});
	}
// 	else
// 		{
// 			//layer.msg("请先登录");
<%-- 			//setTimeout("window.location.href='<%=basePath%>/front/login.jsp'",1000); --%>
// 		var cart = getCartCookie();
		
// 		var item=new Object();
// 		item.cookieId = getUuid();
// 		item.goodsId = id;
// 		item.goodsCount = goodsNumber;
// 		item.price = Number($("#price"+id).val());
// 		cart.push(item);
// 		$.cookie('cart', JSON.stringify(cart), { expires: 7, path: '/' }); 
// 		layer.msg("加入成功");
// 		}
	
}
</script>
<body>
<div class="checkout">	 
	 <div class="container">	
		 <ol class="breadcrumb">
		  <li><a href="index.html">首页</a></li>
		  <li class="active">订单详情</li>
		 </ol>
		 <div class="product-price1">
			 <div class="check-out">			
				 <div class="order-checkBox">
					 <div class="orderStatusBox">
					 		<h3 class="pull-left">订单状态：<span class="statusBox">${businessOrderVO.businessOrderRecord.paymentStatusName }</span></h3>
							<a href="javascript:addToCart('${businessOrderVO.businessOrderRecord.id }')" class="pull-right buyBtn">
								再次购买
							</a>
					 </div>
					 <div class="stepInfoBox">
						 <div class="receiptBox ">
							<div class="defalutAddBox" style="display:block" id="defalutAddBox">
								<p class="addr-name"><b>收货人信息：</b></p>
								<p class="addr-address"><span>${businessOrderVO.businessOrderRecord.clientUserName}</span><span>${businessOrderVO.businessOrderRecord.clientUserMobile}</span><span> ${businessOrderVO.businessOrderRecord.cityName} ${businessOrderVO.businessOrderRecord.countryName}  ${businessOrderVO.businessOrderRecord.areaName}</span></p>
							</div>
						</div>
						</div>
						<div class="receiptBox">
							 <p class="addr-name"><b>支付方式：</b></p>
							 <div class="infoItem paymentMode">
							 	<span>${businessOrderVO.businessOrderRecord.paymentProviderName}</span>
							 </div>
						</div>
						<div class="receiptBox checkOrderInfo">
							<h3>商品清单：</h3>
							 <div class="in-check" >
								  <ul class="unit">
									<li class="productCol"><span>商品</span></li>		
									<li><span>单价</span></li>
									<li><span>数量</span></li>
									<li>小计(元)</li>
									<div class="clearfix"> </div>
								  </ul>
								  <div class="cartListBox">
								  <c:forEach items="${businessOrderVO.businessOrderRelationVO }" var="brv">
								  	<ul class="cart-header"id="test">
											<c:if test="${not empty brv.businessGoods}">
												<li class="productCol">
													<a href="#" >
														<img src="${basePath }${brv.businessGoods.goodsLogo}" class="pull-left img-responsive" alt=""></a>
														<span class="pull-left cart-pDetail">${brv.businessGoods.goodsName }</span>
												</li>
												<li><span>${brv.businessGoods.goodsOriginalPrice}</span></li>
											</c:if>
											<c:if test="${not empty brv.combo}">
												<li class="productCol">
												<a href="#" >
													<img src="${basePath}${brv.combo.comboImgSmall}" class="pull-left img-responsive" alt=""></a>
													<span class="pull-left cart-pDetail">${brv.combo.comboName }</span>
												</li>
												<li><span>${brv.combo.comboPrice}</span></li>
											</c:if>
											<li class="cart-num">						
												<span class="chooseNum">${brv.orderGoodsRelation.goodsCount}</span>
											</li>
											<li>
												<span>
													<fmt:formatNumber type="number" value="${(brv.combo.comboPrice)*(brv.orderGoodsRelation.goodsCount)}" maxFractionDigits="2"/>
												</span>
											</li>
											<div class="clearfix"> </div>
										</ul>
								  </c:forEach>
								  </div>
							 </div>
							  <div class="in-check-mobile" >
								  <div class="cartListBox">
								  <c:forEach items="${businessOrderVO.businessOrderRelationVO }" var="brv">
								  	<ul class="cart-header"id="test">
											<c:if test="${not empty brv.businessGoods}">
												<li class="productCol">
													<a href="#" >
														<img src="${basePath }${brv.businessGoods.goodsLogo}" class="pull-left img-responsive" alt=""></a>
														<span class="pull-left cart-pDetail">${brv.businessGoods.goodsName }</span>
												</li>
												<li><span>${brv.businessGoods.goodsOriginalPrice}</span></li>
											</c:if>
											<c:if test="${not empty brv.combo}">
												<li class="productCol">
												<a href="#" >
													<img src="${basePath}${brv.combo.comboImgSmall}" class="pull-left img-responsive" alt=""></a>
													<span class="pull-left cart-pDetail">${brv.combo.comboName }</span>
												</li>
												<li><span>${brv.combo.comboPrice}</span></li>
											</c:if>
											<li class="cart-num">						
												<span class="chooseNum">${brv.orderGoodsRelation.goodsCount}</span>
											</li>
											<li>
												<span>
													<fmt:formatNumber type="number" value="${(brv.combo.comboPrice)*(brv.orderGoodsRelation.goodsCount)}" maxFractionDigits="2"/>
												</span>
											</li>
											<div class="clearfix"> </div>
										</ul>
								  </c:forEach>
								  </div>
					 		</div>
						 </div>
					 </div>
				  </div>					  
			 </div>
		 </div>
		 <div class="orderSubmit">
		 	<div class="container">
			 	<ul class="cart-con">
			 		<li class="pull-right totalCol">
			 			<div class=" totalMoney orderDetailMoney">
				 			<p>总金额:<span class="t-money">${businessOrderVO.businessOrderRecord.totalPrice}</span></p>
			 			</div>
			 		</li>
			 	</ul>
		 	</div>
		 </div>

</div>
</div> 
<%@include file="footer.jsp" %>
</body>
</html>