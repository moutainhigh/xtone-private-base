<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="org.common.util.ConnectionService"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.vanggame.info.Content"%>
<%
	int count = 0;

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Content> list = new ArrayList<Content>();
	try {
		con = ConnectionService.getInstance().getConnectionForLocal();
		String limit = " limit " + 6 * (1 - 1) + "," + 6;
		String sql = "SELECT id,`title`,`lastModifyTime` FROM `tbl_cms_contents` WHERE `catalog` LIKE '%news%' AND `status`=1 ORDER BY lastModifyTime DESC "
				+ limit;
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
			Content news = new Content();
			news.setId(rs.getInt("id"));
			news.setTitle(rs.getString("title"));
			news.setLastModifyTime(rs.getLong("lastModifyTime"));
			list.add(news);
		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta name="description" content="Bootstrap.">
<meta name="keywords"
	content="HTML, CSS, JS, JavaScript, framework, bootstrap, front-end, frontend, web development">
<title>万家游戏-首页</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/docs.min.css" rel="stylesheet">
<!--[if lt IE 9]><script src="../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script async="" id="gauges-tracker"
	data-site-id="4f0dc9fef5a1f55508000013" src="js/bootstrap/track.js"></script>
<script async="" src="js/bootstrap/analytics.js"></script>
<script src="js/bootstrap/ie-emulation-modes-warning.js"></script>

<!--[if lt IE 9]><script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
<!-- <script>!function(e,t,a,n,c,o,s){e.GoogleAnalyticsObject=c,e[c]=e[c]||function(){(e[c].q=e[c].q||[]).push(arguments)},e[c].l=1*new Date,o=t.createElement(a),s=t.getElementsByTagName(a)[0],o.async=1,o.src=n,s.parentNode.insertBefore(o,s)}(window,document,"script","//www.google-analytics.com/analytics.js","ga"),ga("create","UA-146052-10","getbootstrap.com"),ga("send","pageview");</script> -->
<style>
.carbonad {
	display: block;
	background: #fdfdfd;
	background-image: -moz-linear-gradient(top, #f8f8f8, #fdfdfd);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #f8f8f8),
		color-stop(1, #fdfdfd));
	border: 1px solid #d5d5d5;
	font-family: Lucida Grande, Arial, Helvetica, sans-serif;
	font-size: 11px;
	height: 118px;
	line-height: 15px;
	overflow: hidden;
	width: 300px
}

.carbonad-img {
	border: none;
	display: inline;
	float: left;
	height: 100px;
	margin: 9px;
	width: 130px
}

.carbonad-text {
	display: inline;
	float: left;
	width: 142px;
	padding-top: 13px
}

.carbonad-text a {
	color: #000;
	text-decoration: none;
	text-transform: none;
}

.carbonad-tag {
	float: left;
	margin-top: 9px;
	text-align: center;
	width: 142px;
	color: #999
}

.carbonad-tag a {
	color: #999;
	text-decoration: none
}

.newsli {
	border-bottom: #e1e1e0 solid 1px;
	padding-top: 17px;
	padding-bottom: 16px;
	/* 	list-style-image: url("images/index/dian_1.png"); */
	color: #cf1232;
}

@media ( min-width :768px) {
	.newsli {
		border-bottom: #e1e1e0 solid 1px;
		padding-top: 11px;
		padding-bottom: 11px;
		/* 	list-style-image: url("images/index/dian_1.png"); */
		color: #cf1232;
	}
}

@media ( min-width :1201px) {
	.newsli {
		border-bottom: #e1e1e0 solid 1px;
		padding-top: 17px;
		padding-bottom: 16px;
		/* 	list-style-image: url("images/index/dian_1.png"); */
		color: #cf1232;
	}
}

.newsli font {
	color: #777777;
	font-size: 17px;
}

.newsli em {
	color: #aaaaaa;
}

.links {
	color: #aaaaaa;
	text-decoration: none;
	font-size: 15px;
}

.title {
	margin-top: 80px;
	margin-bottom: 9px;
}

#overlay {
	display: none;
	position: fixed;
	top: 0px;
	left: 0px;
	z-index: 9999;
	width: 100%;
	height: 100%;
	background: #000 none repeat scroll 0% 0%;
	opacity: 0.5;
}

.videoPop {
	display: none;
	position: fixed;
	left: 50%;
	top: 40%;
	z-index: 9999;
	width: 900px;
	height: 600px;
	margin: -225px 0px 0px -400px;
}

.pop {
	display: none;
	position: fixed;
	left: 50%;
	top: 50%;
	z-index: 9999;
	width: 527px;
	height: 282px;
	margin: -272px 0px 0px -439px;
	/*     background: transparent url("images/index/vedio-start.png") no-repeat scroll 0% 0%; */
}

.videoPop .pop-close { 
	position: absolute;
	top: 0px;
	right: -420px;
	width: 47px;
	height: 47px;
	cursor: pointer;
	background: transparent url("images/index/pop_close.png") no-repeat
		scroll 0% 0%;
}

.pop-close {
	position: absolute;
	top: 0px;
	right: -420px;
	width: 47px;
	height: 47px;
	cursor: pointer;
	background: transparent url("images/index/pop_close.png") no-repeat
		scroll 0% 0%;
}
</style>
</head>
<body class="bs-docs-home">
	<header class="navbar navbar-static-top bs-docs-nav" id="top"
		role="banner">
		
		<div class="container">
			<div class="navbar-header">
				<button class="navbar-toggle collapsed" type="button"
					data-toggle="collapse" data-target="#bs-navbar"
					aria-controls="bs-navbar" aria-expanded="false">
					<span class="sr-only"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a href="#"><img alt="logo" src="images/logo.png"></a>
			</div>
			<nav id="bs-navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right ztgs">
					<li class="active"><a id="index" href="index.jsp"><small
							class="nav-chinese">首页</small><br>
						<small class="nav-english">HOME</small></a></li>
					<li><a id="news" href="news.jsp"><small
							class="nav-chinese">新闻动态</small><br>
						<small class="nav-english">NEWS</small></a></li>
					<li><a id="partform" href="porducts-sjyx.html"><small
							class="nav-chinese">旗下产品</small><br>
						<small class="nav-english">PORDUCTS</small></a></li>
					<li><a id="abouts" href="about-us-gsjs.html"><small
							class="nav-chinese">关于我们</small><br>
						<small class="nav-english">VANGGAME</small></a></li>
					<li><a id="cooperation" href="cooperation.html"><small
							class="nav-chinese">商务合作</small><br>
						<small class="nav-english">COOPERATION</small></a></li>
					<li><a id="join" href="join-us-shzp.html"><small
							class="nav-chinese">招贤纳士</small><br>
						<small class="nav-english">JOIN&nbsp;US</small></a></li>
				</ul>
			</nav>
		</div>
	</header>

	<div id="myCarousel" class="carousel slide" data-ride="carousel"
		data-interval="5000">
		<!-- 轮播（Carousel）指标 -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
			<!--       <li data-target="#myCarousel" data-slide-to="3"></li> -->
		</ol>
		<!-- 轮播（Carousel）项目 -->
		<div class="carousel-inner">
			<div class="item active" style="border: none;">
				<img src="images/index/1900x540_1.png" alt="First slide">
			</div>
			<div class="item" style="border: none;">
				<img src="images/index/1900x540_2.png" alt="Second slide">
			</div>
			<div class="item" style="border: none;">
				<img src="images/index/1900x540_3.png" alt="thrid slide">
			</div>
			<!-- 		<div class="item" style="border: none;"> -->
			<!--          <img src="images/index/1900x540_2.png" alt="Second slide"> -->
			<!--       </div> -->
		</div>
		<!-- 轮播（Carousel）导航 -->
		<!--    <a class="carousel-control left" href="#myCarousel"  -->
		<!--       data-slide="prev">&lsaquo;</a> -->
		<!--    <a class="carousel-control right" href="#myCarousel"  -->
		<!--       data-slide="next">&rsaquo;</a> -->
	</div>
	<div
		style="background-image: url(images/index/index-bg.png); background-size: cover;">
		<div class="bs-docs-featurette">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12 nopadding title">
						<div class="col-md-3 col-sm-3 col-xs-6 nopadding">
							<img src="images/index/hot_game.png" class=""></img>
						</div>

						<div class="col-md-9 col-sm-9 col-xs-6">
							<a href="porducts-sjyx.html" style="float: right"><img
								src="images/index/more.png"></img></a>
						</div>
					</div>

					<div class="col-md-12 col-sm-12 col-xs-12 nopadding">
						<div class="col-md-4 col-sm-4 col-xs-4 nopadding">
							<div class="hot_game hot_list">

								<div class="l hot_gamebox mb1">
									<a href="porducts-sjyx.html"><img
										src="images/index/350x290_1.png" class="bsimg"></a>
									<div class="hot_yt hot_yt_sw ztgs">
										<h4 class="hot_yt_left">《口袋大冒险》</h4>
										<h4 class="hot_yt_right">Q版/ARPG</h4>
									</div>
									<!-- 								<div class="hot_mark" align="center"> -->
									<!-- 									<dl class="c" style="box-sizing: content-box"> -->
									<!-- 										<dd class="game_erweima l"> -->
									<!-- 											<img src="images/index/erweima_smzg.png"> -->
									<!-- 										</dd> -->
									<!-- 										<dd class="game_str" style="padding-top: 10px;">《零纪元》</dd> -->
									<!-- 										<dd class="game_lx">简介|官网</dd> -->
									<!-- 									</dl> -->
									<!-- 								</div> -->
								</div>

							</div>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4 nopadding">
							<div class="hot_game hot_list">
								<div class="l hot_gamebox mb1">
									<a href="porducts-sjyx.html"><img
										src="images/index/350x290_2.png" class="bsimg"></a>
									<div class="hot_yt hot_yt_sw ztgs">
										<h4 class="hot_yt_left">《绝地大逃亡》</h4>
										<h4 class="hot_yt_right">动作格斗</h4>
									</div>
									<!-- 								<div class="hot_mark" align="center"> -->
									<!-- 									<dl class="c" style="box-sizing: content-box"> -->
									<!-- 										<dd class="game_erweima l"> -->
									<!-- 											<img src="images/index/erweima_smzg.png"> -->
									<!-- 										</dd> -->
									<!-- 										<dd class="game_str" style="padding-top: 10px;">《闪客快打》</dd> -->
									<!-- 										<dd class="game_lx">简介|官网</dd> -->
									<!-- 									</dl> -->
									<!-- 								</div> -->
								</div>
							</div>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4 nopadding">
							<div class="hot_game hot_list">
								<div class="hot_gamebox mb1">
									<a href="porducts-sjyx.html"><img
										src="images/index/350x290_3.png" class="bsimg"></a>
									<div class="hot_yt hot_yt_sw ztgs">
										<h4 class="hot_yt_left">《神魔战歌》</h4>
										<h4 class="hot_yt_right">卡牌/RPG</h4>
									</div>
									<!-- 								<div class="hot_mark" align="center"> -->
									<!-- 									<dl class="c" style="box-sizing: content-box"> -->

									<!-- 										<dd class="game_erweima"> -->
									<!-- 											<img src="images/index/erweima_smzg.png"> -->
									<!-- 										</dd> -->
									<!-- 										<dd class="game_str" style="padding-top: 10px;">《神魔战歌》</dd> -->
									<!-- 										<dd class="game_lx">简介|官网</dd> -->
									<!-- 									</dl> -->
									<!-- 								</div> -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="bs-docs-featurette">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12 nopadding title">
						<div class="col-md-3 col-sm-3 nopadding">
							<img src="images/index/business_structure.png"></img>
						</div>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4 nopadding">
						<!-- 					<a href="http://expo.getbootstrap.com/2014/10/29/lyft/" -->
						<!-- 						target="_blank" title="Lyft"> -->

						<a
							href="cooperation.html"> <img src="images/index/ad1.png"
							alt="商务合作1" class="bsimg">
						</a>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4 nopadding">
						<a href="cooperation.html"> <img src="images/index/ad3.png"
							alt="商务合作2" class="bsimg">
						</a>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4 nopadding">
						<a href="cooperation.html"> <img src="images/index/ad2.png"
							alt="商务合作3" class="bsimg">
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="bs-docs-featurette">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12 nopadding title">
						<div class="col-md-3 col-sm-3 col-xs-6 nopadding">
							<img src="images/index/vangame_news.png"></img>
						</div>
						<div class="col-md-5 col-sm-9 col-xs-6 nopadding">
							<a href="news.jsp" style="float: right;"><img
								src="images/index/more.png"></img></a>
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12 nopadding">
					<div class="col-md-8 col-sm-12 col-xs-12 nopadding ztgs"
						style="background-color: white;">
						<ul class="" style="margin-right: 20px; margin-bottom: 0px;">
							<%
								for (Content news : list) {
							%>
							<li class="newsli"><a
								href="news-content.jsp?pageindex=1&id=<%=news.getId()%>"
								target="_blank"><font><%=news.getTitle()%></font></a> <em
								style="float: right;"><%=news.getTimeStr()%></em> <%
 	}
 %>
						</ul>
					</div>
					<div class="col-md-4 col-sm-12 col-xs-12 video nopadding">
						<a class="btn-video" href="javascript:openVideo();"><img
							src="images/index/vedio-kddmx.png" class="bsimg"> </a>
						<div id="overlay" style="display: none;"></div>
						<div class="pop videoPop" id="pop-video" style="display: none;">
							<div class="pop-close"></div>
							<div class="pop-content"></div>
						</div>

					</div>
					</div>
				</div>
			</div>
		</div>
		<div class="bs-docs-featurette bottom-hieght">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12 nopadding title">
						<div class="col-md-3 col-sm-3 nopadding">
							<img src="images/index/links.png"></img>
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12 nopadding ztgs"
						style="background-color: #fafafa; padding: 22px;text-align: center;">
						<div class="tt20"><a href="http://www.alllink.com.cn/" class="links" target="_blank">北京通联天地科技有限公司</a>&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="tt20"><a href="http://www.xtonecomic.com/" class="links" target="_blank">北京讯宇创世科技有限公司</a>&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="tt20"><a href="http://14643233.czvv.com/" class="links" target="_blank">北京游动天地科技有限公司</a>&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="tt20"><a href="http://www.pvc123.com/b-pxtonewinnie/" class="links"	target="_blank">深圳市创世互动科技有限公司</a>&nbsp;&nbsp;&nbsp;&nbsp; </div>
						<div class="tt20"><a href="http://www.m-time.com/" class="links">深圳市浩天投资有限公司</a>&nbsp;&nbsp;&nbsp;&nbsp;</div>					
					</div>
					
				</div>
			</div>
		</div>
	</div>
	<footer class="bs-docs-footer" role="contentinfo">
		<jsp:include page="footer.html"/>
	</footer>

	<script src="js/bootstrap/jquery.min.js"></script>
	<script src="js/bootstrap/bootstrap.min.js"></script>
	<script src="js/docs.min.js"></script>
	<script src="js/index.js"></script>
	<script src="js/bootstrap/ie10-viewport-bug-workaround.js"></script>
	
	<script>
// 		var _gauges = _gauges || [];
// 		!function() {
// 			var e = document.createElement("script");
// 			e.async = !0, e.id = "gauges-tracker", e.setAttribute(
// 					"data-site-id", "4f0dc9fef5a1f55508000013"),
// 					e.src = "//secure.gaug.es/track.js";
// 			var t = document.getElementsByTagName("script")[0];
// 			t.parentNode.insertBefore(e, t)
// 		}();
	</script>
	<script type="text/javascript">
		$(function() {
			$('.hot_list .hot_gamebox').hotlist();

		})
		function ishidden(){
			var _this = $("#hide").parent();
			if(_this.is(':hidden')){
				_this.show();
				return;
			}
			_this.hide();
		}
	</script>
	<script type="text/javascript">
		function openPop(popID) {
			closePop();

			var iframe = "<div class='pop-close'></div><iframe marginWidth=0 marginHeight=0 src=http://www.wsview.com/yzplayerAction!play2.action?autoPlay=true&userVideoID=102264&iframe=iframe frameBorder=0 width=900 scrolling=no height=600 allowTransparency></iframe>";
			//<iframe marginWidth=0 marginHeight=0 src='http://www.wsview.com/yzplayerAction!play2.action?autoPlay=true&userVideoID=84293&iframe=iframe' frameBorder=0 width=900 scrolling=no height=600 allowTransparency></iframe>
			//<iframe marginWidth=0 marginHeight=0 src='http://www.wsview.com/yzplayerAction!play2.action?autoPlay=true&userVideoID=99941&iframe=iframe' frameBorder='0' width='900' scrolling='no' height='600' allowTransparency  ></iframe>
			//<iframe src="http://www.tudou.com/programs/view/html5embed.action?type=0&code=TH2_JYClSmY&lcode=&resourceId=88968501_06_05_99" allowtransparency="true" allowfullscreen="true" allowfullscreenInteractive="true" scrolling="no" border="0" frameborder="0" style="width:900px;height:600px;"></iframe>
			//$("#overlay,"+popID).html(iframe);
			//$("#overlay, .pop-close").click(closePop);

			$(popID).html(iframe);

			$("#pop-video").click(closePop);
			$("#overlay," + popID).show();
			//$("#pop-vido").show();
			//$("#overlay").show();
		}
		function closePop() {
			$(".videoPop .pop-content").empty();
			$("#pop-video").html("");
			$("#overlay, .pop").hide();
		}
		$("#overlay, .pop-close").click(closePop);

		function openVideo() {

			//$("#pop-vido").innelHTML()
			//$("#pop-vido").show();
			var str = "#pop-video";
			openPop(str);
		}
	</script>

</body>
</html>