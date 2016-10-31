<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<title>上传照片</title>
<link href="<%=path %>/themes/css/login.css" rel="stylesheet"
	type="text/css" />
<script src="<%=path %>/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=path %>/js/verifyCode.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="<%=path %>/css/wx/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=path %>/css/wx/style.css">
<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"
	type="text/javascript"></script>
<script type="text/javascript">
wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: 'wx9eb4133bf836c7ae', // 必填，企业号的唯一标识，此处填写企业号corpid
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
	alert("失败");
});
function previewImage(imgUrl){
    wx.previewImage({
        current: imgUrl, 
        urls: [imgUrl]
    });
};
function changeImage()
{
	wx.chooseImage({
	    count: 9, // 默认9
	    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	    success: function (res) {
	        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	        $("#Id").val(localIds);
	        $("#previewPictures").html();
	        for(var i=0;i<localIds.length;i++)
	        {
	        	var imageId = localIds[i];
	        	$("#previewPictures").append("<li><img src='"+localIds[i]+"' class='img-responsive' onclick=\"previewImage('"+imageId+"')\"/></li>");
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
	        		uploadI(localIds);
	        	}else
	        		{
	        			alert(serverIds);
	        			$("#dId").val(serverIds);
// 	        			alert($("#dId").val());
			       		window.location.href="<%= path %>/page/wx/wxconnect/downloadF.action?serverId="
									+ serverIds
									+ "&userId="
									+ $("#userId").val()
									+ "&userName="
									+ $("#userName").val();
						}

					}
				});
	};
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
							type="button" id="exampleInputFile" class="uploadFileInput" onclick="changeImage()">
					</div>
					<div class="showPhotoes">
						<ul id="previewPictures">
						</ul>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label labelFont">选择分类</label>
					<div class="col-sm-10">
						<select class="form-control">
							<option>生菜</option>
							<option>白菜</option>
							<option>土豆</option>
						</select>
					</div>
				</div>
				<div class="btnBox">
					<input type="hidden" id="Id" />
					<input type="hidden" id="dId" />
					<input type="hidden" id="userId" value="${userId }" />
					<input type="hidden" id="userName" value="${userName }" />
					<button type="button" class="btn btn-default" onclick="uploadI()">确认提交</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>