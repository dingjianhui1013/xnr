<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String actionForm = basePath + "page/business/admin/warehouse/wareHouseOperate/add.action";
	String supplierStaffLookUp = basePath + "page/staff/lookup.action?queryOrgid=18";
	String purchaserStaffLookUp = basePath + "page/staff/lookup.action?queryOrgid=15";
	String goodsLookup = basePath+"page/business/admin/commodity/goods/lookup.action";
	request.setAttribute("actionForm", actionForm);
	request.setAttribute("supplierStaffLookUp",supplierStaffLookUp);
	request.setAttribute("purchaserStaffLookUp",purchaserStaffLookUp);
	request.setAttribute("goodsLookup", goodsLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<fieldset>
				<label>选择仓库：</label>
				<select class="combox" name="wareHouseId">
					<c:if test="${businessWareHouseList!=null}">
						<c:forEach items="${businessWareHouseList}" var="loop">
							<option value=${loop.id}>${loop.wareHouseName}</option>
						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
				<label>选择商品：</label>
				<input class="readonly" name="goods.id" readonly="readonly" type="hidden"/>
				<input class="readonly" name="goods.goodsCategoryId" readonly="readonly" type="hidden"/>
				<input class="readonly" name="goods.goodsWeightId" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="goods.goodsName" type="text"/>		
				<a class="btnLook" href="${goodsLookup}" lookupGroup="goods">查找带回</a>	
			</fieldset>
			<fieldset>
				<label>商品价格：</label>
				<input name="price" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品数量：</label>
				<input name="count" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>选择供货商：</label>
				<input class="readonly" name="supplierStaff.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="supplierStaff.staffName" type="text"/>		
				<a class="btnLook" href="${supplierStaffLookUp}" lookupGroup="supplierStaff">查找带回</a>	
			</fieldset>
			<fieldset>
				<label>选择采购商：</label>
				<input class="readonly" name="purchaserStaff.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="purchaserStaff.staffName" type="text"/>		
				<a class="btnLook" href="${purchaserStaffLookUp}" lookupGroup="purchaserStaff">查找带回</a>	
			</fieldset>
			<fieldset>
				<label>操作类型：</label>
				<select class="combox" name="operationStatus">
					<c:if test="${operationStatusList!=null}">
						<c:forEach items="${operationStatusList}" var="loop">
							<option value=${loop.id}>${loop.statusName}</option>
						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
				<label>操作理由：</label>
				<select class="combox" name="reasonStatus">
					<c:if test="${reasonStatusList!=null}">
						<c:forEach items="${reasonStatusList}" var="loop">
							<option value=${loop.id}>${loop.statusName}</option>
						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
				<legend>备注：</legend>
				<dl class="nowrap">
					<dd><textarea name="remark" cols="60" rows="5"></textarea></dd>
				</dl>
			</fieldset>
		</div>
		<div class="formBar">
			<ul>				
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>