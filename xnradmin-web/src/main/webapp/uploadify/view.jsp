<%@page import="com.cntinker.util.StringHelper"%>
<%@ page language="java" import="java.util.*" import="com.cntinker.uuid.*" import="com.cntinker.*" pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    
    
	String refreshAction = basePath+"page/attach/refreshAttach.action";
	
    request.setAttribute("refreshAction",refreshAction);
 
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css" media="screen">
.my-uploadify-button {
	background:none;
	border: none;
	text-shadow: none;
	border-radius:0;
}

.uploadify:hover .my-uploadify-button {
	background:none;
	border: none;
}

.fileQueue {
	width: 400px;
	height: 150px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>
<script type="text/javascript">
        $(document).ready(function() {
        	refresh();
        });        
        function refresh(data){
        	console.log("sessionid: "+'<%=session.getId()%>');
        	var url = "${refreshAction}";
        	$.ajax({
				type:'POST',
				url : url,
				data : "uploadGroupid=${uploadGroupid}",
				async :false,
				success:function(data){
					refreshFiles(data);
					
					$('#countBygroupid').html(data.countByGroupid);
					var text = $("#uploadRemark").val();
					var href = "javascript:$.bringBack({groupid:'${uploadGroupid}', uploadCount:'"+data.countByGroupid+"个文件'})";
					$("#submitBack").attr("href",href);
					
				}
			});	
        }
        
        function deleteAttach(attachId){
        	var url = "${deleteAction}";
            $.ajax({
				type:'POST',
				url : url,
				data : "attachId="+attachId,
				async :false,
				success:function(data){
					refreshFiles(data);
				}
			});	      	
        }
        
        function refreshFiles(data){
			var attachList = data.attachList;
			var html = "<table>";
			for(var i=0;i<attachList.length;i++){
				var attach=attachList[i];
				html = html + "<tr><td width=\"350px\">" + attach.oldFileName+ "</td>";
				html = html + "<td width=\"50px\"><a href=\"${viewPath}${webPath}" + attach.path + attach.attachName+ "\" target=\"_blank\">查看</a></td></tr>";
			}
			html = html + "</table>";
			$('#attachList').html(html);
			var href = "javascript:$.bringBack({accessoryGroupId:'${uploadGroupId}',groupid:'${uploadGroupid}', uploadCount:'有" +data.attachCount+ "个附件' , groupId:'${uploadGroupId}', uploadContent:'"+data.uploadContent+"', uploadDesc:'有"+data.attachCount+"个附件'})";
			$("#submitBack").attr("href",href);       	
        }
      
</script>
<h2 class="contentTitle">查看附件</h2>
<form action="" enctype="multipa" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
<div class="pageContent">
	<!-- 
	<input name="uploadGroupid" value="${uploadGroupid}"/>
	 -->
	<div class="pageFormContent" layoutH="97">
		<dl class="nowrap">
			<!-- <input id="countBygroupid" name="countBygroupid" /> -->
			
			<!-- 
			<dd>已上传文件数：<span id="countBygroupid" name="countBygroupid"></span></dd>
			 -->
			<dd>已上传文件：
				<!-- 
				<input type="button" onclick="javascript:refresh()" value="刷新" >
				 -->
				<div id="attachList">
				</div>
			</dd>
		
			
			 
			
		</dl>
		
		
	</div>
	<div class="formBar">
		<ul>
			<!-- 
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">上传</button></div></div></li>
			 -->
			<!-- 
			<li><div class="button"><div class="buttonContent"><a id="submitBack" href="" title="查找带回"><button type="button">选择上传的附件</button></a></div></div></li>
			 -->
			 <!-- 
			<li><div class="button"><div class="buttonContent">[<a id="submitBack" href="javascript:$.bringBack({groupid:'${uploadGroupId}', uploadContent:'${uploadContent}'})" title="查找带回">选择上传的附件</a>]</div></div></li>
			 -->
			<!-- 
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
			 -->
		</ul>
	</div>
</div>
</form>
