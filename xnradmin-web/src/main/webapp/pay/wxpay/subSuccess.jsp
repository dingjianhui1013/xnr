<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>支付页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <%@include file="../../front/header.jsp" %>
 <script type="application/x-javascript"> 
	addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
 </script> 
<script type="text/javascript">
 $(function() {
 setInterval("checkPay()", 20000);//每20秒查询一次
 });
 function checkPay() {
		$
				.ajax({
					url : "${basePath}/front/weixinPay/queryOrder.action?_" + new Date(),
					type : "POST",
					data : {
						"outTradeNo" : "${outTradeNo}"
					},
					dataType : "JSON",
					success : function(d) {
						if (d.status == "1") {
							
							window.location.href = "${basePath}/front/paySuccess.jsp";
							
						}
					}
				})
	}
 </script>
</head>
<body> 
<div class="checkout">	 
	 <div class="container">	
		 <ol class="breadcrumb">
		  <li><a href="index.html">首页</a></li>
		  <li class="active">支付</li>
		 </ol>	
		 <div class="payWrapBox">
			 <div class="orderTips">
			 	<div class="pull-left">
				 	 <p><span>您的订单已提交成功</span><span class="orderNumer">订单号:${outTradeNo } </span></p>
					 <p>请您及时付款，以便订单尽快处理！</p>
				 </div>
				 <div class="pull-right payMoneyBox">
				 	应付金额：<span class="payMoneyNum">${totalMoney }元</span>
				 </div>
			 </div>	
			 <div class="payCodeBox">
			 		<h3>微信支付</h3>
			 		<div class="codeImgWrap">
				 		<div class="pull-left payImgBox">
				 			<img src="../../images/getWxImage.jpg" />
				 			<p>请使用微信扫一扫</p>
				 			<p>扫描二维码支付</p>
				 		</div>
				 		<div class="pull-left phoneImgBox">
				 			<img src="../../images/phone-bg.png" />
				 		</div>
			 		</div>
			 </div>
		 </div>		
	</div>
</div>
</div>
<%@include file="../../front/footer.jsp"%>	 
</body>
</html>