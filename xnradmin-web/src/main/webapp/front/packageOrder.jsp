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
	function toPage(){
		var pageN = $("#pageNum").val();
		if(pageN>'${totalCount}')
			{
				pageN = '${totalCount}';
			}else if(pageN<1)
				{
					pageN = 1;
				}
		window.location.href="${basePath}front/packageOrderDetail.action?pageNum="+pageN+"&businessOrderRecordId=${businessOrderRecordId}&comboId=${comboId}&comboUserId=${comboUserId}";
	}
</script>
<body>
<div class="checkout">	 
	 <div class="container">	
		 <ol class="breadcrumb">
		  <li><a href="${basePath }/front/index.action">首页</a></li>
		  <li class="active">套餐订单详情</li>
		 </ol>
		 <div class="product-price1">
			 <div class="check-out">			
				 <div class="order-checkBox">
					 <div class="orderStatusBox">
					 		<h3 class="pull-left">总订单号：<span class="statusBox">${orderRecord.orderNo}</span> 总配送次数：<span class="statusBox">${combo.comboTimes}</span> 未配置数：<span class="statusBox">${comboUser.usingTimes}</span></h3>
					 </div>
					 <div class="stepInfoBox">
						 <div class="receiptBox ">
							<div class="defalutAddBox" style="display:block" id="defalutAddBox">
								<p class="addr-name"><b>收货人信息：</b></p>
								<p class="addr-address"><span>${orderRecord.userRealName}</span><span>${orderRecord.clientUserMobile}</span><span>${orderRecord.cityName} ${orderRecord.countryName} ${orderRecord.areaName}</span></p>
							</div>
						</div>
						</div>
						<div class="receiptBox">
							 <p class="addr-name"><b>支付方式：</b></p>
							 <div class="infoItem paymentMode">
							 	<span>${orderRecord.paymentProviderName}</span>
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
		                    			<li style="width:25%">收货地址</li>
<!-- 		                    			<li>确认收货</li> -->
		                    		</ul>
		                    	</div>
		                    </div>
							 <c:forEach items="${comboList}" var="comboList">
								 <div class="orderList">
		                    	<div class="orderTit">
			                                                              订单号：<span>${comboList.orderRecord.orderNo}</span> 
		                    		<span class="orderTime">
			                          	${comboList.orderRecord.createTime}
			                        </span>
		                    	</div>
		                    	<div class="orderTitCol">
		                    		<ul>
		                    			<li class="ordercol-d">
			                    			<div class="pull-left  productListShow">
												<c:forEach items="${comboList.goodsList}" var="goodsList" begin="0" end="4"> 
													<a href="#"><img src="${basePath}${goodsList.goodsLogo}"></a>
													<c:if test="${fn:length(comboList.goodsList)>5}"><em>...</em></c:if>
												</c:forEach>
			                    		 	</div>
			                    		 	<div class="pull-right  productListNum">
			                    		 		<a href="#">共${fn:length(comboList.goodsList)}种</a>
			                    		 	</div>
		                    			</li>
		                    			<li>
		                    				<span>${comboList.orderRecord.userRealName}</span>
		                    			</li>
		                    			<li> 
					                          <span >${comboList.orderRecord.deliveryStatusName}</span>
					                    </li>
					                    <li  style="width:25%"> 
					                          <span>${comboList.orderRecord.cityName}${comboList.orderRecord.countryName} ${comboList.orderRecord.areaName}</span>
					                    </li>
<!-- 					                     <li>  -->
<!-- 					                          <span> -->
<%-- 						                          <c:if test="${comboList.orderRecord.deliveryStatus==208}"> --%>
<!-- 					                    			未收货 -->
<%-- 					                    		  </c:if> --%>
<%-- 					                    		  <c:if test="${comboList.orderRecord.deliveryStatus==209}"> --%>
<!-- 					                    			已收货 -->
<%-- 					                    		  </c:if> --%>
<!-- 				                    		</span> -->
<!-- 					                    </li> -->
		                    		</ul>
		                    	</div>
		                    </div>
	                    </c:forEach>
		                    <nav class="text-center">
						      <ul class="pagination">
						      <c:choose>
								<c:when test="${pageNum==1}">
									 <li class="disabled"><a>«</a></li>
								</c:when>
								<c:otherwise>	
									<li><a href="${basePath}front/packageOrderDetail.action?pageNum=${pageNum-1}&businessOrderRecordId=${businessOrderRecordId}&comboId=${comboId}&comboUserId=${comboUserId}">«</a></li>
								</c:otherwise>
							</c:choose>
							<li class="active"><a>${pageNum}</a></li>
								<c:choose> 
									<c:when test="${pageNum==totalCount}">
										 <li class="disabled"><a>»</a></li>
									</c:when>
									<c:otherwise>	
										<li><a href="${basePath}front/packageOrderDetail.action?pageNum=${pageNum+1}&businessOrderRecordId=${businessOrderRecordId}&comboId=${comboId}&comboUserId=${comboUserId}">»</a></li>
									</c:otherwise>
								</c:choose>
							<li><a>共${totalCount}页</a></li>
							<li><a>跳转至</a></li>
							<li><a class="writeNum"><input type="text" value="" id="pageNum" onkeyup="if(/\D/.test(this.value)){this.value='';}" style="width:25px;height:20px"/></a></li>
						    <li><a href="javascript:toPage()">»</a></li>
						    </ul>
		   				</nav>
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