<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/business/admin/warehouse/wareHouse/lookup.action";
	
	request.setAttribute("action",action);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">				
		<input type="hidden" name="wareHouseName" value="${wareHouseName}" />
		<input type="hidden" name="wareHouseStatus" value="${wareHouseStatus}" />
		<input type="hidden" name="wareHouseAddress" value="${wareHouseAddress}" />
		<input type="hidden" name="wareHouseSerno" value="${wareHouseSerno}" />
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${action}" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					仓库名称：
					<input type="text" name="wareHouseName" value="${wareHouseName}"/>
				</td>
				<td>
					仓库名称：
					<input type="text" name="wareHouseAddress" value="${wareHouseAddress}"/>
				</td>
				<td>
					仓库状态：
				</td>
				<td>
				<select class="combox" name="wareHouseStatus">
					<c:if test="${wareHouseStatusList!=null}">
					<option value="" selected>选择</option>
						<c:forEach items="${wareHouseStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==wareHouseStatus}">
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
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="100">仓库名称</th>
				<th width="100">仓库地址</th>
				<th width="100">仓库状态</th>
				<th width="100">仓库序号</th>
				<th width="100">建立人员</th>
				<th width="100">建立时间</th>
				<th width="100">修改人员</th>
				<th width="100">修改时间</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_wareHouseId" rel="${loop.businessWareHouse.id}">					
						<td>${loop.businessWareHouse.wareHouseName}</td>
						<td>${loop.businessWareHouse.wareHouseAddress}</td>
						<td>
						<c:if test="${wareHouseStatusList!=null}">
							<c:forEach items="${wareHouseStatusList}" var="statusList">
								<c:if test="${statusList.id==loop.businessWareHouse.wareHouseStatus}">
									${statusList.statusName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>${loop.businessWareHouse.wareHouseSerno}</td>
						<td>${loop.businessWareHouse.createTime}</td>
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffcreate">
								<c:if test="${staffcreate.userid==loop.businessWareHouse.createStaffId}">
									${staffcreate.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>${loop.businessWareHouse.modifyTime}</td>	
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffmodify">
								<c:if test="${staffmodify.userid==loop.businessWareHouse.modifyStaffId}">
									${staffmodify.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({id:'${loop.businessWareHouse.id}', 
							wareHouseName:'${loop.businessWareHouse.wareHouseName}'})" title="查找带回">选择</a>
						</td>
					</tr>				
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	
	<div class="panelBar">
		<div class="pages">
			<span>每页</span>						
			<select name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value})">
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
		<div class="pagination" targetType="dialog" totalCount="${totalCount}" numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}"></div>
	</div>	
</div>
