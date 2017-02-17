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


$(function(){
	 radioBound();
	
});



function radioBound(){
	 $(":radio").click(function(){
		   //$(this).val();
		   
		  $(this).parent().parent().css('background','#fff3e8');
		  
		  
		  //给提交的地址id赋值
		  
		  
		  if($(this).val()!='addnewoption'){
			  var id = $(this).parent().parent().attr("id");
			  $("#receiptAddressId").val(id.substring(5));
			  $("#newAddressDiv").hide();
		  }
		  
		  
		  if($(this).val()=='addnewoption'){
			  $("#newAddressDiv").show();
		  }
		  
		  
		  $(":radio").each(function(){
				if(this.checked == false) {
					$(this).parent().parent().css('background','white');
				}
				
				
				if(this.value == 'addnewoption'){
					$("#receiptName").val("");
	            	$("#detailedAddress").val("");
	            	$("#telAddress").val("");
				}
			})

		  
		   
		  });
}


function useNewAddress(){
	
	
	
	var url = '<%=basePath%>front/receiptAddress/saveNewReceiptAddress.action';   
	
	var receiptName = $("#receiptName").val();
	var detailedAddress = $("#detailedAddress").val();
	var telAddress = $("#telAddress").val();
	var county = $("#county").val();
	
	var addressId = 0;
	
	 $(":radio").each(function(){
			if(this.checked == true) {
				if(this.value != 'addnewoption'){
					addressId = this.value;
				}
			}
	
	 });
	 
	 
	 
	$.ajax({
        url:url,
        data:{"addressId":addressId,"receiptName":receiptName,"detailedAddress":detailedAddress,"telAddress":telAddress,"county":county,_:new Date().getTime()},
        type:"POST",
        dataType:"json",
        success:function(data){
        	
            if (data.saveSuccess == 1){
            	$("#receiptName").val("");
            	$("#detailedAddress").val("");
            	$("#telAddress").val("");
            	
            	
            	var str = "<label id='label"+data.addressId+"' style='background: #fff3e8'><span><input type='radio' name='optionsRadios' checked='checked'></span> <span>"+receiptName+"</span><span>"+detailedAddress+"</span><span>"+telAddress+"</span><div class='operatorBox'>"
		          +"<a href='javascript:;' onclick='editAddress(this,"+data.addressId+"' class='editAddBtn'>编辑</a>"
		          +"<a href='javascript:deleteAddress("+data.addressId+")' class='delAddBtn'>删除</a>"
	          	  +"</div></label>";
        		
        		$("#addresstype0").append(str);
        	
            	$("#addnewoption").parent().parent().css('background','white');
            	
            	
            	radioBound();
            	
            	$("#newAddressDiv").hide();
            	
            	layer.msg("保存成功！");
            	
            }else if(data.saveSuccess == 2){
            	
            	var editSpan = $("#addr"+addressId).parent();
            	
            	
            	editSpan.next("span").text(receiptName);
            	editSpan.next().next().text(detailedAddress);
            	editSpan.next().next().next().text(telAddress);
            	
            	
                
            	$("#receiptName").val("");
            	$("#detailedAddress").val("");
            	$("#telAddress").val("");
            	
            	radioBound();
            	
            	$("#newAddressDiv").hide();
            	layer.msg("编辑成功！");
            	
            }else{
            	
            }
        }
    });	
	
}


function editAddress(obj,addressId){
	

	$(obj).parent().parent().css('background','#fff3e8');
	
	var objInput = $(obj).parent().parent().find("input");
	
	objInput.prop("checked",true);
	
	
	 $(":radio").each(function(){
			if(this.checked == false) {
				$(this).parent().parent().css('background','white');
			}
			
		})
	
	
	
	var url = '<%=basePath%>front/receiptAddress/getReceiptAddress.action';
	
	$.ajax({
        url:url,
        data:{"addressId":addressId,_:new Date().getTime()},
        type:"POST",
        dataType:"json",
        success:function(data){
            if (data.getSuccess == 1){
            	 $("#newAddressDiv").show();
            	
            	$("#receiptName").val(data.receiptName);
            	$("#detailedAddress").val(data.detailedAddress);
            	$("#telAddress").val(data.telAddress);
            	$("#county").val(data.county);
            }else{
                
            }
        }
    });	
	
}


