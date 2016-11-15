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
	request.setAttribute("actionForm", actionForm);
	request.setAttribute("rwMaterialTypeLookup", rwMaterialTypeLookup);

%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<select class="combox" name="rawMaterialList[#index#].status.id">
	<c:if test="${materialTypeList!=null}">
		<c:forEach items="${materialTypeList}" var="loop">	
			<option value=${loop.id}>${loop.statusCode}</option>
		</c:forEach>
	</c:if>						
</select>		