// mews02
(function(a){a.fn.vTicker=function(b){var c={speed:700,pause:4000,showItems:3,animation:"",mousePause:true,isPaused:false,direction:"up",height:0};var b=a.extend(c,b);moveUp=function(g,d,e){if(e.isPaused){return}var f=g.children("ul");var h=f.children("li:first").clone(true);if(e.height>0){d=f.children("li:first").height()}f.animate({top:"-="+d+"px"},e.speed,function(){a(this).children("li:first").remove();a(this).css("top","0px")});if(e.animation=="fade"){f.children("li:first").fadeOut(e.speed);if(e.height==0){f.children("li:eq("+e.showItems+")").hide().fadeIn(e.speed)}}h.appendTo(f)};moveDown=function(g,d,e){if(e.isPaused){return}var f=g.children("ul");var h=f.children("li:last").clone(true);if(e.height>0){d=f.children("li:first").height()}f.css("top","-"+d+"px").prepend(h);f.animate({top:0},e.speed,function(){a(this).children("li:last").remove()});if(e.animation=="fade"){if(e.height==0){f.children("li:eq("+e.showItems+")").fadeOut(e.speed)}f.children("li:first").hide().fadeIn(e.speed)}};return this.each(function(){var f=a(this);var e=0;f.css({overflow:"hidden",position:"relative"}).children("ul").css({position:"absolute",margin:0,padding:0}).children("li").css({margin:0,padding:0});if(b.height==0){f.children("ul").children("li").each(function(){if(a(this).height()>e){e=a(this).height()}});f.children("ul").children("li").each(function(){a(this).height(e)});f.height(e*b.showItems)}else{f.height(b.height)}var d=setInterval(function(){if(b.direction=="up"){moveUp(f,e,b)}else{moveDown(f,e,b)}},b.pause);if(b.mousePause){f.bind("mouseenter",function(){b.isPaused=true}).bind("mouseleave",function(){b.isPaused=false})}})}})(jQuery);

$(document).ready(function () {
	rollback('.notice');
	//rollback('.news02');
});
function rollback(id){
	$(id).vTicker({
		speed: 500,
		pause: 3000,
		showItems: 1,
		animation: 'fade',
		mousePause: true,//false,
		height: 0,
		direction: 'up'
	});
}

