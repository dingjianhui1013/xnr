<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/status/add.action";
	

	
	request.setAttribute("action",action);

%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>状态名：</label>
				<input name="query.statusName" type="text" size="30" alt="显示用" class="required"/>
			</p>			
			<p>
				<label>描述：</label>
				<input name="query.description" type="text" size="30" alt="可自定义聚合用" class="required"/>				
			</p>
			<p>
				<label>类名：</label>
				<input name="query.className" type="text" size="30" alt="具体类名:com.xx.xx" class="required"/>				
			</p>	
			<p>
				<label>状态码：</label>
				<input name="query.statusCode" type="text" size="30" alt="自定义状态码"/>				
			</p>			
			<p>
				<label>排序(大->小)：</label>
				<input name="query.sort" type="text" size="30" value="0" class="digits required"/>				
			</p>	
			<p>
				<label>状态说明：</label>
				<input name="query.readme" type="text" size="30" alt="状态说明"/>				
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