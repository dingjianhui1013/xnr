<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>关于我们</title>
</head>
<body>
<%@include file="header.jsp" %>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=yMqqu0nz8TS4mUWfZDi9Rav1pZuakbNW"></script>
<div class="contact">
	  <div class="container">
		 <ol class="breadcrumb">
		  <li><a href="<%=basePath%>/front/index.action">首页</a></li>
		  <li class="active">关于我们</li>
		 </ol>
		<div class="contact_info">
			<h3>公司简介</h3>
			<div class="companyBox">
				<p>康源公社于2014年正式涉足电商领域。2015年，京东集团市场交易额达到4627亿元，净收入达到1813亿元，年交易额同比增长78%，增速是行业平均增速的2倍*。京东是中国收入规模最大的互联网企业。2016年7月，京东入榜2016《财富》全球500强，成为中国首家、唯一入选的互联网企业。截至2016年6月30日，京东集团拥有超过11万名正式员工，业务涉及电商、金融和技术三大领域。</p>
				<p>2014年5月，京东集团在美国纳斯达克证券交易所正式挂牌上市，是中国第一个成功赴美上市的大型综合型电商平台，并成功跻身全球前十大互联网公司排行榜，2015年7月，京东凭借高成长性入选纳斯达克100指数和纳斯达克100平均加权指数。
				京东商城</p>
			</div>
	 		<div class="mapBox" id="allmap" style="width:100%;height:300px"></div>
			<script type="text/javascript">
				// 百度地图API功能
				var map = new BMap.Map("allmap");
				var point = new BMap.Point(116.34483,40.090792);
				map.centerAndZoom(point, 15);
				var marker = new BMap.Marker(point);  // 创建标注
				map.addOverlay(marker);               // 将标注添加到地图中
				marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			</script>
 		</div>
 	</div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>