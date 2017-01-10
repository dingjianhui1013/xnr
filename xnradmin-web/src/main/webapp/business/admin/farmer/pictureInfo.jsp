<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://"  
            + request.getServerName() + ":" + request.getServerPort()  
            + path + "/"; 
	String action = basePath+"page/wx/farmer/pictureInfo.action";
	
	request.setAttribute("action",action);
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${action}">			
		
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="numPerPage" value="${numPerPage}" />
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>
<script type="text/javascript">
	function generate(id){
		if(confirm("确定删除吗？")){
			$.ajax({
				url:'<%= basePath%>/page/wx/farmer/dealPicture.action',
				type:'POST',
				data:{delId:id},
				dataType:'JSON',
				success:function(data){
					/* if(data.status==true){
	            		 alert("删除成功")
	            	 }else{
	            		 alert("删除失败");
	            	 } */
	            	 
				}
			});
			window.location='<%= basePath%>/page/wx/farmer/pictureInfo.action';
		}
	}
</script>
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
				<th width="45">用户名称</th>
				<th width="45">图片</th>
				<th width="45">操作</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${FarmerImageVOs!=null}">
				<c:forEach items="${FarmerImageVOs}" var="loop">
					<tr target="sid_menuid">								
						<td>${loop.farmerImage.id}</td>
						<td>${loop.farmer.userName}</td>
						<td>
							<image src="${basePath}${loop.farmerImage.url}" />					
						</td>
						<td>
						<a title="删除" href="javascript:void(0)" class="btnDel" onclick="generate('${loop.farmerImage.id}')">删除</a>
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
