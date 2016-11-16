<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	 
	String action = basePath + "page/wx/wxuser/lookup.action";	
	String modify = basePath+"page/wx/wxuser/modifyInfo.action";
	String del =  basePath+"page/wx/wxuser/del.action";
	String add =  basePath+"page/wx/wxuser/addInfo.action";
	String staffLookup = basePath + "page/staff/lookup.action";
	String view = basePath+"page/wx/wxuser/view.action";
	String reload = basePath+"page/wx/wxuser/reload.action";
	
	request.setAttribute("action",action);	
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);	
	request.setAttribute("add", add);
	request.setAttribute("staffLookup", staffLookup);
	request.setAttribute("view", view);
	request.setAttribute("reload", reload);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="staff.id" value="${staff.id}" />	
					
		<input type="hidden" name="totalCount" value="${totalCount}" />		
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${param.orderField}" />
		<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>


<div class="pageHeader">
	<form onsubmit="return dwzSearch(this,'dialog');" action="${action}" method="post">
	<div class="searchBar">		
		<table class="searchContent">
			<tr>
				<td>		
					appid（可模糊）：<input type="text" value="${query.wxuser.appid}" name="query.wxuser.appid" />					
				</td>	
				<!-- 
				<td>
					对应用户
				</td>
				<td>					
					<dd>								
					<input id="staff.id" name="staff.id" value="${staff.id}" type="hidden"/>
					<input name="staff.staffName" value="${staff.staffName}" readonly="true" type="text" postField="keyword"  lookupGroup="staff"/>					
					</dd>	
				</td>		
				<td>
					<a class="btnLook" href="${staffLookup}" lookupGroup="staff">查找带回</a>
				</td>
				 -->
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
                <th width="45">微信appid</th>  
                <th width="45">微信token</th>  
                <th width="45">添加时间</th>               
                <th width="35">状态</th>
                <th width="45">对应用户</th>
                <th width="45">最后获取accessToken时间</th>
                <th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr>						
						<td>${loop.wxuser.id}</td>
						<td>${loop.wxuser.appid}</td>
						<td>${loop.wxuser.token}</td>					
						<td><fmt:formatDate value="${loop.wxuser.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${loop.status.statusName}</td>	
						<td>${loop.staff.staffName}</td>	
						<td><fmt:formatDate value="${loop.wxaccessToken.lastGenTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>				
							<!-- 
							<a title="设置为删除状态?" target="ajaxTodo" href="${del}?query.wxuser.id=${loop.wxuser.id}&del=1" class="btnDel">删除</a>
							 -->	
							<a class="btnSelect" href="javascript:$.bringBack({wxuserid:'${loop.wxuser.id}', staffName:'${loop.staff.staffName}'})" title="查找带回">选择</a>
						</td>	
					</tr>
				</c:forEach>
			</c:if>		
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
				<select name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value})">
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
		
		<div class="pagination" targetType="dialog" totalCount="${totalCount}" numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}"></div>

	</div>
</div>
