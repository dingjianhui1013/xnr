<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="contentTitle">拒绝通过</h2>
<script type="text/javascript">
	
	function yz(){
		var v = $("#remarks").val();
		if(v==null||v==""){
			$("#yz").html("请输入拒绝理由").show();
		}else{
			$("#yz").html("").hide();
			$("#form").submit();
		}
	}
	function quxiao(){
		$("#yz").html("").hide();
	}

</script>
<form id="form" method="post" action="page/wx/farmer/saveExamineNO.action?farmerId=${farmerId}&status=${status}"
	class="pageForm required-validate"
	onsubmit="return validateCallback(this, dialogAjaxDone)">
	拒绝理由：<input id="remarks" name="remarks" onchange="quxiao()">
	<div  id="yz" style="display: none;color:red"></div>
	<div class="buttonContent">
		<button type="button" onclick="yz()">提交</button>
	</div>
</form>
