<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/wx/admin/orderrecord/add.action";
	String goodsLookup = basePath+"page/wx/admin/commodity/goods/lookup.action";
	String provinceLookup = basePath + "page/wx/admin/region/province/lookup.action";
	String cityLookup = basePath + "page/wx/admin/region/city/lookup.action";
	String areaLookup = basePath + "page/wx/admin/region/area/lookup.action";
	String del = basePath + "page/client/clientuserregioninfo/del.action";
	
	request.setAttribute("action",action);
	request.setAttribute("goodsLookup",goodsLookup);
	request.setAttribute("provinceLookup",provinceLookup);
	request.setAttribute("cityLookup",cityLookup);
	request.setAttribute("areaLookup",areaLookup);
	request.setAttribute("del",del);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="clientUserId" value="${clientUserInfoId}" />
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>
<script type="text/javascript">
	
</script>

<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		<input type="hidden" name="clientUserId" value="${clientUserId}" />
		<c:choose>
			<c:when test="${!empty clientUserRegionInfoList}">
				<c:forEach items="${clientUserRegionInfoList}" varStatus="s" var="loop">
					<c:choose>
						<c:when test="${s.index==0}">
							<fieldset><input type="radio" name="clientUserRegionInfoId" value="${loop.id}" checked="checked"> 地址：${loop.provinceName}${loop.cityName}${loop.areaName}${loop.userRealAddress}。
							收货人：${loop.userRealName}。收货电话：${loop.userRealMobile}<a title="删除" target="ajaxTodo" href="${del}?clientUserRegionInfoId=${loop.id}&del=1" class="btnDel">删除</a></fieldset>
						</c:when>
						<c:otherwise>
							<fieldset><input type="radio" name="clientUserRegionInfoId" value="${loop.id}"> 地址：${loop.provinceName}${loop.cityName}${loop.areaName}${loop.userRealAddress}。
							收货人：${loop.userRealName}。收货电话：${loop.userRealMobile}<a title="删除" target="ajaxTodo" href="${del}?clientUserRegionInfoId=${loop.id}&del=1" class="btnDel">删除</a></fieldset></fieldset>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<fieldset><input type="radio" name="clientUserRegionInfoId" value="new">使用新地址</fieldset>
			</c:when>
			<c:otherwise>
   				<fieldset><input type="radio" name="clientUserRegionInfoId" value="new" checked="checked">使用新地址</fieldset>
   			</c:otherwise>
		</c:choose>
			<p>
				<label>所属省份：</label>
				<input id="province_id" class="readonly" name="provincePO.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="provincePO.province" type="text"/>		
				<a class="btnLook" href="${provinceLookup}" lookupGroup="provincePO">查找带回</a>	
			</p>
			<p>
				<label>所属城市：</label>
				<input id="city_id" class="readonly" name="cityPO.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="cityPO.city" type="text"/>	
				<a class="btnLook" href="${cityLookup}?provinceId={province_id}" lookupGroup="cityPO">查找带回</a>	
			</p>
			<p>
				<label>所属区/县：</label>
				<input id="area_id" class="readonly" name="areaPO.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="areaPO.area" type="text"/>	
				<a class="btnLook" href="${areaLookup}?provinceId={province_id}&cityId={city_id}" lookupGroup="areaPO">查找带回</a>	
			</p>
			<p>
				<label>详细地址：</label>
				<input name="userRealAddress" type="text" size="25"/>			
			</p>
			<p>
				<label>收货人名称：</label>
				<input name="userRealName" type="text" size="25"/>			
			</p>
			<p>
				<label>收货人电话：</label>
				<input name="userRealMobile" type="text" size="25"/>			
			</p>
			<p><label>配送方式：</label>
				<select class="combox" name="logisticsCompanyId">
					<c:if test="${logisticsCompanyList!=null}">
						<c:forEach items="${logisticsCompanyList}" var="loop">
							<option value=${loop.id}>${loop.companyName}</option>
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
							<option value=${loop.id}>${loop.statusName}</option>
						</c:forEach>
					</c:if>
				</select>
			</p>
			<p><label></label>
				
			</p>
			<fieldset>
				<legend>备注：</legend>
				<dl class="nowrap">
					<dd><textarea name="userDescription" cols="60" rows="5"></textarea></dd>
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
							<tbody></tbody>
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