<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/port/lookup.action";	
	

	request.setAttribute("action",action);
	
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="query.scriptpo.className" value="${query.scriptpo.className}" />	
		
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
				<label>类名:</label>				
				<input class="textInput" type="text" value="${query.scriptpo.className}" name="query.scriptpo.className" />
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
				<th width="30">ID</th>
                <th width="85">类名</th>    
                <th width="75">添加时间</th>               
                <th width="35">状态</th>                					
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr>								
						<td>${loop.scriptpo.id}</td>
						<td>${loop.scriptpo.className}</td>										
						<td><fmt:formatDate value="${loop.scriptpo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${loop.scriptpo.statusDesc}</td>												
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({id:'${loop.scriptpo.id}', className:'${loop.scriptpo.className}'})" title="查找带回">选择</a>
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
