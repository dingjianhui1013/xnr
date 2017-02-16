<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/business/admin/commodity/goods/add.action";
	String categoryLookup = basePath + "page/business/admin/commodity/category/lookup.action";
	String goodsLookup = basePath+"page/business/admin/commodity/goods/lookup.action";
	request.setAttribute("action",action);
	request.setAttribute("categoryLookup",categoryLookup);
	request.setAttribute("goodsLookup",goodsLookup);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
function checkTime(){
	var startTimes = [];
	var endTimes =[];
	var orderError = "前后时间顺序不对";
	var unionError = "时间有交叉";
	var error ="";
	var flag = true;
	$("#checkTimeTable").find(".unitBox").each(function(j,two){
		var start ='';
		var end ='';
		$(two).find(".date.required.textInput").each(function(i,one){
			if(one.value==''){
				$("#checkTimeFormADD").submit();
				return false;
			}else{
				if(i==0){
					start = Date.parse(new Date(one.value))
				}else{
					end = Date.parse(new Date(one.value))
				}
			}
		});
		if(start!=''&&end!=''){
			if(start>end){
				error ="第"+(j+1)+"行"+orderError;
				flag=false;
				alertMsg.error(error);
				return false;
			}else{
				startTimes[startTimes.length]=start;
				endTimes[endTimes.length]=end;
			}
		}
	});
	//判断时间是否交叉
	console.log(startTimes);
	console.log(endTimes);
	for(var m=0;m<=startTimes.length-1;m++){
		for(var n=0;n<m;n++){
			//alert("第"+(m+1)+"与"+(n+1)+"行比较");
			//alert(endTimes[m]+"和"+startTimes[n]+"比较"+(endTimes[m]<startTimes[n]));
			//alert(startTimes[m]+"和"+endTimes[n]+"比较"+(startTimes[m]>endTimes[n]));
			if(!((endTimes[m]<startTimes[n]) || (startTimes[m]>endTimes[n]))){
				error = "第"+(m+1)+"行与"+(n+1)+"行"+ unionError;
				flag=false;
				alertMsg.error(error);
				return false;
			}
		}
	}
	if(flag){
		$("#checkTimeFormADD").submit();
	}
}
</script>

