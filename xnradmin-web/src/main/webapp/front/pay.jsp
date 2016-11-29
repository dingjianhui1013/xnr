<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="checkout">	 
	 <div class="container">	
		 <ol class="breadcrumb">
		  <li><a href="index.html">首页</a></li>
		  <li class="active">支付</li>
		 </ol>
		 <div class="product-price1">
			 <div class="check-out">			
				 <div class="order-checkBox">
					 <h3>填写并核对订单信息</h3>
					 <div class="stepInfoBox">
						 <div class="receiptBox ">
							<div class="defalutAddBox">
								<p class="addr-name"><b>收货人信息：</b><a href="#">[修改]</a></p>
								<p class="addr-address"><span>张某某</span><span>1360103343</span><span>北京市昌平区回龙观</span></p>
							</div>
							 <div class="infoItem editAddres">
							 	<form class="form-horizontal">
							 		<div class="radio addListItem cur">
								        <label>
								          <input type="radio" name="optionsRadios" id="optionsRadios1" value="option2">
								          <span>张某某</span>
								          <span>山东省日照市</span>
								          <span>13502034321</span>
								          <div class="operatorBox">
									          <a href="#" class="editAddBtn">编辑</a>
									          <a href="#" class="delAddBtn">删除</a>
								          </div>
								        </label>
										</div>
										<div class="radio addListItem">
								        <label>
								          <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
								          <span>王某某</span>
								          <span>山东省日照市</span>
								          <span>13502034321</span>
								          <div class="operatorBox">
									          <a href="#" class="editAddBtn">编辑</a>
									          <a href="#" class="delAddBtn">删除</a>
								          </div>
								        </label>
										</div>
										<div class="radio addListItem">
								        <label>
								          <input type="radio" name="optionsRadios" id="optionsRadios3" value="option2">
								          <span>使用新地址</span>
								        </label>
										</div>
										<div class="add-addItem">
											<form class="form-horizontal" role="form">
												  <div class="form-group">
												    <label class="col-sm-2 control-label"><em>*</em>收货人：</label>
												    <div class="col-sm-6">
											          <input type="text" class="form-control" id="" placeholder="请输入收货人">
											        </div>
												  </div>
												  <div class="form-group">
												    <label class="col-sm-2 control-label">所在地区：</label>
												    <div class="col-sm-6">
											          <select class="form-control provinceSel">
											          	<option value="">山东省</option>
											          </select>
											          <select class="form-control citySel">
											          	<option value="">日照市</option>
											          </select>
											          <select class="form-control countrySel">
											          	<option value=""></option>
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
							 	<a href="#" class="active">在线支付</a>
							 	<a href="#">货到付款</a>
							 	<a href="#">微信支付</a>
							 	<a href="#">支付宝支付</a>
							 </div>
						</div>
						<div class="receiptBox">
							<h3>商品信息：</h3>
							 <div class="in-check" >
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
										<ul class="cart-header"id="test">
										<li class="checkCol"><input type="checkbox" /></li>
										<li class="productCol">
											<a href="productDetail.html" >
												<img src="images/products/sc-img3.jpg" class="pull-left img-responsive" alt=""></a>
												<span class="pull-left cart-pDetail">有机怀山堂铁棍山药（垆土） 1.5kg/箱 长度为38cm 左右</span>
										</li>
										<li><span>60.00</span></li>
										<li class="cart-num">
											<div class="addNum">						
												<span><input type="text" class="item_quantity" value="1" /></span>
												<span>
													<a href="#" class="plusNum">+</a>
													<a href="#" class="minus-Num">-</a>
												</span>
											</div>
										</li>
										<li><span>60.00</span></li>
										<li><span><a href="#" class="delBtn1">删除</a></span></li>
										<div class="clearfix"> </div>
										</ul>
										<ul class="cart-header">
										<li class="checkCol"><input type="checkbox" /></li>
										<li class="productCol">
											<a href="productDetail.html" >
												<img src="images/products/sc-img2.jpg" class="pull-left img-responsive" alt=""></a><span class="pull-left cart-pDetail">有机怀山堂铁棍山药（垆土） 1.5kg/箱 长度为38cm 左右</span>
										</li>
										<li><span>60.00</span></li>
										<li class="cart-num">
											<div class="addNum">						
												<span><input type="text" class="item_quantity" value="1" /></span>
												<span>
													<a href="#" class="plusNum">+</a>
													<a href="#" class="minus-Num">-</a>
												</span>
											</div>
										</li>
										<li><span>60.00</span></li>
										<li><span><a href="#" class="delBtn2">删除</a></span></li>
										<div class="clearfix"> </div>
										</ul>
										<ul class="cart-header">
										<li class="checkCol"><input type="checkbox" /></li>
										<li class="productCol">
											<a href="productDetail.html" >
												<img src="images/products/sc-img1.jpg" class="pull-left img-responsive" alt=""></a><span class="pull-left cart-pDetail">有机怀山堂铁棍山药（垆土） 1.5kg/箱 长度为38cm 左右</span>
										</li>
										<li><span>60.00</span></li>
										<li class="cart-num">	
											<div class="addNum">						
												<span><input type="text" class="item_quantity" value="1" /></span>
												<span>
													<a href="#" class="plusNum">+</a>
													<a href="#" class="minus-Num">-</a>
												</span>
											</div>
										</li>
										<li><span>60.00</span></li>
										<li><span><a href="#" class="delBtn3">删除</a></span></li>
										<div class="clearfix"> </div>
										</ul>
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
			 		<li class="pull-left checkCol"><input type="checkbox" />全选</li>
			 		<li class="pull-right totalCol">
			 			<div class=" totalMoney">
				 			<p>总价:<span class="t-money">￥120.00</span></p>
				 			<p>已节省:-￥120.00</p>
			 			</div>
			 			<a href="#" class=" cartSubmitBtn">提交订单</a>
			 		</li>
			 	</ul>
		 	</div>
		 </div>

	 </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>