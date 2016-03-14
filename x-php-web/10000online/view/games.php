<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0038)http://www.cmge.com/category/game.html -->
<html xmlns="http://www.w3.org/1999/xhtml" style="">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>游戏中心-捷酷软件-国际领先的移动游戏开发商与发行商</title>
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

<div class="sub_top">
  <div class="colum"><!-- 推荐游戏 -->
    <div class="mnbb">
      <div class="hd">
        <h3>推荐游戏</h3>
      </div>
      <div class="bd">
        <div class="slides">
          <div class="slides_container"><!-- 第一屏 -->
            <div class="slides_control">
              <div>
                <dl class="dl4">
      			<?php if($hotgames){ foreach($hotgames as $val){ ?>
                  <dd><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>" target="_blank"><img src="<?php echo $val['bigBannerUrl']; ?>" alt="" width="386" height="160" class="pic"><em><img src="./images/hot.png" alt="热门"></em>
                    <h3></h3>
                    <h4></h4>
                    <span><b><?php echo $val['gTitle']; ?>&nbsp;&nbsp;&nbsp;&nbsp;<?php echo $val['typename']; ?></b>
                    <p><?php echo substr($val['introduce'],0,120); ?></p>
                    <img src="./images/go_in.png" alt="进入官网"></span></a></dd>
        		<?php }} ?>
                </dl>
              </div>
            </div>
           </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- 中间 -->
<div class="sub_wrap">
  <div class="wrap"><!--选择-->
    <dl class="dlchose">
      <dd>
        <h3>游戏平台</h3>
        <p><a href="#" class="cur">不限</a><a href="#">Android</a></p>
      </dd>
      
      <dd>
        <h3>游戏类型</h3>
        <p>
        	<a href="index.php?controller=games&action=index&tid=0" <?php if($tid==0){ echo 'class="cur"';} ?>>不限</a>
            <?php if($gType){ foreach($gType as $val){ ?>
        	<a title="<?php echo $val['gt_title']; ?>" href="index.php?controller=games&action=index&tid=<?php echo $val['gt_id']; ?>"  <?php if($tid==$val['gt_id']){ echo 'class="cur"';} ?>><?php echo $val['gt_title']; ?></a>
            <?php }} ?>
        </p>
      </dd>
    </dl>
    <!-- 知名品牌logo -->
    <div class="dl_game">
      <dl>
      	<?php if($games){ foreach($games as $val){ ?>
        <dd><span><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>" target="_blank"><img src="<?php echo $val['icon']; ?>" alt=""></a></span>
          <h5><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['gTitle']; ?></a></h5>
          <p><?php echo $val['apkSize']; ?></p>
          <p><b><?php echo $val['typename']; ?></b></p>
          <div class="down"><a href="<?php echo $val['apkLink']; ?>" target="_blank" class="down_an">Android版</a></div>
        </dd>
          <?php }} ?>
      </dl>
    </div>
    <!-- 分页 -->
    <p style="width:100%; text-align:center; font-size:12px;"><?php echo $page; ?></p>
  </div>
</div>

<?php include("footer.php"); ?>
</body>
</html>