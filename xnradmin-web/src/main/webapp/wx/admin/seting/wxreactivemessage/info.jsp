<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/wx/wxreactivemessage/info.action";
	String add = basePath+"page/wx/wxreactivemessage/addInfo.action";
	String modify = basePath+"page/wx/wxreactivemessage/modifyInfo.action";
	String del = basePath+"page/wx/wxreactivemessage/del.action";
	String view = basePath+"page/wx/wxreactivemessage/view.action";
	String wxuserlookup = basePath+"page/wx/wxuser/lookup.action";
	String menuLookup = basePath + "page/wx/wxmenu/lookup.action?query.menu.typeid=137";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
	request.setAttribute("view",view);
	request.setAttribute("wxuserlookup",wxuserlookup);
	request.setAttribute("menuLookup",menuLookup);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">			

		<input type="hidden" name="query.wxuservo.wxuserid" value="${query.wxuservo.wxuserid}" />
		
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
					对应用户
				</td>
				<td>					
					<dd>								
					<input id="query.wxuservo.wxuserid" name="query.wxuservo.wxuserid" value="${query.wxuservo.wxuserid}" type="hidden"/>
					<input name="query.wxuservo.staffName" value="${query.wxuservo.staffName}" readonly="true" type="text" postField="keyword"/>					
					</dd>	
				</td>	
				<td>
					<a class="btnLook" href="${wxuserlookup}" lookupGroup="query.wxuservo">查找带回</a>
				</td>		
				<td>
					对应菜单
				</td>
				<td>					
					<dd>								
					<input id="query.wxmenu.menu.id" name="query.wxmenu.menu.id" value="${query.wxmenu.menu.id}" type="hidden"/>
					<input name="query.wxmenu.menu.menuName" value="${query.wxmenu.menu.menuName}" readonly="true" type="text" postField="keyword"/>					
					</dd>	
				</td>	
				<td>
					<a class="btnLook" href="${menuLookup}" lookupGroup="query.wxmenu.menu">查找带回</a>
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
			<li><a class="add" href="${add}" target="navTab" rel="menu_add"><span>添加消息</span></a></li>				
			<li class="line">line</li>			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>							
				<th width="25"">ID</th>
				<th width="45">对应输入内容</th>
				<th width="45">对应菜单</th>
				<th width="45">对应微信用户</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_menuid" rel="${loop.reactiveMessage.id}">
						<td>${loop.reactiveMessage.id}</td>
						<td>${loop.reactiveMessage.recContent}</td>	
						<td>${loop.wxmenu.menu.menuName}</td>		
						<td>${loop.wxuservo.staffName}</td>
						<td>					
							<a title="编辑" target="navTab" href="${modify}?query.reactiveMessage.id=${loop.reactiveMessage.id}" rel="reactiveMessage_modify" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="${del}?query.reactiveMessage.id=${loop.reactiveMessage.id}&del=1" class="btnDel">删除</a>
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
