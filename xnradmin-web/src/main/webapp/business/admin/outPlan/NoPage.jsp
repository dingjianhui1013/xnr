<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="contentTitle">拒绝通过</h2>
<form method="post" action="page/wx/outplan/saveExamineNo.action"
	class="pageForm required-validate"
	onsubmit="return validateCallback(this, dialogAjaxDone)">
	<input name="examineNoId" value="${examineNoId}">
	拒绝理由：<input name="remarks">
	<div class="buttonContent">
		<button type="submit">提交</button>
	</div>
</form>
