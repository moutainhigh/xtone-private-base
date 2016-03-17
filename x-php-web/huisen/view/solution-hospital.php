<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<meta name="keywords" content="荟森数码,手机网站,微信营销" />
<meta name="description" content="荟森数码" />
<meta name="author" content="荟森数码" />
<title>荟森数码--医院网站系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.easing.js" type="text/javascript"></script>
<script src="js/jquery.navpart.js" type="text/javascript"></script>
<script src="js/area.js" type="text/javascript"></script>
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
	<div>解决方案<span>Solution</span></div>
</div>
<div id="navpart" class="navpart-solution">
	<ul>
	    <li><a href="index.php?controller=solution">电子政务网站</a></li>
		<li class="hover current-cat"><a href="index.php?controller=solution&action=hospital">医院网站系统</a></li>
        <li><a href="index.php?controller=solution&action=group">集团网站群</a></li>
		<li><a href="index.php?controller=solution&action=brand">企业品牌官网</a></li>		
        <li><a href="index.php?controller=solution&action=trade">企业外贸网站</a></li>
        <li><a href="index.php?controller=solution&action=marketing">企业营销网站</a></li>
        <li><a href="index.php?controller=solution&action=mall" class="last">电商运营</a></li>
    </ul>
</div>

<div id="content">
    <div id="solution-hospital">
        <div class="title">医院网站系统</div>
        <div class="explanation">医院网站信息化平台解决方案是荟森基于多年实践经验，经过大量市场调研，并与本地多家知名的大型医院密切合作，参考不同医院的管理流程和业务特点，参考不同医院（如公立医院、民营医院、社区医院、乡镇医院等）对信息化建设的需求，,为实现现代化医院的管理流程和业务需求,推出一套面向不同规模医院的完整网站信息化平台解决方案</div>
        <div class="hospital_list">
		    <div class="title">适用单位</div>
            <div class="con">各级医院、卫生院、医学会、医疗系统等单位</div>
        </div>
        <div class="hospital_list">
		    <div class="title">主要功能</div>
            <div class="img"><img src="images/web/solution-hospital_img.png" /></div>
        </div>
        <div class="hospital_list">
		    <div class="title">案例展示</div>
            <div class="con"><a href="#"><span>温州市龙湾区人民医院</span></a><a href="#"><span>乐清市第一人民医院</span></a><a href="#"><span>乐清市妇幼保健院</span></a><a href="#"><span>乐清市第二人民医院</span></a><a href="#"><span>乐清市中医院</span></a><a href="#"><span>乐清市医学会</span></a></div>
        </div>       
    </div>
</div>


<?php include('footer.php'); ?>
<script src="js/jquery.brand.js" type="text/javascript"></script>
<script src="js/menu.js" type="text/javascript"></script>
</body>
</html>
