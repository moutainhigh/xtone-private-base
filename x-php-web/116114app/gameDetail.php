<?php
include("./admin/include/config.php");


$gameid = intval($_REQUEST['gameid']);
$sqlOne = "select * from game AS g LEFT JOIN gType AS gt ON g.gType=gt.gt_id where g.id='$gameid'";
$ArrRow = $sqlsrv->FetchOne($sqlOne);

$screenshot = explode("|",  $ArrRow['screenshot']);
$count = count($screenshot,0);

//热门新闻
$sqlNews = "select top 6 * from news where status=1 order by id desc";
$News = $sqlsrv->FetchAll($sqlNews);


$curPage = 'gameCenter';
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>游戏官网 - 游戏详情</title>
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./js/jquery.form.js"></script>
<script src="./js/common.js" type="text/javascript"></script>
<script src="./js/requestoptemp.js" type="text/javascript"></script>
<script src="./js/huanchang.js" type="text/javascript"></script>
<link href="./css/common.css" rel="stylesheet" type="text/css">
<link href="./css/product.css" rel="stylesheet" type="text/css">
 
<script>
$(function(){
    var $cur = 1;
    var $i = 1;
    var $len = $('.showbox>ul>li').length;
    var $pages = Math.ceil($len / $i);
    var $w = $('.ibox').width();
    var $showbox = $('.showbox');
    var $pre = $('.left-arrow')
    var $next = $('.right-arrow');
    var $autoFun;
    autoSlide();
    $pre.click(function(){
        if (!$showbox.is(':animated')) { 
            if ($cur == 1) { 
                $showbox.animate({
                    left: '-=' + $w * ($pages - 1)
                }, 500);                 
				$cur = $pages; 
            }
            else {
                $showbox.animate({
                    left: '+=' + $w
                }, 500); 
                $cur--; 
            }
        }
    });
    $next.click(function(){
        if (!$showbox.is(':animated')) { 
            if ($cur == $pages) { 
                $showbox.animate({
                    left: 0
                }, 500);
                $cur = 1; 
            }
            else {
                $showbox.animate({
                    left: '-=' + $w
                }, 500);
                $cur++; 
            }
        }
    });
    clearFun($showbox);
    clearFun($pre);
    clearFun($next);
    function clearFun(elem){
        elem.hover(function(){
            clearAuto();
        }, function(){
            autoSlide();
        });
    }
    function autoSlide(){
        $next.trigger('click');
        $autoFun = setTimeout(autoSlide, 3000);
    }
    function clearAuto(){
        clearTimeout($autoFun);
    }
});
$(function(){
	$('.left-arrow,.right-arrow').hover(function(){
			$(this).fadeTo('fast',0.5);
		},function(){
			$(this).fadeTo('fast',1);
	})

})
</script>
</head>
<body>

<!--header start-->
<?php include('header.php'); ?>
<!--header end--> 

<div class="size-w">
  <h1 class="nav-title"><i></i>您现在的位置：<a href="http://www.116114app.com/">首页</a>&gt;<a href="gameDetail.php">游戏中心</a>&gt;<span><?php echo $ArrRow['gTitle']; ?></span></h1>
  <div class="product-list-fm"> 
    <!--左侧 start:-->
    <div class="detail-l">
      <h1><?php echo $ArrRow['gTitle']; ?></h1>
      <div class="d-game-box"> <span class="game-pic"><img src="<?php echo $ArrRow['icon']; ?>" width="155" height="155"></span>
        <div class="game-link-box">
          <p><em>类型：<?php echo $ArrRow['gt_title']; ?></em></p>
          <p><em>上架：<?php echo substr($ArrRow['createTime'],0,10); ?></em></p>
          <div id="website" class="d-button-box"><a href="#" target="_blank" title="游戏官网" class="button"><i class="icon03"></i>游戏官网</a></div>
        </div>
      </div>
      <!--下载地址 start:-->
      <h1>下载地址</h1>
      <div class="d-game-box">
        <div class="game-link-box">
          <ul class="d-button-box">
          
<li id="appstored" style="display: none;"><a href="#" target="_blank" title="苹果正版下载" onmousemove="$(&#39;#code01&#39;).show()" onmouseout="$(&#39;#code01&#39;).hide()"><i class="icon01"></i>苹果正版下载</a>
<div class="code-fm" id="code01"><img width="120" height="120" src=""></div>
</li>

<li id="iosd" style="display: none;"><a href="#" target="_blank" title="苹果越狱版下载" onmousemove="$(&#39;#code02&#39;).show()" onmouseout="$(&#39;#code02&#39;).hide()"><i class="icon01"></i>苹果越狱版下载</a>
<div class="code-fm" id="code02"><img width="120" height="120" src=""></div>
</li>

