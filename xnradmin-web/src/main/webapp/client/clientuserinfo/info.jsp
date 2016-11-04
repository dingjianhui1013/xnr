<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/client/clientuserinfo/info.action";
	String add = basePath+"page/client/clientuserinfo/addinfo.action";
	String modify = basePath+"page/client/clientuserinfo/modifyinfo.action";
	String del = basePath+"page/client/clientuserinfo/del.action";
	String addOrder = basePath+"page/wx/admin/orderrecord/addinfo.action";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
	request.setAttribute("addOrder",addOrder);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="status" value="${status}" />
		<input type="hidden" name="type" value="${type}" />
		<input type="hidden" name="provinceName" value="${provinceName}" />
		<input type="hidden" name="cityName" value="${cityName}" />
		<input type="hidden" name="areaName" value="${areaName}" />
		<input type="hidden" name="mobile" value="${mobile}" />
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
					手机号码：
					<input type="text" name="mobile" value="${mobile}" type="text"/>
				</td>
				<td>	
					所属省份：
					<input class="textInput" name="provinceName" value="${provinceName}" type="text">
				</td>
				<td>
					所属城市：
					<input class="textInput" name="cityName" value="${cityName}" type="text">
				</td>
			</tr>
			<tr>
				<td>
					所属区县：
					<input class="textInput" name="areaName" value="${areaName}" type="text">
				</td>
				<td>
					<label>用户状态：</label>
					<select class="combox" name="status">
					<c:if test="${statusList!=null}">
						<option value="" selected>选择</option>
						<c:forEach items="${statusList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==status}">
									<option value=${loop.id} selected>${loop.statusName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusName}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
					</select>
				</td>
				<td>
					<label>用户类型：</label>
					<select class="combox" name="type">
					<c:if test="${typeList!=null}">
						<option value="" selected>选择</option>
						<c:forEach items="${typeList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==type}">
									<option value=${loop.id} selected>${loop.statusName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusName}</option>
   								</c:otherwise>
							</c:choose>
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
			<li><a class="add" href="${add}" target="dialog" rel="clientUserInfo_add"><span>添加用户</span></a></li>				
			<li><a class="edit" href="${modify}?clientuserinfoId={sid_clientUserInfoId}" target="navTab" warn="请选择一个记录"><span>修改</span></a></li>
			<li class="line">line</li>			
			<li><a class="delete" href="${del}?clientuserinfoId={sid_clientUserInfoId}&del=1" target="ajaxTodo" title="删除某个品牌"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="158">
		<thead>
			<tr>
				<th width="100">用户昵称</th>
				<th width="100">手机号码</th>
				<th width="50">用户状态</th>
				<th width="70">用户类型</th>
				<th width="100">注册时间</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_clientUserInfoId" rel="${loop.clientUserInfoId}">						
						<td>${loop.nickName}</td>					
						<td>${loop.getMobile()}</td>
						<td>${loop.statusName}</td>
						<td>${loop.typeName}</td>
						<td>${loop.clientUserInfoCreateTime}</td>						
						<td>
							<a title="增加订单" target="navTab" href="${addOrder}?clientUserId=${loop.clientUserInfoId}&del=1" class="btnAdd">增加订单</a>
							<a title="编辑" target="navTab" href="${modify}?clientuserinfoId=${loop.clientUserInfoId}" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="${del}?clientuserinfoId=${loop.clientUserInfoId}&del=1" class="btnDel">删除</a>
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
