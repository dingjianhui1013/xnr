<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>商户首页</title>
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
	
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<span class="titleBox">产出计划</span>
			<div class="myAccount"><span class="glyphicon glyphicon-user"></span>我的账户</div>
		</div>
		<div class="contentBox">
			<form role="form" action="save.action">
				  <div class="form-group">
				    <label for="" class="col-sm-2 control-label labelFont">选择分类</label>
				    <div class="col-sm-10">
				    	<select class="form-control" name="outplan.goodsId">
						  <option value="1">生菜</option>
						  <option value="2">白菜</option>
						  <option value="3">土豆</option>
						</select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="" class="col-sm-2 control-label labelFont">预计产出日期</label>
				    <div class="col-sm-10">
				    	 <input type="text"  id="dateStart" name="outplan.startTime" class="input form-control" placeholder="请选择开始日期" />
				    	<input type="text"  id="dateEnd"  name="outplan.endTime" class="input form-control dateEnd" placeholder="请选择结束日期" />
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="" class="col-sm-2 control-label labelFont">产出数量</label>
				    <div class="col-sm-7">
				    	<input type="text" name="outplan.output" class="numInput form-control" />
				    </div>
				    <div class="col-sm-3 mt1">
				    	<select class="form-control" name="outplan.unitId">
						  <option value="1">吨</option>
						  <option value="2">千克</option>
						  <option value="3">斤</option>
						</select>
				    </div>
				  </div>
				  <div class="btnBox">
				  	<button type="submit" class="btn btn-default">确认提交</button>
				  </div>
</form>
			
		</div>
	</div>
</body>
</html>