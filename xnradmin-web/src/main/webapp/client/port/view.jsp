<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" 
	import="java.util.*" 
	import="java.text.*"
	import="sun.misc.BASE64Encoder" 
	import="java.net.*"
	import="com.cntinker.util.*"
%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/port/modify.action";
	String scriptLookup = basePath+"page/script/lookup.action";		
	
	request.setAttribute("action",action);
	request.setAttribute("scriptLookup", scriptLookup);
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<input name="query.port.id" value="${query.port.id}" type="hidden"/>
		<div class="pageFormContent" layoutH="56">
				
			<fieldset>
				<legend>基础信息</legend>
				<p>
					接口名：${query.port.portName}			
				</p>
				<p>
					选定脚本：${query.script.className}
				</p>
				<p>
					状态：${query.port.statusName}
				</p>									
			</fieldset>		
			<fieldset>
				<legend>接口描述:</legend>
				${query.port.portDesc}
			</fieldset>					
			<fieldset>
				<legend>输入相关</legend>
				<p>
					输入类型：${query.port.intputTypeDesc}
				</p>
				<p>
					是否加密：
					<c:choose>
						<c:when test="${query.port.inputEncrypt}">
							 是
						</c:when>
						<c:otherwise>
							否
						</c:otherwise>
					</c:choose>
				</p>							
			</fieldset>	
			<fieldset>
				<legend>输入描述/示例:</legend>				
				${query.port.inputSource}				
			</fieldset>	
			<fieldset>
				<legend>输出相关</legend>
				<p>
					输入类型：${query.port.outputTypeDesc}
				</p>
				<p>
					是否加密：
					<c:choose>
						<c:when test="${query.port.outputEncrypt}">
							 是
						</c:when>
						<c:otherwise>
							否
						</c:otherwise>
					</c:choose>
				</p>
								
			</fieldset>		
			<fieldset>
				<legend>输入描述/示例:</legend>
				${query.port.outputSource}
			</fieldset>	
		</div>
		
		
			
	</form>

</div>