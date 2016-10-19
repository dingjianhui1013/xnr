<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	 
	String action = basePath + "page/port/info.action";	
	String modify = basePath+"page/port/modifyInfo.action";
	String del =  basePath+"page/port/del.action";
	String add =  basePath+"page/port/addinfo.action";
	String scriptLookup = basePath+"page/script/lookup.action";
	String view = basePath+"page/port/view.action";
				
	request.setAttribute("action",action);	
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);	
	request.setAttribute("add", add);
	request.setAttribute("scriptLookup", scriptLookup);
	request.setAttribute("view", view);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="query.port.portName" value="${query.port.portName}" />	
		<input type="hidden" name="script.id" value="${script.id}" />		
					
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
					接口名（可模糊）：<input type="text" value="${query.port.portName}" name="query.port.portName" />					
				</td>	
				<td>
					使用脚本
				</td>
				<td>					
					<dd>								
					<input id="script.id" name="script.id" value="${script.id}" type="hidden"/>
					<input name="script.className" value="${script.className}" readonly="true" type="text" postField="keyword"  lookupGroup="script"/>					
					</dd>	
				</td>		
				<td>
					<a class="btnLook" href="${scriptLookup}" lookupGroup="script">查找带回</a>
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
		</ul>
	</div>
	<table class="table" width="100%" layoutH="135">
		<thead>
			<tr>
				<!-- 
				<th width="30"  align="center">ID</th>
				 -->
				 
                <th width="30">ID</th>
                <th width="85">接口名</th>  
                <th width="85">脚本名</th>  
                <th width="75">添加时间</th>               
                <th width="35">状态</th>
                <th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr>						
						<td>${loop.port.id}</td>
						<td>${loop.port.portName}</td>
						<td>${loop.port.scriptClassName}</td>										
						<td><fmt:formatDate value="${loop.port.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${loop.port.statusName}</td>										
						<td>				
							<a title="设置为删除状态?" target="ajaxTodo" href="${del}?query.port.id=${loop.port.id}&del=1" class="btnDel">删除</a>	
							<a title="编辑" rel="portModify" target="navTab" href="${modify}?query.port.id=${loop.port.id}" class="btnEdit">编辑</a>
							<a title="查看" rel="portView" target="dialog" href="${view}?query.port.id=${loop.port.id}" class="btnLook">查看</a>														
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
