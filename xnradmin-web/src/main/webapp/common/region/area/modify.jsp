<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String getCbm = basePath+"page/Common/Region/Area/all.action";
	String actionForm = basePath+"page/Common/Region/Area/modify.action";
	String provinceLookup = basePath + "page/Common/Region/Province/lookup.action";
	String cityLookup = basePath + "page/Common/Region/City/lookup.action";
	
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("getCbm",getCbm);
	request.setAttribute("provinceLookup",provinceLookup);
	request.setAttribute("cityLookup",cityLookup);
	
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>所属省份：</label>
				<input id="province_id" class="readonly" name="provincetype.id" readonly="readonly" value="${provinceid}" type="hidden"/>
				<input readonly="readonly" name="provincetype.province" value="${provincename}" type="text"/>		
				<a class="btnLook" href="${provinceLookup}" lookupGroup="provincetype">查找带回</a>	
			</p>	
			<p>
				<label>所属城市：</label>
				<input class="readonly" name="citytype.id" readonly="readonly" value="${cityid}" type="hidden"/>
				<input readonly="readonly" name="citytype.city" value="${cityname}" type="text"/>		
				<a class="btnLook" href="${cityLookup}?provinceid={province_id}" lookupGroup="citytype">查找带回</a>	
			</p>	
			<p>
				<label>区/县名称：</label>
				<input name="areaid" readonly="readonly" value="${areaid}" type="hidden"/>
				<input name="area" type="text" size="30" value="${area}" class="required"/>
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