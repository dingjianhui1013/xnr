<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/menu/lookup.action";
	String add = basePath+"page/menu/addinfo.action";
	String modify = basePath+"page/menu/modifyinfo.action";
	String del = basePath+"page/menu/del.action";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">				
		<input type="hidden" name="menuName" value="${menuName}" />
		
		<input type="hidden" name="totalCount" value="${totalCount}" />
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${action}" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>菜单名称:</label>
				<input class="textInput" name="menuName" value="" type="text">
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
				<th width="100">菜单名称</th>
				<th width="70">菜单ID</th>				
				<th width="100">上级菜单名称</th>	
				<th width="55">菜单级别</th>				
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr>
						<td>${loop.menuname}</td>
						<td>${loop.id}</td>
						<td>${loop.pmenuname}</td>
						<td>${loop.menulevel}</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({id:'${loop.id}', orgName:'${loop.menuname}', orgNum:'${loop.id}'})" title="查找带回">选择</a>
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