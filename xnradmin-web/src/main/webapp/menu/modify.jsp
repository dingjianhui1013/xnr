<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String all = basePath+"page/menu/all.action";
	String actionForm = basePath+"page/menu/modify.action";	
	String lookup = basePath + "page/menu/lookup.action";
	
	request.setAttribute("lookup",lookup);
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("all",all);
	
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
				<input id="inputOrg1" name="menuid" value="${menuid}" type="hidden"/>
			<p>
				<label>菜单名称：</label>
				<input name="menuname" type="text" size="30" value="${menuname}" class="required"/>
			</p>	
			<p>
				<label>菜单英文名称：</label>
				<input name="enname" type="text" size="30" value="${enmenuname}"/>
			</p>
			<p>
				<label>菜单链接：</label>
				<input name="menulink" type="text" size="30" value="${menulink}"/>
			</p>
			<p>
				<label>上级菜单名称：</label>
				<input name="org1.orgName" value="${pmenuname}" readonly="readonly" type="text"/>
			</p>
			<p>
				<label>上级菜单：</label>								
				<input name="org1.id" value="${pmenuid}" readonly="readonly" type="text"/>
				<a class="btnLook" href="${lookup}" lookupGroup="org1">查找带回</a>											
			</p>
			<p>
				<label>菜单排序：</label>
				<input name="sortOrder" type="text" size="30" value="${sortOrder}" class="digits" alt="请输入数字"/>
			</p>
			<!-- 
			<p>
				<label>上级菜单：</label>
				<input id="inputOrg1" name="org1.id" value="${pmenuid}" type="hidden"/>
				<input name="org1.menuName" value="${pmenuname}" type="text" postField="keyword" suggestFields="menuName" 
					suggestUrl="${all}" lookupGroup="org1"/>
			</p>
			 -->	
			<p>
				<label>菜单级别：</label>
				<select class="combox" name="level">
					<c:forEach var="number" begin="1" end="3" step="1">
						<c:choose>
							<c:when test="${number==menulevel}">
								<option value=${number} selected="selected">${number}</option>
							</c:when>
							<c:otherwise>
								<option value=${number}>${number}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
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