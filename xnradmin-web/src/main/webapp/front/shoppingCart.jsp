<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>购物车</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="康源公社" content="康源,公社,蔬菜,有机" />
<%@include file="header.jsp"%>

</head>
<script type="text/javascript">
function add(a, b) {
	var  c=a+b;
	var d=c.toFixed(1);	
	return d;
}

function sub(a, b) {
	var  c=a-b;
	var d=c.toFixed(1);	
	return d;
}

function plusNum(id)
{		var price = Number($("#price"+id).html());  
		var xiaoji = Number($("#xiaoji"+id).html());
		var totalprices = Number($("#totalprices").html());
		
		var totalpricesNew = add(totalprices,price);
		var xiaojiNew = add(xiaoji,price);
// 		alert(totalpricesNew);
// 		if(Number.isInteger(totalpricesNew) ){
// 			totalpricesNew = totalpricesNew+".0"
// 		}
// 		if(Number.isInteger(xiaojiNew) ){
// 			xiaojiNew = xiaojiNew+".0"
// 		}
		$("#totalprices").html(totalpricesNew);
		$("#xiaoji"+id).html(xiaojiNew);
		var index = $("#count"+id).val();
		index++;
		$("#simpleCart_total").html((Number($("#simpleCart_total").html())+Number(price)).toFixed(2));
		$("#simpleCart_number").html((Number($("#simpleCart_number").html())+Number(1)));
		$("#count"+id).val(index);
}
function minusNum(id)
{		var price = Number.parseFloat($("#price"+id).html());
		var xiaoji = Number.parseFloat($("#xiaoji"+id).html());
		var totalprices = Number.parseFloat($("#totalprices").html());
		
		var index = $("#count"+id).val();
		index--;
		if(index>=1)
			{
				var totalpricesNew = sub(totalprices,price);
				var xiaojiNew = sub(xiaoji,price);
// 				if(Number.isInteger(totalpricesNew) ){
// 					totalpricesNew = totalpricesNew+".0"
// 				}
// 				if(Number.isInteger(xiaojiNew) ){
// 					xiaojiNew = xiaojiNew+".0"
// 				}
				$("#totalprices").html(totalpricesNew);
				$("#xiaoji"+id).html(xiaojiNew);
				$("#count"+id).val(index);
				$("#simpleCart_total").html((Number($("#simpleCart_total").html())-Number(price)).toFixed(2));
				$("#simpleCart_number").html((Number($("#simpleCart_number").html())-Number(1)));
			}else
				{
				$("#count"+id).val(1);
				}
}



function delfromCart(id){
	var userId = ${user.id};
	
	$.ajax({
		type:"POST", 
		url:"/front/shopingCart/del.action",
		data:{"shoppingCartId":id,_:new Date().getTime()},
		dataType:"json",
		success:function(msg){
				
			}
		});
	var price = Number($("#price"+id).html());
	var index = Number($("#count"+id).val());
	var totalpricesNew = Number($("#totalprices").html())-price*index;
	$("#totalprices").html(totalpricesNew);
}


</script>

<body> 

<!--header-->	
<!---->
<div class="checkout">	 
	 <div class="container">	
		 <ol class="breadcrumb">
		  <li><a href="/front/index.action">首页</a></li>
		  <li class="active">购物车</li>
		 </ol>
		 <div class="product-price1">
			 <div class="check-out">			
				 <div class=" cart-items">
					 <h3>全部商品 </h3>
					 <!--pc端购物车-->
					 <div class="in-check">
						  <ul class="unit">
							<li class="checkCol"><label for="checkAll" id="checkALLBtn"><input type="checkbox" id="checkAll"/>全选</label>
							</li>
							<li class="productCol"><span>商品</span></li>		
							<li><span>单价</span></li>
							<li><span>数量</span></li>
							<li>小计(元)</li>
							<li>操作</li>
							<div class="clearfix"> </div>
						  </ul>
						  <div class="cartListBox">
						  <c:set var="totalprices" value="0" />
						  <c:forEach items="${cartVoList}" var="cartVo" varStatus="status">
						  
								<ul class="cart-header"id="test">
								<li class="checkCol"><input type="checkbox" /></ >
								<li class="productCol">
									<a href="/front/productDetail.action?goodsId=${cartVo.goods.id}" >
										<img src="${basePath }${cartVo.goods.goodsLogo}" class="pull-left img-responsive" alt=""></a>
										<span class="pull-left cart-pDetail">${cartVo.goods.goodsName} 约${cartVo.goods.goodsWeight }g</span>
								</li>
								<li><span id="price${cartVo.cart.id }">${cartVo.goods.goodsOriginalPrice}</span></li>
								<li class="cart-num">
									<div class="addNum">						
										<span><input type="text" id="count${cartVo.cart.id }" class="item_quantity" value="${cartVo.cart.goodsCount}" readonly="readonly"/></span>
										<span>
											<a id="plus${cartVo.cart.id }" href="javascript:plusNum(${cartVo.cart.id })" class="plusNum">+</a>
											<a id="mimus${cartVo.cart.id }" href="javascript:minusNum(${cartVo.cart.id })" class="minus-Num">-</a>
										</span>
									</div>
								</li>
								<li><span id="xiaoji${cartVo.cart.id }">${cartVo.cart.totalPrice}</span></li>
								<li><span><a href="javascript:delfromCart(${cartVo.cart.id})" class="delBtn1">删除</a></span></li>
								<div class="clearfix"> </div>
								</ul>
							 <c:set var="totalprices" value="${totalprices+cartVo.cart.totalPrice }"/>	
							</c:forEach>	
						  </div>
					 </div>
					 <!--pc端购物车-->
					 <!--mobile端购物车-->
