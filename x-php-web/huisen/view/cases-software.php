<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<meta name="keywords" content="荟森数码,手机网站,微信营销" />
<meta name="description" content="荟森数码" />
<meta name="author" content="荟森数码" />
<title>荟森数码--软件开发</title>
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
    	<li><a href="index.php?controller=cases&action=web">网站开发</a></li>
		<li><a href="index.php?controller=cases&action=mobile">移动互联</a></li>
        <li><a href="index.php?controller=cases&action=government">电子政务</a></li>
		<li class="hover current-cat"><a href="index.php?controller=cases&action=software">软件开发</a></li>
		<li><a href="index.php?controller=cases&action=mall">电商运营</a></li>
        <li><a href="index.php?controller=cases&action=brand" class="last">品牌设计</a></li>
    </ul>
</div>
<div id="head-navpart">
  <ul>
    <li class="title">案例展示</li>
    <li class="subnav">
    	<a href="index.php?controller=cases" class="current">全部案例</a>
        <a href="index.php?controller=cases&action=web">网站开发</a>
        <a href="index.php?controller=cases&action=mobile">移动互联</a>
        <a href="index.php?controller=cases&action=government">电子政务</a>
        <a href="index.php?controller=cases&action=softwarel">软件开发</a>
        <a href="index.php?controller=cases&action=mall">电商运营</a>
        <a href="index.php?controller=cases&action=brand">品牌设计</a>
    </li>  
  </ul>
</div>



<div id="content">
	<div id="cases">
        <div class="title">软件开发</div>
        <div class="case_con">
            <?php if($data){ foreach($data as $val){ ?>
             <div class="boxgrid caption">
                <a href="index.php?controller=cases&action=caseView&id=<?php echo $val['id']; ?>"><img src="<?php echo $val['icon']; ?>" width="290" height="160" alt="<?php echo $val['title']; ?>"/></a>
                <div class="cover boxcaption">
                <h3><a href="index.php?controller=cases&action=caseView&id=<?php echo $val['id']; ?>"><b><img src="<?php echo $val['icon']; ?>" width="20%" align="middle" /><?php echo $val['title']; ?></b></a></h3>
                <p><?php echo $val['sign']; ?></p>
                </div>
            </div>
            <?php }} ?>
        </div>
    </div>
</div>



<?php include('footer.php'); ?>
<script src="js/jquery.brand.js" type="text/javascript"></script>
<script src="js/menu.js" type="text/javascript"></script>
</body>
</html>
