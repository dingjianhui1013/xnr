<%@ page 
	
	language="java"
	import="java.io.*" 
	import="java.util.*" 
	import="java.text.*"
	import="sun.misc.BASE64Encoder" 
	import="java.net.*"
%>
<% 

//调试模式
 boolean DEBUG_ = true;
 String PARTNER		= "1220311401" ;	//财付通商户号
 String PARTNER_KEY	= "a398c60b3f0b846773897307d3d19c6d";	//财付通密钥
 String APP_ID		= "wxaeabc3bd7fd54699";	//appid
 String APP_SECRET	= "2600d40d93b689670c2f2de19b18212e";	//appsecret
 String APP_KEY		= "zzybkXgAi2InoZX49yKFhBIn3vBUixHFLjztqOjkM5i0rJzQ2y9fYPAVpPAKXbrNcsn55vfBXE5J51tZ12stVpW5WAw4vaE6QNkkiGYyXM5xwzJOXvhXpbsb3A5vRsE5";	//paysignkey 128位字符串(非appkey)
 String NOTIFY_URL	= "http://admin.17xnr.com/mall/portal/wx/demo/payack.jsp";  //支付完成后的回调处理页面,*替换成notify_url.asp所在路径
 String LOGING_DIR	= "/data/app/xnradmin/logs/paylog/";  //日志保存路径
%>
