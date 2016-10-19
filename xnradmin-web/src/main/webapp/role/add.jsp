<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/role/add.action";

	
	request.setAttribute("action",action);
	
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>角色名称：</label>
				<input name="roleName" type="text" size="30" alt="请输入名称" class="required"/>
			</p>	
			<p>
				<label>角色代码：</label>
				<input name="roleCode" type="text" size="30" alt="请输入代码" class="required"/>
			</p>
			<fieldset>
				<legend>描述</legend>
				<dl class="nowrap">
					<dt>请输入角色描述：</dt>
					<dd><textarea name="desc" cols="50" rows="2""></textarea></dd>
				</dl>
			</fieldset>
			
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