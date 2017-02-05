<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/business/admin/allocation/modify.action";
	String search = basePath+"page/business/admin/allocation/toAllocation.action?pageType=3";
	String outplanLookup = basePath+"/page/wx/outplan/lookup.action";
	String goodsLookup = basePath+"page/business/admin/commodity/goods/lookup.action";
	String provinceLookup = basePath + "page/wx/admin/region/province/lookup.action";
	String cityLookup = basePath + "page/wx/admin/region/city/lookup.action";
	String areaLookup = basePath + "page/wx/admin/region/area/lookup.action";
	request.setAttribute("action",action);
	request.setAttribute("search",search);
	request.setAttribute("outplanLookup",outplanLookup);
	request.setAttribute("goodsLookup",goodsLookup);
	request.setAttribute("provinceLookup",provinceLookup);
	request.setAttribute("cityLookup",cityLookup);
	request.setAttribute("areaLookup",areaLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
function saveAllocation(){
	//form 表单参数验证
	if(!$("#content").valid()){
		return false;
	}
	var flag=true;
	//查出的产品必须都要分配 分配数量验证
	$("#goodsTable").find("tr").each(function(i,one){
		var goodsId = $(one).find("input[name$='businessGoods.id']").val();
		var goodsName = $(one).find("input[name$='businessGoods.goodsName']").val();
		var goodsNumber = $(one).find("input[name$='businessOrderGoodsRelation.goodsCount']").val();
		//alert(goodsId+"叫"+goodsName+"有"+goodsNumber);
		var count=0;
		$("#farmeTable").find("tr").each(function(j,two){
			var farmegoodsId = $(two).find("input[name$='].id']").val();
			if(goodsId==farmegoodsId){
				var farmegoodscount = parseInt($(two).find("input[name$='].goodsCount']").val());
				count+=farmegoodscount;
			}
		});
		if(goodsNumber!=count){
			alertMsg.error(goodsName+"分配数目不对！");
			flag=false;
		}
	});
	$("#createStartTime1").val($("#createStartTime").val());
	$("#createEndTime1").val($("#createEndTime").val());
	if(flag){
		$.ajax({
			type: 'POST',
			url:$("#content").attr("action"),
			data:$("#content").serializeArray(),
			dataType:"json",
			cache: false,
			success: function(data){
				
				var pageType = '${pageType}';
				if(pageType==2){
					alertMsg.info("修改成功");
					navTabAjaxDone(data);
				}else{
					alertMsg.info("保存成功");
					navTabSearch($("#search")[0]);
				}
			}
		});
	}
}
$(function(){
	var pageType = '${pageType}';
	if(pageType==1){//查看页面 禁用所有input 和button
		$("#search").find("input").each(function(i,one){
			$(one).attr("readonly","readonly");
			$(one).attr("disabled","disabled");
		})
		$("#search").find("button").each(function(i,one){
			$(one).attr("disabled","disabled");
		})
		$("#content").find("input").each(function(i,one){
			$(one).attr("readonly","readonly");
		})
		$("#content").find("button").each(function(i,one){
			$(one).attr("disabled","disabled");
		})
		$("#itemDetail1").removeClass("itemDetail");
		$("#itemDetail2").removeClass("itemDetail");
	}else if(pageType==2){
		$("#search").find("input").each(function(i,one){
			$(one).attr("readonly","readonly");
			$(one).attr("disabled","disabled");
		})
		$("#search").find("button").each(function(i,one){
			$(one).attr("disabled","disabled");
		})
		$("#itemDetail1").find("input").each(function(i,one){
			$(one).attr("readonly","readonly");
		})
		$("#itemDetail1").find("button").each(function(i,one){
			$(one).attr("disabled","disabled");
		})
		$("#itemDetail1").removeClass("itemDetail");
		$("#itemDetail2").addClass("itemDetail");
	}else if(pageType==3){
		$("#itemDetail1").find("input").each(function(i,one){
			$(one).attr("readonly","readonly");
		})
		$("#itemDetail1").removeClass("itemDetail");
		$("#itemDetail2").addClass("itemDetail");
	}
})
</script>
<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="${search}" method="post" id="search">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					订单ID：
					<input type="text" name="orderRecordId" value="${orderRecordId}"/>
				</td>
				<td>
					订单编号：
					<input type="text" name="orderNo" value="${orderNo}"/>
				</td>
				<td>
					用户手机号：
					<input type="text" name="clientUserMobile" value="${clientUserMobile}"/>
				</td>
				<td>
					收货人手机号：
					<input type="text" name="userRealMobile" value="${userRealMobile}"/>
				</td>
				<td>
					收货人名称：
					<input type="text" name="userRealName" value="${userRealName}"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>支付渠道：</label>
					<select class="combox" name="paymentProvider">
					<c:if test="${paymentProviderList!=null}">
						<option value="" selected>选择</option>
						<c:forEach items="${paymentProviderList}" var="loop">
							<c:choose>
								<c:when test="${loop.id==paymentProvider}">
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
		<table>
			<tr>
				<td>
					订单日期（起始结束时间都要选）：从
					<input type="text" id="createStartTime" name="createStartTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${createStartTime}" class="date" readonly="true" />
					到
					<input type="text" id="createEndTime"  name="createEndTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${createEndTime}" class="date" readonly="true" />
				</td>
			</tr>
		</table>
		<c:if test="${pageType==3}">
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
				</ul>
			</div>
		</c:if>
		
	</div>
	</form>
</div>
<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="" id="content">
	<input type="hidden" value="${allocationId}" name="allocationId">
		<div class="pageFormContent" layoutH="165" style="height: 320px">
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li class="selected"><a href="javascript:void(0)"><span>商品表管理</span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div>
						<table class="list nowrap itemDetail" addButton="添加商品" width="100%" id="itemDetail1" >
							<thead>
								<tr>
									<th type="input" name="allocationList[#index#].businessGoods.goodsName" readonly>选择商品</th>
									<th type="input" name="allocationList[#index#].businessOrderGoodsRelation.goodsCount" size="20" fieldClass="required number" >数量</th>
									<th type="text" name="allocationList[#index#].businessGoods.goodsOriginalPrice" readonly>销售价</th>
									<th type="text" name="allocationList[#index#].businessGoods.goodsPurchasePrice" readonly>进货价</th>						 
									<th type="del"  width="10%">操作</th>
								</tr>
							</thead>
							<tbody id="goodsTable">
							<c:if test="${allocationList!=null}">
									<c:forEach items="${allocationList}" var="loop" varStatus="status">
									<tr class="unitBox">
											<td>
												<input type="hidden" value="${loop.businessGoods.id}" name="allocationList[${status.index}].businessGoods.id">
												<input type="hidden" value="${loop.businessOrder}" name="allocationList[${status.index}].businessOrder">
												<input type="text" value="${loop.businessGoods.goodsName}" name="allocationList[${status.index}].businessGoods.goodsName">
												<%-- <a class="btnLook" title="查找带回" lookupgroup="goodsList[${loop.businessGoods.id}].businessGoodsVO.businessGoods" href="${goodsLookup}">查找带回</a> --%>
											</td>
											<td>
												<input id="" class="required number" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													class="required number"
													value="${loop.businessGoodsCount}" name="allocationList[${status.index}].businessOrderGoodsRelation.goodsCount">
											</td>
											<td>
											<c:choose>
												<c:when test="${loop.businessOrderGoodsRelation.currentPrice=='0.0'}">
													<input id="" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													value="0.0" name="allocationList[${status.index}].businessGoods.goodsOriginalPrice">
												</c:when>
												<c:otherwise>
													<input id="" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													value="${loop.businessGoods.goodsOriginalPrice}" name="allocationList[${status.index}].businessGoods.goodsOriginalPrice">
												</c:otherwise>
											</c:choose>
											</td>	
											<td>
											<c:choose>
												<c:when test="${loop.businessOrderGoodsRelation.currentPrice=='0.0'}">
													<input id="" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													value="0.0" name="allocationList[${status.index}].businessGoods.goodsPurchasePrice">
												</c:when>
												<c:otherwise>
													<input id="" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													value="${loop.businessGoods.goodsPurchasePrice}" name="allocationList[${status.index}].businessGoods.goodsPurchasePrice">
												</c:otherwise>
											</c:choose>
											</td>	
											
											<td>
												<a class="btnDel " href="javascript:void(0)">删除</a>
											</td>
										</tr> 
									</c:forEach>
							</c:if>
							</tbody>
						</table>
					</div>							
				</div>
				<div class="tabsFooter">
					<div class="tabsFooterContent"></div>
				</div>
			</div>
			
			<div class="divider"></div>
			<h3 class="contentTitle">分配列表</h3>




			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li class="selected"><a href="javascript:void(0)"><span>分配管理</span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div>
						<table class="list nowrap " addButton="添加分配条目" width="100%" id="itemDetail2">
							<thead>
								<tr>
								    <th type="text" name="items[#index#].outPlanId" size="4" readonly="readonly" fieldClass="required digits">序号</th>
									<th type="text" name="items[#index#].goodsName" size="20" readonly="readonly">商品名称</th>
									<th type="lookup" name="items[#index#].userName" lookupGroup="items[#index#]" lookupUrl="${outplanLookup}?goodsIdstr=${goodsIdstr}" size="20" readonly="readonly">农户</th>
									<th type="text" name="items[#index#].validAmount" size="20" readonly="readonly">可操作数量</th>
									<th type="input" name="items[#index#].goodsCount" size="20" fieldClass="required number">分配数量</th>			 
									<th type="del"  width="10%">操作</th>
								</tr>
							</thead>
							<tbody id="farmeTable">
							<c:if test="${farmerOrderList!=null}">
									<c:forEach items="${farmerOrderList}" var="loop" varStatus="status">
									<tr class="unitBox">
									
									        <td>
									        <input name="items[${status.index}].outPlanId" type="text" value="${loop.farmerOrder.outPlanId}">
									        </td>
											<td>
											    <input name="items[${status.index}].goodsName" type="text" value="${loop.businessGoods.goodsName}" >
											</td>
											<td>
												<input type="hidden" name="items[${status.index}].id" value="${loop.businessGoods.id}">
												<input name="items[${status.index}].userName" type="text" value="${loop.farmer.userName}" >
												<c:if test="${pageType==2}">
													<a class="btnLook" title="查找带回" lookupGroup="items[#index#]" href="${outplanLookup}?goodsIdstr=${goodsIdstr}">查找带回</a>
												</c:if>
											</td>
											<td>
											 	<input name="items[${status.index}].validAmount" type="text" value="${loop.outPlan.validAmount}" >
											</td>	
											<td>
												<input name="items[${status.index}].goodsCount" type="text" value="${loop.farmerOrder.goodsCount}" >
											</td>	
											
											<td>
												<a class="btnDel " href="javascript:void(0)">删除</a>
											</td>
											
										</tr> 
									</c:forEach>
							</c:if>
							</tbody>
						</table>
					</div>							
				</div>
				<div class="tabsFooter">
					<div class="tabsFooterContent"></div>
				</div>
			</div>
		</div>
		<input type="hidden" id="createStartTime1" name="createStartTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${createStartTime}" class="date" readonly="true" />
		<input type="hidden" id="createEndTime1" name="createEndTime" yearstart="-80" yearend="1"  dateFmt="yyyy-MM-dd HH:mm:ss" value="${createEndTime}" class="date" readonly="true" />
			<c:if test="${pageType!=1}">
				<div class="formBar">
					<ul>				
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="saveAllocation()">保存</button></div></div></li>
						<li>
							<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
						</li>
					</ul>
				</div>
			</c:if>
	</form>

</div>