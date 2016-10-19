<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String actionForm = basePath + "page/business/admin/warehouse/wareHouse/add.action";
	request.setAttribute("actionForm", actionForm);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>仓库名称：</label>
				<input name="wareHouseName" type="text" size="25" class="required"/>
			</p>
			<p>
				<label>仓库地址：</label>
				<input name="wareHouseAddress" type="text" size="25" />
			</p>
			<p>
				<label>仓库状态：</label>
				<select class="combox" name="wareHouseStatus">
					<c:if test="${wareHouseStatusList!=null}">
						<c:forEach items="${wareHouseStatusList}" var="loop">
							<option value=${loop.id}>${loop.statusName}</option>
						</c:forEach>
					</c:if>
				</select>
			</p>
			<p>
				<label>仓库序号：</label>
				<input name="wareHouseSerno" type="text" size="25" value="0" class="required number"/>
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