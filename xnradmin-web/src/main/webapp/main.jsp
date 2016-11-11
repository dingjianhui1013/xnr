<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*" 
	import="com.xnradmin.constant.*" 
	import="sun.misc.BASE64Encoder" import="java.net.*"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	
	//默认的系统菜单
	//组织信息
	String orgInfo = basePath+"page/org/info.action";
	//组织类型
	String orgType = basePath+"page/orgtype/info.action";
	//员工信息
	String staffInfo = basePath+"page/staff/info.action";
	//角色信息
	String roleInfo = basePath+"page/role/info.action";
	//权限信息
	String perInfo = basePath+"page/per/info.action";
	//菜单信息
	String menuInfo = basePath+"page/menu/info.action";
	
	
	
	request.setAttribute("orgInfo",orgInfo);
	request.setAttribute("orgType",orgType);
	request.setAttribute("staffInfo",staffInfo);
	request.setAttribute("roleInfo",roleInfo);
	request.setAttribute("perInfo",perInfo);
	request.setAttribute("menuInfo",menuInfo);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<title>康源公社管理平台</title>

		<link href="themes/default/style.css" rel="stylesheet" type="text/css"
			media="screen" />
		<link href="themes/css/core.css" rel="stylesheet" type="text/css"
			media="screen" />
		<link href="themes/css/print.css" rel="stylesheet" type="text/css"
			media="print" />
		<link href="uploadify/css/uploadify.css" rel="stylesheet"
			type="text/css" media="screen" />		
		<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

		<script src="js/speedup.js" type="text/javascript">
</script>
		<script src="js/jquery-1.7.2.min.js" type="text/javascript">
</script>
		<script src="js/jquery.cookie.js" type="text/javascript">
</script>
		<script src="js/jquery.validate.js" type="text/javascript">
</script>
		<script src="js/jquery.bgiframe.js" type="text/javascript">
</script>
		<script src="xheditor/xheditor-1.2.1.min.js"
			type="text/javascript">
</script>
		<script src="uploadify/scripts/uploadify.swf" type="text/javascript">
</script>
		<script src="uploadify/scripts/jquery.uploadify.min.js"
			type="text/javascript">
</script>

		<script src="js/dwz.core.js" type="text/javascript">
</script>
		<script src="js/dwz.util.date.js" type="text/javascript">
</script>
		<script src="js/dwz.validate.method.js" type="text/javascript">
</script>
		<script src="js/dwz.regional.zh.js" type="text/javascript">
</script>
		<script src="js/dwz.barDrag.js" type="text/javascript">
</script>
		<script src="js/dwz.drag.js" type="text/javascript">
</script>
		<script src="js/dwz.tree.js" type="text/javascript">
</script>
		<script src="js/dwz.accordion.js" type="text/javascript">
</script>
		<script src="js/dwz.ui.js" type="text/javascript">
</script>
		<script src="js/dwz.theme.js" type="text/javascript">
</script>
		<script src="js/dwz.switchEnv.js" type="text/javascript">
</script>
		<script src="js/dwz.alertMsg.js" type="text/javascript">
</script>
		<script src="js/dwz.contextmenu.js" type="text/javascript">
</script>
		<script src="js/dwz.navTab.js" type="text/javascript">
</script>
		<script src="js/dwz.tab.js" type="text/javascript">
</script>
		<script src="js/dwz.resize.js" type="text/javascript">
</script>
		<script src="js/dwz.dialog.js" type="text/javascript">
</script>
		<script src="js/dwz.dialogDrag.js" type="text/javascript">
</script>
		<script src="js/dwz.sortDrag.js" type="text/javascript">
</script>
		<script src="js/dwz.cssTable.js" type="text/javascript">
</script>
		<script src="js/dwz.stable.js" type="text/javascript">
</script>
		<script src="js/dwz.taskBar.js" type="text/javascript">
</script>
		<script src="js/dwz.ajax.js" type="text/javascript">
</script>
		<script src="js/dwz.pagination.js" type="text/javascript">
</script>
		<script src="js/dwz.database.js" type="text/javascript">
</script>
		<script src="js/dwz.datepicker.js" type="text/javascript">
</script>
		<script src="js/dwz.effects.js" type="text/javascript">
</script>
		<script src="js/dwz.panel.js" type="text/javascript">
</script>
		<script src="js/dwz.checkbox.js" type="text/javascript">
</script>
		<script src="js/dwz.history.js" type="text/javascript">
</script>
		<script src="js/dwz.combox.js" type="text/javascript">
</script>
		<script src="js/dwz.print.js" type="text/javascript">
</script>
		<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
		<script src="js/dwz.regional.zh.js" type="text/javascript">
</script>

		<script src="js/ajaxsendrest.js" type="text/javascript">
</script>
		
		<script type="text/javascript">
