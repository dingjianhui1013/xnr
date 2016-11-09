<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	 
	String action = basePath + "page/script/info.action";	
	String modify = basePath+"page/script/modifyInfo.action";
	String del =  basePath+"page/script/del.action";
	String add =  basePath+"page/script/addinfo.action";	
	String ref = basePath+"page/script/ref.action";
	String refAll = basePath+"page/script/refAll.action";
				
	request.setAttribute("action",action);	
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);	
	request.setAttribute("add", add);
	request.setAttribute("ref",ref);	
	request.setAttribute("refAll", refAll);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="query.scriptClassName" value="${query.scriptClassName}" />			
					
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
					类名（可模糊）：<input type="text" value="${query.scriptClassName}" name="query.scriptClassName" />
					
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
			<li><a class="add" href="${add}" target="navTab"><span>添加</span></a></li>
			
			<li class="line">line</li>			
			<li><a title="全部重新加载?" class="delete" href="${refAll}" target="ajaxTodo"><span>全部重载</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="135">
		<thead>
			<tr>
				<!-- 
				<th width="30"  align="center">ID</th>
				 -->
				 
                <th width="30">ID</th>
                <th width="85">类名</th>    
                <th width="75">添加时间</th>               
                <th width="35">状态</th>
                <th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr>						
						<td>${loop.scriptpo.id}</td>
						<td>${loop.scriptpo.className}</td>										
						<td><fmt:formatDate value="${loop.scriptpo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${loop.scriptpo.statusDesc}</td>										
						<td>				
							<a title="设置为删除状态?" target="ajaxTodo" href="${del}?query.scriptpo.id=${loop.scriptpo.id}&del=1" class="btnDel">删除</a>	
							<a title="编辑" rel="scriptModifyInfo" target="navTab" href="${modify}?query.scriptpo.id=${loop.scriptpo.id}" class="btnEdit">编辑</a>
							<a title="重新加载?" target="ajaxTodo" href="${ref}?query.scriptpo.id=${loop.scriptpo.id}" class="btnRefresh">重新加载</a>							
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
