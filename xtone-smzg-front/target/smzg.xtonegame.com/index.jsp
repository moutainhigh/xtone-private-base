<%@page import="org.common.util.ConnectionService"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.xtone.util.CheckLoad"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	CheckLoad check = new CheckLoad();
	System.out.println("pone:"+check.JudgeIsMoblie(request));
	if(check.JudgeIsMoblie(request)){
		response.sendRedirect("view/mpLoad.jsp");
	}
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String exchange = "";
	try{
		con = ConnectionService.getInstance().getConnectionForLocal();
		String sql = "SELECT * FROM `tbl_exchange_codes`;";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next()){
			exchange = rs.getString("content");
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
%>

<!DOCTYPE html>

<html><head><meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta charset="gb2312">
<title>《神魔战歌》2015 最酷炫神魔题材手游</title>
<meta name="keywords" content="神魔,魔幻,战歌,卡牌,魔兽,刀塔,LOL,养成,RPG,动作,游戏,手游,点控,酷炫特效,美女,英雄,大集合,大乱斗,MOBA,公会战,图腾,女神">
<meta name="description" content="2015最酷炫神魔题材手游《神魔战歌》正式登陆。神魔狂战，鹿死谁手!集齐史上各路神魔，养成史上最强神魔英雄。突破传统动作卡牌限制，领地建设给你带来不一样的养成体验~">
<meta name="author" content="神魔传奇.">
<meta name="copyright" content="神魔传奇.">
<link type="text/css" rel="stylesheet" href="./index.css">
<link href="./favicon.ico" rel="icon" type="image/x-icon" />
<!-- Bootstrap -->
<link href="../bootstrap.min.css" rel="stylesheet">
<link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="./share.v3.2.css"><style type="text/css">#NIE-copyRight a{text-decoration: none;}#NIE-copyRight a:hover{text-decoration: underline;}</style><style type="text/css">.adbase-ctn{background:url(./img/loading.png) center center no-repeat;}</style><style type="text/css">#NIE-topBar .hide,#NIE-topBar-include .hide{display:none!important}.NIE-topBar-logo,.NIE-topBar-arrIcon i,#NIE-topBar-menu span,.NIE-topBar-hot,.NIE-topBar-1st,.NIE-topBar-new,.NIE-topBar-btn{background:url(./img/sprite-w.png) no-repeat}.NIE-topBar-arrIcon{position:relative;top:4px;width:16px;height:16px;display:inline-block;background-color:#fff;overflow:hidden}.NIE-topBar-arrIcon i{width:9px;height:4px;float:left;margin:6px 0 0 4px;background-position:-103px -101px;display:inline-block;-webkit-transition:all .2s ease 0s;transition:all .2s ease 0s}#NIE-topBar,#NIE-topBar-include{position:relative;z-index:9999;min-width:1000px;height:40px;background:#333;line-height:24px;font-family:"Microsoft YaHei",simSun,"Lucida Grande","Lucida Sans Unicode",Arial}#NIE-topBar *,#NIE-topBar-include *{font-size:12px;font-style:normal}#NIE-topBar a,#NIE-topBar-include a{color:#999;text-decoration:none}#NIE-topBar a:hover,#NIE-topBar-include a:hover{color:#fff}#NIE-topBar ul,#NIE-topBar li,#NIE-topBar-include ul,#NIE-topBar-include li{margin:0;padding:0;float:left}.NIE-topBar-main{position:relative;z-index:9999;width:1000px;margin:auto}.NIE-topBar-logo{width:133px;height:44px;display:block;float:left;margin-top:6px;background:url(./img/logo.png) no-repeat}#NIE-topBar-news{position:absolute;z-index:13;width:420px;height:54px;left:125px;padding-left:18px;padding-top:0;overflow:hidden;display:block}#NIE-topBar-news.NIE-topBar-news-hover{height:250px;overflow:visible;border-color:#1f1f1f}#NIE-topBar-news a{width:960px;display:block;height:0;overflow:hidden;z-index:1;-webkit-box-shadow:0 2px 4px rgba(0,0,0,.2);-o-box-shadow:0 2px 4px rgba(0,0,0,.2);-moz-box-shadow:0 2px 4px rgba(0,0,0,.2);box-shadow:0 2px 4px rgba(0,0,0,.2)}#NIE-topBar-news-close{position:absolute;top:63px;right:-380px;display:block;width:32px;height:32px;overflow:hidden;background:url(./img/sprite-w.png) no-repeat -9999px -9999px;text-indent:-9999px;z-index:2}#NIE-topBar-news.NIE-topBar-news-hover .NIE-topBar-abc-s{visibility:hidden}#NIE-topBar-news.NIE-topBar-news-hover a{position:absolute;z-index:2;left:-132px;top:55px;height:auto}#NIE-topBar-news.NIE-topBar-news-hover a img{display:block}.NIE-topBar-mid{position:absolute;z-index:2;right:230px;width:385px;text-align:right}#NIE-topBar .NIE-topBar-mid a,#NIE-topBar-include .NIE-topBar-mid a{line-height:40px;padding:0 12px;color:#fff;display:inline-block}#NIE-topBar .NIE-topBar-mid a:hover,#NIE-topBar-include .NIE-topBar-mid a:hover{color:#ca0b0b}#NIE-topBar-menu{position:absolute;z-index:1;top:0;right:0;color:#999;width:1000px;height:40px;overflow:hidden;-webkit-transition:height .3s ease 0s;-o-transition:height .3s ease 0s;-moz-transition:height .3s ease 0s;transition:height .3s ease 0s}#NIE-topBar-menu span{position:absolute;width:108px;padding:8px 0 12px;height:20px;line-height:14px;right:106px;background-position:0 -28px;background-repeat:repeat-x;padding-left:15px;color:#fff;display:inline-block;overflow:hidden}#NIE-topBar-menu .NIE-table{position:absolute;top:40px;left:0;background:#262626;border-collapse:collapse;overflow:hidden;width:100%}#NIE-topBar-menu .NIE-table a{width:100px;margin-left:6px;line-height:24px;display:inline-block}#NIE-topBar-menu .NIE-table a.long{width:125px}#NIE-topBar-menu .NIE-table ul{list-style:none}#NIE-topBar-menu .NIE-table .NIE-nav{width:100%;position:relative}#NIE-topBar-menu .NIE-table .NIE-nav li{width:110px;float:left;border-right:1px solid #eee;vertical-align:top}#NIE-topBar-menu .NIE-table .NIE-nav .mobi{position:absolute;left:387px;-webkit-transition:all .3s ease-out}#NIE-topBar-menu .NIE-table b{line-height:39px;color:#bc2e2e;display:block;width:100%;height:39px;font-size:14px;text-indent:20px;background:#f7f7f8;overflow:hidden;clear:both}#NIE-topBar-menu b i{margin-left:5px;padding:2px 4px;color:#9b9b9b;font-style:normal;background:#111;display:inline}#NIE-topBar-menu .NIE-list{float:left;overflow:hidden;width:100%;height:400px}.w1680 #NIE-topBar-menu .NIE-list{height:450px}#NIE-topBar-menu .NIE-list li{border-right:1px solid #eee;height:100%;float:left;padding:10px 0 0 14px}#NIE-topBar-menu .NIE-list .news{position:absolute;background:#fdfefe;left:545px;-webkit-transition:all .3s ease-out;top:39px}#NIE-topBar-menu .NIE-list .hot{position:absolute;background:#fdfefe;left:387px;-webkit-transition:all .3s ease-out;top:39px}#NIE-topBar-menu .NIE-list .hot a{width:125px}.w1680 #NIE-topBar-menu .NIE-list .news div div{margin-right:8px}#NIE-topBar-menu .NIE-list .news .long-news{width:133px;float:left}#NIE-topBar-menu .NIE-list .news .long-news a{width:125px}#NIE-topBar-menu .NIE-list .news .short-news{float:left;width:106px}#NIE-topBar-menu .moreAni .NIE-list .news{left:313px;width:524px!important;-webkit-transition:all .3s ease-out}#NIE-topBar-menu .moreAni .NIE-list .hot{left:155px;border-left:1px solid #eee;box-shadow:-1px 0 1px #eee;-webkit-transition:all .3s ease-out}#NIE-topBar-menu .moreAni .NIE-nav .mobi{left:155px;width:696px!important;border-left:1px solid #eee;box-shadow:-1px 0 1px #eee;-webkit-transition:all .3s ease-out}#NIE-topBar-menu .NIE-list .NIE-topBar-more{width:20px;height:95px;height:92px\9;display:block;position:absolute;right:0;top:124px;font-size:12px;color:#fff;background:#bc2e2e;padding-left:10px;line-height:14px;padding-top:7px;border-top-left-radius:2px;border-bottom-left-radius:2px}#NIE-topBar-menu .NIE-list .NIE-topBar-more:hover{opacity:.8}#NIE-topBar-menu .moreAni .NIE-topBar-more{width:21px;height:23px;border-radius:2px;right:9px;top:163px;padding-left:4px;padding-top:2px;height:20px;height:18px\9}.w1680 #NIE-topBar-menu .NIE-list .NIE-topBar-more{display:none}#NIE-topBar-menu .NIE-table a em{font-weight:400;display:block;line-height:24px}.NIE-topBar-1st{background-position:right -267px}.NIE-topBar-hot{background-position:right -144px}.NIE-topBar-new{background-position:right -206px}#NIE-topBar-menu.NIE-topBar-menu-hover .NIE-table{display:block}#NIE-topBar-menu.NIE-topBar-menu-hover{height:505px;opacity:.9;-webkit-transition:height .4s ease 0s;-o-transition:height .4s ease 0s;-moz-transition:height .4s ease 0s;transition:height .4s ease 0s}a.NIE-topBar-btn{width:105px!important;height:18px;line-height:18px!important;margin:5px 0;display:inline-block;padding-left:0!important;background-position:0 -338px;text-indent:25px;color:#bc3030!important}a.NIE-topBar-btn:hover{}.NIE-topBar-ad{height:40px;left:50%}.NIE-topBar-main.w1680{width:1260px}.NIE-topBar-main.w1680 #NIE-topBar-menu{width:1260px}#NIE-topBar-news{height:54px}#NIE-topBar .NIE-topBar-menu-hover,#NIE-topBar-include .NIE-topBar-menu-hover{z-index:8}.NIE-topBar-ad-big{position:absolute;top:0;left:0;z-index:10;height:0;overflow:hidden;text-align:center}#NIE-topBar-menu{z-index:7;height:55px}.NIE-topBar-mid{z-index:12;width:250px;right:230px;width:536px}.NIE-topBar-logo{position:relative;z-index:13!important}#NIE-topBar,#NIE-topBar-include{height:55px;background:#fbfbfb}.NIE-topBar-logo{margin-top:5px}#NIE-topBar-menu .NIE-table{top:55px;background:#fdfefe;border-top:1px solid #ececec;margin:0}#NIE-topBar-menu span{background:0 0;color:#bc2e2e}#NIE-topBar-menu .NIE-table{display:none}#NIE-topBar-menu .NIE-table .last_td,#NIE-topBar-menu .NIE-table .last_th{border-right:0}#NIE-topBar a,#NIE-topBar-include a{color:#626262}#NIE-topBar-menu b i{background-color:#fbfbfb;color:#bc2e2e}#NIE-topBar-menu p{padding:0;margin:0;margin-left:6px;line-height:24px;margin-bottom:4px;font-weight:700;color:#626262}#NIE-topBar a:hover,#NIE-topBar-include a:hover{color:#bc2e2e}#NIE-topBar-menu.NIE-topBar-menu-hover{opacity:.95}#NIE-topBar-menu .NIE-table *{font-size:12px}#NIE-topBar-menu.NIE-topBar-menu-hover span{border-bottom:3px solid #bc2e2e;background-color:#F3F3F3;background-image:-webkit-linear-gradient(top,#F3F3F3 0,#FEFEFE 52px);background-image:linear-gradient(top,#F3F3F3 0,#FEFEFE 52px)}#NIE-topBar-menu span{height:25px;height:26px\0;width:108px;padding-top:15px;padding-top:14px\0;transition:background ease-in-out .33s;z-index:20}#NIE-topBar .NIE-topBar-mid a,#NIE-topBar-include .NIE-topBar-mid a{color:#333;line-height:52px;transition:background ease-in-out .33s;*line-height:55px}#NIE-topBar .NIE-topBar-mid a:hover,#NIE-topBar-include .NIE-topBar-mid a:hover{color:#bc2e2e!important;border-bottom:3px solid #bc2e2e;background-color:#F3F3F3;background-image:-webkit-linear-gradient(top,#F3F3F3 0,#FEFEFE 52px);background-image:linear-gradient(top,#F3F3F3 0,#FEFEFE 52px);*border-bottom:0}.NIE-topBar-right{position:absolute;right:0;top:0;display:block;width:100px;height:55px;overflow:hidden;z-index:9}.NIE-topBar-right a{color:#333!important;width:100%;text-align:center;display:inline-block;float:left;line-height:52px;transition:background ease-in-out .33s;-webkit-transition:background ease-in-out .33s}.NIE-topBar-right a:hover{color:#bc2e2e!important;border-bottom:3px solid #bc2e2e;background-color:#F3F3F3;background-image:-webkit-linear-gradient(top,#F3F3F3 0,#FEFEFE 52px);background-image:linear-gradient(top,#F3F3F3 0,#FEFEFE 52px);*border-bottom:0}.NIE-topBar-right a.fadeUp{opacity:0;-webkit-transform:translate3d(0,-70%,0);-webkit-transition:all ease-in-out .5s;transform:translate3d(0,-70%,0);transition:all ease-in-out .5s}.NIE-topBar-right a.none{opacity:0;-webkit-transform:translate3d(0,70%,0);transform:translate3d(0,70%,0)}.NIE-topBar-right a.fadeUp2{opacity:1;-webkit-transform:translate3d(0,0,0);-webkit-transition:all ease-in-out .5s;transform:translate3d(0,0,0);transition:all ease-in-out .5s}</style>
 </head>
<body>

<!--  <div id="" class=".container-fluid">
<div id="" class="row">-->
<div class="wrap">
<div class="wrap-1">
<div class="wrap-2">
<div class="wrap-3">


  <div class="main">

	<h1 class="hide">神魔战歌穿越公测</h1>


	<div class="ent-top">
	  <a class="ent-top-index" href="view/main.jsp">进入官网</a>
	</div>

	<div class="download">
	  <img  src="img/load/code.jpg" width="125px" height="125px" alt="神魔战歌二维码">
	  <a class="download--ios" href="javascript:openVideo();" rel="nofollow">App Store下载</a>
	  <a class="download--android" href="javascript:;" title="">安卓下载</a>
	 <!-- <a class="btn-video" href="javascript:openVideo();"></a> -->
	  <!--epart start-->
	  <div class="ent-download-share2">
	  	<a href="javascript:;"><img src="img/load/code.jpg"></a>
	  	<a href="javascript:;"><img src="img/load/code.jpg"></a>
	  	<a href="javascript:alert('暂未开通！');" rel="nofollow" target="_blank"></a>
	  </div>
	  <!--epart end-->
	</div>



	<ul class="hide">
	  <li><b>登入惊喜好礼</b>21份惊喜任你开启</li>
	  <li><b>升级成长礼包</b>冲级元宝送不停</li>
	  <li><b>首充双重豪礼</b>四大美女任选其一</li>
	  <li><b>女神助阵礼包</b>购女娲整卡紫卡，送等额元宝</li>
	  <li><b>公平竞技礼包</b>真人竞技入场券，限时放送</li>
	</ul>

	<div class="md">
	  <h2>游戏特色</h2>
	  <ul class="md-ul">
	    <li class="md-ul-li1 on"><b>国风穿越</b></li>
	    <li class="md-ul-li2"><b>战斗革新</b></li>
	    <li class="md-ul-li3"><b>题材革新</b></li>
	    <li class="md-ul-li4"><b>竞技革新</b></li>
	    <li class="md-ul-li5"><b>策略革新</b></li>
	  </ul>
	</div>


	</div>

  <div id="NIE-copyRight"><p id="NIE-copyRight-corp" style="width:100%;text-align:center;margin:0 auto;padding:15px 0 0 0;font-size:12px;font-family:&#39;宋体&#39;;line-height:20px;display:block;position:relative;"><span style="position:relative;vertical-align:top;top:4px;display:inline-block;position:relative;"><a href="http://www.nomo.cn" target="_blank" style="width:224px;height:28px;padding-right:15px;display:inline-block;cursor:pointer;background:url(img/load/corp.png) no-repeat"></a></span><span style="text-align:left;display:inline-block;padding-left:6px;"><span id="ncp-l1"><a href="javascript:alert('公司简介！');" target="_blank">相关法律</a> - <a href="javascript:alert('公司简介！');" target="_blank">翔通游戏</a> - <a href="javascript:alert('公司简介！');" target="_blank">联系我们</a> - <a href="javascript:alert('公司简介！');" target="_blank">商务合作</a></span><br><span id="ncp-l2">翔通游戏 创造精彩世界 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://www.uupark.com/jzjh_xtonegroup/supervise.html" target="_blank">家长监护</a>
  </span><br><span id="ncp-l2">粤网文（2015）1397-182号&nbsp;<a href="http://www.miitbeian.gov.cn/state/outPortal/loginPortal.action" target="_blank">粤ICP备12058124号-20</a></span></span></p></div>

</div>
</div>
</div>
</div>
</div>
</div>

<!--弹层-->
<div id="overlay" style="display: none;"></div>

<!--ios-->
<div class="pop" id="pop-ios" style="display: none;">
  <div class="pop-close"></div>
  <div class="pop-title"><span>8月25日</span>苹果首发，<br>iOS用户关注公众号领取专属福利</div>
  <p class="pop-qrcode"><img src=""><span>公众号： </span></p>
</div>

<!--android-->
<div class="pop" id="pop-android" style="display: none;">
  <div class="pop-close"></div>
  <div class="pop-title">您的专属康熙恩泽天下礼包<br><span><%=exchange %></span><em>(仅限安卓)</em></div>
  <p class="pop-how2use">
    <b>兑换方式：</b><br>
	<span>首页</span> → 左上角“<span>更多</span>”按钮 → “<span>礼包码</span>”→ 输入礼包码即可。<br>
	<span>【ios用户关注公众号领取专属福利】</span>
  </p>
</div>


<!--视频弹层-->
<div class="pop videoPop" id="pop-video" style="display: none;">
  <div class="pop-close"></div>
  <div class="pop-content"></div>
</div>


<script charset="gb2312" type="text/javascript" src="./js-css/load/jquery(mixNIE).last.js"></script>
<iframe src="./point.html" style="display:none;"></iframe>
<script charset="gb2312" type="text/javascript">
nie.config.copyRight.setWhite();
//分享
var shareTxt = "";
var shareImg = "";
nie.use(["nie.util.shareV3"],function(){
	nie.util.share({
		fat : ".ent-top-share",
		type : 2,
		defShow : [22, 2, 1, 3],
		img : shareImg,
		title : shareTxt
	});
});

//卖点切换
$(".md-ul li").mouseover(function(){
	$(".md-ul li.on").removeClass("on");
	$(this).addClass("on");
}).eq(0).mouseover();


//弹层
function openPop(popID){
	closePop();
	var iframe = "<div class='pop-close'></div><iframe marginWidth=0 marginHeight=0 src='http://www.wsview.com/yzplayerAction!play2.action?autoPlay=true&userVideoID=84293&iframe=iframe' frameBorder=0 width=900 scrolling=no height=600 allowTransparency></iframe>";
	//$("#overlay,"+popID).html(iframe);
	//$("#overlay, .pop-close").click(closePop);

	$("#pop-video").html(iframe);
	$("#pop-video").click(closePop);
	$("#overlay,"+popID).show();
}
function closePop(){
	$(".videoPop .pop-content").empty();
	$("#pop-video").html("");
	$("#overlay, .pop").hide();
}
$("#overlay, .pop-close").click(closePop);

//视频弹层
var movieUrl = 'http://v.crazynote.netease.com/2015/0805/4cbcca0d33486262946324a9448e42e7qt.f4v';
var mp4_movieUrl = 'http://v.crazynote.netease.com/2015/0805/4cbcca0d33486262946324a9448e42e7qt.mp4';
function openVideo(){
	console.log("aaaaaaa");
	//$("#pop-vido").innelHTML()
	//$("#pop-vido").show();
	openPop("#pop-video");
}

function openVideo2(){
	console.log("aaaaaaa");
	//$("#pop-vido").innelHTML()
	$("#pop-vido").show();
}

 function mutilChannel(){
    //多渠道处理
    var url = window.location.href,
        $iosBtn = $(".download--ios"),
        $androidBtn = $(".download-android"),
        $codeImg = $(".download-qrcode"),
        ua = window.navigator.userAgent,
        needJump = !!ua.match(/mobile/i) && !url.match(/\/m\//);

    if(/fab/.test(url)){
        //fab
        $iosBtn.attr("href","javascript:alert('敬请期待！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","");

        if (needJump) {
            window.location = 'http://jianglin.163.com/m/fab/';
        }

    }else if(/shenma/.test(url)){
        //神马
        $iosBtn.attr("href","javascript:alert('敬请期待！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","");

        if (needJump) {
            window.location = 'http://jianglin.163.com/m/shenma/';
        }

    }else if(/baidu/.test(url)){
        //百度sem
        $iosBtn.attr("href","javascript:alert('敬请期待！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","");

        if (needJump) {
            window.location = 'http://jianglin.163.com/m/baidu/';
        }

    }else if(/pinpai/.test(url)){
    	//百度品牌
        $iosBtn.attr("href","javascript:alert('敬请期待！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","");

        if (needJump) {
            window.location = 'http://jianglin.163.com/m/pinpai/';
        }

    }else if(/weix/.test(url)){
    	//微信
        $iosBtn.attr("href","javascript:alert('敬请期待！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","");

        if (needJump) {
            window.location = 'http://jianglin.163.com/m/weix/';
        }

    }else if(/tieb/.test(url)){
    	//贴吧
        $iosBtn.attr("href","javascript:alert('敬请期待！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","");

        if (needJump) {
            window.location = 'http://jianglin.163.com/m/tieb/';
        }

    }else if(/weib/.test(url)){
    	//微博
        $iosBtn.attr("href","javascript:alert('敬请期待！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","");

        if (needJump) {
            window.location = 'http://jianglin.163.com/m/weib/';
        }

    }else{
        //官网
        $iosBtn.attr("href","javascript:alert('敬请期待！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","");//可替换页面主二维码
    }
}
mutilChannel();
</script>


<!--This is Git Project--></body></html>
