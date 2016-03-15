<?php
include("./admin/include/config.php");
include("./admin/include/page.php");

//获取翻页列表。
$page = intval($_GET['pn']) ? intval($_GET['pn']) :1;
$page = intval($page);
//获取总记录数。
$totalnum = array();
$sqlrow = "SELECT count(id) FROM news where status=1";
$queryrow = sqlsrv_query($con, $sqlrow);
$rows = sqlsrv_fetch_array($queryrow);
$totalnum = $rows[0];

//计算总页数。
$limit = 15;//每页显示的数量。
$totalpages = ceil($totalnum/$limit);
$offset = ($page-1)*$limit;
$sqlNews="select top $limit n.id,* from news AS n where n.id not in (select top $offset id from news where  status=1 ORDER BY id DESC) AND status=1 ORDER BY n.id DESC";

//新闻与测评
//$sqlNews = "select top 16 id,title,createTime from news where status=1 order by id desc";
$News = $sqlsrv->FetchAll($sqlNews);

//新闻
$sqlNews2 = "select top 16 id,title,createTime from news where ntype=1 and status=1 order by id desc";
$News2 = $sqlsrv->FetchAll($sqlNews2);

//测评
$sqlNews3 = "select top 16 id,title,createTime from news where ntype=2 and status=1 order by id desc";
$News3 = $sqlsrv->FetchAll($sqlNews3);


//新游戏
$sqlgame = "select top 5 id,gTitle,icon,star,typename from game where (gClass=1 or gClass=2) and status=1 order by orderSort,id desc";
$game = $sqlsrv->FetchAll($sqlgame);

//广告
$sqlBanner = "select top 5 id,gTitle,smallBannerUrl,bigBannerUrl,apkLink from game where isBanner=1 and status=1 order by orderSort,id desc";
$Banner = $sqlsrv->FetchAll($sqlBanner);


$curPage = 'info';
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>游戏官网 - 相关资讯</title>
<meta name="keywords" content="游戏咨讯,游戏新闻,游戏活动,游戏公告,游戏专题,游戏壁纸,玩家靓照,游戏视频">
<meta name="description" content="每天实时汇总各游戏新闻、公告、活动、专题等新鲜资讯，再也不用担心会错过精彩！">
<link href="./css/common2.css" rel="stylesheet" type="text/css">
<link href="./css/home.css" rel="stylesheet" type="text/css">
<link href="./css/common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" async="" src="./js/ga.js"></script>
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
<script src="./js/SuperSlide2.1.js"></script>
<script type="text/javascript" src="./js/home.js"></script>
<script type="text/javascript" src="./js/kl_sso.js"></script>
<script id="b5mmain" type="text/javascript" charset="utf-8" src="./js/b5m.main.js"></script>

</head>
<body>
<!--header start-->
<?php include('header.php'); ?>
<!--header end--> 

<div id="zx">
<div id="container">
<div id="kfMain" style=" padding-top:20px;">
<!--  mainLeft -->
<div id="mLeft">
    <div class="rightTd" id="kjTD" style="background:#FBFBFB">
        <div style="background:#fff; border-bottom:4px solid #76A4C0; line-height:32px;"><h2 class="YH16">新游戏</h2></div>
        <ul class="leftKf clear">
        	<?php foreach($game as $val){ ?>
            <li><span><a target="_blank" href="gameDetail.php?gameid=<?php echo $val['id']; ?>"><?php echo $val['typename']; ?></a></span><img src="<?php echo $val['icon']; ?>" border="0" align="absmiddle" width="30" height="30"><a target="_blank" href="gameDetail.php?gameid=<?php echo $val['id']; ?>">&nbsp;<?php echo substr($val['gTitle'],0,15); ?></a></li>
            <?php } ?>
        </ul>
    </div>
    <?php foreach($Banner as $key=>$val){ ?>
    <div style="padding:5px 0;">
    <a target="_blank" href="gameDetail.php?gameid=<?php echo $val['id']; ?>" target="_blank"><img src="<?php echo $val['bigBannerUrl']; ?>" width="224" border="0"></a>
    </div>
	<?php } ?>
</div>

