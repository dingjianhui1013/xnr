<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/cus/conInfo.action";	
	String modify = basePath+"page/cus/conModifyInfo.action";			
	
	request.setAttribute("action",action);
	request.setAttribute("modify",modify);	
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="searchName" value="${searchName}" />
		<input type="hidden" name="conid" value="${conid}" />
		
		<input type="hidden" name="totalCount" value="${totalCount}" />		
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
					联系人名称（可模糊查询）
					<input type="text" name="searchName" />
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
			<li><a class="edit" href="${modify}?queryid={sid_queryid}&conid=${conid}" target="navTab" warn="请选择一个记录"><span>修改</span></a></li>
			<li class="line">line</li>						
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>							
				<th width="35">ID</th>
				<th width="100">客户名称</th>	
				<th width="55">联系人名称</th>				
				<th width="45">联系人电话</th>
				<th width="55">联系人邮箱</th>
				<th width="55">联系人职位</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${contListVO!=null}">
				<c:forEach items="${contListVO}" var="loop">
					<tr target="sid_queryid" rel="${loop.contactid}">								
						<td width="35">${loop.contactid}</td>						
						<td>${loop.customerName}</td>
						<td>${loop.contactName}</td>
						<td>${loop.mobile}</td>
						<td>${loop.email}</td>
						<td>${loop.position}</td>																								
						<td>					
							<a title="编辑" target="navTab" href="${modify}?queryid=${loop.contactid}&conid=${conid}" class="btnEdit">编辑</a>
						</td>	
					</tr>				
				</c:forEach>
			</c:if>		
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value,conid:${conid}})">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="4">4</option>
				<option value="10">10</option>
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
