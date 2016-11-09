<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String staffLookup = basePath + "page/order/staffLookup.action";

	request.setAttribute("staffLookup", staffLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<form action="">
			<input name="carWashOrderRecordId" value="${vo.carWashOrderRecordId}"
				type="hidden" />
			<p>
				<label>订单号：</label> ${vo.carWashOrderRecordId}
			</p>
			<p>
				<label>车型：</label>${vo.carBrandName}${vo.carBrandModelsName}
			</p>
			<p>
				<label>车牌号：</label>${vo.licensePlateNumber}
			</p>
			<p>
				<label>车辆位于：</label>${vo.regionDescription}${vo.buildingNumber}
			</p>
			<p>
				<label>车位号码：</label>${vo.parkingSpace}
			</p>
			<p>
				<label>附近标志性建筑物： </label>${vo.landmarkBuilding}
			</p>
			<p>
				<label>订单状态：</label>${vo.operateStatusName}
			</p>
			<p>
				<label>订单时间：</label>${vo.orderTime}
			</p>
			<p>
				<label>用户ID：</label>${vo.userId}
			</p>
			<p>
				<label>用户名:</label>${vo.userName}
			</p>
			<p>
				<label>用户手机号:</label>${vo.userMobile}
			</p>
			<p>
				<label>派单对象:</label>${vo.sellerName}
			</p>
			<p>
				<label>技师:</label>${vo.washerName}
			</p>
			<p>
				<label>技师手机号:</label>${vo.washerMobile}
			</p>
		</form>
	</div>
</div>