<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"message/msg/info.action";	
	String staffLookup = basePath + "page/staff/lookup.action";
	String orgLookup = basePath + "page/org/lookup.action";
	String viewLog = basePath+"hrkpi/table/viewLog.action";
	String sendMsg = basePath+"message/msg/sendMsgInfo.action";
		
	request.setAttribute("action",action);
	request.setAttribute("staffLookup",staffLookup);
	request.setAttribute("orgLookup",orgLookup);
	request.setAttribute("viewLog", viewLog);
	request.setAttribute("sendMsg", sendMsg);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="query.sendOrg.id" value="${query.sendOrg.id}" />
		<input type="hidden" name="query.recOrg.id" value="${query.recOrg.id}" />
		<input type="hidden" name="query.receiver.id" value="${query.receiver.id}" />
		<input type="hidden" name="query.sender.id" value="${query.sender.id}" />		
		
		<input type="hidden" name="query.sendOrg.organizationName" value="${query.sendOrg.organizationName}" />
		<input type="hidden" name="query.recOrg.organizationName" value="${query.recOrg.organizationName}" />
		<input type="hidden" name="query.receiver.staffName" value="${query.receiver.staffName}" />
		<input type="hidden" name="query.sender.staffName" value="${query.sender.staffName}" />	
				
		<input type="hidden" name="totalCount" value="${totalCount}" />		
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${action}" method="post">
	<div class="searchBar">	
		<table class="searchContent">
			<tr>
				<td>
					发送消息部门:					
				</td>
				<td>					
					<dd>			
					<input name="query.sendOrg.id" value="${query.sendOrg.id}" type="hidden"/>
					<input name="query.sendOrg.organizationName" value="${query.sendOrg.organizationName}" readonly="readonly"  type="text"/>
					
					</dd>	
				</td>
				<td>
					<a class="btnLook" href="${orgLookup}" lookupGroup="query.sendOrg">查找带回</a>
				</td>
				<td>
					接收消息部门:					
				</td>
				<td>					
					<dd>			
					<input id="inputOrg1" name="query.recOrg.id" value="${query.recOrg.id}" type="hidden"/>
					<input name="query.recOrg.organizationName" value="${query.recOrg.organizationName}" readonly="readonly"  type="text"/>
					
					</dd>	
				</td>
				<td>
					<a class="btnLook" href="${orgLookup}" lookupGroup="query.recOrg">查找带回</a>
				</td>
				
				
			</tr>
			<tr>
				<td>
					发送人:					
				</td>
				<td>					
					<dd>			
					<input id="inputOrg1" name="query.sender.id" value="${query.sender.id}" type="hidden"/>
					<input name="query.sender.staffName" value="${query.sender.staffName}" readonly="readonly"  type="text"/>
					
					</dd>	
				</td>
				<td>
					<a class="btnLook" href="${staffLookup}" lookupGroup="query.sender">查找带回</a>
				</td>
				<td>
					收信人:					
				</td>
				<td>					
					<dd>			
					<input id="inputOrg1" name="query.receiver.id" value="${query.receiver.id}" type="hidden"/>
					<input name="query.receiver.staffName" value="${query.receiver.staffName}" readonly="readonly"  type="text"/>					
					</dd>	
				</td>
				<td>
					<a class="btnLook" href="${staffLookup}" lookupGroup="query.receiver">查找带回</a>
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
			<li><a class="add" href="${sendMsg}" target="navTab" rel="sendMsgInfo"><span>发送消息</span></a></li>		
			<li class="line">line</li>			
		</ul>
	</div>
	<table class="table" width="1600px" layoutH="161">
		<thead>
			<tr>							
				<th width="15" orderField="id" class="${idsort}">编号</th>
				<th width="45">发送人</th>
				<th width="55">发送部门</th>	
				<th width="65">接收人</th>			
				<th width="65">接收部门</th>
				<th width="85">发送时间</th>
				<th width="155">发送标题</th>
				<th width="155">发送内容</th>
				<th width="65">模块名称</th>
				<th width="45">消息类型</th>
				<th width="15">接收邮箱</th>
				<th width="35">接收手机号</th>				
			</tr>
		</thead>
		<tbody>		
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_queryid" rel="${loop.id}">	
						<td>${loop.id}</td>						
						<td>${loop.senderName}</td>
						<td>${loop.sendOrgName}</td>
						<td>${loop.receiverName}</td>
						<td>${loop.recOrgName}</td>																
						<td><fmt:formatDate value="${loop.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>		
						<td>${loop.title}</td>							
						<td>${loop.content}</td>	
						<td>${loop.modelName}</td>
						<td>${loop.msgTypeName}</td>		
						<td>${loop.recMail}</td>		
						<td>${loop.recMobile}</td>							
					</tr>				
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
