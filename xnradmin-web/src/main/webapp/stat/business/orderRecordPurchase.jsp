<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%
  String path = request.getContentType();
  String basePath = request.getScheme()+"://"
		  + request.getServerName() + ":" + request.getServerPort()
		  + path + "/";
  String action = basePath+"/page/stat/business/orderRecordPurchase.action";
  request.setAttribute("action", action);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form id="pagerForm" method="post" action="action">
	<input type="hidden" name="clientUserName" value="${clientUserName}"/>
	<input type="hidden" name="clientUserMobile" value="${clientUserMobile}"/>
	<input type="hidden" name="registrationCreatetime" value="${registrationCreatetime}"/>
	<input type="hidden" name="registrationEndTim" value="${registrationEndTim}"/>
	<input type="hidden" name="createTime" value="${createTime}"/>
	<input type="hidden" name="endTime" value="${endTime}"/>
	<input type="hidden" name='alone' value="${alone}"/>
	<input type="hidden" name="allPrice" value="${allPrice}"/>
	<input type="hidden" name="averagePrice" value="${averagePrice}"/>
	<input type="hidden" name="numPerPage" value="${numPerPage}"/>
	<input type="hidden" name="pageNum" value="${pageNum}"/>
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="" method="post">
	<div class="searchBar">	
		<table class="searchContent">
			<tr>
				<td>
					 商户名
					<input type="text" name="clientUserName" value="${clientUserName}"/>
				</td>
				<td>
					商户电话
					<input type="text" name="clientUserMobile" value="${clientUserMobile}"/>
				</td>
			</tr>
		</table>
		<table class="searchContent">
			<tr>
				<td>
					注册日期（起始结束时间都要选）：从
					<input type="text" name="registrationCreatetime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd" value="${registrationCreatetime}" class="date" readonly="true" />
					到
					<input type="text" name="registrationEndTim" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd" value="${registrationEndTim}" class="date" readonly="true" />
				</td>
			</tr>
		</table>
		<table class="searchContent">
			<tr>
				<td>
					下单日期（起始结束时间都要选）：从
					<input type="text" name="createTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd" value="${createTime}" class="date" readonly="true" />
					到
					<input type="text" name="endTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd" value="${endTime}" class="date" readonly="true" />
				</td>
			</tr>
		</table>
	</div>
		<div class="subBar">
		<table align="right">
			<tr>
				<td>
				<ul>
					<li ><div class="buttonActive"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
				</ul>
				</td>
			</tr>
		</table>
		</div>
</form>
</div>
<div class="pageContent">
	<table class="table" windth="100%" lanth="336" layoutH="160">
		<thead>
			<tr>
				<th width="200" align="center"><div style="font-size:18px">统计日期</div></th>
				<th width="200" align="center"><div style="font-size:18px">用户数</div></th>
				<th width="200" align="center"><div style="font-size:18px">订单总价</div></th>
				<th width="200" align="center"><div style="font-size:18px">平均客单价</div></th>
				<th width="200" align="center"><div style="font-size:18px">下单数</div></th>
				<th width="200" align="center"><div style="font-size:18px">用户平均下单数</div></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${contentList!=null}">
				<c:forEach items="${contentList}" var="loop">
					<tr>
						<td width="200" align="center"><div style="font-size:18px">${loop[0]}</div></td>
						<td width="200" align="center"><div style="font-size:18px">${loop[1]}</div></td>
						<td width="200" align="center"><div style="font-size:18px">${loop[2]}(元)</div></td>
						<td width="200" align="center"><div style="font-size:18px">${loop[3]}(元)</div></td>
						<td width="200" align="center"><div style="font-size:18px">${loop[4]}</div></td>
						<td width="200" align="center"><div style="font-size:18px">${loop[5]}</div></td>
					</tr>
				</c:forEach>
			</c:if>	
		</tbody>
	</table>
</div>
<!-- 
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
 -->