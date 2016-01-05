//main_06_23.js @zhangxiaotong 20150624
$(function(){
	//navlist
	(function(){
	var time=null;
	var _this=0;
	$('.navlist > li').not('.first').not('.last').each(function(i,e){
			var that = $(this);
			$(this).find("a").eq(0).mouseover(function(){
				if(time){
					clearTimeout(time);
					time=null;
				}
				$(this).parent().siblings("li").find("ul").hide();
				$(this).parent().find("ul").show();
				$('.next_menu').show();
				
		  })

		  $(this).find("a").eq(0).mouseout(function(){
				var thata = $(this);
				time=setTimeout
				(
					function ()
					{	
						thata.parent().find("ul").hide();
						time = null;
						$('.next_menu').hide();
						
					},300
				);
   
		 });

		 $(this).find("ul").mouseover(function(){
			   if(time){
					clearTimeout(time);
					time=null;
				}
		 });
		 $(this).find("ul").mouseout(function(){
				var thatul = $(this);
				time=setTimeout
				(
					function ()
					{	
					   
					  
						thatul.hide();
						time = null;
						$('.next_menu').hide();
						
						
					},300
				);
		 });
	});
	
	$('.next_menu').hover(function(){
		clearTimeout(time);
		$('.next_menu').show()
	},function(){
		time=setTimeout(function(){
			$('.next_menu').hide();
		},300);
		
	});
	})();
	
	//tablist
	(function(){
		var iNow=0;
		var oTimer=null;
		var oWinw=$(window).width();
		var oWinh=$(window).height();
		
		$('.tablist_smdian li').click(function(){
			iNow=$(this).index();
			oTab(iNow);
			
		});
		
		$('.tablist_rightbtn').click(function(){
			oRightList();
		});
		$('.tablist_leftbtn').click(function(){
			oLeftlist();
		});
		
		//$('.dt_tablist ul > li').each(function(){
			$('.dt_tablist ul > li').first().click(function(event){
				$('#mark').css('width',oWinw+'px');
				$('#mark').css('height',$(document).height()+'px');
				$('#mark').css({'background':'black','opacity':'0.7'});
				$('#mark').css('display','block');
				
				$('.win_tv').css('left',(oWinw-$('.win_tv').width())/2 + 'px');
				
				$('.win_tv').css('top',(oWinh-$('.win_tv').height())/2 +$(window).scrollTop()+ 'px');	
				
				$('.win_tv').css('display','block');
				$('#myVideo').trigger('play');
				
				event.stopPropagation();
				
			});
		//});
		
		
		$('#win_close').click(function(event){
			$('.win_tv').css('display','none');
			$('#mark').css('display','none');
			$('#myVideo').trigger('pause');
			event.stopPropagation();
		});
		
		$('.tablist li').hover(function(){
			clearInterval(oTimer);	
		},function(){
			oTimer=setInterval(function(){
				oRightList();	
			},5000);	
		});
		
		oTimer=setInterval(function(){
			oRightList();	
		},6000);
		
		function oRightList(){
			iNow++;
			if(iNow == $('.tablist_smdian li').size()){
				iNow=0;	
			}
			oTab(iNow);
		}
		
		function oLeftlist(){
			if(iNow == 0){
				iNow=$('.tablist_smdian li').size();	
			}
			iNow--;
			oTab(iNow);
		}
		
		function oTab(){
			
			$('.tablist_smdian li').eq(iNow).addClass('tablist_active').siblings('li').removeClass();
			$('.tablist li').eq(iNow).addClass('opc').siblings().removeClass('opc');	
		}
	})();
	
	(function($){
		$.fn.extend({
			hotlist:function(){
				var This = this;
				this.hover(function(){
					$(this).addClass('hot_gamebox_active');	
				},function(){
					$(this).removeClass('hot_gamebox_active');	
				});	
			},
			lkdtnew:function(){
				var This = this;
				this.bind('mouseover',function(){
					$(this).siblings('li').find('.lk_dt_list_con').hide();
					$(this).find('.lk_dt_list_con').show();
					$(this).siblings('li').find('.lk_dt_list').show();
					$(this).find('.lk_dt_list').hide();
				});	
			},
			language:function(){
				var This = this;
				$('.other_language dt').click(function(){
					$(this).next().show();
				});
			},
			languane_next:function(){
				var This = this;
				This.click(function(){
					$('.other_language dt').html($(this).text()+'<i></i>');
					$(this).parent().hide();	
				});
			},
			sild:function(){
				var fold = $(window).height()+$(window).scrollTop();
				var This = this;
				
				animate(This);
				
				$(window).bind('scroll',function(){
					animate(This);
				})
				
				function animate(obj){
					obj.each(function(event){
						var fold = $(window).height()+$(window).scrollTop();
						if($(this).offset().top <= fold){
							
							$(this).find('.gameleft').stop().animate({'left':'-220px'}).stop().animate({'left':'0'});
							$(this).find('.game_peo').stop().animate({'left':'1200px'}).stop().animate({'left':'692px'});
								
						}	
					})	
				}	
			},
			events:function(){
				var fold = $(window).height()+$(window).scrollTop();
				var This = this;
				oUp(This);
				$(window).bind('scroll',function(){
					oUp(This)
				});
				
				function oUp(obj){
					$(obj).each(function(){
						var fold = $(window).height()+$(window).scrollTop();
						
						if($(this).offset().top <= fold){
							$(this).find('.eventup').stop().animate({'top':'355px'},'600').stop().animate({'top':'0'},'600',function(){
								$(this).siblings('.eventdown').delay(2000).stop().animate({'top':'355px'},'600').stop().animate({'top':'0'},'600');
							})	
						}	
					})
				}
			},
			cptimes:function(){
				var fold = $(window).height()+$(window).scrollTop();
				var This = this;
				oUp(This);
				$(window).bind('scroll',function(){
					oUp(This)
				});
				
				function oUp(obj){
					$(obj).each(function(){
						var fold = $(window).height()+$(window).scrollTop();
						
						if($(this).offset().top <= fold){
							$(this).find('.cptimesup').stop().animate({'top':'355px'},'600').stop().animate({'top':'0'},'600',function(){
								$(this).siblings('.cptimesdown').delay(2000).stop().animate({'top':'355px'},'600').stop().animate({'top':'0'},'600');
							})	
						}	
					})
				}
			},
			company:function(){
				var This=this;
				$('.company_big:first').show().addClass('show');
				this.bind('mouseover',function(){
					$(this).addClass('company_active').siblings('li').removeClass('company_active');
					$(this).siblings('li').find('.company_sm').show();
					$(this).find('.company_sm').hide();
					$(this).siblings('li').find('.company_big').removeClass('show').css('display','none');	
					$(this).find('.company_big').show().addClass('show');	
				
				});
			},
			invest:function(){
				var This=this; 
				this.click(function(){
					$(this).siblings().find('.invest_big').slideUp('fast');
					$(this).siblings().find('i').removeClass('invest_icon_hover').addClass('invest_icon');
					$(this).find('.invest_big').slideDown('fast');
					$(this).find('i').removeClass('invest_icon').addClass('invest_icon_hover');	
				})
			
			},
			infor:function(){
				var This = this;
				this.click(function(){
					var newLeft = $('.infor_xian').width()*$(this).index() + 'px';
					$(this).addClass('infor_active').siblings().removeClass('infor_active');
					$('.infor_xian').stop().animate({'left':newLeft});
					$('.infor_contaions > div').hide().eq($(this).index()).show();	
				})	
			},
			peoCom:function(){
				var This = this;
				this.hover(function(){
					$(this).find('.peo_intro').hide();
					$(this).find('.peo_intro_hover').show();	
				},function(){
					$(this).find('.peo_intro_hover').hide();
					$(this).find('.peo_intro').show();	
				});	
			},
			
			
		})
	})(jQuery);
	
	$('.other_language dt').language();
	$('.other_language dd a').languane_next();
	
	//news_art_jd
	(function(){
		var iNow=0;
		var oTimer=null;
		$('.art_tab_dian li').click(function(){
			iNow=$(this).index();
			oTab(iNow);
		});
		
		
		$('.news_art_jd li').hover(function(){
			clearInterval(oTimer);	
		},function(){
			oTimer=setInterval(function(){
				oRightList();	
			},5000);	
		});
		
		oTimer=setInterval(function(){
			oRightList();	
		},6000);
		
		function oRightList(){
			iNow++;
			if(iNow == $('.art_tab_dian li').size()){
				iNow=0;	
			}
			oTab(iNow);
		}
		
		function oLeftlist(){
			if(iNow == 0){
				iNow=$('.art_tab_dian li').size();	
			}
			iNow--;
			oTab(iNow);
		}
		
		function oTab(){
			$('.art_tab_dian li').eq(iNow).addClass('art_active').siblings().removeClass();
			$('.news_art_jd li').hide().eq(iNow).show();
			$('.news_art_font li').hide().eq(iNow).show();	
		}
	})();
	
	
	$('#orca').hover(function(){
		$(this).html('暂未开放');	
	},function(){
		$(this).html('ORCA商城');
	});	
	
})