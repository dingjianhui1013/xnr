<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String modify = basePath + "page/per/modify.action";

	request.setAttribute("modify", modify);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${modify}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>id：</label> <input name="queryid" readonly="readonly"
					type="text" size="30" value="${queryid}" class="required" />
			</p>
			<p>
				<label>权限名称：</label> <input name="permissionName" type="text"
					size="30" value="${permissionName}" class="required" />
			</p>
			<p>
				<label>权限编码：</label> <input name="permissionCode" type="text"
					size="30" value="${permissionCode}" class="required" />
			</p>
			<p>
				<label>权限说明</label>
				<textarea name="permissionDesc" cols="50" rows="2">${permissionDesc}</textarea>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>

</div>