<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/wx/wxmenu/info.action";
	String add = basePath+"page/wx/wxmenu/addInfo.action";
	String modify = basePath+"page/wx/wxmenu/modifyInfo.action";
	String del = basePath+"page/wx/wxmenu/del.action";
	String wxuserlookup = basePath+"page/wx/wxuser/lookup.action";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
	request.setAttribute("wxuserlookup",wxuserlookup);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">			

		<input type="hidden" name="query.menu.menuName" value="${query.menu.menuName}" />
		<input type="hidden" name="query.menu.typeid" value="${query.menu.typeid}" />	
		
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
					<input type="text" name="query.menu.menuName" />
				</td>			
				<td>
					对应用户
				</td>
				<td>					
					<dd>								
					<input id="wxuservo.wxuserid" name="wxuservo.wxuserid" value="${wxuservo.wxuserid}" type="hidden"/>
					<input name="wxuservo.staffName" value="${wxuservo.staffName}" readonly="true" type="text" postField="keyword"/>					
					</dd>	
				</td>	
				<td>
					<a class="btnLook" href="${wxuserlookup}" lookupGroup="wxuservo">查找带回</a>
				</td>		
				<td>
					<select class="combox" name="query.menu.typeid">
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
	<div class="panelBar">
		<ul class="toolBar">		
			<li><a class="add" href="${add}" target="dialog" rel="menu_add"><span>添加菜单</span></a></li>		
			<li class="line">line</li>			
			<li><a class="edit" target="ajaxTodo" href="${basePath }page/wx/wxconnect/createFMenu.action"  rel="menu_create"><span>创建菜单</span></a></li>	
			<li class="line">line</li>			
			<li><a class="delete" href="${del}?menuid={sid_menuid}&del=1" target="ajaxTodo" title="删除某个菜单将连带删除其下的所有子菜单"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>							
				<th width="25"">ID</th>
				<th width="45">菜单名称</th>
				<th width="45">系统用户名称</th>
				<th width="45">菜单级别</th>
				<th width="45">菜单类型</th>
				<th width="35">上级菜单名称</th>
				<th width="35">菜单排序</th>
				<th width="35">菜单key</th>
				<th width="35">菜单url</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_menuid" rel="${loop.menu.id}">								
						<td>${loop.menu.id}</td>
						<td>${loop.menu.menuName}</td>						
						<td>${loop.wxuservo.staff.staffName}</td>
						<td>${loop.menu.menuLevel}</td>	
						<td>${loop.menuType.statusName}</td>
						<td>${loop.parentMenu.menuName}</td>	
						<td>${loop.menu.sortOrder}</td>
						<td>${loop.menu.wxkey}</td>			
						<td>${loop.menu.url}</td>									
						<td>					
							<a title="编辑" target="dialog" href="${modify}?query.menu.id=${loop.menu.id}" rel="menu_modify" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="${del}?query.menu.id=${loop.menu.id}&del=1" class="btnDel">删除</a>
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
