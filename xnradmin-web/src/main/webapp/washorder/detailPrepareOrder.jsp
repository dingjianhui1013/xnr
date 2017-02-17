<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String staffLookup = basePath + "page/order/staffLookup.action";
	String sendOrder = basePath + "page/order/sendOrder.action";

	request.setAttribute("staffLookup", staffLookup);
	request.setAttribute("sendOrder", sendOrder);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
	<form action="${sendOrder}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
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
			<label>人员指派:</label>
			 <input class="readonly"
				name="staffVO.userid" readonly="readonly" value="${washerId}"
				type="hidden" /> 
				 <input class="readonly"
				name="staffVO.mobile" readonly="readonly" value="${washerMobile}"
				type="hidden" /> 
				<input readonly="readonly"
				name="staffVO.staffName" value="${washerName}" type="text" class="required" /> <a
				class="btnLook" href="${staffLookup}"
				lookupGroup="staffVO">查找带回</a>
		</p>
			<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
		</form>
	</div>
</div>