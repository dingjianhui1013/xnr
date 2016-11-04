<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	 
	String action = basePath + "page/status/info.action";
	String modify = basePath+"page/status/modifyInfo.action";
	String del =  basePath+"page/status/del.action";
	String add =  basePath+"page/status/addInfo.action";
	
				
	request.setAttribute("action",action);	
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);	
	request.setAttribute("add", add);
		
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="query.statusName" value="${query.statusName}" />	
		<input type="hidden" name="query.description" value="${query.description}" />
		<input type="hidden" name="query.className" value="${query.className}" />		
		<input type="hidden" name="query.readme" value="${query.readme}" />	
		<input type="hidden" name="query.statusCode" value="${query.statusCode}" />	
		
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
					状态名（可模糊）：<input type="text" value="${query.statusName}" name="query.statusName" />					
				</td>	
				<td>		
					描述（可模糊）：<input type="text" value="${query.description}" name="query.description" />					
				</td>
				<td>		
					类名（可模糊）：<input type="text" value="${query.className}" name="query.className" />					
				</td>				
				
			</tr>	
			<tr>
				<td>		
					状态说明（可模糊）：<input type="text" value="${query.readme}" name="query.readme" />					
				</td>	
				<td>		
					状态码（可模糊）：<input type="text" value="${query.statusCode}" name="query.statusCode" />					
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
			<li><a class="add" rel="statusAddInfo" href="${add}" target="dialog"><span>添加</span></a></li>
			
			<li class="line">line</li>						
		</ul>
	</div>
	<table class="table" width="100%" layoutH="162">
		<thead>
			<tr>
				<!-- 
				<th width="30"  align="center">ID</th>
				 -->
				 
                <th width="30">ID</th>
                <th width="85">状态名称</th>  
                <th width="85">状态描述</th>  
                <th width="85">状态代码</th>
                <th width="75">适用类对象</th> 
                <th width="75">状态说明</th> 
                <th width="75">排序</th>                                
                <th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr>						
						<td>${loop.id}</td>
						<td>${loop.statusName}</td>
						<td>${loop.description}</td>
						<td>${loop.statusCode}</td>
						<td>${loop.className}</td>	
						<td>${loop.readme}</td>
						<td>${loop.sort}</td>																														
						<td>				
							<a title="确认删除?" target="ajaxTodo" href="${del}?query.id=${loop.id}&del=1" class="btnDel">删除</a>	
							<a title="编辑" rel="statusModifyInfo" target="dialog" href="${modify}?query.id=${loop.id}" class="btnEdit">编辑</a>														
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
				<option value="10">10</option>
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
