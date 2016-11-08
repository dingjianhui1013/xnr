<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/wx/farmer/info.action";
	String add = basePath+"page/wx/farmer/addInfo.action";
	String modify = basePath+"page/wx/farmer/modifyInfo.action";
	String del = basePath+"page/wx/farmer/del.action";
	String wxuserlookup = basePath+"page/wx/farmer/lookup.action";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
	request.setAttribute("wxuserlookup",wxuserlookup);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">			

		<input type="hidden" name="query.userName" value="${query.userName}" />
		<input type="hidden" name="query.userId" value="${query.userId}" />	
		
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
					用户名称
					<input type="text" name="query.userName" />
				</td>	
				<td>
					用户ID
					<input type="text" name="query.userId" />
				</td>		
				<td>商品类型</td>	
				<td>
					<select class="combox" name="query.types">
						<c:if test="${statusList!=null}">
							<c:forEach items="${statusList}" var="loop">
								<c:choose>
									<c:when test="${query.menu.typeid==loop.id}">
										<option value=${loop.id} selected="selected">${loop.statusName}</option>
									</c:when>
									<c:otherwise>
										<option value=${loop.id}>${loop.statusName}</option>	
									</c:otherwise>
								</c:choose>					
							</c:forEach>
						</c:if>		
					</select>
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
<!-- 	<div class="panelBar"> -->
<!-- 		<ul class="toolBar">		 -->
<%-- 			<li><a class="add" href="${add}" target="dialog" rel="menu_add"><span>添加用户</span></a></li>				 --%>
<!-- 			<li class="line">line</li>			 -->
<%-- 			<li><a class="delete" href="${del}?menuid={sid_menuid}&del=1" target="ajaxTodo" title="删除某个菜单将连带删除其下的所有子菜单"><span>删除用户</span></a></li> --%>
<!-- 		</ul> -->
<!-- 	</div> -->
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>							
				<th width="25"">ID</th>
				<th width="45">用户ID</th>
				<th width="45">用户名称</th>
				<th width="45">商品类型</th>
				<th width="45">用户头像</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_menuid" rel="${loop.id}">								
						<td>${loop.id}</td>
						<td>${loop.userId}</td>
						<td>${loop.userName}</td>						
						<td>${loop.types}</td>	
						<td>${loop.headPortrait}</td>									
						<td>
							<a title="设置角色" rel="staffInfo" target="dialog" href="page/staff/anthinfo.action?queryid=${loop.userid}&queryOrg1.id=${org1.id}&queryOrg1.organizationName=${org1.organizationName}&queryStaffname=${staffname}&queryStime=${sTime}&queryEtime=${eTime}&queryStaffStatus=${staffStatus}&totalCount=${totalCount}&pageNum=${pageNum}&numPerPage=${numPerPage}&orderField=${orderField}&orderDirection=${orderDirection}" class="btnAuth">角色</a>					
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
