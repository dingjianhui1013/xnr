<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String actionForm = basePath+"page/client/clientuserinfo/add.action";
	String statusLookup = basePath + "page/client/clientuserinfo/statusLookup.action";
	String typeLookup = basePath + "page/client/clientuserinfo/typeLookup.action";
	String descriptionLookup = basePath + "page/Common/Region/Description/lookup.action";
	
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("statusLookup",statusLookup);
	request.setAttribute("typeLookup",typeLookup);
	request.setAttribute("descriptionLookup",descriptionLookup);
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		<input name="ismobileuser" value="1" type="hidden"/>
			<p>
				<label>用户名称：</label>
				<input name="nickName" type="text" size="30"/>
			</p>	
			<p>
				<label>用户邮箱：</label>
				<input name="email" type="text" size="30"/>
			</p>
			<p>
				<label>手机号码：</label>
				<input name="mobile" type="text" size="30" alt="请输入手机号码" class="required"/>
			</p>
			<p>
				<label>用户状态：</label>
				<select class="combox" name="status">
					<c:if test="${statusList!=null}">
						<c:forEach items="${statusList}" var="loop">
							<option value=${loop.id}>${loop.statusName}</option>
						</c:forEach>
					</c:if>
				</select>
			</p>
			<p>
				<label>用户类型：</label>
				<select class="combox" name="type">
					<c:if test="${typeList!=null}">
						<c:forEach items="${typeList}" var="loop">
							<option value=${loop.id}>${loop.statusName}</option>
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