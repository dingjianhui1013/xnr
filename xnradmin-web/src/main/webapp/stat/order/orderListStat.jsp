<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"/page/stat/order/orderListStat.action";
	String detailed = basePath+"/page/stat/order/detailed.action";
	String orderListExcel = basePath+"/page/stat/order/orderListExcel.action";
	request.setAttribute("action",action);
	request.setAttribute("detailed",detailed);
	request.setAttribute("orderListExcel",orderListExcel);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="orderNo" value="${orderNo}" />
		<input type="hidden" name="orderRecordId" value="${orderRecordId}" />
		<input type="hidden" name="clientUserMobile" value="${clientUserMobile}" />
		<input type="hidden" name="userRealMobile" value="${userRealMobile}" />
		<input type="hidden" name="userRealName" value="${userRealName}" />
		<input type="hidden" name="paymentStatus" value="${paymentStatus}" />
		<input type="hidden" name="paymentProvider" value="${paymentProvider}" />
		<input type="hidden" name="deliveryStatus" value="${deliveryStatus}" />
		<input type="hidden" name="operateStatus" value="${operateStatus}" />
		<input type="hidden" name="createStartTime" value="${createStartTime}" />
		<input type="hidden" name="createEndTime" value="${createEndTime}" />
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
				<td>
					订单ID：
					<input type="text" name="orderRecordId" value="${orderRecordId}"/>
				</td>
				<td>
					订单编号：
					<input type="text" name="orderNo" value="${orderNo}"/>
				</td>
				<td>
					用户手机号：
					<input type="text" name="clientUserMobile" value="${clientUserMobile}"/>
				</td>
				<td>
					收货人手机号：
					<input type="text" name="userRealMobile" value="${userRealMobile}"/>
				</td>
				<td>
					收货人名称：
					<input type="text" name="userRealName" value="${userRealName}"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>支付状态：</label>
					<select class="combox" name="paymentStatus">
					<c:if test="${paymentStatusList!=null}">
						<option value="" selected>选择</option>
						<c:forEach items="${paymentStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==paymentStatus}">
									<option value=${loop.id} selected>${loop.statusName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusName}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
					</select>
				</td>
				<td>
					<label>支付渠道：</label>
					<select class="combox" name="paymentProvider">
					<c:if test="${paymentProviderList!=null}">
						<option value="" selected>选择</option>
						<c:forEach items="${paymentProviderList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==paymentProvider}">
									<option value=${loop.id} selected>${loop.statusName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusName}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
					</select>
				</td>
				<td>
					<label>派送状态：</label>
					<select class="combox" name="deliveryStatus">
					<c:if test="${deliveryStatusList!=null}">
						<option value="" selected>选择</option>
						<c:forEach items="${deliveryStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==deliveryStatus}">
									<option value=${loop.id} selected>${loop.statusName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusName}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
					</select>
				</td>
				<td>
					<label>订单状态：${operateStatus}</label>
					<select class="combox" name="operateStatus">
					<c:if test="${operateStatusList!=null}">
						<option value="" selected>选择</option>
						<c:choose>
							<c:when test="${operateStatus=='0'}">
								<option value='0' selected>全部</option>
   							</c:when>
   							<c:otherwise>
   								<option value='0'>全部</option>
   							</c:otherwise>
						</c:choose>
						<c:forEach items="${operateStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==operateStatus}">
									<option value=${loop.id} selected>${loop.statusName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusName}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
					</select>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td>
					订单日期（起始结束时间都要选）：从
					<input type="text" name="createStartTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${createStartTime}" class="date" readonly="true" />
					到
					<input type="text" name="createEndTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${createEndTime}" class="date" readonly="true" />
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">	
			<li><a class="icon" href="/themes/mall/excel/orderListExcel.xls" target="dwzExport" targetType="navTab" title="要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<li><a class="icon" href="/themes/mall/excel/orderListExcel2.xls" target="dwzExport" targetType="navTab" title="要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="198">
		<thead>
			<tr>
				<th width="100">订单ID</th>
				<th width="100">订单编号</th>
				<th width="100">支付状态</th>
				<th width="100">订单状态</th>
				<th width="100">派送状态</th>
				<th width="100">收货人</th>
				<th width="100">手机号</th>
				<th width="100">派送时间</th>
				<th width="100">支付渠道</th>
				<th width="100">下单时间</th>
				<th width="100">订单总价</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_orderRecordId" rel="${loop.orderRecordId}">						
						<td>${loop.orderRecordId}</td>
						<td>${loop.orderNo}</td>
						<td>${loop.orderRecordPaymentStatusName}</td>
						<td>${loop.orderRecordOperateStatusName}</td>
						<td>${loop.orderRecordDeliveryStatusName}</td>
						<td>${loop.orderRecordUserRealName}</td>
						<td>${loop.orderRecordUserRealMobile}</td>
						<td>${loop.orderRecordUserRealTime}</td>
						<td>${loop.orderRecordPaymentProviderName}</td>
						<td>${loop.orderRecordCreateTime}</td>
						<td>${loop.orderRecordTotalPrice}</td>			
						<td>					
							<a title="编辑" target="navTab" href="${detailed}?orderRecordId=${loop.orderRecordId}" class="btnEdit">编辑</a>
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
