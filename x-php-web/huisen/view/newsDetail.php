<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<meta name="keywords" content="荟森数码,手机网站,微信营销" />
<meta name="description" content="荟森数码" />
<meta name="author" content="荟森数码" />
<title>荟森数码--资讯动态详情</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.easing.js" type="text/javascript"></script>
<script src="js/jquery.navpart.js" type="text/javascript"></script>
<script src="js/public.js" type="text/javascript"></script>
<script src="js/MSClass.js" type="text/javascript"></script>
<script type="text/javascript">
//navpart
$(function(){$("#navpart").lavaLamp({ fx:"backout", speed:700})});

//便民导航
head(100,"top");
</script>
</head>

<body>
<div id="top">
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
<div id="menu-sub" class="submargin">
	<div class="sub">
    	<div id="s1">&nbsp;</div>
        <div id="s2">
        	<a href="#" target="_blank">
            	<img src="images/web/new.gif" border="0" align="absmiddle" />移动互联
            </a>
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
            <a href="#" target="_blank">Weibo/微博</a>
            <a href="index.php?controller=about&action=contact">联系方式</a>
        </div>
    </div>
</div>

<div id="location">
  <div>关于我们<span>About Us</span></div>
</div>
<div id="navpart" class="navpart-about">
	<ul>
	    <li><a href="index.php?controller=about">公司简介</a></li>
		<li><a href="index.php?controller=about&action=superiority">我们的优势</a></li>
		<li class="hover current-cat"><a href="index.php?controller=about&action=news">资讯动态</a></li>
		<li><a href="#">Weibo/微博</a></li>
		<li><a href="index.php?controller=about&action=contact" class="last">联系方式</a></li>
    </ul>
</div>

<div id="content">
    <div id="news_con">		
    	<div class="title">
        	<li class="news_title"><?php echo $data['ntitle']; ?></li>
            <li class="back"><a href="javascript:history.go(-1);"><img src="./images/web/btn_back.gif"></a></li>
            <div class="clear"></div>
        </div>
        <div class="share">
        	<div>分类：<a href="<?php if($data['ntype']==1){ echo 'index.php?controller=about&action=news'; }else{ echo 'index.php?controller=about&action=industry';} ?>"><?php if($data['ntype']==1){ echo '公司新闻'; }else{ echo '行业动态';} ?></a>&nbsp;&nbsp;发布时间：<?php echo mb_substr($data['ncreateTime'],0, 10, 'utf8'); ?>&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;</div>
            <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
            <span>分享到：</span>
			
            </div>
            
		</div>
        <div class="text">
        <?php echo $data['ncontent']; ?>
 		</div>
        <?php if($data['nid']==7){ ?>
        <div style="text-align: center; margin: 0 auto;"><a href="./uploads/apk/JLLPKMM.apk"><img src="images/web/download.png" width="150" /></a></div>
        <?php } ?>
        
        <div id="pagination">
        	<div>
                <?php if($pre['nid']){  ?>
                <a href="index.php?controller=about&action=newsDetail&nid=<?php echo $pre['nid']; ?>" title=""><span class="imgPrve"><img src="./images/web/pagination-prve.jpg" border="0" align="absmiddle"></span><span class="text">上一篇</span></a>
                <?php } ?>
                <a href="index.php?controller=about&action=news" title="查看全部"><span class="imgAll"><img src="./images/web/pagination-all.jpg" border="0" align="absmiddle"></span><span class="textAll">全部<br>新闻</span></a>
                <?php if($next['nid']){  ?>
                <a href="index.php?controller=about&action=newsDetail&nid=<?php echo $next['nid']; ?>" title=""><span class="imgNext"><img src="./images/web/pagination-next.jpg" border="0" align="absmiddle"></span><span class="text">下一篇</span></a>                
                <?php } ?>
            </div>
        </div>
    </div>
</div>

<?php include('footer.php'); ?>

<script src="js/jquery.brand.js" type="text/javascript"></script>
<script src="js/menu.js" type="text/javascript"></script>
</body>
</html>