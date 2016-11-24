<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="header.jsp"%>
<script type="text/javascript">
	$(function(){
		$('#editMsg').click(function(){
			$('#myModal').modal({
		   backdrop: "static"
		})
		})
		$('#addAddressBtn').click(function(){
			$('#newAddressBox').modal({
		   backdrop: "static"
		})
		})
		
		
	})
</script>
</head>
<body> 
<div class="single-sec">
	 <div class="container">
		 <ol class="breadcrumb">
				  <li><a href="index.html">首页</a></li>
				  <li class="">个人中心</li>
		 </ol>
		 <div class="personalBox">
		 	<div class="pSliderBar pull-left">
		 		<div class="userInfoBox">
                  <span class="personalImg"><img src="${basePath }images/front/head.jpg" ></span>
                  <div class="perInfoBox">
                   <p><strong>账号：</strong>${user.userName }</p>
                  </div>
                </div>
                <ul class="pSlideNavUl">
                    <li class="active"><a href="#">我的订单</a></li> 
                    <li><a href="/page/front/userInformation.action?userId=3">账号信息</a></li> 
                </ul>
		 	</div>
			<div class="p-contentBox pull-left">
				<div class="p-orderList" id="p-orderList">
                    <div class="orderListBox">
                    	<div class="orderTitCol">
                    		<ul>
                    			<li class="ordercol-d">订单详情</li>
                    			<li>收货人</li>
                    			<li>金额</li>
                    			<li>状态</li>
                    			<li>操作</li>
                    		</ul>
                    	</div>
                    </div>
                     <div class="orderList">
                    	<div class="orderTit">
	                          订单号：<span>2016010223</span>
                    		<span class="orderTime">
	                          	2016-10-12
	                        </span>
                    	</div>
                    	<div class="orderTitCol">
                    		<ul>
                    			<li class="ordercol-d">
                    				<a href="#"><img src="${basePath }images/front/products/sc-img1.jpg"></a>
	  	                          	 <div class="orderCon">
	  			                          <h3><a href="#">有机怀山堂铁棍山药（垆土） 1.5kg/箱 长度为38cm 左右</a></h3>
	  		                          </div>
                    			</li>
                    			<li>
                    				<span>张三</span>
                    			</li>
                    			<li>
			                          <span class="orderMoney">总额：<em>￥200</em></span>
			                          <p>微信支付</p>
			                    </li>
                    			<li><span>已完成 </span></li>
                    			<li><span><a href="#">再次购买</a></span></li>
                    		</ul>
                    	</div>
                    </div>
                    <div class="orderList">
                    	<div class="orderTit">
	                          订单号：<span>2016010223</span>
                    		<span class="orderTime">
	                          	2016-10-12
	                        </span>
                    	</div>
                    	<div class="orderTitCol">
                    		<ul>
                    			<li class="ordercol-d">
                    				<a href="#"><img src="${basePath }images/front/products/sc-img1.jpg"></a>
	  	                          	 <div class="orderCon">
	  			                          <h3><a href="#">有机怀山堂铁棍山药（垆土） 1.5kg/箱 长度为38cm 左右</a></h3>
	  		                          </div>
                    			</li>
                    			<li>
                    				<span>张三</span>
                    			</li>
                    			<li>
			                          <span class="orderMoney">总额：<em>￥200</em></span>
			                          <p>微信支付</p>
			                    </li>
                    			<li><span>已完成 </span></li>
                    			<li><span><a href="#">再次购买</a></span></li>
                    		</ul>
                    	</div>
                    </div>
                    <div class="orderList">
                    	<div class="orderTit">
	                          订单号：<span>2016010223</span>
                    		<span class="orderTime">
	                          	2016-10-12
	                        </span>
                    	</div>
                    	<div class="orderTitCol">
                    		<ul>
                    			<li class="ordercol-d">
                    				<a href="#"><img src="${basePath }images/front/products/sc-img1.jpg"></a>
	  	                          	 <div class="orderCon">
	  			                          <h3><a href="#">有机怀山堂铁棍山药（垆土） 1.5kg/箱 长度为38cm 左右</a></h3>
	  		                          </div>
                    			</li>
                    			<li>
                    				<span>张三</span>
                    			</li>
                    			<li>
			                          <span class="orderMoney">总额：<em>￥200</em></span>
			                          <p>微信支付</p>
			                    </li>
                    			<li><span>已完成 </span></li>
                    			<li><span><a href="#">再次购买</a></span></li>
                    		</ul>
                    	</div>
                    </div>
                </div>
                <!--分页-->
                <nav class="text-center">
				      <ul class="pagination">
				        <li class="disabled"><a href="#">«</a></li>
				        <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
				        <li><a href="#">2</a></li>
				        <li><a href="#">3</a></li>
				        <li><a href="#">4</a></li>
				        <li><a href="#">5</a></li>
				        <li><a href="#">»</a></li>
				     </ul>
   				</nav>
				<!--分页end-->
                </div>
                <div class="p-orderList editList">
					<h3>地址管理</h3>
                	<a href="javascript:;" class="btn btn-default add-address" id="addAddressBtn">新增收货地址</a>
                	<div class="addressBox">
                		<div class="addressList">
							<form class="form-horizontal" role="form">
							  <div class="form-group">
							    <label class="col-sm-2 control-label">收货人：</label>
							    <p class="form-control-static">张某某</p>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">地址：</label>
							    <p class="form-control-static">山东省日照某某小区</p>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">电话：</label>
							    <p class="form-control-static">13601023443</p>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">电子邮箱：</label>
							    <p class="form-control-static">13601023443</p>
							  </div>
							  <div class="editBox">
							  	 <button type="submit" class="btn btn-default">修改</button>
							  	 <button type="submit" class="btn btn-default">设为默认</button>
							  </div>
							</form>
                		</div>
                		<div class="addressList">
							<form class="form-horizontal" role="form">
							  <div class="form-group">
							    <label class="col-sm-2 control-label">收货人：</label>
							    <p class="form-control-static">张某某</p>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">地址：</label>
							    <p class="form-control-static">山东省日照某某小区</p>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">电话：</label>
							    <p class="form-control-static">13601023443</p>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">电子邮箱：</label>
							    <p class="form-control-static">13601023443</p>
							  </div>
							  <div class="editBox">
							  	 <button type="submit" class="btn btn-default" id="editMsg">修改</button>
							  	 <button type="submit" class="btn btn-default">设为默认</button>
							  </div>
							</form>
                		</div>
                	</div>
                </div>
                 <div class="p-orderList editList">
                 		<h3>密码修改</h3>
                		<form class="form-horizontal" id="submitForm" action="savePassword.action">
                				<input type="hidden" name="user.id" value="${user.id }">
                                  <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">原始密码：</label>
                                    <div class="col-sm-10">
                                      <input onchange="yzPassword()" type="password" id="yuanshimima" name="yuanshimima" class="form-control" id="" value="">
                                      <span id="check_yz" style="color: red;font-size: 10px;display: none;">密码错误</span>
                                    </div>
                                  </div>
                                  <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">新密码：</label>
                                    <div class="col-sm-10">
                                      <input type="password" class="form-control" id="password" name="user.password" value="" onblur="checkPassword()">
                                      <p class="errorTips" id="check_passwordError"
											style="display: none">
											<span class="glyphicon glyphicon-remove-sign errorIcon"></span>
											<span id="check_password" style="display: none"></span>
										</p>
                                    </div>
                                  </div>
                                  <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">确认密码：</label>
                                    <div class="col-sm-10">
                                      <input type="password" class="form-control" id="confirmPassword" value="" onblur="checkConfirmPassword()">
                                      <p class="errorTips" id="check_confirmPasswordError"
										style="display: none">
										<span class="glyphicon glyphicon-remove-sign errorIcon"></span>
										<span id="check_confirmPassword" style="display: none"></span>
									</p>
                                    </div>
                                  </div>
                                  <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                      <button type="button" onclick="save()" class="btn btn-default">保存修改</button>
                                    </div>
                                  </div>
                        </form>
                 </div>
			</div>
		 </div>
	  </div>	 
