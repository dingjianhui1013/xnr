<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
	$(function(){
		$('#editMsg').click(function(){
			$('#myModal').modal({
		   backdrop: "static"
		})
		})
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
					$("#check_phoneError").hide();
					$("#check_phone").hide();
					$("#check_phone").html("");
					phoneFlag = true;
				} else {
					$("#check_phoneError").show();
					$("#check_phone").show();
					$("#check_phone").html(" <span style=\"color: red;font-size: 10px\">手机号码已被注册</span>");
				}
			}
		});
	}
</script>
</head>
<body> 
<!--header-->	
<%@include file="header.jsp" %>
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
                    <li><a href="#">地址管理</a></li> 
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
                		<div class="addressList">
							<form class="form-horizontal" role="form">
							  <div class="form-group">
							    <label class="col-sm-2 control-label">收货人：</label>
							    <p class="form-control-static">张某某</p>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">地址：</label>
							    <p class="form-control-static">山东省日照某某小区</p>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">电话：</label>
							    <p class="form-control-static">13601023443</p>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">电子邮箱：</label>
							    <p class="form-control-static">13601023443</p>
							  </div>
							  <div class="editBox">
							  	 <button type="submit" class="btn btn-default">修改</button>
							  	 <button type="submit" class="btn btn-default">设为默认</button>
							  </div>
							</form>
                		</div>
                		<div class="addressList">
							<form class="form-horizontal" role="form">
							  <div class="form-group">
							    <label class="col-sm-2 control-label">收货人：</label>
							    <p class="form-control-static">张某某</p>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">地址：</label>
							    <p class="form-control-static">山东省日照某某小区</p>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">电话：</label>
							    <p class="form-control-static">13601023443</p>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">电子邮箱：</label>
							    <p class="form-control-static">13601023443</p>
							  </div>
							  <div class="editBox">
							  	 <button type="submit" class="btn btn-default" id="editMsg">修改</button>
							  	 <button type="submit" class="btn btn-default">设为默认</button>
							  </div>
							</form>
                		</div>
                	</div>
                </div>
                 <div class="p-orderList editList">
                 		<h3>密码修改</h3>
                		<form class="form-horizontal">
                                  <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">原始密码：</label>
                                    <div class="col-sm-10">
                                      <input type="password" id="yuanshimima" name="yuanshimima" class="form-control" id="" value="">
                                      <span style="color: red;font-size: 10px">手机号码已被注册</span>
                                    </div>
                                  </div>
                                  <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">新密码：</label>
                                    <div class="col-sm-10">
                                      <input type="password" class="form-control" id="" value="" onblur="yzPassword()">
                                    </div>
                                  </div>
                                  <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">确认密码：</label>
                                    <div class="col-sm-10">
                                      <input type="password" class="form-control" id="" value="">
                                    </div>
                                  </div>
                                  <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                      <button type="submit" class="btn btn-default">保存修改</button>
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
		      <form role="form">
				  <div class="form-group">
				    <label for="">收货人：</label>
				    <input type="text" class="form-control" id="" placeholder="张三">
				  </div>
				  <div class="form-group">
				    <label for="">地址：</label>
				    <input type="text" class="form-control" id="" placeholder="山东省日照某某小区">
				  </div>
				  <div class="form-group">
				    <label for="">电话号码：</label>
				    <input type="text" class="form-control" id="" placeholder="136030454">
				  </div>
				</form>
      </div>
      <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary">保存</button>
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
		      <form role="form">
				  <div class="form-group">
				    <label for="">收货人：</label>
				    <input type="text" class="form-control" id="" placeholder="请输入收货人信息">
				  </div>
				  <div class="form-group">
				    <label for="">地址：</label>
				    <div class="selAddressBox">
					    <select class="form-control provinceSel" class="">
					    	<option>北京市</option>
					    	<option>河北省</option>
					    	<option>山东省</option>
					    </select>
					    <select class="form-control citySel" >
					    	<option>北京市</option>
					    	<option>三河市</option>
					    	<option>济南市</option>
					    </select>
					    <select class="form-control countrySel">
					    	<option>燕郊</option>
					    	<option>河北省</option>
					    	<option>济南县区</option>
					    </select>
				    </div>
				    <input type="text" class="form-control detailAddress" placeholder="请输入详情地址" />
				  </div>
				  <div class="form-group">
				    <label for="">电话号码：</label>
				    <input type="text" class="form-control" id="" placeholder="请输入电话号码">
				  </div>
				  <div class="form-group">
				    <label for="">电子邮箱：</label>
				    <input type="email" class="form-control" id="" placeholder="请输入电子邮箱：">
				  </div>
				</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>