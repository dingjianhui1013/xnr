<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String actionForm = basePath+"page/wx/wxreactivemessage/add.action";	
	String wxuserlookup = basePath + "page/wx/wxuser/lookup.action";
	String wxmessagelookup = basePath + "page/wx/wxmessage/lookup.action"; //?query.wxmessage.msgTypeId=150
	String viewattach = basePath + "page/attach/view.action";
	String menuLookup = basePath + "page/wx/wxmenu/lookup.action?query.menu.typeid=137";
	
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("wxuserlookup",wxuserlookup);
	request.setAttribute("wxmessagelookup",wxmessagelookup);
	request.setAttribute("viewattach",viewattach);
	request.setAttribute("menuLookup",menuLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>对应菜单：</label>					
				<input name="query.wxmenu.menu.id" type="hidden" readonly="readonly" type="text"/>
				<input name="query.wxmenu.menu.menuName" readonly="readonly" type="text"/>
				<a class="btnLook" href="${menuLookup}" lookupGroup="query.wxmenu.menu">查找带回</a>	
			</p>
			<p>
				<span class="info">如果要回复用户手工填写的消息则不用选择对应菜单</span>	
			</p>
			<div class="divider"></div>
			<fieldset>
				<legend>接收内容-非按钮点击</legend>
				<dl class="nowrap">
					<dt>请输入接收用户的消息内容：</dt>
					<dd><textarea name="query.reactiveMessage.recContent" cols="60" rows="5"></textarea></dd>
					<dd><span class="info">如果选了对应菜单则不用填写此项</span></dd>
				</dl>
			</fieldset>
			<p>
				<label>所属微信用户：</label>								
				<input name="query.wxuservo.wxuserid" type="hidden" readonly="readonly" type="text"/>
				<input name="query.wxuservo.staffName" class="required" readonly="readonly" type="text"/>
				<a class="btnLook" href="${wxuserlookup}" lookupGroup="query.wxuservo">查找带回</a>											
			</p>
			<div class="divider"></div>
			<div class="tabs">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li class="selected"><a href="javascript:void(0)"><span>图文消息管理</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent">
				<div>
					<table class="list nowrap itemDetail" addButton="新增图文消息" width="100%">
						<thead>
							<tr>																							
								<th type="lookup" width="30%" name="wxmessagelist[#index#].msgTitle"  
									id="wxmessagelist_#index#_local"  lookupGroup="wxmessagelist[#index#]" 		
									lookupPk="id"							 
									lookupUrl="${wxmessagelookup}" readonly="readonly" postField="keywords" size="20"  
									>消息标题</th>
								<th type="input" readonly="readonly" width="30%" name="wxmessagelist[#index#].msgContent" 									 
									size="20"  >消息内容</th>
								<th type="input" width="30%" name="wxmessagelist[#index#].sortOrder" 									 
									size="20" fieldClass="digits" >消息排序(由大->小排序)</th>
								<th type="del"  width="10%">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
				<div class="tabsFooter">
					<div class="tabsFooterContent"></div>
				</div>
			</div>
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