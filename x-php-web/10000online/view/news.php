<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0038)http://www.cmge.com/category/news.html -->
<html xmlns="http://www.w3.org/1999/xhtml" style="">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>动态-捷酷软件-国际领先的移动游戏开发商与发行商</title>
<link href="./css/global.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./js/common.js"></script>
<script src="./js/txz.js" type="text/javascript"></script>
<script type="text/javascript" src="./js/banner.js"></script>

<script type="text/javascript" src="./js/css3-mediaqueries.js"></script>
<script id="b5mmain" type="text/javascript" charset="utf-8" src="./js/b5m.main.js"></script>
</head>
<body>
<?php include('header.php'); ?>

<!-- 中间 -->
<div class="sub_wrap">
  <div class="wrap"><!--colum-->
    <div class="colum"><!-- 最新活动 -->
      <div class="mnbb">
        <div class="hd">
          <h3>推荐游戏</h3>
        </div>
        <div class="bd">
          <div class="dl9">
            <dl>
      			<?php if($hotgames){ foreach($hotgames as $val){ ?>
               <dd><span><a target="_blank" title="<?php echo $val['gTitle']; ?>" href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>"><img alt="<?php echo $val['gTitle']; ?>" src="<?php echo $val['bigBannerUrl']; ?>"></a></span>
                <h3><a target="_blank" title="<?php echo $val['gTitle']; ?>" href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>"><?php echo $val['gTitle']; ?></a></h3>
              </dd>
              <?php }} ?>
            </dl>
          </div>
        </div>
      </div>
    </div>
    <!-- 左边 -->
    <div class="sub_left"><!-- 分类选择 -->
      <div class="dl13">
        <dl>
          <dd><a <?php if($ntype==1){ echo 'class="cur"';} ?> title="游戏新闻" href="index.php?controller=news">游戏新闻</a></dd>
          <dd><a <?php if($ntype==2){ echo 'class="cur"';} ?> title="行业资讯" href="index.php?controller=news&action=industry">行业资讯</a></dd>
          <dd><a <?php if($ntype==3){ echo 'class="cur"';} ?> title="游戏公告" href="index.php?controller=news&action=notice">游戏公告</a></dd>
        </dl>
      </div>
      <!-- 新闻列表 -->
      <div class="dl14">
        <dl>
          <?php if($news){ foreach($news as $val){ ?>
          <dd>
            <h3><a target="_blank" title="<?php echo $val['ntitle']; ?>" href="index.php?controller=news&action=newsDetail&nid=<?php echo $val['nid']; ?>"><?php echo $val['ntitle']; ?></a><?php echo $val['ncreateTime']; ?></h3>
            <p><?php echo $val['ninfor']; ?></p>
          </dd>
          <?php }} ?>
        </dl>
      </div>
      <!-- 分页 -->
      <p style="width:100%; text-align:center; font-size:12px;"><?php echo $page; ?></p>
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