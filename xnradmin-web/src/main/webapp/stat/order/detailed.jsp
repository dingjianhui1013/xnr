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
			<li><a class="icon" href="javascript:$.printBox('w_list_print')"><span>打印配送单</span></a></li>
		</ul>
	</div>
<div class="pageFormContent" layoutH="56">
	
	
	<div id="w_list_print">
	<fieldset>
	<legend>小农人儿配送单</legend>
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		
			
			
			<table class="table" width="100%" >
			<tr>
				<td>
					<label>所属省份：${provinceName}</label>
				</td>
			</tr>
			<tr>
				<td>
				<label>所属城市：${cityName}</label>	
				</td>
			</tr>
			<tr>
				<td>
					<label>所属区/县：${areaName}</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>详细地址：${userRealAddress}</label>			
				</td>
			</tr>
			<tr>
				<td>
					<label>收货人名称：${userRealName}</label>	
				</td>
			</tr>
			<tr>
				<td>
					<label>收货人电话：${userRealMobile}</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>配送方式：
						<c:if test="${logisticsCompanyList!=null}">
							<c:forEach items="${logisticsCompanyList}" var="loop">
								<c:choose>
									<c:when test="${loop.id==logisticsCompanyId}">
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
									<c:when test="${loop.id==paymentProvider}">
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
									<c:when test="${loop.id==operateStatus}">
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
									<c:when test="${loop.id==paymentStatus}">
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
									<c:when test="${loop.id==deliveryStatus}">
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
					<label>备注：
						${userRealDescription}
					</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>订单总价：
						${totalPrice}
					</label>
				</td>
			</tr>
			
			<tr>
				<td>
					<label>送达时间：<%=StringHelper.getSystime("yyyy-MM-dd")%></label>
				</td>
			</tr>
			<tr>
				<td>
					<label>客户签字：</label>
				</td>
			</tr>
		
			</table>
			<div class="tabs">
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
									<th type="lookup" name="goodsList[#index#].goods.goodsName"  lookupGroup="goodsList[#index#].goods" lookupUrl="${goodsLookup}" size="20"  fieldClass="required">商品名称</th>						 
									<th type="input" name="goodsList[#index#].orderGoodsRelationGoodsCount" size="20" fieldClass="required number" >数量</th>
									<th type="text" name="goodsList[#index#].goods.goodsOriginalPrice" >原价</th>						 
									<th type="text" name="goodsList[#index#].goods.goodsPreferentialPrice" >优惠价</th>						 
									<th type="text" name="goodsList[#index#].goods.isDiscountGoods" >是否优惠</th>						 
									
								</tr>
							</thead>
							<tbody>
							<c:if test="${dvList!=null}">
									<c:forEach items="${dvList}" var="loop">
									<tr class="unitBox">
											<td>
												${loop.goods.goodsName}
											</td>
											<td>
												${loop.orderGoodsRelationGoodsCount}
											</td>
											<td>
											<c:choose>
												<c:when test="${loop.orderGoodsRelationCurrentPrice=='0.0'}">
													0.0
												</c:when>
												<c:otherwise>
													${loop.goods.goodsOriginalPrice}
												</c:otherwise>
											</c:choose>
											</td>
											<td>
											<c:choose>
												<c:when test="${loop.orderGoodsRelationCurrentPrice=='0.0'}">
													0.0
												</c:when>
												<c:otherwise>
													${loop.goods.goodsPreferentialPrice}
												</c:otherwise>
											</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${loop.goods.isDiscountGoods==120}">
														优惠价
   													</c:when>
   													<c:otherwise>
   														原价
   													</c:otherwise>
												</c:choose>
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
			
		
	</form>
	</fieldset>
	</div>
	
	</div>
</div>