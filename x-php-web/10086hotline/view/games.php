<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>游戏中心-广州垄金软件开发有限公司</title>
<meta name="Description" content="">
<meta name="Keywords" content="">
<meta name="Author" content="KUNNIU.INC">
<link href="./css/style.css" rel="stylesheet" type="text/css">
<link href="./css/foucs.css" rel="stylesheet" type="text/css">
<script src="./js/jquery.js"></script>
<script src="./js/jquery.foucs.js"></script>
<script id="b5mmain" type="text/javascript" charset="utf-8" src="./js/b5m.main.js"></script>
</head>
<body style="background-color: rgb(231,231,231);">

<!-- 公用头部 -->
<?php include("header.php"); ?>
<!-- 公用头部 -->
 
<div id="main" style="margin: 0px;">
        <div id="index_b_hero">
            <div class="hero-wrap">
                <ul class="heros clearfix">
                    <li class="hero" style="left: -1678px;">
                        <a href="javascript:;" target="_blank">
                            <img src="./images/tmsj_03.jpg" class="thumb">
                        </a>
                    </li>
                    <li class="hero" style="left: -839px;">
                        <a href="javascript:;" target="_blank">
                            <img src="./images/tgxy_03.jpg" class="thumb">
                        </a>
                    </li>
                    <li class="hero" style="left: 0px;">
                        <a href="javascript:;" target="_blank">
                            <img src="./images/jstf_03.jpg" class="thumb">
                        </a>
                    </li>
                    <li class="hero" style="left: 839px;">
                        <a href="javascript:;" target="_blank">
                            <img src="./images/xdm_03.jpg" class="thumb">
                        </a>
                    </li>
                </ul>
            </div>
            <div class="helper">
                <div class="mask-left">
                </div>
                <div class="mask-right">
                </div>
                <!--  <a href="#" class="prev icon-arrow-a-left"></a>
                <a href="#" class="next icon-arrow-a-right"></a> -->
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $.foucs({ direction: 'left' });
    </script>
<div id="page" style="background-image: url(http://www.szkuniu.com/Public/images//bj_01.jpg); ">
	<div class="main mt20" style=" ">
		<div style="heght:22px;width:100%; text-align:center; font-size: 20px; border-bottom:solid #ccc 1px; padding-bottom:10px;">所有产品 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#ccc;">|</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所有产品</div>
		<div style=" margin-top: 20px;;margin-right: auto;margin-left: auto; width:1100px;">
				<div class="game-item-2">
					<a href="index.php?controller=games&action=gamesDetail" target="_blank" style="background: url(http://www.szkuniu.com/Public/images//c_tmsj.jpg); width: 514px; height: 278px; display: block;">
						<div class="desc-2">
							<h2>唐门世界</h2>
							<div class="game_desc">首款根据唐家三少小说作品创作的手机游戏，集合九本作品中的人物角色</div>
						</div>
					</a>
				</div>

				<div class="game-item-2 right">
					<a href="index.php?controller=games&action=gamesDetail" target="_blank" style="background: url(http://www.szkuniu.com/Public/images//c_tgxy.jpg); width: 514px; height: 278px; display: block;">
						<div class="desc-2">
							<h2>太古仙域</h2>
							<div class="game_desc">由辰东亲自全程监制，成熟的剧情架构，搭载多元丰富创新的游戏系统</div>
						</div>
					</a>
				</div>
			
				<div class="game-item-2">
					<a href="index.php?controller=games&action=gamesDetail" target="_blank" style="background: url(http://www.szkuniu.com/Public/images//c_jstf.jpg); width: 514px; height: 278px; display: block;">
						<div class="desc-2">
							<h2>绝世天府</h2>
							<div class="game_desc">由天蚕土豆根据自己小说监制改编的卡牌策略手游。全面回归小说剧情</div>
						</div>
					</a>
				</div>
			
				<div class="game-item-2 right">
					<a href="index.php?controller=games&action=gamesDetail" target="_blank" style="background: url(http://www.szkuniu.com/Public/images//c_xdm.jpg); width: 514px; height: 278px; display: block;">
						<div class="desc-2">
							<h2>兄弟萌</h2>
							<div class="game_desc">水浒题材的策略卡牌游戏，超萌的水浒英雄卡牌、首创的幻兽系统</div>
						</div>
					</a>
				</div>
			
			<div class="clear"></div>
		</div>
	</div>
</div>

	
<script>
$(function(){
	// 处理个分辨率下幻灯片尺寸问题
	sWidth = $(window).width();
	$("#new-banner img").css('width', sWidth);
	var h = 421 * sWidth / 1920;
	$("#new-banner img").css('height', h);
	$("#new-banner").css('height', h -1);
});
</script>
<div class="clear"></div>
<?php include("footer.php"); ?>  

</body>
</html>