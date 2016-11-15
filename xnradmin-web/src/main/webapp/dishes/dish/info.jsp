<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/dishes/dish/info.action";
	String add = basePath+"page/dishes/dish/addinfo.action";
	String modify = basePath+"page/dishes/dish/modifyinfo.action";
	String del = basePath+"page/dishes/dish/del.action";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="dishName" value="${rawMaterialName}" />
		<input type="hidden" name="dishTypeId" value="${materialTypeId}" />
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
					菜品名称：
					<input type="text" name="dishName" value="${dishName}"/>
				</td>
				<td>
					<label>菜品类型：</label>
					<select class="combox" name="dishTypeId">
					<c:if test="${dishTypeList!=null}">
					<option value="" selected>选择</option>
						<c:forEach items="${dishTypeList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==dishTypeId}">
									<option value=${loop.id} selected>${loop.statusCode}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusCode}</option>
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
			<li><a class="add" href="${add}" target="navTab" rel="dish_add"><span>添加商品</span></a></li>				
			<li><a class="edit" href="${modify}?dishId={sid_dishId}" target="navTab" warn="请选择一个记录"><span>修改</span></a></li>
			<li class="line">line</li>			
			<li><a class="delete" href="${del}?dishId={sid_dishId}&del=1" target="ajaxTodo" title="删除某个产品"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="100">菜品名称</th>
				<th width="100">菜品类型</th>
				<th width="100">菜品介绍</th>
				<th width="100">烹饪方法</th>
				<th width="100">图片URL</th>
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
					<tr target="sid_dishId" rel="${loop.dishId}">						
						<td>${loop.dishName}</td>
						<td>
						<c:if test="${dishTypeList!=null}">
							<c:forEach items="${dishTypeList}" var="dish">
								<c:if test="${dish.id==loop.dishTypeId}">
									${dish.statusCode}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>${loop.introduction}</td>
						<td>${loop.cookingMethod}</td>
						<td>${loop.picUrl}</td>		
						<td>${loop.createTime}</td>
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffcreate">
								<c:if test="${staffcreate.userid==loop.createStaffId}">
									${staffcreate.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>		
						<td>${loop.modifyTime}</td>	
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffmodify">
								<c:if test="${staffmodify.userid==loop.modifyStaffId}">
									${staffmodify.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>					
						<td>					
							<a title="编辑" target="navTab" href="${modify}?dishId=${loop.dishId}" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="${del}?dishId=${loop.dishId}&del=1" class="btnDel">删除</a>
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
