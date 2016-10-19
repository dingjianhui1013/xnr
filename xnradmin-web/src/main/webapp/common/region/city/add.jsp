<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String actionForm = basePath+"page/Common/Region/City/add.action";	
	String lookup = basePath + "page/Common/Region/Province/lookup.action";
	
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("lookup",lookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>所属省份：</label>
				<input class="readonly" name="provincetype.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="provincetype.province" type="text"/>		
				<a class="btnLook" href="${lookup}" lookupGroup="provincetype">查找带回</a>	
			</p>
			<p>
				<label>城市名称：</label>
				<input name="city" type="text" size="30" alt="请输入城市名称" class="required"/>
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