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

function onSubmit(){
	if($("#cartids").val()== ""){
		layer.msg("请至少选择一种商品!");
		return false;
	}
	
	$("#inputForm").submit();
	return true;
			

}




function add(a, b) {
	var  c=a+b;
	var d=c.toFixed(1);	
	return Number(d);
}

function sub(a, b) {
	var  c=a-b;
	var d=c.toFixed(1);	
	return Number(d);
}

function plusNum(id)

{	
		var userId = $("#userId").val();
		var price = Number($("#price"+id).html());  
		var xiaoji = Number($("#xiaoji"+id).html());
		var xiaojiNew = add(xiaoji,price);
		
		$("#xiaoji"+id).html(xiaojiNew);
		var index = $("#count"+id).val();
		index++;
		$("#simpleCart_total").html((Number($("#simpleCart_total").html())+Number(price)).toFixed(1));
		$("#simpleCart_number").html((Number($("#simpleCart_number").html())+Number(1)));
		$("#count"+id).val(index);
		if(userId!=null&&userId!=""){
			modefyToCart(id);
			
		}else{
			var cart = getCartCookie();
			var x;
			for (x in cart)
			{
				var item = cart[x];
				if(item.cookieId==id){
					item.goodsCount = item.goodsCount+1;
				}
			}
		$.cookie('cart', JSON.stringify(cart), { expires: 7, path: '/' }); 
		}
		totalprice();
}
function minusNum(id)
{		
		var userId = $("#userId").val();
		var price = Number($("#price"+id).html());
		var xiaoji = Number($("#xiaoji"+id).html());
		
		var index = $("#count"+id).val();
		index--;
		if(index>=1)
			{
				var xiaojiNew = sub(xiaoji,price);
				$("#xiaoji"+id).html(xiaojiNew);
				$("#count"+id).val(index);
				$("#simpleCart_total").html((Number($("#simpleCart_total").html())-Number(price)).toFixed(1));
				$("#simpleCart_number").html((Number($("#simpleCart_number").html())-Number(1)));
				if(userId!=null&&userId!=""){
					modefyToCart(id);
				}else{
					var cart = getCartCookie();
					var x;
					for (x in cart)
					{
						var item = cart[x];
						if(item.cookieId==id){
							item.goodsCount = item.goodsCount-1;
						}
					}
				$.cookie('cart', JSON.stringify(cart), { expires: 7, path: '/' }); 
				}
			}else
				{
				$("#count"+id).val(1);
				}
		totalprice();
}



function delfromCart(id){
	var userId = $("#userId").val();
	var totalpriceAll = 0;
	if(userId!=null&&userId!=""){
	$.ajax({
		type:"POST", 
		url:"<%=basePath%>front/shopingCart/del.action",
		data:{"shoppingCartId":id,_:new Date().getTime()},
		dataType:"json",
		success:function(msg){
				
			}
		});
	}else{
		var cart = getCartCookie();
		var x;
		for (x in cart)
		{
			var item = cart[x];
			if(item.cookieId==id){
				cart.splice($.inArray(item,cart),1);
			}
		}
	$.cookie('cart', JSON.stringify(cart), { expires: 7, path: '/' }); 
	}
	var price = Number($("#price"+id).html());
	var index = Number($("#count"+id).val());
	$("#ul"+id).remove();
	
	totalprice();
	//计算总价
	$('input:checkbox').each(function(i){
		if($(this).attr("id")!="checkAll"){
		var cartId = ($(this).attr("cartId"));
		var xiaoji = Number($("#xiaoji"+cartId).html());
		totalpriceAll = add(totalpriceAll,xiaoji);
		}
	});
	$("#simpleCart_total").html(totalpriceAll);
	$("#simpleCart_number").html((Number($("#simpleCart_number").html())-index));
}

