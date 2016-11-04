<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/Common/Region/Community/info.action";
	String add = basePath+"page/Common/Region/Community/addinfo.action";
	String modify = basePath+"page/Common/Region/Community/modifyinfo.action";
	String del = basePath+"page/Common/Region/Community/del.action";
	
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
					<input type="text" name="community" />
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
			<li><a class="add" href="${add}" target="dialog" rel="community_add"><span>添加社区名称</span></a></li>				
			<li><a class="edit" rel="communityInfo" href="${modify}?communityid={sid_commynityid}" target="navTab" warn="请选择一个记录"><span>编辑</span></a></li>
			<li class="line">line</li>			
			<li><a class="delete" href="${del}?communityid={sid_communityid}&del=1" target="ajaxTodo" title="删除某个社区"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="100">社区</th>						
				<th width="100">街/路名称</th>
				<th width="100">区县/名称</th>	
				<th width="100">地市名称</th>
				<th width="100">省份名称</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_communityid" rel="${loop.communityid}">	
						<td>${loop.community}</td>		
						<td>${loop.road}</td>			
						<td>${loop.area}</td>
						<td>${loop.city}</td>
						<td>${loop.province}</td>
						<td>					
							<a title="编辑" rel="communityInfo" target="navTab" href="${modify}?communityid=${loop.communityid}" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="${del}?communityid=${loop.communityid}&del=1" class="btnDel">删除</a>
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
