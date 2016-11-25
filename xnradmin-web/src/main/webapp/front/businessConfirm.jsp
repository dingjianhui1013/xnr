<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>支付页</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="康源公社" content="康源,公社,蔬菜,有机" />

<%@include file="header.jsp"%>

</head>

<script type="text/javascript">

$(document).ready(function() {
	$('#newAddressForm').submit(function() {
		$.ajax({
            url:"mobileSurveyAction_addSurvey.action",//提交地址
            data:$("#newAddressForm").serialize(),//将表单数据序列化
            type:"POST",
            dataType:"json",
            success:function(result){
                if (result.success == '100'){
                    $("#mySection").hide();
                    $(".footer").hide();
                    $("#alertMsg").show();
                     
                }else{
                    alert("失败！");
                }
            }
        });	
	});
});


function changePaymentMethod(index){
	if(index = 0){
		$("#wechat").addClass("active");
		$("#alipay").removeClass("active");
	}else if(index = 1){
		$("#alipay").addClass("active");
		$("#wechat").removeClass("active");
	}
}


</script>


<!---->
<div class="checkout">	 
	 <div class="container">	
		 <ol class="breadcrumb">
		  <li><a href="index.html">首页</a></li>
		  <li class="active">核对订单信息</li>
		 </ol>
		 <div class="product-price1">
			 <div class="check-out">			
				 <div class="order-checkBox">
					 <h3>填写并核对订单信息</h3>
					 <div class="stepInfoBox">
						 <div class="receiptBox ">
							<div class="defalutAddBox" style="display:block" id="defalutAddBox">
								<p class="addr-name"><b>收货人信息：</b><a href="#" class="eidtAddressBtn">[修改]</a></p>
								
								<p class="addr-address"><span>张某某</span><span>1360103343</span><span>北京市昌平区回龙观</span></p>
							</div>
							 <div class="infoItem editAddres" id="editAddres"style="display:none;">
							 	<form class="form-horizontal">
							 		<div class="radio addListItem cur">
							 		<c:forEach items="${addrs}" var="addr">
							 			<c:if test="${addr.type==1}">
								        <label>
								          <input type="radio" name="optionsRadios" id="addr${addr.id}" value="${addr.id}">
								          <span>${addr.receiptName}</span>
								          <span>${addr.detailedAddress}</span>
								          <span>${addr.tel}</span>
								          <div class="operatorBox">
									          <a href="#" class="editAddBtn">编辑</a>
									          <a href="#" class="delAddBtn">删除</a>
								          </div>
								        </label>
								        </c:if>
								       </c:forEach> 
										</div>
										<div class="radio addListItem">
										<c:forEach items="${addrs}" var="addr">
								        <label>
								          <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
								          <span>${addr.receiptName}</span>
								          <span>${addr.detailedAddress}</span>
								          <span>${addr.tel}</span>
								          <div class="operatorBox">
									          <a href="#" class="editAddBtn">编辑</a>
									          <a href="#" class="delAddBtn">删除</a>
								          </div>
								        </label>
								        </c:forEach>
										</div>
										<div class="radio addListItem">
								        <label>
								          <input type="radio" name="optionsRadios" id="optionsRadios3" value="option2">
								          <span>使用新地址</span>
								        </label>
										</div>
										<div class="add-addItem">
											<form id="newAddressForm" class="form-horizontal" role="form">
												  <div class="form-group">
												    <label class="col-sm-2 control-label"><em>*</em>收货人：</label>
												    <div class="col-sm-6">
											          <input type="text" class="form-control" name="" id="" placeholder="请输入收货人">
											        </div>
												  </div>
												  <div class="form-group">
												    <label class="col-sm-2 control-label">所在地区：</label>
												    <div class="col-sm-6">
											          <select class="form-control provinceSel">
											          	<option value="">山东省</option>
											          </select>
											          <select class="form-control citySel">
											          	<option value="">济南市</option>
											          </select>
											          <select class="form-control countrySel">
											          	<option value="0">市中区</option>
											          	<option value="1">历下区</option>
											          	<option value="2">历城区</option>
											          	<option value="3">高新区</option>
											          	<option value="4">槐荫区</option>
											          </select>
												  </div>
												  </div>
												  <div class="form-group">
												    <label class="col-sm-2 control-label">详细地址：</label>
													    <div class="col-sm-6">
												          <input type="text" class="form-control" id="" placeholder="请输入详细地址">
												        </div>
												  </div>
												  <div class="form-group">
												    <label class="col-sm-2 control-label">电话号码：</label>
													    <div class="col-sm-6">
												          <input type="text" class="form-control" id="" placeholder="请输入电话">
												        </div>
												  </div>
												  <div class="editBox">
												  	 <button type="submit" class="btn btn-primary col-sm-offset-2">保存收货地址</button>
												  </div>
											</form>
										</div>
							 	</form>
							 </div>
						</div>
						</div>
						<div class="receiptBox">
							 <p class="addr-name"><b>支付及配送方式：</b></p>
							 <div class="infoItem paymentMode">
							 	<a href="changePaymentMethod(0)" id="wechat"  class="active">微信支付</a>
							 	<a href="changePaymentMethod(1)" id="alipay"  >支付宝支付</a>
							 </div>
						</div>
						<div class="receiptBox checkOrderInfo">
							<h3>商品信息：</h3>
							 <div class="in-check" >
								  <ul class="unit">
									<li class="productCol"><span>商品</span></li>		
									<li><span>单价</span></li>
									<li><span>数量</span></li>
									<li>小计(元)</li>
									<div class="clearfix"> </div>
								  </ul>
								  <div class="cartListBox">
								  <c:forEach items="${cartVoList}" var="cartVo" varStatus="status">
										<ul class="cart-header"id="test">
											<li class="productCol">
												<a href="productDetail.html" >
													<img src="images/products/sc-img3.jpg" class="pull-left img-responsive" alt=""></a>
													<span class="pull-left cart-pDetail">${cartVo.goods.goodsName} 约${cartVo.goods.goodsWeight }g</span>
											</li>
											<li><span>${cartVo.goods.goodsOriginalPrice}</span></li>
											<li class="cart-num">						
												<span class="chooseNum">${cartVo.cart.goodsCount}</span>
											</li>
											<li><span>${cartVo.cart.totalPrice}</span></li>
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
			 			<div class=" totalMoney">
				 			<p>总价:<span class="t-money">￥120.00</span></p>
				 			<p>已节省:-￥120.00</p>
			 			</div>
			 			<a href="<%=basePath%>/front/orderrecord/add.action" class=" cartSubmitBtn">提交订单</a>
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