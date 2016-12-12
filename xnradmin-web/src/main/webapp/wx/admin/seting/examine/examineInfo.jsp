<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="post" action="page/wx/farmer/saveAnthinfo.action"
	class="pageForm required-validate"
	onsubmit="return validateCallback(this, dialogAjaxDone)">
<div class="pageContent">
	<div class="pageFormContent" layoutH="98">
		<div class="divider"></div>
		<p>
			<label>姓名：</label>
			<input type="text"  class="login_input" readonly="readonly" value="${farmerExamine.farmerName }"/>
		</p>
		<p>
			<label>手机号码</label>
			<input type="text"  class="login_input" readonly="readonly" value="${farmerExamine.farmerTel }"/>
		</p>
		<p>
			<label>合同号：</label>
			<input type="text"  class="login_input" readonly="readonly" value="${farmerExamine.contractNumber}"/>
		</p>
		<c:if test="${query.status==2}">
			<p>
				<label>拒绝理由：</label>
				<input type="text"  class="login_input" readonly="readonly" value="${query.remarks}"/>
			</p>
		</c:if>
	</div>
	<div class="formBar">
	</div>
	</div>
</form>
