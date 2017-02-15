<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/business/admin/combo/modify.action";
	String enumAction = basePath+"page/business/admin/combo/enum.action";
	String enumPriceAction = basePath+"page/business/admin/combo/enumPrice.action";
	String goodsLookup = basePath+"page/business/admin/commodity/goods/lookup.action";
	request.setAttribute("action",action);
	request.setAttribute("enumAction",enumAction);
	request.setAttribute("enumPriceAction",enumPriceAction);
	request.setAttribute("goodsLookup",goodsLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
function saveCombo(){
	//form 表单参数验证
	/* if(!$("#content").valid()){
		return false;
	} */
	//检测有没有套餐商品 有没有分配计划
	if($("#goodsTable").find("tr").length==0||$("#farmeTable").find("tr").length==0){
		alertMsg.error("套餐必须有商品和分配计划");
		return false;
	};
	
	//配送计划里面的时间验证 不能是过去时间
	var flag=true;
	var today = new Date();
	var date = new Date(today.getFullYear()+"-"+(today.getMonth()+1)+"-"+today.getDate());
	$("#farmeTable").find("input[id='comboCycleDate'").each(function(i,one){
		var date1 = new Date($(one).val());
		if(date1.getTime() < date.getTime()){
			flag=false;
	    }
	})
	if(!flag){
		return false;
	}
	
	//套餐价格与商品总价格不等提醒
    var count = parseFloat($("#goodsTotalCount").val());
	var comboCount = parseFloat($("#content").find("input[name='comboVo.combo.comboPrice']").val());
	if(count!=comboCount){
		alertMsg.confirm("套餐价格与商品总价格不符，确定要保存吗？",{
			okCall:	function(){
				$.ajax({
					type: 'POST',
					url:$("#content").attr("action"),
					data:$("#content").serializeArray(),
					dataType:"json",
					cache: false,
					success: function(data){
						alert(data);
						/* var pageType = '${pageType}';
						if(pageType==2){
							alertMsg.info("修改成功");
							navTabAjaxDone(data);
						}else{
							alertMsg.info("保存成功");
							navTabSearch($("#search")[0]);
						} */
					}
				}); 
			}
		});
	}

}
function changeComboType(obj){
	var val=$(obj).val();
	var tr=$(obj).closest("tr");
	tr.find("span").each(function(i,one){
		$(one).remove();
	})
	if(val==0){
		var comboCycleDate =$('<span id="comboCycleDateSpan"><input type="text" id="comboCycleDate" name="comboCycleDate" class="date required number textInput" dateFmt="yyyy-MM-dd" size="20"/><a class="inputDateButton" href="javascript:void(0)">选择</a></span>');
		comboCycleDate.insertAfter($(obj));
		$('#comboCycleDate').datepicker();
		
	}else if(val==1){
		var comboCycle =$("<span id='comboCycleSpan'><select id='comboCycle' name='comboCycle'>"
						+ "		<option value=''>请选择</option>"
						+ "		<option value='0'>每三天</option>"
						+ "		<option value='1'>每一周</option>"
						+ "		<option value='2'>每两周</option>"
						+ "  <select></span>");
		comboCycle.insertAfter($(obj));
		
	}else if(val==2){
		var comboCycle =$("<span id='comboCycleSpan'><select id='comboCycle' name='comboCycle' onchange='changeComboCycle(this)'>"
						+ "		<option value=''>请选择</option>"
						+ "		<option value='1'>每一周</option>"
						+ "		<option value='2'>每两周</option>"
						+ "		<option value='3'>每一月</option>"
						+ "  <select></span>");
		comboCycle.insertAfter($(obj));
	}
}
function changeComboCycle(obj){
	var val=$(obj).val();
	var tr=$(obj).closest("tr");
	tr.find("span[id='comboCycleDateSpan']").each(function(i,one){
		$(one).remove();
	})
	if(val==1){
		var comboCycleDate =$("<span id='comboCycleDateSpan'><select id='comboCycleDate' name='comboCycleDate' >"
				+ "		<option value=''>请选择</option>"
				+ "		<option value='1'>周一</option>"
				+ "		<option value='2'>周二</option>"
				+ "		<option value='3'>周三</option>"
				+ "		<option value='4'>周四</option>"
				+ "		<option value='5'>周五</option>"
				+ "  <select></span>");
		comboCycleDate.insertAfter($(obj));
	}else if(val==2){
		var comboCycleDate =$("<span id='comboCycleDateSpan'><select id='comboCycleDate' name='comboCycleDate' >"
				+ "		<option value=''>请选择</option>"
				+ "		<option value='11'>第一周周一</option>"
				+ "		<option value='12'>第一周周二</option>"
				+ "		<option value='13'>第一周周三</option>"
				+ "		<option value='14'>第一周周四</option>"
				+ "		<option value='15'>第一周周五</option>"
				+ "		<option value='21'>第二周周一</option>"
				+ "		<option value='22'>第二周周二</option>"
				+ "		<option value='23'>第二周周三</option>"
				+ "		<option value='24'>第二周周四</option>"
				+ "		<option value='25'>第二周周五</option>"
				+ "  <select></span>");
		comboCycleDate.insertAfter($(obj));
		
	}else if(val==3){
		var comboCycleDate =$("<span id='comboCycleDateSpan'><select id='comboCycleDate' name='comboCycleDate' >"
				+ "		<option value=''>请选择</option>"
				+ "		<option value='1'>月初</option>"
				+ "		<option value='2'>月中</option>"
				+ "		<option value='3'>月末</option>"
				+ "  <select></span>");
		comboCycleDate.insertAfter($(obj));
	}
}
function countPrice(obj){
	var count=0.0;
	var tbody;
	if(obj==undefined){
		tbody = $("#itemDetail1").find("tbody");
	}else{
		tbody = $(obj).closest("tbody");
	}
	$(tbody).find("tr").each(function(i,one){
		var number = parseFloat($(one).find("input[name$='comboGoods.goodsCount']").val());
		var price = parseFloat($(one).find("input[name$='businessGoods.goodsOriginalPrice']").val());
		if(!isNaN(number)&&!isNaN(price)){
			count+=price*number;
		}
	});
	$("#goodsTotalCount").val(count.toFixed(2));
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
		$("#itemDetail1").addClass("itemDetail");
		$("#itemDetail2").addClass("itemDetail");
	}
	
/* 	$("#itemDetail1").find("input").each(function(i,one){
		$(one).change(countPrice(one));
	}) */
	/* console.log($(".tabsContent").find("button").html());
	$(".tabsContent").find("button").each(function(i,one){
		alert($(one).html());
	}) */
	
})
</script>
<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="" id="content">
	
	<div class="pageFormContent" layoutH="699" style="height: 70px">
			<fieldset>
				<label>套餐名称：</label>
				<input name="comboVo.combo.comboName" type="text" size="25" class="required"/>			
			</fieldset>
			<fieldset>
				<label>套餐价格：</label>
				<input name="comboVo.combo.comboPrice" type="text" size="25" class="required number"/>			
			</fieldset>
			<fieldset>
				<label>套餐周期时间：</label>
				<select class="combox" name="comboVo.combo.comboCycleStatus">
					<c:if test="${comboCycleStatusList!=null}">
						<c:forEach items="${comboCycleStatusList}" var="loop">
						
							<c:choose>
								<c:when test="${loop.id==comboVo.combo.comboCycleStatus}">
									<option value=${loop.id} selected>${loop.statusName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value=${loop.id}>${loop.statusName}</option>
   								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</select>		
			</fieldset>
			<%-- <fieldset>
				<label>商品类型：</label>
				<input class="readonly" name="category.id" readonly="readonly" type="hidden"/>
				<input class="readonly" name="category.primaryConfigurationId" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="category.categoryName" type="text" class="required"/>		
				<a class="btnLook" href="${categoryLookup}" lookupGroup="category">查找带回</a>
			</fieldset> --%>
	</div>
	
	<input type="hidden" value="${comboVo.combo.id}" name="comboVo.combo.id">
		<h3 class="contentTitle">套餐商品列表</h3>
		<div class="pageFormContent" layoutH="245" style="height: 320px">
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li class="selected">
								<a href="javascript:void(0)"><span>商品管理</span>
							</a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div>
						<table class="list nowrap itemDetail" addButton="添加商品" width="100%" id="itemDetail1" >
							<thead>
								<tr>
									<th type="lookup" name="comboVo.comboGoodsList[#index#].businessGoods.goodsName"  lookupGroup="comboVo.comboGoodsList[#index#].businessGoods" lookupUrl="${goodsLookup}" size="20"  fieldClass="required">选择商品</th>
									<th type="enum" name="comboVo.comboGoodsList[#index#].comboGoods.goodsCount" enumUrl="${enumPriceAction}" fieldClass="required number" >数量</th>
									<th type="text" name="comboVo.comboGoodsList[#index#].businessGoods.goodsOriginalPrice"  callback="countPrice(this)">销售价</th>
									<th type="text" name="comboVo.comboGoodsList[#index#].businessGoods.goodsPurchasePrice" readonly>进货价</th>						 
									<th type="del"  width="10%">操作</th>
								</tr>					 
							</thead>
							<tbody id="goodsTable">
							<c:if test="${comboVo.comboGoodsList!=null}">
									<c:forEach items="${comboVo.comboGoodsList}" var="loop" varStatus="status">
									<tr class="unitBox">
											<td>
												<input type="hidden" value="${loop.businessGoods.id}" name="comboVo.comboGoodsList[${status.index}].businessGoods.id">
												<input type="text" value="${loop.businessGoods.goodsName}" name="comboVo.comboGoodsList[${status.index}].businessGoods.goodsName">
												<a class="btnLook" title="查找带回" lookupgroup="comboVo.comboGoodsList[${loop.businessGoods.id}].businessGoods" href="${goodsLookup}">查找带回</a>
											</td>
											<td>
												<input id="" class="required number" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													class="required number"
													value="${loop.comboGoods.goodsCount}" name="comboVo.comboGoodsList[${status.index}].comboGoods.goodsCount">
											</td>
											<td>
											<c:choose>
												<c:when test="${loop.businessGoods.goodsOriginalPrice=='0.0'}">
													<input id="" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													value="0.0" name="comboVo.comboGoodsList[${status.index}].businessGoods.goodsOriginalPrice">
												</c:when>
												<c:otherwise>
													<input id="" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													value="${loop.businessGoods.goodsOriginalPrice}" name="comboVo.comboGoodsList[${status.index}].businessGoods.goodsOriginalPrice">
												</c:otherwise>
											</c:choose>
											</td>	
											<td>
											<c:choose>
												<c:when test="${loop.businessGoods.goodsPurchasePrice=='0.0'}">
													<input id="" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													value="0.0" name="comboVo.comboGoodsList[${status.index}].businessGoods.goodsPurchasePrice">
												</c:when>
												<c:otherwise>
													<input id="" type="text" maxlength="" onafterpaste="" onkeyup="" onkeydown=""
													onkeypress="" onchange="" size="20"
													value="${loop.businessGoods.goodsPurchasePrice}" name="comboVo.comboGoodsList[${status.index}].businessGoods.goodsPurchasePrice">
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
			<div><fieldset>
				<label>商品总价：</label>
				<button type="button" onclick="countPrice()">计算总价</button>
				<input id="goodsTotalCount" name="goodsTotalCount" type="text" size="25" class="required number"/>	
			</fieldset> </div>
			<h3 class="contentTitle">套餐配送计划列表</h3>
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li class="selected"><a href="javascript:void(0)"><span>配送计划</span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div>
						<table class="list nowrap " addButton="添加配送计划" width="100%" id="itemDetail2">
							<thead>
								<tr>
									<th type="enum" name="comboVo.comboPlanList[#index#].enum" enumUrl="${enumAction}">计划时间</th>
									<th type="lookup" name="comboVo.comboPlanList[#index#].businessGoods.goodsName" lookupGroup="comboVo.comboPlanList[#index#].businessGoods" lookupUrl="${goodsLookup}" size="20" readonly="readonly">商品</th>
									<th type="text" name="comboVo.comboPlanList[#index#].comboPlan.goodsNumber" size="20" fieldClass="required number">数量</th>		 
									<th type="del"  width="10%">操作</th>
								</tr>
							</thead>
							<tbody id="farmeTable">
							<c:if test="${comboVo.comboPlanList!=null}">
									<c:forEach items="${comboVo.comboPlanList}" var="loop" varStatus="status">
									<tr class="unitBox">
									
									        <td>
									        	<select id='comboVo.comboPlanList[${status.index}].comboPlan.comboPlanType' name='comboPlanType' onchange='changeComboType(this)'>
													<option value=''>请选择</option>
													<option value='0'>固定时间</option>
													<option value='1'>固定周期</option>
													<option value='2'>固定周期固定时间</option>
												<select>"
									        </td>
											<td>
											    <input name="comboVo.comboPlanList[${status.index}].comboPlan.goodsName" type="text" value="${loop.comboPlan.goodsName}" >
											</td>
											<td>
												<input type="hidden" name="comboVo.comboPlanList[${status.index}].businessGoods.id" value="${loop.businessGoods.id}">
												<c:if test="${pageType==2}">
													<a class="btnLook" title="查找带回" lookupGroup="comboVo.comboPlanList[#index#].businessGoods" href="${goodsLookup}?goodsIdstr=${goodsIdstr}">查找带回</a>
												</c:if>
											</td>
											<td>
											 	<input name="comboVo.comboPlanList[${status.index}].comboPlan.goodsNumber" type="text" value="${loop.comboPlan.goodsNumber}" >
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
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="saveCombo()">保存</button></div></div></li>
						<li>
							<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
						</li>
					</ul>
				</div>
			</c:if>
	</form>

</div>