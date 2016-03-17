<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<meta name="keywords" content="荟森数码,手机网站,微信营销" />
<meta name="description" content="荟森数码" />
<meta name="author" content="荟森数码" />
<title>荟森数码</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.easing.js" type="text/javascript"></script>
<script src="js/jquery.MogFocus.js" type="text/javascript"></script>
<script src="js/jquery.navigation.js" type="text/javascript"></script>
<script src="js/jquery.tabSlide.js" type="text/javascript"></script>
<script src="js/MSClass.js" type="text/javascript"></script>
<script type="text/javascript">
//banner
$(function(){$("#banner").mogFocus({btnStyle:'noNumber',prevNextPos:0,prevNextToggle:"hide"});});
//navigation
$(document).ready(function(){$('#Solution').bxCarousel({display_num:4,move:1,margin:0});});
//news
$(document).ready(function($){$("#news").tabSlide({cntSelect:"#newsList",tabEvent:"mouseover",tabStyle:"move-animate",direction:"top"});});

//adbox
$(document).ready(function(){
	//$("#adbox").show();
	//$("#adbox").animate({top:0},200);
	$("#menu-sub-bg").css("display","none");
	$("#menu-sub").css("display","none");
	$("#adbox").slideDown(2000);
	//$("#adbox").animate({height:"300px"},700,'easeOutExpo');
	setTimeout(function(){
        var obj = document.getElementById("adbox");
        
		$("#adbox").stop().animate({height:"0px"},700,'easeOutExpo',function(){
		
		$("#menu-sub-bg").css("display","block");
		$("#menu-sub").css("display","block");
		});
		$("#adbox").fadeOut(500);
    },6000);
	$(".close").click(function(){
		$("#adbox").stop().animate({height:"0px"},700,'easeOutExpo',function(){
		
		$("#menu-sub-bg").css("display","block");
		$("#menu-sub").css("display","block");
		});
		$("#adbox").fadeOut(500);
	})
})
</script>
</head>

<body>



<div id="adbox">
	<div class="adpic">
		<a href="index.php?controller=about&action=contact" target="_blank"><img src="images/web/ad.png" style="width:100%" /></a>
		<a href="javascript:void(0);" class="close">关闭</a>
	</div>	
</div>
<div id="top-index">
	<ul>
        <li class="logo"><a href="index.php"><img src="images/web/logo.png" /></a></li>
        <li id="menu">
            <a href="index.php" id="m1">首 页</a>
            <a href="index.php?controller=solution" id="m2">解决方案</a>
            <a href="index.php?controller=product" id="m3">产品&amp;服务</a>
            <a href="index.php?controller=cases" id="m4">案例&amp;体验</a>
            <a href="index.php?controller=service" id="m5">服务&amp;支持</a>
            <a href="index.php?controller=about" id="m6">关于我们</a>
        </li>
    </ul>
</div>
<div id="menu-sub-bg"></div>
<div id="menu-sub" class="submargin-index">
	<div class="sub-index">
    	<div id="s1">&nbsp;</div>
        <div id="s2">
            <a href="index.php?controller=solution">电子政务系统</a>
            <a href="index.php?controller=solution&action=hospital">医院网站系统</a>
            <a href="index.php?controller=solution&action=group">集团网站群</a>
            <a href="index.php?controller=solution&action=brand">企业品牌网站</a>
            <a href="index.php?controller=solution&action=trade">企业外贸网站</a>
            <a href="index.php?controller=solution&action=marketing">企业营销网站</a>
            <a href="index.php?controller=solution&action=mall">电商运营</a>
        </div>
        <div id="s3">
            <a href="index.php?controller=product">域名空间邮箱</a>
            <a href="index.php?controller=product&action=design">网站设计服务</a>
            <a href="index.php?controller=product&action=seo">网站SEO优化</a>
            <a href="index.php?controller=product&action=beautify">淘宝阿里装修</a>
            <a href="index.php?controller=product&action=mobile">手机网站</a>
            <a href="index.php?controller=product&action=store">应用软件开发</a>
            <a href="index.php?controller=product&action=services">增值设计服务</a>
        </div>
        <div id="s4">
            <a href="index.php?controller=cases&action=web">网站开发</a>
            <a href="index.php?controller=cases&action=mobile">移动互联</a>
            <a href="index.php?controller=cases&action=government">电子政务</a>
            <a href="index.php?controller=cases&action=software">软件开发</a>
            <a href="index.php?controller=cases&action=mall">电商运营</a>
            <a href="index.php?controller=cases&action=brand">品牌设计</a>
        </div>
        <div id="s5">
            <a href="index.php?controller=service">服务理念</a>
            <a href="index.php?controller=service&action=faq">FAQ</a>
            <a href="index.php?controller=service&action=feedback">提交反馈</a>
        </div>
        <div id="s6">
            <a href="index.php?controller=about">企业简介</a>
            <a href="index.php?controller=about&action=superiority">我们的优势</a>
            <a href="index.php?controller=about&action=news">资讯动态</a>
            <!--<a href="#" target="_blank">Weibo/微博</a>-->
            <a href="index.php?controller=about&action=contact">联系方式</a>
        </div>
    </div>