function deleteAddress(addressId){
var url = '<%=basePath%>front/receiptAddress/deleteReceiptAddress.action';

		$.ajax({
			url : url,
			data : {
				"addressId" : addressId,
				_ : new Date().getTime()
			},
			type : "POST",
			dataType : "json",
			success : function(data) {
				if (data.deleteSuccess == 1) {
					$("#label" + addressId).css('display', 'none');

				} else {

				}
			}
		});
	}

	function changePaymentMethod(index) {

		if (index == "0") {

			$("#wechat").addClass("active");
			$("#alipay").removeClass("active");

			$("#paymethod").val("0");

		} else if (index == "1") {

			$("#alipay").addClass("active");
			$("#wechat").removeClass("active");

			$("#paymethod").val("1");
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
							<div class="defalutAddBox" style="display: block"
								id="defalutAddBox">
								<p class="addr-name">
									<b>收货人信息：</b><a href="#" class="eidtAddressBtn">[修改]</a>
								</p>

								<c:forEach items="${addrs}" var="addr">
									<c:if test="${addr.type==1}">
										<p class="addr-address">
											<span>${addr.receiptName}</span><span>${addr.tel}</span><span>${addr.detailedAddress}</span>
										</p>
									</c:if>
								</c:forEach>

							</div>
							<div class="infoItem editAddres" id="editAddres"
								style="display: none;">
								<form class="form-horizontal">
									<div class="radio addListItem">
										<c:forEach items="${addrs}" var="addr">
											<c:if test="${addr.type==1}">
												<label id="label${addr.id}" style="background: #fff3e8">
													<span> <input type="radio" checked="checked"
														name="optionsRadios" id="addr${addr.id}"
														value="${addr.id}">
												</span> <span>${addr.receiptName}</span> <span>${addr.detailedAddress}</span>
													<span>${addr.tel}</span>
													<div class="operatorBox">
														<a  href="javascript:;"  onclick="editAddress(this,${addr.id})"
															class="editAddBtn">编辑</a> 
														<a  href="javascript:deleteAddress(${addr.id})"
															class="delAddBtn">删除</a>
													</div>
												</label>
											</c:if>
										</c:forEach>
									</div>
									<div class="radio addListItem" id="addresstype0">
										<c:forEach items="${addrs}" var="addr">
											<c:if test="${addr.type==0}">
												<label id="label${addr.id}"> <span> <input
														type="radio" name="optionsRadios" id="addr${addr.id}"
														value="${addr.id}">
												</span> <span>${addr.receiptName}</span> <span>${addr.detailedAddress}</span>
													<span>${addr.tel}</span>
													<div class="operatorBox">
														<a href="javascript:;" onclick="editAddress(this,${addr.id})"
															class="editAddBtn">编辑</a> <a
															href="javascript:deleteAddress(${addr.id})"
															class="delAddBtn">删除</a>
													</div>
												</label>
											</c:if>
										</c:forEach>
									</div>
									<div class="radio addListItem">
										<label> <span> <input type="radio"
												name="optionsRadios" id="addnewoption" value="addnewoption">
										</span> <span>使用新地址</span>
										</label>
									</div>
									<div id="newAddressDiv" class="add-addItem"
										style="display: none">
										<form id="newAddressForm" class="form-horizontal" role="form">
											<div class="form-group">
												<label class="col-sm-2 control-label"><em>*</em>收货人：</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" name="receiptName"
														id="receiptName" placeholder="请输入收货人">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">所在地区：</label>
												<div class="col-sm-6">
													<select class="form-control provinceSel" id="province">
														<option value="山东省">山东省</option>
													</select> <select class="form-control citySel" id="city">
														<option value="济南市">济南市</option>
													</select> <select class="form-control countrySel" id="county">
														<option value="市中区">市中区</option>
														<option value="历下区">历下区</option>
														<option value="历城区">历城区</option>
														<option value="高新区">高新区</option>
														<option value="槐荫区">槐荫区</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">详细地址：</label>
												<div class="col-sm-6">
													<input type="text" class="form-control"
														name="detailedAddress" id="detailedAddress"
														placeholder="请输入详细地址">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">电话号码：</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" name="telAddress"
														id="telAddress" placeholder="请输入电话">
												</div>
											</div>
											<div class="editBox">
												<button type="button" onclick="useNewAddress()"
													class="btn btn-primary col-sm-offset-2">保存收货地址</button>
											</div>
										</form>
									</div>
								</form>
							</div>
						</div>
					</div>
					<div class="receiptBox">
						<p class="addr-name">
							<b>支付及配送方式：</b>
						</p>
						<div class="infoItem paymentMode">
							<a href="javascript:changePaymentMethod(0)" id="wechat"
								class="active">微信支付</a> <a
								href="javascript:changePaymentMethod(1)" id="alipay">支付宝支付</a>
						</div>
					</div>
					<div class="receiptBox checkOrderInfo">
						<h3>商品信息：</h3>
						<div class="in-check">
							<ul class="unit">
								<li class="productCol"><span>商品</span></li>
								<li><span>单价</span></li>
								<li><span>数量</span></li>
								<li>小计(元)</li>
								<div class="clearfix"></div>
							</ul>
							<div class="cartListBox">
								<c:forEach items="${cartVoList}" var="cartVo" varStatus="status">
									<ul class="cart-header" id="test">
										<li class="productCol"><a href="productDetail.html">
												<img src="${basePath }${cartVo.goods.goodsLogo}"
												class="pull-left img-responsive" alt="">
										</a> <span class="pull-left cart-pDetail">${cartVo.goods.goodsName}
												约${cartVo.goods.goodsWeight }g</span></li>
										<li><span>${cartVo.goods.goodsOriginalPrice}</span></li>
										<li class="cart-num"><span class="chooseNum">${cartVo.cart.goodsCount}</span>
										</li>
										<li><span>${cartVo.cart.totalPrice}</span></li>
										<div class="clearfix"></div>
									</ul>
								</c:forEach>
								<c:forEach items="${comboVOs}" var="comboVOs">
									<ul class="cart-header" id="test">
										<li class="productCol"><a href="productDetail.html">
												<img src="${basePath }${cartVo.goods.goodsLogo}"
												class="pull-left img-responsive" alt="">
										</a> <span class="pull-left cart-pDetail">${comboVOs.combo.comboName}</span></li>
										<li><span>${comboVOs.combo.comboPrice}</span></li>
										<li class="cart-num"><span class="chooseNum">${comboVOs.shoppingCart.goodsCount}</span>
										</li>
										<li><span>${comboVOs.shoppingCart.totalPrice}</span></li>
										<div class="clearfix"></div>
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
						<p>
							总价:<span class="t-money">${totalMoney}</span>
						</p>

					</div>

					<form id="inputForm" method="post"
						action="${basePath }/front/orderrecord/add.action">

						<input type="hidden" id="paymethod" name="paymethod" value="0">
						<input type="hidden" id="receiptAddressId" name="receiptAddressId">
						<input type="hidden" id="totalMoney" name="totalMoney"
							value="${totalMoney}"> <input type="hidden"
							value="${cartids}" name="cartids"> <input type="submit"
							class="cartSubmitBtn" value="提交订单" />

					</form>

				</li>
			</ul>
		</div>
	</div>

</div>

<!---->
<%@include file="footer.jsp"%>
</body>
</html>