<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/stat/business/purchaser.action";
	String lookup = basePath + "page/staff/lookup.action?queryOrgid=16";
	String purchaserlookup = basePath + "page/staff/lookup.action?queryOrgid=15";
	String categoryLookup = basePath + "page/business/admin/commodity/category/lookup.action";
	String goodsLookup = basePath + "page/business/admin/commodity/goods/lookup.action";
	request.setAttribute("action", action);
	request.setAttribute("lookup",lookup);
	request.setAttribute("purchaserlookup",purchaserlookup);
	request.setAttribute("categoryLookup",categoryLookup);
	request.setAttribute("goodsLookup",goodsLookup);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="orderNo" value="${orderNo}" />
		<input type="hidden" name="orderRecordId" value="${orderRecordId}" />
		<input type="hidden" name="dishName" value="${dishName}"/>
		<input type="hidden" name="property"  value="${property}" /> 
		<input type="hidden" name="unitPrice"  value="${unitPrice}" />
		<input type="hidden" name="orderStartTime" value="${orderStartTime}" />
		<input type="hidden" name="orderEndTime" value="${orderEndTime}" />
		<input type="hidden" name="dishCount" value="${dishCount}" />
		<input type="hidden" name="totalPrice" value="${totalPrice}" />
		<input type="hidden" name="paymentStatus" value="${paymentStatus}" />
		<input type="hidden" name="deliveryStatus" value="${deliveryStatus}" />
		<input type="hidden" name="operateStatus" value="${operateStatus}" />
		<input type="hidden" name="customer" value="${customer}" />
		<input type="hidden" name="mobile" value="${mobile}" />
		<input type="hidden" name="address" value="${address}" />
		<input type="hidden" name="code" value="${code}" />
		<input type="hidden" name="createStaffId" value="${createStaffId}" />
		<input type="hidden" name="createStartTime" value="${createStartTime}" />
		<input type="hidden" name="createEndTime" value="${createEndTime}" />
		<input type="hidden" name="staffCreateStartTime" value="${staffCreateStartTime}" />
		<input type="hidden" name="staffCreateEndTime" value="${staffCreateEndTime}" />
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
					订单日期（起始结束时间都要选）：从
					<input type="text" name="createStartTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd" value="${createStartTime}" class="date" readonly="true" />
					到
					<input type="text" name="createEndTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd" value="${createEndTime}" class="date" readonly="true" />
				</td>
			
				<td>
					支付状态：
				</td>
				<td>
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
					配送状态：
				</td>
				<td>
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
					订单状态：
				</td>
				<td>
					<select class="combox" name="operateStatus">
					<c:if test="${operateStatusList!=null}">
					<option value="" selected>选择</option>
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
		<table class="searchContent">
			<tr>
				<td>
					商户注册时间（可选）：从
					<input type="text" name="staffCreateStartTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${staffCreateStartTime}" class="date" readonly="true" />
					到
					<input type="text" name="staffCreateEndTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${staffCreateEndTime}" class="date" readonly="true" />
				</td>	
				<td>					
					选择商务经理：
					<input id="inputOrg1" name="leaderStaff.id" value="${leaderStaff.id}" type="hidden"/>
					<input name="leaderStaff.staffName" size=15 value="${leaderStaff.staffName}" readonly="true" type="text"/>
				</td>
				<td><a class="btnLook" href="${lookup}" lookupGroup="leaderStaff">查找带回</a></td>
				<td>					
					选择商户：
					<input id="inputOrg1" name="staff.id" value="${staff.id}" type="hidden"/>
					<input name="staff.staffName" size=15 value="${staff.staffName}" readonly="true" type="text"/>
				</td>
				<td><a class="btnLook" href="${purchaserlookup}" lookupGroup="staff">查找带回</a></td>
			</tr>
		</table>
				<table class="searchContent">
			<tr>
				<td>					
					选择商品：
					<input id="inputOrg1" name="businessGoods.id" value="${businessGoods.id}" type="hidden"/>
					<input name="businessGoods.goodsName" size=15 value="${businessGoods.goodsName}" readonly="true" type="text"/>
				</td>
				<td><a class="btnLook" href="${goodsLookup}" lookupGroup="businessGoods">查找带回</a></td>
				<td>					
					选择商品分类：
					<input id="inputOrg1" name="businessCategory.id" value="${businessCategory.id}" type="hidden"/>
					<input name="businessCategory.categoryName" size=15 value="${businessCategory.categoryName}" readonly="true" type="text"/>
				</td>
				<td><a class="btnLook" href="${categoryLookup}" lookupGroup="businessCategory">查找带回</a></td>
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
	<table class="table" width="100%" layoutH="218">
		<thead>
			<tr>
				<th width="100">商户名称</th>
				<th width="100">商品</th>
				<th width="100">商品分类</th>						
				<th width="100">客户经理</th>
				<th width="100">下单量</th>
				<th width="100">订单总金额</th>
				<th width="100">平均金额</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${priceList!=null}">
				<c:forEach items="${priceList}" var="loop">
					<tr>
						<td width="100" align="center">${loop[0]}</td>
						<td width="100" align="center">${loop[1]}</td>
						<td width="100" align="center">${loop[2]}</td>
						<td width="100" align="center">${loop[3]}</td>
						<td width="100" align="center">${loop[4]}</td>
						<td width="100" align="center">${loop[5]}</td>
						<td width="100" align="center">${loop[6]}</td>
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
