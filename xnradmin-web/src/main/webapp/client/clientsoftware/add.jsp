<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String actionForm = basePath+"page/Client/ClientSoftware/add.action";
	String softwareTypeLookup = basePath+"page/Client/ClientSoftware/softwareTypeLookup.action";
	String osTypeLookup = basePath+"page/Client/ClientSoftware/osTypeLookup.action";
	String updateModeLookup = basePath+"page/Client/ClientSoftware/updateModeLookup.action";

	request.setAttribute("actionForm",actionForm);
	request.setAttribute("softwareTypeLookup",softwareTypeLookup);
	request.setAttribute("osTypeLookup",osTypeLookup);
	request.setAttribute("updateModeLookup",updateModeLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>软件名称：</label>
				<input name="softwareName" type="text" size="30" alt="请输入软件名称" class="required"/>
			</p>	
			<p>
				<label>用户类型：</label>
				<input class="readonly" name="softwareType1.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="softwareType1.statusName" type="text"/>	
				<a class="btnLook" href="${softwareTypeLookup}" lookupGroup="softwareType1">查找带回</a>	
			</p>
			<p>
				<label>系统类型：</label>
				<input class="readonly" name="osType1.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="osType1.statusName" type="text"/>	
				<a class="btnLook" href="${osTypeLookup}" lookupGroup="osType1">查找带回</a>	
			</p>
			<p>
				<label>更新类型：</label>
				<input class="readonly" name="updateMode1.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="updateMode1.statusName" type="text"/>	
				<a class="btnLook" href="${updateModeLookup}" lookupGroup="updateMode1">查找带回</a>	
			</p>
			<p>
				<label>版本号：</label>
				<input name="versionNumber" type="text" size="30" alt="请输入版本号" class="required"/>
			</p>
			<p>
				<label>软件描述：</label>
				<input name="description" type="text" size="30" alt="请输入软件描述" class="required"/>
			</p>
			<p>
				<label>下载链接：</label>
				<input name="softwarePath" type="text" size="30" alt="请输入下载链接" class="required"/>
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