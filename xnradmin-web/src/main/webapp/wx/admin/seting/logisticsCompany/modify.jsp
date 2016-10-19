<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String action = basePath + "page/wx/admin/seting/logisticsCompany/modify.action";
	request.setAttribute("action", action);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		<input name="logisticsCompanyId" value="${logisticsCompanyId}" type="hidden"/>
			<p>
				<label>物流公司名称：</label>
				<input name="companyName" type="text" size="25" value="${companyName}" class="required"/>
			</p>
			<p>
				<label>物流公司名称：</label>
				<input name="companyUrl" type="text" size="25" value="${companyUrl}" class="required"/>
			</p>
			<p>
				<label>排序：</label>
				<input name="sortId" type="text" size="25" value="${sortId}" class="required number"/>
			</p>
			<p>
				<label>对应商城：</label>
				<input class="readonly" name="primaryConfiguration.id" value="${primaryConfigurationId}" readonly="readonly" type="hidden"/>
				<c:if test="${primaryConfigurationList!=null}">
					<c:forEach items="${primaryConfigurationList}" var="primaryConfiguration">
						<c:if test="${primaryConfiguration.id==primaryConfigurationId}">
							<input readonly="readonly" name="primaryConfiguration.mallName" value="${primaryConfiguration.mallName}"type="text"/>	
   						</c:if>
					</c:forEach>
				</c:if>
				<a class="btnLook" href="${lookup}" lookupGroup="primaryConfiguration">查找带回</a>	
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