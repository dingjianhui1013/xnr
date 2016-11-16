<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String actionForm = basePath+"page/wx/wxmessage/add.action";	
	String upload = basePath + "page/attach/uploadAttachInfo.action";
	
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("upload",upload);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>消息标题：</label>
				<input name="query.wxmessage.msgTitle" type="text" size="30" alt="请输入消息标题,图文类消息用"/>
			</p>	
			<p>
				<label>消息类型</label>
				<select class="combox" name="query.msgType.id">
					<c:if test="${statusList!=null}">
						<c:forEach items="${statusList}" var="loop">	
							<option value=${loop.id}>${loop.readme}</option>
						</c:forEach>
					</c:if>						
				</select>
			</p>
			<p>
				<label>图文消息链接：</label>
				<input name="query.wxmessage.clickUrl" type="text" size="30" alt="图文消息点击跳转用"/>
			</p>
			<p>
				<label>图文消息图片：</label>
				<!-- 
				<input type="text" name="upload.uploadContent" size="15" readonly="readonly" />
				 -->
				<input readonly="readonly"  name="query.upload.groupid"/>
				<a class="btnAttach" href="${upload}" lookupGroup="query.upload"  lookupPk="1" width="660" height="350" title="添加附件">添加附件</a>
			</p>
			<div class="divider"></div>
			<fieldset>
				<legend>消息内容</legend>
				<dl class="nowrap">
					<dt>请输入消息内容：</dt>
					<dd><textarea name="query.wxmessage.content" cols="60" rows="5"></textarea></dd>
				</dl>
			</fieldset>
			<div class="divider"></div>
			<fieldset>
				<legend>图文消息描述</legend>
				<dl class="nowrap">
					<dt>请输入描述内容：</dt>
					<dd><textarea name="query.wxmessage.msgDescription" cols="60" rows="5"></textarea></dd>
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