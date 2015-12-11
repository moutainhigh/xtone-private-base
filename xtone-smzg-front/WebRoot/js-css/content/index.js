// 鏍囩鍒囨崲
var tabLi = {
	init: function() {
		var baikeTime;
		//鏂伴椈
		$("#news_nav li").live("mouseover", function () {
			var c = $(this).attr("class");
			if($(this).find('a').attr("class") == 'mu_gd')return;
			if(c && c.indexOf("current") != -1) return;
			tabLi.turnNewsTab(this);
		});		
		//娓告垙鏀荤暐
		$("#gameInfoMenu li").live("mouseover", function () {
			var c = $(this).find('a').attr("class");
			if(c && c.indexOf("on") != -1) return;
			tabLi.turnGameWar(this);
		});
		//鍙嬫儏閾炬帴
		$("#friendlyLink a").live("mouseover", function () {
			var i = $(this).index();
			if($(this).attr("class") == 'yl_menu_on')return;
	
			$("#friendlyLink a").removeClass("yl_menu_on").addClass("yl_menu_off");
			$(this).removeClass("yl_menu_off").addClass("yl_menu_on");
			
			$(".ylnrs:visible").hide();
			$(".ylnrs").eq(i).show();
		});
		//濯掍綋涓撳尯
		$("#bkMenuZu li").live("mouseover", function() {
			if($(this).find('a').attr("class") === 'on') return;
			
			$("#bkMenuZu li a.on").removeClass().addClass("off");
			$(this).find('a').removeClass().addClass("on");
			
			var Url = $(this).find('a').attr('ref');
			
			//$(".gamegl_nrzu:visible").hide();
			$(".yinyongqu iframe").attr('src',Url);
			
		});
		//娓告垙鍥惧簱
		$("#pic_list li").live("mouseover", function () {
			if($(this).find('a').attr("class") == 'on')return;

			tabLi.turnPicTab(this);
		});
		//娓告垙浠嬬粛
		$("#type_list li").live("mouseover", function () {
			var c = $(this).find('a').attr("class");
			$("#type_list li").removeClass("type_list_lis");
			$(this).addClass("type_list_lis");
			
			if(c && c.indexOf("on") != -1) return;
			
			tabLi.tunrIntroduction(this);
		});
		
		//浠嬬粛婊氬姩灏忓浘
		$("#jsPicMenu li").live("mouseover", function () {			
			tabLi.turnjsPicTab(this);
		});
		//浠嬬粛婊氬姩灏忓浘
		$(".imgCont").live("mouseover", function () {
			alert($("#jsPicMenu ul li:first-child").attr("class"));
			
			if($(".hj_jsGroup").is(":visible"))
			{
				var _alsid = $(".hj_jsGroup:visible").attr("data");
				$(".als-wrapper li").find("img").removeClass("cur");
				$(".jsMeanu_"+_alsid).find("img").addClass("cur");				
			}				
				
		});
		//鍒嗛〉
		$(".pagenumQu li").live("click", function () {
			if($(this).find('img').attr("class") == 'cur')return;			
		});
	},
	
	turnNewsTab: function (me) {
		$("#news_nav li.current").removeClass("current");
		$(me).addClass("current");

		var i = $(me).index(),_url=$('#news_nav li:eq('+i+') a').attr('data');
		
		$(".mu_gd").attr("href",_url);
		
		$(".news_nr:visible").hide();
		$(".news_nr").eq(i).show();
	},
	
	tunrIntroduction: function (me) {
		$("#type_list li a.on").removeClass("on").addClass("off");
		$(me).find('a').removeClass("off").addClass("on");
		
		var i = $(me).index(),_url=$('#type_list li:eq('+i+') a').attr('data');
		
		$(".jsMore").attr("href",_url);
				
		$(".nrzw_txts span").attr("style","color:#ffff");	
				
		if($(".hj_js_heng_bar").is(":visible")){
			var contid = $(".hj_"+i+" ul li:first-child").attr("tid");			
			$(".hj_js_heng_bar li img.cur").removeClass("cur");
			$(".jsMeanu_"+contid).find("img").addClass("cur");
			$(".hj_jsGroup:visible").hide();		
			$(".jsGroup_"+contid).show();				
		}
		$(".imgbox .hj_js_heng_bar:visible").hide();
		$(".imgbox .hj_js_heng_bar").eq(i).show();
		//gameIntroduction.init();	
	},
	
	turnGameWar: function (me) {
		$("#gameInfoMenu li a.on").removeClass("on").addClass("off");
		$(me).find('a').removeClass("off").addClass("on");

		var i = $(me).index(),_url=$('#gameInfoMenu li:eq('+i+') a').attr('href');
		$("#glmore").attr("href",_url);
		
		$(".yg_nr_zu:visible").hide();
		$(".yg_nr_zu").eq(i).show();
	},
	
	turnPicTab: function (me) {
		$("#pic_list li a.on").removeClass("on").addClass("off");
		$(me).find('a').removeClass("off").addClass("on");

		var i = $(me).index(),_url=$('#pic_list li:eq('+i+') a').attr('data');
		$("#picMore").attr("href",_url);
		
		$(".tushowZu:visible").hide();
		$(".tushowZu").eq(i).show();
	},
	/*浠嬬粛榧犳爣绉诲姩鏄剧ず鍐呭*/
	turnjsPicTab: function (me) {
		$("#jsPicMenu li img.cur").removeClass("cur");
		$(me).find('img').addClass("cur");

		var i = $(me).index(),_url=$('#jsPicMenu li:eq('+i+') a').attr('href'),_id=$(me).attr("tid");
		$("#picMore").attr("href",_url);	
		
		$(".hj_jsGroup:visible").hide();		
		$(".jsGroup_"+_id).show();
	}
}


