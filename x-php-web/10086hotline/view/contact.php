<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>联系我们-广州垄金软件开发有限公司</title>
<meta name="Description" content="">
<meta name="Keywords" content="">
<meta name="Author" content="KUNNIU.INC">
<link href="./css/style.css" rel="stylesheet" type="text/css">
<script src="./js/jquery.js"></script>
<script id="b5mmain" type="text/javascript" charset="utf-8" src="./js/b5m.main.js"></script>
</head>
<body>

<!-- 公用头部 -->
<?php include("header.php"); ?>
<!-- 公用头部 -->
 
<div id="page">
	<div id="new-banner"><img src="./images/contact_banner.jpg" style="width: 1351px; height: 296.2348958333333px;"></div>
	<div class="main bgwhite mt20">
		
		<div class="contact-main">
			<div class="tab c-tab">联系我们</div>
			<div>
				<div class="c-item">
					<div class="c-ico"><img src="./images/c_email.png"></div>
					<div class="c-con">
						<div style="color: #525568; font-size: 30px;">邮箱地址</div>
							<p>商务合作：2193224094@qq.com</p>
							<p>意见反馈：2193224094@qq.com</p>
					</div>
				</div>
				
				<div class="c-item">
					<div class="c-ico"><img src="./images/c_tel.png"></div>
					<div class="c-con">
						<div style="color: #525568; font-size: 30px;">联系电话</div>
							<p>商务合作：020-29866106</p>
							<p>意见反馈：020-29866106</p>
					</div>
				</div>
				
				<div class="c-item">
					<div class="c-ico"><img src="./images/c_addr.png"></div>
					<div class="c-con">
						<div style="color: #525568; font-size: 30px;">通讯地址</div>
							<p>地址：广州市海珠区光大花园</p>
					</div>
				</div>
				
				<div class="c-item">
					<div class="c-ico"><img src="./images/c_qq.png"></div>
					<div class="c-con">
						<div style="color: #525568; font-size: 30px;">官方企业QQ</div>
							<p>客服QQ：2193224094</p>
					</div>
				</div>
				
				<div class="c-item">
					<div class="c-ico"></div>
					<div class="c-con" style="width: 800px;">
						<div style="color: #525568; font-size: 30px; float: left;">我们的位置</div>
							<img src="./images/c_address.jpg">
					</div>
				</div>
				
				<div class="clear"></div>
			</div>
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
});
</script>
<div class="clear"></div>
<?php include("footer.php"); ?>  

</body>
</html>