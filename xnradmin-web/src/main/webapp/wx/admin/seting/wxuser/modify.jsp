<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/wx/wxuser/modify.action";
	String staffLookup = basePath+"page/staff/lookup.action";
	
	request.setAttribute("action",action);
	request.setAttribute("staffLookup", staffLookup);
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input name="query.wxuser.id" value="${query.wxuser.id}" type="hidden"/>
		
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>微信token：</label>
				<input name="query.wxuser.token" value="${query.wxuser.token}" type="text" size="30" alt="微信token" class="required"/>
				
			</p>	
			<p>
				<label>微信appid：</label>
				<input name="query.wxuser.appid" value="${query.wxuser.appid}" type="text" size="30" alt="微信appid" class="required"/>
			</p>	
			<p>
				<label>微信appSecret：</label>
				<input name="query.wxuser.appSecret" value="${query.wxuser.appSecret}" type="text" size="30" alt="微信appSecret" class="required"/>
			</p>	
			
			
			<p>				
				<label>选定用户：</label>
				<input id="query.staff.id" name="query.staff.id" value="${query.staff.id}" type="hidden"/>
				<input name="query.staff.staffName" value="${query.staff.staffName}" readonly="true" type="text" postField="keyword"  lookupGroup="query.staff"/>
				<a class="btnLook" href="${staffLookup}" lookupGroup="query.staff">查找带回</a>
			</p>		
			<div class="divider"></div>
			<fieldset>
				<legend>首次关注欢迎语</legend>
				<dl class="nowrap">
					<dt>请输入欢迎语（暂时只支持文本消息）：</dt>
					<dd><textarea class="required" name="query.wxuser.welcome" cols="60" rows="5">${query.wxuser.welcome}</textarea></dd>
				</dl>
			</fieldset>			
			<p>
				<label>状态：</label>
				<select class="combox" name="query.wxuser.status">
					<c:if test="${statusList!=null}">
						<c:forEach items="${statusList}" var="loop">
							<c:choose>
								<c:when test="${query.wxuser.status==loop.id}">
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