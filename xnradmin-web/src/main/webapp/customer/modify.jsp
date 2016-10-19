<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String action = basePath + "page/cus/modify.action";

request.setAttribute("action",action);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input name="customer.createStaffId" value="${customer.createStaffId}" type="hidden"/>
		<input name="customer.createStaffName" value="${customer.createStaffName}" type="hidden"/>
		<input name="customer.createStaffOrgId" value="${customer.createStaffOrgId}" type="hidden"/>
		<input name="customer.createStaffOrgName" value="${customer.createStaffOrgName}" type="hidden"/>
		<input name="customer.createTime" value="${customer.createTime}" type="hidden"/>
		
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>客户ID：</label>
				<input name="queryid" value="${customer.id}" readonly="readonly"  type="text" size="30" class="required"/>
			</p>	
			<p>
				<label>客户名称：</label>
				<input name="customerName" value="${customer.customerName}" type="text" size="30" alt="请输入客户名称" class="required"/>
			</p>	
			
			<p>
				<label>国家：</label>
				<input name="customerCountry" value="${customer.country}" type="text" class="required" size="30" alt="请输入国家"/>
			</p>
			<p>
				<label>省份：</label>
				<input name="customerProvince" value="${customer.province}" type="text" size="30" alt="请输入省份"/>
			</p>
			<p>
				<label>城市：</label>
				<input name="customerCity" value="${customer.city}" type="text" size="30" alt="请输入城市"/>
			</p>
			<p>
				<label>规模：</label>
				<input name="customerExtent" value="${customer.extent}" type="text" size="30" alt="请输入企业规模"/>
			</p>
			<p>
				<c:set var="levelList" value="A,B,C" />
				
				<label>级别：</label>				
				<select class="combox" name="customerLevel">					
					<c:forEach items="${levelList}" var="loop">
						<c:choose>
							<c:when test="${customer.level==loop}">
								<option value=${loop} selected="selected">${loop}</option>
							</c:when>
							<c:otherwise>
								<option value=${loop}>${loop}</option>	
							</c:otherwise>
						</c:choose>					
					</c:forEach>		
				</select>						
			</p>
			<p>
				<label>邮编：</label>
				<input name="postcode" value="${customer.postCode}" type="text" size="30"/>
			</p>
			<p>
				<label>电话：</label>
				<input name="telephone" value="${customer.telephone}" type="text" size="30"/>
			</p>
			<p>
				<label>传真：</label>
				<input name="fax" value="${customer.fax}" type="text" size="30"/>
			</p>
			<div class="divider"></div>			
			<fieldset>
				<legend>客户地址</legend>
				<dl class="nowrap">
					<dt>请输入详细地址：</dt>
					<dd><textarea class="required" name="customerAddr" cols="60" rows="2">${customer.customerAddress}</textarea></dd>
				</dl>
			</fieldset>		
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

