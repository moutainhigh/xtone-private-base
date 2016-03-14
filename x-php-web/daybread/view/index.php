<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>万荟数码-国际领先的移动游戏开发商与发行商</title>
<!--<meta name="Keywords" content="中国手游,中国手游,cmge,安卓,安卓游戏,安卓手机游戏,安卓">
<meta name="Description" content="中国手游拥有实力强劲的开发团队与发行团队，专注于移动互联网游戏产品的代理和发行，致力于打造中国Android手机网游第一平台，为Android手机用户提供一站式游戏服务。">-->

<!--<link rel="icon" href="http://image.cmge.com/cmgecom/themes/ico.ico" type="image/x-icon">
<link rel="shortcut icon" href="http://image.cmge.com/cmgecom/themes/ico.ico" type="image/x-icon">-->
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

<!-- 幻灯片代码 开始 -->
<div class="banner">
  <div id="zSlider">
    <div id="picshow">
      <div id="picshow_img">
        <ul>
          <?php if($banner){ foreach($banner as $key=>$val){ ?>
          <li style="display: none;"><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>" target="_blank"><img src="<?php echo $val['bigBannerUrl']; ?>"></a></li>
          <?php }} ?>
        </ul>
      </div>
    </div>
    <div id="select_btn">
      <ul>
       <?php if($banner){ foreach($banner as $key=>$val){ ?>
        <li class="" style="border: none;"><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>" target="_blank"><span class="select_pic"><img src="<?php echo $val['bigBannerUrl']; ?>" alt=""></span><span class="select_text"><?php echo $val['gTitle']; ?></span><span class="select_date"></span><span class="select_more">查看详情</span></a></li>
         <?php }} ?>
      </ul>
    </div>
  </div>
