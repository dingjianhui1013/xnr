<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/Client/ClientUserRegionInfo/info.action";
	
	request.setAttribute("action",action);

%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
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
					用户ID
					<input type="text" name="clientUserInfoId" />
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
			<li></li>				
			<li></li>
			<li class="line">line</li>			
			<li></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>							
				<th width="70">ID</th>
				<th width="100">用户号码</th>
				<th width="100">省份</th>
				<th width="100">地市</th>
				<th width="100">区县</th>
				<th width="100">街路</th>
				<th width="100">小区</th>
				<th width="100">区域描述</th>
				<th width="100">楼号</th>
				<th width="100">车位</th>
				<th width="100">标志建筑</th>
				<th width="100">综合描述</th>
				<th width="100">建立时间</th>
				<th width="100">修改时间</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_clientuserregioninfoid" rel="${loop.clientUserRegionInfoId}">	
						<td>${loop.clientUserRegionInfoId}</td>				
						<td>${loop.mobile}</td>
						<td>${loop.provinceName}</td>					
						<td>${loop.cityName}</td>
						<td>${loop.areaName}</td>
						<td>${loop.roadName}</td>
						<td>${loop.communityName}</td>
						<td>${loop.descriptionDesc}</td>
						<td>${loop.buildingNumber}</td>
						<td>${loop.parkingSpace}</td>
						<td>${loop.landmarkBuilding}</td>
						<td>${loop.regionOrderDescription}</td>
						<td>${loop.clientUserRegionInfoCreateTime}</td>	
						<td>${loop.clientUserRegionInfoModifyTime}</td>
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
