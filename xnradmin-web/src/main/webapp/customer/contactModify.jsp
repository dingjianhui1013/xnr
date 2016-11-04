<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String action = basePath + "page/cus/conModify.action";

request.setAttribute("action",action);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>客户ID：</label>
				<input name="queryid" value="${contactVO.customerid}" readonly="readonly"  type="text" size="30" class="required"/>
			</p>	
			<p>
				<label>客户名称：</label>
				<input name="customerName" value="${contactVO.customerName}" readonly="readonly" type="text" size="30"/>
			</p>	
			
			<p>
				<label>国家：</label>
				<input name="customerCountry" value="${contactVO.country}" readonly="readonly" type="text" size="30"/>
			</p>
			<p>
				<label>省份：</label>
				<input name="customerProvince" value="${contactVO.province}" readonly="readonly" type="text" size="30"/>
			</p>
			<p>
				<label>城市：</label>
				<input name="customerCity" value="${contactVO.city}" readonly="readonly" type="text" size="30"/>
			</p>
			<p>
				<label>规模：</label>
				<input name="customerExtent" value="${contactVO.extent}" readonly="readonly" type="text" size="30"/>
			</p>
			<p>				
				<label>级别：</label>		
				<input name="customerLevel" value="${contactVO.level}" readonly="readonly" type="text" size="30"/>							
			</p>
			<div class="divider"></div>			
			<fieldset>
				<legend>客户地址</legend>
				<dl class="nowrap">					
					<dd><textarea class="required" name="customerAddr" readonly="readonly" cols="60" rows="2">${contactVO.customerAddress}</textarea></dd>
				</dl>
			</fieldset>		
			<div class="divider"></div>		
			<fieldset>
			<legend>请填写联系人信息</legend>
				<p>
					<label>联系人ID：</label>
					<input name="contid" readonly="readonly" type="text" value="${contactVO.contactid}" size="30" alt="请输入联系人名称" class="required"/>
				</p>
				<p>
					<label>联系人名称：</label>
					<input name="contactName" type="text" value="${contactVO.contactName}" size="30" alt="请输入联系人名称" class="required"/>
				</p>
				<p>
					<label>联系人电话：</label>
					<input name="contactMobile"  type="text" value="${contactVO.mobile}" size="30" alt="请输入联系人手机" class="required"/>
				</p>
				<p>
					<label>联系人邮箱：</label>
					<input name="contactEmail" type="text" value="${contactVO.email}" size="30" alt="请输入联系人邮箱" class="email"/>
				</p>
				<p>
					<label>联系人职位：</label>
					<input name="contactExtent"  type="text" value="${contactVO.position}" size="30" alt="请输入联系人职位"/>
				</p>
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

