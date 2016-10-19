<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	 
	String action = basePath + "page/staff/supplierInfo.action?queryOrgid=18";	
	String modify = basePath+"page/staff/modifyinfo.action";
	String del =  basePath+"page/staff/del.action";
	String add =  basePath+"page/staff/addinfo.action";
	String lookup = basePath + "page/staff/lookup.action?queryOrgid=19";
	String viewWxqrcode = basePath + "page/wx/wxqrcode/view.action";
	String addOrder = basePath+"page/business/admin/orderrecord/addinfo.action";
	String modifyStatus = basePath + "page/staff/modifyStatus.action";
				
	request.setAttribute("action",action);
	request.setAttribute("lookup", lookup);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);	
	request.setAttribute("add", add);
	request.setAttribute("viewWxqrcode", viewWxqrcode);
	request.setAttribute("addOrder", addOrder);
	request.setAttribute("modifyStatus", modifyStatus);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="org1.id" value="${org1.id}" />
		<input type="hidden" name="org1.organizationName" value="${org1.organizationName}" />	
			
		<input type="hidden" name="staffname" value="${staffname}" />
		<input type="hidden" name="sTime" value="${sTime}" />
		<input type="hidden" name="eTime" value="${eTime}" />
		<input type="hidden" name="staffStatus" value="${staffStatus}" />
		<input type="hidden" name="manager" value="${manager}" />
		<input type="hidden" name="director" value="${director}" />
		<input type="hidden" name="login_id" value="${login_id}" />
		<input type="hidden" name="leaderStaff.id" value="${leaderStaff.id}" />
		<input type="hidden" name="leaderStaff.staffName" value="${leaderStaff.staffName}" />
		
		
		<input type="hidden" name="totalCount" value="${totalCount}" />	
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${param.orderField}" />
		<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${action}" method="post">
	<div class="searchBar">		
		<table class="searchContent">
			<tr>
				<td>		
					员工名称（可模糊）：<input type="text" size=15 value="${staffname}" name="staffname" />
				</td>
				<td>		
					登陆名称（可模糊）：<input type="text" size=15 value="${login_id}" name="login_id" />
				</td>
				
				<td>					
					所属商务经理		
					<input id="inputOrg1" name="leaderStaff.id" value="${leaderStaff.id}" type="hidden"/>
					<input name="leaderStaff.staffName" size=15 value="${leaderStaff.staffName}" readonly="true" type="text"/>
					
				</td>
				<td><a class="btnLook" href="${lookup}" lookupGroup="leaderStaff">查找带回</a></td>
				
			</tr>
			<!-- 
			<tr>
				
				<td>
					是否部门总监	:
					<c:choose>
						<c:when test="${director==1}">
							<input name="director" value="1" type="checkbox" checked="checked">											
						</c:when>
						<c:otherwise>
							<input name="director" value="1" type="checkbox">
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					是否部门主管	:
					<c:choose>
						<c:when test="${manager==1}">
							<input name="manager" value="1" type="checkbox" checked="checked">											
						</c:when>
						<c:otherwise>
							<input name="manager" value="1" type="checkbox">
						</c:otherwise>
					</c:choose>
				</td>
				
				
			</tr>
			 -->
			<tr>
				
				<td>				
					建档日期：从 <input type="text" name="sTime" class="date" readonly="true" />				
				</td>
				<td>
					 到 <input type="text" name="eTime" class="date" readonly="true" /> 之间
				</td>
				<td>				
					
					<select class="combox" name="staffStatus">
						<option value="all">请选择状态类型</option>	
						<c:if test="${statusList!=null}">
							<c:forEach items="${statusList}" var="loop">																													
									<option value=${loop.id}>${loop.statusName}</option>	
							</c:forEach>
						</c:if>						
					</select>		
				</td>
				
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>				
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${add}" target="navTab"><span>添加</span></a></li>
			<!-- 
			<li><a class="edit" href="${modify}?queryid={sid_queryid}" target="navTab"><span>修改</span></a></li>
			 -->			
			<li class="line">line</li>			
			<!-- 
			<li><a class="delete" href="${del}?queryid={sid_queryid}&del=1" target="ajaxTodo" title="设置为离职状态?"><span>设置为离职状态</span></a></li>
			-->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="187">
		<thead>
			<tr>
				<!-- 
				<th width="30"  align="center">ID</th>
				 -->
				 
                <th width="30" orderField="userid" class="${useridsort}">编号</th>
                <th width="35">名称</th>
                <!-- 
                <th width="75">所属部门</th>
                <th width="30">主管</th>
                <th width="30">身份证</th>
                <th width="45">是否总监</th>
                <th width="20">性别</th>
                <th width="20">生日</th>
                -->
                <th width="45">上级领导</th>
                <th width="35">手机号</th>
                <!-- 
                <th width="35">邮箱</th>
                 -->
                <th width="45">显示折扣</th>
                <th width="45">建档日期</th>
                <th width="45">商品结算折扣</th>
                <th width="35">状态</th>
                <th width="45">结算周期</th>
                <th width="75">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<c:if test="${loop.orgid == 18}">
					<tr target="sid_queryid" rel="${loop.userid}">
						<!-- 
						<td>${loop.userid}</td>
						 -->
						<td>${loop.login_id}</td>
						<td>${loop.staffName}</td>
						<!-- 
						<td>${loop.orgname}</td>
						<td>							
							<c:choose>
								<c:when test="${loop.manager}">
									是												
								</c:when>
								<c:otherwise>
									否
								</c:otherwise>
							</c:choose>		
						</td>
						<td>${loop.idcard}</td>	
						<td>							
							<c:choose>
								<c:when test="${loop.staff.director==1}">
									是									
								</c:when>
								<c:otherwise>
									否
								</c:otherwise>
							</c:choose>		
						</td>
						<td>							
							<c:choose>
								<c:when test="${loop.staff.gender==1}">
									女									
								</c:when>
								<c:otherwise>
									男
								</c:otherwise>
							</c:choose>		
						</td>
						<td><fmt:formatDate value="${loop.staff.birthday}" pattern="yyyy-MM-dd"/></td>
						 -->
						<td>${loop.staff.leadStaffName}</td>									
						<td>${loop.mobile}</td>
						<!-- 
						<td>${loop.email}</td>
						 -->	
						<td>${loop.staff.viewDiscount}</td>			
						<td>${loop.createTime}</td>
						<td>${loop.discount}</td>						
						<td>${loop.statusName}</td>
						<td>
							<c:if test="${billList!=null}">
								<c:forEach items="${billList}" var="loopBill">
									<c:choose>
										<c:when test="${loop.staff.billTime !=null && loop.staff.billTime==loopBill.id}">
											${loopBill.statusName}
										</c:when>
									</c:choose>					
								</c:forEach>
							</c:if>
						</td>
						<td>				
							
							<a title="编辑" rel="businessStaffInfo" target="navTab" href="${modify}?queryid=${loop.userid}&queryOrg1.id=${org1.id}&queryOrgid=15&queryOrg1.organizationName=${org1.organizationName}&queryStaffname=${staffname}&queryStime=${sTime}&queryEtime=${eTime}&queryStaffStatus=${staffStatus}&totalCount=${totalCount}&pageNum=${pageNum}&numPerPage=${numPerPage}&orderField=${orderField}&orderDirection=${orderDirection}&divid=supplierStaffInfo" class="btnEdit">编辑</a>
							
						</td>	
					</tr>
					</c:if>	
				</c:forEach>
			</c:if>		
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="4">4</option>
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}"></div>

	</div>
</div>
