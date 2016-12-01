<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>个人中心页</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/mobiscroll.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/mobiscroll_date.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/site.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/photoswipe.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/default-skin.css">
	
	<script type="text/javascript" src="<%=path %>/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/mobiscroll_date.js"></script>
	<script type="text/javascript" src="<%=path %>/js/mobiscroll.js"></script>
	<script type="text/javascript" src="<%=path %>/js/common.js"></script>
	
	<script src="<%=path %>/js/photoswipe.min.js"></script>
    <script src="<%=path %>/js/photoswipe-ui-default.min.js"></script>
    <script type="text/javascript">
    </script>
	<script type="text/javascript" >
	$(function(){
		if("${status}"==null||"${status}"==""||"${status}".val()=="0")
			{
				alert("请联系系统管理员，审核身份！")
			}
	})
		function deletePlan(id){
			var status = true;
			//获取状态
// 			$.ajax({
// 				 type: "GET",
// 	             url: "",
// 	             data: {id:id,type:"delete"},
// 	             dataType: "json",
// 	             success: function(data){
// 	            	 status = data.status;
// 	             }
// 			});
			if(status){
				location.href="<%=path %>/page/wx/outplan/deletePlan.action?deleteId="+id;
<%-- 			//	$.post("<%=path %>/page/wx/outplan/deletePlan.action",{deleteId:id},null);  --%>
			}else{
				alert("计划不能删除");
			}
		}
		function deleteImgae(imageUrl,imageid,count,typeCount){
			// 点击图片所在日期所含类型数量
			var date_typeCount  = $(".datecount"+count).val();
			//点击图片所在日期所在类型下的照片数量
			var type_imageCount = $(".typecount"+count+typeCount).val();
			$.ajax({
				type:'POST',
				url:"<%=path %>/page/wx/personalCenter/deleteImage.action",
				data:{imageUrl:imageUrl,imageid:imageid,_:new Date().getTime()},
				dataType:'json',
				success:function(data)
				{
					if(data.status=="0")
						{
							
							alert("删除成功");
							if(type_imageCount!=1){
								$("#image"+imageid).remove();
								$(".typecount"+count+typeCount).val(type_imageCount-1);
								
							}
							if(type_imageCount==1)
								{
									if(date_typeCount!=1)
										{
											$("#typeCount"+typeCount).remove();
											$("#image"+data.imageid).remove();
											$(".datecount"+count).val(date_typeCount-1);
										}
									if(date_typeCount==1)
										{
											$("#datecount"+count).remove();
										}
								}
						}else
							{
								alert("删除失败");
							}
				}
			});
		}
		function changeShow(){
			$(".closeIcon").css('display','block'); ;
			$("#bj").html("完成");
			$("#bj").attr("onclick","changeHidden()");
		}
		function changeHidden(){
			$(".closeIcon").css('display','none'); ;
			$("#bj").html("编辑");
			$("#bj").attr("onclick","changeShow()");
		}
		function editPlan(id){
			var status = true;
			//获取状态
// 			$.ajax({
// 				 type: "GET",
// 	             url: "",
// 	             data: {id:id,type:"delete"},
// 	             dataType: "json",
// 	             success: function(data){
// 	            	 status = data.status;
// 	             }
// 			});
			if(status){
				location.href="<%=path %>/page/wx/outplan/editPlanForm.action?eidtId="+id;
				//$.post('<%=path %>/page/wx/outplan/editPlanForm.action',{eidtId:id},null,'json/xml/html');
			}else{
				alert("计划不能修改");
			}
		}
	</script>
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<span class="titleBox">个人中心</span>
			<div class="myAccount"><span class="glyphicon glyphicon-user"></span>我的账户</div>
		</div>
		<div class="contentBox personalBox">
			<div class="uploadImgListBox">
				<form role="form">
					  <div class="form-group">
					    <div class="searchBox">
					    	<input type="text"  class="form-control"  placeholder="请输入关键词进行搜索" id="tags"/>
					    </div>
					    	<input type="button" value="搜索" class="btn btn-primary searchBtn">
					  </div>
					  <h3 class="bigTit">我上传的图片</h3>
					  <div class="form-group">
					    <div class="col-sm-10 uploadImgBox">
					    	 <ul>
					    	 <c:set var="i" value="1"/>
					    	 <c:set var="ii" value="1"/>
					    	 <c:set var="count" value="1" />
					    	 	<c:forEach items="${date_type_images}" var="dtis">
									<li id="datecount${count}"><span class="circleIcon"></span>
									<c:forEach items="${dtis}" var="dti">
											<h3>${dti.key}</h3>
											<div>
												<c:forEach items="${dti.value}" var="dtiv">
<!-- 												当前日期下的类型数量 -->
												<input type="hidden" value="${fn:length(dtiv)}" class="datecount${count}"/>
												<c:set var="typeCount" value="1"/>
													<c:forEach items="${dtiv}" var="ditvs">
														<p class="sortTit" id="typeCount${typeCount}">${ditvs.key}</p>
														<c:forEach items="${ditvs.value}" var="images">
															<img src="<%=path %>${images}" style="display: none" class="images${ii}"/>
															 <c:set var="ii" value="${ii+1}"/>
														</c:forEach> 
														<div class="uploadImgList  demo-gallery">
															<c:forEach items="${ditvs.value }" var="images">
																	<a href="<%=path %>${images}" data-size="1600x1068" data-med="<%=path %>${images}" data-med-size="1024x683" id="image${i}">
									    	 						<p class="closeIcon" onclick="deleteImgae('${images}','${i}','${count}','${typeCount}')" style="display: none"><span class="glyphicon glyphicon-remove"></span></p>
					          											<img src="<%=path %>${images}" />
					          											<script type="text/javascript">
