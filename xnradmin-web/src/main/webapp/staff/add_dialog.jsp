<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/staff/add.action";
	String lookup = basePath + "page/org/lookup.action";
	String leadStaffLookup = basePath + "page/staff/leadStaffLookup.action";
	
	request.setAttribute("action",action);
	request.setAttribute("lookup", lookup);
	request.setAttribute("leadStaffLookup", leadStaffLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="divid" value="${divid}" />	
		<div class="pageFormContent" layoutH="56">
			<fieldset>
				<legend>基础信息</legend>
				<p>
					<label>登陆名：</label>
					<input name="staffno" type="text" size="30" alt="请输入登陆名" class="required"/>
				</p>	
				<p>
					<label>员工名称：</label>
					<input name="staffname" type="text" size="30" alt="请输入员工名称" class="required"/>
				</p>	
				<p>
					<label>手机号码：</label>
					<input name="mobile" type="text" size="30" alt="请输入手机号码" class="phone"/>
				</p>	
				<p>
					<label>邮箱：</label>
					<input name="email" type="text" size="30" alt="请输入邮箱" class="email"/>
				</p>	
				<p>
					<label>身份证：</label>
					<input name="idcard" type="text" size="30" alt="请输入身份证"/>
				</p>
				<p>
					<label>性别：</label>
					<select class="combox" name="staff.gender">
						<option value=0>男</option>
						<option value=1>女</option>				
					</select>		
				</p>
				<p>
					<label>生日：</label>
					<input type="text" name="staff.birthday" yearstart="-80" yearend="1"  class="date" readonly="true" />
					<a class="inputDateButton" href="javascript:;">选择</a>	
				</p>
				<p>
					<label>工作年限：</label>
					<input name="worklife" type="text" size="30" alt="请输入数字" class="number"/>
				</p>
				<p>
					<label>入职时间：</label>
					<input type="text" name="workingtime" class="date" yearstart="-80" yearend="1" readonly="true" />
					<a class="inputDateButton" href="javascript:;">选择</a>	
				</p>
				<p>
				<c:choose>
							<c:when test="${currentStaff.organizationId==5}">
								<input type="hidden" name="staff.leadStaffId" value="${currentStaff.id}" />	
								<input type="hidden" name="staff.leadStaffName" value="${currentStaff.staffName}" />	
							</c:when>
							<c:otherwise>
								<label>上级领导：</label>
								<input class="readonly" name="staff.leadStaffId" readonly="readonly" type="hidden"/>
								<input readonly="readonly" name="staff.leadStaffName" type="text"/>		
								<a class="btnLook" href="${leadStaffLookup}" lookupGroup="staff">查找带回</a>	
							</c:otherwise>
				</c:choose>		
				</p>
				<p>
				<label>密码：</label>
				<input name="pswd" type="password" size="30" class="required alphanumeric" minlength="3" maxlength="20"/>
			</p>
			<p>
				<c:choose>
						<c:when test="${currentStaff.organizationId==5}">
							<input type="hidden" name="org1.id" value="6" />	
							<input type="hidden" name="org1.organizationName" value="技师组" />		
						</c:when>
						<c:otherwise>
							<label>组织名称：</label>
							<input name="org1.id" class="required" readonly="readonly"  type="hidden"/>
							<input name="org1.organizationName" readonly="readonly" type="text"/>
							<a class="btnLook" href="${lookup}" lookupGroup="org1">查找带回</a>	
						</c:otherwise>
				</c:choose>		
			
				
			</p>				
			<p>
				<label>是否为部门主管：</label>
				<input name="manager" value="1" type="checkbox">			
			</p>	
			<p>
				<label>是否为部门总监：</label>										
				<input name="director" value="1" type="checkbox">				
			</p>	
			<p>
				<label>人员状态：</label>
				<select class="combox" name="staffStatus">
					<c:if test="${statusList!=null}">
						<c:forEach items="${statusList}" var="loop">	
							<c:choose>
								<c:when test="${currentStaff.organizationId==5}">	
									<c:choose>
										<c:when test="${loop.id==1 || loop.id==5 || loop.id==6}">	
											<option value=${loop.id}>${loop.statusName}</option>
										</c:when>
									</c:choose>	
								</c:when>	
								<c:otherwise>
									<option value=${loop.id}>${loop.statusName}</option>
								</c:otherwise>
							</c:choose>	
						</c:forEach>
					</c:if>						
				</select>		
			</p>
			</fieldset>
			<div class="divider"></div>
			
			
			<fieldset>
				<legend>商户相关</legend>
				<p>
					<label>银行账户信息（转账用）：</label>
					<input name="bankInformation" type="text" size="30" alt="请输入银行账户信息"/>
					
				</p>
				<p>
					<label>商品折扣：</label>
					<input name="discount" type="text" size="5" alt="请输数字" value="1.0" class="number"/>(例如：0.9为9折，最大不可超过1.0(1为无折扣))
					<span class="info"></span>
				</p>
				<p>
					<label>最早送达时间：</label>
					<select class="combox" name="staff.theEarliestTime">
						<option value="7" selected>最早配送时间 7点</option>
	                	<option value="8">最早配送时间 8点</option>
	                	<option value="9">最早配送时间 9点</option>
	                	<option value="10">最早配送时间 10点</option>
	                	<option value="11">最早配送时间 11点</option>
	                	<option value="12">最早配送时间 12点</option>
	                	<option value="13">最早配送时间 13点</option>
	                	<option value="14">最早配送时间 14点</option>
	                	<option value="15">最早配送时间 15点</option>
	                	<option value="16">最早配送时间 16点</option>
					</select>
				</p>
				<p>
					<label>最晚送达时间：</label>
					<select class="combox" name="staff.theLatestTime">
							<option value="8" selected>最晚配送时间 8点</option>
	                		<option value="9">最晚配送时间 9点</option>
	                		<option value="10">最晚配送时间 10点</option>
	                		<option value="11">最晚配送时间 11点</option>
	                		<option value="12">最晚配送时间 12点</option>
	                		<option value="13">最晚配送时间 13点</option>
	                		<option value="14">最晚配送时间 14点</option>
	                		<option value="15">最晚配送时间 15点</option>
	                		<option value="16">最晚配送时间 16点</option>
	                		<option value="17">最晚配送时间 17点</option>
					</select>
				</p>
				<p>
					<label>商户账期：</label>
						<select class="combox" name="staff.billTime">
							<c:if test="${billList!=null}">
								<c:forEach items="${billList}" var="loop">
									<c:choose>
										<c:when test="${currentStaff.billTime==loop.id}">
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
				<p>
					<label>显示商品折扣：</label>
					<input name="viewDiscount" type="text" size="5" alt="请输数字" value="1.0" class="number"/>(例如：0.9为9折，可超过1.0(1为无折扣))
					<span class="info"></span>
				</p>
				<p>
					<fieldset>
						<legend>用户固定备注</legend>
						<dl class="nowrap">
							<dt>请输入备注：</dt>
							<dd><textarea name="staff.userDesc" cols="60" rows="5"></textarea></dd>
						</dl>
					</fieldset>		
				</p>
			</fieldset>
			<fieldset>
				<legend>线上合作方相关</legend>
				<p>
					<label>接口请求服务器IP：</label>
					<input name="ip" type="text" size="25" alt="请输入IP地址，类似192.168.0.1"/>
					
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