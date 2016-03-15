<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit|ie-stand|ie-comp">
<title>招聘-捷酷软件-国际领先的移动游戏开发商与发行商</title>
<link href="./css/global.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./css/reset.css" media="all">
</head>
<body>
<?php include('header.php'); ?>

<div class="third-main clear">
  <div class="wrap">
   <span class="nav-line" id="navLine" style="display: inline; left: 0px; -webkit-transform: translate(166px, 0px) translateZ(0px); transform: translate(166px, 0px) translateZ(0px); -webkit-transition: 300ms cubic-bezier(0.1, 0.57, 0.1, 1); transition: 300ms cubic-bezier(0.1, 0.57, 0.1, 1);"></span>
    <div class="tm-right">
      <p class="p-statistical"><em class="type" id="statisticalType">公司招聘</em>共 <?php echo $totalnum; ?> 条职位信息</p>
      <ul class="detail-list social">
      	
        <?php if($JoinUs){ foreach($JoinUs as $val){ ?>
        <li>
          <div class="dl-header">
            <div class="dl-header-main"><em><a href="javascript:;"><?php echo $val['title']; ?></a></em><span class="time"><?php echo $val['theDate']; ?> <?php echo $val['company']; ?></span><span class="place"><?php echo $val['area']; ?></span><span class="company"></span></div>
          </div>
          <div class="dl-wrap">
            <div class="dl-content">
              <div class="dl-content-right"><a class="normal-btn style-a js-detail" href="javascript:;">查看详情</a><a class="normal-btn style-b" style="margin: 0;" href="#">职位申请</a></div>
              <div class="dl-content-left">
               <?php echo $val['content']; ?>
              </div>
            </div>
          </div>
        </li>
        <?php }} ?>
      </ul>
      <p style="width:100%; text-align:center; font-size:12px;"><?php echo $page; ?></p>
    </div>
  </div>
</div>
<?php include("footer.php"); ?>
<script type="text/javascript" src="./js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="./js/all.js"></script>
</body>
</html>