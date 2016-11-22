<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String actionForm = basePath + "frontUserManager/modify.action";
	request.setAttribute("actionForm", actionForm);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		<input name="updatefrontUser.id" value="${updatefrontUser.id}" type="hidden"/>
			<p>
				<label>用户名：</label>
				<input name="updatefrontUser.userName" type="text" size="25" value="${updatefrontUser.userName}" class="required"/>
			</p>
			<p>
				<label>手机号：</label>
				<input name="updatefrontUser.phone" value="${updatefrontUser.phone}" type="text" size="25" class="required"/>
			</p>
			<p>
				<label>邮箱：</label>
				<input name="updatefrontUser.email" value="${updatefrontUser.email}" type="text" size="25" class="required"/>
			</p>
		</div>
		<div class="formBar">
			<ul>				
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>