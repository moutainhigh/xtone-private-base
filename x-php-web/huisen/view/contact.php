<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<meta name="keywords" content="荟森数码,手机网站,微信营销" />
<meta name="description" content="荟森数码" />
<meta name="author" content="荟森数码" />
<title>荟森数码--联系我们</title>
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
<?php include('header.php'); ?>

<div id="location">
  <div>关于我们<span>About Us</span></div>
</div>
<div id="navpart" class="navpart-about">
	<ul>
	    <li><a href="index.php?controller=about">公司简介</a></li>
		<li><a href="index.php?controller=about&action=superiority">我们的优势</a></li>
		<li><a href="index.php?controller=about&action=news">资讯动态</a></li>
		<li class="hover current-cat"><a href="index.php?controller=about&action=contact" class="last">联系方式</a></li>
    </ul>
</div>

<div id="content">
	<div id="contact">
    	<div class="title">联系方式</div>
        <div class="QR">
        	<span><img src="images/web/contact-QR_weixin.jpg" />手机网站</span>
           <!-- <span><img src="images/web/contact-QR_weixin.jpg" />微信官网</span>-->
        </div>
      <div class="text">
        	<p>
            	<strong>地址、总机及售后服务：</strong>
                地址：广州市海珠区光大花园中海锦榕湾东门（地铁8号线沙园站A出口）<br />
                总机、业务咨询：020-89206892<br />
                售后服务：020-89205181
            </p>            
            
            <p>
            	<strong>网络联系：</strong>
                http://www.128digital.com/<br />
                <span>(当您忘记我们网址的时候请直接在各大搜索引擎中搜索“<u>荟森</u>”或“<u>荟森数码</u>”即可找到我们)</span>
            </p>
        </div>
        <div class="map"><iframe src="js/BaiduMap.html?center=113.2637747852,23.0993982780&zoom=19&width=699&height=452&markers=113.33808599999998,23.141109&markerStyles=l,A" width="699" height="452" scrolling="no" frameborder="0"></iframe></div>
    </div>
</div>


<?php include('footer.php'); ?>
<script src="js/jquery.brand.js" type="text/javascript"></script>
<script src="js/menu.js" type="text/javascript"></script>
</body>
</html>
