<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/dishes/dish/modify.action";
	String dishTypeLookup = basePath+"page/dishes/dish/dishTypeLookup.action";
	String rawMaterialLookup = basePath+"page/dishes/rawmaterial/lookup.action";
	String weightLookup = basePath+"page/dishes/dish/weightLookup.action";
	String select = basePath+"page/dishes/dish/weightSelect.action";
	
	request.setAttribute("action",action);
	request.setAttribute("dishTypeLookup",dishTypeLookup);
	request.setAttribute("rawMaterialLookup",rawMaterialLookup);
	request.setAttribute("weightLookup",weightLookup);
	request.setAttribute("select",select);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="dishId" value="${dishId}" />	
			<input type="hidden" name="dishName" value="${dishName}" />	
			<input type="hidden" name="introduction" value="${introduction}" />	
			<input type="hidden" name="cookingMethod" value="${cookingMethod}" />	
			<input type="hidden" name="picUrl" value="${picUrl}" />
			<input type="hidden" name="dishTypeId" value="${dishTypeId}" />	
			<p>
				<label>菜品名称：</label>
				<input name="dish.dishName" value="${dishName}" type="text" size="25" class="required" />			
			</p>	
			<p>
				<label>菜品类型：</label>
				<input name="dishType.statusCode" readonly="readonly" value="${dishTypeName}" type="text" class="required"/>
				<input name="dishType.id" readonly="readonly"  value="${dishTypeId}" type="hidden"/>
				<a class="btnLook" href="${dishTypeLookup}" lookupGroup="dishType">选择菜品类型</a>	
			</p>
			<fieldset>
				<legend>菜品介绍：</legend>
				<dl class="nowrap">
					<dt>请输入详细信息：</dt>
					<dd><textarea class="required" name="dish.introduction" cols="60" rows="5" >${introduction}</textarea></dd>
				</dl>
			</fieldset>
			<fieldset>
				<legend>烹饪方法：</legend>
				<dl class="nowrap">
					<dt>请输入详细信息：</dt>
					<dd><textarea class="required" name="dish.cookingMethod" cols="60" rows="5" >${cookingMethod}</textarea></dd>
				</dl>
			</fieldset>
			<fieldset>
				<legend>图片链接：</legend>
				<dl class="nowrap">
					<dt>请输入详细信息：</dt>
					<dd><textarea class="required" name="dish.picUrl" cols="60" rows="5" >${picUrl}</textarea></dd>
				</dl>
			</fieldset>
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li class="selected"><a href="javascript:void(0)"><span>配料表管理</span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div>
						<table class="list nowrap itemDetail" addButton="新建配料表" width="100%">
							<thead>
								<tr>
									<th type="lookup" name="rawMaterialList[#index#].rawMaterial.materialName"  lookupGroup="rawMaterialList[#index#].rawMaterial" lookupUrl="${rawMaterialLookup}" size="20" fieldClass="required">选择原料</th>
									<!-- 
									<th type="lookup" name="rawMaterialList[#index#].status.statusCode" lookupGroup="rawMaterialList[#index#].status" lookupUrl="${weightLookup}" size="20" value="${dishTypeName}" fieldClass="required">选择单位</th>
									 -->
									<th type="enum" name="rawMaterialList[#index#].status.id" enumUrl="${select}" size="12">选择单位</th>								 
									<th type="input" name="rawMaterialList[#index#].number" size="20" fieldClass="required digits" >数量</th>
									<th type="del"  width="10%">操作</th>
								</tr>
							</thead>
							<tbody>
							<c:if test="${dvList!=null}">
									<c:forEach items="${dvList}" var="loop">					
										<tr class="unitBox">
											<td>
												<input type="hidden" value="${loop.rawMaterial.id}" name="rawMaterialList[${loop.rawMaterial.id}].rawMaterial.id">
												<input type="text" value="${loop.rawMaterial.materialName}" name="rawMaterialList[${loop.rawMaterial.id}].rawMaterial.materialName">
												<a class="btnLook" title="查找带回" lookupgroup="rawMaterialList[${loop.rawMaterial.id}].rawMaterial" href="${rawMaterialLookup}">查找带回</a>
											</td>
											<td>
												<select class="combox" name="rawMaterialList[${loop.rawMaterial.id}].status.id">
													<c:if test="${weightList!=null}">
														<c:forEach items="${weightList}" var="loop2">
															<c:choose>
																<c:when test="${loop.status.id==loop2.id}">
																	<option value=${loop2.id} selected="selected">${loop2.statusCode}</option>
																</c:when>
																<c:otherwise>
																	<option value=${loop2.id}>${loop2.statusCode}</option>	
																</c:otherwise>
															</c:choose>					
														</c:forEach>
													</c:if>						
												</select>
											</td>
											<td>
												<input id="" class="required number" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown="" 
													onkeypress="" onchange="" size="20"
													class="required number"  
													value="${loop.number}" name="rawMaterialList[${loop.rawMaterial.id}].number">												
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