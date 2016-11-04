<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String getWcpr = basePath+"page/WashCarProductRule/all.action";
	String actionForm = basePath+"page/WashCarProductRule/add.action";	
	String productLookup = basePath + "page/WashCarProduct/lookup.action";
	String statusLookup = basePath + "page/WashCarProductRule/statusLookup.action";
	String ruleTypeLookup = basePath + "page/WashCarProductRule/maxTypeLookup.action";
	String descriptionLookup = basePath + "page/Common/Region/Description/lookup.action";
	String agentStaffLookup = basePath + "page/staff/lookup.action";
	
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("getWcpr",getWcpr);
	request.setAttribute("productLookup",productLookup);
	request.setAttribute("statusLookup",statusLookup);
	request.setAttribute("ruleTypeLookup",ruleTypeLookup);
	request.setAttribute("descriptionLookup",descriptionLookup);
	request.setAttribute("agentStaffLookup",agentStaffLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${actionForm}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>规则名称：</label>
				<input name="ruleName" type="text" size="30" alt="请输入规则名称" class="required"/>
			</p>	
			<p>
				<label>选择产品：</label>
				<input class="readonly" name="washCarProduct.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="washCarProduct.productName" type="text"/>	
				<a class="btnLook" href="${productLookup}" lookupGroup="washCarProduct">查找带回</a>	
			</p>
			<p>
				<label>选择合作方：</label>
				<input class="readonly" name="agentCurrenStaff.mobile" readonly="readonly" value="${agentStaffMobile}" type="hidden"/>
				<input class="readonly" name="agentCurrenStaff.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="agentCurrenStaff.staffName" type="text"/>	
				<a class="btnLook" href="${agentStaffLookup}?queryOrgid=5" lookupGroup="agentCurrenStaff">查找带回</a>	
			</p>
			<p>
				<label>对应区域：</label>
				<input class="readonly" name="description.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="description.description" type="text"/>	
				<a class="btnLook" href="${descriptionLookup}" lookupGroup="description" rel="region_add" >查找带回</a>	
			</p>
			<p>
				<label>规则状态：</label>
				<input class="readonly" name="productRuleStatus.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="productRuleStatus.statusName" type="text"/>	
				<a class="btnLook" href="${statusLookup}" lookupGroup="productRuleStatus">查找带回</a>	
			</p>
			<p>
				<label>限制类型：</label>
				<input class="readonly" name="productRuleMaxType.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="productRuleMaxType.statusName" type="text"/>	
				<a class="btnLook" href="${ruleTypeLookup}" lookupGroup="productRuleMaxType">查找带回</a>	
			</p>
			<p>
				<label>生效时间：</label>
				<input type="text" name="startTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" class="date" readonly="true" />
				<a class="inputDateButton" href="javascript:;">选择</a>	
			</p>
			<p>
				<label>结束时间：</label>
				<input type="text" name="endTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" class="date" readonly="true" />
				<a class="inputDateButton" href="javascript:;">选择</a>	
			</p>
			<p>
				<label>时间限制次数：</label>
				<input name="countMax" type="text" size="30" value="0"  class="required"/>
			</p>
			<p>
				<label>日限次数：</label>
				<input name="dayMax" type="text" size="30" value="0"  class="required"/>
			</p>
			<p>
				<label>月限次数：</label>
				<input name="monthMax" type="text" size="30" value="0"  class="required"/>
			</p>
			<p>
				<label>年限次数：</label>
				<input name="yearMax" type="text" size="30" value="0"  class="required"/>
			</p>
			<p>
				<label>结算单价：</label>
				<input name="paymentPrice" type="text" size="30" value="0"  class="required"/>
			</p>
			<p>
				<label>同步地址：</label>
				<input name="syncUrl" type="text" size="30" value="127.0.0.1"  class="required"/>
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