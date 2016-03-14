<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<meta name="keywords" content="荟森数码,手机网站,微信营销" />
<meta name="description" content="荟森数码" />
<meta name="author" content="荟森数码" />
<title>荟森数码--<?php echo $data['tName']; ?></title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.easing.js" type="text/javascript"></script>
<script src="js/jquery.navpart.js" type="text/javascript"></script>
<script src="js/jquery.cases.js" type="text/javascript"></script>
<script src="js/public.js" type="text/javascript"></script>
<script src="js/MSClass.js" type="text/javascript"></script>
<script type="text/javascript">
//navpart
$(function(){$("#navpart").lavaLamp({ fx:"backout", speed:700})});

//便民导航
head(230,"navpart");
</script>
</head>

<body>
<?php include('header.php'); ?>

<div id="location">
	<div>案例&amp;体验<span>Case &amp; Experience</span></div>
</div>
<div id="navpart" class="navpart-cases">
	<ul>
	    <li><a href="index.php?controller=cases">全部案例</a></li>
    	<li class="hover current-cat"><a href="index.php?controller=cases&action=web">网站开发</a></li>
		<li><a href="index.php?controller=cases&action=mobile">移动互联</a></li>
        <li><a href="index.php?controller=cases&action=government">电子政务</a></li>
		<li><a href="index.php?controller=cases&action=software">软件开发</a></li>
		<li><a href="index.php?controller=cases&action=mall">电商运营</a></li>
        <li><a href="index.php?controller=cases&action=brand" class="last">品牌设计</a></li>
    </ul>
<li class="back" style="left: 448px; width: 79px; overflow: hidden;">
<div class="left"></div>
</li>
</div>
<div id="head-navpart" style="display: none;">
  <ul>
    <li class="title"><?php echo $data['title']; ?></li>
    <li class="subnav">
        <?php if($pre['id']){  ?>
    	<a href="index.php?controller=cases&action=caseView&id=<?php echo $pre['id']; ?>" class="prev" title="没有了">上一个</a>
        <?php } ?>
        <?php if($next['id']){  ?>
        <a href="index.php?controller=cases&action=caseView&id=<?php echo $next['id']; ?>" class="next" title="荟森数码">下一个</a>  
        <?php } ?>  
    </li>  
  </ul>
</div>
<div id="content">
    <div id="cases_con">
    <div class="title"><?php echo $data['title']; ?></div>
    <div class="tags">
    	<?php $count = count($imgUrl,0); for($i=0;$i<=$count;$i++){ ?>
        <div class="tags_content">
            <img alt="" src="<?php echo $imgUrl[$i]; ?>">
        </div>
		<?php } ?>
    </div>
    <div class="introduction">
        <div class="text">
            <b>案例介绍</b><?php echo $data['introduce']; ?>
        </div>            
        <div class="url"><a href="<?php echo $data['siteUrl']; ?>" target="_blank"><img src="./images/web/url.jpg"></a></div>
        <div class="clear"></div>
        
        </div>
    </div>     
</div>

<?php include('footer.php'); ?>
<script src="js/jquery.brand.js" type="text/javascript"></script>
<script src="js/menu.js" type="text/javascript"></script>
</body>
</html>