</div>
<script src="js/menu-index.js" type="text/javascript"></script><div id="banner">
	<div class="imgWarp">
    	<ul class="imgList">
            <li><div style="background:url(images/banner/2_bg.jpg) repeat-x left center;"><span style="background:url(images/banner/2.jpg) no-repeat center center;"></span></div></li>
            <li><div style="background:url(images/banner/3_bg.jpg) repeat-x left center;"><span style="background:url(images/banner/3.jpg) no-repeat center center;"></span></div></li>
        	<li><a href="#"><div style="background:url(images/banner/1_bg.jpg) repeat-x left center;"><span style="background:url(images/banner/1.jpg) no-repeat center center;"></span></div></a></li>
        </ul>
        <ul class="slogans">
        	<li><a href="#"><img src="images/banner/text_1.png" /></a></li>
            <li><img src="images/banner/text_2.png" /></li>
            <li><img src="images/banner/text_3.png" /></li>
        </ul>
    </div>
</div><!-- 解决方案开始-->
<div id="navigation">
	<div class="bx_wrap">
    	<div class="bx_container">
            <ul id="Solution">
            	<li><a href="index.php?controller=product&action=design"><img src="images/web/navigation-img_web.jpg" width="209" /></a></li>
            	<li><a href="index.php?controller=product&action=mobile"><img src="images/web/navigation-img_phone.jpg" width="209" /></a></li>                
                <li><a href="index.php?controller=product&action=seo"><img src="images/web/navigation-img_seo.jpg" width="209" /></a></li>
                <li><a href="index.php?controller=product&action=beautify"><img src="images/web/navigation-img_shopping.jpg" width="209" /></a></li>
                <li><a href="index.php?controller=product"><img src="images/web/navigation-img_email.jpg" width="209" /></a></li>
                <li><a href="index.php?controller=product&action=store"><img src="images/web/navigation-img_store.jpg" width="209" /></a></li>
                <li><a href="index.php?controller=product&action=services"><img src="images/web/navigation-img_value-added.jpg" width="209" /></a></li>
            </ul>
        </div>        
    </div>
    <div class="shadow"></div>
</div>
<!-- 解决方案结束-->
<!-- 案例开始-->
<div id="cases-index">
	<div class="title"><a href="index.php?controller=cases" class="more"></a></div>
    <div class="brand">
        	<li>  
            	<?php if($web){ foreach($web as $val){ ?>      
            	<a href="index.php?controller=cases&action=caseView&id=<?php echo $val['id']; ?>" ><img alt="<?php echo $val['title']; ?>" src="<?php echo $val['icon']; ?>" /></a>
                <?php }} ?>
            </li>
        	
        </div>
    <div class="shadow"></div>
</div>
<!-- 案例结束-->
<!-- 新闻开始-->
<div id="news_superiority">
	<div id="news-index">
    	<div class="title" id="news">
            <li class="current"><a href="index.php?controller=about&action=news">公司新闻</a></li><li><a href="index.php?controller=about&action=industry">行业动态</a></li>
        </div>
        <div class="title_shadow"></div>
        <div class="con" id="newsList">
        	<div class="box">
                <div class="list">
                   <?php if($news1){ foreach($news1 as $val){ ?>
                   <a href="index.php?controller=about&action=newsDetail&nid=<?php echo $val['nid']; ?>"><span class="heading"><?php echo mb_substr($val['ntitle'],0, 20, 'utf8'); ?></span><span class="time"><?php echo mb_substr($val['ncreateTime'],0, 10, 'utf8'); ?></span></a>
                   <?php }} ?>
                    <a href="index.php?controller=about&action=news" class="more"></a>
                </div>
                <div class="list">
                	<?php if($news2){ foreach($news2 as $val){ ?>
                    <a href="index.php?controller=about&action=newsDetail&nid=<?php echo $val['nid']; ?>"><span class="heading"><?php echo mb_substr($val['ntitle'],0, 20, 'utf8'); ?></span><span class="time"><?php echo mb_substr($val['ncreateTime'],0, 10, 'utf8'); ?></span></a>
                   <?php }} ?>     
                    <a href="index.php?controller=about&action=industry" class="more"></a>
                </div>                
            </div>
        </div>
    </div>
    <div id="superiority-index">
    	<div class="title"></div>
        <div class="con">
        	<a href="index.php?controller=about&action=superiority"><p class="num">1</p><p>多年服务经验<span><br />多年经验 成就非凡</span></p></a>
            <a href="index.php?controller=about&action=superiority"><p class="num">2</p><p>7×24小时服务<span><br />持续服务 用心服务</span></p></a>
            <a href="index.php?controller=about&action=superiority"><p class="num">3</p><p>强大的技术支持<span><br />独立研发团队</span></p></a>
            <a href="index.php?controller=about&action=superiority"><p class="num">4</p><p>高素质客服人员<span><br />全程把握您的需求</span></p></a>
            <a href="index.php?controller=about&action=superiority"><p class="num">5</p><p>庞大的客户群体<span><br />已服务千万家客户</span></p></a>
        </div>
    </div>
</div>
<!-- 新闻结束-->

<?php include('footer.php'); ?>
</body>
</html>
