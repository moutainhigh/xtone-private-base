<?php
include("./admin/include/config.php");
include("./admin/include/page.php");

$gClass = isset($_GET['gClass']) ? $_GET['gClass'] : 1;
$gClass = intval($gClass);
if($gClass==2){
	$gName = "网游";
}elseif($gClass==3){
	$gName = "应用";
}else{
	$gName = "单机";
}

//获取翻页列表。
$page = intval($_GET['pn']) ? intval($_GET['pn']) :1;
$page = intval($page);
//获取总记录数。
$totalnum = array();
$sqlrow = "SELECT count(id) FROM game where gClass=$gClass and status=1";
$queryrow = sqlsrv_query($con, $sqlrow);
$rows = sqlsrv_fetch_array($queryrow);
$totalnum = $rows[0];

//计算总页数。
$limit = 15;//每页显示的数量。
$totalpages = ceil($totalnum/$limit);
$offset = ($page-1)*$limit;
$sqlType="select top $limit g.id,* from game AS g where g.id not in (select top $offset id from game where gClass=$gClass and status=1 ORDER BY orderSort,id DESC) AND gClass=$gClass and status=1 ORDER BY orderSort,g.id DESC";
//print_r($sqlType);exit;

//$sqlType = "select * from game where gClass=2 and status=1 order by orderSort,id desc";
$type = $sqlsrv->FetchAll($sqlType);

$sqlR = "select top 4 * from game where gClass=$gClass and status=1 order by id desc";
$R = $sqlsrv->FetchAll($sqlR);

$curPage = 'gameCenter';
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>游戏官网 - 游戏中心</title>
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./js/jquery.form.js"></script>
<script src="./js/common.js" type="text/javascript"></script>
<script type="text/javascript" src="./js/JSON2.js"></script>
<script type="text/javascript" src="./js/verific.js"></script>
<script type="text/javascript" src="./js/requestoptemp.js"></script>
<script type="text/javascript" src="./js/huanchang.js"></script>
<script src="./js/common.js" type="text/javascript"></script>
<link href="./css/common.css" rel="stylesheet" type="text/css">
<link href="./css/product.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
$().ready(function(){
       setSelected('mode');
})
</script>
<script language="javascript" type="text/javascript">
$(function(){
    showLogin('userlogin','login','');
})
</script>
</head>
<body>

<!--header start-->
<?php include('header.php'); ?>
<!--header end--> 

<div class="size-w">
  <h1 class="nav-title"><i></i>您现在的位置：<a href="index.php">首页</a>&gt;<span>游戏中心</span></h1>
  <!--网游筛选 start:-->
  <div class="find-box">
    <h1><?php echo $gName; ?>筛选<a href="gameCenter.php?gClass=1" class="sort-button3" title="<?php echo $gName; ?>分类">单机分类</a><a href="gameCenter.php?gClass=2" class="sort-button2" title="<?php echo $gName; ?>分类">网游分类</a><a href="gameCenter.php?gClass=3" class="sort-button" title="<?php echo $gName; ?>分类">应用分类</a></h1>
    <dl id="select1">
      <dt>平台：</dt>
      <dd id="0000" class="select-all selected"><a href="javascript:void(0)" onclick="queryGame(&#39;&#39;,&#39;0000&#39;,&#39;2&#39;,&#39;mode&#39;)">安卓</a></dd>
    </dl>
    <dl id="select2">
      <dt>特征：</dt>
      <dd class="select-all"><a id="1169" href="javascript:void(0)" class="selected">全部</a></dd>
      <dd><a id="1147" class="on" href="javascript:void(0)">卡牌</a></dd>
      <dd><a id="1148" href="javascript:void(0)">竞技</a></dd>
      <dd><a id="1149" href="javascript:void(0)">武侠</a></dd>
  
    </dl>
  </div>
  <!--网游筛选 end-->
  <div class="product-list-fm"> 
    <!--左侧 start:-->
    <div class="product-list-box-l">
      
<div class="find-list-box">
<ul>
    <?php foreach($type as $val){ ?>
    <li><a href="gameDetail.php?gameid=<?php echo $val['id']; ?>" class="img" target="_blank">
        <img width="95" height="95" src="<?php echo $val['icon']; ?>"></a>
        <span>
            <strong><a href="gameDetail.phpgameid=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['gTitle']; ?></a></strong>
            <p><?php echo $val['apkSize']; ?></p>
            <p class="star-small"><span style="width:84%;"></span></p>
            <p><em><?php echo $val['typename']; ?></em></p>
        </span>
        <div class="down-box"><a href="gameDetail.php?gameid=<?php echo $val['id']; ?>"  title="游戏下载" class="download-button-1" target="_blank">立即下载</a><em>|</em><?php echo substr($val['createTime'],0,7); ?></div>
    </li>
    <?php } ?>
    <div style="clear:both"></div>
</ul>

<div style="height:20px;"></div>
<p style="width:100%; text-align:center; font-size:12px;"><?php echo page($totalnum,$limit); ?></p>
<div style="height:30px;"></div>
</div>
</div>
<!--左侧 end-->



<div class="product-list-box-r"> 
<!--关联发号 start:-->
<div class="experience-fm">
<h1 title="新游推荐"><span></span></h1>
<ul>    
<?php foreach($R as $val){ ?>
<li>
    <dl>
    <dt><img src="<?php echo $val['icon']; ?>" width="85" height="85"></dt>
    <dd><strong><a href="gameDetail.php?gameid=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['gTitle']; ?></a></strong>
    <p>上线时间：<?php echo substr($val['createTime'],0,7); ?></p>
    <p>游戏大小：<?php echo $val['apkSize']; ?></p>
    <p>游戏类型：<?php echo $val['typename']; ?></p>
    </dd>
    </dl>
    <span><!--<a class="packs-button" title="礼包领取" href="#">礼包领取</a>--><a class="download-button" title="游戏下载" href="gameDetail.php?gameid=<?php echo $val['id']; ?>" target="_blank">游戏下载</a></span> 
</li>
<?php } ?>
</ul>
</div>
<!--关联发号 end--> 
</div>
</div>
</div>


<!--footer start:-->
<?php include('footer.php'); ?>
<!--footer end-->
</body>
</html>