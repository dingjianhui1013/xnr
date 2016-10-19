<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/business/admin/commodity/goods/add.action";
	String categoryLookup = basePath + "page/business/admin/commodity/category/lookup.action";
	String goodsLookup = basePath+"page/business/admin/commodity/goods/lookup.action";
	request.setAttribute("action",action);
	request.setAttribute("categoryLookup",categoryLookup);
	request.setAttribute("goodsLookup",goodsLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	
</script>

<div class="pageContent">
	<form method="post" action="${action}" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this)">
		<div class="pageFormContent" layoutH="56">
			<fieldset>
				<label>商品名称：</label>
				<input name="goodsName" type="text" size="25" class="required"/>			
			</fieldset>
			<fieldset>
				<label>商品类型：</label>
				<input class="readonly" name="category.id" readonly="readonly" type="hidden"/>
				<input class="readonly" name="category.primaryConfigurationId" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="category.categoryName" type="text" class="required"/>		
				<a class="btnLook" href="${categoryLookup}" lookupGroup="category">查找带回</a>
			</fieldset>
			<fieldset>
				<label>上级商品：</label>
				<input class="readonly" name="goodsParent.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="goodsParent.goodsName" type="text"/>		
				<a class="btnLook" href="${goodsLookup}" lookupGroup="goodsParent">查找带回</a>	
			</fieldset>
			<fieldset>
				<label>商品描述：</label>
				<textarea name="goodsDescription" rows="8" cols="100" 
					upLinkUrl="${upload}" upLinkExt="zip,rar,txt" 
					upImgUrl="${upload}" upImgExt="jpg,jpeg,gif,png" 
					upFlashUrl="upload.php" upFlashExt="swf"
					upMediaUrl="upload.php" upMediaExt:"avi"
					class="editor" type="text"/>
			</fieldset>
			<fieldset>
				<label>商品售价：</label>
				<input name="goodsOriginalPrice" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品进货价：</label>
				<input name="goodsPurchasePrice" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品重量：</label>
				<input name="goodsWeight" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>重量单位：</label>
				<select class="combox" name="goodsWeightId">
					<c:if test="${weightList!=null}">
						<c:forEach items="${weightList}" var="loop">
							<option value=${loop.id} select>${loop.weightName}</option>
   						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
				<label>商品状态：</label>
				<select class="combox" name="goodsStatus">
					<c:if test="${goodsStatusList!=null}">
						<c:forEach items="${goodsStatusList}" var="loop">
							<option value=${loop.id} select>${loop.statusName}</option>
   						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
				<label>采购分组：</label>
				<select class="combox" name="goods.buyTeamId">
					<c:if test="${buyTeamList!=null}">
						<c:forEach items="${buyTeamList}" var="loop">
							<option value=${loop.id} select>${loop.statusName}</option>
   						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
				<label>数据来源：</label>
				<select class="combox" name="goods.sourceId">
					<c:if test="${goodsSourceList!=null}">
						<c:forEach items="${goodsSourceList}" var="loop">
							<option value=${loop.id} select>${loop.statusName}</option>
   						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<!-- <fieldset>
				<label>是否优惠：</label>
				<select class="combox" name="isDiscountGoods">
					<c:if test="${isDiscountList!=null}">
						<c:forEach items="${isDiscountList}" var="loop">
							<option value=${loop.id} select>${loop.statusName}</option>
   						</c:forEach>
					</c:if>
				</select>
			</fieldset> -->
			<fieldset>
				<label>商品排序(小->大)：</label>
				<input name="goodsSortId" type="text" size="25" value="255" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品小图片：</label>
				<input name="goodsLogoFile" type="file" />
			</fieldset>
			<fieldset>
				<label>商品大图片：</label>
				<input name="goodsBigLogoFile" type="file" />
			</fieldset>
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