<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/farmerQrCode/info.action";
	
	request.setAttribute("action",action);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">			
		
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${action}" method="post">
	<div class="searchBar">	
		<table class="searchContent">
			<tr>
				<td>
					用户名称
					<input type="text" name="query.farmerName " value="${query.farmerName }"/>
				</td>	
				<td>
					用户ID
					<input type="text" name="query.farmerId" value="${query.farmerId}"/>
				</td>
				<td>
					菜品名称
					<input type="text" name="query.goodsId" value="${query.goodsName}"/>
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
				<th width="25"">ID</th>
				<th width="45">农户姓名</th>
				<th width="45">菜品名称</th>
				<th width="45">二维码</th>
				<th width="45">操作</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${farmerQrCodeVo!=null}">
				<c:forEach items="${farmerQrCodeVo}" var="loop">
					<tr target="sid_menuid" rel="${loop.farmerQrCode.id}">								
						<td>${loop.farmerQrCode.id}</td>
						<td>${loop.farmer.userName}</td>
						<td>${loop.businessGoods.goodsName}</td>
						<td><image src="${loop.farmerQrCode.qrCodeUrl}" /></td>
						<td><a title="商品" target="dialog" href="page/wx/farmer/anthinfo.action?farmerId=${loop.farmerQrCode.id}" class="btnAuth">商品</a></td>	
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