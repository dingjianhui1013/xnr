<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>个人中心页</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/mobiscroll.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/mobiscroll_date.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
	<script type="text/javascript" src="<%=path %>/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/mobiscroll_date.js"></script>
	<script type="text/javascript" src="<%=path %>/js/mobiscroll.js"></script>
	<script type="text/javascript" src="<%=path %>/js/common.js"></script>
	<script type="text/javascript" >
		
		function validationPin(){
			var goodsId = $("#goodsId").val();
 			var dateStart = $("#dateStart").val();
			var dateEnd = $("#dateEnd").val();
			var output = $("#output").val();
 			if(dateStart==null||dateStart==""){
				$("#Yz").html("请输入预计产出开始日期").show();
 			}else if(dateEnd==null||dateEnd==""){
				$("#Yz").html("请输入预计产出结束日期").show();
			}else if(output==null||output==""){
				$("#Yz").html("产出数量").show();
			}else if(goodsId==null||goodsId==""){
				$("#Yz").html("请输入详细类型").show();
			}else{
				$("#Yz").html("").hide();
				$("#form").submit();
			}
		}
// 		function getGoods()
// 		{
// 			var id= $('#businesCategoryId option:selected').val();
// 			$.ajax({
// 				type:'POST',
<%-- 				url:'<%=path %>/page/wx/outplan/getGoods.action', --%>
// 			data : {
// 				businesCategoryId : id
// 			},
// 			dataType : 'JSON',
// 			success : function(data) {
// 				$("#goodsId").html("<option value=''>请选择详细</option>");
// 				for (var i = 0; i < data.goodslist.length; i++) {
// 					$("#goodsId")
// 							.append(
// 									"<option value="+data.goodslist[i].id+" class="+data.goodslist[i].goodsWeightId+">"
// 											+ data.goodslist[i].goodsName
// 											+ "</option>");
// 				}
// 			}
// 		});
// 	}
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
				$("#goodsId").html("<option value=''>请选择详细</option>");
				for (var i = 0; i < data.goodslist.length; i++) {
					$("#goodsId")
							.append(
									"<option value="+data.goodslist[i].id+" class="+data.goodslist[i].goodsWeightId+">"
											+ data.goodslist[i].goodsName
											+ "</option>");
				}
			}
		});
	}
		function getWeight()
		{
			var id =$("#goodsId option:selected").attr("class");
			$.ajax({
				type:'POST',
				url:'<%=path %>/page/wx/outplan/getWeight.action',
					data : {
						weightId : id
					},
					dataType : 'JSON',
					success : function(data) {
						$("#weigthId").html("");
						$("#weigthId").append(
								"<option value="+data.businessWeight.id+">"
										+ data.businessWeight.weightName
										+ "</option>");
					}
				});
	}
	</script>
	
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<span class="titleBox">个人中心</span>
			<div class="myAccount"><span class="glyphicon glyphicon-user"></span>我的账户</div>
		</div>
		<div class="contentBox personalBox">
				  <div class="planListBox">
				  	 <h3 class="bigTit">编辑我的产出计划</h3>
				  	 <div class="planListCon">
						  <div class="editPlanBox">
							  <form id="form" action="saveEdit.action" method="post">
							  		<input type="hidden" name="outplan.id" value="${outPlanVO.outPlan.id}"/>
								<div class="form-group">
<!-- 									<label for="" class="col-sm-2 control-label labelFont">选择分类</label> -->
<!-- 									<div class="col-sm-10"> -->
<!-- 										<select class="form-control" id="businesCategoryId" -->
<!-- 											onchange="getGoods()" name="outplan.businesCategoryId"> -->
<%-- 											<option value="${outPlanVO.outPlan.businesCategoryId}">${outPlanVO.businessCategory.categoryName }</option> --%>
<%-- 											<c:forEach items="${businesCategorys}" var="businesCategorys"> --%>
<%-- 												<option value="${businesCategorys.id}">${businesCategorys.categoryName}</option> --%>
<%-- 											</c:forEach> --%>
<!-- 										</select> -->
<!-- 									</div> -->
									<label for="" class="col-sm-2 control-label labelFont">选择详细类型</label>
									<div class="col-sm-11">
										<select class="form-control" name="outplan.goodsId" id="goodsId" onchange="getWeight()">
											<option value="">请选择详细</option>
											<c:forEach items="${goodslist}" var="goodslist">
												<option value="${goodslist.id}" class="${goodslist.goodsWeightId}" <c:if test="${outPlanVO.outPlan.goodsId==goodslist.id}">selected="selected"</c:if>>${goodslist.goodsName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								  <div class="form-group">
								    <label for="" class="col-sm-2 control-label labelFont">预计产出日期</label>
								    <div class="col-sm-10">
								    	  <input onchange="startTimeYz()" type="text" name="outplan.startTime"  id="dateStart" value="<fmt:formatDate value="${outPlanVO.outPlan.startTime }" pattern="yyyy-MM-dd " ></fmt:formatDate>" class="input form-control" placeholder="请选择开始日期" />
				    					  <input onchange="endTimeYz()" type="text"  name="outplan.endTime"  id="dateEnd"  value="<fmt:formatDate value="${outPlanVO.outPlan.endTime }" pattern="yyyy-MM-dd " ></fmt:formatDate>"  class="input form-control dateEnd" placeholder="请选择结束日期" />
								    </div>
								  </div>
								  <div class="form-group">
								    <label for="" class="col-sm-2 control-label labelFont">产出数量</label>
								    <div class="col-sm-10">
										<input type="text" id="output" onblur="outputYz()" name="outplan.output" value="${outPlanVO.outPlan.output}" class="numInput form-control" />
										<div class=" mt1">
											<select class="form-control" name="outplan.unitId" id="weigthId">
												<option value="${outPlanVO.outPlan.unitId }">${outPlanVO.businessWeight.weightName }</option>
											</select>
										</div>
								    </div>
								  </div>
								  <div id="Yz" style="display:none ;color:red" class="listTipsBox"></div>
								  <div class="btnBox">
								  	<button type="button" onclick="validationPin()"  class="btn btn-success">保存</button>
								  	<button type="button" onclick="javascript:history.back(-1);"  class="btn btn-default">返回</button>								 
								  </div>
							  </form>
						  </div>
					  </div>
				  </div>

			
		</div>
	</div>
</body>
</html>
