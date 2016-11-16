<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	 
	String action = basePath + "page/log/lookup.action";

				
	request.setAttribute("action",action);	

		
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="query.logName" value="${query.logName}" />	
		<input type="hidden" name="query.description" value="${query.description}" />
						
		<input type="hidden" name="totalCount" value="${totalCount}" />		
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${param.orderField}" />
		<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>


<div class="pageHeader">
	<form onsubmit="return dwzSearch(this, 'dialog');" action="${action}" method="post">
	<div class="searchBar">		
		<table class="searchContent">
			<tr>
				<td>		
					状态名（可模糊）：<input type="text" value="${query.logName}" name="query.logName" />					
				</td>	
				<td>		
					描述（可模糊）：<input type="text" value="${query.description}" name="query.description" />					
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
	<table class="table" width="100%" layoutH="135">
		<thead>
			<tr>
				<!-- 
				<th width="30"  align="center">ID</th>
				 -->
				 
                <th width="30">ID</th>
                <th width="85">日志名称</th>  
                <th width="85">日志描述</th>  
                <th width="85">日志路径</th>
                <th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr>						
						<td>${loop.id}</td>
						<td>${loop.logName}</td>
						<td>${loop.description}</td>
						<td>${loop.filepath}</td>																										
						<td>				
							<a class="btnSelect" href="javascript:$.bringBack({logid:'${loop.id}', logName:'${loop.logName}'})" title="查找带回">选择</a>														
						</td>	
					</tr>
				</c:forEach>
			</c:if>		
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value})">
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
