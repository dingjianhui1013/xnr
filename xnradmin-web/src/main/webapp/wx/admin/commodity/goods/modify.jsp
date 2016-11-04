<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/wx/admin/commodity/goods/modify.action";
	String categoryLookup = basePath + "page/wx/admin/commodity/category/lookup.action";
	String goodsLookup = basePath+"page/wx/admin/commodity/goods/lookup.action";
	String dishLookup = basePath + "page/dishes/dish/lookup.action";
	String upload = basePath + "page/attach/uploadAttachInfo.action";
	
	request.setAttribute("action",action);
	request.setAttribute("categoryLookup",categoryLookup);
	request.setAttribute("goodsLookup",goodsLookup);
	request.setAttribute("dishLookup",dishLookup);
	request.setAttribute("upload",upload);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="pageContent">
	<form method="post" action="${action}" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this)">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="goodsId" value="${goodsId}" />	
			<fieldset>
				<label>商品名称：</label>
				<input name="goodsName" value="${goodsName}" type="text" size="25"  class="required"/>			
			</fieldset>
			<fieldset>
				<label>商品类型：</label>
				<input class="readonly" name="category.id" readonly="readonly" value="${goodsCategoryId}" type="hidden"/>
				<input class="readonly" name="category.primaryConfigurationId" value="${goodsPrimaryConfigurationId}" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="category.categoryName" value="${goodsCategoryName}" type="text"/>		
				<a class="btnLook" href="${categoryLookup}" lookupGroup="category">查找带回</a>	
			</fieldset>
			<fieldset>
				<label>上级商品：</label>
				<input class="readonly" name="goodsParent.id" readonly="readonly" value="${goodsParentId}" type="hidden"/>
				<c:if test="${goodsList!=null}">
					<c:forEach items="${goodsList}" var="goodsTemp">
						<c:if test="${goodsTemp.id==goodsParentId}">
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
					class="editor" type="text">${goodsDescription}</textarea>
			</fieldset>
			<fieldset>
				<label>商品原价：</label>
				<input name="goodsOriginalPrice" value="${goodsOriginalPrice}" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品优惠价：</label>
				<input name="goodsPreferentialPrice" value="${goodsPreferentialPrice}" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品重量：</label>
				<input name="goodsWeight" value="${goodsWeight}" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品状态：</label>
				<select class="combox" name="goodsStatus">
					<c:if test="${goodsStatusList!=null}">
						<c:forEach items="${goodsStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==goodsStatus}">
									<option value=${loop.id} selected>${loop.statusCode}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusCode}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
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
			</fieldset>
			<fieldset>
				<label>商品排序：</label>
				<input name="goodsSortId" value="${goodsSortId}" type="text" size="25" value="0" class="required number"/>
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
							<tbody>
							<c:if test="${dvList!=null}">
									<c:forEach items="${dvList}" var="loop">					
										<tr class="unitBox">
											<td>
												<input type="hidden" value="${loop.dish.id}" name="dishList[${loop.dish.id}].dish.id">
												<input type="text" value="${loop.dish.dishName}" name="dishList[${loop.dish.id}].dish.dishName">
												<a class="btnLook" title="查找带回" lookupgroup="dishList[${loop.dish.id}].dish" href="${dishLookup}">查找带回</a>
											</td>
											<td>
												<input id="" class="required number" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown="" 
													onkeypress="" onchange="" size="20"
													class="required number"  
													value="${loop.goodsDishrelationSortId}" name="dishList[${loop.dish.id}].goodsDishRelation.sortId">												
											</td>
											<td>
												<input id="" class="required number" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown="" 
													onkeypress="" onchange="" size="20"
													class="required number"  
													value="${loop.goodsDishrelationDishCount}" name="dishList[${loop.dish.id}].goodsDishRelation.dishCount">												
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