</div>
<!-- 代码 结束 --><!-- 中间 -->
<div class="wrap"><!--colum-->
  <div class="colum"><!-- 推荐游戏 -->
    <div class="mnbb">
      <div class="hd"><a href="http://www.cmge.com/category/game.html">更多</a>
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
  <!--colum-->
  <div class="colum"><!-- 新闻动态 -->
    <div class="news indexnew1" style="margin-right:50px;">
      <div class="hd"><a href="index.php?controller=news">更多测评</a>
        <h3>游戏测评</h3>
      </div>
      <dl class="dl3">
        <dt><img src="./images/1427968274_1525197333.jpg">
          <h3><a href="#">《新仙剑奇侠传》御用配音团队曝光 </a></h3>
        </dt>
        <?php if($news){ foreach($news as $val){ ?>
        <dd><a target="_blank" title="<?php echo $val['ntitle']; ?>" href="index.php?controller=news&action=newsDetail&nid=<?php echo $val['nid']; ?>"><?php echo $val['ntitle']; ?></a></dd>
        <?php }} ?>
      </dl>
    </div>
    <!-- 论坛动态 -->
    <div class="news indexnew2">
      <div class="hd"><a href="index.php?controller=news">更多热门新闻</a>
        <h3>热门新闻</h3>
      </div>
      <dl class="dl3">
        <?php if($hotnews){ foreach($hotnews as $val){ ?>
        <dd><a href="index.php?controller=news&action=newsDetail&nid=<?php echo $val['nid']; ?>" target="_blank"><em>[<?php echo $val['ntName']; ?>]</em><?php echo $val['ntitle']; ?></a></dd>
        <?php }} ?>
      </dl>
    </div>
	<div style="clear:both;"></div>
	
    <!-- 商城礼包 -->
    <div class="gift">
      <div class="hd"><a href="index.php?controller=games" target="_blank">更多游戏</a>
        <h3>热门游戏与应用</h3>
      </div>
      <div class="bd">
        <div class="dl5">
          <dl>
      		<?php if($games){ foreach($games as $val){ ?>
            <dd><span><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>" target="_blank" title="<?php echo $val['gTitle']; ?>"><img src="<?php echo $val['icon']; ?>" alt="<?php echo $val['gTitle']; ?>" width="122" height="122"><em></em></a></span>
              <h3><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['gTitle']; ?></a></h3>
              <p>分类：<b><?php echo $val['typename']; ?></b></p>
            </dd>
            <?php }} ?>
          </dl>
        </div>
      </div>
    </div>
  </div>
  <!--colum-->
  <div class="colum">
    
    <!-- 游戏列表 -->
    <div class="games">
      <div class="hd"><a href="index.php?controller=games">所有游戏</a>
        <h3>游戏列表</h3>
      </div>
      <div class="bd">
        <table width="100%" border="0" class="table0">
          <tbody>
            <tr>
              <th><a href="index.php?controller=games&action=index&tid=5" target="_blank" class="game_title">休闲游戏</a></th>
              <th><a href="index.php?controller=games&action=index&tid=4" target="_blank" class="game_title">卡牌类</a></th>
              <th><a href="index.php?controller=games&action=index&tid=7" target="_blank" class="game_title">养成类</a></th>
              <th><a href="index.php?controller=games&action=index&tid=8" target="_blank" class="game_title">策略经营</a></th>
              <th><a href="index.php?controller=games&action=index&tid=10" target="_blank" class="game_title">角色扮演</a></th>
              <th><a href="index.php?controller=games&action=index&tid=12" target="_blank" class="game_title">应用类</a></th>
            </tr>
            <tr>
              <td>
                <dl class="dl7">
      			  <?php if($game1){ foreach($game1 as $val){ ?>
                  <dd><em class="icon"><img src="./images/icon_hot.jpg" alt=""></em><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['gTitle']; ?></a></dd>
            	  <?php }} ?>
                </dl>
              </td>
              <td>
                <dl class="dl7">
      			  <?php if($game2){ foreach($game2 as $val){ ?>
                  <dd><em class="icon"><img src="./images/icon_hot.jpg" alt=""></em><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['gTitle']; ?></a></dd>
            	  <?php }} ?>
                </dl>
              </td>
              <td>
                <dl class="dl7">
      			  <?php if($game3){ foreach($game3 as $val){ ?>
                  <dd><em class="icon"><img src="./images/icon_hot.jpg" alt=""></em><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['gTitle']; ?></a></dd>
            	  <?php }} ?>
                </dl>
              </td>
              <td>
                <dl class="dl7">
      			  <?php if($game4){ foreach($game4 as $val){ ?>
                  <dd><em class="icon"><img src="./images/icon_hot.jpg" alt=""></em><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['gTitle']; ?></a></dd>
            	  <?php }} ?>
                </dl>
              </td>
              <td>
                <dl class="dl7">
      			  <?php if($game5){ foreach($game5 as $val){ ?>
                  <dd><em class="icon"><img src="./images/icon_hot.jpg" alt=""></em><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['gTitle']; ?></a></dd>
            	  <?php }} ?>
                </dl>
              </td>
              <td>
                <dl class="dl7">
      			  <?php if($game6){ foreach($game6 as $val){ ?>
                  <dd><em class="icon"><img src="./images/icon_hot.jpg" alt=""></em><a href="index.php?controller=games&action=gamesDetail&id=<?php echo $val['id']; ?>" target="_blank"><?php echo $val['gTitle']; ?></a></dd>
            	  <?php }} ?>
                </dl>
              </td>
              
            </tr>
            <!--<tr>
              <td colspan="6" class="td_last"><span><img src="./images/icon_new.jpg" alt=""> 最新游戏</span><span><img src="./images/icon_hot.jpg" alt=""> 热门游戏</span></td>
            </tr>-->
          </tbody>
        </table>
      </div>
    </div>
    <!-- 品牌宣传 -->
    <div class="ppxc">
      <div class="hd">
        <h3>关于我们</h3>
      </div>
      <div class="bd">
        <div class="ppxc_top"><span><img src="./images/erweima.png" alt=""></span>
          <h3>广州万荟数码科技有限公司拥有国内较早从事手机网游研发、运营、发行的管理团队，凭借敏锐的市场嗅觉，深厚的行业积累享誉业内。</h3>
        </div>
        <div class="ppxc_p">
          <p><b>联系电话：</b>020-29866206</p>
          <p><b>客服邮箱：</b>623770651@qq.com</p>
        </div>
      </div>
    </div>
  </div>
 
  <!-- 知名品牌logo -->
  <div class="corper">
    <div class="hd">
	  <!--<a href="#">更多合作伙伴</a>-->
      <h3>合作伙伴</h3>
    </div>
    <dl class="dl8">
      <dd><a href="#"><img src="./images/1404981746_372367950.jpg" alt=""></a></dd>
      <dd><a href="#"><img src="./images/1404981486_197831077.jpg" alt=""></a></dd>
      <dd><a href="#"><img src="./images/1404982109_441582223.jpg" alt=""></a></dd>
      <dd><a href="#"><img src="./images/1404981781_499446357.jpg" alt=""></a></dd>
      <dd><a href="#"><img src="./images/1404981679_274500179.jpg" alt=""></a></dd>
      <dd><a href="#"><img src="./images/1404982253_2031294062.jpg" alt=""></a></dd>
      <dd><a href="#"><img src="./images/1404982132_1921340580.jpg" alt=""></a></dd>
      <dd><a href="#"><img src="./images/1404982172_1926043068.jpg" alt=""></a></dd>
    </dl>
  </div>
</div>

<?php include("footer.php"); ?>

</body>
</html>