<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String all = basePath+"page/Common/Region/Province/all.action";
	String actionForm = basePath+"page/Common/Region/Province/modify.action";	
	String lookup = basePath + "page/Common/Region/Province/lookup.action";
	
	request.setAttribute("lookup",lookup);
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("all",all);
	
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
				<input name="provinceid" value="${provinceid}" type="hidden"/>
			<p>
				<label>省份名称：</label>
				<input name="province" type="text" size="30" value="${province}" class="required"/>
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