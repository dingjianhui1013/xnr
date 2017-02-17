<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="contentTitle">菜单授权</h2>
<form method="post" action="page/menu/auth.action"
	class="pageForm required-validate"
	onsubmit="return validateCallback(this, dialogAjaxDone)">
<div class="pageContent">
	<div class="pageFormContent" layoutH="98">

		<div class="divider"></div>
		<c:if test="${permissions!=null}">
			<c:forEach items="${permissions}" var="loop">
				
					<label><input type="checkbox" name="myPermissionCodes"
						value="${loop.id}"
						<c:forEach items="${myPermissionCodes}" var="myid">
						<c:if test="${myid==loop.id}">
						checked="checked"
						</c:if>
						</c:forEach> />${loop.permissionName}</label>
				

			</c:forEach>
		</c:if>
		<input type="hidden" name="menuid" value="${menuid}"/>
	</div>
	<div class="formBar">
		<label style="float: left"><input type="checkbox"
			class="checkboxCtrl" group="myPermissionCodes" />全选</label>
		<ul>
			<li><div class="button">
					<div class="buttonContent">
						<button type="button" class="checkboxCtrl"
							group="myPermissionCodes" selectType="invert">反选</button>
					</div>
				</div></li>
			<li><div class="buttonActive">
					<div class="buttonContent">
						<button type="submit">提交</button>
					</div>
				</div></li>
		</ul>
	</div>
	</div>
</form>
