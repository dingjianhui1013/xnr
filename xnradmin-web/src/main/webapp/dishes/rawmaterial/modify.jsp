<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String actionForm = basePath+"page/dishes/rawmaterial/modify.action";
	String rwMaterialTypeLookup = basePath+"page/dishes/rawmaterial/rwMaterialTypeLookup.action";
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("rwMaterialTypeLookup", rwMaterialTypeLookup);
	
	
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		<input name="rawMaterialId" value="${rawMaterialId}" type="hidden"/>
			<p>
				<label>原料名称：</label>
				<input name="rawMaterialName" type="text" size="30" value="${rawMaterialName}" class="required"/>
			</p>	
			<p>
				<label>原料类型：</label>
				<input class="readonly" name="status.id" readonly="readonly" value="${materialTypeId}" type="hidden"/>
				<input readonly="readonly" name="status.statusCode" value="${materialTypeName}" type="text"/>	
				<a class="btnLook" href="${rwMaterialTypeLookup}" lookupGroup="status">查找带回</a>
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