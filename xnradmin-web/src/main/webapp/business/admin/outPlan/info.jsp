<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String action = basePath+"page/wx/outplan/info.action";
	String add = basePath+"page/wx/outplan/addInfo.action";
	String modify = basePath+"page/wx/outplan/modifyInfo.action";
	String del = basePath+"page/wx/outplan/del.action";
	String outPlanlookup = basePath+"page/wx/outplan/lookup.action";
	
	request.setAttribute("action",action);
	request.setAttribute("add",add);
	request.setAttribute("modify",modify);
	request.setAttribute("del",del);
	request.setAttribute("outPlanlookup",outPlanlookup);
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function examine(id){
		var s = $("#ex"+id).val();
		if(s==1){
			alert("请不要重复审核");
			return;
		}
		if(confirm("确定通过审核")){
			 $.ajax({
	             type: "post",
	             url: "page/wx/outplan/examine.action",
	             data: {examineId:id},
	             dataType: "json",
	             success: function(data){
	            	 if(data.examineStatus==true){
	            		 $("#examine"+id).html("<div>通过</div>");
	            		 $("#ex"+id).val(1);
	            	 }else{
	            		 alert("系统异常，审核失败");
	            	 }
	             }
	         });
		}
	}
</script>
<form id="pagerForm" method="post" action="${action}">			

		<%-- <input type="hidden" name="query.menu.menuName" value="${query.menu.menuName}" />
		<input type="hidden" name="query.menu.typeid" value="${query.menu.typeid}" />	 --%>
		
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
					ID（可模糊查询）
					<input type="text" name="query.outPlan.id" value="${ query.outPlan.id}"/>
				</td>	
				<td>
					农户
					<input type="text" name="query.farmer.userName" value="${ query.farmer.userName}"/>
				</td>	
				<td>
					商品
					<input type="text" name="query.businessGood.goodsName" value="${ query.businessGood.goodsName}"/>
				</td>	
				<td>
					开始时间
					<input type="text" name="query.outPlan.startTime" yearstart="-80" yearend="1" class="date" dateFmt="yyyy-MM-dd" value="<fmt:formatDate value="${ query.outPlan.startTime}" pattern="yyyy-MM-dd"/>"/>
				</td>	
				<td>
					结束时间
					<input type="text" name="query.outPlan.endTime" yearstart="-80" yearend="1" class="date" dateFmt="yyyy-MM-dd"  value="<fmt:formatDate value="${ query.outPlan.endTime}" pattern="yyyy-MM-dd"/>"/>
				</td>
				<td>
					审核状态
					<select name="query.outPlan.examine">
						<option>请选择</option>
						<option value="1" <c:if test="${ query.outPlan.examine==1}">selected="selected"</c:if>>通过</option>
						<option value="0" <c:if test="${ query.outPlan.examine==0}">selected="selected"</c:if>>未通过</option>
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
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>							
				<th width="25"">ID</th>
				<th width="45">农户</th>
				<th width="45">商品</th>
				<th width="45">开始时间</th>
				<th width="45">结束时间</th>
				<th width="35">计划产量</th>
				<th width="35">单位</th>
				<th width="35">审核状态</th>
				<th width="30">审核</th>
			</tr>
		</thead>
		<tbody>		
			<c:if test="${voList!=null}">
				<c:forEach items="${voList}" var="loop">
					<tr target="sid_menuid" rel="${loop.outPlan.id}">	
						<td>${loop.outPlan.id}</td>							
						<td>${loop.farmer.userName}</td>
						<td>${loop.businessGood.goodsName}</td>						
						<td><fmt:formatDate value="${loop.outPlan.startTime}" pattern="yyyy-MM-dd " ></fmt:formatDate></td>
						<td><fmt:formatDate value="${loop.outPlan.endTime}" pattern="yyyy-MM-dd " ></fmt:formatDate></td>	
						<td>${loop.outPlan.output}</td>	
						<td>${loop.businessWeight.weightName}</td>
						<input id="ex${loop.outPlan.id}" type="hidden" value="${loop.outPlan.examine}">
						<td id="examine${loop.outPlan.id}">
						<c:if test="${ loop.outPlan.examine==1}">通过</c:if>
						<c:if test="${ loop.outPlan.examine==0}">未通过</c:if>
						</td>
						<td>	
							 <a  href="javascript:void(0)" class="btnSelect" onclick="examine(${loop.outPlan.id})">通过</a>
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
