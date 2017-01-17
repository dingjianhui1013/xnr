<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="header.jsp" %>
<script type="text/javascript">
	$(function(){
// 		$('#editMsg').click(function(){
// 			$('#myModal').modal({
// 		   backdrop: "static"
// 		})
// 		})
		$('#addAddressBtn').click(function(){
			$('#newAddressBox').modal({
		   backdrop: "static"
		})
		})
	
	})
	// 验证原始密码
	var phoneFlag = false;
	function yzPassword() {
		var yuanshimima = $("#yuanshimima").val();
		$.ajax({
			type : 'POST',
			url : '${basePath}front/validateYuanshimima.action?_' + new Date(),
			data : {
				"yuanshimima" : yuanshimima
			},
			dataType : 'JSON',
			success : function(data) {
				//alert(data.status);
				if (data.status == 1) {
					$("#check_yz").hide();
					//$("#check_yz").html("");
					phoneFlag = true;
				} else {
					$("#check_yz").show();
					//$("#check_yz").html(" <span style=\"color: red;font-size: 10px\">密码错误</span>");
				}
			}
		});
	}
	//验证密码
	function checkPassword() {
		var password = $("#password").val();
		if (password.length<6||password.length>20) {
			$("#check_passwordError").show();
			$("#check_password").show();
			$("#check_password").html(" <span style=\"color: red;font-size: 10px\">请输入6-20位的密码</span>");
			return false;
		} else {
			$("#check_passwordError").hide();
			$("#check_password").hide();
			$("#check_password").html("");
			return true;
		}
	}

	//验证确认密码
	function checkConfirmPassword() {
		var password = $("#password").val();
		var confirmPassword = $("#confirmPassword").val();
		if (password.length == 0) {
			$("#check_passwordError").show();
			$("#check_password").show();
			$("#check_password").html(" <span style=\"color: red;font-size: 10px\">请输入6-20位的密码</span>");
			return false;
		} else {
			if (confirmPassword.length == 0) {
				$("#check_confirmPasswordError").show();
				$("#check_confirmPassword").show();
				$("#check_confirmPassword").html(" <span style=\"color: red;font-size: 10px\">请输入确认密码</span>");
				return false;
			} else if (password != confirmPassword) {
				$("#check_confirmPasswordError").show();
				$("#check_confirmPassword").show();
				$("#check_confirmPassword").html(" <span style=\"color: red;font-size: 10px\">确认密码和密码不一致</span>");
				$("#confirmPassword").val("");
				return false;
			} else {
				$("#check_confirmPasswordError").hide();
				$("#check_confirmPassword").hide();
				$("#check_confirmPassword").html("");
				return true;
			}
		}
	}
	
	function save() {
		if (phoneFlag && checkPassword() && checkConfirmPassword()) {//&&checkcode
			$("#submitForm").submit();
		}else{
			yzPassword();
		}
	}
	function sub()
	{
		var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if($("#addReceiptName").val()=="")
			{
				$("#addReceiptNameError").html("请输入收货人")
			}else if($("#addDetailAddress").val()=="")
				{
					$("#addDeailError").html("请输入详细 地址");
					$("#addReceiptNameError").html("")
				}else if($("#addTel").val()=="")
					{
						$("#addTelError").html("请输入手机号")
						$("#addDeailError").html("");
					}else if($("#addEmail").val()=="")
						{
						$("#addEmailError").html("请输入邮箱");
						$("#addTelError").html("")
						}else if(!$("#addEmail").val().match(reg))
							{
								$("#addEmailError").html("请输入正确的邮箱");
								$("#addTelError").html("")
								emailV.val("");
							}else
								{
									$("#addEmailError").html("");
									$("#addAddress").submit();
								}
	}
	function setDefault(id)
	{
		window.location.href="<%=basePath%>/front/setDefault.action?setDefaultId="+id+"&receiptAddress.frontUserId=${user.id}";
	}
	function verificationEmail()
	{
		var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		var emailV = $("#addEmail").val();
		if((!emailV.match(reg)))
			{
				$("#addEmailError").html("请输入正确的邮箱");
				emailV.val("");
			}
	
	}
	function modifyAddress(id){
		$(".modifyAddress").click(function(){
			$.ajax({
				url:"<%=basePath%>/front/modifyAddress.action",
				type:'POST',
				data:{"receiptAddress.id":id,"_":new Date().getTime()},
				dataType:'JSON',
				success:function(data){
					$("#addressId").val(data.receiptAddress.id);
					$("#modifyReceiptName").val(data.receiptAddress.receiptName);
					$("#modifyTel").val(data.receiptAddress.tel);
					$("#modifyDetailAddress").val(data.receiptAddress.detailedAddress);
					$("#modifyReceiptName").val(data.receiptAddress.receiptName);
					$("#modifyCity option[value="+data.receiptAddress.city+"]").attr("selected",true);
					$("#modifyProvince option[value="+data.receiptAddress.province+"]").attr("selected",true);
					$("#modifyCounty option[value="+data.receiptAddress.county+"]").attr("selected",true);
					$("#myModal").modal({
						backdrop: "static"
					})
				}
			});
		})
	}
	function modify()
	{
		var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if($("#modifyReceiptName").val()=="")
			{
				$("#mReceiptNameError").html("请输入收货人")
			}else if($("#modifyDetailAddress").val()=="")
				{
					$("#mDeailError").html("请输入详细 地址");
					$("#mReceiptNameError").html("")
				}else if($("#modifyTel").val()=="")
					{
						$("#mTelError").html("请输入手机号")
						$("#mDeailError").html("");
					}else
						{
							$("#mEmailError").html("");
							$("#mAddress").submit();
						}
	}
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
					 		$("#simpleCart_total").html((Number($("#simpleCart_total").html())+msg.totalMoney*Number(msg.totalCount)).toFixed(1));
					 		$("#simpleCart_number").html((Number($("#simpleCart_number").html())+Number(msg.totalCount)));
							layer.msg("加入成功");
						}
					}
				});
		}
