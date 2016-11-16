<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String actionForm = basePath + "page/dishes/rawmaterial/add.action";
	String rwMaterialTypeLookup = basePath + "page/dishes/rawmaterial/rwMaterialTypeLookup.action";
	String select = basePath + "page/dishes/rawmaterial/select.action";
	
	request.setAttribute("actionForm", actionForm);
	request.setAttribute("rwMaterialTypeLookup", rwMaterialTypeLookup);
	request.setAttribute("select", select);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="post" action="${actionForm}" class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="97">
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li class="selected"><a href="javascript:void(0)"><span>添加原料</span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div>
						<table class="list nowrap itemDetail" addButton="添加原料" width="100%">
							<thead>
								<tr>
									<th type="input" name="rawMaterialList[#index#].materialName" size="50" defaultVal="" fieldClass="required">原料名称</th>
									<!-- 
									<th type="lookup" name="rawMaterialList[#index#].status.statusCode" lookupGroup="rawMaterialList[#index#].status" lookupUrl="${rwMaterialTypeLookup}" size="15" fieldClass="required">选择类型</th>
									 -->
									<th type="enum" name="rawMaterialList[#index#].status.id" enumUrl="${select}" size="12">选择类型</th>
									
									<th type="del">操作</th>
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
					<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
				</ul>
			</div>
		</div>
	</div>
</form>