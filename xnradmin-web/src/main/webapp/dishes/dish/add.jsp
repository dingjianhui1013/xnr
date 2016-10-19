<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/dishes/dish/add.action";
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

<script type="text/javascript">
	
</script>

<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>菜品名称：</label>
				<input name="dishName" type="text" size="25" class="required"/>			
			</p>
			<p>
				<label>菜品类型：</label>
				<input name="status.statusCode" readonly="readonly" type="text" class="required"/>
				<input name="status.id" readonly="readonly" type="hidden"/>
				<a class="btnLook" href="${dishTypeLookup}" lookupGroup="status">选择菜品类型</a>	
			</p>
			<fieldset>
				<legend>菜品介绍：</legend>
				<dl class="nowrap">
					<dt>请输入详细信息：</dt>
					<dd><textarea class="required" name="introduction" cols="60" rows="5"></textarea></dd>
				</dl>
			</fieldset>
			<fieldset>
				<legend>烹饪方法：</legend>
				<dl class="nowrap">
					<dt>请输入详细信息：</dt>
					<dd><textarea class="required" name="cookingMethod" cols="60" rows="5"></textarea></dd>
				</dl>
			</fieldset>
			<fieldset>
				<legend>图片链接：</legend>
				<dl class="nowrap">
					<dt>请输入详细信息：</dt>
					<dd><textarea class="required" name="picUrl" cols="60" rows="5"></textarea></dd>
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
									<th type="lookup" name="rawMaterialList[#index#].rawMaterial.materialName"  lookupGroup="rawMaterialList[#index#].rawMaterial" lookupUrl="${rawMaterialLookup}" size="20"  fieldClass="required">选择原料</th>
									<!-- 
									<th type="lookup" name="rawMaterialList[#index#].status.statusCode" lookupGroup="rawMaterialList[#index#].status" lookupUrl="${weightLookup}" size="15" fieldClass="required">选择单位</th>
									 -->
									<th type="enum" name="rawMaterialList[#index#].status.id" enumUrl="${select}" size="12">选择单位</th>								 
									<th type="input" name="rawMaterialList[#index#].number" size="20" fieldClass="required number" >数量</th>
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