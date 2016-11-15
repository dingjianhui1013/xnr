<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/staff/modify.action";
	String lookup = basePath + "page/org/lookup.action";
	String leadStaffLookup = basePath + "page/staff/leadStaffLookup.action";
	
	request.setAttribute("action",action);
	request.setAttribute("lookup",lookup);
	request.setAttribute("leadStaffLookup", leadStaffLookup);
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
					
		<input type="hidden" name="queryStime" value="${queryStime}" />
		<input type="hidden" name="queryEtime" value="${queryEtime}" />
		<input type="hidden" name="queryStaffname" value="${queryStaffname}" />
		<input type="hidden" name="queryStaffStatus" value="${queryStaffStatus}" />			
		<input type="hidden" name="queryOrg1.id" value="${queryOrg1.id}" />		
		<input type="hidden" name="queryOrg1.organizationName" value="${queryOrg1.organizationName}" />
		
		<input type="hidden" name="totalCount" value="${totalCount}" />		
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${param.orderField}" />
		<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="divid" value="${divid}" />	
			<fieldset>
				<legend>基础信息</legend>
			<p>
				<label>ID：</label>
				<input name="staffid" readonly="readonly" type="text" size="30" value="${vo.userid}" class="required"/>
			</p>	
			<p>
				<label>员工姓名：</label>
				<input name="staffname" type="text" size="30" value="${vo.staffName}" class="required"/>
			</p>	
			<p>
				<label>员工密码：</label>
				<input name="pswd" type="password" size="30" class="alphanumeric" minlength="3" maxlength="20"/>
			</p>
			<p>
				<label>员工编号：</label>
				<input name="login_id" type="text" size="30" value="${vo.login_id}" class="required"/>
			</p>	
			<p>
				<label>手机号：</label>
				<input name="mobile" type="text" size="30" value="${vo.mobile}" class="phone"/>
			</p>	
			<p>
				<label>邮箱：</label>
				<input name="email" type="text" size="30" value="${vo.email}" class="email"/>
			</p>
			<p>
				<label>身份证：</label>
				<input name="idcard" type="text" size="30" value="${vo.idcard}"/>
			</p>
			<p>
				<label>性别：</label>
				<select class="combox" name="staff.gender">								
					<c:choose>
						<c:when test="${vo.staff.gender==1}">
							<option value=1 selected="selected">女</option>
							<option value=0>男</option>
						</c:when>
						<c:otherwise>
							<option value=0>男</option>	
							<option value=1>女</option>	
						</c:otherwise>
					</c:choose>												
				</select>
			</p>	
			<p>
				<label>生日：</label>
				<input type="text" name="staff.birthday" yearstart="-80" yearend="80" value="<fmt:formatDate value="${vo.staff.birthday}" pattern="yyyy-MM-dd"/>" class="date" readonly="true" />
				<a class="inputDateButton" href="javascript:;">选择</a>	
			</p>
			
			<p>
				<label>工作年限：</label>
				<input name="worklife" type="text" size="30" value="${vo.worklife}" class="number"/>
			</p>
			<p>
				<label>入职时间：</label>
				<input type="text" name="workingtime" class="date" value="${vo.workingtime}" readonly="true" />
				<a class="inputDateButton" href="javascript:;">选择</a>	
			</p>
			<p>
				<label>上级领导：</label>
				<input class="readonly" name="staff.leadStaffId" readonly="readonly" value="${vo.staff.leadStaffId}" type="hidden"/>
				<input readonly="readonly" name="staff.leadStaffName" value="${vo.staff.leadStaffName}" type="text"/>		
				<a class="btnLook" href="${leadStaffLookup}" lookupGroup="staff">查找带回</a>	
			</p>
			<div class="divider"></div>
			<p>
				<label>所属部门：</label>
					<input id="inputOrg1" name="org1.id" value="${vo.orgid}"  type="hidden"/>
					<input name="org1.organizationName" value="${vo.orgname}" readonly="true" type="text" postField="keyword"  lookupGroup="org1"/>
					<a class="btnLook" href="${lookup}" lookupGroup="org1">查找带回</a>	
			</p>
			<p>
				<label>是否为部门主管：</label>
					<c:choose>
					<c:when test="${vo.manager}">
						<input name="manager" value="1" type="checkbox" checked="checked">											
					</c:when>
					<c:otherwise>
						<input name="manager" value="1" type="checkbox">
					</c:otherwise>
				</c:choose>
			</p>
			<p>
				<label>是否为部门总监：</label>
					<c:choose>
					<c:when test="${vo.staff.director==1}">
						<input name="director" value="1" type="checkbox" checked="checked">											
					</c:when>
					<c:otherwise>
						<input name="director" value="1" type="checkbox">
					</c:otherwise>
				</c:choose>
			</p>
			<p>
				<label>员工状态：</label>
				<select class="combox" name="staffStatus">
					<c:if test="${statusList!=null}">
						<c:forEach items="${statusList}" var="loop">
							<c:choose>
								<c:when test="${vo.statusid==loop.id}">
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
			</fieldset>
			<div class="divider"></div>
			
			
			<fieldset>
				<legend>商户相关</legend>
			
				<p>
					<label>银行账户信息（转账用）：</label>
					<input name="bankInformation" type="text" size="30" value="${vo.bankInformation}" alt="请输入银行账户信息"/>
				</p>
				<p>
					<label>商品折扣：</label>
					<input name="discount" type="text" size="5" alt="请输数字" value="${vo.discount}" class="number"/>(例如：0.9为9折，最大不可超过1.0(1为无折扣))
					<span class="info"></span>
				</p>
				<p>
					<label>最早送达时间：</label>
					<select class="combox"  name="staff.theEarliestTime">
						<c:forEach var="x" begin="7" end="16" step="1">
							<c:choose>
								<c:when test="${vo.staff.theEarliestTime==x}">
									<option value="${x}" selected="selected">最早配送时间 ${x}点</option>
								</c:when>
								<c:otherwise>
									<option value="${x}">最早配送时间 ${x}点</option>	
								</c:otherwise>
							</c:choose>					
						</c:forEach>
						
					</select>
				</p>
				<p>
					<label>最晚送达时间：</label>
					<select class="combox"  name="staff.theLatestTime">
						<c:forEach var="x" begin="8" end="16" step="1">
							<c:choose>
								<c:when test="${vo.staff.theLatestTime==x}">
									<option value="${x}" selected="selected">最晚配送时间 ${x}点</option>
								</c:when>
								<c:otherwise>
									<option value="${x}">最晚配送时间 ${x}点</option>	
								</c:otherwise>
							</c:choose>					
						</c:forEach>
						
					</select>
					
				</p>
				<p>
					<label>商户账期：</label>
						<select class="combox" name="staff.billTime">
							<c:if test="${billList!=null}">
								<c:forEach items="${billList}" var="loop">
									<c:choose>
										<c:when test="${vo.staff.billTime==loop.id}">
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
					<input name="viewDiscount" type="text" size="5" alt="请输数字" value="${vo.staff.viewDiscount}" class="number"/>(例如：0.9为9折，可超过1.0(1为无折扣))
					<span class="info"></span>
				</p>	
				<fieldset>
						<legend>用户固定备注</legend>
						<dl class="nowrap">
							<dt>请输入备注：</dt>
							<dd><textarea name="staff.userDesc" cols="60" rows="5">${vo.staff.userDesc}</textarea></dd>
						</dl>
					</fieldset>		
			</fieldset>
			<fieldset>
				<legend>线上合作方相关</legend>
				<p>
					<label>接口请求服务器IP：</label>
					
					<input name="ip" type="text" size="25" alt="请输入IP地址，类似192.168.0.1" value="${vo.staff.ip}"/>
					
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