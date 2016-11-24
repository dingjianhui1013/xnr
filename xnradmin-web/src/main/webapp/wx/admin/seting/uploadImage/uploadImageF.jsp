<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传照片</title>
<script src="<%=path %>/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=path %>/js/verifyCode.js" type="text/javascript"></script>
<script>
$(function(){
	$.get("<%= path %>/page/wx/wxconnect/uploadFF.action",{userName:$("#userName").val(),userId:$("#userId").val(),_:new Date().getTime()},function (data){
		  window.location.href="<%= path %>/wx/admin/seting/uploadImage/obtainImageF.jsp";
	  },"json");
});
</script>
</head>
<body>
	<form action="">
		<input type="hidden" value="${userName}" id="userName"/>
		<input type="hidden" value="${userId}" id="userId"/>
	</form>
</body>
</html>