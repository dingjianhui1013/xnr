$(function(){
	$(".memenu").memenu();
	$('.sortTab1 li').hover(function(){
		 _index=$(this).index();
		 $(this).addClass('cur').siblings().removeClass('cur');
         $('.sortListBox1 div.sortListCon').eq(_index).show().siblings().hide();
	})
	$('.sortTab2 li').hover(function(){
		 _index=$(this).index();
		 $(this).addClass('cur').siblings().removeClass('cur');
         $('.sortListBox2 div.sortListCon').eq(_index).show().siblings().hide();
	})
	$('.instroTab li').click(function(){
		 _index=$(this).index();
		 $(this).addClass('cur').siblings().removeClass('cur');
         $('.productDetailCon div.detailBox').eq(_index).show().siblings().hide();
	})
	$('.comentBoxTab ul li').click(function(){
		 _index=$(this).index();
		 $(this).addClass('cur').siblings().removeClass('cur');
         $('.commentBox div.commentCon').eq(_index).show().siblings().hide();
	})
	
	
			
		/*分类JS*/	
			$(".tab1 .single-bottom").hide();
			$(".tab2 .single-bottom").hide();
			$(".tab3 .single-bottom").hide();
			
			$(".tab1 ul").click(function(){
				$(".tab1 .single-bottom").slideToggle(300);
				$(".tab2 .single-bottom").hide();
				$(".tab3 .single-bottom").hide();
				$(".tab4 .single-bottom").hide();
				$(".tab5 .single-bottom").hide();
			})
			$(".tab2 ul").click(function(){
				$(".tab2 .single-bottom").slideToggle(300);
				$(".tab1 .single-bottom").hide();
				$(".tab3 .single-bottom").hide();
				$(".tab4 .single-bottom").hide();
				$(".tab5 .single-bottom").hide();
			})
			/*切换手机首页轮播图*/
       var screenW=$(window).width();
        if(screenW<767){
          var Len=$('#carouselInner').find('.item').length;
          $('.item').eq(0).find('img').attr('src','images/banner1_s.jpg');
          $('.item').eq(1).find('img').attr('src','images/banner2_s.jpg');
          $('.item').eq(2).find('img').attr('src','images/banner3_s.jpg');
        }

        /*删除*/
		var len=$('.in-check .cart-header').length;
		for(i=0;i<=len;i++){
			$('.delBtn'+i).on('click', function(){
			var par=$(this).parents('.cart-header');
			par.fadeOut('slow', function(){par.remove();});
			});	
		};
		/*全选*/
		$('#checkALLBtn').click(function(){
			var labBox=$(this).find("#checkAll");
			if(labBox.is(':checked')){ 
				$(".cartListBox :checkbox").prop("checked", true); 
			}else{
				$(".cartListBox :checkbox").prop("checked", false); 
			}

		})

		$('.pSlideNavUl li').click(function(){
			_index=$(this).index();
			$(this).addClass('active').siblings().removeClass('active');
			$('.p-contentBox .p-orderList').eq(_index).show().siblings().hide();
		})


})