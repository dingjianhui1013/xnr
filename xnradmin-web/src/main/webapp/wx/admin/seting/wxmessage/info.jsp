<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/wx/wxmessage/info.action";
	String add = basePath+"page/wx/wxmessage/addInfo.action";
	String modify = basePath+"page/wx/wxmessage/modifyInfo.action";
	String del = basePath+"page/wx/wxmessage/del.action";
	String wxuserlookup = basePath+"page/wx/wxmessage/lookup.action";
	String view = basePath+"page/wx/wxmessage/view.action";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
	request.setAttribute("wxuserlookup",wxuserlookup);
	request.setAttribute("view",view);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">			
	
		<input type="hidden" name="query.wxmessage.msgTitle" value="${query.wxmessage.msgTitle}" />
		<input type="hidden" name="query.wxmessage.content" value="${query.wxmessage.content}" />
		<input type="hidden" name="query.wxmessage.msgTypeId" value="${query.wxmessage.msgTypeId}" />
	
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
					消息标题（可模糊查询）
					<input type="text" name="query.wxmessage.msgTitle" value="${query.wxmessage.msgTitle}"/>
				</td>			
				<td>
					消息内容（可模糊查询）
					<input type="text" name="query.wxmessage.content" value="${query.wxmessage.content}" />
				</td>	
				<td>
					<select class="combox" name="query.wxmessage.msgTypeId">
						<c:if test="${statusList!=null}">
							<c:forEach items="${statusList}" var="loop">
								<c:choose>
									<c:when test="${query.wxmessage.msgTypeId==loop.id}">
										<option value=${loop.id} selected="selected">${loop.readme}</option>
									</c:when>
									<c:otherwise>
										<option value=${loop.id}>${loop.readme}</option>	
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
			<li><a class="add" href="${add}" target="navTab" rel="menu_add"><span>添加消息</span></a></li>				
			<li class="line">line</li>			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>							
				<th width="25"">ID</th>
				<th width="45">消息类型</th>
				<th width="45">消息标题</th>
				<th width="45">消息内容</th>
				<th width="45">图文消息描述</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_menuid" rel="${loop.wxmessage.id}">								
						<td>${loop.wxmessage.id}</td>
						<td>${loop.msgType.readme}</td>		
						<td>${loop.wxmessage.msgTitle}</td>
						<td>${loop.msgContent}</td>
						<td>${loop.wxmessage.msgDescription}</td>	
						<td>					
							<a title="编辑" target="navTab" href="${modify}?query.wxmessage.id=${loop.wxmessage.id}" rel="wxmessage_modify" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="${del}?query.wxmessage.id=${loop.wxmessage.id}&del=1" class="btnDel">删除</a>
							<a title="查看" target="dialog" href="${view}?query.wxmessage.id=${loop.wxmessage.id}" rel="wxmessage_view" class="btnLook">查看</a>
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
