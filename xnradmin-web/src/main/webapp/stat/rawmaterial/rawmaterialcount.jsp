<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/stat/rawmaterial/rawmaterialcount.action";

	request.setAttribute("action", action);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="purchaseId" value="${purchaseId}"/>
		<input type="hidden" name="clientUserId" value="${clientUserId}"/>
		<input type="hidden" name="orderRecordId" value="${orderRecordId}"/>
		<input type="hidden" name="orderNo" value="${orderNo}"/>
		<input type="hidden" name="goodsId" value="${goodsId}"/>
		<input type="hidden" name="goodsName" value="${goodsName}"/>
		<input type="hidden" name="goodsCategoryId" value="${goodsCategoryId}"/>
		<input type="hidden" name="goodsCategoryName" value="${goodsCategoryName}"/>
		<input type="hidden" name="goodsCount" value="${goodsCount}"/>
		<input type="hidden" name="dishId" value="${dishId}"/>
		<input type="hidden" name="dishName" value="${dishName}"/>
		<input type="hidden" name="dishTypeId" value="${dishTypeId}"/>
		<input type="hidden" name="dishTypeName" value="${dishTypeName}"/>
		<input type="hidden" name="dishCount" value="${dishCount}"/>
		<input type="hidden" name="rawMaterialId" value="${rawMaterialId}"/>
		<input type="hidden" name="rawMaterialName" value="${rawMaterialName}"/>
		<input type="hidden" name="rawMaterialTypeId" value="${rawMaterialTypeId}"/>
		<input type="hidden" name="rawMaterialTypeName" value="${rawMaterialTypeName}"/>
		<input type="hidden" name="weightId" value="${weightId}"/>
		<input type="hidden" name="weightName" value="${weightName}"/>
		<input type="hidden" name="number" value="${number}"/>
		<input type="hidden" name="createStartTime" value="${createStartTime}"/>
		<input type="hidden" name="createEndTime" value="${createEndTime}"/>
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
					 商品名
					<input type="text" name="goodsName" value="${goodsName}"/>
				</td>
				<td>
					 菜品名
					<input type="text" name="dishName" value="${dishName}"/>
				</td>
				<td>
					 原料名
					<input type="text" name="materialName" value="${materialName}"/>
				</td>
				<td>
					商品类型：
				</td>
				<td>
					<select class="combox" name="goodsCategoryId">
					<c:if test="${goodsCategoryList!=null}">
					<option value="" selected>选择</option>
						<c:forEach items="${goodsCategoryList}" var="loop">
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
					菜品类型：
				</td>
				<td>
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
				<td>
					原料类型：
				</td>
				<td>
					<select class="combox" name="rawMaterialTypeId">
					<c:if test="${rawMaterialTypeList!=null}">
					<option value="" selected>选择</option>
						<c:forEach items="${rawMaterialTypeList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==rawMaterialTypeId}">
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
		<table class="searchContent">
			<tr>
				<td>
					订单日期（起始结束时间都要选）：从
					<input type="text" name="createStartTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${createStartTime}" class="date" readonly="true" />
					到
					<input type="text" name="createEndTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${createEndTime}" class="date" readonly="true" />
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
	<table class="table" width="100%" layoutH="178">
		<thead>
			<tr>
				<th width="100" align="center">原料名称</th>
				<th width="100" align="center">原料数量</th>
				<th width="100" align="center">数量单位</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${contentList!=null}">
				<c:forEach items="${contentList}" var="loop">
					<tr>
						<td width="100" align="center">${loop[0]}</td>
						<td width="100" align="center">${loop[1]}</td>
						<td width="100" align="center">${loop[2]}</td>
					<tr>
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
