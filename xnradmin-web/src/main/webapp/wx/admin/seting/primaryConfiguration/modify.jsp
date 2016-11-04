<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String actionForm = basePath + "page/wx/admin/seting/primaryConfiguration/modify.action";
	request.setAttribute("actionForm", actionForm);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	
	<form method="post" action="${actionForm}" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this)">
		<div class="pageFormContent" layoutH="56">
		<input name="primaryConfigurationId" value="${primaryConfigurationId}" type="hidden"/>
			<p>
				<label>商城名称：</label>
				<input name="mallName" type="text" size="25" value="${mallName}" class="required"/>
			</p>
			<p>
				<label>商城LOGO：</label>
				<input name="mallLogoFile" type="file" />
			</p>
			<p>
				<label>商城背景：</label>
				<input name="mallBackgroundFile" type="file" />
			</p>
			<p>
				<label>背景开启：</label>
					<select class="combox" name="mallBackgroundStatus">
					<c:if test="${mallBackgroundStatusList!=null}">
						<c:forEach items="${mallBackgroundStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==mallBackgroundStatus}">
									<option value=${loop.id} selected>${loop.statusCode}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusCode}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</p>
			<p>
				<label>联系地址：</label>
				<input name="address" type="text" size="25" value="${address}" class="required"/>
			</p>
			<p>
				<label>联系电话：</label>
				<input name="mobile" type="text" size="25" value="${mobile}" class="required"/>
			</p>
			<p>
				<label>邮政编码：</label>
				<input name="code" type="text" size="25" value="${code}" class="required"/>
			</p>
			<p>
				<label>邮箱地址：</label>
				<input name="email" type="text" size="25" value="${email}" class="required"/>
			</p>
			<p>
				<label>商城介绍：</label>
				<input name="mallIntroduction" type="text" size="25" value="${mallIntroduction}"/>
			</p>
			<p>
			
				<label>商城状态：</label>
				<select class="combox" name="mallStatus">
					<c:if test="${mallStatusList!=null}">
						<c:forEach items="${mallStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==mallStatus}">
									<option value=${loop.id} selected>${loop.statusCode}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusCode}</option>
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