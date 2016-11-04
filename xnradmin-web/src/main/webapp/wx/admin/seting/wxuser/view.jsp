<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" 
	import="java.util.*" 
	import="java.text.*"
	import="sun.misc.BASE64Encoder" 
	import="java.net.*"
	import="com.cntinker.util.*"
%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";

	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	
		<div class="pageFormContent" layoutH="56">
				
			<fieldset>
				<legend>基础信息</legend>
				<p>
					公众号名称：${query.staff.staffName}			
				</p>
				<p>
					token：${query.wxuser.token}
				</p>
				<p>
					appid：${query.wxuser.appid}
				</p>									
			</fieldset>	
			<fieldset>
				<legend>关注返回信息:</legend>
				${query.wxuser.welcome}
			</fieldset>	
			<fieldset>
				<legend>access_token:</legend>
				${query.wxaccessToken.accessToken}
			</fieldset>		
			<fieldset>
				<legend>通信返回信息:</legend>
				${query.wxaccessToken.res}
			</fieldset>		
			<fieldset>
				<legend>access_token信息:</legend>
				<p>
					最后生成时间：
					<fmt:formatDate value="${query.wxaccessToken.lastGenTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</p>
				<p>
					超时时间（单位秒）：
					${query.wxaccessToken.expiresin}
				</p>
				<p>
					errcode：
					${query.wxaccessToken.errcode}
				</p>
				<p>
					errmsg：
					${query.wxaccessToken.errmsg}
				</p>
			</fieldset>					
			
		</div>
		
		
			
	</form>

</div>