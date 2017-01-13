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


function plusNum(id)
{
		var index = $("#count"+id).val();
		index++;
		$("#count"+id).val(index);
}
function minusNum(id)
{
		var index = $("#count"+id).val();
		index--;
		if(index>=1)
			{
				$("#count"+id).val(index);
			}else
				{
				$("#count"+id).val(1);
				}
}

function addToCart(obj,money){
	var userId = $("#userId").val();
	var id = $(obj).attr("id").substring(3);
	var count = $("#count"+id).val();
	$("#simpleCart_total").html((Number($("#simpleCart_total").html())+money*Number(count)).toFixed(1));
	$("#simpleCart_number").html((Number($("#simpleCart_number").html())+Number(count)));
	if(userId!=null&&userId!=""){
		$.ajax({
			type:"POST", 
			url:"<%=basePath%>/front/shopingCart/add.action",
			data:{"goodsId":id,"goodsCount":count,"clientUserId":userId,_:new Date().getTime()},
			dataType:"json",
			success:function(msg){
				layer.msg("加入成功");
				}
			});
	}else
	{
		//layer.msg("请先登录");
		//setTimeout("window.location.href='<%=basePath%>/front/login.jsp'",1000);
		var cart = getCartCookie();
		
		var item=new Object();
		item.cookieId = getUuid();
		item.goodsId = id;
		item.goodsCount = count;
		item.price = Number($("#price"+id).val());
		cart.push(item);
		$.cookie('cart', JSON.stringify(cart), { expires: 7, path: '/' }); 
		layer.msg("加入成功");
	}
}

function getCartCookie(){
	var cartCookie = $.cookie('cart')//拿到cookie
	if(cartCookie==null||cartCookie==""){
		var cartCookie = [];
		return cartCookie;
	}
	return JSON.parse(cartCookie); 
}
function getUuid(){
	  var len=32;//32长度
	  var radix=16;//16进制
	  var chars='0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');var uuid=[],i;radix=radix||chars.length;if(len){for(i=0;i<len;i++)uuid[i]=chars[0|Math.random()*radix];}else{var r;uuid[8]=uuid[13]=uuid[18]=uuid[23]='-';uuid[14]='4';for(i=0;i<36;i++){if(!uuid[i]){r=0|Math.random()*16;uuid[i]=chars[(i==19)?(r&0x3)|0x8:r];}}}
	  return uuid.join('');
}
</script>


<body>
<div class="product-model">	 
	 <div class="container">
		 <c:if test="${!empty first && !empty three}">
			<ol class="breadcrumb">
				  <li><a href="index.action">首页</a></li>
				  <li class="">${first}</li>
				  <li class="">${three}</li>
		 	</ol>
		 </c:if>		
			 <div class="col-md-12 product-model-sec">
			 	<c:if test="${empty productList}">
			 		<div class="searchTips">
				 			<p>很抱歉！没有找到与“${search}”相关的商品。</p>
				 			<p>建议您：</p>
				 			<p>1.查看您输入的文字是否有误;</p>
				 			<p>2.调整输入的关键字;</p>
			 				<a href="<%=basePath%>front/index.action">返回首页>></a>
			 		</div>
			 	</c:if>
			 	<c:if test="${empty first && empty three}">
			 		<c:if test="${!empty productList}">
			 			<h3 class="searchTipsNav"><span>“${search}”</span>搜索到${fn:length(productList)}件相关商品</h3>
			 		</c:if>
				 </c:if>
			 	<c:forEach items="${productList}" var="product" varStatus="status">
					 <div class="product-grid love-grid">	
				 		<a href="<%=basePath%>/front/productDetail.action?goodsId=${product.businessGoods.id}">				
							<div class="product-img b-link-stripe b-animate-go  thickbox">
								<img src="${basePath }${product.businessGoods.goodsLogo}" class="img-responsive" alt="" style="height: 250px"/>
								<div class="b-wrapper">
									<h4 class="b-animate b-from-left  b-delay03">							
										<button class="btns"><span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span>查看详情</button>
									</h4>
								</div>
							</div>
						</a>						
						<div class="product-info simpleCart_shelfItem">
							<div class="product-info-cust prt_name">
								<h4><a href="<%=basePath%>/front/productDetail.action?goodsId=${product.businessGoods.id}">${product.businessGoods.goodsName} </a></h4>
								<p><a href="<%=basePath%>/front/productDetail.action?goodsId=${product.businessGoods.id}">农场出品，清脆爽口</a></p>
								<input id="price${product.businessGoods.id}" type="hidden" value="${product.businessGoods.goodsOriginalPrice }"/>
								<p class="item_price">￥${product.businessGoods.goodsOriginalPriceStr }/${product.businessWeight.weightName }</p>
								<div class="addCartBox">	
									<div class="addNum">						
										<span><input id="count${product.businessGoods.id }" type="text" class="item_quantity" value="1"/></span>
										<span>
											<a id="plus${product.businessGoods.id }" href="javascript:plusNum(${product.businessGoods.id })" class="plusNum">+</a>
											<a id="mimus${product.businessGoods.id }" href="javascript:minusNum(${product.businessGoods.id })" class="minus-Num">-</a>
										</span>
									</div>
									<input type="button" id="add${product.businessGoods.id }" onclick="javascript:addToCart(this,${product.businessGoods.goodsOriginalPrice })" class="item_add" value="加入购物车">
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
