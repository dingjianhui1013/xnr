<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/org/info.action";
	String getOrg = basePath+"page/org/all.action";
	String modify = basePath+"page/org/modifyinfo.action";
	String add = basePath + "page/org/addinfo.action";
	String lookup = basePath + "page/org/lookup.action";
	
	request.setAttribute("getOrg",getOrg);
	request.setAttribute("action",action);
	request.setAttribute("modify",modify);	
	request.setAttribute("add", add);
	request.setAttribute("lookup", lookup);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="org1.id" value="${org1.id}" />
		<input type="hidden" name="orgName" value="${orgName}" />
		
		<input type="hidden" name="totalCount" value="${totalCount}" />		
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
					父类组织:					
				</td>
				<td>					
					<dd>			
					<input id="inputOrg1" name="org1.id" value=""  type="hidden"/>
					<input name="org1.organizationName" type="text" postField="keyword"  lookupGroup="org1"/>
					
					</dd>	
				</td>
				<td>
					<a class="btnLook" href="${lookup}" lookupGroup="org1">查找带回</a>
				</td>
				<td>
					指定组织名称（可模糊）					
				</td>
				<td>
					<input type="text" name="orgName"/>
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
			<li><a class="add" href="${add}" target="dialog" rel="dlg_page10"><span>添加组织</span></a></li>			
			<li><a class="edit" href="${modify}?orgid={sid_orgid}" target="navTab" rel="modify" warn="请选择一个组织"><span>修改</span></a></li>
			<li class="line">line</li>			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>							
				<th width="70" orderField="id" class="${idsort}">编号</th>
				<th width="100">组织名称</th>				
				<th width="100">上级组织 </th>
				<th width="100">组织类型 </th>							
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>		
			
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_orgid" rel="${loop.orgid}">								
						<td>${loop.orgid}</td>
						<td>${loop.orgname}</td>						
						<td>${loop.porgname}</td>
						<td>${loop.type}</td>											
						<td>					
							<a title="编辑" target="navTab" href="${modify}?orgid=${loop.orgid}" class="btnEdit">编辑</a>
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
