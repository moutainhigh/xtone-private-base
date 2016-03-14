<?php
include("./admin/include/config.php");


//广告
$sqlBanner = "select top 4 id,gTitle,smallBannerUrl,bigBannerUrl,apkLink from game where isBanner=1 and status=1 order by orderSort,id desc";
$Banner = $sqlsrv->FetchAll($sqlBanner);

//单机
$sqlDJ = "select top 12 id,gTitle,icon,star,typename from game where gClass=1 and status=1 order by orderSort,id desc";
$DJ = $sqlsrv->FetchAll($sqlDJ);

//网游
$sqlWY = "select top 12 id,gTitle,icon,star,typename from game where gClass=2 and status=1 order by orderSort,id desc";
$WY = $sqlsrv->FetchAll($sqlWY);

//应用
$sqlYY = "select top 8 id,gTitle,icon,star,typename from game where gClass=3 and status=1 order by orderSort,id desc";
$YY = $sqlsrv->FetchAll($sqlYY);

//下载排行榜
$sqlPHB = "select top 9 id,gTitle,icon,star,typename from game where status=1 order by downloadCount desc";
$PHB = $sqlsrv->FetchAll($sqlPHB);

//热门新闻
$sqlNews = "select top 8 id,title,createTime from news where status=1 order by id desc";
$News = $sqlsrv->FetchAll($sqlNews);

$curPage = 'index';



?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>游戏官网 - 首页</title>
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./js/jquery.form.js"></script>
<script src="./js/common.js" type="text/javascript"></script>
<script src="./js/requestoptemp.js" type="text/javascript"></script>
<script src="./js/huanchang.js" type="text/javascript"></script>
<link href="./css/common.css" rel="stylesheet" type="text/css">

</head>
<body>
<!--header start-->
<?php include('header.php'); ?>
<!--header end--> 

<!--banner start:-->
<div class="size-w">
<div class="banner-fm">
<ul class="slider-pic" style="left: -1940px;">
	<?php foreach($Banner as $key=>$val){ ?>
    <li><img src="<?php echo $val['bigBannerUrl']; ?>" width="970" height="405"></li>
    <?php } ?>
</ul>
<ul class="small-pic iepng">
	<?php foreach($Banner as $key=>$val){ ?>
    <li class=""><img src="<?php echo $val['smallBannerUrl']; ?>" width="166" height="66"><em></em></li>
    <?php } ?>
</ul>
<ul class="download-fm">
	<?php foreach($Banner as $key=>$val){ ?>
    <li class=""><!--<a href="http://www.gamebean.com/down?eid%3D1600000595%26cid%3D1036%26pid%3D48" class="ios-button02" title="苹果越狱下载"></a>--> <a href="<?php echo $val['apkLink']; ?>" class="android-button" title="安卓版下载"></a></li>
	<?php } ?>
</ul>
</div>
</div>
<!--banner end-->


<div class="size-w">
    <div class="side-l"> 
        <!--快速通道 start:-->
        <div class="aisle-fm">
            <h1 title="快速通道"></h1>
            <ul>
                <li><a href="#" title="注册" class="aisle-nav01"></a></li>
                <li><a href="javascript:void(0)" onclick="myHuanchang();" title="账号" class="aisle-nav02"></a></li>
                <li><a href="#" title="充值" class="aisle-nav03"></a></li>
                <li><a href="#" title="客服" class="aisle-nav04"></a></li>
            </ul>
        </div>
        <!--快速通道 end--> 
        
    <!--下载排行榜 start:-->
    <div class="ranking-fm">
        <h1 title="下载排行榜"></h1>
        <div class="ranking-box">
        <ul>
        	<?php foreach($PHB as $key=>$val){ ?>
            
            <li class="<?php if($key==0){ echo 'one';}elseif($key==1){ echo 'two';}elseif($key==2){ echo 'three';}elseif($key==3){ echo 'four';}elseif($key==4){ echo 'five current';}elseif($key==5){ echo 'six';}elseif($key==6){ echo 'seven';}elseif($key==7){ echo 'eight';}else{echo 'nine';} ?>">
                <div><em></em><span class="gamename"><?php echo $val['gTitle']; ?></span><i><?php echo $val['typename']; ?></i></div>
                <dl>
                <dt><img src="<?php echo $val['icon']; ?>" width="60" height="60"></dt>
                <dd>
                <p>已下载<b><?php echo $val['downloadCount']; ?></b>万</p>
                <a href="gameDetail.php?gameid=<?php echo $val['id']; ?>" class="download-button-2" title="下载" target="_blank"></a></dd>
                </dl>
        	</li>
        	<?php } ?>
           
        </ul>
        </div>
    </div>
    <!--下载排行榜 end--> 
    
    
    <!--客服 start:-->
    <div class="customer-box">
      <h1 title="客服"></h1>
      <ul class="contact-box">
        <li><em class="tel"></em>电话：020-89205181</li>
        <li><em class="qq"></em>邮箱：2074455273@qq.com</li>
      </ul>
      <div class="weixin-box">
