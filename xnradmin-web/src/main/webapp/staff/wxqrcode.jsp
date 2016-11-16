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
	String genUrl = basePath+"page/attach/view.action";
	
	request.setAttribute("action",action);
	request.setAttribute("attachView", attachView);
	request.setAttribute("genUrl", genUrl);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function genwxqrcode(){
		console.log("staff id: ${query.staff.id}");
		var url = "${genUrl}";
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
</script>
<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	
		<div class="pageFormContent" layoutH="56">
				
			<fieldset>
				<legend>二维码信息</legend>
				<p>
					员工/合作方名称：			
				</p>
				<p>
					二维码原图:
				</p>
				<p>
					图文消息跳转链接：${query.wxmessage.clickUrl}
				</p>
				
					<c:if test="${query.wxqrcode==null || query.attach==null }">
						<p>
							<input type="button" onclick="javascript:genwxqrcode()" value="获取二维码" >
						</p>
					</c:if>
				
			</fieldset>		
			<fieldset>
				<legend>消息内容:</legend>
				${query.wxmessage.content}
			</fieldset>		
			<fieldset>
				<legend>消息描述:</legend>
				${query.wxmessage.msgDescription}
			</fieldset>					
			
		</div>
		
		
			
	</form>

</div>