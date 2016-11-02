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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<span class="titleBox">个人中心</span>
			<div class="myAccount"><span class="glyphicon glyphicon-user"></span>我的账户</div>
		</div>
		<div class="contentBox personalBox">
			<div class="uploadImgListBox">
				<form role="form">
					  <div class="form-group">
					    <div class="searchBox">
					    	<input type="text"  class="form-control"  placeholder="请输入关键词进行搜索"/>
					    </div>
					    	<input type="button" value="搜索" class="btn btn-primary searchBtn">
					    
					  </div>
					  <h3 class="bigTit">我上传的图片</h3>
					  <div class="form-group">
					    <div class="col-sm-10 uploadImgBox">
					    	 <ul>
					    	 	<c:forEach items="${imageTypes}" var="imageType">
					    	 	<li>
					    	 		<span class="circleIcon"></span>
					    	 		<h3><fmt:formatDate value="${imageType[1]}" pattern="yyyy-MM-dd " ></fmt:formatDate> </h3>
					    	 		<div>
					    	 			<p class="sortTit">${imageType[0]}</p>
					    	 			<div class="uploadImgList">
					    	 				<ul>
					    	 				<li>
					    	 					<c:forEach items="${farmerImages}" var="farmerImage">
					    	 					<c:if test="${farmerImage.type eq  imageType[0]}">
					    	 					<li><img src="<%=path %>${farmerImage.url}"  class="img-responsive" /></li>
												</c:if>
					    	 					</c:forEach>						
					    	 				</ul>
					    	 			</div>
					    	 		</div>
					    	 	</li>
					    	 	</c:forEach>
					    	 </ul>
					    </div>
					  </div>
					  <div class="btnBox">
							  	<button type="submit" class="btn btn-success">编辑</button>
							  </div>
					  </form>
				  </div>
				  <div class="planListBox">
				  	 <h3 class="bigTit">我的产出计划</h3>
				  	 <div class="planListCon">
				  	 	<div class="planShowBox" >
				  	 		<div class="planList">
				  	 			<c:forEach items="${outplans}" var="outplan">
				  	 					<div class="d-planList">
											  <form action="">
												  <div class="form-group">
												    <label for="" class="col-sm-2 control-label labelFont">类别</label>
												    <div class="col-sm-10">
												    	 <p class="form-control-static outputDate"><span>${outplan.goodsId }</span></p>
												    </div>
												  </div>
												  <div class="form-group">
												    <label for="" class="col-sm-2 control-label labelFont">产出日期</label>
												    <div class="col-sm-10">
												    	 <p class="form-control-static outputDate"><span><fmt:formatDate value="${outplan.startTime }" pattern="yyyy-MM-dd " ></fmt:formatDate></span>至<span><fmt:formatDate value="${outplan.endTime }" pattern="yyyy-MM-dd " ></fmt:formatDate></span></p>
												    </div>
												  </div>
												  <div class="form-group">
												    <label for="" class="col-sm-2 control-label labelFont">产出重量</label>
												    <div class="col-sm-10">
												    	 <p class="form-control-static outputDate"><span>${outplan.output }${outplan.unitId }</p>
												    </div>
												  </div>
											  </form>
											  <div class="btnBox">
												<button type="submit" class="btn btn-success">编辑</button>
												<button type="submit" class="btn btn-default">删除</button>
											</div>
										</div>
										</c:forEach>
							  	</div>
						  </div>
					  </div>
				  </div>

			
		</div>
	</div>
</body>
</html>