<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String actionForm = basePath + "page/business/admin/commodity/category/modify.action";
	String lookup = basePath + "page/business/admin/commodity/category/lookup.action";
	String primaryConfigurationlookup = basePath + "page/wx/admin/seting/primaryConfiguration/lookup.action";
	request.setAttribute("actionForm", actionForm);
	request.setAttribute("lookup", lookup);
	request.setAttribute("primaryConfigurationlookup", primaryConfigurationlookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	
	<form method="post" action="${actionForm}" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this)">
		<div class="pageFormContent" layoutH="56">
		<input name="categoryId" value="${categoryId}" type="hidden"/>
			<p>
				<label>分类名称：</label>
				<input name="categoryName" type="text" size="25" value="${businessGoodsVO.businessCategory.categoryName}" class="required"/>
			</p>
			<p>
				<label>分类图片：</label>
				<input name="categoryLogoFile" type="file" />
			</p>
			<p>
				<label>分类置顶图片：</label>
				<input name="categoryHeadLogoFile" type="file" />
			</p>
			<p>
				<label>选择父类：</label>
				<input class="readonly" name="categoryParent.id" value="${businessGoodsVO.businessCategory.categoryParentId}" readonly="readonly" type="hidden"/>
				<input class="readonly" name="categoryParent.categoryLevel" value="${businessGoodsVO.businessCategory.categoryLevel}" readonly="readonly" type="hidden"/>
				<input class="readonly" name="categoryParent.primaryConfigurationId" value="${businessGoodsVO.businessCategory.primaryConfigurationId}" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="categoryParent.categoryName" value="${categoryParent.categoryName}" type="text"/>
				<a class="btnLook" href="${lookup}" lookupGroup="categoryParent">查找带回</a>	
			</p>
			<p>
				<label>对应商城：</label>
				<input class="readonly" name="primaryConfiguration.id" value="${businessGoodsVO.businessCategory.primaryConfigurationId}" readonly="readonly" type="hidden"/>
				<c:if test="${primaryConfigurationList!=null}">
					<c:forEach items="${primaryConfigurationList}" var="loop">
						<c:choose>
							<c:when test="${loop.id==businessGoodsVO.businessCategory.primaryConfigurationId}">
								<input readonly="readonly" name="primaryConfiguration.mallName" value="${loop.mallName}" type="text"/>
   							</c:when>
						</c:choose>
					</c:forEach>
				</c:if>
				<a class="btnLook" href="${primaryConfigurationlookup}" lookupGroup="primaryConfiguration">查找带回</a>	
			</p>
			<p>
				<label>根分类：</label>
				<c:choose>
					<c:when test="${businessGoodsVO.businessCategory.categoryLevel==0}">
						<input type="radio" name="firstLevel" value="0" checked>是
						<input type="radio" name="firstLevel" value="1">否
   					</c:when>
   					<c:otherwise>
   						<input type="radio" name="firstLevel" value="0">是
   						<input type="radio" name="firstLevel" value="1" checked>否
   					</c:otherwise>
				</c:choose>	
			</p>
			<p>
				<label>描述：</label>
				<input name="categoryDescription" value="${businessGoodsVO.businessCategory.categoryDescription}" type="text" size="25" />
			</p>
			<p>
				<label>分类状态：</label>
					<select class="combox" name="categoryStatus">
					<c:if test="${categoryStatusList!=null}">
						<c:forEach items="${categoryStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==businessGoodsVO.businessCategory.categoryStatus}">
									<option value=${loop.id} selected>${loop.statusName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusName}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</p>
			<p>
				<label>排序：</label>
				<input name="categorySortId" type="text" size="25" value="${businessGoodsVO.businessCategory.sortId}" class="required number"/>
			</p>
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