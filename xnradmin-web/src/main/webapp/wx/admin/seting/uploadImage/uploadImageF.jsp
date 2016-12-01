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
	if($("#status").val()==null||$("#status").val()==""||$("#status").val()=="0")
		{
			var farmerId = $('#userId').val();
			window.location.href="<%= path%>/page/wx/farmer/farmerExamine.action?farmerId="+farmerId;
		}else if ($("#status").val()=="3")
			{
				alert("审核信息已经提交，请等待！")
			}else
			{
				$.get("<%= path %>/page/wx/wxconnect/uploadFF.action",{userName:$("#userName").val(),_:new Date().getTime()},function (data){
					window.location.href="<%= path %>/wx/admin/seting/uploadImage/obtainImageF.jsp";
				  },"json");
			}
});
</script>
</head>
<body>
	<form action="">
		<input type="hidden" value="${userName}" id="userName"/>
		<input type="hidden" value="${status}" id="status"/>
	</form>
</body>
</html>