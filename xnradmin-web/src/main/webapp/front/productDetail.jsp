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
function plusNum()
{
		var index = $(".item_quantity").val();
		index++;
		$(".item_quantity").val(index);
	}
function minusNum()
{
		var index = $(".item_quantity").val();
		index--;
		if(index>=1)
			{
				$(".item_quantity").val(index);
			}else
				{
				$(".item_quantity").val(1);
				}
	}
</script>
</head>
<body>
	<div class="logoWrap">
		<div class="container">
			<div class="logo">
				<a href="index.html">
					<h1>
						<img src="${basePath }images/front/login_logo.png" />
					</h1>
				</a>
			</div>
			<div class="pull-left searchBox">
				<form action="" class="form-inline">
					<input type="text" placeholder="请输入搜索内容" class="searchInput" /> <input
						type="button" value="搜索" class="searchBtn">
				</form>
			</div>
			<div class="pull-right cart box_1">
				<a href="checkout.html">
					<h3>
						<div class="total">
							<span class="glyphicon glyphicon-shopping-cart"
								aria-hidden="true"></span> <span class="simpleCart_total"></span>
							(<span id="simpleCart_quantity" class="simpleCart_quantity"></span>)
						</div>
					</h3>
				</a>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="guiderBox">
		<div class="container">
			<ul class="memenu skyblue pull-left">
				<li class="active"><a href="index.html">首页</a></li>
				<li class="grid"><a href="#">蔬菜水果</a>
					<div class="mepanel">
						<div class="row">
							<div class="col1 me-one">
								<h4>蔬菜类</h4>
								<ul>
									<li><a href="product.html">有机蔬菜</a></li>
									<li><a href="product.html">地方特产蔬菜</a></li>
									<li><a href="product.html">自产蔬菜</a></li>
								</ul>
							</div>
							<div class="col1 me-one">
								<h4>水果类</h4>
								<ul>
									<li><a href="product.html">有机水果</a></li>
									<li><a href="product.html">进口水果</a></li>
									<li><a href="product.html">国产水果</a></li>
								</ul>
							</div>
						</div>
					</div></li>
				<li class="grid"><a href="#">肉类禽蛋</a>
					<div class="mepanel">
						<div class="row">
							<div class="col1 me-one">
								<h4>牛羊肉</h4>
								<ul>
									<li><a href="product.html">有机牛羊肉</a></li>
									<li><a href="product.html">进口牛羊肉</a></li>
									<li><a href="product.html">精品牛羊肉</a></li>
								</ul>
							</div>
							<div class="col1 me-one">
								<h4>猪肉</h4>
								<ul>
									<li><a href="product.html">有机猪肉</a></li>
									<li><a href="product.html">进口猪肉</a></li>
									<li><a href="product.html">国产猪肉</a></li>
									<li><a href="product.html">农场自养猪肉</a></li>
								</ul>
							</div>
							<div class="col1 me-one">
								<h4>禽类</h4>
								<ul>
									<li><a href="product.html">有机禽类</a></li>
									<li><a href="product.html">散养禽类</a></li>
								</ul>
							</div>
							<div class="col1 me-one">
								<h4>蛋类</h4>
								<ul>
									<li><a href="product.html">有机蛋</a></li>
									<li><a href="product.html">散养蛋</a></li>
								</ul>
							</div>
						</div>
					</div></li>
				<li class="grid"><a href="#">粮油副食</a></li>
				<li class="grid"><a href="#contact">关于我们</a></li>
			</ul>
		</div>
	</div>
	<!--head//-->
	<div class="single-sec">
		<div class="container">
			<ol class="breadcrumb">
				<li><a href="/front/index.action">首页</a></li>
				<li class="">${productDetailVo.firstName }</li>
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
								<p class="priceBox">
									优惠价：<span class="active">${businessGoodsVO.businessGoods.goodsOriginalPrice}/${businessGoodsVO.businessWeight.weightName}</span>
								</p>
								<div class="comment-count">
									<p class="comment">已售数量</p>
									<a class="" href="#comment">${businessGoodsVO.businessGoods.goodsSoldCount}</a>
								</div>
							</div>
							<div class="p-detailCon">
								<div class="sendAdd msgList">
									<p class="priceBox">
										配送至： <span> <select>
												<option>北京</option>
												<option>上海</option>
										</select>
										</span>
									</p>
								</div>
								<p class="msgList">
									发货地：<span>北京</span>
								</p>
								<div class="addCart-box">
									<div class="addNum">
										<span><input type="text" class="item_quantity"
											value="1"></span> <span> <a href="javascript:plusNum()" class="plusNum">+</a>
											<a href="javascript:minusNum()" class="minus-Num">-</a>
										</span>
									</div>
									<span class="addCart"> <a href="#">加入购物车</a>
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
									品牌：<span>怀山堂</span>
								</p>
								<p>
									规格：<span>1.5kg/箱</span>
								</p>
								<p>
									产地<span>焦作</span>
								</p>
								<p>
									当前存储温度<span> 0-4度</span>
								</p>
								<p>
									建议储藏方法：<span>常温通风处，长期存储需冷藏</span>
								</p>
							</div>
							<div class="detailImg">
								<img src="${basePath }images/front/products/1.jpg" /> <img
									src="${basePath }images/front/products/2.jpg" /> <img
									src="${basePath }images/front/products/3.jpg" /> <img
									src="${basePath }images/front/products/4.jpg" /> <img
									src="${basePath }images/front/products/5.jpg" /> <img
									src="${basePath }images/front/products/6.jpg" /> <img
									src="${basePath }images/front/products/7.jpg" /> <img
									src="${basePath }images/front/products/8.jpg" /> <img
									src="${basePath }images/front/products/9.jpg" />
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
							
							<a href="#"><span class="glyphicon glyphicon-chevron-right"
								aria-hidden="true"></span><span>${rcv.goodsName }</span>
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