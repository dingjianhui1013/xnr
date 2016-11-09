<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.xnradmin.client.service.ClientUserRegLogService"%>
<%@ page import="com.xnradmin.po.client.ClientUserRegLog"%>
<%@ page import="com.cntinker.util.*"%>
<%@ page import="com.xnradmin.core.util.*"%>
<%
	//获取银联POST过来异步通知信息
	String mobile = request.getParameter("mobile");
	if(!StringHelper.isNull(mobile)){
		ClientUserRegLog cur = new ClientUserRegLog();
		ClientUserRegLogService service = (ClientUserRegLogService) SpringBase.getCtx().getBean(
				"ClientUserRegLogService");
		cur = service.findByMobile(mobile);
		%>
		<form method="post" action="yanzheng.jsp">
			<input name="mobile" type="text" value=<%=mobile.replaceAll("/", "")%>>
			<input type="submit" value="查询">
		</form>
		
		
<%	
		if(cur!=null&&cur.getContent()!=null){		
			out.println(cur.getContent());
		}else{
			out.println("无验证码，可能未注册");
		}
	}
	else{// 服务器签名验证失败
%>		
	<form method="post" action="yanzheng.jsp">
		<input name="mobile" type="text"/>
		<input type="submit" value="查询">
	</form>
<%
}
%>
