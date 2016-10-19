<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String action =  basePath+"message/msg/sendMsg.action";    
	String staffLookup = basePath+"page/staff/multLookup.action";
	//String orgLookup = basePath+"page/org/multLookup.action";
	
    request.setAttribute("action",action);   
    request.setAttribute("staffLookup",staffLookup);
    //request.setAttribute("orgLookup",orgLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2 class="contentTitle">发送消息</h2>
<form action="${action}" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
<div class="pageContent">
	<div class="pageFormContent" layoutH="97">
		<dl class="nowrap">
			<dt>消息标题：</dt>
			<dd>
				<input class="required" type="text" name="query.msg.title" value="${query.msg.title}"/>								
				<span class="info"></span>
			</dd>
		</dl>
		<dl class="nowrap">
			<dt>接收人：</dt>
			<dd>				
				<input name="query.recStaffIds" value="${query.recStaffIds}"  type="hidden" class="required"/>
				<input name="query.recStaffNames" value="${query.recStaffNames}" class="required" readonly="readonly" type="text"/>
				<a class="btnLook" href="${staffLookup}" lookupGroup="query">查找带回</a>	
			</dd>
		</dl>	
		<dl>			
			<dt>发送短信：</dt>
			<dd>
				<input name="query.sendSms" value="true" type="checkbox" checked="checked">
			</dd>
					
		</dl>
		<dl>
			
			<dt>发送邮件：</dt>
			<dd>
				<input name="query.sendMail" value="true" type="checkbox" checked="checked">			
			</dd>
		</dl>
			
		<fieldset>
			<legend>消息内容</legend>
			<dl class="nowrap">
				<dt>请输入消息内容：</dt>
				<dd><textarea name="query.msg.content" cols="50" rows="5"">${processFlow.remark}</textarea></dd>
			</dl>
		</fieldset>
							
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">发送</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</div>
</form>
