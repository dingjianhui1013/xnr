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
	//http://121.199.32.231:8080/coop/sp/106668733/report.jsp?servicecode=MTK1065800810115824&msgid=&mobileid=13466676309&status=DELIVRD&serviceid=100456&codeid=1001&linkid=CZ01301710154694303604&sendtime=2013-01-30+17%3A10%3A15

Log exLog = LogFactory.getLog("ex");	
try{

	
	String ip = request.getRemoteAddr();	
	request.setCharacterEncoding("UTF-8");	
	
	Enumeration e = request.getParameterNames();
	while(e.hasMoreElements()){
	    String k = e.nextElement().toString();
	    String v = request.getParameter(k);	    
	    System.out.println("key: "+k+" | value: "+v);
	}
	
	InterfaceService service = (InterfaceService)SpringBase.getCtx().getBean("InterfaceService");
	
	service.commonInterface(request,response);
	
	
}catch(Exception e){
	e.printStackTrace();
	exLog.error(e.getMessage());
	out.println(StringHelper.getStackInfo(e));	
}
%>
