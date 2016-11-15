<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/business/admin/warehouse/wareHouseInvRel/info.action";
	String add = basePath+"page/business/admin/warehouse/wareHouseInvRel/addinfo.action";
	String modify = basePath+"page/business/admin/warehouse/wareHouseInvRel/modifyinfo.action";
	String del = basePath+"page/business/admin/warehouse/wareHouseInvRel/del.action";
	String goodsLookup = basePath+"page/business/admin/commodity/goods/lookup.action";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
	request.setAttribute("goodsLookup",goodsLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="wareHouseInvRelWareHouseId" value="${wareHouseInvRelWareHouseId}" />
		<input type="hidden" name="wareHouseInvRelGoodsId" value="${wareHouseInvRelGoodsId}" />
		<input type="hidden" name="wareHouseInvRelCount" value="${wareHouseInvRelCount}" />
		<input type="hidden" name="wareHouseInvRelGoodsCategoryId" value="${wareHouseInvRelGoodsCategoryId}" />
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
					<label>选择仓库：</label>
					<select class="combox" name="wareHouseId">
						<c:if test="${businessWareHouseList!=null}">
							<option value="" selected>选择</option>
							<c:forEach items="${businessWareHouseList}" var="loop">
								<c:choose>
									<c:when test="${loop.id==wareHouseId}">
										<option value=${loop.id} selected>${loop.wareHouseName}</option>
   									</c:when>
   									<c:otherwise>
   										<option value=${loop.id}>${loop.wareHouseName}</option>
   									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</select>
				</td>
				<td>
					<label>选择分类：</label>
					<select class="combox" name="categoryId">
						<c:if test="${businessCategoryList!=null}">
							<option value="" selected>选择</option>
							<c:forEach items="${businessCategoryList}" var="loop">
								<c:choose>
									<c:when test="${loop.id==categoryId}">
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
			</tr>
		</table>
		<table>
			<tr>
				<td>
					<label>选择商品：</label>
					<input class="readonly" name="goods.id" value="${goods.id}" readonly="readonly" type="hidden"/>
					<input readonly="readonly" name="goods.goodsName" value="${goods.goodsName}" type="text"/>
				</td>
				<td>	
					<a class="btnLook" href="${goodsLookup}" lookupGroup="goods">查找带回</a>	
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
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="100">仓库名称</th>
				<th width="100">商品名称</th>
				<th width="100">商品分类</th>
				<th width="100">商品数量</th>
				<th width="100">商品单位</th>
				<th width="100">建立人员</th>
				<th width="100">建立时间</th>
				<th width="100">修改人员</th>
				<th width="100">修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_wareHouseInvRelId" rel="${loop.businessWareHouseInvRel.id}">
						<td>
						<c:if test="${businessWareHouseList!=null}">
							<c:forEach items="${businessWareHouseList}" var="businessWareHouse">
								<c:if test="${businessWareHouse.id==loop.businessWareHouseInvRel.wareHouseId}">
									${businessWareHouse.wareHouseName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>				
						<td>
						<c:if test="${businessGoodsList!=null}">
							<c:forEach items="${businessGoodsList}" var="businessGoods">
								<c:if test="${businessGoods.id==loop.businessWareHouseInvRel.goodsId}">
									${businessGoods.goodsName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>
						<c:if test="${businessCategoryList!=null}">
							<c:forEach items="${businessCategoryList}" var="businessCategory">
								<c:if test="${businessCategory.id==loop.businessCategory.id}">
									${businessCategory.categoryName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>${loop.businessWareHouseInvRel.count}</td>
						<td>
						<c:if test="${businessWeightList!=null}">
							<c:forEach items="${businessWeightList}" var="businessWeight">
								<c:if test="${businessWeight.id==loop.businessWareHouseInvRel.weightId}">
									${businessWeight.weightName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffcreate">
								<c:if test="${staffcreate.userid==loop.businessWareHouse.createStaffId}">
									${staffcreate.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>${loop.businessWareHouse.createTime}</td>
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffmodify">
								<c:if test="${staffmodify.userid==loop.businessWareHouse.modifyStaffId}">
									${staffmodify.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>${loop.businessWareHouse.modifyTime}</td>	
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
