<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/menu/info.action";
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
					菜单名称（可模糊查询）
					<input type="text" name="menuName" />
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
			<li><a class="add" href="${add}" target="dialog" rel="menu_add"><span>添加菜单</span></a></li>				
			<li><a class="edit" href="${modify}?menuid={sid_menuid}" target="navTab" warn="请选择一个记录"><span>修改</span></a></li>
			<li class="line">line</li>			
			<li><a class="delete" href="${del}?menuid={sid_menuid}&del=1" target="ajaxTodo" title="删除某个菜单将连带删除其下的所有子菜单"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>							
				<th width="70"">ID</th>
				<th width="100">名称</th>
				<th width="100">英文名称</th>
				<th width="70" orderField="menu_level" class="${menu_level}">级别</th>
				<th width="100">链接</th>
				<th width="100">上级菜单名称</th>
				<th width="35">菜单排序</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_menuid" rel="${loop.id}">								
						<td>${loop.id}</td>
						<td>${loop.menuname}</td>						
						<td>${loop.enmenuname}</td>
						<td>${loop.menulevel}</td>	
						<td>${loop.link}</td>
						<td>${loop.pmenuname}</td>	
						<td>${loop.sortOrder}</td>										
						<td>					
							<a title="编辑" target="navTab" href="${modify}?menuid=${loop.id}" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="${del}?menuid=${loop.id}&del=1" class="btnDel">删除</a>
							<a title="授权" target="dialog" href="page/menu/anthinfo.action?menuid=${loop.id}" class="btnAuth">授权</a>
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
