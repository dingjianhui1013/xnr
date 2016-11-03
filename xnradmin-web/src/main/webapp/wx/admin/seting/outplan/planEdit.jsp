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
 			var dateStart = $("#dateStart").val();
			var dateEnd = $("#dateEnd").val();
			var output = $("#output").val();
 			if(dateStart==null||dateStart==""){
				$("#Yz").html("请输入预计产出开始日期").show();
 			}else if(dateEnd==null||dateEnd==""){
				$("#Yz").html("请输入预计产出结束日期").show();
			}else if(output==null||output==""){
				$("#Yz").html("产出数量").show();
			}else{
				$("#Yz").html("").hide();
				$("#form").submit();
			}
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
							  <form action="saveEdit.action" method="post">
							  		<input type="hidden" name="outPlan.id" value="${outPlan.id}"/>
								  <div class="form-group">
								    <label for="" class="col-sm-2 control-label labelFont">类别</label>
								    <div class="col-sm-10">
								    	<select name="outPlan.goodsId"  class="form-control">
								    	
										  <option <c:if test="${outPlan.goodsId eq '生菜' }">selected="selected" </c:if>>生菜</option>
										  <option <c:if test="${outPlan.goodsId eq '白菜' }">selected="selected" </c:if>>白菜</option>
										  <option <c:if test="${outPlan.goodsId eq '土豆' }">selected="selected" </c:if>>土豆</option>
										</select>
								    </div>
								  </div>
								  <div class="form-group">
								    <label for="" class="col-sm-2 control-label labelFont">产出日期</label>
								    <div class="col-sm-10">
								    	  <input type="text" name="outPlan.startTime"  id="dateStart" value="${outPlan.startTime }" class="input form-control" placeholder="请选择开始日期" />
				    					  <input type="text"  name="outPlan.endTime"  id="dateEnd"  value="${outPlan.endTime }"  class="input form-control dateEnd" placeholder="请选择结束日期" />
								    </div>
								  </div>
								  <div class="form-group">
								    <label for="" class="col-sm-2 control-label labelFont">产出重量</label>
								    <div class="col-sm-10">
										<input type="text" id="output" name="outPlan.output" value="${ outPlan.output}" class="numInput form-control" />
										<div class=" mt1">
											<select name="outPlan.type"  class="form-control">
											  <option <c:if test="${outPlan.unitId eq '吨' }">selected="selected" </c:if>>吨</option>
											  <option <c:if test="${outPlan.unitId eq '千克' }">selected="selected" </c:if>>千克</option>
											  <option <c:if test="${outPlan.unitId eq '斤' }">selected="selected" </c:if>>斤</option>
											</select>
										</div>
								    </div>
								  </div>
								  <div id="Yz" style="display:none ;color:red" class="listTipsBox"></div>
								  <div class="btnBox">
								  	<button type="submit" onclick="validationPin()"  class="btn btn-success">保存</button>
								  	<button type="submit" class="btn btn-default">返回</button>								 
								  </div>
							  </form>
						  </div>
					  </div>
				  </div>

			
		</div>
	</div>
</body>
</html>