<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
<%@include file="header.jsp"%>
<!-- /start menu -->
<script type="text/javascript">
var productMaxNum=0;
var simpleCart_numbertmp =0;
$(function(){
	//初始化购买数量
	var productMaxNumtmp = '${goodsAllocationShow.surplusCount}';
	if(productMaxNumtmp!=''){
		productMaxNum=productMaxNumtmp;
	}
	if($("#userId").val()!=null||$("#userId").val()!="")
		{
			$.ajax({
				url:"<%=basePath%>/front/receiptAddress/getDistribution.action",
				type:"POST",
				data:{"userId":$("#userId").val(),"_":new Date().getTime()},
				dataType:"json",
				success:function(data){
					if(data.findSuccess==0)
						{
							if(data.addr.province=="北京市")
								{
									$("#distribution option[value=bj]").attr("selected","true");
								}else if(data.addr.province=="山东省")
									{
										$("#distribution option[value=sd]").attr("selected","true");
									}else if(data.addr.province=="河北省")
										{
											$("#distribution option[value=hb]").attr("selected","true");
										}
						}
				}
			});
		}
})
function plusNum()
{
		var index = $(".item_quantity").val();
		index++;
		if(index>productMaxNum){
			layer.msg("库存有限，该产品购买量不能超过"+productMaxNum);
			index--;
		}else{
			$(".item_quantity").val(index);
		}
	}
function minusNum()
{
		var index = $(".item_quantity").val();
		index--;
		if(index>productMaxNum){
			layer.msg("库存有限，该产品购买量不能超过"+productMaxNum);
			index++;
		}else{
			if(index>=1)
				{
					$(".item_quantity").val(index);
				}else
					{
					$(".item_quantity").val(1);
					}
		}
	}
function addToCart(id,money){
	var userId = $("#userId").val()
	var goodsNumber = $("#goodsNumber").val();
	//购物车里面的数量
	simpleCart_numbertmp = (Number($("#simpleCart_number").html())+Number(goodsNumber));
	if(simpleCart_numbertmp>productMaxNum){
		layer.msg("库存有限，该产品购买量不能超过"+productMaxNum);
	}else{	
		$("#simpleCart_total").html((Number($("#simpleCart_total").html())+money*Number(goodsNumber)).toFixed(1));
		$("#simpleCart_number").html((Number($("#simpleCart_number").html())+Number(goodsNumber)));
			
		if(userId!=null&&userId!=""){
			$.ajax({
				type:"POST", 
				url:"<%=basePath%>/front/shopingCart/add.action",
				data:{"goodsId":id,"goodsCount":goodsNumber,"clientUserId":userId,_:new Date().getTime()},
				dataType:"json",
				success:function(msg){
					layer.msg("加入成功");
					}
				});
		}else
			{
				//layer.msg("请先登录");
				//setTimeout("window.location.href='<%=basePath%>/front/login.jsp'",1000);
			var cart = getCartCookie();
			
			var item=new Object();
			item.cookieId = getUuid();
			item.goodsId = id;
			item.goodsCount = goodsNumber;
			item.price = Number($("#price"+id).val());
			cart.push(item);
			$.cookie('cart', JSON.stringify(cart), { expires: 7, path: '/' }); 
			layer.msg("加入成功");
			}
	}
		
	
}
function getCartCookie(){
	var cartCookie = $.cookie('cart')//拿到cookie
	if(cartCookie==null||cartCookie==""){
		var cartCookie = [];
		return cartCookie;
	}
	return JSON.parse(cartCookie); 
}
function getUuid(){
	  var len=32;//32长度
	  var radix=16;//16进制
	  var chars='0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');var uuid=[],i;radix=radix||chars.length;if(len){for(i=0;i<len;i++)uuid[i]=chars[0|Math.random()*radix];}else{var r;uuid[8]=uuid[13]=uuid[18]=uuid[23]='-';uuid[14]='4';for(i=0;i<36;i++){if(!uuid[i]){r=0|Math.random()*16;uuid[i]=chars[(i==19)?(r&0x3)|0x8:r];}}}
	  return uuid.join('');
}
</script>
</head>
<body>
	</div>
	<!--head//-->
	<div class="single-sec">
		<div class="container">
			<ol class="breadcrumb">
				<li><a href="<%=basePath%>/front/index.action">首页</a></li>
				<li class="">${productDetailVo.firstName }</li>
