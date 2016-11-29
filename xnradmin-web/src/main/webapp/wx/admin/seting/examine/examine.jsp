<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>商户审核</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css"
	href="<%=path %>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=path %>/css/mobiscroll.css">
<link rel="stylesheet" type="text/css"
	href="<%=path %>/css/mobiscroll_date.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
<script type="text/javascript" src="<%=path %>/js/jquery-1.11.3.min.js"></script>
<%-- 	<script type="text/javascript" src="<%=path %>/js/mobiscroll_date.js"></script> --%>
<script type="text/javascript" src="<%=path %>/js/mobiscroll_date.js"
	charset="utf-8"></script>
<script type="text/javascript" src="<%=path %>/js/mobiscroll.js"></script>
<script type="text/javascript" src="<%=path %>/js/common.js"></script>
<script type="text/javascript">
$(function(){
	if($("#msg").val()!="")
		{
			alert($("#msg").val());
			$(".readOnly").attr("readonly","readonly");
			$("#sub").text('提交成功');
			$("#sub").removeAttr("onclick");
			$("#sub").removeAttr("id");
		}
});
function validation()
{
	var farmerName = $("#farmerName").val();
	var farmerTel = $("#farmerTel").val();
	if(farmerName==null||farmerName=="")
		{
			$("#Yz").html("请填写姓名").show();
		}else if(farmerTel==null||farmerTel=="")
			{
				$("#Yz").html("请填写电话号").show();
			}else
				{
					$("#Yz").html("").show();
					$("#form").submit();
				}
	}
</script>
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<span class="titleBox">商户审核</span>
		</div>
		<div class="contentBox">
			<form id="form" role="form" action="<%=path %>/page/wx/farmer/saveFarmerExamine.action" method="post">
				<div class="form-group">
					<label for="" class="col-sm-2 control-label labelFont">姓名</label>
					<div class="col-sm-11">
						<input type="text" id="farmerName" name="farmerExamine.farmerName" class="numInput form-control readOnly" value="${farmerExamine.farmerName}"/>
						<input type="hidden" value="${farmerId}" name="farmerExamine.farmerId"/>
						<input type="hidden" value="${msg}" id="msg"/>
					</div>
					<label for="" class="col-sm-2 control-label labelFont">电话号</label>
					<div class="col-sm-11">
						<input type="text" id="farmerTel" name="farmerExamine.farmerTel" class="numInput form-control readOnly"  maxlength="11" onkeyup='this.value=this.value.replace(/\D/gi,"")' value="${farmerExamine.farmerTel}"/>
					</div> 
					<label for="" class="col-sm-2 control-label labelFont">合同号</label>
					<div class="col-sm-11">
						<input type="text" name="farmerExamine.contractNumber" class="numInput form-control readOnly" onkeyup='this.value=this.value.replace(/\D/gi,"")' value="${farmerExamine.contractNumber}"/>
					</div> 
				</div>
				<div id="Yz" style="display: none; color: red" class="listTipsBox"></div>
				<div class="btnBox">
					<button type="button" onclick="validation()" class="btn btn-success" id="sub">确认提交</button>
				</div>
			</form>

		</div>
	</div>
</body>
</html>
