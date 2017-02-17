<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://"  
            + request.getServerName() + ":" + request.getServerPort()  
            + path + "/"; 
	String action = basePath+"page/wx/farmer/pictureInfo.action";
	
	request.setAttribute("action",action);
	request.setAttribute("basePath",basePath);
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${action}">			
		
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${action}" method="post">
	<div class="searchBar">	
		<table class="searchContent">
			<tr>
				<td>
					农户名称
					<input type="text" name="farmerImage.userName" value="${farmerImage.userName }"/>
				</td>	
<%-- 				<td>
					用户ID
					<input type="text" name="query.userId" value="${ query.userId}"/>
				</td> --%>		
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
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>							
				<th width="25"">ID</th>
				<th width="45">农户名称</th>
				<th width="45">图片</th>
				<th width="45">操作</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${FarmerImageVOs!=null}">
				<c:forEach items="${FarmerImageVOs}" var="loop">
					<tr target="sid_menuid">								
						<td>${loop.farmerImage.id}</td>
						<td>${loop.farmer.userName}</td>
						<td align="center">
							<c:if test="${loop.farmerImage.newUrl!=null}">
								<img src="${basePath}${loop.farmerImage.newUrl}" style="height: 100px;vertical-align:middle"/>	
							</c:if>
							<c:if test="${loop.farmerImage.newUrl==null}">
								<img src="${basePath}${loop.farmerImage.url}" style="height: 100px;vertical-align:middle"/>
							</c:if>					
						</td>
						<td>
						<a title="删除" target="ajaxTodo" href="page/wx/farmer/dealPicture.action?delId=${loop.farmerImage.id}" class="btnDel">删除</a>
						<a title="编辑替换" target="dialog" href="page/wx/farmer/replacePicture.action?farmerImage.id=${loop.farmerImage.id}" class="btnEdit">编辑</a>
						<a title="下载" href="page/wx/farmer/downloadPicture.action?farmerImage.url=${loop.farmerImage.url}" class="btnSelect">下载</a>
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
