<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/business/admin/commodity/category/lookup.action";
	
	request.setAttribute("action",action);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">				
		<input type="hidden" name="categoryParentId" value="${categoryParentId}" />
		<input type="hidden" name="categoryLevel" value="${categoryLevel}" />
		<input type="hidden" name="categoryName" value="${categoryName}" />
		<input type="hidden" name="categoryStatus" value="${categoryStatus}" />
		<input type="hidden" name="primaryConfigurationId" value="${primaryConfigurationId}" />
		<input type="hidden" name="staffId" value="${staffId}" />
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${action}" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					分类名称：
					<input type="text" name="categoryName" value="${categoryName}"/>
				</td>
				<td>
					分类级别：
					<input type="text" name="categoryLevel" value="${categoryLevel}"/>
				</td>
				<td>
					分类状态：
				<select class="combox" name="categoryStatus">
					<c:if test="${categoryStatusList!=null}">
					<option value="" selected>选择</option>
						<c:forEach items="${categoryStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==categoryStatus}">
									<option value=${loop.id} selected>${loop.statusName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusName}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
				</td>
				<td>
					选择商城：
				<select class="combox" name="primaryConfigurationId">
					<c:if test="${primaryConfigurationList!=null}">
					<option value="" selected>选择</option>
						<c:forEach items="${primaryConfigurationList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==primaryConfigurationId}">
									<option value=${loop.id} selected>${loop.mallName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.mallName}</option>
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

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="100">分类名称</th>
				<th width="100">分类排序</th>
				<th width="100">分类等级</th>
				<th width="100">分类图片</th>
				<th width="100">分类置顶图片</th>
				<th width="100">分类状态</th>
				<th width="100">上级分类</th>
				<th width="100">对应商城</th>
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
					<tr target="sid_categoryId" rel="${loop.businessCategory.id}">					
						<td>${loop.businessCategory.categoryName}</td>
						<td>${loop.businessCategory.sortId}</td>
						<td>${loop.businessCategory.categoryLevel}</td>
						<td><img src="${loop.businessCategory.categoryLogo}" width="100%" height="100%" alt="" /></td>
						<td><img src="${loop.businessCategory.categoryHeadLogo}" width="100%" height="100%" alt="" /></td>
						<td>
						<c:if test="${categoryStatusList!=null}">
							<c:forEach items="${categoryStatusList}" var="statusList">
								<c:if test="${statusList.id==loop.businessCategory.categoryStatus}">
									${statusList.statusCode}
   								</c:if>
							</c:forEach>
						</c:if>
						<td>
						</td>
						<c:if test="${categoryList!=null}">
							<c:forEach items="${categoryList}" var="categoryAll">
								<c:if test="${categoryAll.id==loop.businessCategory.categoryParentId}">
									${categoryAll.categoryName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>
						<c:if test="${primaryConfigurationList!=null}">
							<c:forEach items="${primaryConfigurationList}" var="primaryConfiguration">
								<c:if test="${primaryConfiguration.id==loop.businessCategory.primaryConfigurationId}">
									${primaryConfiguration.mallName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>				
						<td>${loop.businessCategory.createTime}</td>
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffcreate">
								<c:if test="${staffcreate.userid==loop.businessCategory.createStaffId}">
									${staffcreate.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>${loop.businessCategory.modifyTime}</td>	
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffmodify">
								<c:if test="${staffmodify.userid==loop.businessCategory.modifyStaffId}">
									${staffmodify.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({id:'${loop.businessCategory.id}', 
							categoryLevel:'${loop.businessCategory.categoryLevel}', categoryName:'${loop.businessCategory.categoryName}', 
							primaryConfigurationId:'${loop.businessCategory.primaryConfigurationId}'})" title="查找带回">选择</a>
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