// 		else
// 			{
// 				//layer.msg("请先登录");
<%-- 				//setTimeout("window.location.href='<%=basePath%>/front/login.jsp'",1000); --%>
// 			var cart = getCartCookie();
			
// 			var item=new Object();
// 			item.cookieId = getUuid();
// 			item.goodsId = id;
// 			item.goodsCount = goodsNumber;
// 			item.price = Number($("#price"+id).val());
// 			cart.push(item);
// 			$.cookie('cart', JSON.stringify(cart), { expires: 7, path: '/' }); 
// 			layer.msg("加入成功");
// 			}
		
	}
</script>
</head>
<body> 
<!--header-->	

<!--head//-->
<div class="single-sec">
	 <div class="container">
		 <ol class="breadcrumb">
				  <li><a href="index.html">首页</a></li>
				  <li class="">个人中心</li>
		 </ol>
		 <div class="personalBox">
		 	<div class="pSliderBar pull-left">
		 		<div class="userInfoBox">
                  <span class="personalImg"><img src="${basePath }images/front/head.jpg" ></span>
                  <div class="perInfoBox">
                   <p><strong>账号：</strong>${user.userName }</p>
                  </div>
                </div>
                <ul class="pSlideNavUl">
                	<li class="active"><a href="#">账号信息</a></li> 
                	<li id="myorder"><a href="#">我的订单</a></li>
