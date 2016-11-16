<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/business/admin/commodity/goods/modify.action";
	String categoryLookup = basePath + "page/business/admin/commodity/category/lookup.action";
	String goodsLookup = basePath+"page/business/admin/commodity/goods/lookup.action";
	String upload = basePath + "page/attach/uploadAttachInfo.action";
	
	request.setAttribute("action",action);
	request.setAttribute("categoryLookup",categoryLookup);
	request.setAttribute("goodsLookup",goodsLookup);
	request.setAttribute("upload",upload);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="pageContent">
	<form method="post" action="${action}" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this)">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="goodsId" value="${businessGoodsVO.businessGoods.id}" />	
			<fieldset>
				<label>商品名称：</label>
				<input name="goodsName" value="${businessGoodsVO.businessGoods.goodsName}" type="text" size="25"  class="required"/>			
			</fieldset>
			<fieldset>
				<label>商品类型：</label>
				<input class="readonly" name="category.id" readonly="readonly" value="${businessGoodsVO.businessGoods.goodsCategoryId}" type="hidden"/>
				<input class="readonly" name="category.primaryConfigurationId" value="${businessGoodsVO.businessGoods.primaryConfigurationId}" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="category.categoryName" value="${category.categoryName}" type="text" class="required"/>		
				<a class="btnLook" href="${categoryLookup}" lookupGroup="category">查找带回</a>	
			</fieldset>
			<fieldset>
				<label>上级商品：</label>
				<input class="readonly" name="goodsParent.id" readonly="readonly" value="${businessGoodsVO.businessGoods.goodsParentId}" type="hidden"/>
				<c:if test="${goodsList!=null}">
					<c:forEach items="${goodsList}" var="goodsTemp">
						<c:if test="${goodsTemp.id==businessGoodsVO.businessGoods.goodsParentId}">
							<input readonly="readonly" name="goodsParent.goodsName" value="${goodsTemp.goodsName}" type="text"/>
   						</c:if>
					</c:forEach>
				</c:if>
				<a class="btnLook" href="${goodsLookup}" lookupGroup="goodsParent">查找带回</a>	
			</fieldset>
			<fieldset>
				<label>商品描述：</label>
				<textarea name="goodsDescription" rows="8" cols="100" 
					upLinkUrl="${upload}" upLinkExt="zip,rar,txt" 
					upImgUrl="${upload}" upImgExt="jpg,jpeg,gif,png" 
					upFlashUrl="upload.php" upFlashExt="swf"
					upMediaUrl="upload.php" upMediaExt:"avi"
					class="editor" type="text">${businessGoodsVO.businessGoods.goodsDescription}</textarea>
			</fieldset>
			<fieldset>
				<label>商品售价：</label>
				<input name="goodsOriginalPrice" value="${businessGoodsVO.businessGoods.goodsOriginalPrice}" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品进货价：</label>
				<input name="goodsPurchasePrice" value="${businessGoodsVO.businessGoods.goodsPurchasePrice}" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品重量：</label>
				<input name="goodsWeight" value="${businessGoodsVO.businessGoods.goodsWeight}" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>重量单位：</label>
				<select class="combox" name="goodsWeightId">
					<c:if test="${weightList!=null}">
						<c:forEach items="${weightList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==businessGoodsVO.businessGoods.goodsWeightId}">
									<option value=${loop.id} selected>${loop.weightName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.weightName}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
				<label>商品状态：</label>
				<select class="combox" name="goodsStatus">
					<c:if test="${goodsStatusList!=null}">
						<c:forEach items="${goodsStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==businessGoodsVO.businessGoods.goodsStatus}">
									<option value=${loop.id} selected>${loop.statusName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusName}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
				<label>采购分组：</label>
				<select class="combox" name="goods.buyTeamId">
					<c:if test="${buyTeamList!=null}">
						<c:forEach items="${buyTeamList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==businessGoodsVO.businessGoods.buyTeamId}">
									<option value=${loop.id} selected>${loop.statusName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusName}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
				<label>数据来源：</label>
				<select class="combox" name="goods.sourceId">
					<c:if test="${goodsSourceList!=null}">
						<c:forEach items="${goodsSourceList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==businessGoodsVO.businessGoods.sourceId}">
									<option value=${loop.id} selected>${loop.statusName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusName}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<!--  <fieldset>
				<label>是否优惠：</label>
				<select class="combox" name="isDiscountGoods">
					<c:if test="${isDiscountList!=null}">
						<c:forEach items="${isDiscountList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==isDiscountGoods}">
									<option value=${loop.id} selected>${loop.statusCode}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusCode}</option>
   								</c:otherwise>
							</c:choose>
   						</c:forEach>
					</c:if>
				</select>
			</fieldset>-->
			<fieldset>
				<label>商品排序(小->大)：</label>
				<input name="goodsSortId" value="${businessGoodsVO.businessGoods.sortId}" type="text" size="25" value="0" class="required number"/>
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