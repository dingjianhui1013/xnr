<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<select class="combox" name="rawMaterialList[#index#].status.id">
	<c:if test="${weightList!=null}">
		<c:forEach items="${weightList}" var="loop">	
			<option value=${loop.id}>${loop.statusCode}</option>
		</c:forEach>
	</c:if>						
</select>		