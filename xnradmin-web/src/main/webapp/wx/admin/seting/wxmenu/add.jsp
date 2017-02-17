<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String actionForm = basePath+"page/wx/wxmenu/add.action";	
	String wxuserlookup = basePath + "page/wx/wxuser/lookup.action";
	String menuLookup = basePath + "page/wx/wxmenu/lookup.action";
	
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("wxuserlookup",wxuserlookup);
	request.setAttribute("menuLookup",menuLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>菜单名称：</label>
				<input name="query.menu.menuName" type="text" size="30" alt="请输入菜单名称" class="required"/>
			</p>	
			<p>
				<label>上级菜单：</label>					
				<input name="query.parentMenu.id" type="hidden" readonly="readonly" type="text"/>
				<input name="query.parentMenu.menuName" readonly="readonly" type="text"/>
				<a class="btnLook" href="${menuLookup}" lookupGroup="query.parentMenu">查找带回</a>											
			</p>
			<p>
				<label>所属微信用户：</label>								
				<input name="wxuservo.wxuserid" type="hidden" readonly="readonly" type="text"/>
				<input name="wxuservo.staffName" class="required" readonly="readonly" type="text"/>
				<a class="btnLook" href="${wxuserlookup}" lookupGroup="wxuservo">查找带回</a>											
			</p>			
			<p>
				<label>微信菜单类型</label>
				<select class="combox" name="query.menu.typeid">
					<c:if test="${statusList!=null}">
						<c:forEach items="${statusList}" var="loop">	
							<option value=${loop.id}>${loop.statusName}</option>
						</c:forEach>
					</c:if>						
				</select>
			</p>
			<p>
				<label>菜单链接：</label>
				<input name="query.menu.url" type="text" size="30" alt="VIEW类型用"/>
			</p>
			<p>
				<label>微信KEY：</label>
				<input name="query.menu.wxkey" type="text" size="30" alt="请输入链接,CLICK类型用"/>
			</p>
			<p>
				<label>菜单级别：</label>
				<select class="combox" name="query.menu.menuLevel">
					<option value="1">1</option>
					<option value="2">2</option>
				</select>
			</p>
			<p>
				<label>菜单排序(由大到小)：</label>
				<input name="query.menu.sortOrder" value="0" type="text" size="25" class="digits required" alt="请输入数字,排序由大到小"/>
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