<div class="pageContent">
	<form id="checkTimeFormADD" method="post" action="${action}" enctype="multipart/form-data" 
	class="pageForm required-validate" onsubmit="return iframeCallback(this)">
		<div class="pageFormContent" layoutH="56">
			<fieldset>
				<label>商品名称：</label>
				<input name="goodsName" type="text" size="25" class="required"/>			
			</fieldset>
			<fieldset>
				<label>商品类型：</label>
				<input class="readonly" name="category.id" readonly="readonly" type="hidden"/>
				<input class="readonly" name="category.primaryConfigurationId" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="category.categoryName" type="text" class="required"/>		
				<a class="btnLook" href="${categoryLookup}" lookupGroup="category">查找带回</a>
			</fieldset>
			<fieldset>
				<label>上级商品：</label>
				<input class="readonly" name="goodsParent.id" readonly="readonly" type="hidden"/>
				<input readonly="readonly" name="goodsParent.goodsName" type="text"/>		
				<a class="btnLook" href="${goodsLookup}" lookupGroup="goodsParent">查找带回</a>	
			</fieldset>
			<fieldset>
				<label>商品描述：</label>
				<textarea name="goodsDescription" rows="8" cols="100" 
					upLinkUrl="${upload}" upLinkExt="zip,rar,txt" 
					upImgUrl="${upload}" upImgExt="jpg,jpeg,gif,png" 
					upFlashUrl="upload.php" upFlashExt="swf"
					upMediaUrl="upload.php" upMediaExt:"avi"
					class="editor" type="text"/>
			</fieldset>
			<fieldset>
				<label>商品售价：</label>
				<input name="goodsOriginalPrice" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品进货价：</label>
				<input name="goodsPurchasePrice" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品重量：</label>
				<input name="goodsWeight" type="text" size="25" class="required number"/>
			</fieldset>
			<fieldset>
				<label>重量单位：</label>
				<select class="combox" name="goodsWeightId">
					<c:if test="${weightList!=null}">
						<c:forEach items="${weightList}" var="loop">
							<option value=${loop.id} select>${loop.weightName}</option>
   						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
				<label>商品状态：</label>
				<select class="combox" name="goodsStatus">
					<c:if test="${goodsStatusList!=null}">
						<c:forEach items="${goodsStatusList}" var="loop">
							<option value=${loop.id} select>${loop.statusName}</option>
   						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
				<label>采购分组：</label>
				<select class="combox" name="goods.buyTeamId">
					<c:if test="${buyTeamList!=null}">
						<c:forEach items="${buyTeamList}" var="loop">
							<option value=${loop.id} select>${loop.statusName}</option>
   						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<fieldset>
				<label>数据来源：</label>
				<select class="combox" name="goods.sourceId">
					<c:if test="${goodsSourceList!=null}">
						<c:forEach items="${goodsSourceList}" var="loop">
							<option value=${loop.id} select>${loop.statusName}</option>
   						</c:forEach>
					</c:if>
				</select>
			</fieldset>
			<!-- <fieldset>
				<label>是否优惠：</label>
				<select class="combox" name="isDiscountGoods">
					<c:if test="${isDiscountList!=null}">
						<c:forEach items="${isDiscountList}" var="loop">
							<option value=${loop.id} select>${loop.statusName}</option>
   						</c:forEach>
					</c:if>
				</select>
			</fieldset> -->
			<fieldset>
				<label>商品排序(小->大)：</label>
				<input name="goodsSortId" type="text" size="25" value="255" class="required number"/>
			</fieldset>
			<fieldset>
				<label>商品小图片：</label>
				<input name="goodsLogoFile" type="file" />
			</fieldset>
			<fieldset>
				<label>商品大图片：</label>
				<input name="goodsBigLogoFile" type="file" />
			</fieldset>
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li class="selected"><a href="javascript:void(0)"><span>商品分配管理</span></a></li>
						</ul>
					</div>
				</div>
			    <div class="tabsContent">
					<div>
						<table id="checkTimeTable" class="list nowrap itemDetail" addButton="新建商品分配" width="100%">
							<thead>
								<tr>
									<th type="date" name="allocationShowList[#index#].startTime"  lookupGroup="dishList[#index#].dish" lookupUrl="${dishLookup}" size="20"  fieldClass="required">初始时间</th>
									<th type="date" name="allocationShowList[#index#].endTime" size="20" fieldClass="required" >结束时间</th>								 
									<th type="input" name="allocationShowList[#index#].allocationCount" size="20" fieldClass="required number" >分配数量</th>
									<th type="del"  width="10%">操作</th>
								</tr>
							</thead>
							<tbody>
							<c:if test="${allocationShowList!=null}">
									<c:forEach items="${allocationShowList}" var="loop" varStatus="status">					
										<tr class="unitBox">
											<td>
												<input name="allocationShowList[${status.index}].startTime" value="${loop.startString}" class="date required textInput" datefmt="yyyy-MM-dd" size="20" type="text">
												<a class="inputDateButton" href="javascript:void(0)">选择</a>
											</td>
											<td>
												<input name="allocationShowList[${status.index}].endTime" value="${loop.endString}" class="date required textInput" datefmt="yyyy-MM-dd" size="20" type="text">
												<a class="inputDateButton" href="javascript:void(0)">选择</a>
											</td>
											<td>
												<input name="allocationShowList[${status.index}].allocationCount" value="${loop.allocationCount}" size="20" class="required number textInput" type="text">
											</td>
											<td>
												<a href="javascript:void(0)" class="btnDel ">删除</a>
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
			<div class="formBar">
				<ul>				
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="checkTime()">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</div>
	</form>

</div>