// 					          												alert($(".images"+${i}).width()+"x"+$(".images"+${i}).height());
					          												$("#image"+${i}).attr("data-med-size",$(".images"+${i}).width()+"x"+$(".images"+${i}).height());
// 					          												alert($("#image"+${i}).attr("data-med-size"));
					          											</script>
					          											<c:set var="i" value="${i+1}"/>
<!-- 					          											类型下的图片数量 -->
					          											<input type="hidden" value="${fn:length(ditvs.value)}" class="typecount${count}${typeCount}"/>
					        										</a>
															</c:forEach>
														</div>
														<c:set var="typeCount" value="${typeCount+1}"/>
													</c:forEach>
												</c:forEach>
											</div>
										</c:forEach>
									</li>
								<c:set var="count" value="${count+1}" />
								</c:forEach>
					    	 </ul>
					    </div>
					  </div>
					  <div class="btnBox">
							  	<button type="button" class="btn btn-success" onclick="changeShow()" id="bj">编辑</button>
							  </div>
					  </form>
				  </div>
				  <div class="planListBox">
				  	 <h3 class="bigTit">我的产出计划</h3>
				  	 <div class="planListCon">
				  	 	<div class="planShowBox" >
				  	 		<div class="planList">
				  	 			<c:forEach items="${outplans}" var="outplanVo">
				  	 					<div class="d-planList">
											  <form action="">
												  <div class="form-group">
												    <label for="" class="col-sm-2 control-label labelFont">类别</label>
												    <div class="col-sm-10">
												    	 <p class="form-control-static outputDate"><span>${outplanVo.businessGoods.goodsName}</span></p>
												    </div>
												  </div>
												  <div class="form-group">
												    <label for="" class="col-sm-2 control-label labelFont">产出日期</label>
												    <div class="col-sm-10">
												    	 <p class="form-control-static outputDate"><span><fmt:formatDate value="${outplanVo.outPlan.startTime}" pattern="yyyy-MM-dd " ></fmt:formatDate></span>至<span><fmt:formatDate value="${outplanVo.outPlan.endTime }" pattern="yyyy-MM-dd " ></fmt:formatDate></span></p>
												    </div>
												  </div>
												  <div class="form-group">
												    <label for="" class="col-sm-2 control-label labelFont">产出重量</label>
												    <div class="col-sm-10">
												    	 <p class="form-control-static outputDate"><span>${outplanVo.outPlan.output}${outplanVo.businessWeight.weightName}</span></p>
												    </div>
												  </div>
												   <div class="form-group">
												    <label for="" class="col-sm-2 control-label labelFont">审核状态</label>
												    <div class="col-sm-10">
														    	 <c:if test="${outplanVo.outPlan.examine==0}">
														    		<p class="form-control-static outputDate"><span>审核中</span></p>
														    	 </c:if>
														    	 <c:if test="${outplanVo.outPlan.examine==1}">
														    		<p class="form-control-static outputDate"><span>审核通过</span></p>
														    	 </c:if>
														    	 <c:if test="${outplanVo.outPlan.examine==2}">
															    	<p class="form-control-static outputDate"><span>审核拒绝</span></p>
																 </c:if>
												    </div>
												    <c:if test="${outplanVo.outPlan.examine==2}">
													    <label for="" class="col-sm-2 control-label labelFont">拒绝原因</label>
													    <div class="col-sm-10">
												    		<p class="form-control-static outputDate"><span>${outplanVo.outPlan.remarks}</span></p>
														</div>
													</c:if>
												  </div>
											  </form>
											  <div class="btnBox">
												<button type="button" onclick="editPlan(${outplanVo.outPlan.id})" class="btn btn-success">编辑</button>
												<button type="button" onclick="deletePlan(${outplanVo.outPlan.id})" class="btn btn-default">删除</button>
											</div>
										</div>
										</c:forEach>
							  	</div>
						  </div>
					  </div>
				  </div>
		</div>
	</div>
	<div id="gallery" class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="pswp__bg"></div>
        <div class="pswp__scroll-wrap">
          <div class="pswp__container">
			<div class="pswp__item"></div>
			<div class="pswp__item"></div>
			<div class="pswp__item"></div>
          </div>
          <div class="pswp__ui pswp__ui--hidden">
            <div class="pswp__top-bar">

				<div class="pswp__counter"></div>
				<button class="pswp__button pswp__button--close" title="Close (Esc)"></button>

				<div class="pswp__preloader">
					<div class="pswp__preloader__icn">
					  <div class="pswp__preloader__cut">
					    <div class="pswp__preloader__donut"></div>
					  </div>
					</div>
				</div>
            </div>
			<!-- <div class="pswp__loading-indicator"><div class="pswp__loading-indicator__line"></div></div> -->
            <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
	            <div class="pswp__share-tooltip">
	            </div>
	        </div>
            <button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)"></button>
            <button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)"></button>
            <div class="pswp__caption">
              <div class="pswp__caption__center">
              </div>
            </div>
          </div>
        </div>
	</div>
    <script src="<%=path %>/js/viewPhoto.js"></script>
</body>
</html>
