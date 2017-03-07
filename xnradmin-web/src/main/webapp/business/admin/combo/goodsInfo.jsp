<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String orderInfo = basePath+"page/business/admin/combo/orderInfo.action";
	request.setAttribute("orderInfo",orderInfo);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${orderInfo}">
		<input type="hidden" name="orderNo" value="${orderNo}" />
		<input type="hidden" name="createStartTime" value="${createStartTime}" />
		<input type="hidden" name="createEndTime" value="${createEndTime}" />
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />		
		<input type="hidden" name="staff.staffName" value="${staff.staffName}" />
</form>
<div class="pageContent">
	<table class="table" width="100%" layoutH="65">
		<thead>
			<tr>
				<th width="50">商品名称</th>
				<th width="50">套餐商品总数</th>
				<th width="50">已下单商品数</th>
			</tr>
		</thead>
		<tbody>
			 <c:if test="${comboUserGoodsVOList!=null}">
			 ${loop.hasAllocateNumber}
				<c:forEach items="${comboUserGoodsVOList}" var="loop" varStatus="status">
					<tr target="sid_comboUserId" rel="${loop.comboGoodsVO.businessGoods.id}">
						<td>${loop.comboGoodsVO.businessGoods.goodsName}</td>
						<td>${loop.comboGoodsVO.comboGoods.goodsCount}</td>	
						<td>${loop.hasAllocateNumber}</td>
					</tr>
				</c:forEach>
			</c:if>		 
		</tbody>
	</table>
	<%-- <div class="panelBar">
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
	</div> --%>
</div>
