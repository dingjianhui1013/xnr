<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>注册页</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="header.jsp"%>
</head>
<body> 
<div class="logoWrap">	
	<div class="container">	
		<div class="logo">
			<a href="index.html">
			<h1><img src="${basePath }images/front/login_logo.png" /></h1>
			</a>
		</div>
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
				</div>
			</li>
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
				</div>
			</li>
			<li class="grid"><a href="#">粮油副食</a></li>
			<li class="grid"><a href="#contact">关于我们</a></li>
		</ul>
	 </div>
</div>
			
<!---->
<div class="container">
	 <div class="container loginBox">
         <form class="form-horizontal">
              <h4 class="loginBoxTit">找回密码</h4>
                  <div class="tab-pane active" id="p-acountCon">
                      <ul class="nav nav-tabs findPwdTabBox">
                         <li class="active"><a href="#telFind" aria-controls="telFind" role="tab" data-toggle="tab">手机号找回</a></li> 
                          <li><a href="#emailFind" aria-controls="emailFind" role="tab" data-toggle="tab">邮箱找回</a></li>
                      </ul>
                      <div class="tab-content">
                          <div role="tabpanel" class="tab-pane active" id="telFind">
                              <div class="stepBox1">
                                    <div class="form-group">
                                      <label for="inputEmail3" class="col-sm-2 control-label">手机号:</label>
                                      <div class="col-sm-10">
                                        <input type="email" class="form-control" id="inputEmail3" placeholder="请输入手机号">
                                      </div>
                                    </div>
                                    <div class="form-group">
                                          <label for="register-email" class="col-sm-2 control-label">验证码:</label>
                                          <div class="col-sm-10">
                                              <input type="email" id="register-email" class="pull-left form-control yzmInput" placeholder="验证码" >
                                              <input type="button" value="获取验证码" class="pull-right btn btn-default" />
                                          </div>
                                      </div>
                                    <div class="form-group" >
                                      <div class="col-sm-offset-2 col-sm-10 loginBtn">
                                        <a href="resetPwd.html" class="btn btn-success">下一步</a>
                                      </div>
                                    </div>
                              </div>
                          </div>
                          <div role="tabpanel" class="tab-pane" id="emailFind">
                                <div class="stepBox1" style="display:block;">
                                    <div class="form-group">
                                      <label for="inputEmail3" class="col-sm-2 control-label">注册邮箱:</label>
                                      <div class="col-sm-10">
                                        <input type="email" class="form-control" id="inputEmail3" placeholder="请输入邮箱">
                                      </div>
                                    </div>
                                    <div class="form-group" >
                                      <div class="col-sm-offset-2 col-sm-10 loginBtn">
                                        <input type="submit" class="btn btn-success" value="下一步">
                                      </div>
                                    </div>
                                </div>
                                <div class="stepBox2"  style="display:none;">
                                    <p class="redColor">找回密码链接已发至您的邮箱，请登录邮箱进行密码的重置！</p>

                                </div>
                          </div>
                      </div>
                  </div>
        </form>
      </div>
</div>
<!---->
<%@include file="footer.jsp"%>		 
</body>
</html>