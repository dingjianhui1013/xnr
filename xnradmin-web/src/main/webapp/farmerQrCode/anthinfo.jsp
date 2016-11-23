<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="contentTitle">修改二维码</h2>
<form method="post" action="page/farmerQrCode/saveAnthinfo.action"
	class="pageForm required-validate"
	onsubmit="return validateCallback(this, dialogAjaxDone)">
	<div class="pageContent">
	<input type="hidden" name="farmerQrCode.id" value="${farmerQrCode.id}"/>
	<input type="hidden" name="farmerQrCode.qrCodeUrl" value="${farmerQrCode.qrCodeUrl}"/>
	<input type="hidden" name="farmerQrCode.farmerId" value="${farmerQrCode.farmerId}"/>
	<input type="hidden" name="farmerQrCode.goodsId" value="${farmerQrCode.goodsId}"/>
		<div class="pageFormContent" layoutH="98">
			<div class="divider"></div>
			<label>二维码跳转地址：<input type="text" name="farmerQrCode.skipUrl" value="${farmerQrCode.skipUrl}" /></label>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</form>
