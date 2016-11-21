<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>注册页</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="header.jsp"%>
<script>
	//验证密码
	function checkPassword() {
		var password = $("#password").val();
		if (password.length<6||password.length>20) {
			$("#check_passwordError").show();
			$("#check_password").show();
			$("#check_password").html("请输入6-20位的密码");
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
			$("#check_password").html("请输入6-20位的密码");
			return false;
		} else {
			if (confirmPassword.length == 0) {
				$("#check_confirmPasswordError").show();
				$("#check_confirmPassword").show();
				$("#check_confirmPassword").html("请输入确认密码");
				return false;
			} else if (password != confirmPassword) {
				$("#check_confirmPasswordError").show();
				$("#check_confirmPassword").show();
				$("#check_confirmPassword").html("确认密码和密码不一致");
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
				$("#check_email").html("请输入正确的邮箱地址");
			}
		} else {
			$("#check_emailError").show();
			$("#check_email").show();
			$("#check_email").html("请输入邮箱");
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
						alert(data.status);
						if (data.status == 1) {
							$("#check_phoneError").hide();
							$("#check_phone").hide();
							$("#check_phone").html("");
							phoneFlag = true;
						} else {
							$("#check_phoneError").show();
							$("#check_phone").show();
							$("#check_phone").html("手机号码已被注册");
						}
					}
				});
			} else {
				$("#check_phoneError").show();
				$("#check_phone").show();
				$("#check_phone").html("请输入正确的手机号码");
			}
		} else {
			$("#check_phoneError").show();
			$("#check_phone").show();
			$("#check_phone").html("请输入手机号码");
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
						$("#check_code").html("请输入正确的验证码");
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
				&& emailFlag &&checkcode) {
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
	<div class="logoWrap">
		<div class="container">
			<div class="logo">
				<a href="index.jsp">
					<h1>
						<img src="${basePath }images/front/login_logo.png" />
					</h1>
				</a>
			</div>
			<div class="pull-left welcomeBox">康源公社欢迎您注册</div>
		</div>
	</div>
	<div class="guiderBox">
		<div class="container">
			<ul class="memenu skyblue pull-left">
				<li class="active"><a href="index.jsp">首页</a></li>
				<li class="grid"><a href="#">蔬菜水果</a>
					<div class="mepanel">
						<div class="row">
							<div class="col1 me-one">
								<h4>蔬菜类</h4>
								<ul>
									<li><a href="product.html">有机蔬菜</a></li>
									<li><a href="product.html">地方特产蔬菜</a></li>
									<li><a href="product.html">自产蔬菜</a></li>
								</ul>
							</div>
							<div class="col1 me-one">
								<h4>水果类</h4>
								<ul>
									<li><a href="product.html">有机水果</a></li>
									<li><a href="product.html">进口水果</a></li>
									<li><a href="product.html">国产水果</a></li>
								</ul>
							</div>
						</div>
					</div></li>
				<li class="grid"><a href="#">肉类禽蛋</a>
					<div class="mepanel">
						<div class="row">
							<div class="col1 me-one">
								<h4>牛羊肉</h4>
								<ul>
									<li><a href="product.html">有机牛羊肉</a></li>
									<li><a href="product.html">进口牛羊肉</a></li>
									<li><a href="product.html">精品牛羊肉</a></li>
								</ul>
							</div>
							<div class="col1 me-one">
								<h4>猪肉</h4>
								<ul>
									<li><a href="product.html">有机猪肉</a></li>
									<li><a href="product.html">进口猪肉</a></li>
									<li><a href="product.html">国产猪肉</a></li>
									<li><a href="product.html">农场自养猪肉</a></li>
								</ul>
							</div>
							<div class="col1 me-one">
								<h4>禽类</h4>
								<ul>
									<li><a href="product.html">有机禽类</a></li>
									<li><a href="product.html">散养禽类</a></li>
								</ul>
							</div>
							<div class="col1 me-one">
								<h4>蛋类</h4>
								<ul>
									<li><a href="product.html">有机蛋</a></li>
									<li><a href="product.html">散养蛋</a></li>
								</ul>
							</div>
						</div>
					</div></li>
				<li class="grid"><a href="#">粮油副食</a></li>
				<li class="grid"><a href="#contact">关于我们</a></li>
			</ul>
		</div>
	</div>
	<!---->
	<div class="container">
		<div class="registration">
			<div class="container loginBox">
				<form class="form-horizontal" action="${basePath}/front/register" method="post" id="submitForm">
					<h4 class="loginBoxTit">康源公社注册</h4>
					<div class="form-group">
						<label for="userNameInput" class="col-sm-2 control-label"><em>*</em>手机号:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="phone" name="phone"
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
								name="password" onblur="checkPassword()" placeholder="请输入密码">
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
							<input type="email" class="form-control" id="email" name="email"
								onblur="checkEmail()" placeholder="请输入邮箱">
							<p class="errorTips" id="check_emailError" style="display: none">
								<span class="glyphicon glyphicon-remove-sign errorIcon"></span>
								<span id="check_email" style="display: none"></span>
							</p>
						</div>
					</div>
					<div class="form-group">
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
					</div>
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