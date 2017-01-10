<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="contentTitle">商品签约</h2>
<form method="post" action="page/wx/farmer/saveAnthinfo.action"
	class="pageForm required-validate"
	onsubmit="return validateCallback(this, dialogAjaxDone)">
<div class="pageContent">
	<div class="pageFormContent" layoutH="98">
	<input type="hidden" name="farmerId" value="${farmerId}">
		<div class="divider"></div>
		<c:if test="${allBusinessGoods!=null}">
			<c:forEach items="${allBusinessGoods}" var="loop">
				
					<label><input type="checkbox" name="types"
						value="${loop.id}"
						<c:forEach items="${fenleiById}" var="fenlei">
						<c:if test="${fenlei==loop.id}">
						checked="checked"
						</c:if>
						</c:forEach> />${loop.goodsName}</label>
				

			</c:forEach>
		</c:if>
	</div>
	<div class="formBar">
		<label style="float: left"><input type="checkbox"
			class="checkboxCtrl" group="types" />全选</label>
		<ul>
			<li><div class="button">
					<div class="buttonContent">
						<button type="button" class="checkboxCtrl"
							group="types" selectType="invert">反选</button>
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