<%-- 				/front/product.action?productCategoryId=${productDetailVo.firstClassification}&&first=${productDetailVo.firstName}&&three=${productDetailVo.secoundName} --%>
				<li class="">${productDetailVo.secoundName }</li>
				<li class="">${productDetailVo.foodName }</li>
			</ol>
			<!-- start content -->
			<div class="col-md-9 det">
				<div class="productsLeftBox">
					<div class="single_left">
						<div class="flexslider">
							<ul class="slides">
								<li data-thumb="${basePath }${businessGoodsVO.businessGoods.goodsBigLogo}">
									<div class="thumb-image">
										<img src="${basePath }${businessGoodsVO.businessGoods.goodsLogo}" data-imagezoom="true"
											class="img-responsive">
									</div>
								</li>
<%-- 								<li data-thumb="${basePath }images/front/products/sc-img2.jpg"> --%>
<!-- 									<div class="thumb-image"> -->
<%-- 										<img src="${basePath }images/front/products/sc-img1.jpg" data-imagezoom="true" --%>
<!-- 											class="img-responsive"> -->
<!-- 									</div> -->
<!-- 								</li> -->
<%-- 								<li data-thumb="${basePath }images/front/products/sc-img3.jpg"> --%>
<!-- 									<div class="thumb-image"> -->
<%-- 										<img src="${basePath }images/front/products/sc-img3.jpg" data-imagezoom="true" --%>
<!-- 											class="img-responsive"> -->
<!-- 									</div> -->
<!-- 								</li> -->
							</ul>
						</div>
					</div>
					<div class="single-right">
						<h3>${businessGoodsVO.businessGoods.goodsName}</h3>
<!-- 						<div class="orangeTit"></div> -->
						<div class="cost">
							<div class="prdt-cost">
							<input id="price${businessGoodsVO.businessGoods.id}" type="hidden" value="${businessGoodsVO.businessGoods.goodsOriginalPrice}"/>
								<p class="priceBox">
									优惠价：<span class="active">${businessGoodsVO.businessGoods.goodsOriginalPrice}/${businessGoodsVO.businessWeight.weightName}</span>
								</p>
								<div class="comment-count">
									<p class="comment">已售数量</p>
									<a class="" href="#comment"><c:if test="${!empty businessGoodsVO.businessGoods.goodsSoldCount}">
									${businessGoodsVO.businessGoods.goodsSoldCount}
									</c:if>
									<c:if test="${empty businessGoodsVO.businessGoods.goodsSoldCount}">
									200
									</c:if></a>
								</div>
							</div>
							<div class="p-detailCon">
								<div class="sendAdd msgList">
									<p class="priceBox">
										配送至： <span> 
										<select id="distribution">
												<option value="jn">济南</option>
												<option value="bj">北京市</option>
												<option value="hb">河北省</option>
												<option value="sd">山东省</option>
										</select>
										</span>
									</p>
								</div>
								<p class="msgList">
									发货地：<span>济南</span>
								</p>
								<div class="addCart-box">
									<div class="addNum">
										<span><input type="text" class="item_quantity"
											value="1" id="goodsNumber"></span> <span> <a href="javascript:plusNum()" class="plusNum">+</a>
											<a href="javascript:minusNum()" class="minus-Num">-</a>
										</span>
									</div>
									<span class="addCart"> <a href="javascript:addToCart('${businessGoodsVO.businessGoods.id}','${businessGoodsVO.businessGoods.goodsOriginalPrice}')">购物车</a>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="productInstro">
					<div class="instroTabBox">
						<ul class="instroTab">
							<li class="cur">商品详情</li>
<!-- 							<li>购买评论</li> -->
						</ul>
					</div>
					<div class="productDetailCon">
						<div class="p-detailcon detailBox" style="display: block">
							<div class="detailInfo_item">
								<p>
									名称：<span>${businessGoodsVO.businessGoods.goodsName}</span>
								</p>
								<p>
									价格：<span>${businessGoodsVO.businessGoods.goodsOriginalPrice}/${businessGoodsVO.businessWeight.weightName}</span>
								</p>
								<p>
									规格<span> 约${businessGoodsVO.businessGoods.goodsWeight }g</span>
								</p>
								<p>
									已售数量<span>
									<c:if test="${!empty businessGoodsVO.businessGoods.goodsSoldCount}">
									${businessGoodsVO.businessGoods.goodsSoldCount}
									</c:if>
									<c:if test="${empty businessGoodsVO.businessGoods.goodsSoldCount}">
									200
									</c:if>
									</span>
								</p>
								<p>
									发货地<span> 北京</span>
								</p>
								<p>
									建议储藏方法：<span>常温通风处，长期存储需冷藏</span>
								</p>
							</div>
							<div class="detailImg">
								${businessGoodsVO.businessGoods.goodsDescription}
								<%-- <img src="${basePath }images/front/products/1.jpg" /> <img
									src="${basePath }images/front/products/2.jpg" /> <img
									src="${basePath }images/front/products/3.jpg" /> <img
									src="${basePath }images/front/products/4.jpg" /> <img
									src="${basePath }images/front/products/5.jpg" /> <img
									src="${basePath }images/front/products/6.jpg" /> <img
									src="${basePath }images/front/products/7.jpg" /> <img
									src="${basePath }images/front/products/8.jpg" /> <img
									src="${basePath }images/front/products/9.jpg" /> --%>
							</div>
						</div>
