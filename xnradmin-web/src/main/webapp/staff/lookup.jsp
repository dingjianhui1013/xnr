<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	 
	String action = basePath + "page/staff/lookup.action";	
	String lookup = basePath + "page/org/lookup.action";
	
	request.setAttribute("action",action);
	request.setAttribute("lookup", lookup);

%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="org1.id" value="${org1.id}" />		
		<input type="hidden" name="staffname" value="${staffname}" />
		<input type="hidden" name="orgname" value="${orgname}" />
		<input type="hidden" name="sTime" value="${sTime}" />
		<input type="hidden" name="eTime" value="${eTime}" />
		<input type="hidden" name="staffStatus" value="${staffStatus}" />
		<input type="hidden" name="queryOrgid" value="${queryOrgid}" />
		<input type="hidden" name="manager" value="${manager}" />
		<input type="hidden" name="director" value="${director}" />
		
		
		
		<input type="hidden" name="totalCount" value="${totalCount}" />		
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${param.orderField}" />
		<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${action}" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>		
					员工名称（可模糊）：<input type="text" name="staffname" />
					
				</td>
				
				<td>
					所属组织(可模糊):<input type="text" name="orgname" />
				</td>
				<!-- 
				<td>					
					<dd>			
					<input id="inputOrg1" name="org1.id" type="hidden"/>
					<input name="org1.organizationName" readonly="true" type="text" postField="keyword"  lookupGroup="org1"/>
					
					</dd>	
				</td>
				 
				<td>
					<a class="btnLook" href="${lookup}" lookupGroup="org1">查找带回</a>
				</td>		
				-->		
				<td>				
					建档日期：从 <input type="text" name="sTime" class="date" readonly="true" /> 到 <input type="text" name="eTime" class="date" readonly="true" /> 之间				
				</td>
				<td>状态类型	:</td>
				<td>				
					
					<select class="combox" name="staffStatus">
						<c:if test="${statusList!=null}">
							<c:forEach items="${statusList}" var="loop">																													
									<option value=${loop.id}>${loop.statusName}</option>	
							</c:forEach>
						</c:if>						
					</select>		
				</td>
			</tr>
			<tr>
				<td>
					是否部门主管	:
					<c:choose>
						<c:when test="${manager==1}">
							<input name="manager" value="1" type="checkbox" checked="checked">											
						</c:when>
						<c:otherwise>
							<input name="manager" value="1" type="checkbox">
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					是否部门总监	:
					<c:choose>
						<c:when test="${director==1}">
							<input name="director" value="1" type="checkbox" checked="checked">											
						</c:when>
						<c:otherwise>
							<input name="director" value="1" type="checkbox">
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="158" targetType="dialog" width="100%">
		<thead>
			<tr>				
				<th width="80"  align="center">ID</th>
				<th width="120" orderField="userid" class="${useridsort}">编号</th>
				<th width="100">名称</th>
				<th width="100">所属部门</th>				
				<th width="100">角色</th>
				<th width="100">手机号</th>
				<th width="100">邮箱</th>				
				<th width="80" orderField="createTime" class="${createTimeSort}">建档日期</th>
				<th width="55">状态</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_queryid" rel="${loop.userid}">
						<td>${loop.userid}</td>
						<td>${loop.login_id}</td>
						<td>${loop.staffName}</td>
						<td>${loop.orgname}</td>
						<td></td>					
						<td>${loop.mobile}</td>
						<td>${loop.email}</td>				
						<td>${loop.createTime}</td>						
						<td>${loop.statusName}</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({id:'${loop.userid}', staffName:'${loop.staffName}', mobile:'${loop.mobile}', orgNum:'${loop.userid}'})" title="查找带回">选择</a>
						</td>						
					</tr>
				</c:forEach>
			</c:if>		
		</tbody>
	</table>
	
	
	
	<div class="panelBar">
		<div class="pages">
			<span>每页</span>						
			<select name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value})">
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
		<div class="pagination" targetType="dialog" totalCount="${totalCount}" numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}"></div>
	</div>
</div>
