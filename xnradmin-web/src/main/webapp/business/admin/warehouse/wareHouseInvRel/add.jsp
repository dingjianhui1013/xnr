<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String actionForm = basePath + "page/business/admin/warehouse/wareHouseInvRel/add.action";
	String goodsLookup = basePath+"page/business/admin/commodity/goods/lookup.action";
	request.setAttribute("actionForm", actionForm);
	request.setAttribute("goodsLookup", goodsLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<fieldset>
				<label>选择仓库：</label>
				<select class="combox" name="wareHouseInvRelWareHouseId">
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
				<input readonly="readonly" name="goods.goodsName" type="text"/>		
				<a class="btnLook" href="${goodsLookup}" lookupGroup="goods">查找带回</a>	
			</fieldset>
			<fieldset>
				<label>商品数量：</label>
				<input name="wareHouseInvRelCount" type="text" size="25" class="required number"/>
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