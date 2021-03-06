<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/business/admin/commodity/goods/info.action";
	String add = basePath+"page/business/admin/commodity/goods/addinfo.action";
	String modify = basePath+"page/business/admin/commodity/goods/modifyinfo.action";
	String del = basePath+"page/business/admin/commodity/goods/del.action";
	String exportGoods = basePath+"page/business/admin/commodity/goods/createGoodsExcel.action";
	String outputYsmc = basePath+"page/business/admin/commodity/goods/outputYsmc.action";
	String outputFdlm = basePath+"page/business/admin/commodity/goods/outputFdlm.action";
	String outputLnFile = basePath+"page/business/admin/commodity/goods/outputLn.action";
	String uploadGoodsInfo = basePath+"page/business/admin/commodity/goods/uploadGoodsInfo.action";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
	request.setAttribute("exportGoods",exportGoods);
	request.setAttribute("outputYsmc", outputYsmc);
	request.setAttribute("outputFdlm", outputFdlm);
	request.setAttribute("outputLnFile", outputLnFile);
	request.setAttribute("uploadGoodsInfo", uploadGoodsInfo);
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${add}" target="navTab" rel="goods_add"><span>添加商品</span></a></li>				
			<li><a class="edit" href="${modify}?goodsId={sid_goodsId}" target="navTab" warn="请选择一个记录"><span>修改</span></a></li>
			<li class="line">line</li>			
			<li><a class="delete" href="${del}?goodsId={sid_goodsId}&del=1" target="ajaxTodo" title="删除某个商品"><span>删除</span></a></li>
			<li class="line">line</li>	
			<li><a class="icon" href="${exportGoods}" target="dwzExport" targetType="navTab" title="要导出这些记录吗?"><span>导出小农人商品列表</span></a></li>
			<li><a class="icon" href="${outputYsmc}" target="dwzExport" targetType="navTab" title="要导出这些记录吗?"><span>导出ysmc商品列表</span></a></li>
			<li><a class="icon" href="${outputFdlm}" target="dwzExport" targetType="navTab" title="要导出这些记录吗?"><span>导出fdlm商品列表</span></a></li>
			<li><a class="icon" href="${outputLnFile}" target="dwzExport" targetType="navTab" title="要导出这些记录吗?"><span>导出ln商品列表</span></a></li>
			<li class="line">line</li>	
			<li><a class="edit" href="${uploadGoodsInfo}" target="navTab" ><span>批量上传更新商品</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="125">商品名称</th>
				<th width="35">商品单位</th>
				<th width="45">商品类型</th>
				<th width="75">商品描述</th>
				<!-- 
				<th width="100">商品小图片</th>
				<th width="100">商品大图片</th>
				 -->
				<th width="35">商品售价</th>
				<th width="35">商品进货价</th>
				<th width="35">是否上架</th>
				<th width="35">录入来源</th>
				<th width="35">采购分组</th>
				<!-- 
				<th width="35">建立人员</th>
				<th width="45">建立时间</th>
				 -->
				<th width="35">修改人员</th>
				<th width="45">修改时间</th>
				<th width="55">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_goodsId" rel="${loop.businessGoods.id}">						
						<td>${loop.businessGoods.goodsName}</td>
						<td>
							<c:if test="${weightList!=null}">
								<c:forEach items="${weightList}" var="loop2">
									<c:choose>
										<c:when test="${loop2.id==loop.businessGoods.goodsWeightId}">
											${loop2.weightName}
		   								</c:when>
									</c:choose>
		   						</c:forEach>
							</c:if>
						</td>
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
						<!-- 
						<td><img src="${loop.businessGoods.goodsLogo}" width="100%" height="100%" alt="" /></td>
						<td><img src="${loop.businessGoods.goodsBigLogo}" width="100%" height="100%" alt="" /></td>
						 -->
						<td>${loop.businessGoods.goodsOriginalPrice}</td>
						<td>${loop.businessGoods.goodsPurchasePrice}</td>
						<!-- <td>
						<c:if test="${isDiscountList!=null}">
							<c:forEach items="${isDiscountList}" var="discount">
								<c:if test="${discount.id==loop.businessGoods.isDiscountGoods}">
									${discount.statusName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td> -->
						<td>
						<c:if test="${goodsStatusList!=null}">
							<c:forEach items="${goodsStatusList}" var="statusList">
								<c:if test="${statusList.id==loop.businessGoods.goodsStatus}">
									${statusList.statusName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>
							<c:if test="${goodsSourceList!=null}">
								<c:forEach items="${goodsSourceList}" var="statusList">
									<c:if test="${statusList.id==loop.businessGoods.sourceId}">
										${statusList.statusName}
	   								</c:if>
								</c:forEach>
							</c:if>
						</td>
						<td>
							<c:if test="${buyTeamList!=null}">
								<c:forEach items="${buyTeamList}" var="statusList">
									<c:if test="${statusList.id==loop.businessGoods.buyTeamId}">
										${statusList.statusName}
	   								</c:if>
								</c:forEach>
							</c:if>
						</td>
						<!-- 
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffcreate">
								<c:if test="${staffcreate.userid==loop.businessGoods.createStaffId}">
									${staffcreate.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>
						<td>${loop.businessGoods.createTime}</td>
						 -->
						<td>
						<c:if test="${staffList!=null}">
							<c:forEach items="${staffList}" var="staffmodify">
								<c:if test="${staffmodify.userid==loop.businessGoods.modifyStaffId}">
									${staffmodify.staffName}
   								</c:if>
							</c:forEach>
						</c:if>
						</td>		
						<td>${loop.businessGoods.modifyTime}</td>		
						<td>			
							<a title="编辑" target="navTab" href="${modify}?goodsId=${loop.businessGoods.id}" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="${del}?goodsId=${loop.businessGoods.id}&del=1" class="btnDel">删除</a>
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
