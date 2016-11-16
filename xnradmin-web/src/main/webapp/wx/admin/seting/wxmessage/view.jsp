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
		
	String action = basePath+"page/wxmessage/view.action";
	String attachView = basePath+"page/attach/view.action";		
	
	request.setAttribute("action",action);
	request.setAttribute("attachView", attachView);
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	
		<div class="pageFormContent" layoutH="56">
				
			<fieldset>
				<legend>基础信息</legend>
				<p>
					消息标题：${query.wxmessage.msgTitle}			
				</p>
				<p>
					消息类型：${query.msgType.readme}
				</p>
				<p>
					图文消息跳转链接：${query.wxmessage.clickUrl}
				</p>
				
			</fieldset>		
			<fieldset>
				<legend>消息内容:</legend>
				${query.wxmessage.content}
			</fieldset>		
			<fieldset>
				<legend>消息描述:</legend>
				${query.wxmessage.msgDescription}
			</fieldset>					
			<c:if test="${query.upload!=null && query.upload.groupid!=null}">
				查看附件：<a title="查看附件" target="dialog" width=600 height=300 href="${attachView}?uploadGroupid=${query.upload.groupid}" rel="view_attach" class="btnAttach">查看附件</a>
			</c:if>
		</div>
		
		
			
	</form>

</div>