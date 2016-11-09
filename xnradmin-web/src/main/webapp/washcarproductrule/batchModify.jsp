<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String productLookup = basePath + "page/WashCarProduct/lookup.action";
	String statusLookup = basePath + "page/WashCarProductRule/statusLookup.action";
	String ruleTypeLookup = basePath + "page/WashCarProductRule/maxTypeLookup.action";
	String descriptionLookup = basePath + "page/Common/Region/Description/batchLookup.action";
	String agentStaffLookup = basePath + "page/staff/lookup.action";
	String actionForm = basePath+"page/WashCarProductRule/batchModify.action";	
	String lookup = basePath + "page/WashCarProductRule/lookup.action";
	
	request.setAttribute("actionForm",actionForm);
	request.setAttribute("lookup",lookup);
	request.setAttribute("statusLookup",statusLookup);
	request.setAttribute("ruleTypeLookup",ruleTypeLookup);
	request.setAttribute("productLookup",productLookup);
	request.setAttribute("descriptionLookup",descriptionLookup);
	request.setAttribute("agentStaffLookup",agentStaffLookup);
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="post" action="${actionForm}" class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="97">
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li class="selected"><a href="javascript:void(0)"><span>批量修改规则</span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div>
						<table class="list nowrap itemDetail" addButton="修改规则" width="100%">
							<thead>
								<tr>
									<th type="lookup" name="ruleList[#index#].washCarProduct.productName" lookupGroup="ruleList[#index#].washCarProduct" lookupUrl="${productLookup}" size="15" fieldClass="required">选择产品</th>
									<th type="lookup" name="ruleList[#index#].agentCurrenStaff.staffName" mobile="mobile" lookupGroup="ruleList[#index#].agentCurrenStaff" lookupUrl="${agentStaffLookup}?queryOrgid=5" size="5" fieldClass="required">选择合作方</th>
									<th type="lookup" name="ruleList[#index#].provinceVO.community" descriptionid="descriptionid" description="description" lookupGroup="ruleList[#index#].provinceVO" lookupUrl="${descriptionLookup}" size="10" fieldClass="required">选择区域</th>
									<th type="lookup" name="ruleList[#index#].productRuleStatus.statusName" lookupGroup="ruleList[#index#].productRuleStatus" lookupUrl="${statusLookup}" size="1" fieldClass="required">状态</th>
									<th type="lookup" name="ruleList[#index#].productRuleMaxType.statusName" lookupGroup="ruleList[#index#].productRuleMaxType" lookupUrl="${ruleTypeLookup}" size="1" fieldClass="required">限制</th>
									<th type="date" dateFmt="yyyy-MM-dd HH:mm:ss" name="ruleList[#index#].ruleStartTime" size="10" fieldClass="required">生效时间</th>
									<th type="date" dateFmt="yyyy-MM-dd HH:mm:ss" name="ruleList[#index#].ruleEndTime" size="10" fieldClass="required">结束时间</th>
									<th type="input" name="ruleList[#index#].ruleCountMax" size="1" defaultVal="0" fieldClass="digits" >时间限</th>
									<th type="input" name="ruleList[#index#].ruleDayMax" size="1" defaultVal="0" fieldClass="digits" >日限</th>
									<th type="input" name="ruleList[#index#].ruleMonthMax" size="1" defaultVal="0" fieldClass="digits" >月限</th>
									<th type="input" name="ruleList[#index#].ruleYearMax" size="1" defaultVal="0" fieldClass="digits" >年限</th>
									<th type="input" name="ruleList[#index#].rulePaymentPrice" size="1" defaultVal="0" fieldClass="required number">结算单价</th>
									<th type="input" name="ruleList[#index#].ruleSyncUrl" size="1" defaultVal="127.0.0.1">同步地址</th>
									<th type="del">操作</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
				<div class="tabsFooter">
					<div class="tabsFooterContent"></div>
				</div>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
				</ul>
			</div>
		</div>
	</div>
</form>