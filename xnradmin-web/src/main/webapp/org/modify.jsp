<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String getOrg = basePath+"page/org/all.action";
	String actionForm = basePath+"page/org/modify.action";
	String lookup = basePath + "page/org/lookup.action";
	
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("getOrg",getOrg);
	request.setAttribute("lookup",lookup);
	
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>组织id：</label>
				<input name="orgid" readonly="readonly" type="text" size="30" value="${orgid}" class="required"/>
			</p>	
			<p>
				<label>组织名称：</label>
				<input name="orgname" type="text" size="30" value="${orgname}" class="required"/>
			</p>	
			<p>
				<label>上级组织</label>
				<input id="inputOrg1" name="org1.id" value="${porgid}" type="hidden"/>
				<input name="org1.organizationName" value="${porgname}" readonly="readonly" type="text"/>
				<a class="btnLook" href="${lookup}" lookupGroup="org1">查找带回</a>
											
			</p>
			
			<p>
			<label>组织类型：</label>
			<select class="combox" name="orgType">
			
				<c:if test="${typeList!=null}">
					<c:forEach items="${typeList}" var="loop">
						<c:choose>
							<c:when test="${typeId==loop.id}">
								<option value=${loop.id} selected="selected">${loop.organizationTypeName}</option>
							</c:when>
							<c:otherwise>
								<option value=${loop.id}>${loop.organizationTypeName}</option>	
							</c:otherwise>
						</c:choose>					
					</c:forEach>
				</c:if>							
			</select>
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