<!-- 						<div class="p-commentBox detailBox"> -->
<!-- 							<div class="comentBoxTab"> -->
<!-- 								<ul> -->
<!-- 									<li class="cur">全部评论(1126)</li> -->
<!-- 									<li>好评(26)</li> -->
<!-- 									<li>中评(116)</li> -->
<!-- 									<li>差评(126)</li> -->
<!-- 								</ul> -->
<!-- 							</div> -->
<!-- 							<div class="commentBox"> -->
<!-- 								<div class="AllComment commentCon"> -->
<!-- 									<ul> -->
<!-- 										<li> -->
<!-- 											<div class="commentLeft pull-left"> -->
<!-- 												<div class="p-comment">商品不错，香脆可口，价格优惠。</div> -->
<!-- 												<div class="comment-operate"> -->
<!-- 													<a class="nice J-nice" href="" title="">点赞（0）</a> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="commentRight pull-right"> -->
<!-- 												<div class="user-item"> -->
<%-- 													<img src="${basePath }images/front/userImg.jpg" /> <span>小***元</span> --%>
<!-- 													<p>金牌会员</p> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</li> -->
<!-- 										<li> -->
<!-- 											<div class="commentLeft pull-left"> -->
<!-- 												<div class="p-comment">商品不错，香脆可口，价格优惠。</div> -->
<!-- 												<div class="comment-operate"> -->
<!-- 													<a class="nice J-nice" href="" title="">点赞（0）</a> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="commentRight pull-right"> -->
<!-- 												<div class="user-item"> -->
<%-- 													<img src="${basePath }images/front/userImg.jpg" /> <span>小***元</span> --%>
<!-- 													<p>金牌会员</p> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</li> -->
<!-- 										<li> -->
<!-- 											<div class="commentLeft pull-left"> -->
<!-- 												<div class="p-comment">商品不错，香脆可口，价格优惠。</div> -->
<!-- 												<div class="comment-operate"> -->
<!-- 													<a class="nice J-nice" href="" title="">点赞（0）</a> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="commentRight pull-right"> -->
<!-- 												<div class="user-item"> -->
<%-- 													<img src="${basePath }images/front/userImg.jpg" /> <span>小***元</span> --%>
<!-- 													<p>金牌会员</p> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</li> -->
<!-- 									</ul> -->
<!-- 								</div> -->
<!-- 								<div class="positiveComment commentCon"></div> -->
<!-- 								<div class="moderateComment commentCon"></div> -->
<!-- 								<div class="negativeComment commentCon"></div> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
				</div>
			</div>
			<div class="rsidebar span_1_of_left">
				<section class="sky-form">
				<div class="product_right">
					<h4 class="m_2">相关分类</h4>
					<div class="tab1">
					<c:forEach items="${related_classification}" var="rc">
						<ul class="place">
							<li class="sort">${rc.key}</li>
							<li class="by"><img src="${basePath }images/front/do.png" alt=""></li>
							<div class="clearfix"></div>
						</ul>
						<div class="single-bottom">
						<c:forEach items="${rc.value}" var="rcv">
							
							<a href="<%=basePath%>/front/productDetail.action?goodsId=${rcv[0].id}"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span><span>${rcv[0].goodsName }</span></a>
						</c:forEach>
						</div>
					</c:forEach>
					</div>
				</section>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!-- FlexSlider -->
	<script type="text/javascript" src="${basePath }js/front/imagezoom.js"></script>
	<script defer src="${basePath}js/front/jquery.flexslider.js"></script>
	<script type="text/javascript" src="${basePath }js/front/jquery.flexisel.js"></script>
	<script>
		// Can also be used with $(document).ready()
		$(window).load(function() {
			$('.flexslider').flexslider({
				animation : "slide",
				controlNav : "thumbnails"
			});
		});
	</script>
	<%@include file="footer.jsp"%>
</body>
</html>
