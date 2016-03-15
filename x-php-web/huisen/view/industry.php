<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<meta name="keywords" content="荟森数码,手机网站,微信营销" />
<meta name="description" content="荟森数码" />
<meta name="author" content="荟森数码" />
<title>荟森数码--行业动态</title>
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
		<li class="hover current-cat"><a href="index.php?controller=about&action=news">资讯动态</a></li>
		<!--<li><a href="#">Weibo/微博</a></li>-->
		<li><a href="index.php?controller=about&action=contact" class="last">联系方式</a></li>
    </ul>
</div>

<div id="content">
    <div id="news_list">		
        <div id="tab_a">
            <li><a href="index.php?controller=about&action=news">公司新闻</a></li>
            <li><a href="index.php?controller=about&action=industry" class="current">行业动态</a></li>
        </div>
        <div id="list-wrap">
            <div id="company">
            	<?php if($news){ foreach($news as $val){ ?>
               <a href="index.php?controller=about&action=newsDetail&nid=<?php echo $val['nid']; ?>">
                    <li class="time"><p class="day"><?php echo mb_substr($val['ncreateTime'],8, 2, 'utf8'); ?></p><p class="year"><?php echo mb_substr($val['ncreateTime'],0, 4, 'utf8'); ?>年<?php echo mb_substr($val['ncreateTime'],5, 2, 'utf8'); ?>月</p></li>
                    <li class="listtext">
                        <p class="title"><?php echo $val['ntitle']; ?></p>
                        <p class="click">&nbsp;</p>
                        <p class="text"><?php echo mb_substr($val['ninfor'],0,120,'utf8'); ?>...</p>
                    </li>
                </a>
              <?php }} ?>
                
                
               </div>
			<!--<div id="page">
                <a href="javascript:void(0);" title="已经是第一页了!" class="prveNO">&nbsp;</a><a href="�page=1.html" class="current">1</a><a href="�page=2.html">2</a><a href="�page=3.html">3</a><span class="pageell">……</span><a href="�page=28.html">28</a><a href="�page=29.html">29</a><a href="�page=2.html" title="查看下一页！" class="next">&nbsp;</a>            </div>-->
        </div>
    </div>
</div>


<?php include('footer.php'); ?>
<script src="js/jquery.brand.js" type="text/javascript"></script>
<script src="js/menu.js" type="text/javascript"></script>
</body>
</html>
