<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/business/admin/warehouse/wareHouseOperate/info.action";
	String add = basePath+"page/business/admin/warehouse/wareHouseOperate/addinfo.action";
	String modify = basePath+"page/business/admin/warehouse/wareHouseOperate/modifyinfo.action";
	String del = basePath+"page/business/admin/warehouse/wareHouseOperate/del.action";
	String supplierStaffLookUp = basePath + "page/staff/lookup.action?queryOrgid=18";
	String purchaserStaffLookUp = basePath + "page/staff/lookup.action?queryOrgid=15";
	String goodsLookup = basePath+"page/business/admin/commodity/goods/lookup.action";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${add}" target="navTab" rel="wareHouseOperate_add"><span>库存操作</span></a></li>				
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="100">仓库名称</th>
				<th width="100">商品名称</th>
				<th width="100">商品分类</th>
				<th width="100">商品价格</th>
				<th width="100">商品数量</th>
				<th width="100">商品单位</th>
				<th width="100">操作类型</th>
				<th width="100">操作理由</th>
				<th width="100">供货商</th>
				<th width="100">采购商</th>
				<th width="100">建立人员</th>
				<th width="100">建立时间</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_wareHouseOperateId" rel="${loop.businessWareHouseOperate.id}">
						<td>
						<c:if test="${businessWareHouseList!=null}">
							<c:forEach items="${businessWareHouseList}" var="businessWareHouse">
								<c:if test="${businessWareHouse.id==loop.businessWareHouseOperate.wareHouseId}">
									${businessWareHouse.wareHouseName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>				
						<td>
						<c:if test="${businessGoodsList!=null}">
							<c:forEach items="${businessGoodsList}" var="businessGoods">
								<c:if test="${businessGoods.id==loop.businessWareHouseOperate.goodsId}">
									${businessGoods.goodsName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>
						<c:if test="${businessCategoryList!=null}">
							<c:forEach items="${businessCategoryList}" var="businessCategory">
								<c:if test="${businessCategory.id==loop.businessWareHouseOperate.categoryId}">
									${businessCategory.categoryName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>${loop.businessWareHouseOperate.price}</td>
						<td>${loop.businessWareHouseOperate.count}</td>
						<td>
						<c:if test="${businessWeightList!=null}">
							<c:forEach items="${businessWeightList}" var="businessWeight">
								<c:if test="${businessWeight.id==loop.businessWareHouseOperate.weightId}">
									${businessWeight.weightName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>
						<c:if test="${operationStatusList!=null}">
							<c:forEach items="${operationStatusList}" var="operationStatus">
								<c:if test="${operationStatus.id==loop.businessWareHouseOperate.operationStatus}">
									${operationStatus.statusName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>
						<c:if test="${reasonStatusList!=null}">
							<c:forEach items="${reasonStatusList}" var="reasonStatus">
								<c:if test="${reasonStatus.id==loop.businessWareHouseOperate.reasonStatus}">
									${reasonStatus.statusName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="supplierStaff">
								<c:if test="${supplierStaff.orgid==18}">
									<c:if test="${supplierStaff.userid==loop.businessWareHouseOperate.supplierStaffId}">
										${supplierStaff.staffName}
   									</c:if>
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="purchaserStaff">
								<c:if test="${purchaserStaff.orgid==15}">
									<c:if test="${purchaserStaff.userid==loop.businessWareHouseOperate.purchaserStaffId}">
										${purchaserStaff.staffName}
   									</c:if>
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