<div class="weixin-code-box"><img src="./images/130.png" width="110" height="110"></div>
        <div class="weibo-box">
  <p><a href="#" class="sina-icon" target="_blank"></a><a href="#" class="customer-button" target="_blank">关注新浪微博</a></p>
  <p><a href="#" class="tencent-icon" target="_blank"></a><a href="#" class="customer-button" target="_blank">关注腾讯微博</a></p>
          <p>扫描二维码<br>
            <span class="weixin-name">116114app</span></p>
        </div>
      </div>
    </div>
    <!--客服 end--> 
  </div>
  
  
<div class="side-r"> 
    <!--热门网游 start:-->
    <div class="hot-fm">
		<h1 title="热门网游"><span></span></h1>
		<ul>
			<?php foreach($WY as $val){ ?>
			<li>
				<a href="gameDetail.php?gameid=<?php echo $val['id']; ?>" target="_blank"><img src="<?php echo $val['icon']; ?>" width="70" height="70"></a> <span> <strong><a href="gameDetail.php?gameid=<?php echo $val['id']; ?>" target="_blank"><?php echo substr($val['gTitle'],0,15); ?></a></strong><p><em><?php echo $val['typename']; ?></em></p><p id="1600000495" class="version all01"></p></span>
			</li>
			<?php } ?>
		</ul>
    </div>
	<!--热门网游 end--> 
	
	 <div class="hot-fm">
		<h1 title="精品单机游戏"  style="background-position:0 -527px;";><span></span></h1>
		<ul>
			<?php foreach($DJ as $val){ ?>
			<li>
				<a href="gameDetail.php?gameid=<?php echo $val['id']; ?>" target="_blank"><img src="<?php echo $val['icon']; ?>" width="70" height="70"></a> <span> <strong><a href="gameDetail.php?gameid=<?php echo $val['id']; ?>" target="_blank"><?php echo substr($val['gTitle'],0,15); ?></a></strong><p><em><?php echo $val['typename']; ?></em></p><p id="1600000495" class="version all01"></p></span>
			</li>
			<?php } ?>
		</ul>
    </div>
	<div class="hot-fm">
		<h1 title="精品应用"  style="background:url(images/app.jpg) no-repeat;"><span></span></h1>
		<ul>
			<?php foreach($YY as $val){ ?>
			<li>
				<a href="gameDetail.php?gameid=<?php echo $val['id']; ?>" target="_blank"><img src="<?php echo $val['icon']; ?>" width="70" height="70"></a> <span> <strong><a href="gameDetail.php?gameid=<?php echo $val['id']; ?>" target="_blank"><?php echo substr($val['gTitle'],0,15); ?></a></strong><p><em><?php echo $val['typename']; ?></em></p><p id="1600000495" class="version all01"></p></span>
			</li>
			<?php } ?>
		</ul>
    </div>
        
        
    <!--精品单机游戏 start:-->
	<!--<div class="single-fm">
      <h1 title="精品单机游戏"><span></span></h1>
      <span class="pre" title="向左滚动"></span> <span class="next" title="向右滚动"></span>
      <div class="ibox">
        <div class="showbox" style="left: 0px;">
          <ul>
			<?php //foreach($DJ as $val){ ?>
			<li><p><a href="gameDetail.php?gameid=<?php //echo $val['id']; ?>" target="_blank"><img src="<?php //echo $val['icon']; ?>" width="85" height="85" alt=""></a></p><p><?php echo $val['gTitle']; ?></p></li>
    		<?php //} ?>
          </ul>
        </div>
      </div>
    </div>-->
    <!--精品单机游戏 end--> 
    
    <!--热门专题 start:-->
    <div class="zt-hot-fm">
    <h1 title="热门专题"><span></span></h1>
    <ul>
    
    
    <li>
    <p><a href="#" target="_blank"><img src="./images/267.JPG" width="211" height="88"></a></p>
    <p class="text"><a href="#" target="_blank">妖怪哪里跑 抢特权大礼包</a></p>
    </li>
    
    <li>
    <p><a href="#" target="_blank"><img src="./images/266.jpg" width="211" height="88"></a></p>
    <p class="text"><a href="#" target="_blank">冲击LV8 现场约会男神</a></p>
    </li>
    
    <li>
    <p><a href="#" target="_blank"><img src="./images/265.jpg" width="211" height="88"></a></p>
    <p class="text"><a href="#" target="_blank">3D坦克争霸全新指挥官系统</a></p>
    </li>
    
    
    
    </ul>
    </div>
    <!--热门专题 end-->
    
    <div class="bottom-fm"> 
      <!--热门新闻 start:-->
      <div class="news-fm">
        <h1 title="热门新闻"><span></span></h1>
        <ul>
        	<?php foreach($News as $key=>$val){ ?>
            <li class="<?php if($key==0){ echo 'one';}elseif($key==1){ echo 'two';}elseif($key==2){ echo 'three';}elseif($key==3){ echo 'four';}elseif($key==4){ echo 'five';}elseif($key==5){ echo 'six';}elseif($key==6){ echo 'seven';}elseif($key==7){ echo 'eight';}else{echo 'nine';} ?>"><em></em><a href="infoDetail.php?id=<?php echo $val['id']; ?>" class="text" target="_blank"><?php echo $val['title']; ?></a><i class="data"><?php echo substr($val['createTime'],0,10); ?></i></li>
            <?php } ?>
        </ul>
      </div>
      <!--热门新闻 end--> 
    </div>
  </div>
</div>


<!--footer start:-->
<?php include('footer.php'); ?>
<!--footer end-->

</body>
</html>