<%--                     <li id="myorder"><a href="${basePath}/front/frontUserInfo.action?clientUserId=${user.id}">我的订单</a></li>  --%>
                    <li id="address"><a href="#">地址管理</a></li> 
                    <li><a href="#">密码修改</a></li> 
                </ul>
		 	</div>
			<div class="p-contentBox pull-left">
                <div class="p-orderList editList" style="display:block">
                 	<h3 class="titBox">账号信息</h3>
                	<div class="accountBox">
							<form action="saveForm.action" class="form-horizontal" role="form">
								<input type="hidden" name="user.id" value="${user.id }">
							  <div class="form-group">
							    <label class="col-sm-2 control-label">账号：</label>
							    <div class="r-userNameBox">
								    <p class="r-editName">
								    	<span class="d-userName" id="d-userName">
											<span class="pull-left">${user.userName }</span>
								    		<a href="#" class="form-control-static pull-left editUserName" id="editUserName">[修改]</a>
								    	</span>
								    	<span class="editUserInput">
								    		<input name="user.userName" type="text" value="${user.userName }" class="pull-left form-control"/>
								    		<input type="submit"  class="form-control-static pull-left saveUserName" value="[保存]"/>
								    	</span>
								    </p>
								 </div>
							    <p></p>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">手机号：</label>
							    <p class="form-control-static">${user.phone }</p>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">电子邮箱：</label>
							    <p class="form-control-static">${user.email }</p>
							  </div>
							</form>
                	</div>
                </div>
						<div class="p-orderList editList" id="p-orderList">
						<h3 class="titBox">我的订单</h3>
	                    <div class="orderListBox">
	                    	<div class="orderTitCol">
	                    		<ul>
	                    			<li class="ordercol-d">订单详情</li>
	                    			<li>收货人</li>
	                    			<li>金额</li>
	                    			<li>状态</li>
	                    			<li>派送状态</li>
	                    			<li>操作</li>
	                    		</ul>
	                    	</div>
	                    </div>
	                    <c:if test="${empty voList}">
	                    	<div class="searchTips">
					 			<p>暂时您还没有购买我们的商品</p>
					 			<p>建议您：</p>
				 				<a href="<%=basePath%>front/index.action">前去购买>></a>
			 				</div>
	                    </c:if>
	                    <c:if test="${not empty voList}">
	                    
	                    
	                    <c:forEach items="${voList}" var="loop">
	                     <div class="orderList">
	                    	<div class="orderTit">
		                                                              订单号：<span>${loop.businessOrderRecord.orderNo}</span> 
	                    		<span class="orderTime">
		                          	${loop.businessOrderRecord.createTime} 
		                        </span>
	                    	</div>
	                    	<div class="orderTitCol">
	                    		<ul>
	                    			<li class="ordercol-d">
		                    			<div class="pull-left  productListShow">
		                    			<c:forEach items="${loop.businessOrderRelationVO}" var="businessOrderRelations" begin="0" end="2"> 
			                    			<a href="#"><img src="${basePath}${businessOrderRelations.businessGoods.goodsLogo}"></a>
		                    			</c:forEach>
		                    			<c:if test="${loop.businessOrderRecord.totalCount>3}"><em>...</em></c:if>
		                    		 	</div>
		                    		 	<div class="pull-right  productListNum">
		                    		 		<a href="${basePath}front/orderDetail.action?businessOrderRecordId=${loop.businessOrderRecord.id}">共${loop.businessOrderRecord.totalCount}件></a>
		                    		 	</div>
	                    			</li>
	                    			<li>
	                    				<span>${loop.businessOrderRecord.userRealName}</span>
	                    			</li>
	                    			<li> 
				                          <span class="orderMoney">总额：<em>${loop.businessOrderRecord.totalPrice}</em></span>
				                          <p>${loop.businessOrderRecord.paymentProviderName}</p>
				                    </li>
				                    <c:if test="${loop.businessOrderRecord.paymentStatusName!='未支付'}">
				                   		<li><span>${loop.businessOrderRecord.paymentStatusName}</span></li>
				                    </c:if>
				                    <c:if test="${loop.businessOrderRecord.paymentStatusName=='未支付'}">
				                   		<li><span>${loop.businessOrderRecord.paymentStatusName}<br>
				                   		<c:if test="${loop.businessOrderRecord.paymentProviderName=='支付宝支付'}">
						                   	<a href="${basepath}/page/alipay/againPayment.action?orderId=${loop.businessOrderRecord.id}">前往支付</a></span></li>
				                   		</c:if>
				                   		<c:if test="${loop.businessOrderRecord.paymentProviderName=='微信支付'}">
						                   	<a href="${basePath}front/orderrecord/wxPayAgain.action?businessOrderRecodeId=${loop.businessOrderRecord.id}">前往支付</a></span></li>
				                   		</c:if>
				                    </c:if>
				                    <li><span>${loop.businessOrderRecord.deliveryStatusName}</span></li>
	                    			<li><span><a href="javascript:addToCart('${loop.businessOrderRecord.id }')">再次购买</a></span><a href="${basePath}front/orderDetail.action?businessOrderRecordId=${loop.businessOrderRecord.id}">查看详情</a></li>
	                    		</ul>
	                    	</div>
	                    </div>
	             </c:forEach>
                <!--分页-->
   				<nav class="text-center">
				      <ul class="pagination">
				      <c:choose>
						<c:when test="${pageNum==1}">
							 <li class="disabled"><a>«</a></li>
						</c:when>
						<c:otherwise>	
							<li><a href="${basePath}front/personalCenter.action?pageNum=1&flag=myorder">«</a></li>
						</c:otherwise>
					</c:choose>
						<c:forEach begin="1" end="${totalCount}" var="numpage">
							<c:choose>
								<c:when test="${numpage==pageNum}">
									<li class="active"><a href="${basePath}front/personalCenter.action?pageNum=${numpage}&flag=myorder">${numpage}</a></li>
								</c:when>
								<c:otherwise>	
									<li><a href="${basePath}front/personalCenter.action?pageNum=${numpage}&flag=myorder">${numpage}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${pageNum==totalCount}">
								 <li class="disabled"><a>»</a></li>
							</c:when>
							<c:otherwise>	
								<li><a href="${basePath}front/personalCenter.action?pageNum=${totalCount}&flag=myorder">»</a></li>
							</c:otherwise>
						</c:choose>
				    </ul>
   				</nav>
   				</c:if>
				<!--分页end-->
                </div>
                <div class="p-orderList editList">
					<h3 class="titBox">地址管理</h3>
                	<a href="javascript:;" class="btn btn-default add-address" id="addAddressBtn">新增收货地址</a>
                	<c:if test="${empty receiptAddressList }">
                		<div class="searchTips">
					 			<p>暂时您还没有添加收货地址</p>
					 			<p>建议您：</p>
				 				<p>点击上方“新增收货地址”进行添加地址</p>
			 			</div>
                	</c:if>
                	<c:if test="${not empty receiptAddressList}">
                	<div class="addressBox">
                	<c:forEach items="${receiptAddressList}" var = "address">
	                	 <div class="addressList">
								<form class="form-horizontal" role="form">
								  <div class="form-group">
								    <label class="col-sm-2 control-label">收货人：</label>
								    <p class="form-control-static">${address.receiptName}</p>
								  </div>
								  <div class="form-group">
								    <label class="col-sm-2 control-label">地址：</label>
								    <p class="form-control-static">${address.province} ${address.city } ${address.county} ${address.detailedAddress }</p>
								  </div>
								  <div class="form-group">
								    <label class="col-sm-2 control-label">电话：</label>
								    <p class="form-control-static">${address.tel}</p>
								  </div>
								  <div class="form-group">
								    <label class="col-sm-2 control-label">电子邮箱：</label>
								    <p class="form-control-static">${address.email}</p>
								  </div>
								  <div class="editBox">
								  	 <c:if test="${address.type==1 }"><button type="button" class="btn btn-default">默认地址</button></c:if>
								 	 <c:if test="${address.type==0 || address.type==null}"><button type="button" class="btn btn-default" onclick="setDefault('${address.id}')">设为默认</button></c:if>
								  	 <button type="button" class="btn btn-default modifyAddress" onclick="modifyAddress('${address.id}')">修改</button>
								  	 <a  class="btn btn-default" href="<%=basePath%>/front/deleteAddress.action?receiptAddress.id=${address.id}">删除</a>
								  </div>
								</form>
	                		</div>
                	</c:forEach>
                	</div>
                	<nav class="text-center">
				      <ul class="pagination">
				      <c:choose>
						<c:when test="${RPageNum==1}">
							 <li class="disabled"><a>«</a></li>
						</c:when>
						<c:otherwise>	
							<li><a href="${basePath}front/personalCenter.action?RPageNum=1&flag=address">«</a></li>
						</c:otherwise>
					</c:choose>
						<c:forEach begin="1" end="${RTotalPage}" var="numpage">
							<c:choose>
								<c:when test="${numpage==RPageNum}">
									<li class="active"><a href="${basePath}front/personalCenter.action?RPageNum=${numpage}&flag=address">${numpage}</a></li>
								</c:when>
								<c:otherwise>	
									<li><a href="${basePath}front/personalCenter.action?RPageNum=${numpage}&flag=address">${numpage}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${RPageNum==RTotalPage}">
								 <li class="disabled"><a>»</a></li>
							</c:when>
							<c:otherwise>	
								<li><a href="${basePath}front/personalCenter.action?RPageNum=${RTotalPage}&flag=address">»</a></li>
							</c:otherwise>
						</c:choose>
				    </ul>
   				</nav>
                </div>
                </c:if>
                 <div class="p-orderList editList">
                 		<h3 class="titBox">密码修改</h3>
                		<form class="form-horizontal" id="submitForm" action="savePassword.action">
                				<input type="hidden" name="user.id" value="${user.id }">
                                  <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">原始密码：</label>
                                    <div class="col-sm-10">
                                      <input onchange="yzPassword()" type="password" id="yuanshimima" name="yuanshimima" class="form-control" id="" value="">
                                      <span id="check_yz" style="color: red;font-size: 10px;display: none;">密码错误</span>
                                    </div>
                                  </div>
                                  <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">新密码：</label>
                                    <div class="col-sm-10">
                                      <input type="password" class="form-control" id="password" name="user.password" value="" onblur="checkPassword()">
                                      <p class="errorTips" id="check_passwordError"
											style="display: none">
											<span class="glyphicon glyphicon-remove-sign errorIcon"></span>
											<span id="check_password" style="display: none"></span>
										</p>
                                    </div>
                                  </div>
                                  <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">确认密码：</label>
                                    <div class="col-sm-10">
                                      <input type="password" class="form-control" id="confirmPassword" value="" onblur="checkConfirmPassword()">
                                      <p class="errorTips" id="check_confirmPasswordError"
										style="display: none">
										<span class="glyphicon glyphicon-remove-sign errorIcon"></span>
										<span id="check_confirmPassword" style="display: none"></span>
									</p>
                                    </div>
                                  </div>
                                  <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                      <button type="button" onclick="save()" class="btn btn-default">保存修改</button>
                                    </div>
                                  </div>
                        </form>
                 </div>
			</div>
		 </div>
	  </div>	 
