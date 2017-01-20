<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>农户产品展示</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/mobiscroll.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/mobiscroll_date.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/site.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/photoswipe.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/default-skin.css">

<script type="text/javascript" src="<%=path%>/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/mobiscroll_date.js"></script>
<script type="text/javascript" src="<%=path%>/js/mobiscroll.js"></script>
<script type="text/javascript" src="<%=path%>/js/common.js"></script>

<script src="<%=path%>/js/photoswipe.min.js"></script>
<script src="<%=path%>/js/photoswipe-ui-default.min.js"></script>
<script type="text/javascript">
    </script>
</head>
<body>
	<div class="wrapper">
		<div class="contentBox personalBox">
			<div class="uploadImgListBox">
				<form role="form">
					<h3 class="bigTit">农户姓名：${query.userName} </h3>
					<h3 class="bigTit">菜品名称：${goods.goodsName} </h3>
					<h3 class="bigTit">生长过程</h3>
					<div class="form-group">
						<div class="col-sm-10 uploadImgBox">
							<ul>
								<c:forEach items="${farmerImages}" var="farmerImages">
									<li>
										<h3>${farmerImages.date}</h3>
										<div>
											<img alt="" src="<%=path%>${farmerImages.url}" />
										</div>
										<c:if test="${farmerImages.remarks!=null || farmerImages.remarks!=''}">
											<h3>${farmerImages.remarks}</h3>
										</c:if>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
