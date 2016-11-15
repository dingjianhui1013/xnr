<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/business/admin/commodity/goods/modifyAllGoods.action;jsessionid="+session.getId();
	
	request.setAttribute("action",action);
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	
	<form method="post" action="${action}" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this)">
		<div class="pageFormContent" layoutH="56">
			
			
			<fieldset>
				<label>选择上传文件：</label>
				<p>
					请选择要上传的EXCEL（必须在导出的商品EXCEL基础上修改且列顺序不可变）：
				</p>
				<input name="goodsUploadFile" type="file" />
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