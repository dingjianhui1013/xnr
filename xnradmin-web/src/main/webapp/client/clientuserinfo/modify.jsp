<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String actionForm = basePath+"page/client/clientuserinfo/modify.action";
	String statusLookup = basePath + "page/client/clientuserinfo/statusLookup.action";
	String typeLookup = basePath + "page/client/clientuserinfo/typeLookup.action";
	String descriptionLookup = basePath + "page/Common/Region/Description/lookup.action";
	
	request.setAttribute("statusLookup",statusLookup);
	request.setAttribute("typeLookup",typeLookup);
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("descriptionLookup",descriptionLookup);
	
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
				<input name="clientuserinfoId" value="${clientuserinfoId}" type="hidden"/>
			<p>
				<label>用户名称：</label>
				<input name="nickName" value="${nickName}" type="text" size="30"/>
			</p>	
			<p>
				<label>用户邮箱：</label>
				<input name="email" value="${email}" type="text" size="30"/>
			</p>
			<p>
				<label>手机号码：</label>
				<input name="mobile" value="${mobile}" type="text" size="30" alt="请输入手机号码" class="required"/>
			</p>
			<p>
				<label>用户状态：</label>
				<select class="combox" name="status">
					<c:if test="${statusList!=null}">
						<c:forEach items="${statusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==status}">
									<option value=${loop.id} selected>${loop.statusName}</option>
								</c:when>
								<c:otherwise>
									<option value=${loop.id}>${loop.statusName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</p>
			<!-- <p>
				<label>用户类型：</label>
				<select class="combox" name="type">
					<c:if test="${typeList!=null}">
						<c:forEach items="${typeList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==type}">
									<option value=${loop.id} selected>${loop.statusName}</option>
								</c:when>
								<c:otherwise>
									<option value=${loop.id}>${loop.statusName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>
			</p> -->
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