<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/business/admin/commodity/goods/lookup.action";
	
	request.setAttribute("action",action);
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
	<form rel="pagerForm" method="post" action="${action}" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					菜品名称：
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
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="100">商品名称</th>
				<th width="100">商品类型</th>
				<th width="100">商品描述</th>
				<th width="100">商品原价</th>
				<th width="100">商品优惠价</th>
				<th width="100">是否优惠</th>
				<th width="100">上级商品</th>
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
					<tr target="sid_goodsId" rel="${loop.businessGoods.id}">						
						<td>${loop.businessGoods.goodsName}</td>
						<td>
						<c:if test="${categoryList!=null}">
							<c:forEach items="${categoryList}" var="category">
								<c:if test="${category.id==loop.businessGoods.goodsCategoryId}">
									${category.categoryName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>${loop.businessGoods.goodsDescription}</td>
						<td>${loop.businessGoods.goodsOriginalPrice}</td>
						<td>${loop.businessGoods.goodsPurchasePrice}</td>
						<td>
						<c:if test="${isDiscountList!=null}">
							<c:forEach items="${isDiscountList}" var="discount">
								<c:if test="${discount.id==loop.businessGoods.isDiscountGoods}">
									${discount.statusName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>
						<c:if test="${goodsList!=null}">
							<c:forEach items="${goodsList}" var="goodsTemp">
								<c:if test="${goodsTemp.id==loop.businessGoods.goodsParentId}">
									${goodsTemp.goodsName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>	
						<td>${loop.businessGoods.createTime}</td>
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffcreate">
								<c:if test="${staffcreate.userid==loop.businessGoods.createStaffId}">
									${staffcreate.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>		
						<td>${loop.businessGoods.modifyTime}</td>	
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffmodify">
								<c:if test="${staffmodify.userid==loop.businessGoods.modifyStaffId}">
									${staffmodify.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>			
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({id:'${loop.businessGoods.id}',
							 goodsName:'${loop.businessGoods.goodsName}', goodsCategoryId:'${loop.businessGoods.goodsCategoryId}',
							 goodsOriginalPrice:'${loop.businessGoods.goodsOriginalPrice}', goodsPurchasePrice:'${loop.businessGoods.goodsPurchasePrice}',
							 goodsStatus:'${loop.businessGoods.goodsStatus}', isDiscountGoods:'${loop.businessGoods.isDiscountGoods}', 
							 goodsWeightId:'${loop.businessGoods.goodsWeightId}'})" title="查找带回">选择</a>
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
		<div class="pagination" targetType="dialog" totalCount="${totalCount}" numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}"></div>
	</div>
</div>
