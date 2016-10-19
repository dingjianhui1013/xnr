<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String action = basePath+"page/cus/add.action"; //basePath+"page/cus/add.action";

request.setAttribute("action",action);
%>

<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>客户名称：</label>
				<input name="customerName" type="text" size="30" alt="请输入客户名称" class="required"/>
			</p>	
			
			<p>
				<label>国家：</label>
				<input name="customerCountry" type="text" class="required" size="30" alt="请输入国家"/>
			</p>
			<p>
				<label>省份：</label>
				<input name="customerProvince" type="text" size="30" alt="请输入省份"/>
			</p>
			<p>
				<label>城市：</label>
				<input name="customerCity" type="text" size="30" alt="请输入城市"/>
			</p>
			<p>
				<label>规模：</label>
				<input name="customerExtent" type="text" size="30" alt="请输入企业规模"/>
			</p>
			<p>
				<label>级别：</label>
				<select name="customerLevel" class="required combox">					
					<option value="A">A</option>
					<option value="B" selected>B</option>
					<option value="C">C</option>
				</select>			
			</p>
			<div class="divider"></div>			
			<fieldset>
				<legend>客户地址</legend>
				<dl class="nowrap">
					<dt>请输入详细地址：</dt>
					<dd><textarea class="required" name="customerAddr" cols="60" rows="2" value="${customerAddr}"></textarea></dd>
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
