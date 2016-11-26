<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/wx/farmer/info.action";
	
	request.setAttribute("action",action);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function generate(id){
		$.ajax({
			url:'<%= basePath%>page/farmerQrCode/generateQr.action',
			type:'POST',
			data:{farmerId:id},
			dataType:'JSON',
			success:function(data){
				if(data.index==0)
					{
						alert("成功生成"+data.fenleiL+"张，请到二维码管理处查看");
					}else{
						alert("共"+data.fenleiL+"张，失败"+data.index+"张，原因：重复创建或系统异常");
					}
			}
			
		});
	}
</script>
<form id="pagerForm" method="post" action="${action}">			
		
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
					用户名称
					<input type="text" name="query.userName" value="${query.userName }"/>
				</td>	
				<td>
					用户ID
					<input type="text" name="query.userId" value="${ query.userId}"/>
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
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>							
				<th width="25"">ID</th>
				<th width="45">用户ID</th>
				<th width="45">用户名称</th>
				<th width="45">审核状态</th>
				<th width="45">用户头像</th>
				<th width="45">操作</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${farmerList!=null}">
				<c:forEach items="${farmerList}" var="loop">
					<tr target="sid_menuid" rel="${loop.id}">								
						<td>${loop.id}</td>
						<td>${loop.userId}</td>
						<td>${loop.userName}</td>
						<td>
							<c:if test="${loop.status=='0'||loop.status==null}">未审核</c:if>
							<c:if test="${loop.status=='1'}">已审核</c:if>
						</td>
						<td><image src="${loop.headPortrait}64" /></td>
						<td><a title="商品" target="dialog" href="page/wx/farmer/anthinfo.action?farmerId=${loop.id}" class="btnAuth">商品</a>
						<a title="生成二维码" href="javascript:void(0)" class="btnSelect" onclick="generate('${loop.userId}')">生成二维码</a></td>
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