//计算总价格
function totalprice(obj){
	var totalprice = 0;
	$('input:checkbox:checked').each(function(i){
		if($(this).attr("id")!="checkAll"){
		var cartId = ($(this).attr("cartId"));
		var xiaoji = Number($("#xiaoji"+cartId).html());
		totalprice = add(totalprice,xiaoji);
		}
	});
	$("#totalprices").html(totalprice);
	
	$("#totalMoney").val(totalprice);
	
	
	
	var cartids = $("#cartids").val();
	
	
	
	if($(obj).is(":checked") == true) {
		
		if(cartids==""||cartids.length==0){
			$("#cartids").val($(obj).val());
		}else{
			$("#cartids").val(cartids+","+$(obj).val());
		}	
	}else{
		if(cartids.indexOf($(obj).val())>-1){
			cartids = cartids.replace($(obj).val(), "");
			cartids = cartids.replace(",,", ",");
			if(cartids.indexOf(",")==0){
				cartids = cartids.subString(1);
			}
			
			$("#cartids").val(cartids);
			
		}
		
	}
	
	
	
}
//计算全选价格
function totalpriceAll(obj){
	var totalprice = 0;
	//alert(obj.checked);
	if(obj.checked==true){
		$('input:checkbox').each(function(i){
			if($(this).attr("id")!="checkAll"){
			var cartId = ($(this).attr("cartId"));
			var xiaoji = Number($("#xiaoji"+cartId).html());
			totalprice = add(totalprice,xiaoji);
			}
		});	
	}
	
	$("#totalprices").html(totalprice);
	
	$("#totalMoney").val(totalprice);
	
	
	var cartids = "";
	
	if($(obj).is(":checked") == true) {
		/* $('input:checkbox').each(function(i){
			if($(this).attr("id")!="checkAll"){
			
			if(cartids==""||cartids.length==0){
				cartids = $(this).val();
			}else{
				cartids = cartids +","+$(this).val();  
			}	
		}
		}); */	
		
		$("#cartids").val("all");
		
	}else{
		$("#cartids").val("");
	}
	
}
//修改数据库，购物车的商品数量和小计
function modefyToCart(id){
	var userId = $("#userId").val();
	var count = $("#count"+id).val();
	var xiaoji = Number($("#xiaoji"+id).html());
	if(userId!=null&&userId!=""){
		$.ajax({
			type:"POST", 
			url:"<%=basePath%>/front/shopingCart/modifyCount.action",
			data:{"cartId":id,"goodsCount":count,"totalPrice":xiaoji,_:new Date().getTime()},
			dataType:"json",
			success:function(msg){
				}
			});
	}else
	{
		layer.msg("请先登录");
		setTimeout("window.location.href='<%=basePath%>/front/login.jsp'",1000);
	}
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
							<li class="checkCol"><label for="checkAll" id="checkALLBtn"><input type="checkbox" id="checkAll" onclick="totalpriceAll(this)"/>全选</label>
							</li>
							<li class="productCol"><span>商品</span></li>		
							<li><span>单价</span></li>
							<li><span>数量</span></li>
							<li>小计(元)</li>
							<li>操作</li>
							<div class="clearfix"> </div>
						  </ul>
						  <div class="cartListBox">
						  <c:forEach items="${cartVoList}" var="cartVo" varStatus="status">
						  		<c:if test="${!empty cartVo.cart.id }">
								<ul class="cart-header" id="ul${cartVo.cart.id }">

								</c:if>
								<c:if test="${empty cartVo.cart.id }">
								<ul class="cart-header" id="ul${cartVo.cart.cookieCartId }">
								</c:if>
								<li class="checkCol">
								<c:if test="${!empty cartVo.cart.id }">
								<input type="checkbox" value="${cartVo.cart.id }" cartId="${cartVo.cart.id }" onclick="totalprice(this)"/>
								</c:if>
								<c:if test="${empty cartVo.cart.id }">
								<input type="checkbox" value="${cartVo.cart.id }" cartId="${cartVo.cart.cookieCartId }" onclick="totalprice(this)"/>
								</c:if>

								<li class="productCol">
									<a href="${basePath }front/productDetail.action?goodsId=${cartVo.goods.id}" >
										<img src="${basePath }${cartVo.goods.goodsLogo}" class="pull-left img-responsive" alt=""></a>
										<span class="pull-left cart-pDetail">${cartVo.goods.goodsName} 约${cartVo.goods.goodsWeight }g</span>
								</li>
								<li>
									<c:if test="${!empty cartVo.cart.id }">
										<span id="price${cartVo.cart.id }">${cartVo.goods.goodsOriginalPrice}</span>
									</c:if>
									<c:if test="${empty cartVo.cart.id }">
										<span id="price${cartVo.cart.cookieCartId }">${cartVo.goods.goodsOriginalPrice}</span>
									</c:if>
								</li>
								<li class="cart-num">
									<div class="addNum">						
										<span>
											<c:if test="${!empty cartVo.cart.id }">
												<input type="text" id="count${cartVo.cart.id }" class="item_quantity" value="${cartVo.cart.goodsCount}" readonly="readonly"/>
											</c:if>
											<c:if test="${empty cartVo.cart.id }">
												<input type="text" id="count${cartVo.cart.cookieCartId }" class="item_quantity" value="${cartVo.cart.goodsCount}" readonly="readonly"/>
											</c:if>
										</span>
										<span>
											<c:if test="${!empty cartVo.cart.id }">
												<a id="plus${cartVo.cart.id }" href="javascript:plusNum(${cartVo.cart.id })" class="plusNum">+</a>
											</c:if>
											<c:if test="${empty cartVo.cart.id }">
												<a id="plus${cartVo.cart.cookieCartId }" href="javascript:plusNum('${cartVo.cart.cookieCartId }')" class="plusNum">+</a>
											</c:if>
											<c:if test="${!empty cartVo.cart.id }">
												<a id="mimus${cartVo.cart.id }" href="javascript:minusNum(${cartVo.cart.id })" class="minus-Num">-</a>
											</c:if>
											<c:if test="${empty cartVo.cart.id }">
												<a id="mimus${cartVo.cart.cookieCartId }" href="javascript:minusNum('${cartVo.cart.cookieCartId }')" class="minus-Num">-</a>
											</c:if>
											
										</span>
									</div>
								</li>
								<li>
									<c:if test="${!empty cartVo.cart.id }">
									<span id="xiaoji${cartVo.cart.id }">${cartVo.cart.totalPrice}</span>
									</c:if>
									<c:if test="${empty cartVo.cart.id }">
									<span id="xiaoji${cartVo.cart.cookieCartId }">${cartVo.cart.totalPrice}</span>
									</c:if>
								</li>
								<li><span>
									<c:if test="${!empty cartVo.cart.id }">
										<a href="javascript:delfromCart(${cartVo.cart.id})" class="delBtn1">删除</a>
									</c:if>
									<c:if test="${empty cartVo.cart.id }">
										<a href="javascript:delfromCart('${cartVo.cart.cookieCartId }')" class="delBtn1">删除</a>
									</c:if>
									</span></li>
								<div class="clearfix"> </div>
								</ul>
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
			 		
			 		    <form method="post" action="/front/orderrecord/businessConfirm.action">
			 		    	<input type="hidden" id="cartids" name="cartids"/>
			 		    	<input type="hidden" id="totalMoney" name="totalMoney"/>
			 		    
			 		    <input type="submit" onclick="return onSubmit()" class="pull-right cartSubmitBtn" value="去结算">
			 		    </form>
			 		
			 			
			 			<div class="pull-right totalMoney">
				 			<p>总价:￥<span class="t-money" id="totalprices"> 0</span></p>
				 			
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
