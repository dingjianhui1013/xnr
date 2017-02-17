<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String login = basePath+"loginDialog.action";	
	
	request.setAttribute("login",login);

%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/verifyCode.js" type="text/javascript"></script>

<div class="pageContent">
	<form method="post" action="${login}" class="pageForm" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="56">
			<span style="color:red">${errorMsg}</span>
			<p>
				<label>用户名：</label>
				<input type="text" name="j_username" size="30" class="required"/>
			</p>	
			<p>
				<label>密码：</label>
				<input type="password" name="j_password" size="30" class="required"/>
			</p>
			<p>
				<label>验证码：</label>						
				<input id="validateCode" class="code" size="4" name="validateCode" type="text" class="required"/>  
				<label>
					<span><img id="imgObj" onclick="changeImg()" src="page/vcode/vcode.action" alt="" width="80" height="25" /></span>
					<span><a href="#" onclick="changeImg()">换一张</a></span>
				</label>
			</p>			
		</div>
		<div class="formBar">
			<ul>				
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
				</li>
			</ul>
		</div>
	</form>

</div>