</div>
<%@include file="footer.jsp"%>	
<!-- FlexSlider -->		
<script type="text/javascript" src="${basePath }js/front/imagezoom.js"></script>
<script defer src="${basePath }js/front/jquery.flexslider.js"></script>
<script type="text/javascript" src="${basePath }js/front/jquery.flexisel.js"></script>
<script>
// Can also be used with $(document).ready()
$(window).load(function() {
$('.flexslider').flexslider({
animation: "slide",
controlNav: "thumbnails"
});
});
</script>
 <script type="text/javascript">
    (function() {

		var initPhotoSwipeFromDOM = function(gallerySelector) {

			var parseThumbnailElements = function(el) {
			    var thumbElements = el.childNodes,
			        numNodes = thumbElements.length,
			        items = [],
			        el,
			        childElements,
			        thumbnailEl,
			        size,
			        item;

			    for(var i = 0; i < numNodes; i++) {
			        el = thumbElements[i];

			        // include only element nodes 
			        if(el.nodeType !== 1) {
			          continue;
			        }

			        childElements = el.children;

			        size = el.getAttribute('data-size').split('x');

			        // create slide object
			        item = {
						src: el.getAttribute('href'),
						w: parseInt(size[0], 10),
						h: parseInt(size[1], 10),
						author: el.getAttribute('data-author')
			        };

			        item.el = el; // save link to element for getThumbBoundsFn

			        if(childElements.length > 0) {
			          item.msrc = childElements[0].getAttribute('src'); // thumbnail url
			          if(childElements.length > 1) {
			              item.title = childElements[1].innerHTML; // caption (contents of figure)
			          }
			        }


					var mediumSrc = el.getAttribute('data-med');
		          	if(mediumSrc) {
		            	size = el.getAttribute('data-med-size').split('x');
		            	// "medium-sized" image
		            	item.m = {
		              		src: mediumSrc,
		              		w: parseInt(size[0], 10),
		              		h: parseInt(size[1], 10)
		            	};
		          	}
		          	// original image
		          	item.o = {
		          		src: item.src,
		          		w: item.w,
		          		h: item.h
		          	};

			        items.push(item);
			    }

			    return items;
			};

			// find nearest parent element
			var closest = function closest(el, fn) {
			    return el && ( fn(el) ? el : closest(el.parentNode, fn) );
			};

			var onThumbnailsClick = function(e) {
			    e = e || window.event;
			    e.preventDefault ? e.preventDefault() : e.returnValue = false;

			    var eTarget = e.target || e.srcElement;

			    var clickedListItem = closest(eTarget, function(el) {
			        return el.tagName === 'A';
			    });

			    if(!clickedListItem) {
			        return;
			    }

			    var clickedGallery = clickedListItem.parentNode;

			    var childNodes = clickedListItem.parentNode.childNodes,
			        numChildNodes = childNodes.length,
			        nodeIndex = 0,
			        index;

			    for (var i = 0; i < numChildNodes; i++) {
			        if(childNodes[i].nodeType !== 1) { 
			            continue; 
			        }

			        if(childNodes[i] === clickedListItem) {
			            index = nodeIndex;
			            break;
			        }
			        nodeIndex++;
			    }

			    if(index >= 0) {
			        openPhotoSwipe( index, clickedGallery );
			    }
			    return false;
			};

			var photoswipeParseHash = function() {
				var hash = window.location.hash.substring(1),
			    params = {};

			    if(hash.length < 5) { // pid=1
			        return params;
			    }

			    var vars = hash.split('&');
			    for (var i = 0; i < vars.length; i++) {
			        if(!vars[i]) {
			            continue;
			        }
			        var pair = vars[i].split('=');  
			        if(pair.length < 2) {
			            continue;
			        }           
			        params[pair[0]] = pair[1];
			    }

			    if(params.gid) {
			    	params.gid = parseInt(params.gid, 10);
			    }

			    return params;
			};

			var openPhotoSwipe = function(index, galleryElement, disableAnimation, fromURL) {
			    var pswpElement = document.querySelectorAll('.pswp')[0],
			        gallery,
			        options,
			        items;

				items = parseThumbnailElements(galleryElement);

			    // define options (if needed)
			    options = {

			        galleryUID: galleryElement.getAttribute('data-pswp-uid'),

			        getThumbBoundsFn: function(index) {
			            // See Options->getThumbBoundsFn section of docs for more info
			            var thumbnail = items[index].el.children[0],
			                pageYScroll = window.pageYOffset || document.documentElement.scrollTop,
			                rect = thumbnail.getBoundingClientRect(); 

			            return {x:rect.left, y:rect.top + pageYScroll, w:rect.width};
			        },

			        addCaptionHTMLFn: function(item, captionEl, isFake) {
						if(!item.title) {
							captionEl.children[0].innerText = '';
							return false;
						}
						captionEl.children[0].innerHTML = item.title +  '<br/><small>Photo: ' + item.author + '</small>';
						return true;
			        },
					
			    };


			    if(fromURL) {
			    	if(options.galleryPIDs) {
			    		// parse real index when custom PIDs are used 
			    		// http://photoswipe.com/documentation/faq.html#custom-pid-in-url
			    		for(var j = 0; j < items.length; j++) {
			    			if(items[j].pid == index) {
			    				options.index = j;
			    				break;
			    			}
			    		}
				    } else {
				    	options.index = parseInt(index, 10) - 1;
				    }
			    } else {
			    	options.index = parseInt(index, 10);
			    }

			    // exit if index not found
			    if( isNaN(options.index) ) {
			    	return;
			    }



				var radios = document.getElementsByName('gallery-style');
				for (var i = 0, length = radios.length; i < length; i++) {
				    if (radios[i].checked) {
				        if(radios[i].id == 'radio-all-controls') {

				        } else if(radios[i].id == 'radio-minimal-black') {
				        	options.mainClass = 'pswp--minimal--dark';
					        options.barsSize = {top:0,bottom:0};
							options.captionEl = false;
							options.fullscreenEl = false;
							options.shareEl = false;
							options.bgOpacity = 0.85;
							options.tapToClose = true;
							options.tapToToggleControls = false;
				        }
				        break;
				    }
				}

			    if(disableAnimation) {
			        options.showAnimationDuration = 0;
			    }

			    // Pass data to PhotoSwipe and initialize it
			    gallery = new PhotoSwipe( pswpElement, PhotoSwipeUI_Default, items, options);

			    // see: http://photoswipe.com/documentation/responsive-images.html
				var realViewportWidth,
				    useLargeImages = false,
				    firstResize = true,
				    imageSrcWillChange;

				gallery.listen('beforeResize', function() {

					var dpiRatio = window.devicePixelRatio ? window.devicePixelRatio : 1;
					dpiRatio = Math.min(dpiRatio, 2.5);
				    realViewportWidth = gallery.viewportSize.x * dpiRatio;


				    if(realViewportWidth >= 1200 || (!gallery.likelyTouchDevice && realViewportWidth > 800) || screen.width > 1200 ) {
				    	if(!useLargeImages) {
				    		useLargeImages = true;
				        	imageSrcWillChange = true;
				    	}
				        
				    } else {
				    	if(useLargeImages) {
				    		useLargeImages = false;
				        	imageSrcWillChange = true;
				    	}
				    }

				    if(imageSrcWillChange && !firstResize) {
				        gallery.invalidateCurrItems();
				    }

				    if(firstResize) {
				        firstResize = false;
				    }

				    imageSrcWillChange = false;

				});

				gallery.listen('gettingData', function(index, item) {
				    if( useLargeImages ) {
				        item.src = item.o.src;
				        item.w = item.o.w;
				        item.h = item.o.h;
				    } else {
				        item.src = item.m.src;
				        item.w = item.m.w;
				        item.h = item.m.h;
				    }
				});

			    gallery.init();
			};

			// select all gallery elements
			var galleryElements = document.querySelectorAll( gallerySelector );
			for(var i = 0, l = galleryElements.length; i < l; i++) {
				galleryElements[i].setAttribute('data-pswp-uid', i+1);
				galleryElements[i].onclick = onThumbnailsClick;
			}

			// Parse URL and open gallery if it contains #&pid=3&gid=1
			var hashData = photoswipeParseHash();
			if(hashData.pid && hashData.gid) {
				openPhotoSwipe( hashData.pid,  galleryElements[ hashData.gid - 1 ], true, true );
			}
		};

		initPhotoSwipeFromDOM('.demo-gallery');

	})();

	</script>
</body>
</html>
