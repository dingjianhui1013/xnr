<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/wx/admin/commodity/goods/add.action";
	String categoryLookup = basePath + "page/wx/admin/commodity/category/lookup.action";
	String goodsLookup = basePath+"page/wx/admin/commodity/goods/lookup.action";
	String dishLookup = basePath + "page/dishes/dish/lookup.action";
	request.setAttribute("action",action);
	request.setAttribute("categoryLookup",categoryLookup);
	request.setAttribute("goodsLookup",goodsLookup);
	request.setAttribute("dishLookup",dishLookup);
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
				<input readonly="readonly" name="category.categoryName" type="text"/>		
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
				<label>商品原价：</label>
				<input name="goodsOriginalPrice" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品优惠价：</label>
				<input name="goodsPreferentialPrice" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品重量：</label>
				<input name="goodsWeight" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品状态：</label>
				<select class="combox" name="goodsStatus">
					<c:if test="${goodsStatusList!=null}">
						<c:forEach items="${goodsStatusList}" var="loop">
							<option value=${loop.id} select>${loop.statusCode}</option>
   						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
				<label>是否优惠：</label>
				<select class="combox" name="isDiscountGoods">
					<c:if test="${isDiscountList!=null}">
						<c:forEach items="${isDiscountList}" var="loop">
							<option value=${loop.id} select>${loop.statusCode}</option>
   						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
				<label>商品排序：</label>
				<input name="goodsSortId" type="text" size="25" value="0" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品小图片：</label>
				<input name="goodsLogoFile" type="file" />
			</fieldset>
			<fieldset>
				<label>商品大图片：</label>
				<input name="goodsBigLogoFile" type="file" />
			</fieldset>
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li class="selected"><a href="javascript:void(0)"><span>菜品表管理</span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div>
						<table class="list nowrap itemDetail" addButton="新建菜品表" width="100%">
							<thead>
								<tr>
									<th type="lookup" name="dishList[#index#].dish.dishName"  lookupGroup="dishList[#index#].dish" lookupUrl="${dishLookup}" size="20"  fieldClass="required">选择菜品</th>
									<th type="input" name="dishList[#index#].goodsDishRelation.sortId" size="20" fieldClass="required number" >排序</th>								 
									<th type="input" name="dishList[#index#].goodsDishRelation.dishCount" size="20" fieldClass="required number" >数量</th>
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