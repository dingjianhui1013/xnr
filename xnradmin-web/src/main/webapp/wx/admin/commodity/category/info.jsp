<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/wx/admin/commodity/category/info.action";
	String add = basePath+"page/wx/admin/commodity/category/addinfo.action";
	String modify = basePath+"page/wx/admin/commodity/category/modifyinfo.action";
	String del = basePath+"page/wx/admin/commodity/category/del.action";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
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
	<form onsubmit="return navTabSearch(this);" action="" method="post">
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
				</td>
				<td>
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
				</td>
				<td>
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${add}" target="navTab" rel="primary_add"><span>添加分类</span></a></li>				
			<li><a class="edit" href="${modify}?categoryId={sid_categoryId}" target="navTab" warn="请选择一个记录"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="${del}?categoryId={sid_categoryId}&del=1" target="ajaxTodo" title="删除某个分类"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
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
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_categroyId" rel="${loop.categoryId}">					
						<td>${loop.categoryName}</td>
						<td>${loop.categorySortId}</td>
						<td>${loop.categoryLevel}</td>
						<td><img src="${loop.categoryLogo}" width="100%" height="100%" alt="" /></td>
						<td><img src="${loop.categoryHeadLogo}" width="100%" height="100%" alt="" /></td>
						<td>
						<c:if test="${categoryStatusList!=null}">
							<c:forEach items="${categoryStatusList}" var="statusList">
								<c:if test="${statusList.id==loop.categoryStatus}">
									${statusList.statusName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>
						<c:if test="${categoryList!=null}">
							<c:forEach items="${categoryList}" var="categoryAll">
								<c:if test="${categoryAll.id==loop.categoryParentId}">
									${categoryAll.categoryName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>
						<c:if test="${primaryConfigurationList!=null}">
							<c:forEach items="${primaryConfigurationList}" var="primaryConfiguration">
								<c:if test="${primaryConfiguration.id==loop.categoryPrimaryConfigurationId}">
									${primaryConfiguration.mallName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>				
						<td>${loop.categoryCreateTime}</td>
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffcreate">
								<c:if test="${staffcreate.userid==loop.categoryCreateStaffId}">
									${staffcreate.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>${loop.categoryModifyTime}</td>	
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffmodify">
								<c:if test="${staffmodify.userid==loop.categoryModifyStaffId}">
									${staffmodify.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>					
							<a title="编辑" target="navTab" href="${modify}?categoryId=${loop.categoryId}" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="${del}?categoryId=${loop.categoryId}&del=1" class="btnDel">删除</a>
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
