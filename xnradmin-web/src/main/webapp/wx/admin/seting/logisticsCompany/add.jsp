<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String action = basePath + "page/wx/admin/seting/logisticsCompany/add.action";
	String lookup = basePath + "page/wx/admin/seting/primaryConfiguration/lookup.action";
	request.setAttribute("action", action);
	request.setAttribute("lookup", lookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>物流公司名称：</label>
				<input name="companyName" type="text" size="25" class="required"/>
			</p>
			<p>
				<label>物流公司名称：</label>
				<input name="companyUrl" type="text" size="25" class="required"/>
			</p>
			<p>
				<label>排序：</label>
				<input name="sortId" type="text" size="25" value="0" class="required number"/>
			</p>
			<p>
				<label>对应商城：</label>
				<input class="readonly" name="primaryConfiguration.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="primaryConfiguration.mallName" type="text"/>		
				<a class="btnLook" href="${lookup}" lookupGroup="primaryConfiguration">查找带回</a>	
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