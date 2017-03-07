<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>套餐订单详情页</title>
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
	else
		{
			//layer.msg("请先登录");
			//setTimeout("window.location.href='<%=basePath%>/front/login.jsp'",1000);
		var cart = getCartCookie();
		
		var item=new Object();
		item.cookieId = getUuid();
		item.goodsId = null;
		item.comboId = id;
		item.goodsCount = goodsNumber;
		item.price = Number($("#price"+id).val());
		cart.push(item);
		$.cookie('cart', JSON.stringify(cart), { expires: 7, path: '/' }); 
		layer.msg("加入成功");
		}
	
}
</script>
<body>
<div class="checkout">	 
	 <div class="container">	
		 <ol class="breadcrumb">
		  <li><a href="index.html">首页</a></li>
		  <li class="active">套餐订单详情</li>
		 </ol>
		 <div class="product-price1">
			 <div class="check-out">			
				 <div class="order-checkBox">
					 <div class="orderStatusBox">
					 		<h3 class="pull-left">总订单号：<span class="statusBox">2016121610103223984</span> 总配送次数：<span class="statusBox">52</span> 未配置数：<span class="statusBox">10</span></h3>
					 </div>
					 <div class="stepInfoBox">
						 <div class="receiptBox ">
							<div class="defalutAddBox" style="display:block" id="defalutAddBox">
								<p class="addr-name"><b>收货人信息：</b></p>
								<p class="addr-address"><span>丁建辉</span><span>18811528411</span><span>北京市 燕郊 测试地址</span></p>
							</div>
						</div>
						</div>
						<div class="receiptBox">
							 <p class="addr-name"><b>支付方式：</b></p>
							 <div class="infoItem paymentMode">
							 	<span>支付宝支付</span>
							 </div>
						</div>
						<div class="receiptBox checkOrderInfo">
							<h3>子订单清单：</h3>
							 <div class="orderListBox">
		                    	<div class="orderTitCol">
		                    		<ul>
		                    			<li class="ordercol-d">订单详情</li>
		                    			<li>收货人</li>
		                    			<li>派送状态</li>
		                    			<li>收货地址</li>
		                    			<li>确认收货</li>
		                    		</ul>
		                    	</div>
		                    </div>
							 <div class="orderList">
	                    	<div class="orderTit">
		                                                              订单号：<span>2016121610103223984</span> 
	                    		<span class="orderTime">
		                          	2016-12-16 00:00:00.0 
		                        </span>
	                    	</div>
	                    	<div class="orderTitCol">
	                    		<ul>
	                    			<li class="ordercol-d">
		                    			<div class="pull-left  productListShow">
<%-- 		                    			<c:forEach items="${loop.businessOrderRelationVO}" var="businessOrderRelations" begin="0" end="2">  --%>
<%-- 			                    			<a href="#"><img src="${basePath}${businessOrderRelations.businessGoods.goodsLogo}"></a> --%>
<%-- 		                    			</c:forEach> --%>
<%-- 		                    			<c:if test="${loop.businessOrderRecord.totalCount>3}"><em>...</em></c:if> --%>
											<a href="#"><img src="http://localhost:8080//themes/business/goodsLogo/0c1cdca3-2be3-4304-ae45-c0d0b227cdd9r-img2.jpg"></a>
											<a href="#"><img src="http://localhost:8080//themes/business/goodsLogo/0c1cdca3-2be3-4304-ae45-c0d0b227cdd9r-img2.jpg"></a>
											<a href="#"><img src="http://localhost:8080//themes/business/goodsLogo/0c1cdca3-2be3-4304-ae45-c0d0b227cdd9r-img2.jpg"></a>
		                    		 		<em>...</em>
		                    		 	</div>
		                    		 	<div class="pull-right  productListNum">
		                    		 		<a href="${basePath}front/orderDetail.action?businessOrderRecordId=${loop.businessOrderRecord.id}">共3件></a>
		                    		 	</div>
	                    			</li>
	                    			<li>
	                    				<span>收货人姓名</span>
	                    			</li>
	                    			<li> 
				                          <span >派送状态</span>
				                    </li>
				                    <li> 
				                          <span >北京市 燕郊 测试地址</span>
				                    </li>
				                     <li> 
				                          <span >确认收货</span>
				                    </li>
	                    		</ul>
	                    	</div>
	                    </div>
						 </div>
					 </div>
				  </div>					  
			 </div>
		 </div>
</div>
</div> 
<%@include file="footer.jsp" %>
</body>
</html>