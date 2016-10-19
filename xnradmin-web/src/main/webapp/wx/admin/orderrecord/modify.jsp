<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/wx/admin/orderrecord/modify.action";
	String goodsLookup = basePath+"page/wx/admin/commodity/goods/lookup.action";
	String provinceLookup = basePath + "page/wx/admin/region/province/lookup.action";
	String cityLookup = basePath + "page/wx/admin/region/city/lookup.action";
	String areaLookup = basePath + "page/wx/admin/region/area/lookup.action";
	request.setAttribute("action",action);
	request.setAttribute("goodsLookup",goodsLookup);
	request.setAttribute("provinceLookup",provinceLookup);
	request.setAttribute("cityLookup",cityLookup);
	request.setAttribute("areaLookup",areaLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="orderRecordId" value="${orderRecordId}" />
			<input type="hidden" name="clientUserId" value="${clientUserId}" />
			<p>
				<label>所属省份：</label>
				<input id="province_id" class="readonly" name="provincePO.id" value="${provinceId}" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="provincePO.province" value="${provinceName}" type="text"/>		
				<a class="btnLook" href="${provinceLookup}" lookupGroup="provincePO">查找带回</a>	
			</p>
			<p>
				<label>所属城市：</label>
				<input id="city_id" class="readonly" name="cityPO.id" value="${cityId}" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="cityPO.city" value="${cityName}" type="text"/>	
				<a class="btnLook" href="${cityLookup}?provinceId={province_id}" lookupGroup="cityPO">查找带回</a>	
			</p>
			<p>
				<label>所属区/县：</label>
				<input id="area_id" class="readonly" name="areaPO.id" value="${areaId}" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="areaPO.area" value="${areaName}" type="text"/>	
				<a class="btnLook" href="${areaLookup}?provinceId={province_id}&cityId={city_id}" lookupGroup="areaPO">查找带回</a>	
			</p>
			<p>
				<label>详细地址：</label>
				<input name="userRealAddress" value="${userRealAddress}" type="text" size="25"/>			
			</p>
			<p>
				<label>收货人名称：</label>
				<input name="userRealName" value="${userRealName}" type="text" size="25"/>			
			</p>
			<p>
				<label>收货人电话：</label>
				<input name="userRealMobile" value="${userRealMobile}" type="text" size="25"/>			
			</p>
			<p><label>配送方式：</label>
				<select class="combox" name="logisticsCompanyId">
					<c:if test="${logisticsCompanyList!=null}">
						<c:forEach items="${logisticsCompanyList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==logisticsCompanyId}">
									<option value="${loop.id}" selected>${loop.companyName}</option>
								</c:when>
								<c:otherwise>
									<option value="${loop.id}">${loop.companyName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</p>
			<p><label>配送时间：</label>
				<input type="text" name="userRealTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${userRealTime}" class="date" readonly="true" />
			</p>
			<p><label>支付方式：</label>
				<select class="combox" name="paymentProvider">
					<c:if test="${paymentProviderList!=null}">
						<c:forEach items="${paymentProviderList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==paymentProvider}">
									<option value="${loop.id}" selected>${loop.statusName}</option>
								</c:when>
								<c:otherwise>
									<option value="${loop.id}">${loop.statusName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</p>
			<p><label>处理状态：</label>
				<select class="combox" name="operateStatus">
					<c:if test="${operateStatusList!=null}">
						<c:forEach items="${operateStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==operateStatus}">
									<option value="${loop.id}" selected>${loop.statusName}</option>
								</c:when>
								<c:otherwise>
									<option value="${loop.id}">${loop.statusName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</p>
			<p><label>支付状态：</label>
				<select class="combox" name="paymentStatus">
					<c:if test="${paymentStatusList!=null}">
						<c:forEach items="${paymentStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==paymentStatus}">
									<option value="${loop.id}" selected>${loop.statusName}</option>
								</c:when>
								<c:otherwise>
									<option value="${loop.id}">${loop.statusName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</p>
			<p><label>配送状态：</label>
				<select class="combox" name="deliveryStatus">
					<c:if test="${deliveryStatusList!=null}">
						<c:forEach items="${deliveryStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==deliveryStatus}">
									<option value="${loop.id}" selected>${loop.statusName}</option>
								</c:when>
								<c:otherwise>
									<option value="${loop.id}">${loop.statusName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</p>
			<p><label></label>
			</p>
			<fieldset>
				<legend>备注：</legend>
				<dl class="nowrap">
					<dd><textarea name="userRealDescription" cols="60" rows="5">${userRealDescription}</textarea></dd>
				</dl>
			</fieldset>
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li class="selected"><a href="javascript:void(0)"><span>商品表管理</span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div>
						<table class="list nowrap itemDetail" addButton="添加商品" width="100%">
							<thead>
								<tr>
									<th type="lookup" name="goodsList[#index#].goods.goodsName"  lookupGroup="goodsList[#index#].goods" lookupUrl="${goodsLookup}" size="20"  fieldClass="required">选择商品</th>						 
									<th type="input" name="goodsList[#index#].orderGoodsRelationGoodsCount" size="20" fieldClass="required number" >数量</th>
									<th type="text" name="goodsList[#index#].goods.goodsOriginalPrice" >原价</th>						 
									<th type="text" name="goodsList[#index#].goods.goodsPreferentialPrice" >优惠价</th>						 
									<th type="text" name="goodsList[#index#].goods.isDiscountGoods" >是否优惠</th>						 
									<th type="del"  width="10%">操作</th>
								</tr>
							</thead>
							<tbody>
							<c:if test="${dvList!=null}">
									<c:forEach items="${dvList}" var="loop">
									<tr class="unitBox">
											<td>
												<input type="hidden" value="${loop.goods.id}" name="goodsList[${loop.goods.id}].goods.id">
												<input type="text" value="${loop.goods.goodsName}" name="goodsList[${loop.goods.id}].goods.goodsName">
												<a class="btnLook" title="查找带回" lookupgroup="goodsList[${loop.goods.id}].goods" href="${goodsLookup}">查找带回</a>
											</td>
											<td>
												<input id="" class="required number" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													class="required number"
													value="${loop.orderGoodsRelationGoodsCount}" name="goodsList[${loop.goods.id}].orderGoodsRelationGoodsCount">
											</td>
											<td>
											<c:choose>
												<c:when test="${loop.orderGoodsRelationCurrentPrice=='0.0'}">
													<input id="" class="required number" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													class="required number"
													value="0.0" name="goodsList[${loop.goods.id}].goods.goodsOriginalPrice">
												</c:when>
												<c:otherwise>
													<input id="" class="required number" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													class="required number"
													value="${loop.goods.goodsOriginalPrice}" name="goodsList[${loop.goods.id}].goods.goodsOriginalPrice">
												</c:otherwise>
											</c:choose>
											</td>
											<td>
											<c:choose>
												<c:when test="${loop.orderGoodsRelationCurrentPrice=='0.0'}">
													<input id="" class="required number" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													class="required number"
													value="0.0" name="goodsList[${loop.goods.id}].goods.goodsPreferentialPrice">
												</c:when>
												<c:otherwise>
													<input id="" class="required number" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													class="required number"
													value="${loop.goods.goodsPreferentialPrice}" name="goodsList[${loop.goods.id}].goods.goodsPreferentialPrice">
												</c:otherwise>
											</c:choose>
											</td>
											<td>
												<input id="" class="required number" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													class="required number"
													value="${loop.goods.isDiscountGoods}" name="goodsList[${loop.goods.id}].goods.isDiscountGoods">
											</td>
											<td>
												<a class="btnDel " href="javascript:void(0)">删除</a>
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
			<div class="formBar">
				<ul>				
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</div>
	</form>

</div>