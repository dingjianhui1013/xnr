<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/Common/Region/Road/info.action";
	String add = basePath+"page/Common/Region/Road/addinfo.action";
	String modify = basePath+"page/Common/Region/Road/modifyinfo.action";
	String del = basePath+"page/Common/Region/Road/del.action";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
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
					 街/路名称
					<input type="text" name="road" />
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
			<li><a class="add" href="${add}" target="dialog" rel="road_add"><span>添加街/路名称</span></a></li>				
			<li><a class="edit" rel="roadInfo" href="${modify}?roadid={sid_roadid}" target="navTab" warn="请选择一个记录"><span>编辑</span></a></li>
			<li class="line">line</li>			
			<li><a class="delete" href="${del}?roadid={sid_roadid}&del=1" target="ajaxTodo" title="删除某个街/路"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>						
				<th width="100">街道/路</th>
				<th width="100">区县/名称</th>	
				<th width="100">地市名称</th>
				<th width="100">省份名称</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_roadid" rel="${loop.roadid}">			
						<td>${loop.road}</td>			
						<td>${loop.area}</td>
						<td>${loop.city}</td>
						<td>${loop.province}</td>
						<td>					
							<a title="编辑" rel="roadInfo" target="navTab" href="${modify}?roadid=${loop.roadid}" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="${del}?roadid=${loop.roadid}&del=1" class="btnDel">删除</a>
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