// flash
$(document).ready(function(){
	var curr = 0;
	$("#btn li").each(function(i){
		$(this).mouseover(function(){
			curr = i;
			$("#view #flash li").eq(i).fadeIn("slow").siblings().hide();
			$(this).addClass("on").siblings().removeClass("on");
			return false;
		});
	});

	var len = $("#btn li").length;
	var timer = setInterval(function(){
		var todo = (curr + 1) % len;
		$("#btn li").eq(todo).mouseover();
	},3000);

	$("#view").hover(function(){
		clearInterval(timer);
	},function(){
		clearInterval(timer);
		timer = setInterval(function(){
			var todo = (curr + 1) % len;
			$("#btn li").eq(todo).mouseover();
		},3000);
	});

// 	ico_tab
	$("#ico_tab a").click(function(){
		$(this).addClass("now").siblings().removeClass("now");
	});
	$("#ico_t1").click(function (){
		$(".pic_82 li").fadeTo("slow", 0.99);
	});
	$("#ico_t2").click(function (){
		$(".pic_82 li").fadeTo("slow", 0.22);
		$(".pic_82 li.ico_duan").fadeTo("slow", 0.99);
	});
	$("#ico_t3").click(function (){
		$(".pic_82 li").fadeTo("slow", 0.22);
		$(".pic_82 li.ico_ye").fadeTo("slow", 0.99);
	});
	$("#ico_t4").click(function (){
		$(".pic_82 li").fadeTo("slow", 0.22);
		$(".pic_82 li.ico_shou").fadeTo("slow", 0.99);
	});

// 	checked
	$(".czTc li").click(function(){
		$(this).addClass("now").siblings().removeClass("now");
	});

// 	zx zqNavtab
	$("#zqNavtab li").click(function(){
		$(this).addClass("now").siblings().removeClass("now");
		var index = $("#zqNavtab li").index( $(this) );
		$("#zxTabtxt table").eq(index).show().siblings("#zxTabtxt table").hide();
	});

// 	zx newNavtab
	$("#newNavtab li").click(function(){
		$(this).addClass("now").siblings().removeClass("now");
		var index = $("#newNavtab li").index( $(this) );
		$("#zxNewtxt .zx_newList").eq(index).show().siblings("#zxNewtxt .zx_newList").hide();
	});

// 	zx picNavtab
	$("#picNavtab li").click(function(){
		$(this).addClass("now").siblings().removeClass("now");
		var index = $("#picNavtab li").index( $(this) );
		$("#picScrolltxt .picScroll").eq(index).show().siblings("#picScrolltxt .picScroll").hide();
	});




// 	cz libao
	$("#czBody .libaoNav li").click(function(){
		$(this).addClass("now").siblings().removeClass("now");
		var index = $("#czBody .libaoNav li").index( $(this) );
		$(".libaoTxt li").eq(index).show().siblings(".libaoTxt li").hide();
	});

// 	cz libao
	$("#wt01 li").click(function(){
		$(this).addClass("now").siblings().removeClass("now");
	});
// 	input
	var textval=$("input.input_hui");
	//var text2=$("input.input_hui").document.getElementById("id");
	$(".input_hui").focusin(function(){
		$(this).addClass("input_hover");
		$(this).siblings("label").hide();
	});
	$(".input_hui").focusout(function(){
		$(this).removeClass("input_hover");
		//$(this).siblings("label").hide();
	});

	//var text=document.getElementById("id").value.trim();

	$("input.input_hui").focusout(function(){
		if(textval.val() == ""){
		$(this).siblings("label").show();
		}
	})
	/*if(text.val() == ""){
		$(this).siblings("label").show();
		}
		else{$(this).siblings("label").hide();
	}

	$("input.input_hui[value='']").click(
		function () {$(this).addClass("hover");
		},
		function () {$(this).removeClass("hover");
		}
	);*/

// 	allGame
	$("#xzYx li").click(function(){
		$(this).addClass("now").siblings().removeClass("now");
		var index = $("#xzYx li").index( $(this) );
		$("#tw .xzTan").eq(index).show().siblings("#tw .xzTan").hide();
	});
// 	allGame
	$("#allGame .xz_nav li").hover(function(){
		$(this).addClass("now").siblings().removeClass("now");
		var index = $("#allGame .xz_nav li").index( $(this) );
		$("#allGame .xz_list ul").eq(index).show().siblings("#allGame .xz_list ul").hide();
	});
	$("#allGame #closed").click(function(){
		$("#allGame").hide();
	});
// 	allFwq
	$("#allFwq .xz_nav li").hover(function(){
		$(this).addClass("now").siblings().removeClass("now");
		var index = $("#allFwq .xz_nav li").index( $(this) );
		$("#allFwq .xz_list ul").eq(index).show().siblings("#allFwq .xz_list ul").hide();
	});
	$("#allFwq #closed").click(function(){
		$("#allFwq").hide();
	});

// 	cz zcStyle
	$("#zcStyle li").click(function(){
		$(this).addClass("now").siblings().removeClass("now");
		var index = $("#zcStyle li").index( $(this) );
		$("#zcTxt .zcTxtLeft").eq(index).show().siblings("#zcTxt .zcTxtLeft").hide();
	});

// 	pic_570
	$(".pic_570 #khd_td01 li").click(function(){
		$(this).animate({ width: "612px"}).addClass("now").siblings().animate({ width: "41px"}).removeClass("now");
	});

	$(".pic_570 #khd_td02 li").click(function(){
		$(this).animate({ width: "612px"}).addClass("now").siblings().animate({ width: "41px"}).removeClass("now");
	});

	$(".pic_570 #khd_td03 li").click(function(){
		$(this).animate({ width: "612px"}).addClass("now").siblings().animate({ width: "41px"}).removeClass("now");
	});

	$(".pic_570 #khd_td04 li").click(function(){
		$(this).animate({ width: "612px"}).addClass("now").siblings().animate({ width: "41px"}).removeClass("now");
	});

// 	wtList_td
	$(".wtList_td li").click(function(){
		$(this).animate({ height: "100%"}).addClass("now").siblings().animate({ height: "40px"}).removeClass("now");
	});


	//jQuery("#picScroll01").slide({mainCell:".bd",autoPage:true,effect:"leftLoop",autoPlay:true,vis:4,trigger:"click"});



});

// top all game
(function(){
var sub$ = jQuery.sub();
function newPosi(){
var objW = sub$(window);
var objC = sub$('.plant_header');
var broT = objW.scrollTop();
var top = broT;
objC.css({'top':top});
}
sub$(document).ready(function() {
sub$('.plant_hed_nav_hover').mouseenter(function(){
sub$('.next_div').stop(true,true).animate({'height':260},0).animate({'width':630},500);
});
sub$('.plant_hed_nav_hover').mouseleave(function(){
sub$('.next_div').stop(true,true).animate({'height':0},500).animate({'width':0},0);
});
newPosi();
sub$(window).resize(function(){
newPosi();
})
sub$(window).scroll(function(){
newPosi();
})
});
})();
function add2favorite(){
try{
if(document.all){
window.external.AddFavorite(location.href,document.title);
}else if(window.sidebar){
window.sidebar.addPanel(document.title, location.href,"");
}else{
alert('您的浏览器不支持昆仑游戏自带收藏功能,需手动加入收藏夹,谢谢您的支持');
}
}catch(e){
alert('您的浏览器不支持昆仑游戏自带收藏功能,需手动加入收藏夹,谢谢您的支持');
}
}