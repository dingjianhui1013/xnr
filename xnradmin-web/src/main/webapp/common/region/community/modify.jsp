<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String getCbm = basePath+"page/Common/Region/Community/all.action";
	String actionForm = basePath+"page/Common/Region/Community/modify.action";
	String provinceLookup = basePath + "page/Common/Region/Province/lookup.action";
	String cityLookup = basePath + "page/Common/Region/City/lookup.action";
	String areaLookup = basePath + "page/Common/Region/Area/lookup.action";
	String roadLookup = basePath + "page/Common/Region/Road/lookup.action";
	
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("getCbm",getCbm);
	request.setAttribute("provinceLookup",provinceLookup);
	request.setAttribute("cityLookup",cityLookup);
	request.setAttribute("areaLookup",areaLookup);
	request.setAttribute("roadLookup",roadLookup);
	
	
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
				<input id="city_id" class="readonly" name="citytype.id" readonly="readonly" value="${cityid}" type="hidden"/>
				<input readonly="readonly" name="citytype.city" value="${cityname}" type="text"/>		
				<a class="btnLook" href="${cityLookup}?provinceid={province_id}" lookupGroup="citytype">查找带回</a>	
			</p>	
			<p>
				<label>所属区/县：</label>
				<input id="area_id" class="readonly" name="areatype.id" readonly="readonly" value="${areaid}" type="hidden"/>
				<input readonly="readonly" name="areatype.area" value="${areaname}" type="text"/>		
				<a class="btnLook" href="${areaLookup}?provinceid={province_id}&cityid={city_id}" lookupGroup="areatype">查找带回</a>	
			</p>
			<p>
				<label>所属街/路：</label>
				<input id="road_id" class="readonly" name="roadtype.id" readonly="readonly" value="${roadid}" type="hidden"/>
				<input readonly="readonly" name="roadtype.road" value="${roadname}" type="text"/>		
				<a class="btnLook" href="${roadLookup}?provinceid={province_id}&cityid={city_id}&areaid={area_id}" lookupGroup="roadtype">查找带回</a>	
			</p>
			<p>
				<label>社区名称：</label>
				<input id="community_id" name="communityid" readonly="readonly" value="${communityid}" type="hidden"/>
				<input name="community" type="text" size="30" value="${community}" class="required"/>
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