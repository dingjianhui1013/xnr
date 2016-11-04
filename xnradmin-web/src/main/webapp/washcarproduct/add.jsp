<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String getWcp = basePath+"page/WashCarProduct/all.action";
	String actionForm = basePath+"page/WashCarProduct/add.action";	
	String lookup = basePath + "page/WashCarProduct/lookup.action";
	String statusLookup = basePath + "page/WashCarProduct/statusLookup.action";
	String productTypeLookup = basePath + "page/WashCarProduct/productTypeLookup.action";
	String businessTypeLookup = basePath + "page/WashCarProduct/businessTypeLookup.action";
	String payCusLookup = basePath + "page/cus/lookup.action";
	String carTypeLookup = basePath + "page/CarType/lookup.action";
	
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("getWcp",getWcp);
	request.setAttribute("lookup",lookup);
	request.setAttribute("statusLookup",statusLookup);
	request.setAttribute("productTypeLookup",productTypeLookup);
	request.setAttribute("businessTypeLookup",businessTypeLookup);
	request.setAttribute("payCusLookup",payCusLookup);
	request.setAttribute("carTypeLookup",carTypeLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>产品名称：</label>
				<input name="productName" type="text" size="30" alt="请输入产品名称" class="required"/>
			</p>	
			<p>
				<label>产品资费：</label>
				<input name="productPrice" type="text" size="30" alt="请输入产品资费" class="required  number"/>
			</p>
			<p>
				<label>充值资费：</label>
				<input name="addRechargePrice" type="text" size="30" alt="请输入充值资费" class="required number"/>
			</p>
			<p>
				<label>产品折扣：</label>
				<input name="discount" type="text" size="30" alt="请输入产品折扣" value="1" class="required number"/>
			</p>
			<p>
				<label>产品计次：</label>
				<input name="washCount" type="text" size="30" alt="请输入产品计次" value="0" class="required number"/>
			</p>
			<p>
				<label>产品类型：</label>
				<input class="readonly" name="productType1.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="productType1.statusName" type="text"/>	
				<a class="btnLook" href="${productTypeLookup}" lookupGroup="productType1">查找带回</a>	
			</p>
			<p>
				<label>产品状态：</label>
				<input class="readonly" name="productStatus1.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="productStatus1.statusName" type="text"/>	
				<a class="btnLook" href="${statusLookup}" lookupGroup="productStatus1">查找带回</a>	
			</p>
			<p>
				<label>业务类型：</label>
				<input class="readonly" name="businessType1.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="businessType1.statusName" type="text"/>	
				<a class="btnLook" href="${businessTypeLookup}" lookupGroup="businessType1">查找带回</a>	
			</p>
			<p>
				<label>付费商：</label>
				<input class="readonly" name="payCus1.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="payCus1.customerName" type="text"/>	
				<a class="btnLook" href="${payCusLookup}" lookupGroup="payCus1">查找带回</a>	
			</p>
			<p>
				<label>付费商IP：</label>
				<input name="payCusIp" type="text" size="30" value="127.0.0.1"  class="required"/>
			</p>
			<p>
				<label>选择父类产品：</label>
				<input class="readonly" name="parentProduct1.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="parentProduct1.productName" type="text"/>	
				<a class="btnLook" href="${lookup}" lookupGroup="parentProduct1">查找带回</a>	
			</p>	
			<p>
				<label>选择车辆类型：</label>
				<input class="readonly" name="carType.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="carType.typeName" type="text"/>	
				<a class="btnLook" href="${carTypeLookup}" lookupGroup="carType">查找带回</a>	
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