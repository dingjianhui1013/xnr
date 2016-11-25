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
		window.location.href="<%=basePath%>/frontsetDefault.action?setDefaultId="+id+"&receiptAddress.frontUserId=${user.id}";
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
				url:"<%=basePath%>/frontmodifyAddress.action",
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
                    <li id="address"><a href="#">地址管理</a></li> 
                    <li><a href="#">密码修改</a></li> 
                </ul>
		 	</div>
			<div class="p-contentBox pull-left">
                <div class="p-orderList editList" style="display:block">
                 	<h3>账号信息</h3>
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
					<h3>我的订单</h3>
                    <div class="orderListBox">
                    	<div class="orderTitCol">
                    		<ul>
                    			<li class="ordercol-d">订单详情</li>
                    			<li>收货人</li>
                    			<li>金额</li>
                    			<li>状态</li>
                    			<li>操作</li>
                    		</ul>
                    	</div>
                    </div>
                     <div class="orderList">
                    	<div class="orderTit">
	                          订单号：<span>2016010223</span>
                    		<span class="orderTime">
	                          	2016-10-12
	                        </span>
                    	</div>
                    	<div class="orderTitCol">
                    		<ul>
                    			<li class="ordercol-d">
                    				<a href="#"><img src="images/products/sc-img1.jpg"></a>
	  	                          	 <div class="orderCon">
	  			                          <h3><a href="#">有机怀山堂铁棍山药（垆土） 1.5kg/箱 长度为38cm 左右</a></h3>
	  		                          </div>
                    			</li>
                    			<li>
                    				<span>张三</span>
                    			</li>
                    			<li>
			                          <span class="orderMoney">总额：<em>￥200</em></span>
			                          <p>微信支付</p>
			                    </li>
                    			<li><span>已完成 </span></li>
                    			<li><span><a href="#">再次购买</a></span></li>
                    		</ul>
                    	</div>
                    </div>
                    <div class="orderList">
                    	<div class="orderTit">
	                          订单号：<span>2016010223</span>
                    		<span class="orderTime">
	                          	2016-10-12
	                        </span>
                    	</div>
                    	<div class="orderTitCol">
                    		<ul>
                    			<li class="ordercol-d">
                    				<a href="#"><img src="images/products/sc-img1.jpg"></a>
	  	                          	 <div class="orderCon">
	  			                          <h3><a href="#">有机怀山堂铁棍山药（垆土） 1.5kg/箱 长度为38cm 左右</a></h3>
	  		                          </div>
                    			</li>
                    			<li>
                    				<span>张三</span>
                    			</li>
                    			<li>
			                          <span class="orderMoney">总额：<em>￥200</em></span>
			                          <p>微信支付</p>
			                    </li>
                    			<li><span>已完成 </span></li>
                    			<li><span><a href="#">再次购买</a></span></li>
                    		</ul>
                    	</div>
                    </div>
                    <div class="orderList">
                    	<div class="orderTit">
	                          订单号：<span>2016010223</span>
                    		<span class="orderTime">
	                          	2016-10-12
	                        </span>
                    	</div>
                    	<div class="orderTitCol">
                    		<ul>
                    			<li class="ordercol-d">
                    				<a href="#"><img src="images/products/sc-img1.jpg"></a>
	  	                          	 <div class="orderCon">
	  			                          <h3><a href="#">有机怀山堂铁棍山药（垆土） 1.5kg/箱 长度为38cm 左右</a></h3>
	  		                          </div>
                    			</li>
                    			<li>
                    				<span>张三</span>
                    			</li>
                    			<li>
			                          <span class="orderMoney">总额：<em>￥200</em></span>
			                          <p>微信支付</p>
			                    </li>
                    			<li><span>已完成 </span></li>
                    			<li><span><a href="#">再次购买</a></span></li>
                    		</ul>
                    	</div>
                    </div>
                <!--分页-->
                <nav class="text-center">
				      <ul class="pagination">
				        <li class="disabled"><a href="#">«</a></li>
				        <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
				        <li><a href="#">2</a></li>
				        <li><a href="#">3</a></li>
				        <li><a href="#">4</a></li>
				        <li><a href="#">5</a></li>
				        <li><a href="#">»</a></li>
				     </ul>
   				</nav>
				<!--分页end-->
                </div>
                <div class="p-orderList editList">
					<h3>地址管理</h3>
                	<a href="javascript:;" class="btn btn-default add-address" id="addAddressBtn">新增收货地址</a>
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
								 	 <c:if test="${address.type==0|| address.type==null}"><button type="button" class="btn btn-default" onclick="setDefault('${address.id}')">设为默认</button></c:if>
								  	 <button type="button" class="btn btn-default modifyAddress" onclick="modifyAddress('${address.id}')">修改</button>
								  	 <a  class="btn btn-default" href="<%=basePath%>/frontdeleteAddress.action?receiptAddress.id=${address.id}">删除</a>
								  </div>
								</form>
	                		</div>
                	</c:forEach>
                	</div>
                </div>
                 <div class="p-orderList editList">
                 		<h3>密码修改</h3>
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
		      <form role="form" id="mAddress" action="<%=basePath%>/frontsaveModifyAddress.action">
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
					    	<option value="北京市">北京市</option>
					    	<option value="河北省">河北省</option>
					    	<option value="山东省">山东省</option>
					    </select>
					    <select class="form-control citySel" id="modifyCity" name="receiptAddress.city">
					    	<option value="北京市">北京市</option>
					    	<option value="三河市">三河市</option>
					    	<option value="济南市">济南市</option>
					    </select>
					    <select class="form-control countrySel" id="modifyCounty" name="receiptAddress.county">
					    	<option value="燕郊">燕郊</option>
					    	<option value="河北省">河北省</option>
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
		      <form role="form" id="addAddress" action="<%=basePath%>/frontaddAddress.action">
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
					    	<option value="北京市">北京市</option>
					    	<option value="河北省">河北省</option>
					    	<option value="山东省">山东省</option>
					    </select>
					    <select class="form-control citySel" name="receiptAddress.city">
					    	<option value="北京市">北京市</option>
					    	<option value="三河市">三河市</option>
					    	<option value="济南市">济南市</option>
					    </select>
					    <select class="form-control countrySel" name="receiptAddress.county">
					    	<option value="燕郊">燕郊</option>
					    	<option value="河北省">河北省</option>
					    	<option value="济南县区">济南县区</option>
					    </select>
				    </div>
				    <input type="text" class="form-control detailAddress" id="addDetailAddress" name ="receiptAddress.detailAddress" placeholder="请输入详情地址" />
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