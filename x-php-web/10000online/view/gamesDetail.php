<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>游戏详情 -捷酷软件-国际领先的移动游戏开发商与发行商</title>
<link href="./css/product.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./js/common.js"></script>
<script src="./js/txz.js" type="text/javascript"></script>
<script type="text/javascript" src="./js/css3-mediaqueries.js"></script>
<script id="b5mmain" type="text/javascript" charset="utf-8" src="./js/b5m.main.js"></script>

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

<script type="text/javascript" src="./js/g/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./js/g/jquery.form.js"></script>
<script src="./js/g/common.js" type="text/javascript"></script>
<script src="./js/g/requestoptemp.js" type="text/javascript"></script>
<script src="./js/g/huanchang.js" type="text/javascript"></script>
</head>
<!-- 内部添加css -->
<body>
<?php include('header.php'); ?>

<!-- 中间 -->
<div class="sub_wrap">
  <div class="wrap">
    <div class="sub_left">
      
      <div class="product-list-fm"> 
    <!--左侧 start:-->
    <div class="detail-l">
      <h1><?php echo $ArrRow['gTitle']; ?></h1>
      <div class="d-game-box"> <span class="game-pic"><img src="<?php echo $ArrRow['icon']; ?>" width="155" height="155"></span>
        <div class="game-link-box">
          <p><em>类型：<?php echo $ArrRow['typename']; ?></em></p>
          <p><em>上架：<?php echo substr($ArrRow['createTime'],0,10); ?></em></p>
          <div id="website" class="d-button-box"><a href="<?php echo $ArrRow['apkLink']; ?>" class="button" style="background:#F90;"><i class="icon03"></i>游戏下载</a></div>
        </div>
      </div>
      
      <!--猜你喜欢 start:-->
      <h1>猜您喜欢</h1>
      <div class="d-game-box">
        <ul class="game-list">
      		<?php if($games){ foreach($games as $val){ ?>
            <li><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>"><img width="80" height="80" src="<?php echo $val['icon']; ?>"></a> 
            <span>
            <strong><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['gTitle']; ?></a></strong>
            <p class="star-small">类型：<?php echo $val['typename']; ?></p>
            <p class="star-small">大小：<?php echo $val['apkSize']; ?></p>
            </span> 
            </li>
            <?php }} ?>
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
        	<?php foreach($hotnews as $key=>$val){ ?>
            <li><span><a href="index.php?controller=news&action=newsDetail&nid=<?php echo $val['nid']; ?>" target="_blank"><?php echo $val['ntitle']; ?></a></span><em><?php echo substr($val['ncreateTime'],0,10); ?></em></li>
            <?php } ?>
        </ul>
        <a href="index.php?controller=news&action=index" class="more" target="_blank" title="更多">更多</a>
        </div>
      
    </div>
    <!--右侧 end--> 
  </div>
  
    </div>
  </div>
</div>


<?php include("footer.php"); ?>
</body>
</html>