<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0037)http://www.cmge.com/article/1659.html -->
<html xmlns="http://www.w3.org/1999/xhtml" style="">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><?php echo $data['ntitle']; ?> -捷酷软件-国际领先的移动游戏开发商与发行商</title>
<link href="./css/global.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./js/common.js"></script>

<script src="./js/txz.js" type="text/javascript"></script>
<script type="text/javascript" src="./js/css3-mediaqueries.js"></script>
<script id="b5mmain" type="text/javascript" charset="utf-8" src="./js/b5m.main.js"></script>
</head>
<!-- 内部添加css -->
<body>
<?php include('header.php'); ?>

<!-- 中间 -->
<div class="sub_wrap">
  <div class="wrap">
    <div class="sub_left"><!-- 当前位置 -->
      <div class="statue"> 现在的位置：<a href="index.php?controller=index" title="">首页</a>»<a href="index.php?controller=news">动态</a></div>
      <div class="newshow_all">
        <div class="newshow_tit">
          <h3><?php echo $data['ntitle']; ?></h3>
          <h4><!--<a href="javascript:history.back(-1)" target="_blank">返回上一页</a>&nbsp;&nbsp;<a title="活动中心" href="http://www.cmge.com/category/activity.html">活动中心</a>&nbsp;&nbsp;-->来源:<?php echo $data['nsource']; ?>&nbsp;&nbsp;发布时间:<?php echo $data['ncreateTime']; ?>&nbsp;&nbsp;分类:<?php echo $data['ntName']; ?><!--浏览次数:<b id="click_num">19</b>--></h4>
        </div>
        <div class="newshow_cont">
        <?php echo $data['ncontent']; ?>
        </div>
        <div class="newshow_bottxt">
          <?php if($pre['nid']){  ?>
          <p>上一篇：<a href="index.php?controller=news&action=newsDetail&nid=<?php echo $pre['nid']; ?>" title="<?php echo $pre['ntitle']; ?>"><?php echo $pre['ntitle']; ?></a></p>
          <?php } ?>
          
          <?php if($next['nid']){  ?>
          <p>下一篇：<a href="index.php?controller=news&action=newsDetail&nid=<?php echo $next['nid']; ?>" title="<?php echo $next['ntitle']; ?>"><?php echo $next['ntitle']; ?></a></p>
          <?php } ?>
        </div>
      </div>
    </div>
    <!-- 左边 -->
    <div class="sub_right"><!-- 热门游戏 -->
      <div class="rmyx">
        <div class="hd">
          <h3>热门游戏与应用</h3>
        </div>
        <div class="bd">
          <div class="dl12">
            <dl>
      		<?php if($games){ foreach($games as $val){ ?>
              <dd><span><a target="_blank" title="<?php echo $val['gTitle']; ?>" href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>"><img alt="<?php echo $val['gTitle']; ?>" src="<?php echo $val['icon']; ?>"></a></span>
                <h3><a target="_blank" title="<?php echo $val['gTitle']; ?>" href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>"><?php echo $val['gTitle']; ?></a></h3>
              </dd>
            <?php }} ?>
            </dl>
          </div>
        </div>
      </div>
      <!-- 热门帖子 -->
      <div class="news news_style">
        <div class="hd">
          <h3>最新动态</h3>
        </div>
        <dl class="dl3">
          <?php if($hotnews){ foreach($hotnews as $val){ ?>
          <dd><a href="index.php?controller=news&action=newsDetail&nid=<?php echo $val['nid']; ?>" title="<?php echo $val['ntitle']; ?>"><em>[<?php echo $val['ntName']; ?>]</em><?php echo $val['ntitle']; ?></a></dd>
          <?php }} ?>
        </dl>
      </div>
    </div>
  </div>
</div>


<?php include("footer.php"); ?>
</body>
</html>