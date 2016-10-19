<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String action = basePath+"page/script/add.action";
	

	
	request.setAttribute("action",action);

%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<form method="post" action="${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>类名：</label>
				<input name="query.scriptpo.className" value="com.xnradmin.script.business." type="text" size="30" alt="输入类名" class="required"/>
				
			</p>				
			<p>
				<label>状态：</label>
				<select class="combox" name="query.scriptpo.status">
					<c:if test="${statusList!=null}">
						<c:forEach items="${statusList}" var="loop">	
							<option value=${loop.id}>${loop.statusName}</option>
						</c:forEach>
					</c:if>						
				</select>		
			</p>				
			<div class="divider"></div>
			<fieldset>
				<legend>脚本环境信息</legend>
				<p>
					脚本物理根目录：${scriptdto.scriptDIR}					
				</p>
				<p>
					脚本编码：${scriptdto.scriptEncode}										
				</p>
				<p>
					脚本入口方法：					
					<c:forEach var="methodName" items="${scriptdto.scriptMethods}">  
					    [<c:out value="${methodName}" />] 
					</c:forEach>  										
				</p>
				
			</fieldset>
			<fieldset>
				<legend>注意:</legend>
				<p>
					<div style="color:red">注意:脚本虽然支持JDK7，但类似@Column(name = "ID",unique = true,nullable = false)的多参数注解还不可用,只能使用@Id("xxx")这种单一注解形势</div>
				</p>
			</fieldset>
		</div>
		
		<div class="formBar">
			<ul>				
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>

</div>