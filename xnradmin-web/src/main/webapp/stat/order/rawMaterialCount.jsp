<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/stat/order/rawMaterialCount.action";

	request.setAttribute("action", action);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="orderRecordId" value="${orderRecordId}" />
		<input type="hidden" name="orderNo" value="${orderNo}" />
		<input type="hidden" name="dishName" value="${dishName}"/>
		<input type="hidden" name="property"  value="${property}" /> 
		<input type="hidden" name="unitPrice"  value="${unitPrice}" />
		<input type="hidden" name="orderStartTime" value="${orderStartTime}" />
		<input type="hidden" name="orderEndTime" value="${orderEndTime}" />
		<input type="hidden" name="dishCount" value="${count}" />
		<input type="hidden" name="totalPrice" value="${totalPrice}" />
		<input type="hidden" name="payStatusId" value="${payStatusId}" />
		<input type="hidden" name="dispatchStatusId" value="${dispatchStatusId}" />
		<input type="hidden" name="orderStatusId" value="${orderStatusId}" />
		<input type="hidden" name="customer" value="${customer}" />
		<input type="hidden" name="mobile" value="${mobile}" />
		<input type="hidden" name="address" value="${address}" />
		<input type="hidden" name="code" value="${code}" />
		<input type="hidden" name="createStaffId" value="${createStaffId}" />
		<input type="hidden" name="createStartTime" value="${createStartTime}" />
		<input type="hidden" name="createEndTime" value="${createEndTime}" />
		<input type="hidden" name="materialName" value="${materialName}" />
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
					 菜品名
					<input type="text" name="dishName" value="${dishName}"/>
				</td>
				<td>
					 原料名
					<input type="text" name="materialName" value="${materialName}"/>
				</td>
				<td>
					 区域名称（可模糊）
					<input type="text" name="address" value="${address}"/>
				</td>
				<td>
					 手机号
					<input type="text" name="mobile" value="${mobile}"/>
				</td>
				<td>
					 收货人
					<input type="text" name="customer" value="${customer}"/>
				</td>
			</tr>
		</table>
		<table class="searchContent">
			<tr>
				<td>
					订单日期（起始结束时间都要选）：从
					<input type="text" name="orderStartTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${orderStartTime}" class="date" readonly="true" />
					到
					<input type="text" name="orderEndTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${orderEndTime}" class="date" readonly="true" />
				</td>
			
				<td>
					支付状态：
				</td>
				<td>
					<select class="combox" name="payStatusId">
					<c:if test="${payStatusList!=null}">
					<option value="" selected>选择</option>
						<c:forEach items="${payStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==payStatusId}">
									<option value=${loop.id} selected>${loop.statusCode}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusCode}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
					</select>
				</td>
				<td>
					配送状态：
				</td>
				<td>
					<select class="combox" name="dispatchStatusId">
					<c:if test="${dispatchStatusList!=null}">
					<option value="" selected>选择</option>
						<c:forEach items="${dispatchStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==dispatchStatusId}">
									<option value=${loop.id} selected>${loop.statusCode}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusCode}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
					</select>
				</td>
				<td>
					订单状态：
				</td>
				<td>
					<select class="combox" name="orderStatusId">
					<c:if test="${orderStatusList!=null}">
					<option value="" selected>选择</option>
						<c:forEach items="${orderStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==orderStatusId}">
									<option value=${loop.id} selected>${loop.statusCode}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusCode}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
					</select>
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
		</ul>
	</div>
	<table class="table" width="100%" layoutH="158">
		<thead>
			<tr>
				<th width="100" align="center">原料名称</th>
				<th width="100" align="center">原料数量</th>
				<th width="100" align="center">数量单位</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${contentList!=null}">
				<c:forEach items="${contentList}" var="loop">
					<tr>
						<td width="100" align="center">${loop[0]}</td>
						<td width="100" align="center">${loop[2]}</td>
						<td width="100" align="center">${loop[1]}</td>
					<tr>
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
