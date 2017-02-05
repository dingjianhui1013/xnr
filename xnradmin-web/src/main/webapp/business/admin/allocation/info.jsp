<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/business/admin/allocation/info.action";
	String add = basePath+"page/business/admin/allocation/toAllocation.action";
	String modify = basePath+"page/business/admin/allocation/modifyInfo.action";
	String del = basePath+"page/business/admin/allocation/delInfo.action";
	String businessLookup = basePath + "page/staff/lookup.action?queryOrgid=16";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
	request.setAttribute("businessLookup", businessLookup);
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
		<input type="hidden" name="leaderStaff.id" value="${leaderStaff.id}" />
		<input type="hidden" name="leaderStaff.staffName" value="${leaderStaff.staffName}" />
		<input type="hidden" name="staff.staffName" value="${staff.staffName}" />
		<input type="hidden" name="productName" value="${productName}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					分配人：
					<input type="text" name="currentStaff.staffName" value="${currentStaff.staffName}"/>
				</td>
				<td>
					<label>分配状态：</label>
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
			</tr>
		</table>
		<table>
			<tr>
				<td>
					分配日期（起始结束时间都要选）：从
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
			<li>
			<a class="add" href="${add}?allocationId=${loop.allocationData.id}&pageType=3" target="navTab" title="新增"><span>新增分配</span></a>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="225">
		<thead>
			<tr>
				<th width="45">分配ID</th>
				<th width="40">分配人</th>
				<th width="100">分配时间</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${allocationVo!=null}">
				<c:forEach items="${allocationVo}" var="loop">
					<tr target="sid_orderRecordId" rel="${loop.allocationData.id}">						
						<td>${loop.allocationData.id}</td>
						<td>${loop.staff.loginId}</td>	
						<td>${loop.allocationData.allocationTime}</td>
						<td>					
							<a title="查看" target="navTab" href="${modify}?allocationId=${loop.allocationData.id}&pageType=1" class="btnLook">查看</a>
							<a title="编辑" target="navTab" href="${modify}?allocationId=${loop.allocationData.id}&pageType=2" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="${del}?allocationId=${loop.allocationData.id}" class="btnDel" title="确认删除？" >删除</a>
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
