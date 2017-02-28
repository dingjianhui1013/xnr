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
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	//计划时间没有选
	
	var flag=true;
	$("#farmeTable").find("tr").each(function(i,one){
		//修改时间的name 
		var comboCycleStr = $(one).find("input[name$='.comboCycleStr']");
		var date = $(one).find("input[name$='comboCycleDate']");
		var input = comboCycleStr.val();
		var name = $(one).find("input[name$='.comboPlan.goodsNumber']").attr("name");
		name = name.substring(0,name.lastIndexOf('.comboPlan.goodsNumber'))+".comboCycleStr";
		var dateName = name.substring(0,name.lastIndexOf('.comboCycleStr'))+".comboPlan.comboPlanDate";
		comboCycleStr.attr("name",name);
		date.attr("name",dateName);
		if(input==undefined){
			alertMsg.error("套餐配送 计划时间必选");
			flag=false;
		}
	})
	//配送计划里面的时间验证 不能是过去时间
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
				$("#content").submit();
			}
		});
	}

}
function changeComboType(obj){
	var val=$(obj).val();
	var tr=$(obj).closest("tr");
	tr.find("span").each(function(i,one){
		$(one).remove();
	});
	tr.find("input[name$='.comboCycleStr']").remove();
	if(val==0){
		var comboCycleDate =$('<span id="comboCycleDateSpan"><input type="text" id="comboCycleDate" name="comboCycleDate" class="date required textInput" dateFmt="yyyy-MM-dd" size="20"/><a class="inputDateButton" href="javascript:void(0)">选择</a></span>');
		comboCycleDate.insertAfter($(obj));
		$('#comboCycleDate').datepicker();
		tr.append("<input type='hidden' name='.comboCycleStr' value=''>");
	}else if(val==1){
		var html = "<span id='comboCycleSpan'><select id='comboCycle' name='comboCycle' onchange='writeTime(this,2)'>";
			html+= "		<option value=''>请选择</option>";
		<s:iterator value="#request.comboCycleList" id="item">
			html+= '		<option value="<s:property value="#item.id" />"><s:property value="#item.statusName" /></option>'
		</s:iterator>
			html+= "  <select></span>"
		var comboCycle =$(html);
		comboCycle.insertAfter($(obj));
		
	}else if(val==2){
		var html = "<span id='comboCycleSpan'><select id='comboCycle' name='comboCycle' onchange='changeComboCycle(this)'>";
		html+= "		<option value=''>请选择</option>";
		<s:iterator value="#request.comboFixedStatusList" id="item">
		html+= '		<option value="<s:property value="#item.status.id" />"><s:property value="#item.status.statusName" /></option>'
		</s:iterator>
		html+= "  <select></span>";
		var comboCycle =$(html);
		comboCycle.insertAfter($(obj));
	}
}
function changeComboCycle(obj){
	var val=$(obj).val();
	var tr=$(obj).closest("tr");
	tr.find("span[id='comboCycleDateSpan']").each(function(i,one){
		$(one).remove();
	})
	tr.find("input[name$='.comboCycleStr']").remove();
	for(var i=0;i<cycleStatus.length;i++){
		if(cycleStatus[i].id==val){
			var html = "<span id='comboCycleDateSpan'><select id='comboCycleDate' name='comboCycleDate' onchange='writeTime(this,3)'>";
			html+= "		<option value=''>请选择</option>";
			for(var j=0;j<cycleStatus[i].list.length;j++){
				html+= "	<option value="+cycleStatus[i].list[j].id+">"+cycleStatus[i].list[j].name+"</option>";
			}
			html+= "  <select></span>";
			var comboCycleDate =$(html);
			comboCycleDate.insertAfter($(obj));
		}
	}
}
function writeTime(obj,type){
	var tr=$(obj).closest("tr");
	var name = tr.find("input[name$='.comboPlan.goodsNumber']").attr("name");
	var comboType = tr.find("select[id='comboType']").val();
	var comboCycle = tr.find("select[id='comboCycle']").val();
	var comboDate = tr.find("select[id='comboCycleDate']").val();
	name = name.substring(0,name.lastIndexOf('.comboPlan.goodsNumber'))+".comboCycleStr";
	
	if(type==2){
		tr.append("<input type='hidden' name="+name+" value="+comboType+"#"+comboCycle+">");
	}else if(type==3){
		tr.append("<input type='hidden' name="+name+" value="+comboType+"#"+comboCycle+"#"+comboDate+">");
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
var cycleStatus=[];
var comboCycleStr=[];
var comboPlanDate=[];
$(function(){
	//初始化下拉选
	<s:iterator value="#request.comboFixedStatusList" id="item">
		var struct ={
				id:'<s:property value="#item.status.id" />',
				name:'<s:property value="#item.status.statusName" />'
		};
		var list=[];
		<s:iterator value="#item.statusChildList" id="item1">
			var child ={
					id:'<s:property value="#item1.status.id" />',
					name:'<s:property value="#item1.status.statusName" />'
			};
			list[list.length]=child;
		</s:iterator>
		struct.list=list;
		cycleStatus[cycleStatus.length]=struct;
	</s:iterator>
	//初始化下拉选的值
	<s:iterator value="#request.comboVo.comboPlanList" id="item">
		comboCycleStr[comboCycleStr.length]='<s:property value="#item.comboCycleStr" />';
		comboPlanDate[comboPlanDate.length]='<s:property value="#item.comboPlan.comboPlanDate" />';
	</s:iterator>
	var pageType = '${pageType}';
	if(pageType==1){//查看页面 禁用所有input 和button
		$("#content").find("input").each(function(i,one){
			$(one).attr("readonly","readonly");
		})
		$("#content").find("button").each(function(i,one){
			$(one).attr("disabled","disabled");
		})
		$("#itemDetail1").removeClass("itemDetail");
		$("#itemDetail2").removeClass("itemDetail");
		//计算商品价格
		countPrice();
		$("#farmeTable").find("tr").each(function(i,one){
			var str = comboCycleStr[i];
			if(str!=''){
				var strs = str.split("#");
				if(strs[0]==1){
					var comboPlanType = $(one).find("select[id$='comboType']");
					comboPlanType.val(strs[0]);
					comboPlanType.trigger("change");
					var comboCycle = $(one).find("select[id$='comboCycle']");
					comboCycle.val(strs[1]);
					comboCycle.trigger("change");
				}else if(strs[0]==2){
					var comboPlanType = $(one).find("select[id$='comboType']");
					comboPlanType.val(strs[0]);
					comboPlanType.trigger("change");
					var comboCycle = $(one).find("select[id$='comboCycle']");
					comboCycle.val(strs[1]);
					comboCycle.trigger("change");
					var comboCycle = $(one).find("select[id$='comboCycleDate']");
					comboCycle.val(strs[2]);
					comboCycle.trigger("change");
				}
			}else{
				var comboPlanType = $(one).find("select[id$='comboType']");
				comboPlanType.val(0);
				comboPlanType.trigger("change");
				$('#comboCycleDate').val(comboPlanDate[i]);
			}
		})
		
	}else if(pageType==2){
		$("#itemDetail1").addClass("itemDetail");
		$("#itemDetail2").addClass("itemDetail");
		//计算商品价格
		countPrice();
		$("#farmeTable").find("tr").each(function(i,one){
			var str = comboCycleStr[i];
			if(str!=''){
				var strs = str.split("#");
				if(strs[0]==1){
					var comboPlanType = $(one).find("select[id$='comboType']");
					comboPlanType.val(strs[0]);
					comboPlanType.trigger("change");
					var comboCycle = $(one).find("select[id$='comboCycle']");
					comboCycle.val(strs[1]);
					comboCycle.trigger("change");
				}else if(strs[0]==2){
					var comboPlanType = $(one).find("select[id$='comboType']");
					comboPlanType.val(strs[0]);
					comboPlanType.trigger("change");
					var comboCycle = $(one).find("select[id$='comboCycle']");
					comboCycle.val(strs[1]);
					comboCycle.trigger("change");
					var comboCycle = $(one).find("select[id$='comboCycleDate']");
					comboCycle.val(strs[2]);
					comboCycle.trigger("change");
				}
			}else{
				var comboPlanType = $(one).find("select[id$='comboType']");
				comboPlanType.val(0);
				comboPlanType.trigger("change");
				$('#comboCycleDate').val(comboPlanDate[i]);
			}
		})
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
	<form method="post" action="${action}" class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this)" id="content">
	
	<div class="pageFormContent" layoutH="649" style="height: 70px">
			<fieldset>
				<label>套餐名称：</label>
				<input name="comboVo.combo.comboName" type="text" size="25" class="required" value="${comboVo.combo.comboName }"/>			
			</fieldset>
			<fieldset>
				<label>套餐价格：</label>
				<input name="comboVo.combo.comboPrice" type="text" size="25" class="required number" value="${comboVo.combo.comboPrice}"/>			
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
			<fieldset>
				<label>商品小图片：</label>
				<input name="comboImgSmallFile" type="file">
			</fieldset>
			<fieldset>
				<label>商品大图片：</label>
				<input name="comboImgBigFile" type="file">
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
		<div class="pageFormContent" layoutH="345" style="height: 320px">
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
												<c:if test="${pageType!=1}">
													<a class="btnLook" title="查找带回" lookupgroup="comboVo.comboGoodsList[${status.index}].businessGoods" href="${goodsLookup}">查找带回</a>
												</c:if>
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
				<c:if test="${pageType!=1}">
					<button type="button" onclick="countPrice()">计算总价</button>
				</c:if>
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
									        	<select id='comboType' name='comboType' onchange='changeComboType(this)'>
													<option value=''>请选择</option>
													<option value='0'>固定时间</option>
													<option value='1'>固定周期</option>
													<option value='2'>固定周期固定时间</option>
												<select>
									        </td>
											<td>
												<input type="hidden" name="comboVo.comboPlanList[${status.index}].businessGoods.id" value="${loop.businessGoods.id}">
											    <input type="text"   name="comboVo.comboPlanList[${status.index}].businessGoods.goodsName"  value="${loop.businessGoods.goodsName}" >
												<c:if test="${pageType==2}">
													<a class="btnLook" title="查找带回" lookupGroup="comboVo.comboPlanList[${status.index}].businessGoods" href="${goodsLookup}?goodsIdstr=${goodsIdstr}">查找带回</a>
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