<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	String action = basePath+"frontUserManager/info.action";
	String add = basePath+"frontUserManager/addinfo.action";
	String modify = basePath+"frontUserManager/modifyinfo.action";
	String del = basePath+"frontUserManager/del.action";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function typeOk(id){
		var s = $("#ty"+id).val();
		if(s!=0){
			alert("已经审核过，无需重复审核");
			return;
		}
		if(confirm("确定通过审核")){
			 $.ajax({
	             type: "post",
	             url: "frontUserManager/type.action",
	             data: {typeId:id},
	             dataType: "json",
	             success: function(data){
 	            	 if(data.typeStatus==true){
 	            		 $("#type"+id).html("<div>通过</div>");
 	            		 $("#ty"+id).val(1);
 	            	 }else{
 	            		 alert("系统异常，审核失败");
 	            	 }
	             }
	         });
		}
	}
	function typeNo(id){
		var s = $("#ty"+id).val();
		if(s!=0){
			alert("已经审核过，无需重复审核");
			return;
		}
		if(confirm("确定拒绝通过")){
			 $.ajax({
	             type: "post",
	             url: "frontUserManager/typeNo.action",
	             data: {typeNoId:id},
	             dataType: "json",
	             success: function(data){
 	            	 if(data.typeStatus==true){
 	            		 $("#type"+id).html("<div>拒绝</div>");
 	            		 $("#ty"+id).val(1);
 	            	 }else{
 	            		 alert("系统异常，审核失败");
 	            	 }
	             }
	         });
		}
	}
	function reset(id){
		if(confirm("确定重置密码")){
			 $.ajax({
	             type: "post",
	             url: "frontUserManager/reset.action",
	             data: {resetId:id},
	             dataType: "json",
	             success: function(data){
 	            	 if(data.resetStatus==true){
 	            		alert("重置成功"); 
 	            	 }else{
	            		 alert("系统异常，重置失败");
	            	 }
	             }
	         });
		}
	}
</script>
<form id="pagerForm" method="post" action="${action}">
		<input type="hidden" name="query.userName" value="${query.userName}" />
		<input type="hidden" name="query.phone" value="${query.phone}" />
		<input type="hidden" name="query.email" value="${query.email}" />
		<input type="hidden" name="query.type" value="${query.type}" />
		
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${action}" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户名：
					<input type="text" name="query.userName" value="${query.userName}"/>
				</td>
				<td>
					手机号：
					<input type="text" name="query.phone" value="${query.phone}"/>
				</td>
				<td>
					邮箱：
					<input type="text" name="query.email" value="${query.email}"/>
				</td>
				<td>
					审核状态
					<select name="query.type">
						<option>请选择</option>
						<option value="0" <c:if test="${ query.type==0}">selected="selected"</c:if>>未审核</option>
						<option value="1" <c:if test="${ query.type==1}">selected="selected"</c:if>>通过</option>
						<option value="2" <c:if test="${ query.type==2}">selected="selected"</c:if>>拒绝</option>
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
			<li><a class="add" href="${add}" target="navTab" rel="primary_add"><span>添加用户</span></a></li>				
			<li><a class="edit" href="${modify}?updatefrontUser.id={sid_userId}" target="navTab" warn="请选择一个记录"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="${del}?updatefrontUser.id={sid_userId}&del=1" target="ajaxTodo" title="删除某个分类"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="100">用户名</th>
				<th width="100">手机号</th>
				<th width="100">邮箱</th>
				<th width="100">审核状态</th>
				<th width="70">操作</th>
				<th width="70">审核</th>
				<th width="70">重置密码</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_userId" rel="${loop.id}">					
						<td>${loop.userName}</td>
						<td>${loop.phone}</td>
						<td>${loop.email}</td>
						<input id="ty${loop.id}" type="hidden" value="${loop.type}">
						<td id="type${loop.id}">
						<c:if test="${ loop.type==0}">未审核</c:if>
						<c:if test="${ loop.type==1}">通过</c:if>
						<c:if test="${ loop.type==2}">拒绝</c:if>
						</td>
						<td>					
							<a title="编辑" target="navTab" href="${modify}?updatefrontUser.id=${loop.id}" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="${del}?updatefrontUser.id=${loop.id}&del=1" class="btnDel">删除</a>
						</td>
						<td>	
							 <a title="通过" href="javascript:void(0)" class="btnSelect" onclick="typeOk(${loop.id})">通过</a>
							 <a title="拒绝" href="javascript:void(0)" class="btnDel" onclick="typeNo(${loop.id})">拒绝</a>
						</td>	
						<td>	
							 <a title="重置密码" href="javascript:void(0)" class="btnSelect" onclick="reset(${loop.id})">重置密码</a>
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
