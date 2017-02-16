<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"page/business/admin/combo/userInfo.action";
	String modify = basePath+"page/business/admin/combo/modifyInfo.action";
	String del = basePath+"page/business/admin/combo/delInfo.action";
	
	request.setAttribute("action",action);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="orderNo" value="${orderNo}" />
		<input type="hidden" name="createStartTime" value="${createStartTime}" />
		<input type="hidden" name="createEndTime" value="${createEndTime}" />
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />		
		<input type="hidden" name="staff.staffName" value="${staff.staffName}" />
</form>
<script type="text/javascript">
	function send(id){
		$.ajax({
			url:'<%= basePath%>page/business/admin/combo/send.action?comboVo.combo.id='+id,
			type:'POST',
			data:{},
			dataType:'JSON',
			success:function(data){
				navTabSearch(data);
			}
			
		});
	}
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户名：
					<input type="text" name="comboVo.combo.comboName" value="${comboVo.combo.comboName}"/>
				</td>
				<td>
					套餐名：
					<input type="text" name="comboVo.combo.comboName" value="${comboVo.combo.comboName}"/>
				</td>
				
				<%-- <td>
					<label>分配状态：</label>
					<select class="combox" name="deliveryStatus">
					<c:if test="${allocationStatusList!=null}">
						<option value="" selected>选择</option>
						<c:forEach items="${allocationStatusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==deliveryStatus}">
									<option value=${loop.id} selected>${loop.statusName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusName}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
					</select>
				</td> --%>
			</tr>
		</table>
		<table>
			<tr>
				<td>
					起止日期（起始结束时间都要选）：从
					<input type="text" name="createStartTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${createStartTime}" class="date" readonly="true" />
					到
					<input type="text" name="createEndTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${createEndTime}" class="date" readonly="true" />
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
			<li>
			<a class="add" href="${modify}?pageType=3" target="navTab" title="新增"><span>新增用户套餐</span></a>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="225">
		<thead>
			<tr>
				<th width="45">用户名</th>
				<th width="40">套餐名</th>
				<th width="40">套餐金额</th>
				<th width="40">已配送金额</th>
				<th width="100">用户套餐状态</th>
				<th width="100">用户套餐开始时间</th>
				<th width="100">用户套餐结束时间</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${comboUserVOList!=null}">
				<c:forEach items="${comboUserVOList}" var="loop">
					<tr target="sid_orderRecordId" rel="${loop.comboUser.id}">						
						<td>${loop.frontUser.userName}</td>
						<td>${loop.combo.comboName}</td>	
						<td>${loop.combo.comboPrice}</td>
						<td>${loop.comboUser.usingMoney}</td>
						<td>${loop.comboUser.comboStartTime}</td>
						<td>${loop.comboUser.comboEndTime}</td>
						<td>					
							<a title="查看订单详情" target="navTab" href="${modify}?comboVo.combo.id=${loop.id}&pageType=1" class="btnLook">查看</a>
							<a title="查看商品详情" target="navTab" href="${modify}?comboVo.combo.id=${loop.id}&pageType=1" class="btnLook">查看</a>
							<a title="查看以后的配送计划" target="navTab" href="${modify}?comboVo.combo.id=${loop.id}&pageType=1" class="btnLook">查看</a>
							<a title="计划外调整订单" target="navTab" href="${modify}?comboVo.combo.id=${loop.id}&pageType=1" class="btnLook">查看</a>
							<%-- <a title="编辑" target="navTab" href="${modify}?comboVo.combo.id=${loop.id}&pageType=2" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="${del}?comboVo.combo.id=${loop.id}" class="btnDel" title="确认删除？" >删除</a> --%>
							<c:if test="${loop.comboStatus == 0}">
								<a title="禁用" href="javascript:send(${loop.id})" class="btnSelect" >禁用</a>
							</c:if>
							<c:if test="${loop.comboStatus == 1}">
								<a title="启用" href="javascript:send(${loop.id})" class="btnSelect" >启用</a>
							</c:if>
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
