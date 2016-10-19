<%@page import="com.google.gson.JsonObject"%>
<%@ page contentType="text/html;charset=GBK" language="java" 
	import="com.cntinker.util.*"
		
	import="org.apache.commons.logging.*"
	
	import="com.xnradmin.core.util.*"
	import="java.util.*"
	import="java.text.*"
	import="java.sql.*"
	import="java.math.*"
	import="java.io.*"
%>
<%
Log exLog = LogFactory.getLog("ex");	
Log log = Log4jUtil.getLog("iosclick");
try{
	
	String ip = request.getRemoteAddr();
	request.setCharacterEncoding("utf-8");
	
	log.debug("test ---------- request");
	log.debug("req: "+request.getRequestURL());
	

	Enumeration eh = request.getHeaderNames();
	while(eh.hasMoreElements()){
	        String h = (String)eh.nextElement();
	        log.debug("headr: "+h+" | "+request.getHeader(h)+"");
	}
	
	Enumeration e = request.getParameterNames();
	while(e.hasMoreElements()){
	    String k = e.nextElement().toString();
	    String v = request.getParameter(k);
	    log.debug("key: "+k+" | value: "+v);
	}
	
	BufferedReader br = new BufferedReader(new InputStreamReader(
            request.getInputStream()));
    StringBuffer sb = new StringBuffer();
    String currentLine = "";
    while( ( currentLine = br.readLine() ) != null){
        sb.append(currentLine);
    }
    String source = sb.toString();
    
    log.debug("post content : "+source);
	
	log.debug("address :"+request.getRemoteAddr()+" | host: "+request.getRemoteHost()+" | port: "+request.getRemotePort());
	
	JsonObject js = new JsonObject();
	
}catch(Exception e){	
	exLog.error(StringHelper.getStackInfo(e));	
	out.print("-1");
}
%>


productHandler(
{
	"id":123,
	"price":15.9,
	"name":"≤‚ ‘≤À∆∑"
}
);