/**
* 澶х溂鐫涘箍鍛婅疆鎾�
*/
var indexEye = {
	autoTime:0,
	
	init: function () {
		var eyeObj = $("#eye_box a:eq(0) img:eq(0)");
		eyeObj.attr("src", eyeObj.attr("data-imgSrc"));
		eyeObj.load(function () {
			indexEye.autoTime = setTimeout(function () {
				indexEye.autoPlay();
  		}, 1000);
		});
		//$("#eye_number").siblings(".rotate_img_link").children("a").attr({"href":$("#eye_box a").eq(0).attr("href"), "title":$("#eye_box a").eq(0).attr("title")});

		$("#eye_number a").live("mouseover", function() {
			if($(this).attr("class").indexOf("on") > 0) return;
			indexEye.autoPlay(this);
		});
	},

	autoPlay:function (me) {
		clearTimeout(this.autoTime);
		this.turnNumber(me);
		var now = $("#eye_number a.on").index();
		var imgObj = $("#eye_box a").eq(now).children("img");
		if(imgObj.attr("src") == "") {
			imgObj.attr("src", imgObj.attr("data-imgSrc"));
		}
		
		
		setTimeout(function () {
			$("#eye_box a:visible").fadeOut(0, function() {
				$("#eye_box a").eq(now).fadeIn(0);
				//$("#eye_number").siblings(".rotate_img_link").children("a").attr({"href":$("#eye_box a").eq(now).attr("href"), "title":$("#eye_box a").eq(now).attr("title")});
			});
		}, 200);
		this.autoTime = setTimeout("indexEye.autoPlay()", 6000);
	},

	turnNumber:function(me) {
		if(typeof(me) == 'undefined') {
			var i = $("#eye_number a.on").index();
			i = i >= $("#eye_number a").length - 1 ? 0 : i + 1;
			me = $("#eye_number a").eq(i);
		}
		$("#eye_number a.on").each(function () {
  		$(this).removeClass("on").addClass('off');
		});
		$(me).removeClass("off").addClass('on');
	}
}

/**
 * 娓告垙浠嬬粛
 */
