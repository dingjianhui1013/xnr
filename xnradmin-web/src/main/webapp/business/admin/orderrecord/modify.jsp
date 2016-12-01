<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/business/admin/orderrecord/modify.action";
	String outplanLookup = basePath+"/page/wx/outplan/lookup.action";
	String goodsLookup = basePath+"page/business/admin/commodity/goods/lookup.action";
	String provinceLookup = basePath + "page/wx/admin/region/province/lookup.action";
	String cityLookup = basePath + "page/wx/admin/region/city/lookup.action";
	String areaLookup = basePath + "page/wx/admin/region/area/lookup.action";
	request.setAttribute("action",action);
	request.setAttribute("outplanLookup",outplanLookup);
	request.setAttribute("goodsLookup",goodsLookup);
	request.setAttribute("provinceLookup",provinceLookup);
	request.setAttribute("cityLookup",cityLookup);
	request.setAttribute("areaLookup",areaLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="orderRecordId" value="${orderRecord.id}" />
			<input type="hidden" name="clientUserId" value="${orderRecord.clientUserId}" />
			<input type="hidden" name="staffId" value="${orderRecord.staffId}" />
			<p>
				<label>所属省份：</label>
				<input id="province_id" class="readonly" name="provincePO.id" value="${orderRecord.provinceId}" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="provincePO.province" value="${orderRecord.provinceName}" type="text"/>		
				<a class="btnLook" href="${provinceLookup}" lookupGroup="provincePO">查找带回</a>	
			</p>
			<p>
				<label>所属城市：</label>
				<input id="city_id" class="readonly" name="cityPO.id" value="${orderRecord.cityId}" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="cityPO.city" value="${orderRecord.cityName}" type="text"/>	
				<a class="btnLook" href="${cityLookup}?provinceId={province_id}" lookupGroup="cityPO">查找带回</a>	
			</p>
			<p>
				<label>所属区/县：</label>
				<input id="area_id" class="readonly" name="areaPO.id" value="${orderRecord.areaId}" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="areaPO.area" value="${orderRecord.areaName}" type="text"/>	
				<a class="btnLook" href="${areaLookup}?provinceId={province_id}&cityId={city_id}" lookupGroup="areaPO">查找带回</a>	
			</p>
			<p>
				<label>详细地址：</label>
				<input name="userRealAddress" value="${orderRecord.userRealAddress}" type="text" size="25"/>			
			</p>
			<p>
				<label>收货人名称：</label>
				<input name="userRealName" value="${orderRecord.userRealName}" type="text" size="25"/>			
			</p>
			<p>
				<label>收货人电话：</label>
				<input name="userRealMobile" value="${orderRecord.userRealMobile}" type="text" size="25"/>			
			</p>
			<p><label>配送方式：</label>
				<select class="combox" name="logisticsCompanyId">
					<c:if test="${logisticsCompanyList!=null}">
						<c:forEach items="${logisticsCompanyList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==orderRecord.logisticsCompanyId}">
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
				<input type="text" name="requiredDeliveryTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd" value="${fn:substring(orderRecord.requiredDeliveryTime, 0, 10)}" class="date" readonly="true" />
			</p>
			<p><label>下单时间：</label>
				<input type="text" name="orderCreateTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd" value="${fn:substring(orderRecord.createTime, 0, 10)}" class="date" readonly="true" />
			</p>
			<p><label>支付方式：</label>
				<select class="combox" name="paymentProvider">
					<c:if test="${paymentProviderList!=null}">
						<c:forEach items="${paymentProviderList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==orderRecord.paymentProvider}">
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
								<c:when test="${loop.id==orderRecord.operateStatus}">
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
								<c:when test="${loop.id==orderRecord.paymentStatus}">
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
								<c:when test="${loop.id==orderRecord.deliveryStatus}">
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
					<dd><textarea name="userRealDescription" cols="60" rows="5">${orderRecord.userRealDescription}</textarea></dd>
				</dl>
			</fieldset>
			<fieldset>
				<legend>订单总价（原始价格为：${orderRecord.totalPrice}）：</legend>
				<input name="totalPrice" type="text"  size="25"/>注：修改订单总价时候用（单位：元）		
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
									<th type="lookup" name="goodsList[#index#].businessGoodsVO.businessGoods.goodsName"  lookupGroup="goodsList[#index#].businessGoodsVO.businessGoods" lookupUrl="${goodsLookup}" size="20"  fieldClass="required">选择商品</th>
									<th type="input" name="goodsList[#index#].businessOrderGoodsRelation.goodsCount" size="20" fieldClass="required number" >数量</th>
									<th type="text" name="goodsList[#index#].businessGoodsVO.businessGoods.goodsOriginalPrice" readonly>销售价</th>
									<th type="text" name="goodsList[#index#].businessGoodsVO.businessGoods.goodsPurchasePrice" readonly>进货价</th>						 
									<th type="del"  width="10%">操作</th>
								</tr>
							</thead>
							<tbody>
							<c:if test="${dvList!=null}">
									<c:forEach items="${dvList}" var="loop">
									<tr class="unitBox">
											<td>
												<input type="hidden" value="${loop.businessGoodsVO.businessGoods.id}" name="goodsList[${loop.businessGoodsVO.businessGoods.id}].businessGoodsVO.businessGoods.id">
												<input type="text" value="${loop.businessGoodsVO.businessGoods.goodsName}" name="goodsList[${loop.businessGoodsVO.businessGoods.id}].businessGoodsVO.businessGoods.goodsName">
												<a class="btnLook" title="查找带回" lookupgroup="goodsList[${loop.businessGoodsVO.businessGoods.id}].businessGoodsVO.businessGoods" href="${goodsLookup}">查找带回</a>
											</td>
											<td>
												<input id="" class="required number" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													class="required number"
													value="${loop.businessOrderGoodsRelation.goodsCount}" name="goodsList[${loop.businessGoodsVO.businessGoods.id}].businessOrderGoodsRelation.goodsCount">
											</td>
											<td>
											<c:choose>
												<c:when test="${loop.businessOrderGoodsRelation.currentPrice=='0.0'}">
													<input id="" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													value="0.0" name="goodsList[${loop.businessGoodsVO.businessGoods.id}].businessGoodsVO.businessGoods.goodsOriginalPrice">
												</c:when>
												<c:otherwise>
													<input id="" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													value="${loop.businessGoodsVO.businessGoods.goodsOriginalPrice}" name="goodsList[${loop.businessGoodsVO.businessGoods.id}].businessGoodsVO.businessGoods.goodsOriginalPrice">
												</c:otherwise>
											</c:choose>
											</td>	
											<td>
											<c:choose>
												<c:when test="${loop.businessOrderGoodsRelation.currentPrice=='0.0'}">
													<input id="" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													value="0.0" name="goodsList[${loop.businessGoodsVO.businessGoods.id}].businessGoodsVO.businessGoods.goodsPurchasePrice">
												</c:when>
												<c:otherwise>
													<input id="" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													value="${loop.businessGoodsVO.businessGoods.goodsPurchasePrice}" name="goodsList[${loop.businessGoodsVO.businessGoods.id}].businessGoodsVO.businessGoods.goodsPurchasePrice">
												</c:otherwise>
											</c:choose>
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
			
			<div class="divider"></div>
			<h3 class="contentTitle">分配列表</h3>

			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li class="selected"><a href="javascript:void(0)"><span>分配管理</span></a></li>

						</ul>
					</div>
				</div>

				<table class="list nowrap itemDetail" addButton="添加分配条目" width="100%"
					needCallback="1">
					<thead>
						<tr>
							<th type="text" width="4%" name="items[#index#].serno"
								id="items[#index#].serno" defaultVal="#index#" size="4"
								readonly="readonly" fieldClass="required digits">序号</th>
							<th type="text" width="15%" name="items[#index#].goodsName"
								id="items[#index#].goodsName" size="20" readonly="readonly"
								rows="3" cols="100">商品名称</th>
							<th nowrap type="lookup" width="15%"
								name="items[#index#].userName"
								id="items[#index#].userName" size="20" readonly="readonly"
								rows="3" cols="100" lookupPk="mesProductId"
								lookupGroup="items[#index#]"
								lookupUrl="${outplanLookup}" postField="keywords"
								fieldClass="required">农户</th>
							<th type="text" width="15%" name="items[#index#].amountValid"
								id="items[#index#].validAmount" size="20" readonly="readonly"
								rows="3" cols="100">可操作数量</th>
							<th type="text" width="15%" name="items[#index#].count"
								id="items[#index#].count" size="20" class="requird" rows="3"
								cols="100" onchang="judgeAmount(this)">分配数量</th>
							<th type="del" width="7%">操作</th>
						</tr>
					</thead>
					<tbody id="tecBody">


					</tbody>
				</table>
			</div>
			<br> <br> <br>

			<div style="height: 15px;"></div>

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