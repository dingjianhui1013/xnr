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
<title>商户首页</title>
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
		
		function validationPin(){
 			var dateStart = $("#dateStart").val();
			var dateEnd = $("#dateEnd").val();
			var output = $("#output").val();
 			if(dateStart==null||dateStart==""){
				$("#Yz").html("请输入预计产出开始日期").show();
 			}else if(dateEnd==null||dateEnd==""){
				$("#Yz").html("请输入预计产出结束日期").show();
			}else if(output==null||output==""){
				$("#Yz").html("产出数量不能为空").show();
			}else{
				$("#Yz").html("").hide();
				$("#form").submit();
			}
		}
		function outputYz(){
			var reg = new RegExp("^[0-9]*$"); 
			var output = $("#output").val();
			//alert(reg.test(output));
			if(!reg.test(output)){ 
				$("#Yz").html("产出数量必须为数字").show();
				$("#output").val("");
			}else{
				$("#Yz").html("").show();
			}
		}
		
		function endTimeYz(){
			var start = new Date($("#dateStart").val().replace(/\-/g, "\/"));  
			var end = new Date($("#dateEnd").val().replace(/\-/g, "\/"));  
			if($("#dateStart").val()!=""&& end<start){
				$("#Yz").html("结束时间不能小于开始时间").show();
				$("#dateEnd").val("");
			}else{
				$("#Yz").html("").show();
			}
		}
		function startTimeYz(){
			var start = new Date($("#dateStart").val().replace(/\-/g, "\/"));  
			var end = new Date($("#dateEnd").val().replace(/\-/g, "\/"));  
			if($("#dateStart").val()!=""&& end<start){
				$("#Yz").html("开始时间不能大于结束时间").show();
				$("#dateStart").val("");
			}else{
				$("#Yz").html("").show();
			}
		}
		function getGoods()
		{
			var id= $('#businesCategoryId option:selected').val();
			$.ajax({
				type:'POST',
				url:'<%=path %>/page/wx/outplan/getGoods.action',
			data : {
				businesCategoryId : id
			},
			dataType : 'JSON',
			success : function(data) {
				$("#goodsId").html("");
				for (var i = 0; i < data.goodslist.length; i++) {
					$("#goodsId")
							.append(
									"<option value="+data.goodslist[i].id+">"
											+ data.goodslist[i].goodsName
											+ "</option>");
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<span class="titleBox">产出计划</span>
			<div class="myAccount">
				<span class="glyphicon glyphicon-user"></span>我的账户
			</div>
		</div>
		<div class="contentBox">
			<form id="form" role="form" action="save.action" method="post">
				<input type="hidden" value="${userId }" name="outplan.userId">
				<div class="form-group">
					<label for="" class="col-sm-2 control-label labelFont">选择分类</label>
					<div class="col-sm-10">
						<select class="form-control" id="businesCategoryId"
							onchange="getGoods()" name="outplan.businesCategoryId">
							<c:forEach items="${businesCategorys}" var="businesCategorys">
								<option value="${businesCategorys.id}">${businesCategorys.categoryName}</option>
							</c:forEach>
						</select>
					</div>
					<br> <label for="" class="col-sm-2 control-label labelFont">选择详细类型</label>
					<div class="col-sm-11">
						<select class="form-control" name="outplan.goodsId" id="goodsId">
							<option value=""></option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label labelFont">预计产出日期</label>
					<div class="col-sm-10">
						<input type="text" id="dateStart" onchange="startTimeYz()"
							name="outplan.startTime" class="input form-control"
							placeholder="请选择开始日期" /> <input type="text" id="dateEnd"
							onchange="endTimeYz()" name="outplan.endTime"
							class="input form-control dateEnd" placeholder="请选择结束日期" />
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label labelFont">产出数量</label>
					<div class="col-sm-7">
						<input type="text" onblur="outputYz()" id="output"
							name="outplan.output" class="numInput form-control" />
					</div>
					<div class="col-sm-3 mt1">
						<select class="form-control" name="outplan.unitId" id="">
							<option value="吨">吨</option>
							<option value="千克">千克</option>
							<option value="斤">斤</option>
						</select>
					</div>
				</div>
				<div id="Yz" style="display: none; color: red" class="listTipsBox"></div>
				<div class="btnBox">
					<button type="button" onclick="validationPin()"
						class="btn btn-success">确认提交</button>
				</div>
			</form>

		</div>
	</div>
</body>
</html>
