<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/stat/business/orderGoodsPurchase.action";
	String modifyAction = basePath+"page/business/admin/commodity/goods/modifyGoodsBuyteam.action";
	
	
	request.setAttribute("action", action);
	request.setAttribute("modifyAction", modifyAction);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="goodsName" value="${goodsName}"/>
		<input type="hidden" name="categoryId" value="${categoryId}"/>
		<input type="hidden" name="parentCategoryId" value="${parentCategoryId}"/>
		<input type="hidden" name="operateStatus" value="${operateStatus}"/>
		<input type="hidden" name="createStartTime" value="${createStartTime}"/>
		<input type="hidden" name="createEndTime" value="${createEndTime}"/>
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
					 商品名
					<input type="text" name="goodsName" value="${goodsName}"/>
				</td>
				<td>
					商品类型：
				</td>
				<td>
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
					主商品类型：
				</td>
				<td>
					<select class="combox" name="parentCategoryId">
					<c:if test="${businessParentCategoryList!=null}">
					<option value="" selected>选择</option>
						<c:forEach items="${businessParentCategoryList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==parentCategoryId}">
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
					订单状态：
				</td>
				<td>
					<select class="combox" name="operateStatus">
					<c:if test="${operateStatusList!=null}">
					<option value="" selected>选择</option>
						<c:forEach items="${operateStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==operateStatus}">
									<option value=${loop.id} selected>${loop.statusCode}</option>
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
		<table class="searchContent">
			<tr>
				<td>
					订单下单日期（起始结束时间都要选）：从
					<input type="text" name="createStartTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd 00:00:00" value="${createStartTime}" class="date" readonly="true" />
					到
					<input type="text" name="createEndTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd 23:59:59" value="${createEndTime}" class="date" readonly="true" />
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
	<form method="post" action="${modifyAction}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="panelBar">
		<ul class="toolBar">		
			<li><a class="icon" href="javascript:$.printBox('w_list_print')"><span>打印采购单</span></a></li>	
			<li><a class="icon" href="/themes/mall/excel/business_buy.xls" target="dwzExport" targetType="navTab" title="要导出这些记录吗?"><span>导出EXCEL</span></a></li>			
		</ul>
	</div>
	<div id="w_list_print">
	<table class="table" width="100%" layoutH="1748">
		<thead>
			<tr>
				<th width="100" align="center">订单总售价</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${sumPrice}(元)</td>
			</tr>
		</tbody>
	</table>
	<table class="table" width="100%" layoutH="1748">
		<thead>
			<tr>
				<th width="100" align="center"><div style="font-size:18px">采购分组</div></th>
				<th width="100" align="center"><div style="font-size:18px">预计采购款总额</div></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${buyTeamTotal!=null}">
				
			    <c:forEach items="${buyTeamTotal}" var="entry">  
			          
			        <tr>
			        <c:forEach items="${buyTeamList}" var="statusList">
							<c:choose>
								<c:when test="${statusList.id==entry.key}">
									<td><div style="font-size:18px">${statusList.statusName}</div></td>
			   					</c:when>
			   					
							</c:choose>
					</c:forEach>
					<td>
						<div style="font-size:18px">
							<fmt:formatNumber value="${entry.value}" pattern="##.##" minFractionDigits="2" >
							</fmt:formatNumber>
						</div>
					</td>
			        </tr>
			    </c:forEach>  

			</c:if>
		</tbody>
	</table>
	<table class="table" width="100%" layoutH="318">
		<thead>
			<tr>
				<th width="100" align="center"><div style="font-size:18px">商品名称</div></th>
				<th width="100" align="center"><div style="font-size:18px">商品备注</div></th>
				<th width="100" align="center"><div style="font-size:18px">商品数量</div></th>
				<th width="100" align="center"><div style="font-size:18px">参考进价(单价)</div></th>
				<th width="100" align="center"><div style="font-size:18px">商品售价(单价)</div></th>
				<th width="100" align="center"><div style="font-size:18px">商品售价(总价)</div></th>
				<th width="100" align="center"><div style="font-size:18px">采购单价</div></th>
				<th width="130" align="center"><div style="font-size:18px">实际采购数量及金额</div></th>
				<th width="100" align="center"><div style="font-size:18px">商品分类</div></th>
				<th width="100" align="center"><div style="font-size:18px">商品主分类</div></th>
				<th width="100" align="center"><div style="font-size:18px">采购分组</div></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${contentList!=null}">
				<c:forEach items="${contentList}" var="loop">
					<tr>
						<td width="100" align="center">
							<div style="font-size:18px;height:61px;">
								<c:choose>
									<c:when test="${fn:length(loop[0]) > 7}">
										<c:set var="namelength" value="${fn:length(loop[0])}"/>
										<c:out value="${fn:substring(loop[0], 0, 7)}" /><br/>
										
										<c:out value="${fn:substring(loop[0],7,namelength)}" /><br/>
										
									</c:when>
									<c:otherwise>
										<c:out value="${loop[0]}" />
									</c:otherwise>
								</c:choose>
							</div>
							
						</td>
						<td width="100" align="center">
							<div style="font-size:18px">
								<c:choose>
									<c:when test="${fn:length(loop[9]) > 7}">
										<c:set var="namelength" value="${fn:length(loop[9])}"/>
										<c:out value="${fn:substring(loop[9], 0, 7)}" /><br/>
										
										<c:out value="${fn:substring(loop[9],7,namelength)}" /><br/>
										
									</c:when>
									<c:otherwise>
										<c:out value="${loop[9]}" />
									</c:otherwise>
								</c:choose>
							</div>
						</td>
						<td width="100" align="center"><div style="font-size:18px">${loop[1]}(${loop[2]})</div></td>
						<td width="100" align="center"><div style="font-size:18px">${loop[10]}</div></td>
						<td width="100" align="center"><div style="font-size:18px">${loop[3]}</div></td>
						<td width="100" align="center"><div style="font-size:18px">${loop[4]}</div></td>
						
						<td width="100" align="center"></td>
						<td width="130" align="center"><div style="font-size:18px">____斤____元</div></td>
						
						<td width="100" align="center"><div style="font-size:18px">${loop[5]}</div></td>
						<td width="100" align="center"><div style="font-size:18px">${loop[6]}</div></td>
						<td width="100" align="center">
							<input type="hidden" name="goodsBuyteamList[${loop[8]}].businessGoods.id" value="${loop[8]}" />
							<select  name="goodsBuyteamList[${loop[8]}].businessGoods.buyTeamId">
								<c:if test="${buyTeamList!=null}">
									<c:forEach items="${buyTeamList}" var="statusList">
										<c:choose>
											<c:when test="${statusList.id==loop[7]}">
												<option value=${statusList.id} selected>${statusList.statusName}</option>
			   								</c:when>
			   								<c:otherwise>
			   									<option value=${statusList.id}>${statusList.statusName}</option>
			   								</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
							</select>
							
							
						
						
						</td>
					<tr>
				</c:forEach>
			</c:if>	
		</tbody>
	</table>
	</div>
	
	<div class="panelBar">
		<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		<!-- 
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
		 -->
	</div>
	</form>
</div>