<li id="andd"><a href="<?php echo $ArrRow['apkLink']; ?>" target="_blank" title="安卓官网下载" onmousemove="$(&#39;#code03&#39;).show()" onmouseout="$(&#39;#code03&#39;).hide()"><i class="icon01"></i>安卓官网下载</a>
<!--<div class="code-fm" id="code03"><img width="120" height="120" src="./images/605_256.png"></div>-->
</li>

           <li class="tips">提示:鼠标移动至下载按钮上可见二维码</li>
          </ul>
        </div>
      </div>
      <!--下载地址 end--> 
      <!--猜你喜欢 start:-->
      <h1>猜您喜欢</h1>
      <div class="d-game-box">
        <ul class="game-list">
         
<li><a href="#"><img width="80" height="80" src="./images/482.png"></a> 
<span>
<strong><a href="#" target="_blank">饥饿鲨进化</a></strong>
<p class="star-small"><span style="width:100%;"></span></p>
<p id="1600000482" class="version android"></p>
</span> 
</li>         
<script type="text/javascript">
var platforms = "0";
var sid = "1600000482"
switch(platforms){
    case "0":$('#'+sid).attr("class", "version android");break;
    case "1":$('#'+sid).attr("class", "version ios");break;
    case "2":$('#'+sid).attr("class", "version xp");break;
    case "3":break;
    case "01":$('#'+sid).attr("class", "version all01");break;
    case "02":$('#'+sid).attr("class", "version all02");break;
    case "12":$('#'+sid).attr("class", "version all03");break;
    case "012":$('#'+sid).attr("class", "version all");break;
}
</script>         

<li><a href="#"><img width="80" height="80" src="./images/481.png"></a> 
<span>
<strong><a href="#" target="_blank">疯狂出租车</a></strong>
<p class="star-small"><span style="width:100%;"></span></p>
<p id="1600000481"></p>
</span> 
</li>         
<script type="text/javascript">
var platforms = "null";
var sid = "1600000481"
switch(platforms){
    case "0":$('#'+sid).attr("class", "version android");break;
    case "1":$('#'+sid).attr("class", "version ios");break;
    case "2":$('#'+sid).attr("class", "version xp");break;
    case "3":break;
    case "01":$('#'+sid).attr("class", "version all01");break;
    case "02":$('#'+sid).attr("class", "version all02");break;
    case "12":$('#'+sid).attr("class", "version all03");break;
    case "012":$('#'+sid).attr("class", "version all");break;
}
</script>         

<li><a href="#"><img width="80" height="80" src="./images/476.png"></a> 
<span>
<strong><a href="#" target="_blank">妖怪哪里跑</a></strong>
<p class="star-small"><span style="width:100%;"></span></p>
<p id="1600000476" class="version all01"></p>
</span> 
</li>         
<script type="text/javascript">
var platforms = "01";
var sid = "1600000476"
switch(platforms){
    case "0":$('#'+sid).attr("class", "version android");break;
    case "1":$('#'+sid).attr("class", "version ios");break;
    case "2":$('#'+sid).attr("class", "version xp");break;
    case "3":break;
    case "01":$('#'+sid).attr("class", "version all01");break;
    case "02":$('#'+sid).attr("class", "version all02");break;
    case "12":$('#'+sid).attr("class", "version all03");break;
    case "012":$('#'+sid).attr("class", "version all");break;
}
</script>         

        </ul>
      </div>
      <!--猜你喜欢 end--> 
    </div>
    <!--左侧 end--> 
    <!--右侧 start:-->
    <div class="detail-r"> 
      <!--游戏简介 start:-->
      <div id="gamedesc">
      <h1><span>游戏简介</span></h1>
      <div class="game-introduction"><?php echo $ArrRow['introduce']; ?></div>
      </div>
      <!--游戏简介 end--> 
      <!--游戏截图 start:-->
      <h1><span>游戏截图</span></h1>
      <div class="game-scroll-pic"><span class="left-arrow" title="向左滚动">&lt;</span><span class="right-arrow" title="向右滚动">&gt;</span>
        <div class="ibox" id="game-show-pic">
          <div class="showbox" style="left: -1127.3761487720435px;">
            <ul>
            	<?php for($i=0;$i<$count;$i++){  ?>
            	<li><img src="<?php echo $screenshot[$i]; ?>" width="585" height="255"></li>
                <?php } ?>
            </ul>
          </div>
        </div>
      </div>
      <!--游戏截图 end--> 
      
      <!--游戏新闻 start:-->
      <div id="gamenews">
      <div style="display:none">1531</div>
      <h1><span>游戏新闻</span></h1>
      <div class="game-info">
        <ul>
        	<?php foreach($News as $key=>$val){ ?>
            <li><span><a href="infoDetail.php?id=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['title']; ?></a></span><em><?php echo substr($val['createTime'],0,10); ?></em></li>
            <?php } ?>
        </ul>
        <a href="info.php" class="more" target="_blank" title="更多">更多</a>
        </div>
      
    </div>
    <!--右侧 end--> 
  </div>
</div>
<!--footer start:-->
<?php include('footer.php'); ?>
<!--footer end-->
</body>
</html>