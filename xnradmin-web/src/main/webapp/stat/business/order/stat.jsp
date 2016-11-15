<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/stat/business/statOrderRecordInfo.action";
	String lookup = basePath + "page/staff/lookup.action?queryOrgid=16";
	String businessLookup = basePath + "page/staff/lookup.action?queryOrgid=15";
	String detailInfo = basePath + "page/stat/business/detailTotalInfo.action";
	String outputExcel = basePath+"page/stat/business/outputOrderDetailTotalExcel.action";
	String viewOrderInfo = basePath+"page/business/admin/orderrecord/info.action";
	
	request.setAttribute("action",action);
	request.setAttribute("lookup", lookup);
	request.setAttribute("businessLookup", businessLookup);
	request.setAttribute("detailInfo", detailInfo);
	request.setAttribute("outputExcel", outputExcel);
	request.setAttribute("viewOrderInfo", viewOrderInfo);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="query.createStartTime" value="${query.createStartTime}" />
		<input type="hidden" name="query.createEndTime" value="${query.createEndTime}" />
		<input type="hidden" name="leaderStaff.id" value="${leaderStaff.id}" />
		<input type="hidden" name="leaderStaff.staffName" value="${leaderStaff.staffName}" />
		
		<input type="hidden" name="userRealName" value="${userRealName}" />
		<input type="hidden" name="paymentStatus" value="${paymentStatus}" />
		<input type="hidden" name="paymentProvider" value="${paymentProvider}" />
		<input type="hidden" name="deliveryStatus" value="${deliveryStatus}" />
		<input type="hidden" name="operateStatus" value="${operateStatus}" />
		<input type="hidden" name="query.productName" value="${query.productName}" />
		<input type="hidden" name="query.businessOrderRecord.areaName" value="${query.businessOrderRecord.areaName}" />
		
		
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
					用户手机号：
					<input type="text" name="query.businessOrderRecord.clientUserMobile" value="${query.businessOrderRecord.clientUserMobile}"/>
				</td>
				<td>
					收货人手机号：
					<input type="text" name="query.businessOrderRecord.userRealMobile" value="${query.businessOrderRecord.userRealMobile}"/>
				</td>
				<td>
					收货人名称：
					<input type="text" name="query.businessOrderRecord.userRealName" value="${query.businessOrderRecord.userRealName}"/>
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
					<label>订单状态：</label>
					<select class="combox" name="operateStatus">
					<c:if test="${operateStatusList!=null}">
						<option value="" selected>选择</option>
						<c:choose>
							<c:when test="${operateStatus==null || operateStatus=='0'}">
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
					<input type="text" name="query.createStartTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd 00:00:00" value="${query.createStartTime}" class="date" readonly="true" />
					到
					<input type="text" name="query.createEndTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd 23:59:59" value="${query.createEndTime}" class="date" readonly="true" />
				</td>
				<td>					
					所属商务经理		
					<input id="inputOrg1" name="leaderStaff.id" value="${leaderStaff.id}" type="hidden"/>
					<input name="leaderStaff.staffName" size=15 value="${leaderStaff.staffName}" readonly="true" type="text"/>
					
				</td>
				<td><a class="btnLook" href="${lookup}" lookupGroup="leaderStaff">查找带回</a></td>
			</tr>
		</table>
		<table>
			<tr>
				<td>					
				商户查询(可模糊):	
				<input id="inputOrg1" name="query.businessOrderRecord.clientUserName" value="${query.businessOrderRecord.clientUserName}" type="text"/>
				</td>
				<td>
				包含的商品名称(可模糊):<input name="query.productName" size=15 value="${query.productName}" type="text"/>
				</td>
				<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				地区查询(可模糊):<input type="text" name="query.businessOrderRecord.areaName" value="${query.businessOrderRecord.areaName}"/>
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
			<li><a class="add" href="${outputExcel}" target="dwzExport" targetType="navTab"  rel="menu_add"><span>导出所有配送单</span></a></li>			
			<li class="line">line</li>			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="215">
		<thead>
			<tr>
				<th width="45">支付状态</th>
				<th width="45">订单状态</th>
				<th width="45">派送状态</th>
				<th width="125">商户</th>
				<th width="45">客户经理</th>
				<th width="40">收货人</th>
				<th width="100">手机号</th>
				<th width="55">支付渠道</th>
				<th width="55">用户注册时间</th>
				<th width="100">下单时间</th>
				<th width="45">订单总价</th>
				<th width="45">订单总数</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr>						
						<td>${loop.businessOrderRecord.paymentStatusName}</td>
						<td>${loop.businessOrderRecord.operateStatusName}</td>
						<td>${loop.businessOrderRecord.deliveryStatusName}</td>
						<td>${loop.staff.staffName}</td>
						<td>${loop.leaderStaff.staffName}</td>
						<td>${loop.businessOrderRecord.userRealName}</td>
						<td>${loop.businessOrderRecord.userRealMobile}</td>
						<td>${loop.businessOrderRecord.paymentProviderName}</td>
						<td><fmt:formatDate value="${loop.staff.createTime}" pattern="yyyy-MM-dd"/></td>
						<td>${loop.businessOrderRecord.createTime}</td>
						<td>${loop.orderSumPrice}</td>
						<td>${loop.orderCount}</td>
						<td>					
							<a title="查看详情" height="700" width="1000" rel="statDetailTotal" target="navTab" href="${detailInfo}?query.serno=${loop.serno}&query.createStartTime=${query.createStartTime}&query.createEndTime=${query.createEndTime}&query.staff.id=${loop.businessOrderRecord.staffId}" class="btnLook">编辑</a>
							
							<a title="查看该用户订单详情" rel="viewOrderInfoList" target="navTab" href="${viewOrderInfo}?userRealMobile=${loop.businessOrderRecord.userRealMobile}&createStartTime=${query.createStartTime}&createEndTime=${query.createEndTime}" class="btnView">查看该用户订单详情</a>
							
							
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
