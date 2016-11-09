<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String action = basePath + "page/order/stopOrder.action";
	String view = basePath + "page/order/stopView.action";
	String washerLookup = basePath
			+ "page/stat/agent/washerLookup.action";
	request.setAttribute("washerLookup", washerLookup);
	request.setAttribute("action", action);
	request.setAttribute("view", view);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="carWashOrderRecordId" value="${carWashOrderRecordId}" />
		<input type="hidden" name="washerName" value="${washerName}" />
		<input type="hidden" name="userName" value="${userName}" />
		<input type="hidden" name="userMobile" value="${userMobile}" />
		<input type="hidden" name="orderStartTime" value="${orderStartTime}" />
		<input type="hidden" name="orderEndTime" value="${orderEndTime}" />
		<input type="hidden" name="pageNum" value="${pageNum}" /> <input
		type="hidden" name="numPerPage" value="${numPerPage}" /> <input
		type="hidden" name="orderField" value="${orderField}" /> <input
		type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		class="pageForm required-validate" action="" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>订单编号 <input type="text" name="carWashOrderRecordId"
						class="digits textInput" value="${carWashOrderRecordId}" />
					<td>技师 <input name="washerStaffVO.userid" readonly="readonly"
						type="hidden" value="${washerId}" /> <input readonly="readonly"
						name="washerStaffVO.staffName" value="${washerName}" type="text" />
					</td>
					<td><a class="btnLook" href="${washerLookup}"
						lookupGroup="washerStaffVO">查找带回</a></td>
					<td>用户名称 <input type="text" name="userName"
						value="${userName}" />
					<td>用户手机号码 <input type="text" name="userMobile"
						value="${userMobile}" />
				</tr>
			</table>
			<table class="searchContent">
				<tr>
				<td>订单日期（起始结束时间都要选）：从 <input type="text" name="orderStartTime"
						yearstart="-80" yearend="1" dateFmt="yyyy-MM-dd HH:mm:ss"
						value="${orderStartTime}" class="date" readonly="true" /> 到 <input
						type="text" name="orderEndTime" yearstart="-80" yearend="1"
						dateFmt="yyyy-MM-dd HH:mm:ss" value="${orderEndTime}" class="date"
						readonly="true" />
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">搜索</button>
							</div>
						</div></li>

				</ul>
			</div>
		</div>
	</form>
</div>


<div class="pageContent">
	<!--  
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${add}" target="dialog"
				rel="washcarproduct_add"><span>添加产品</span></a></li>
			<li><a class="edit"
				href="${modify}?washCarProductid={sid_washcarproductid}"
				target="navTab" warn="请选择一个记录"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete"
				href="${del}?washCarProductid={sid_washcarproductid}&del=1"
				target="ajaxTodo" title="删除某个产品"><span>删除</span></a></li>
		</ul>
	</div>
	-->
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="100">订单编号</th>
				<th width="100">技师</th>
				<th width="100">用户名</th>
				<th width="100">用户手机</th>
				<th width="100">用户注册时间</th>
				<th width="100">订单状态</th>
				<th width="100">订单时间</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr>
						<td>${loop.carWashOrderRecordId}</td>
						<td>${loop.washerName}</td>
						<td>${loop.userName}</td>
						<td>${loop.userMobile}</td>
						<td>${loop.userCreateTime}</td>
						<td>${loop.operateStatusName}</td>
						<td>${loop.orderTime}</td>

						<td><a title="完成" target="navTab"
							href="${view}?carWashOrderRecordId=${loop.carWashOrderRecordId}"
							class="btnEdit">完成</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="4">4</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select> <span>条，共${totalCount}条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}"></div>
	</div>
</div>
