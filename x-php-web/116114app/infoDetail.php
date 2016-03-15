<?php
include("./admin/include/config.php");

$id = intval($_GET['id']);
$sqlOne = "select * from news where id='$id'";
$ArrRow = $sqlsrv->FetchOne($sqlOne);


$curPage = 'info';
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>游戏官网 - 新闻详情</title>
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./js/jquery.form.js"></script>
<script src="./js/common.js" type="text/javascript"></script>
<script src="./js/requestoptemp.js" type="text/javascript"></script>
<script src="./js/huanchang.js" type="text/javascript"></script>
<link href="./css/common.css" rel="stylesheet" type="text/css">
<link href="./css/product.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript">
$(function(){
    showLogin('userlogin','login','');
})
</script>
<script id="b5mmain" type="text/javascript" charset="utf-8" src="./js/b5m.main.js"></script></head>




<body>
<!--header start-->
<?php include('header.php'); ?>
<!--header end--> 

<div class="size-w">
  <h1 class="nav-title"><i></i>您现在的位置：<a href="http://www.116114app.com/">首页</a>&gt;<a>游戏资讯</a>&gt;<span>热门新闻</span></h1>
  <div class="product-list-fm"> 
    <!--左侧 start:-->
    
    <div class="detail-l">
      <h1>热门专题</h1>
      <div class="d-game-box">
      <br>
      
      <span class="game-pic"><a href="#"><img src="./images/267.JPG" width="200" height="87"></a></span>
      
      <span class="game-pic"><a href="#"><img src="./images/266.jpg" width="200" height="87"></a></span>
      
      <span class="game-pic"><a href="#"><img src="./images/265.jpg" width="200" height="87"></a></span>
      
      </div>
      
      <!--猜你喜欢 start:-->
      <h1>猜你喜欢</h1>
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
      <!--新闻正文 start:-->
      <h1><span class="news-title">热门新闻 &gt;&gt; 新闻 &gt;&gt; 正文</span></h1>
      <div class="newsdetail-box">
      <h2><?php echo $ArrRow['title']; ?></h2>
<div class="detail-info"><em>来源：<?php echo $ArrRow['source']; ?></em><em>发表时间：2015-04-03  11:35:11</em></div>
<p style="text-align: left; text-indent: 2em;"><span style="color: rgb(151, 72, 6);"><?php echo $ArrRow['content']; ?></p>
</div>
      <!--新闻正文 end--> 
    </div>
    <!--右侧 end--> 
  </div>
</div>

<!--footer start:-->
<?php include('footer.php'); ?>
<!--footer end-->
</body>
</html>