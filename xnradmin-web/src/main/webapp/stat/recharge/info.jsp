<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/stat/recharge/info.action";
	String rechargeDetailed = basePath+"page/stat/recharge/rechargeDetailed.action";

	request.setAttribute("action", action);
	request.setAttribute("rechargeDetailed",rechargeDetailed);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="carWashOrderRecordId" value="${carWashOrderRecordId}" />
		<input type="hidden" name="communityName" value="${communityName}"/>
		<input type="hidden" name="rechargeStartTime" value="${orderStartTime}" />
		<input type="hidden" name="rechargeEndTime" value="${orderEndTime}" />
		<input type="hidden" name="paymentStatus" value="${paymentStatus}" />
		<input type="hidden" name="operateStatus" value="${operateStatus}" />
		<input type="hidden" name="userMobile" value="${userMobile}" />
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="" method="post">
	<div class="searchBar">	
		<table class="searchContent">
			<tr>
				<td>
					充值单号
					<input type="text" name="carWashOrderRecordId" />
				</td>
				<td>
					 区域名称（可模糊）
					<input type="text" name="communityName" />
				</td>
				<td>
					充值日期（起始结束时间都要选）：从
					<input type="text" name="rechargeStartTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${orderStartTime}" class="date" readonly="true" />
					到
					<input type="text" name="rechargeEndTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${orderEndTime}" class="date" readonly="true" />
				</td>
			</tr>
		</table>
		<table class="searchContent">
			<tr>
				<td>
					支付状态：
					<select name="paymentStatus">
						<option value="" selected>选择</option>
						<option value=508>未支付</option>
						<option value=509>已支付</option>
						<option value=510>退款</option>
					</select>
				</td>
				<td>
					 用户手机号
					<input type="text" name="userMobile" />
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
		</ul>
	</div>
	<table class="table" width="100%" layoutH="158">
		<thead>
			<tr>
				<th width="100">充值单ID</th>					
				<th width="80">客户手机号</th>
				<th width="80">客户地区</th>
				<th width="50">支付状态</th>
				<th width="100">充值时间</th>
				<th width="100">支付金额</th>
				<th width="100">充值金额</th>
				<th width="100">用户余额</th>
				<th width="20">详细</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_userRechargeConsumeRecordid" rel="${loop.userRechargeConsumeRecordid}">	
						<td>${loop.userRechargeConsumeRecordid}</td>
						<td>${loop.userMobile}</td>
						<td>${loop.communityName}</td>
						<td>${loop.paymentStatusName}</td>
						<td>${loop.rechargeTime}</td>
						<td>${loop.money}</td>
						<td>${loop.addRechargeMoney}</td>
						<td>${loop.rechargeMoney}</td>
						<td>					
							<a title="详细信息" rel="rechargeInfo" target="navTab" href="${rechargeDetailed}?userRechargeConsumeRecordid=${loop.userRechargeConsumeRecordid}" class="btnEdit">编辑</a>
						</td>	
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
