function tabCutover(c,d){$(c).parent().attr("class");$(c).parent().children().removeClass("current");$(c).addClass("current");$("."+d).parent().children().hide("");$("."+d).show();}
function showBg(){var bh = $("body").height();var bw = $("body").width();$("#fullbg").css({height:bh,width:bw,display:"block"}); 
$(".pop-box").show();} function closeBg(){$("#fullbg,.pop-box").hide();} 
function shownumsuccess(){var bh = $("body").height();var bw = $("body").width();$("#fullbg").css({height:bh,width:bw,display:"block"}); 
$("#num-success").show();} function hidenumsuccess(){$("#fullbg,#num-success").hide();} 
function showphone(){var bh = $("body").height();var bw = $("body").width();$("#fullbg").css({height:bh,width:bw,display:"block"}); 
$("#showphone").show();} function hidephone(){$("#fullbg,#showphone").hide();} 
function showphonesuccess(){var bh = $("body").height();var bw = $("body").width();$("#fullbg").css({height:bh,width:bw,display:"block"}); 
$("#phonesuccess").show();} function hidephonesuccess(){$("#fullbg,#phonesuccess").hide();} 

$(function(){$(".ranking-box li").hover(function(){$(this).siblings().removeClass('current');$(this).addClass('current')})});
$(function(){$(".nav-game-list").hover(function(){$(this).find(".nav-list").show();},function(){$(this).find(".nav-list").hide();});})

$(function(){
	// 图片左右翻滚
	var size = $('.small-pic>li').length;
	var frist = 0;
	var datetime;
	$('.small-pic li').mouseover(function(){
		frist = $('.small-pic li').index(this);
		showpic(frist);
	}).eq(0).mouseover();
	
	$('.banner-fm').hover(function(){
		clearInterval(datetime);
	},function(){
		datetime = setInterval(function(){
			showpic(frist)
			frist++;
			if(frist==size){
				frist=0;
			}
		},3000);
	}).trigger('mouseleave');

	function showpic(frist){
		var imgheight = $('.banner-fm').width();
		$('.slider-pic').stop(true,false).animate({left:-imgheight*frist},600)
		$('.small-pic li').removeClass('current').eq(frist).addClass('current');
		$('.download-fm li').removeClass('current').eq(frist).addClass('current');
	};
	
});

$(document).ready(function(){
							
	$("#select1 dd").click(function () {
		$(this).addClass("selected").siblings().removeClass("selected");
		if ($(this).hasClass("select-all")) {
			$("#selectA").remove();
		} else {
			var copyThisA = $(this).clone();
			if ($("#selectA").length > 0) {
				$("#selectA a").html($(this).text());
			} else {
				$(".select-result dl").append(copyThisA.attr("id", "selectA"));
			}
		}
	});
	
	$("#select2 dd").click(function () {
		$(this).addClass("selected").siblings().removeClass("selected");
		if ($(this).hasClass("select-all")) {
			$("#selectB").remove();
		} else {
			var copyThisB = $(this).clone();
			if ($("#selectB").length > 0) {
				$("#selectB a").html($(this).text());
			} else {
				$(".select-result dl").append(copyThisB.attr("id", "selectB"));
			}
		}
	});
	
	$("#select3 dd").click(function () {
		$(this).addClass("selected").siblings().removeClass("selected");
		if ($(this).hasClass("select-all")) {
			$("#selectC").remove();
		} else {
			var copyThisC = $(this).clone();
			if ($("#selectC").length > 0) {
				$("#selectC a").html($(this).text());
			} else {
				$(".select-result dl").append(copyThisC.attr("id", "selectC"));
			}
		}
	});
	
		$("#select4 li").click(function () {
		$(this).addClass("selected").siblings().removeClass("selected");
		if ($(this).hasClass("select-all")) {
			$("#selectD").remove();
		} else {
			var copyThisC = $(this).clone();
			if ($("#selectD").length > 0) {
				$("#selectD a").html($(this).text());
			} else {
				$(".select-result li").append(copyThisC.attr("id", "selectD"));
			}
		}
	});
	
	$("#selectA").live("click", function () {
		$(this).remove();
		$("#select1 .select-all").addClass("selected").siblings().removeClass("selected");
	});
	$("#selectB").live("click", function () {
		$(this).remove();
		$("#select2 .select-all").addClass("selected").siblings().removeClass("selected");
	});
	$("#selectC").live("click", function () {
		$(this).remove();
		$("#select3 .select-all").addClass("selected").siblings().removeClass("selected");
	});
	$("#selectD").live("click", function () {
		$(this).remove();
		$("#select4 .select-all").addClass("selected").siblings().removeClass("selected");
	});

	
	/*$(".select dd").live("click", function () {
		if ($(".select-result dd").length > 1) {
			$(".select-no").hide();
		} else {
			$(".select-no").show();
		}
	});*/
	
});
