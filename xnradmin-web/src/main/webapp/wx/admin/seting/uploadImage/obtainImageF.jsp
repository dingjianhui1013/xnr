<%@page import="com.xnradmin.po.wx.connect.WXInit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String corpid = WXInit.CORPID;
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<title>上传照片</title>
<link href="<%=path %>/themes/css/login.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<%=path %>/css/mobiscroll.css">
<link rel="stylesheet" type="text/css"
	href="<%=path %>/css/mobiscroll_date.css">
<script src="<%=path %>/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=path %>/js/verifyCode.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path %>/js/mobiscroll_date.js"
	charset="gb2312"></script>
<script type="text/javascript" src="<%=path %>/js/mobiscroll.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=path %>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"
	type="text/javascript"></script>
<script type="text/javascript">
wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: '${appId}', // 必填，企业号的唯一标识，此处填写企业号corpid
    timestamp: '${timep}', // 必填，生成签名的时间戳
    nonceStr: '${noncestr}', // 必填，生成签名的随机串
    signature:'${signature}',// 必填，签名，见附录1
    jsApiList: ['checkJsApi',
                'openEnterpriseContact',
                'onMenuShareTimeline',
                'onMenuShareAppMessage',
                'onMenuShareQQ',
                'onMenuShareWeibo',
                'hideMenuItems',
                'showMenuItems',
                'hideAllNonBaseMenuItem',
                'showAllNonBaseMenuItem',
                'translateVoice',
                'startRecord',
                'stopRecord',
                'onRecordEnd',
                'playVoice',
                'pauseVoice',
                'stopVoice',
                'uploadVoice',
                'downloadVoice',
                'chooseImage',
                'previewImage',
                'uploadImage',
                'downloadImage',
                'getNetworkType',
                'openLocation',
                'getLocation',
                'hideOptionMenu',
                'showOptionMenu',
                'closeWindow',
                'scanQRCode',
                'chooseWXPay',
                'openProductSpecificView',
                'addCard',
                'chooseCard',
                'openCard']
});
wx.ready(function(){
});
wx.error(function(res){
	alert("链接失败");
});
function previewImage(imgUrl){
    wx.previewImage({
        current: imgUrl, 
        urls: [imgUrl]
    });
};
function removeImage(id,ids){
	$("#"+id).remove();
	var localIds = $("#Id").val().split(",");
	localIds.splice(jQuery.inArray(ids,localIds),1); 
	$("#Id").val(localIds);
}
function changeImage()
{
	wx.chooseImage({
	    count: 9, // 默认9
	    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	    success: function (res) {
	        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	        var localIdv  = $("#Id").val();
	        if(localIdv==null||localIdv=="")
	        {
	     	   $("#Id").val(localIds);
	        }else
	        	{
	        		$("#Id").val(localIdv+","+localIds);
	        	}
 	        $("#previewPictures").html("");
	        var images= $("#Id").val().split(",");
			for(var i=0;i<images.length;i++)
	        {
	        	var imageId = images[i];
	        	$("#previewPictures").append("<li id='image"+i+"'><p class='closeIcon' onclick=\"removeImage('image"+i+"','"+imageId+"')\"><span class='glyphicon glyphicon-remove'></span></p><img src='"+imageId+"' class='img-responsive' onclick=\"previewImage('"+imageId+"')\"/></li>");
	        }

	    }
	});
};
var i =0;
var serverIds = [];
function uploadI()
{
	var localId = $("#Id").val();
	var localIds = localId.split(",");
	var length = localIds.length;
	wx.uploadImage({
	    localId: localIds[i], // 需要上传的图片的本地ID，由chooseImage接口获得
	    isShowProgressTips: 1,// 默认为1，显示进度提示
	    success: function (res) {
	        var serverId = res.serverId; // 返回图片的服务器端ID
	        serverIds.push(serverId);
	        i++;
	        if(i<length)
	        	{
	        		uploadI();
	        	}else
	        		{
	        				$("#dId").val(serverIds);
			       			upF();
						}
					}
				});
	};
function upF()
{
	$.ajax({
		url:"<%= path %>/page/wx/wxconnect/downloadFF.action",
		type:"POST",
		data:{serverId:$("#dId").val(),userId:'${userId}',userName:$('#userName').val(),type:$("#type").val(),remarks:$("#remarks").val(),_:new Date().getTime()},
		success:function(){
				alert("图片保存成功");
				window.location.href="${skiptUrl}";
			}
		});

	}
// function getGoods()
// {
// 	var id= $('#businesCategoryId option:selected').val();
// 	$.ajax({
// 		type:'POST',
<%-- 		url:'<%=path %>/page/wx/outplan/getGoods.action', --%>
// 	data : {
// 		businesCategoryId : id
// 	},
// 	dataType : 'JSON',
// 	success : function(data) {
// 		$("#type").html("<option value=''>请选择详细</option>");
// 		for (var i = 0; i < data.goodslist.length; i++) {
// 			$("#type")
// 					.append(
// 							"<option value="+data.goodslist[i].id+" class="+data.goodslist[i].goodsWeightId+">"
// 									+ data.goodslist[i].goodsName
// 									+ "</option>");
// 		}
// 	}
// });
// }
function yanzheng(){
	if($("#Id").val()=="")
		{
			alert("请先选择照片");
		}else if($("#type").val()=="")
			{
				alert("请选择详细类型");
			}else{
				uploadI();
			}
}
</script>
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<span class="titleBox">商户上传</span>
			<div class="myAccount">
				<span class="glyphicon glyphicon-user"></span>我的账户
			</div>
		</div>
		<div class="contentBox">
			<form role="form">
				<div class="form-group">
					<label for="" class="col-sm-2 control-label labelFont">上传相关图片</label>
					<div class="col-sm-10">
						<label for="exampleInputFile" class="uploadBtn"><span
							class="glyphicon glyphicon-plus addIcon"></span></label> <input
							type="button" id="exampleInputFile" class="uploadFileInput"
							onclick="changeImage()">
					</div>
					<div class="showPhotoes">
						<ul id="previewPictures">
						</ul>
					</div>
				</div>
				<div class="form-group">
<!-- 					<label for="" class="col-sm-2 control-label labelFont">选择分类</label> -->
<!-- 					<div class="col-sm-10"> -->
<!-- 						<select class="form-control" id="businesCategoryId" -->
<!-- 							onchange="getGoods()" > -->
<!-- 							<option value="">请选择商品</option> -->
<%-- 							<c:forEach items="${businesCategorys}" var="businesCategorys"> --%>
<%-- 								<option value="${businesCategorys.id}">${businesCategorys.categoryName}</option> --%>
<%-- 							</c:forEach> --%>
<!-- 						</select> -->
<!-- 					</div> -->
					<label for="" class="col-sm-2 control-label labelFont">选择详细类型</label>
					<div class="col-sm-11">
						<select class="form-control" id="type" >
							<option value="">请选择详细</option>
							<c:forEach items="${typeNames}" var="typeNames">
								<option value="${typeNames.id}">${typeNames.goodsName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div  class="form-group">
					<label for="" class="col-sm-2 control-label labelFont">请填写照片备注</label>
					<input type="text" id="remarks" name="remarks" class="numInput form-control" />
				</div>
				<div class="btnBox">
					<input type="hidden" id="Id" /> <input type="hidden" id="dId" />
<%-- 					<input type="hidden" id="userId" value="${userId }" />  --%>
						<input type="hidden" id="userName" value="${userName }" />
					<button type="button" class="btn btn-success" onclick="yanzheng()">确认提交</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>