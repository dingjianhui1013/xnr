<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/port/modify.action";
	String scriptLookup = basePath+"page/script/lookup.action";
	
	request.setAttribute("action",action);
	request.setAttribute("scriptLookup", scriptLookup);
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<input name="query.port.id" value="${query.port.id}" type="hidden"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>接口名：</label>
				<input name="query.port.portName" value="${query.port.portName}" type="text" size="35" alt="输入接口名" class="alphanumeric required"/>
				
			</p>		
			<p>				
				<label>选定脚本：</label>
				<input id="script.id" name="script.id" value="${query.script.id}" type="hidden"/>
				<input name="script.className" value="${query.script.className}" readonly="true" type="text" postField="keyword"  lookupGroup="script"/>
				<a class="btnLook" href="${scriptLookup}" lookupGroup="script">查找带回</a>
			</p>				
			<p>
				<label>状态：</label>				
				<select class="combox" name="query.port.status">
					<c:if test="${statusList!=null}">
						<c:forEach items="${statusList}" var="loop">
							<c:choose>
								<c:when test="${query.port.status==loop.id}">
									<option value=${loop.id} selected="selected">${loop.statusName}</option>
								</c:when>
								<c:otherwise>
									<option value=${loop.id}>${loop.statusName}</option>	
								</c:otherwise>
							</c:choose>					
						</c:forEach>
					</c:if>						
				</select>
			</p>				
			<div class="divider"></div>
			<fieldset>
				<legend>接口描述</legend>
				<dl class="nowrap">
					<dt>请输入详细信息：</dt>
					<dd><textarea class="required" name="query.port.portDesc" cols="60" rows="5">${query.port.portDesc}</textarea></dd>
				</dl>
			</fieldset>					
			
			
			<div class="divider"></div>
			<fieldset>
				<legend>输入相关</legend>
				
				<p>
					<label>输入类型：</label>					
					<select class="combox" name="query.port.inputType">
					<c:if test="${typeList!=null}">
						<c:forEach items="${typeList}" var="loop">
							<c:choose>
								<c:when test="${query.port.inputType==loop.id}">
									<option value=${loop.id} selected="selected">${loop.statusName}</option>
								</c:when>
								<c:otherwise>
									<option value=${loop.id}>${loop.statusName}</option>	
								</c:otherwise>
							</c:choose>					
						</c:forEach>
						</c:if>						
					</select>
					<label>是否加密：</label>
					<input type="checkbox" name="query.port.inputEncrypt" 
					
					<c:choose>
						<c:when test="${query.port.inputEncrypt}">
							checked="checked" value="true"
						</c:when>
						<c:otherwise>
								
						</c:otherwise>
					</c:choose>
					/>		
				</p>
				<p>
					<fieldset>
					<legend>输入描述/示例</legend>
					<dl class="nowrap">
						<dt>请输入详细信息：</dt>
						<dd><textarea name="query.port.inputSource"  cols="60" rows="2">${query.port.inputSource}</textarea></dd>
					</dl>
					</fieldset>
				</p>
			</fieldset>
			<fieldset>
				<legend>输出相关</legend>
				<p>
					<label>输出类型：</label>					
					<select class="combox" name="query.port.outputType">
					<c:if test="${typeList!=null}">
						<c:forEach items="${typeList}" var="loop">
							<c:choose>
								<c:when test="${query.port.outputType==loop.id}">
									<option value=${loop.id} selected="selected">${loop.statusName}</option>
								</c:when>
								<c:otherwise>
									<option value=${loop.id}>${loop.statusName}</option>	
								</c:otherwise>
							</c:choose>					
						</c:forEach>
						</c:if>						
					</select>		
					<label>是否加密：</label>
					<input type="checkbox" name="query.port.outputEncrypt" 
					<c:choose>
						<c:when test="${query.port.outputEncrypt}">
							checked="checked" value="true"
						</c:when>
						<c:otherwise>
								
						</c:otherwise>
					</c:choose>
					value="true"/>		
				</p>	
				<p>
					<fieldset>
						<legend>输出描述/示例</legend>
						<dl class="nowrap">
							<dt>请输入详细信息：</dt>
							<dd><textarea name="query.port.outputSource" cols="60" rows="2">${query.port.outputSource}</textarea></dd>
						</dl>
					</fieldset>	
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