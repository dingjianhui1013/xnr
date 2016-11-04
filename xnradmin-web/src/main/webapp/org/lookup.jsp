<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/org/lookup.action";	
	

	request.setAttribute("action",action);
	
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="porgid" value="${porgid}" />
		<input type="hidden" name="orgName_save" value="${orgName}" />
		
		<input type="hidden" name="totalCount" value="${totalCount}" />		
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">		
			<li>
				<label>组织名称:</label>
				<input class="textInput" name="orgName" type="text">
				<span class="info">(可模糊)</span>
			</li>			
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>				
			<th width="70" orderField="id" class="${idsort}">编号</th>
				<th width="100">组织名称</th>				
				<th width="100">上级组织 </th>
				<th width="100">组织类型 </th>							
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_orgid" rel="${loop.orgid}">								
						<td>${loop.orgid}</td>
						<td>${loop.orgname}</td>						
						<td>${loop.porgname}</td>
						<td>${loop.type}</td>											
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({id:'${loop.orgid}', organizationName:'${loop.orgname}', orgNum:'${loop.orgid}'})" title="查找带回">选择</a>
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
