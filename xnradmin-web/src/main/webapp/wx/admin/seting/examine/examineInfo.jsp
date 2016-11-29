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
	</div>
	<div class="formBar">
<!-- 		<label style="float: left"><input type="checkbox" -->
<!-- 			class="checkboxCtrl" group="types" />全选</label> -->
<!-- 		<ul> -->
<!-- 			<li><div class="button"> -->
<!-- 					<div class="buttonContent"> -->
<!-- 						<button type="button" class="checkboxCtrl" -->
<!-- 							group="types" selectType="invert">反选</button> -->
<!-- 					</div> -->
<!-- 				</div></li> -->
<!-- 			<li><div class="buttonActive"> -->
<!-- 					<div class="buttonContent"> -->
<!-- 						<button type="submit">提交</button> -->
<!-- 					</div> -->
<!-- 				</div></li> -->
<!-- 		</ul> -->
<!-- 	<button type="submit">提交</button> -->
	</div>
	</div>
</form>
