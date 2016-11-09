<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String action = basePath + "page/stat/recharge/rechargeDetailed.action";

	request.setAttribute("action", action);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
	<input type="hidden" name="totalCount" value="${totalCount}" /> <input
		type="hidden" name="pageNum" value="${pageNum}" /> <input
		type="hidden" name="numPerPage" value="${numPerPage}" /> <input
		type="hidden" name="orderField" value="${orderField}" /> <input
		type="hidden" name="userRechargeConsumeRecordid"
		value="${userRechargeConsumeRecordid}" />
</form>


<div class="pageContent">
	<table class="table" layoutH="1" targetType="dialog" width="100%">
		<tbody>
			<c:if test="${urcr!=null}">
				<tr>
					<td>充值单ID</td>
					<td>${urcr.id}</td>
				</tr>
				<tr>
					<td>银行流水号</td>
					<td>${urcr.serNo}</td>
				</tr>
				<tr>
					<td>用户手机号</td>
					<td>${urcr.userMobile}</td>
				</tr>
				<tr>
					<td>支付状态</td>
					<td>${urcr.paymentStatusName}</td>
				</tr>
				<tr>
					<td>支付提供者</td>
					<td>${urcr.paymentProviderName}</td>
				</tr>
				<tr>
					<td>用户注册时间</td>
					<td>${cui.createTime}</td>
				</tr>
				<tr>
					<td>充值时间</td>
					<td>${urcr.rechargeTime}</td>
				</tr>
				<tr>
					<td>产品名称</td>
					<td>${urcr.productName}</td>
				</tr>
				<tr>
					<td>产品类型</td>
					<td>${urcr.productTypeName}</td>
				</tr>
				<tr>
					<td>规则名称</td>
					<td>${urcr.ruleName}</td>
				</tr>
				<tr>
					<td>规则类型</td>
					<td>${urcr.ruleTypeName}</td>
				</tr>
				<tr>
					<td>充值金额</td>
					<td>${urcr.addRechargeMoney}</td>
				</tr>
				<tr>
					<td>支付金额</td>
					<td>${urcr.money}</td>
				</tr>
				<tr>
					<td>产品折扣</td>
					<td>${urcr.discount}</td>
				</tr>
				<tr>
					<td>金额类型</td>
					<td>${urcr.moneyTypeName}</td>
				</tr>
				<tr>
					<td>用户所属小区</td>
					<td>${regionDescription.communityName}</td>
				</tr>
			</c:if>
		</tbody>
	</table>
</div>
