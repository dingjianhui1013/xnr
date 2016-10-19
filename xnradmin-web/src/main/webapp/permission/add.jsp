<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/per/add.action";
	String lookup = basePath + "page/menu/selectLookup.action";
	
	request.setAttribute("action",action);
	request.setAttribute("lookup",lookup);
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>权限名称：</label>
				<input name="permissionName" type="text" size="30" alt="请输入名称" class="required"/>
			</p>	
			<p>
				<label>权限代码：</label>
				<input name="permissionCode" type="text" size="30" alt="请输入代码" class="required"/>
			</p>
			<p>
				<label>权限说明：</label>				
				<textarea name="permissionDesc" type="text" size="30" alt="请输入说明" />										
			</p>			
			<p>
			
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