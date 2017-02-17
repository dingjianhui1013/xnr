<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/wx/admin/seting/primaryConfiguration/lookup.action";
	
	request.setAttribute("action",action);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">				
		<input type="hidden" name="mallName" value="${mallName}" />
		<input type="hidden" name="staffId" value="${staffId}" />
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${action}" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>商城名称：</label>
				<input class="textInput" name="mallName" value="" type="text">
			</li>	  
					
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="100">商城名称</th>
				<th width="100">logo图</th>
				<th width="100">联系地址</th>
				<th width="100">联系电话</th>
				<th width="100">邮政编码</th>
				<th width="100">邮箱地址</th>
				<th width="100">商城状态</th>
				<th width="100">建立人员</th>
				<th width="100">建立时间</th>
				<th width="100">修改人员</th>
				<th width="100">修改时间</th>	
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_primaryConfigurationId" rel="${loop.primaryConfigurationId}">					
						<td>${loop.mallName}</td>
						<td><img src="${loop.mallLogo}" width="100%" height="100%" alt="" /></td>
						<td>${loop.address}</td>			
						<td>${loop.mobile}</td>
						<td>${loop.code}</td>
						<td>${loop.email}</td>
						<td>
						<c:if test="${mallStatusList!=null}">
							<c:forEach items="${mallStatusList}" var="mallStatus">
								<c:if test="${mallStatus.id==loop.mallStatus}">
									${mallStatus.statusCode}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>				
						<td>${loop.primaryConfigurationCreateTime}</td>
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffcreate">
								<c:if test="${staffcreate.userid==loop.primaryConfigurationCreateStaffId}">
									${staffcreate.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>		
						<td>${loop.primaryConfigurationModifyTime}</td>	
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffmodify">
								<c:if test="${staffmodify.userid==loop.primaryConfigurationModifyStaffId}">
									${staffmodify.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>					
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({id:'${loop.primaryConfigurationId}', mallName:'${loop.mallName}'})" title="查找带回">选择</a>
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
