<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String getOrg = basePath+"page/org/all.action";
	String actionForm = basePath+"page/org/add.action";	
	String lookup = basePath + "page/org/lookup.action";
	
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("getOrg",getOrg);
	request.setAttribute("lookup",lookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>组织名称：</label>
				<input name="orgname" type="text" size="30" alt="请输入组织名称" class="required"/>
			</p>	
			<p>
				<label>上级组织名称：</label>
				<input name="org1.organizationName" readonly="readonly" type="text"/>
			</p>
			<p>
				<label>上级组织ID：</label>				
				<input name="org1.id" readonly="readonly" type="text"/>
				<a class="btnLook" href="${lookup}" lookupGroup="org1">查找带回</a>							
			</p>			
			<p>
				<label>组织类型：</label>
				<select class="combox" name="orgType">
					<c:if test="${typeList!=null}">
						<c:forEach items="${typeList}" var="loop">																													
								<option value=${loop.id}>${loop.organizationTypeName}</option>	
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