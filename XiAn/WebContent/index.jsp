<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge"> 

<title>万家互娱</title>

<!-- 引入 Bootstrap -->
<link
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/docs.min.css" rel="stylesheet">

<style type="text/css">
@font-face {
	font-family: pictos;
	src: url('font/fangzheng.ttf');
}

font {
	font-family: pictos;
}

ul {
	font-family: pictos;
}

.a-x-axy {
	width: 100%;
}

.border {
	border-bottom: #fff 7px solid;
}
</style>

</head>
<body>
	<br />
	<br />
	<header class="navbar navbar-static-top bs-docs-nav" id="top"
		role="banner"> <jsp:include page="top.jsp"></jsp:include> </header>

	<br />
	<div id="myCarousel" class="carousel slide">
		<!-- 轮播（Carousel）指标 -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
			<li data-target="#myCarousel" data-slide-to="3"></li>
		</ol>
		<!-- 轮播（Carousel）项目 -->
		<div class="carousel-inner">
			<div class="item active">
				<img src="./images/banner1.jpg" alt="First slide">
			</div>
			<div class="item">
				<img src="./images/banner2.jpg" alt="Second slide">
			</div>
			<div class="item">
				<img src="./images/banner3.jpg" alt="Third slide">
			</div>
			<div class="item">
				<img src="./images/banner4.jpg" alt="fouth slide">
			</div>
		</div>
		<!-- 轮播（Carousel）导航 -->
		<a style="padding-top: 20%" class="carousel-control left"
			href="#myCarousel" data-slide="prev">&lsaquo;</a> <a
			style="padding-top: 20%" class="carousel-control right"
			href="#myCarousel" data-slide="next">&rsaquo;</a>
	</div>

	<!-- 分隔符div  -->
	<div>
		<img src="./images/space.png" class="img-responsive"
			alt="Cinque Terre" width="100%" height="100%">
	</div>
	<!-- 分隔符div  -->
	<br />
	<br />
	<div style="text-align: center;">
		<p></p>
		<font style="font-size: 40px; color: #7a7979">万家互娱专注游戏的研发</font><br />
		<font style="font-size: 30px; color: #7a7979"> Focuson the
			development of mobile games </font><br />
	</div>
	<br />
	<br />
	<br />

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3">
				<img alt="" src="./images/tuandui.png" width="100%">
			</div>
			<div class="col-md-3">
				<img alt="" src="./images/zhengui.png" width="100%">
			</div>
			<div class="col-md-3">
				<img alt="" src="./images/kaifang.png" width="100%">
			</div>
			<div class="col-md-3">
				<img alt="" src="./images/tiexing.png" width="100%">
			</div>
		</div>
	</div>
	<br />
	<br />
	<br />
	<!-- 分隔符div  -->
	<div>
		<img src="./images/space.png" class="img-responsive"
			alt="Cinque Terre" width="100%" height="100%">
	</div>
	<!-- 分隔符div  -->
	<br />
	<br />
	<div id="Company" style="text-align: center;">
		<p></p>
		<font style="font-size: 40px; color: #7a7979">公司业务</font><br /> <font
			style="font-size: 30px; color: #7a7979"> Company business </font><br />
	</div>
	<br />




	<div class="row clearfix">
		<div class="col-md-6 column">
			<ul style="font-size: 30px; color: #7a7979">
				<li>美术风格设定</li>
				<li>２Ｄ角色设定、绘制</li>
				<li>2D场景背景图绘制</li>
				<li>2D场景设定（大型概念布局）</li>
				<li>2D修图（3渲2）</li>
				<li>2D切片动画（骨骼）</li>
				<li>2D游戏特效（序列帧）</li>
				<li>游戏宣传海报</li>
			</ul>
		</div>
		<div class="col-md-6 column">
			<ul style="font-size: 30px; color: #7a7979">
				<li>整体地编制作（地形及地表贴图）</li>
				<li>3D场景建模</li>
				<li>3D角色建模</li>
				<li>3D VR展示</li>
				<li>3D建模+渲染（3渲2）</li>
				<li>3D骨骼动画</li>
				<li>UE4</li>
				<li>游戏项目整体美术制作（整包）</li>
			</ul>
		</div>
	</div>

	<div style="text-align: center;">
		<p></p>
		<font style="font-size: 30px; color: #7a7979">无论是手机游戏、客户端游戏、网页游戏，无论是产品宣传还是项目研发，万家互娱
		</font> <br /> <font style="font-size: 30px; color: #7a7979">都会为您提供最优秀的解决方案。
		</font>
	</div>
	<br />
	<br />

	<!-- 分隔符div  -->
	<div>
		<img src="./images/space.png" class="img-responsive"
			alt="Cinque Terre" width="100%" height="100%">
	</div>
	<!-- 分隔符div  -->
	<br />
	<br />
	<div id="Appreciation" style="text-align: center;">
		<p></p>
		<font style="font-size: 40px; color: #7a7979">作品欣赏</font><br /> <font
			style="font-size: 30px; color: #7a7979"> Appreciation of works
		</font><br />
	</div>
	<br />
	<br />

	<div>
		<img style="width: 20%; float: left;" id="001" alt=""
			src="./images/img_1.jpg"> <img style="width: 20%; float: left;"
			id="002" alt="" src="./images/img_2.jpg"> <img
			style="width: 20%; float: left;" id="003" alt=""
			src="./images/img_3.jpg"> <img style="width: 20%; float: left;"
			id="004" alt="" src="./images/img_4.jpg"> <img
			style="width: 20%; float: left;" id="005" alt=""
			src="./images/img_5.jpg"> <img style="width: 20%; float: left;"
			id="006" alt="" src="./images/img_6.jpg"> <img
			style="width: 20%; float: left;" id="007" alt=""
			src="./images/img_7.jpg"> <img style="width: 20%; float: left;"
			id="008" alt="" src="./images/img_8.jpg"> <img
			style="width: 20%; float: left;" id="009" alt=""
			src="./images/img_9.jpg"> <img style="width: 20%; float: left;"
			id="0010" alt="" src="./images/img_10.jpg">
	</div>
	<!-- 分隔符div  -->
	<div>
		<img src="./images/space.png" class="img-responsive"
			alt="Cinque Terre" width="100%" height="100%">
	</div>
	<!-- 分隔符div  -->
	<br />
	<div id="About_us" style="text-align: center;">
		<p></p>
		<font style="font-size: 40px; color: #7a7979">关于我们</font><br /> <font
			style="font-size: 30px; color: #7a7979"> About us </font><br />
	</div>
	<br />
	<br />
	<div class="container-fluid" style="">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-3"">
				<img style="width: 110%" src="./images/about_us.png">
			</div>
			<div class="col-md-6" style="">
				<img style="width: 125%" src="./images/about_us_text2.png">
			 	<!-- <font
					style="font-size: 24px; color: #7a7979; line-height: 200%; width: 100%">&nbsp;&nbsp;&nbsp;&nbsp;万家互娱成立于2003年，前身为翔通动漫有限公司，是中国三
					大电信运营商产品基地动漫核心合作伙伴、文化部和财政部认证的国 家级动漫企业以及中国最大的手机动漫公司。同时是中国目前拥有动
					漫版权形象最多的公司，是中国最大的动漫版权授权公司之一。经过 多年运营，万家互娱拥有移动互联网产业包括万家无线、万家游戏、
					翔通动漫三大产业布局。 万家互娱是一家拥有强大的互联网、移动互联网发行渠道、游戏 产品的自主研发能力和动漫制作能力的综合服务提供商。
					2015年8月， 浙江万好万家文化股份有限公司（股票代码：600576）通过重大资
					产重组方式收购公司100%的股权。并入上市公司后公司品牌随之更 新为万家互娱。以全新形象强势布局移动互联网产业。</font>  -->
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
	<br />
	<br />
	<br />
	<!-- 分隔符div  -->
	<div>
		<img src="./images/space.png" class="img-responsive"
			alt="Cinque Terre" width="100%" height="100%">
	</div>
	<!-- 分隔符div  -->

	<br />
	<br />

	<div style="text-align: center;">
		<p></p>
		<font style="font-size: 40px; color: #7a7979">主要合作客户</font><br /> <font
			style="font-size: 30px; color: #7a7979"> Cooperative clinet </font><br />
	</div>

	<br />
	<br />


	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12" style="text-align: center;">
				<img alt="" src="./images/cooperative_clinet.png" width="80%">
			</div>
		</div>
	</div>


	<br />
	<br />
	<br />

	<!-- 底部  -->
	<div id="Contact_us" class="container-fluid"
		style="background-image: url('./images/Footer.png');">

		<div class="row">
			<div class="col-md-2"></div>

			<div class="col-md-5">
				<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <img
					alt="" src="./images/bottom_logo.png"> <br /> <br /> <font
					style="font-size: 18px; color: #ffffff">联系地址：陕西省西安市碑林区南二环69号5层</font>
				<br /> <font style="font-size: 18px; color: #ffffff">商务联系:</font> <br />
				<font style="font-size: 18px; color: #ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;陈佳</font>&nbsp;&nbsp;&nbsp;&nbsp;<img
					alt="" src="./images/QQ.png" style="margin-bottom: 5px;">&nbsp;&nbsp;&nbsp;&nbsp;<font
					style="font-size: 18px; color: #ffffff">308187326</font> <br /> <font
					style="font-size: 18px; color: #ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>&nbsp;&nbsp;&nbsp;&nbsp;<img
					alt="" src="./images/mobile_logo.png" style="margin-bottom: 5px;margin-left: 3px">&nbsp;&nbsp;&nbsp;&nbsp;<font
					style="font-size: 18px; color: #ffffff">15029558113</font> <br /> <br />
				<br /> <br />
			</div>
			<div class="col-md-3"></div>
			<br /> <br /> <br /> <br /> <br /> <br /> <br /> <img alt=""
				src="./images/erweima_logo.jpg">
			<div class="col-md-2"></div>
		</div>
	</div>


	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<!-- 包括所有已编译的插件 -->
	<script src="js/bootstrap.min.js"></script>

</body>
</html>