</div>
<%@include file="footer.jsp" %>
<!--修改收货信息-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">修改地址信息</h4>
      </div>
      <div class="modal-body">
		      <form role="form" id="mAddress" action="<%=basePath%>/front/saveModifyAddress.action">
				  <div class="form-group">
				    <label for="">收货人：</label>
				    <input type="text" class="form-control" id="modifyReceiptName" name="receiptAddress.receiptName" placeholder="请输入收货人">
				    <input type="hidden" value="${user.id}" name="receiptAddress.frontUserId">
				    <input type="hidden"  name="receiptAddress.id"  id="addressId">
				  	<span style="color: red;font-size: 10px" id="mReceiptNameError"></span>
				  </div>
				  <div class="form-group">
				   <label for="">地址：</label>
				    <div class="selAddressBox">
					    <select class="form-control provinceSel" id="modifyProvince" class="" name="receiptAddress.province">
					    	<option value="山东省">山东省</option>
					    </select>
					    <select class="form-control citySel" id="modifyCity" name="receiptAddress.city">
					    	<option value="济南市">济南市</option>
					    </select>
					    <select class="form-control countrySel" id="modifyCounty" name="receiptAddress.county">
					    	<option value="济南县区">济南县区</option>
					    </select>
					 </div>
					    <input type="text" class="form-control detailAddress" id="modifyDetailAddress" name ="receiptAddress.detailedAddress" placeholder="请输入详情地址" />
				  		<span style="color: red;font-size: 10px" id="mDeailError"></span>
				  </div>
				  <div class="form-group">
				    <label for="">电话号码：</label>
				    <input type="text" class="form-control" id="modifyTel" maxlength="11" onkeyup='this.value=this.value.replace(/\D/gi,"")' name="receiptAddress.tel" placeholder="请输入手机号">
				 	<span style="color: red;font-size: 10px" id="mTel"></span>
				  </div>
				</form>
      </div>
      <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
         <button type="button" class="btn btn-primary" onclick="modify()">保存</button>
      </div>
    </div>
  </div>