var gameIntroduction = {
	imgLen:0,
	liWidth:0,
	nowId:0,
	control:true,

	init:function() {
		var _this = this,
		liObj = $("#outerBox .hj_jsGroup"),
		mLeft = parseInt(liObj.css("margin-left")),
		mRight = parseInt(liObj.css("margin-right")),
		liWidth = liObj.width();
		
		this.imgLen = $("#outerBox .hj_js_heng_bar:visible .hj_jsGroup").length;
		this.nowId = 0;

		$('.HeroJs_pic_zu .hj_shu').html('<span class="zi">1</span>/'+this.imgLen+'');
		this.addSelect(this.imgLen);
		
		$("#dataBox").css({"left":"0px"});

		this.liWidth = liWidth + mLeft + mRight;
		var boxWidth = this.liWidth * this.imgLen;
		
		var outerBoxWidth = 733;
		$("#dataBox").css({"width":boxWidth+"px"});
		$("#outerBox").css({"width":outerBoxWidth+"px"});

		$("#outerBox").siblings(".hj_icon_left").unbind("click").bind("click", function() {
			_this.toPrev();
		});
		$("#outerBox").siblings(".hj_icon_right").unbind("click").bind("click", function() {
			_this.toNext();
		});
	},
	
	selectImg: function (i) {
		this.nowId = i;
		this.scrollImg();
		$('.HeroJs_pic_zu .hj_shu').html('<span class="zi">'+(this.nowId+1)+'</span>/'+this.imgLen+'');
		$('.HeroJs_pic_zu .hj_xuans a').removeClass('on');
		$('.HeroJs_pic_zu .hj_xuans a:eq('+this.nowId+')').addClass('on');
		$('.HeroJs_pic_zu .hj_xuans').hide();
	},
	
	addSelect: function (num) {
		var _html = '';
		for(var i=0;i < num;i++){
			var display = '';
			if(i == 0)display = 'on';
			_html += '<p><a href="javascript:gameIntroduction.selectImg('+i+');" class="'+display+'">'+(i+1)+'</a></p>';
		}
		
		$('.HeroJs_pic_zu .hj_xuans').html(_html);
	},
	
	clickNum: function () {
		$(".HeroJs_pic_zu .hj_dianji").click(
			function () {
				if($('.HeroJs_pic_zu .hj_xuans').is(":hidden")){
					$('.HeroJs_pic_zu .hj_xuans').show();
				}else{
					setTimeout(function(){$('.HeroJs_pic_zu .hj_xuans').hide();},200);
				}
			}
		).blur(function(){
			setTimeout(function(){$('.HeroJs_pic_zu .hj_xuans').hide();},200);
		});
	},
	
	toNext:function() {
		if(this.nowId + 1 >= this.imgLen || this.control == false) {
			
			return;
		}
		
		this.control = false;
		this.nowId++;
		this.scrollImg();
		$('.HeroJs_pic_zu .hj_shu').html('<span class="zi">'+(this.nowId+1)+'</span>/'+this.imgLen+'');
		$('.HeroJs_pic_zu .hj_xuans a').removeClass('on');
		$('.HeroJs_pic_zu .hj_xuans a:eq('+this.nowId+')').addClass('on');
		
		$("#outerBox").siblings(".prev").unbind("click").bind("click", function() {
			gameIntroduction.toPrev();
		});
		
		if(this.nowId + 1 == this.imgLen) {
		
		}
	},
	
	toPrev: function() {
		if(this.nowId <= 0 || this.control == false) {
			return;
		}
		
		this.control = false;
		this.nowId--;
		this.scrollImg();
		$('.HeroJs_pic_zu .hj_shu').html('<span class="zi">'+(this.nowId+1)+'</span>/'+this.imgLen+'');
		$('.HeroJs_pic_zu .hj_xuans a').removeClass('on');
		$('.HeroJs_pic_zu .hj_xuans a:eq('+this.nowId+')').addClass('on');
		
		$("#outerBox").siblings(".next").unbind("click").bind("click", function() {
			gameIntroduction.toNext();
		});
		
		if(this.nowId <= 0) {
		
		}
	},
	
	scrollImg: function () {
		var _this = this;

		$('.hj_js_heng_bar:visible .hj_jsGroup').hide();
		$('.hj_js_heng_bar:visible .hj_jsGroup:eq('+this.nowId+')').show();
		_this.control = true;
		
		/*var _this = this,
				l = this.liWidth * this.nowId * -1;

		$("#dataBox").animate({"left":l+"px"}, 300, function () {
			_this.control = true;
		});*/
	}
};


$(function () {
	gameIntroduction.init();
	gameIntroduction.clickNum();
	
	indexEye.init();
	tabLi.init();
	
	//rank.init();
});
$(document).ready(function(){
	//鏂伴椈鍒囨崲
	$('#news_navs li').mouseover(function(){
		var i = $('#news_navs li').index(this);
		$('#news_navs li').each(function(j){
			var cn = $(this).attr('class');
			cn = 'fl';
			if(i==j){
				$(this).attr('class',cn+' on');
			}else{
				$(this).attr('class',cn);
			}
		});
		$('#content_news .news_b_uls').hide().eq(i).show();
	});
	$("#tab_box ul li").click(function(){
		var index_now=$("#tab_box ul li").index(this);
		$(this).addClass('cur').siblings().removeClass('cur');
		
		$("#tab_conver .tab_content:eq("+index_now+")").show().siblings().hide();
		
	});
	$('.action .sns_link.wechat').hover(function(){
		
		$(this).parent().find('.social-content').css('display')=='block'?$(this).parent().find('.social-content').css('display','none'):$(this).parent().find('.social-content').css('display','block');
	})
});
