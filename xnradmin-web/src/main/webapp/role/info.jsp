<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/role/info.action";	
	String modify = basePath+"page/role/modifyinfo.action";
	//String modify = basePath+"role/modify.jsp";
	String add = basePath+"role/add.jsp";
	
	request.setAttribute("add",add);
	request.setAttribute("action",action);
	request.setAttribute("modify",modify);	
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="searchName" value="${searchName}" />
		
		<input type="hidden" name="totalCount" value="${totalCount}" />		
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${param.orderField}" />
		<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${action}" method="post">
	<div class="searchBar">	
		<table class="searchContent">
			<tr>
				<td>
					角色名称（可模糊）
				</td>
				<td>
					<dl class="nowrap">						
						<dd>							
							<input name="searchName" type="text" postField="keyword""/>												
						</dd>
					</dl>
				</td>
				<td>
					
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
			<li><a class="add" href="${add}" target="dialog" rel="dlg_page10"><span>添加</span></a></li>			
			<li><a class="edit" href="${modify}?queryid={sid_queryid}" target="navTab" rel="modify" warn="请选择一条数据"><span>修改</span></a></li>
			<li class="line">line</li>			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>							
				<th width="70" orderField="id" class="${idsort}">编号</th>
				<th width="100">角色名称</th>
				<th width="70">角色编码</th>
				<th width="100">角色描述</th>										
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>		
			
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_queryid" rel="${loop.id}">								
						<td>${loop.id}</td>
						<td>${loop.roleName}</td>
						<td>${loop.roleCode}</td>
						<td>${loop.description}</td>																			
						<td>					
							<a title="编辑" target="navTab" href="${modify}?queryid=${loop.id}" class="btnEdit">编辑</a>
							<a title="授权" target="dialog" href="page/role/anthinfo.action?queryid=${loop.id}" class="btnAuth">授权</a>
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
