<%@page import="com.cntinker.util.StringHelper"%>
<%@ page language="java" import="java.util.*" import="com.cntinker.uuid.*" import="com.cntinker.*" pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    
	String action = basePath+"page/dishes/upload/uploadTecAttachments.action";

    request.setAttribute("action",action);

 
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2 class="contentTitle">上传订单</h2>
<form action="${action}" enctype="multipa" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
<div class="pageContent" style="margin: 0 10px" layoutH="50">
		<h2>单个按钮方式：</h2>
		<div class="divider"></div>
		
		<input id="testFileInput" type="file" name="files" 
		uploaderOption="{
			swf:'uploadify/scripts/uploadify.swf',
			uploader:'${action}',
			formData:{},
			buttonText:'请选择文件',
			fileSizeLimit:'20000KB',
			fileTypeDesc:'*.xls;*.xlsx;',
			fileTypeExts:'*.xls;*.xlsx;',
			auto:true,
			multi:true,
			onUploadSuccess:uploadifySuccess,
			onQueueComplete:uploadifyQueueComplete
		}"
	/>
	<div id="fileQueue" class="fileQueue"></div>
</form>