<!-- 					 <div class="in-check-mobile" > -->
<!-- 						  <div class="cartListBox"> -->
<!-- 								<ul class="cart-header"> -->
<!-- 									<li class="checkCol"><input type="checkbox" /></li> -->
<!-- 									<li class="productCol"> -->
<!-- 										<a href="productDetail.html" > -->
<!-- 											<img src="images/products/sc-img3.jpg" class="pull-left img-responsive" alt=""> -->
<!-- 										</a> -->
<!-- 										<div class="pull-left cart-pDetail"> -->
<!-- 											<span class="">有机怀山堂铁棍山药（垆土） 1.5kg/箱 长度为38cm 左右</span> -->
<!-- 											<div class="priceLine"> -->
<!-- 												<p class="m-price redColor">￥60.00</p> -->
<!-- 												<div class="pull-left cart-num"> -->
<!-- 													<div class="addNum">						 -->
<!-- 														<span><input type="text" class="item_quantity" value="1" /></span> -->
<!-- 														<span> -->
<!-- 															<a href="#" class="plusNum">+</a> -->
<!-- 															<a href="#" class="minus-Num">-</a> -->
<!-- 														</span> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
											
<!-- 									  </div> -->
<!-- 									</li> -->
<!-- 									<li class="operateBtn"><a href="#" class="btn btn-default delBtn1">删除</a></li> -->
<!-- 									<div class="clearfix"> </div> -->
<!-- 								</ul> -->
<!-- 								<ul class="cart-header" > -->
<!-- 									<li class="checkCol"><input type="checkbox" /></li> -->
<!-- 									<li class="productCol"> -->
<!-- 										<a href="productDetail.html" > -->
<!-- 											<img src="images/products/sc-img3.jpg" class="pull-left img-responsive" alt=""> -->
<!-- 										</a> -->
<!-- 										<div class="pull-left cart-pDetail"> -->
<!-- 											<span class="">有机怀山堂铁棍山药（垆土） 1.5kg/箱 长度为38cm 左右</span> -->
<!-- 											<div class="priceLine"> -->
<!-- 												<p class="m-price redColor">￥60.00</p> -->
<!-- 												<div class="pull-left cart-num"> -->
<!-- 													<div class="addNum">						 -->
<!-- 														<span><input type="text" class="item_quantity" value="1" /></span> -->
<!-- 														<span> -->
<!-- 															<a href="#" class="plusNum">+</a> -->
<!-- 															<a href="#" class="minus-Num">-</a> -->
<!-- 														</span> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
											
<!-- 									  </div> -->
<!-- 									</li> -->
<!-- 									<li class="operateBtn"><a href="" class="btn btn-default delBtn1">删除</a></li> -->
<!-- 									<div class="clearfix"> </div> -->
<!-- 								</ul> -->
<!-- 						  </div> -->
<!-- 					 </div> -->
					 <!--mobile端购物车-->
				  </div>					  
			 </div>
		 </div>
		 <div class="cart-fixBar">
		 	<div class="container">
			 	<ul class="cart-con">
			 		<!-- <li class="checkCol"><input type="checkbox" />全选</li> -->
			 		<li class="totalCol">
			 			<a href="/front/orderrecord/businessConfirm.action" class="pull-right cartSubmitBtn">去结算</a>
			 			<div class="pull-right totalMoney">
				 			<p>总价:<span class="t-money" id="totalprices"><fmt:formatNumber type="number" value="${totalprices }" pattern="0.0" maxFractionDigits="1"/> </span>￥</p>
				 			<!-- <p>已节省:-￥120.00</p> -->
			 			</div>
			 		</li>
			 	</ul>
		 	</div>
		 </div>

	 </div>
</div>
<!---->
<%@include file="footer.jsp"%>
</body>
</html>