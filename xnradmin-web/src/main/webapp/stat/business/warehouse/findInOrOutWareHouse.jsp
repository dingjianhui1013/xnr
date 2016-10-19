<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/stat/business/warehouse/findInOrOutWareHouse.action";
	String supplierStaffLookUp = basePath + "page/staff/lookup.action?queryOrgid=18";
	String purchaserStaffLookUp = basePath + "page/staff/lookup.action?queryOrgid=15";
	String goodsLookup = basePath+"page/business/admin/commodity/goods/lookup.action";
	
	request.setAttribute("action",action);
	request.setAttribute("supplierStaffLookUp",supplierStaffLookUp);
	request.setAttribute("purchaserStaffLookUp",purchaserStaffLookUp);
	request.setAttribute("goodsLookup",goodsLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="wareHouseOperateId" value="${wareHouseOperateId}" />
		<input type="hidden" name="wareHouseId" value="${wareHouseId}" />
		<input type="hidden" name="goodsId" value="${goodsId}" />
		<input type="hidden" name="categoryId" value="${categoryId}" />
		<input type="hidden" name="operationStatus" value="${operationStatus}" />
		<input type="hidden" name="reasonStatus" value="${reasonStatus}" />
		<input type="hidden" name="price" value="${price}" />
		<input type="hidden" name="count" value="${count}" />
		<input type="hidden" name="weightId" value="${weightId}" />
		<input type="hidden" name="supplierStaffId" value="${supplierStaffId}" />
		<input type="hidden" name="purchaserStaffId" value="${purchaserStaffId}" />
		<input type="hidden" name="remark" value="${remark}" />
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
				<td>
					<label>选择仓库操作：</label>
					<select class="combox" name="operationStatus">
						<c:if test="${operationStatusList!=null}">
							<option value="" selected>选择</option>
							<c:forEach items="${operationStatusList}" var="loop">
								<c:choose>
									<c:when test="${loop.id==operationStatus}">
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
				<td>
					<label>选择供货商：</label>
					<input class="readonly" name="supplierStaff.id" value="${supplierStaff.id}" readonly="readonly" type="hidden"/>
					<input readonly="readonly" name="supplierStaff.staffName" value="${supplierStaff.staffName}" type="text"/>
				</td>
				<td>
					<a class="btnLook" href="${supplierStaffLookUp}" lookupGroup="supplierStaff">查找带回</a>
				</td>
				<td>
					<label>选择采购商：</label>
					<input class="readonly" name="purchaserStaff.id" value="${purchaserStaff.id}" readonly="readonly" type="hidden"/>
					<input readonly="readonly" name="purchaserStaff.staffName" value="${purchaserStaff.staffName}"  type="text"/>
				</td>
				<td>
					<a class="btnLook" href="${purchaserStaffLookUp}" lookupGroup="purchaserStaff">查找带回</a>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td>
					<label>操作日期：</label>
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
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="100">仓库名称</th>
				<th width="100">商品名称</th>
				<th width="100">操作类型</th>
				<th width="100">总数量</th>
				<th width="100">单位名称</th>
				<th width="100">总金额</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${contentList!=null}">
				<c:forEach items="${contentList}" var="loop">
					<tr>
						<td width="100" align="center">${loop[0]}</td>
						<td width="100" align="center">${loop[1]}</td>
						<td width="100" align="center">${loop[3]}</td>
						<td width="100" align="center">${loop[4]}</td>
						<td width="100" align="center">${loop[2]}</td>
						<td width="100" align="center">${loop[5]}</td>
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