</div>
<!--新增收货信息-->
<div class="modal fade" id="newAddressBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">新增收货地址</h4>
      </div>
      <div class="modal-body">
		      <form role="form" id="addAddress" action="<%=basePath%>/front/addAddress.action">
				  <div class="form-group">
				    <label for="">收货人：</label>
				    <input type="text" class="form-control" id="addReceiptName" name="receiptAddress.receiptName" placeholder="请输入收货人信息">
				    <input type="hidden" value="${user.id}" name="receiptAddress.frontUserId">
				  	<span style="color: red;font-size: 10px" id="addReceiptNameError"></span>
				  </div>
				  <div class="form-group">
				    <label for="">地址：</label>
				    <div class="selAddressBox">
					    <select class="form-control provinceSel" class="" name="receiptAddress.province">
					    	<option value="山东省">山东省</option>
					    </select>
					    <select class="form-control citySel" name="receiptAddress.city">
					    	<option value="济南市">济南市</option>
					    </select>
					    <select class="form-control countrySel" name="receiptAddress.county">
					    	<option value="济南县区">济南县区</option>
					    </select>
				    </div>
				    <input type="text" class="form-control detailAddress" id="addDetailAddress" name ="receiptAddress.detailedAddress" placeholder="请输入详情地址" />
				  	<span style="color: red;font-size: 10px" id="addDeailError"></span>
				  </div>
				  <div class="form-group">
				    <label for="">电话号码：</label>
				    <input type="text" class="form-control" id="addTel" maxlength="11" onkeyup='this.value=this.value.replace(/\D/gi,"")' name="receiptAddress.tel" placeholder="请输入电话号码">
				  	<span style="color: red;font-size: 10px" id="addTelError"></span>
				  </div>
				  <div class="form-group">
				    <label for="">电子邮箱：</label>
				    <input type="email" class="form-control" id="addEmail" onblur="verificationEmail()" name="receiptAddress.email" placeholder="请输入电子邮箱：">
				  	<span style="color: red;font-size: 10px"  id="addEmailError"></span>
				  </div>
				</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" onclick="sub()">保存</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>
