<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String all = basePath+"page/menu/all.action";
	String actionForm = basePath+"page/menu/add.action";	
	String lookup = basePath + "page/menu/lookup.action";
	
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("all",all);
	request.setAttribute("lookup",lookup);
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>菜单名称：</label>
				<input name="menuname" type="text" size="30" alt="请输入菜单名称" class="required"/>
			</p>	
			<p>
				<label>菜单英文名称：</label>
				<input name="enname" type="text" size="30" alt="请输入英文名称"/>
				<span class="info">navTabid</span>
			</p>
			<p>
				<label>菜单链接：</label>
				<input name="menulink" type="text" size="30" alt="请输入链接"/>
			</p>
			<p>
				<label>上级菜单名称：</label>
				<input name="org1.orgName" readonly="readonly" type="text"/>
			</p>
			<p>
				<label>上级菜单：</label>								
				<input name="org1.id" readonly="readonly" type="text"/>
				<a class="btnLook" href="${lookup}" lookupGroup="org1">查找带回</a>											
			</p>
			<p>
				<label>菜单排序：</label>
				<input name="sortOrder" type="text" size="30" class="digits" alt="请输入数字"/>
			</p>
			<p>
				<label>菜单级别：</label>
				<select class="combox" name="level">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
			</select>
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