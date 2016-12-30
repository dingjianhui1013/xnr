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
	alert("成功");
});
</script>
</head>
<body>
	<form action="">
		<input type="text" value="123" id="userName"/>
		<input type="text" value="456" id="status"/>
	</form>
</body>
</html>