$(function() {
	DWZ.init("dwz.frag.xml", {
		loginUrl : "login_dialog.jsp",
		loginTitle : "登录", // 弹出登录对话框
		//		loginUrl:"login.html",	// 跳到登录页面
		statusCode : {
			ok : 200,
			error : 300,
			timeout : 301
		}, //【可选】
		pageInfo : {
			pageNum : "pageNum",
			numPerPage : "numPerPage",
			orderField : "orderField",
			orderDirection : "orderDirection"
		}, //【可选】
		debug : false, // 调试模式 【true|false】
		callback : function() {
			initEnv();
			$("#themeList").theme( {
				themeBase : "themes"
			}); // themeBase 相对于index页面的主题base路径
	}
	});
});
</script>

	</head>

	<body scroll="no">
		<div id="layout">
			<div id="header">
				<div class="headerNav">
					<a class="logo">标志</a>
					<ul class="nav">
						
						<li>
							<a href="#">欢迎： <%=session.getAttribute(SessionConstant.SESSION_LOGIN_STAFF_NAME)%> (<%=session.getAttribute(SessionConstant.SESSION_LOGIN_STAFF)%>)</a>
							
						</li>
						<li>
							<a href="staff/changePwd.jsp" target="dialog" width="600">修改密码</a>
						</li>
						<li>
							<a href="logout.action">退出</a>
						</li>
					</ul>
					<ul class="themeList" id="themeList">
						<li theme="default">
							<div class="selected">
								蓝色
							</div>
						</li>
						<li theme="green">
							<div>
								绿色
							</div>
						</li>
						<!--<li theme="red"><div>红色</div></li>-->
						<li theme="purple">
							<div>
								紫色
							</div>
						</li>
						<li theme="silver">
							<div>
								银色
							</div>
						</li>
						<li theme="azure">
							<div>
								天蓝
							</div>
						</li>
					</ul>
				</div>

				<!-- navMenu -->

			</div>

			<div id="leftside">
				<div id="sidebar_s">
					<div class="collapse">
						<div class="toggleCollapse">
							<div></div>
						</div>
					</div>
				</div>
				<div id="sidebar">
					<div class="toggleCollapse">
						<h2>
							主菜单
						</h2>
						<div>
							收缩
						</div>
					</div>

					<div class="accordion" fillSpace="sidebar">
						<!-- 1级菜单 -->
						
						<!-- 菜单 -->
						${menuList}
						
						
					</div>										
				</div>
			</div>
			<div id="container">
				<div id="navTab" class="tabsPage">
					<div class="tabsPageHeader">
						<div class="tabsPageHeaderContent">
							<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
							<ul class="navTab-tab">
								<li tabid="main" class="main">
									<a href="javascript:;"><span><span class="home_icon">首页</span>
									</span> </a>
								</li>
							</ul>
						</div>
						<div class="tabsLeft">
							left
						</div>
						<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
						<div class="tabsRight">
							right
						</div>
						<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
						<div class="tabsMore">
							more
						</div>
					</div>
					<ul class="tabsMoreList">
						<li>
							<a href="javascript:;">首页</a>
						</li>
					</ul>
					<div class="navTab-panel tabsPageContent layoutBox">
						<div class="page unitBox">					
							<!-- 		
							<h2 class="contentTitle">快捷访问:</h2>
							<div class="pageFormContent" layoutH="80"
								style="margin-right: 230px">

																																			
								<p>
									<a class="button" href="iframe/worktime/myworktime.jsp" target="navTab" rel="myValue"><span>工时查询</span></a><br /><br />									
								</p>
								<p>
									<a class="button" href="users/changePwd.jsp" target="navTab" rel="changepwd"><span>修改密码</span></a><br /><br />									
								</p>
								<p>
									<a class="button" href="process_step/add_task.jsp" target="navTab" rel="addTask"><span>工作安排</span></a><br /><br />									
								</p>
								<p>
									<a class="button" href="process_step/confrimProcessstep.jsp" target="navTab" rel="confirm"><span>工序确认</span></a><br /><br />									
								</p>
								
								<div class="divider"></div>
								<p>
									<a class="button" href="processflow/addFromTemplate.jsp" target="navTab" rel="confirm"><span>模板添加测试</span></a><br /><br />									
								</p>
							</div>
							 -->
							<!-- 
						<div style="width:230px;position: absolute;top:60px;right:0" layoutH="80">
							<iframe width="100%" height="430" class="share_self"  frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?width=0&height=430&fansRow=2&ptype=1&skin=1&isTitle=0&noborder=1&isWeibo=1&isFans=0&uid=1739071261&verifier=c683dfe7"></iframe>
						</div>
						 -->
						</div>

					</div>
				</div>
			</div>

		</div>

		<div id="footer">
			Copyright &copy; 2012
			<a>admin</a> 
		</div>


	</body>
</html>