<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String actionForm = basePath + "page/business/admin/commodity/weight/modify.action";
	String primaryConfigurationlookup = basePath + "page/wx/admin/seting/primaryConfiguration/lookup.action";
	request.setAttribute("actionForm", actionForm);
	request.setAttribute("primaryConfigurationlookup", primaryConfigurationlookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		<input name="weightId" value="${weightId}" type="hidden"/>
			<p>
				<label>数量单位名称：</label>
				<input name="weightName" type="text" size="25" value="${businessGoodsVO.businessWeight.weightName}" class="required"/>
			</p>
			<p>
				<label>对应商城：</label>
				<input class="readonly" name="primaryConfiguration.id" value="${businessGoodsVO.businessWeight.primaryConfigurationId}" readonly="readonly" type="hidden"/>
				<c:if test="${primaryConfigurationList!=null}">
					<c:forEach items="${primaryConfigurationList}" var="loop">
						<c:choose>
							<c:when test="${loop.id==businessGoodsVO.businessWeight.primaryConfigurationId}">
								<input readonly="readonly" name="primaryConfiguration.mallName" value="${loop.mallName}" type="text"/>
   							</c:when>
						</c:choose>
					</c:forEach>
				</c:if>
				<a class="btnLook" href="${primaryConfigurationlookup}" lookupGroup="primaryConfiguration">查找带回</a>	
			</p>
			<p>
				<label>数量单位描述：</label>
				<input name="weightDescription" value="${businessGoodsVO.businessWeight.weightDescription}" type="text" size="25" />
			</p>
			<p>
				<label>数量单位状态：</label>
					<select class="combox" name="weightStatus">
					<c:if test="${weightStatusList!=null}">
						<c:forEach items="${weightStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==businessGoodsVO.businessWeight.weightStatus}">
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
				<input name="sortId" type="text" size="25" value="${businessGoodsVO.businessWeight.sortId}" class="required number"/>
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