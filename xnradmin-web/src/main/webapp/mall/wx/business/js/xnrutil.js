function getUid(){
	var uid = localStorage.getItem("uid");
	var password = localStorage.getItem("password");
	var res;
	$.ajax(
	{
		type: "post",
		url: "/page/wx/client/business/login/login.action",
		data : "password="+password+"&loginId="+uid,
		async : false,
		success : function(data) {
			console.log(data);
			res = data;
		}
	})
	if(res.errorMsg==0){
		setLoginid(res.userInfoJson);
	}
	return res.errorMsg;
}

function xnrlogout(){
	//var uid = localStorage.getItem("uid");
	alert("退出成功，请重新登录");
	localStorage.clear();
	//localStorage.removeItem("uid");
	window.location="login.html";
	return false;
}


function setLoginid(userInfoJson){
	if(userInfoJson!=null){
		localStorage.setItem("userInfo",userInfoJson);
		var _temp = JSON.parse(userInfoJson);
		localStorage.setItem("uid",_temp["loginId"]);
		localStorage.setItem("password",_temp["password"]);
	}
	return userInfoJson;
}

function getQueryString(name) {
	   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
	   var r = window.location.search.substr(1).match(reg);
	   if (r!=null) return (r[2]); return null;
}

function length(str)
{
     var i,sum;
     sum=0;
     for(i=0;i<str.length;i++)
     {
         if ((str.charCodeAt(i)>=0) && (str.charCodeAt(i)<=255))
             sum=sum+1;
         else
             sum=sum+2;
     }
     return sum;
}