<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/Common/Region/Description/batchLookup.action";
	request.setAttribute("action",action);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="provincename" value="${provincename}" />
		<input type="hidden" name="cityname" value="${cityname}" />
		<input type="hidden" name="areaname" value="${areaname}" />
		<input type="hidden" name="roadname" value="${roadname}" />
		<input type="hidden" name="communityname" value="${communityname}" />
		<input type="hidden" name="description" value="${description}" />
		<input type="hidden" name="totalCount" value="${totalCount}" />
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
					所属省份：
					<input class="textInput" name="provincename" value="" type="text">
				</td>
				<td>
					所属城市：
					<input class="textInput" name="cityname" value="" type="text">
				</td>
				<td>
					所属区县：
					<input class="textInput" name="areaname" value="" type="text">
				</td>
			</tr>
			<tr>
				<td>
					所属街路：
					<input class="textInput" name="roadname" value="" type="text">
				</td>
				<td>
					所属社区：
					<input class="textInput" name="communityname" value="" type="text">
				</td>
				<td>
					区域描述：
					<input class="textInput" name="description" value="" type="text">
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" multLookup="description" warn="请选择">选择带回</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" layoutH="136" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="100">区域描述ID</th>
				<th width="100">省份</th>	
				<th width="100">城市</th>	
				<th width="100">区/县</th>	
				<th width="100">街/路</th>	
				<th width="100">社区</th>	
				<th width="100">区域描述名称</th>
				<th width="22"><input type="checkbox" group="description" class="checkboxCtrl"></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr>
						<td>${loop.descriptionid}</td>
						<td>${loop.province}</td>
						<td>${loop.city}</td>
						<td>${loop.area}</td>
						<td>${loop.road}</td>
						<td>${loop.community}</td>
						<td>${loop.description}</td>
						<td>
							<input type="checkbox" name="description" value="{descriptionid:'${loop.descriptionid}',community:'${loop.community}', description:'${loop.province}${loop.city}${loop.area}${loop.road}${loop.community}${loop.description}'}"/>
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
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
				<option value="500">500</option>
				<option value="1000">1000</option>
				<option value="10000">10000</option>
			</select>			
			<span>条，共${totalCount}条</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="${totalCount}" numPerPage="${numPerPage}" pageNumShown="50" currentPage="${pageNum}"></div>
	</div>	
</div>
