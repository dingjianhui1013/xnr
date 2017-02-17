<%@page import="com.cntinker.util.StringHelper"%>
<%@ page language="java" import="java.util.*"
	import="com.cntinker.uuid.*" import="com.cntinker.*"
	pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    request.setAttribute("basePath",basePath);
 
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
		$(function() {
		    $('#uploadify').uploadify({
		        swf:'uploadify/scripts/uploadify.swf',    //指定上传控件的主体文件
		        uploader:'page/wx/farmer/replace.action?farmerImage.id=${farmerImage.id}',    //指定服务器端上传处理文件
				auto:false,
				buttonText:'请选择文件',
				fileObjName:'uploadify',
				fileSizeLimit:'4096KB',
				fileTypeDesc:'*.bmp;*.jpg;*.jpeg;*.png;',
				fileTypeExts:'*.bmp;*.jpg;*.jpeg;*.png;',
				multi:false,
				queueSizeLimit:1,
				uploadLimit:1,
				onUploadSuccess:function(file, data, response){
 						refresh(data);
				}
		    });
		});
        function refresh(data){
        	data=$.parseJSON(data);
        	dialogAjaxDone(data);
        }
        
        function refreshFiles(data){
        	console.log(data);
			$('#newfile').html(html);  	
        }
</script>
<h2 class="contentTitle">图片替换</h2>
<form action="page/wx/farmer/replace.action?farmerImage.id=${farmerImage.id}" enctype="multipart/form-data" method="post" id="upload"
	class="pageForm required-validate"
	onsubmit="return validateCallback(this, dialogAjaxDone)">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="97">
			<dl class="nowrap">
				<!-- <input id="countBygroupid" name="countBygroupid" /> -->
				<dt>上传附件:</dt>
				<dd>
					<div style="float: left; margin: 10px">
							<input id="uploadify" type="file" name="uploadify" />
					</div>
					<img name="preview" alt="" src="">
				</dd>
			</dl>


		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
					<div class="buttonContent">
						<button type="button" onclick="javascript:$('#uploadify').uploadify('upload','*')">提交</button>
					</div>
				</div></li>
			</ul>
		</div>
	</div>
</form>
