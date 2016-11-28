<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>注册页</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	request.setAttribute("basePath",basePath);
%>
<link href="${basePath }css/front/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="${basePath }css/front/style.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="${basePath }css/front/flexslider.css" type="text/css" media="screen" />	
<!-- start menu -->
<link href="${basePath }css/front/memenu.css" rel="stylesheet" type="text/css" media="all" />
<script src="${basePath }js/front/jquery-1.11.3.min.js"></script>
<script src="${basePath }js/front/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath }js/front/common.js"></script>
<script type="application/x-javascript"> 
	addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<script src="${basePath }js/front/simpleCart.min.js"> </script>
<script type="text/javascript" src="${basePath }js/front/memenu.js"></script>
<script>
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

	// 验证email
	var emailFlag = false;
	function checkEmail() {
		var email = $("#email").val();
		email = $.trim(email);
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (email.length != 0) {
			if (filter.test(email)) {
				$("#check_emailError").hide();
				$("#check_email").hide();
				$("#check_email").html("");
				emailFlag = true;
			} else {
				$("#check_emailError").show();
				$("#check_email").show();
				$("#check_email").html(" <span style=\"color: red;font-size: 10px\">请输入正确的邮箱地址</span>");
			}
		} else {
			$("#check_emailError").show();
			$("#check_email").show();
			$("#check_email").html("<span style=\"color: red;font-size: 10px\">请输入邮箱</span>");
		}
	}

	// 验证phone
	var phoneFlag = false;
	function checkPhone() {
		var phone = $("#phone").val();
		phone = $.trim(phone);
		var filter = /^(1+\d{10})$/;
		if (phone.length != 0) {
			if (filter.test(phone)) {
				$.ajax({
					type : 'POST',
					url : '${basePath}front/validatePhone.action?_' + new Date(),
					data : {
						"phone" : phone
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
			} else {
				$("#check_phoneError").show();
				$("#check_phone").show();
				$("#check_phone").html(" <span style=\"color: red;font-size: 10px\">请输入正确的手机号码</span>");
			}
		} else {
			$("#check_phoneError").show();
			$("#check_phone").show();
			$("#check_phone").html(" <span style=\"color: red;font-size: 10px\">请输入手机号码</span>");
		}
	}
	
	function checkCode() {
		var phone = $("#phone").val();
		phone = $.trim(phone);
		var valideCode = $("#valideCode").val();
		if (valideCode == code) {
			$.ajax({
				url : "${ctx}/workUser/validatePin",
				type : "POST",
				data : {
					"phone" : phone,
					"smsPin" : valideCode
				},
				dataType : "JSON",
				success : function(data) {
					if (data.status == 1) {
						$("#check_codeError").hide();
						$("#check_code").hide();
						$("#check_code").html("");
						checkcode = true;
					} else {
						$("#check_codeError").show();
						$("#check_code").show();
						$("#check_code").html(" <span style=\"color: red;font-size: 10px\">请输入正确的验证码</span>");
						$("#valideCode").val("");
					}
				}
			});
		} else {
			$("#check_codeError").show();
			$("#check_code").show();
			$("#check_code").html("验证码错误");
			$("#valideCode").val("");
		}
	}
	
	//注册
	function register() {
		if (phoneFlag && checkPassword() && checkConfirmPassword()
				&& emailFlag ) {//&&checkcode
			$("#submitForm").submit();
		}else{
			checkEmail();
			checkPhone();
			checkCode();
		}
	}
	
</script>
</head>
<body>
	<!--header-->
	<div class="top_bg">
	<div class="container">
		<div class="header_top-sec">
			<div class="top_right">
				<ul>
					<li><a href="#">欢迎光临康源公社！</a></li>|
					<li><a href="#contact">联系我们</a></li>
				</ul>
			</div>
			<div class="top_left">
				<!--登录后显示-->
				<!-- <ul>
					<li class="top_link">用户名:<a href="mailto:info@example.com">myTest</a></li>|
					<li class="top_link"><a href="login.html">订单中心</a></li>					
				</ul> -->
				<!--登录后显示-->
				<ul>
					<li><a href="login.jsp">登录</a></li>
					<li><a href="register.jsp">注册</a></li>
				</ul>

			</div>
				<div class="clearfix"> </div>
		</div>
	</div>
</div>
<div class="logoWrap">	
	<div class="container">	
		<div class="logo">
			<a href="index.action">
			<h1><img src="${basePath }images/front/login_logo.png" /></h1>
			</a>
		</div>
		<div class="pull-left welcomeBox">康源公社欢迎您注册</div>
	</div>
</div>
<div class="guiderBox">
	<div class="container">
		<ul class="memenu skyblue pull-left">
			<li class="active"><a href="index.action">首页</a></li>
			<c:forEach items="${ allBusinessCategorys}" var="firstBusinessCategory">
				<c:forEach items="${firstBusinessCategory }" var="first">
				<li class="grid"><a href="#"> ${ first.key.categoryName}</a>
				<div class="mepanel">
					<div class="row">
						<c:forEach items="${first.value }" var="secondBusinessCategory">
						<c:forEach items="${secondBusinessCategory }" var="second">
							<div class="col1 me-one">
								<h4> ${ second.key.categoryName} </h4>
								<ul>
									<c:forEach items="${second.value }" var="threeBusinessCategory">
									<li><a href="product.html">${threeBusinessCategory.categoryName }</a></li>
									</c:forEach>
								</ul>
							</div>
						</c:forEach>
						</c:forEach>
					</div>
				</div> 
				</c:forEach>
			</li>
			</c:forEach>
			<li class="grid"><a href="<%=basePath%>/front/contact.action">关于我们</a></li>
		</ul>
	 </div>
</div>
	<!---->
	<div class="container">
		<div class="registration">
			<div class="container loginBox">
				<form class="form-horizontal" action="${basePath}front/register.action" method="post" id="submitForm">
					<h4 class="loginBoxTit">康源公社注册</h4>
					<div class="form-group">
						<label for="userNameInput" class="col-sm-2 control-label"><em>*</em>手机号:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="phone" name="frontUser.phone"
								onblur="checkPhone()" placeholder="请输入手机号码">
							<p class="errorTips" id="check_phoneError" style="display: none">
								<span class="glyphicon glyphicon-remove-sign errorIcon"></span>
								<span id="check_phone" style="display: none"></span>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label for="passwordInput" class="col-sm-2 control-label">密码:</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="password"
								name="frontUser.password" onblur="checkPassword()" placeholder="请输入密码">
							<p class="errorTips" id="check_passwordError"
								style="display: none">
								<span class="glyphicon glyphicon-remove-sign errorIcon"></span>
								<span id="check_password" style="display: none"></span>
							</p>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">确认密码:</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="confirmPassword"
								onblur="checkConfirmPassword()" placeholder="请输入确认密码">
							<p class="errorTips" id="check_confirmPasswordError"
								style="display: none">
								<span class="glyphicon glyphicon-remove-sign errorIcon"></span>
								<span id="check_confirmPassword" style="display: none"></span>
							</p>
						</div>
					</div>
					<div class="form-group">
						<label for="emailInput" class="col-sm-2 control-label"><em>*</em>邮箱:</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" id="email" name="frontUser.email"
								onblur="checkEmail()" placeholder="请输入邮箱">
							<p class="errorTips" id="check_emailError" style="display: none">
								<span class="glyphicon glyphicon-remove-sign errorIcon"></span>
								<span id="check_email" style="display: none"></span>
							</p>
						</div>
					</div>
					<!-- <div class="form-group">
						<label for="register-email" class="col-sm-2 control-label">验证码:</label>
						<div class="col-sm-10 ">
							<input type="text" id="valideCode" onblur="checkCode()"
								class="pull-left form-control yzmInput" placeholder="验证码"> <input
								type="button" value="获取验证码" id="btnSendCode"
								onclick="getValideCode()" class="btn btn-default pull-right" />
							<p class="errorTips" id="check_codeError" style="display: none">
								<span class="glyphicon glyphicon-remove-sign errorIcon"></span>
								<span id="check_code" style="display: none"></span>
							</p>
						</div>
					</div> -->
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10 loginBtn">
							<input type="button" class="btn btn-success" onclick="register()"
								value="立即注册"> <a href="login.jsp" class="regLinkBox">已有账号，去登录</a>
						</div>
					</div>
				</form>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!---->
	<%@include file="footer.jsp"%>
</body>
</html>