// JavaScript Document

$(document).ready(function() {

				   

	$("#sub_bar ul li").click(function() {

		$(this).addClass("li_on").siblings().removeClass("li_on");

	});



	

	$(".dl1 dt span a").hover(function(){

		$(this).children("em").slideToggle();

	});

	$(".dl1 dd span a").hover(function(){

		$(this).children("em").slideToggle();

	});

	

	$(".dl6 dd span a").hover(function(){

		$(this).children("em").slideToggle();

	});

	

	$(".dl9 dd").hover(function(){

		$(this).children("h3").slideToggle();

	});

	

	//下载

	$(".down span.ios").hover(function(){

		$(this).toggleClass("iosshow");

	});

	

	//推荐

	$(".dl4 dd").hover(function(){

		$(this).children("span").slideToggle();

		$(this).children("h3").slideToggle();

		$(this).children("h4").slideToggle();

	});
	
	$(".dl4 dd a").hover(function(){

		$(this).children("span").slideToggle();

		$(this).children("h3").slideToggle();

		$(this).children("h4").slideToggle();

	});

	

	$(".dl3 dd:last").addClass("ddlast");

	

	$(".dl2 dd:eq(0)").addClass("dd01");

	$(".dl2 dd:eq(1)").addClass("dd02");

	$(".dl2 dd:eq(6)").addClass("dd01");

	$(".dl2 dd:eq(7)").addClass("dd02");

	

	//列表

	$(".list_left .dl1 dd").hover(function(){

		$(this).addClass("ddhover").siblings().removeClass("ddhover");

	});



});



//tab

$(function(){

    function tabs(tabTit,on,tabCon){

	$(tabCon).each(function(){

	  $(this).children().eq(0).show();

	  });

	$(tabTit).each(function(){

	  $(this).children().eq(0).addClass(on);

	  });

     $(tabTit).children().hover(function(){

        $(this).addClass(on).siblings().removeClass(on);

         var index = $(tabTit).children().index(this);

         $(tabCon).children().eq(index).show().siblings().hide();

    });

     }

  tabs(".tab-hd","active",".tab-bd");

   });

