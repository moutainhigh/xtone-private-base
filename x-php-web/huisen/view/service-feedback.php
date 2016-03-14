<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<meta name="keywords" content="荟森数码,手机网站,微信营销" />
<meta name="description" content="荟森数码" />
<meta name="author" content="荟森数码" />
<title>荟森数码--提交反馈</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.easing.js" type="text/javascript"></script>
<script src="js/jquery.navpart.js" type="text/javascript"></script>
<script src="js/public.js" type="text/javascript"></script>
<script src="js/MSClass.js" type="text/javascript"></script>
<script type="text/javascript">
//navpart
$(function(){$("#navpart").lavaLamp({ fx:"backout", speed:700})});

//便民导航
head(100,"top");
</script>
</head>

<body>
<?php include('header.php'); ?>

<div id="location">
	<div>服务&amp;支持<span>Service &amp; Support</span></div>
</div>
<div id="navpart" class="navpart-service">
    <ul>
        <li><a href="index.php?controller=service">服务理念</a></li>
        <li><a href="index.php?controller=service&action=faq">FAQ</a></li>
        <li class="hover current-cat"><a href="index.php?controller=service&action=feedback" class="last">提交反馈</a></li>
    </ul>
</div>

<div id="content">
	<div id="feedback">
        <div class="title">提交反馈</div>
            <div class="prompt">
        	<div class="top"></div>
            <div class="text">尊敬的客户：<br />您好！荟森数码<sup>®</sup>提供专业的电子政务网站、事业单位网站、品牌网站、集团网站群、外贸网站、电子商务、移动互联等互联网解决方案。无论您是在寻找一个网站开发商，希望在线上推出品牌推广产品，或是想通过专业网站技术服务商为您建立一套软件平台系统，甚至是计划在移动互联手机应用领域有所拓展，我们都有办法帮助您实现梦想，走向成功。</div>
            <div class="boot"></div>
        </div>
        <ul class="form">
            <form id="formfeedback" name="formfeedback" method="post" action="" onsubmit="return feedbackcheck();">
            <li><input name="name" type="text" id="name" onfocus="if(this.value=='您的称呼'){this.value='';}" onblur="if(this.value==''){this.value='您的称呼';}" value="您的称呼" size="50" /></li>
            <li><input name="company" type="text" id="company" onfocus="if(this.value=='您所代表的公司'){this.value='';}" onblur="if(this.value==''){this.value='您所代表的公司';}" value="您所代表的公司" size="50" /></li>
            <li><input name="tel" type="text" id="tel" onfocus="if(this.value=='您的联系电话'){this.value='';}" onblur="if(this.value==''){this.value='您的联系电话';}" value="您的联系电话" size="50" /></li>
            <li><textarea name="content" id="content2" cols="80" rows="5" onfocus="if(this.value=='请填写您的需求描述，我们将尽快给您回复！'){this.value='';}" onblur="if(this.value==''){this.value='请填写您的需求描述，我们将尽快给您回复！';}">请填写您的需求描述，我们将尽快给您回复！</textarea></li>
            <li><input name="imgcode" type="text" id="code" size="8" onfocus="if(this.value=='验证码'){this.value='';}" onblur="if(this.value==''){this.value='验证码';}" value="验证码" /><img src="index.php?controller=service&action=verify" id="imgcode" style="vertical-align:middle; margin-left:6px; cursor:pointer" onclick="javascript: newgdcode(this,this.src);" /></li>
            <li class="btn"><input type="submit" name="button" id="button" value="" />&nbsp;&nbsp;<a href="index.php?controller=about&action=contact">或电话联系我们</a></li>
          </form>
      </ul>
          </div>    
</div>


<?php include('footer.php'); ?>
<script src="js/jquery.brand.js" type="text/javascript"></script>
<script src="js/menu.js" type="text/javascript"></script>
</body>
</html>
<script type="text/javascript">
function feedbackcheck(){
	var name = $('#name').val();
	var company = $('#company').val();
	var tel = $('#tel').val();
	var content2 = $('#content2').val();
	var code = $('#code').val();
	//alert(content2);return false;
	
	if(name==''||name=='您的称呼'){
		alert('称呼不能为空');return false;
	}else if(tel==''||tel=='您的联系电话'){
		alert('联系方式不能为空');return false;
	}else if(content2=='请填写您的需求描述，我们将尽快给您回复！'){
		alert('留言内容不能为空');return false;
	}else{
		return true;
	}
	
}

</script>
<script type="text/javascript">
function newgdcode(obj,url) {
	//alert(url);return false;
	obj.src = url+'&nowtime='+new Date().getTime();
}
</script>
