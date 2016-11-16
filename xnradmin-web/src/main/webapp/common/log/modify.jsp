<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/log/modify.action";
	

	
	request.setAttribute("action",action);

%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input name="query.id" value="${query.id}" type="hidden"/>
			<p>
				<label>状态名：</label>
				<input name="query.logName" value="${query.logName}" type="text" size="30" alt="日志名，用于logger和appender" class="required"/>
				
			</p>			
			<p>
				<label>描述：</label>
				<input name="query.description" value="${query.description}" type="text" size="30" alt="日志描述" class="required"/>				
			</p>
			<p>
				<label>路径(可用类似${catalina.home}的环境变量)：</label>
				<input name="query.filepath" value="${query.filepath}" type="text" size="30" alt="相对或绝对路径" class="required"/>				
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