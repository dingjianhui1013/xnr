<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/wx/admin/commodity/goods/info.action";
	String add = basePath+"page/wx/admin/commodity/goods/addinfo.action";
	String modify = basePath+"page/wx/admin/commodity/goods/modifyinfo.action";
	String del = basePath+"page/wx/admin/commodity/goods/del.action";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="goodsName" value="${goodsName}" />
		<input type="hidden" name="goodsCategoryId" value="${goodsCategoryId}" />
		<input type="hidden" name="goodsStatus" value="${goodsStatus}" />
		<input type="hidden" name="isDiscountGoods" value="${isDiscountGoods}" />
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
					商品名称：
					<input type="text" name="goodsName" value="${goodsName}"/>
				</td>
				<td>
					<label>商品类型：</label>
					<select class="combox" name="goodsCategoryId">
					<c:if test="${categoryList!=null}">
					<option value="" selected>选择</option>
						<c:forEach items="${categoryList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==goodsCategoryId}">
									<option value=${loop.id} selected>${loop.categoryName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.categoryName}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
					</select>
				</td>
				<td>
					<label>商品状态：</label>
					<select class="combox" name="goodsStatus">
					<c:if test="${goodsStatusList!=null}">
					<option value="" selected>选择</option>
						<c:forEach items="${goodsStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==goodsStatus}">
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
			<li><a class="add" href="${add}" target="navTab" rel="goods_add"><span>添加商品</span></a></li>				
			<li><a class="edit" href="${modify}?goodsId={sid_goodsId}" target="navTab" warn="请选择一个记录"><span>修改</span></a></li>
			<li class="line">line</li>			
			<li><a class="delete" href="${del}?goodsId={sid_goodsId}&del=1" target="ajaxTodo" title="删除某个商品"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="100">商品名称</th>
				<th width="100">商品类型</th>
				<th width="100">商品描述</th>
				<th width="100">商品小图片</th>
				<th width="100">商品大图片</th>
				<th width="100">商品原价</th>
				<th width="100">商品优惠价</th>
				<th width="100">是否优惠</th>
				<th width="100">是否上架</th>
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
					<tr target="sid_goodsId" rel="${loop.goodsId}">						
						<td>${loop.goodsName}</td>
						<td>
						<c:if test="${categoryList!=null}">
							<c:forEach items="${categoryList}" var="category">
								<c:if test="${category.id==loop.goodsCategoryId}">
									${category.categoryName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>${loop.goodsDescription}</td>
						<td><img src="${loop.goodsLogo}" width="100%" height="100%" alt="" /></td>
						<td><img src="${loop.goodsBigLogo}" width="100%" height="100%" alt="" /></td>
						<td>${loop.goodsOriginalPrice}</td>
						<td>${loop.goodsPreferentialPrice}</td>
						<td>
						<c:if test="${isDiscountList!=null}">
							<c:forEach items="${isDiscountList}" var="discount">
								<c:if test="${discount.id==loop.isDiscountGoods}">
									${discount.statusCode}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>${loop.goodsStatusCode}</td>	
						
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffcreate">
								<c:if test="${staffcreate.userid==loop.goodsCreateStaffId}">
									${staffcreate.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>${loop.goodsCreateTime}</td>
						
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffmodify">
								<c:if test="${staffmodify.userid==loop.goodsModifyStaffId}">
									${staffmodify.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>		
						<td>${loop.goodsModifyTime}</td>		
						<td>			
							<a title="编辑" target="navTab" href="${modify}?goodsId=${loop.goodsId}" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="${del}?goodsId=${loop.goodsId}&del=1" class="btnDel">删除</a>
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
