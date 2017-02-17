<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="com.cntinker.util.*" 
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/wx/admin/orderrecord/modify.action";
	String goodsLookup = basePath+"page/wx/admin/commodity/goods/lookup.action";
	request.setAttribute("action",action);
	request.setAttribute("goodsLookup",goodsLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
			<li><a class="icon" href="javascript:$.printBox('w_list_print_business_order_detail')"><span>打印配送单</span></a></li>
			<li><a class="icon" href="/themes/mall/excel/business_order_detail.xls" target="dwzExport" targetType="navTab" title="要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	
<div class="pageFormContent" layoutH="56">
	
	
	
	<fieldset>
	
	<legend>小农人儿配送单</legend>
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		
			
			<div id="w_list_print_business_order_detail">
			<table class="table" width="100%" >
			<tr>
				<td>
					<label>商户：${staff.staffName}</label>	
				</td>
			</tr>
			<tr>
				<td>
					<label>
						送达时间：<fmt:formatDate value="${orderRecord.requiredDeliveryTime}" pattern="yyyy-MM-dd"/> 
						最早：${staff.theEarliestTime}点-最晚${staff.theLatestTime}点
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>详细地址：${orderRecord.userRealAddress}</label>			
				</td>
			</tr>
			<tr>
				<td>
					<label>收货人名称：${orderRecord.userRealName}</label>	
				</td>
			</tr>
			<tr>
				<td>
					<label>收货人电话：${orderRecord.userRealMobile}</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>客户经理：${leaderStaff.staffName}</label>	
				</td>
			</tr>
			<tr>
				<td>
					<label>客户经理电话：${leaderStaff.mobile}</label>	
				</td>
			</tr>
			<tr>
				<td>
					<label>备注：
						${orderRecord.userRealDescription}
					</label>
				</td>
			</tr>
			
			<tr>
				<td>
					<label>所属省份：${orderRecord.provinceName}</label>
				</td>
			</tr>
			<tr>
				<td>
				<label>所属城市：${orderRecord.cityName}</label>	
				</td>
			</tr>
			<tr>
				<td>
					<label>所属区/县：${orderRecord.areaName}</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>配送方式：
						<c:if test="${logisticsCompanyList!=null}">
							<c:forEach items="${logisticsCompanyList}" var="loop">
								<c:choose>
									<c:when test="${loop.id==orderRecord.logisticsCompanyId}">
										${loop.companyName}
									</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>支付方式：
						<c:if test="${paymentProviderList!=null}">
							<c:forEach items="${paymentProviderList}" var="loop">
								<c:choose>
									<c:when test="${loop.id==orderRecord.paymentProvider}">
										${loop.statusName}
									</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>处理状态：
						<c:if test="${operateStatusList!=null}">
							<c:forEach items="${operateStatusList}" var="loop">
								<c:choose>
									<c:when test="${loop.id==orderRecord.operateStatus}">
										${loop.statusName}
									</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>支付状态：
						<c:if test="${paymentStatusList!=null}">
							<c:forEach items="${paymentStatusList}" var="loop">
								<c:choose>
									<c:when test="${loop.id==orderRecord.paymentStatus}">
										${loop.statusName}
									</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>配送状态：
						<c:if test="${deliveryStatusList!=null}">
							<c:forEach items="${deliveryStatusList}" var="loop">
								<c:choose>
									<c:when test="${loop.id==orderRecord.deliveryStatus}">
										${loop.statusName}
									</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
					</label>
				</td>
			</tr>
			
			<tr>
				<td>
					<label>订单总价：
						${orderRecord.totalPrice}
					</label>
				</td>
			</tr>
			
			
			<tr>
				<td>
					<label>结算周期：
						<c:if test="${billList!=null}">
							<c:forEach items="${billList}" var="loop2">
								<c:choose>
									<c:when test="${loop2.id==staff.billTime}">
										${loop2.statusName}
									</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>客户确认签字：</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>送货人员签字：</label>
				</td>
			</tr>
			</table>
			
			<div class="tabs" width="100%">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li class="selected"><a href="javascript:void(0)"><span>货物清单</span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div>
						<table class="list nowrap" width="100%">
							<thead>
								<tr>
									<th type="text" size="10" name="goodsList[#index#].goods.goodsName" >商品名称</th>
									<th type="text" size="10" name="goodsList[#index#].goods.goodsDescription" >商品备注</th>
									<th type="text" name="goodsList[#index#].orderGoodsRelationGoodsCount" >数量</th>
									<th type="text" name="goodsList[#index#].orderGoodsRelationGoodsUnit" >单位</th>
									<th type="text" name="goodsList[#index#].orderGoodsRelationGoodsCategory" >分类</th>
									<th type="text" name="goodsList[#index#].orderGoodsRelationGoodsParentCategory" >主分类</th>
									<th type="text" name="goodsList[#index#].goods.goodsOriginalPrice" >单价</th>						 
									<th type="text" name="goodsList[#index#].goods.goodsSingleTotalPrice" >总价</th>
									
								</tr>
							</thead>
							<tbody>
							<c:if test="${dvList!=null}">
									<c:forEach items="${dvList}" var="loop">
									<tr class="unitBox">
											<td>
												${loop.businessGoodsVO.businessGoods.goodsName}
											</td>
											<td>
												${loop.businessGoodsVO.businessGoods.goodsDescription}
											</td>
											<td>
												${loop.businessOrderGoodsRelation.goodsCount}
											</td>
											<td>
												<c:if test="${weightList!=null}">
													<c:forEach items="${weightList}" var="loop2">
														<c:choose>
															<c:when test="${loop2.id==loop.businessOrderGoodsRelation.goodsWeightId}">
																(${loop2.weightName})
															</c:when>
														</c:choose>
													</c:forEach>
												</c:if>
											</td>
											<td>
												${loop.businessGoodsVO.businessCategory.categoryName}
											</td>
											<td>
												${loop.businessGoodsVO.businessParentCategory.categoryName}
											</td>
											<td>
											<c:choose>
												<c:when test="${loop.businessOrderGoodsRelation.currentPrice=='0.0'}">
													0.0
												</c:when>
												<c:otherwise>
													${loop.businessOrderGoodsRelation.currentPrice}
												</c:otherwise>
											</c:choose>
											</td>
											<td>
												<fmt:formatNumber value="${loop.businessOrderGoodsRelation.goodsCount*loop.businessOrderGoodsRelation.currentPrice}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber>    
												
											</td>
											
										</tr> 
									</c:forEach>
							</c:if>
							</tbody>
						</table>
					</div>							
				</div>
				<div class="tabsFooter">
					<div class="tabsFooterContent"></div>
				</div>
				
			</div>
			
	</div>
	</form>
		
	</fieldset>
	
	

	</div>
</div>