<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/wx/wxinterface/sendToView.action";
	
	request.setAttribute("action",action);
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	
			
		<div id=form class="pageFormContent" layoutH="56">
			<div class="divider"></div>
			<fieldset>
				<legend>url</legend>
				<dl class="nowrap">
					<dt>请输入url：</dt>
					<dd><textarea id="urlOut" class="required" name="urlOut" cols="60" rows="5">${urlOut}</textarea></dd>
				</dl>
			</fieldset>	
			<fieldset>
				<legend>返回头部信息：</legend>
				<dl class="nowrap">
					<dt>返回头部信息：</dt>
					<dd><textarea id="headerOut" name="headerOut" cols="60" rows="5"></textarea></dd>
				</dl>
			</fieldset>	
			<fieldset>
				<legend>返回内容：</legend>
				<dl class="nowrap">
					<dt>返回头部信息：</dt>
					<dd><textarea id="resOut" name="resOut" cols="60" rows="5"></textarea></dd>
				</dl>
			</fieldset>	
			<button type="button" onclick="onsub()">发送</button>
		</div>
		
		
		
	</form>

</div>

<script type="text/javascript">
function onsub(){
	var url = $("#urlOut").val();
	console.log("url:"+url);
	var res = send(url);
	console.log(res);
	
}

function send(urlOut){	
	urlOut = encodeURIComponent(urlOut);
	var timestamp = (new Date()).valueOf();
	var url = "page/wx/wxinterface/sendToView.action";
	$.ajax({
		type : "GET",
		url : url,
		data : "urlOut=" + urlOut + "&timestamp=" + timestamp,
		async : false,
		success : function(data) {
			console.log(data);
			res = data;
			
		}
	});
	
	console.log("headerOut:"+res.headerOut);
	console.log("resOut:"+res.resOut);
	
	$("#headerOut").val(res.headerOut);
	$("#resOut").val(res.resOut);
	
	return res;
}
</script>