<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="contentTitle">菜单授权</h2>
<form method="post" action="page/staff/auth.action"
	class="pageForm required-validate"
	onsubmit="return validateCallback(this, navTabAjaxDone)">
<div class="pageContent">
	<div class="pageFormContent" layoutH="98">
		
		<input type="hidden" name="queryStime" value="${queryStime}" />
		<input type="hidden" name="queryEtime" value="${queryEtime}" />
		<input type="hidden" name="queryStaffname" value="${queryStaffname}" />
		<input type="hidden" name="queryStaffStatus" value="${queryStaffStatus}" />			
		<input type="hidden" name="queryOrg1.id" value="${queryOrg1.id}" />		
		<input type="hidden" name="queryOrg1.organizationName" value="${queryOrg1.organizationName}" />
		
		<input type="hidden" name="totalCount" value="${totalCount}" />		
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${param.orderField}" />
		<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
		
		<div class="divider"></div>
		<c:if test="${roles!=null}">
			<c:forEach items="${roles}" var="loop">
				
					<label><input type="checkbox" name="myRoleCodes"
						value="${loop.id}"
						<c:forEach items="${myRoleCodes}" var="myid">
						<c:if test="${myid==loop.id}">
						checked="checked"
						</c:if>
						</c:forEach> />${loop.roleName}</label>
				

			</c:forEach>
		</c:if>
		<input type="hidden" name="queryid" value="${queryid}"/>
	</div>
	<div class="formBar">
		<label style="float: left"><input type="checkbox"
			class="checkboxCtrl" group="myRoleCodes" />全选</label>
		<ul>
			<li><div class="button">
					<div class="buttonContent">
						<button type="button" class="checkboxCtrl"
							group="myRoleCodes" selectType="invert">反选</button>
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
