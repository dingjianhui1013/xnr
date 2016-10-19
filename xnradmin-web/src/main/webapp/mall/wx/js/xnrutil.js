function getUid(){
	var uid = localStorage.getItem("uid");
	if(uid==null || uid=="null"){
		uid = getQueryString("uid");
	}
	if(uid!=null && uid!="null"){
		localStorage.setItem("uid",uid);
	}	
	return uid;
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