<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


String action = basePath+"page/staff/changepwd.action";

request.setAttribute("action",action);
request.setAttribute("basePath",basePath);
%>


<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<dl class="nowrap">
				<label>旧密码：</label>
				<input name="oldpwd" type="password" size="30" class="required alphanumeric"  minlength="3" maxlength="20"/>			
			</dl>
			<dl class="nowrap">
				<label>新密码：</label>
				<input name="newpwd" type="password" size="30" class="required alphanumeric"  minlength="3" maxlength="20"/>
			</dl>
		</div>
		
		<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
	</form>
</div>