<!-- mainRight  -->
<div id="mRight">
<!--<ul id="zqNavtab" class="zxNav YH16">
<li class=""><a>新手卡专区</a></li>
<li class="now"><a>礼包专区</a></li>
</ul>
<div id="zxTabtxt">
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display: none;">
    <tbody>
        <tr>
            <td align="center"><a href="infoDetail.php"><img src="./images/20140512065446877.jpg" border="0"></a></td>
            <td align="center"><a href="infoDetail.php"><img src="./images/20140108112832129.jpg" border="0"></a></td>
            <td align="center"><a href="infoDetail.php"><img src="./images/20141022104914352.jpg" border="0"></a></td>
            <td align="center"><a href="infoDetail.php"><img src="./images/20131218015533813.jpg" border="0"></a></td>
        </tr>
    </tbody>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display: table;">
    <tbody>
        <tr>
            <td align="center"><a href="infoDetail.php"><img src="./images/20140512071522105.jpg" border="0"></a></td>
            <td align="center"><a href="infoDetail.php"><img src="./images/20140512065719111.jpg" border="0"></a></td>
            <td align="center"><a href="infoDetail.php"><img src="./images/20141022020950548.jpg" border="0"></a></td>
            <td align="center"><a href="infoDetail.php"><img src="./images/20140108112852923.jpg" border="0"></a></td>
        </tr>
    </tbody>
</table>
</div>-->
<ul id="newNavtab" class="zxNav YH16">
	<li class="now"><a>全部</a></li>
    <li><a>最新新闻</a></li>
    <li><a>最新测评</a></li>
</ul>
<div id="zxNewtxt">
<ul class="zx_newList" style="display:block">
	<?php foreach($News as $key=>$val){ ?>
    <li><span>[<?php echo substr($val['createTime'],0,10); ?>]</span><strong>·</strong><a href="infoDetail.php?id=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['title']; ?></a></li>
	<?php } ?>
    <div style="height:20px;"></div>
    <p style="width:100%; text-align:center; font-size:12px;"><?php echo page($totalnum,$limit); ?></p>
</ul>
<ul class="zx_newList">
	<?php foreach($News2 as $key=>$val){ ?>
    <li><span>[<?php echo substr($val['createTime'],0,10); ?>]</span><strong>·</strong><a href="infoDetail.php?id=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['title']; ?></a></li>
	<?php } ?>
</ul>
<ul class="zx_newList">
	<?php foreach($News3 as $key=>$val){ ?>
    <li><span>[<?php echo substr($val['createTime'],0,10); ?>]</span><strong>·</strong><a href="infoDetail.php?id=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['title']; ?></a></li>
	<?php } ?>
</ul>

</div>

<!--
<ul id="picNavtab" class="zxNav YH16">
    <li class=""><a>靓照</a></li>
    <li class=""><a>截图</a></li>
    <li class=""><a>壁纸</a></li>
    <li class="now"><a>视频</a></li>
</ul>
<div id="picScrolltxt">
<div style="height: 150px; display: none;" class="picScroll">
    <ul class="bd">
        <li><a href="#" target="_blank"><img src="./images/20131218064209234.jpg" border="0"></a></li>
        <li><a href="#" target="_blank"><img src="./images/20131218064126118.jpg" border="0"></a></li>
        <li><a href="#" target="_blank"><img src="./images/20140102065818274.png" border="0"></a></li>
        <li><a href="#" target="_blank"><img src="./images/20131218063925164.jpg" border="0"></a></li>
    </ul>
</div>
<div style="height: 150px; display: none;" class="picScroll">
    <ul class="bd">
        <li><a href="#" target="_blank"><img src="./images/20131218064938253.jpg" border="0"></a></li>
        <li><a href="#" target="_blank"><img src="./images/20131218064846150.jpg" border="0"></a></li>
        <li><a href="#" target="_blank"><img src="./images/20131218064736274.jpg" border="0"></a></li>
        <li><a href="#" target="_blank"><img src="./images/20131218064633580.jpg" border="0"></a></li>
    </ul>
</div>
<div style="height: 150px; display: none;" class="picScroll">
    <ul class="bd">
        <li><a href="#" target="_blank"><img src="./images/20131218065533997.jpg" border="0"></a></li>
        <li><a href="#" target="_blank"><img src="./images/20131218065439136.jpg" border="0"></a></li>
        <li><a href="#" target="_blank"><img src="./images/20131218065351687.jpg" border="0"></a></li>
        <li><a href="#" target="_blank"><img src="./images/20131218065258939.jpg" border="0"></a></li>
    </ul>
</div>
<div style="height: 150px; display: block;" class="picScroll">
    <ul class="bd">
        <li><a href="#" target="_blank"><img src="./images/20131218070320675.jpg" border="0"></a></li>
        <li><a href="#" target="_blank"><img src="./images/20131218070241775.jpg" border="0"></a></li>
        <li><a href="#" target="_blank"><img src="./images/20131218070208867.jpg" border="0"></a></li>
        <li><a href="#" target="_blank"><img src="./images/20131218070118274.jpg" border="0"></a></li>
    </ul>
</div>
-->

</div>
</div>
</div>
<div class="clear"></div>
</div>
</div>

<!--footer start:-->
<?php include('footer.php'); ?>
<!--footer end-->


</body>
</html>