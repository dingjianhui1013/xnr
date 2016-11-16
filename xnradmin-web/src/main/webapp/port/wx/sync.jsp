<%@ page contentType="text/html;charset=GBK" language="java" 
	
	
	import="com.cntinker.util.*"
	import="com.xnradmin.core.util.*"	
	import="com.xnradmin.client.service.*"
	
	import="org.apache.commons.logging.*"
	
	import="java.util.*"
	import="java.text.*"
	import="java.sql.*"
	import="java.math.*"
	
%>
<%		
	
//http://www.51xnr.com/port/wx/sync.jsp
Log exLog = LogFactory.getLog("ex");	
Log log = Log4jUtil.getLog("wxport");
try{
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	
	String ip = request.getRemoteAddr();	
	request.setCharacterEncoding("UTF-8");	
	log.debug("basePath:"+basePath);
	Enumeration e = request.getParameterNames();
	while(e.hasMoreElements()){
	    String k = e.nextElement().toString();
	    String v = request.getParameter(k);	    
	    System.out.println("key: "+k+" | value: "+v);
	}
	
	
	
	WxPortService service = (WxPortService)SpringBase.getCtx().getBean("WxPortService");
	service.commonInterface(request, response);
	
	
	
}catch(Exception e){
	e.printStackTrace();
	exLog.error(e.getMessage());
	out.println(StringHelper.getStackInfo(e));	
}
%>
