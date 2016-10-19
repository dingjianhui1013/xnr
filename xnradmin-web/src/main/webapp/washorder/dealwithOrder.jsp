<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String action = basePath + "page/order/dealwithOrder.action";
	String agentLookup = basePath
			+ "page/stat/agent/agentLookup.action";
	String washerLookup = basePath
			+ "page/stat/agent/washerLookup.action";
	String orderDetailed=basePath + "page/order/dealwithOrderView.action";
	request.setAttribute("action", action);
	request.setAttribute("agentLookup", agentLookup);
	request.setAttribute("washerLookup", washerLookup);
	request.setAttribute("orderDetailed", orderDetailed);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="carWashOrderRecordId" value="${carWashOrderRecordId}" />
		<input type="hidden" name="regionDescription" value="${regionDescription}"/>
		<input type="hidden" name="agentStaffVO.userid"  value="${agentStaffVO.userid}" /> 
		<input type="hidden" name="washerStaffVO.userid"  value="${washerStaffVO.userid}" />
		<input type="hidden" name="orderStartTime" value="${orderStartTime}" />
		<input type="hidden" name="orderEndTime" value="${orderEndTime}" />
		<input type="hidden" name="operateStatus" value="${operateStatus}" />
		<input type="hidden" name="userMobile" value="${userMobile}" />
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>订单号 <input type="text" name="carWashOrderRecordId" />
					</td>
					<td>区域名称（可模糊） <input type="text" name="regionDescription" />
					</td>
					<td>订单日期（起始结束时间都要选）：从 <input type="text" name="orderStartTime"
						yearstart="-80" yearend="1" dateFmt="yyyy-MM-dd HH:mm:ss"
						value="${orderStartTime}" class="date" readonly="true" /> 到 <input
						type="text" name="orderEndTime" yearstart="-80" yearend="1"
						dateFmt="yyyy-MM-dd HH:mm:ss" value="${orderEndTime}" class="date"
						readonly="true" />
					</td>
				</tr>
			</table>
			<table class="searchContent">
				<tr>

					<c:choose>
						<c:when test="${organizationId==5}">
							<td>技师 <input name="washerStaffVO.userid"
								readonly="readonly" type="hidden" value="${washerId}" /> <input
								readonly="readonly" name="washerStaffVO.staffName"
								value="${washerName}" type="text" />
							</td>
							<td><a class="btnLook" href="${washerLookup}"
								lookupGroup="washerStaffVO">查找带回</a></td>

						</c:when>
						<c:otherwise>

							<td>合作方 <input id="seller_id" name="agentStaffVO.userid"
								readonly="readonly" type="hidden" value="${selleId}" /> <input
								readonly="readonly" name="agentStaffVO.staffName"
								value="${sellerName}" type="text" />
							</td>
							<td><a class="btnLook" href="${agentLookup}"
								lookupGroup="agentStaffVO">查找带回</a></td>

							<td>技师 <input id="washer_id" name="washerStaffVO.userid"
								readonly="readonly" type="hidden" value="${washerId}" /> <input
								readonly="readonly" name="washerStaffVO.staffName"
								value="${washerName}" type="text" />
							</td>
							<td><a class="btnLook"
								href="${washerLookup}?sellerId={seller_id}"
								lookupGroup="washerStaffVO">查找带回</a></td>
						</c:otherwise>
					</c:choose>
					
					<td>派单状态： <select name="operateStatus">
							<option value="" selected>选择</option>
							<option value=519>待派订单</option>
							<option value=520>已派订单</option>
							<option value=521>终止订单</option>
							<option value=522>接受订单</option>
							<option value=523>拒绝订单</option>
							<option value=524>完成订单</option>
					</select>
					</td>
					<td>用户手机号 <input type="text" name="userMobile" />
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
	<div class="panelBar">
		<ul class="toolBar">						
		</ul>
	</div>
	<table class="table" width="100%" layoutH="158">
		<thead>
			<tr>
				<th width="100">订单编号</th>
				<th width="100">技师</th>
				<th width="100">用户名</th>
				<th width="100">用户手机</th>
				<th width="100">订单状态</th>
				<th width="100">订单时间</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_carWashOrderRecordId" rel="${loop.carWashOrderRecordId}">	
						<td>${loop.carWashOrderRecordId}</td>
						<td>${loop.washerName}</td>
						<td>${loop.userName}</td>
						<td>${loop.userMobile}</td>
						<td>${loop.operateStatusName}</td>
						<td>${loop.orderTime}</td>
						<td>					
							<a title="详细信息" rel="orderInfo" target="navTab" href="${orderDetailed}?carWashOrderRecordId=${loop.carWashOrderRecordId}" class="btnEdit">编辑</a>
						</td>	
					</tr>				
				</c:forEach>
			</c:if>			
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="4">4</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>			
			<span>条，共${totalCount}条</span>
		</div>		
		<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}"></div>
	</div>
</div>
