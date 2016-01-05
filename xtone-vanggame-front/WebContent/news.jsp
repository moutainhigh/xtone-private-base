<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="org.common.util.ConnectionService"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.vanggame.info.Content"%>
<%@page import="org.vanggame.util.PageUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta name="description"
	content="Bootstrap.">
<meta name="keywords"
	content="HTML, CSS, JS, JavaScript, framework, bootstrap, front-end, frontend, web development">
<%
	int pageIndex =1;
	try{
		String index = request.getParameter("pageindex");
		pageIndex =Integer.parseInt(index);
	}catch(Exception e){
// 		System.out.println("第一页");
// 		e.printStackTrace();
	}
	int count = 0;

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Content> list = new ArrayList<Content>();
	try {
		con = ConnectionService.getInstance().getConnectionForLocal();
		String limit = " limit " + 6 * (pageIndex - 1) + "," + 6;
		String sql = "SELECT id,`title`,`content`,`lastModifyTime`,`catalog` FROM `tbl_cms_contents` WHERE `catalog` LIKE '%news%' AND `status`=1 ORDER BY lastModifyTime DESC "
				+ limit;
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
			Content news = new Content();
			news.setId(rs.getInt("id"));
			news.setCatalog(rs.getString("catalog"));
			news.setTitle(rs.getString("title"));
			news.setContent(rs.getString("content"));
			news.setLastModifyTime(rs.getLong("lastModifyTime"));
			list.add(news);
		}

		sql = "SELECT count(*) count FROM `tbl_cms_contents` WHERE `catalog` LIKE '%news%' AND `status`=1 ";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			int m = 1;
			count = rs.getInt(m);
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
<title>万家游戏-新闻动态</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/docs.min.css" rel="stylesheet">
<!--[if lt IE 9]><script src="assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script async="" id="gauges-tracker" data-site-id="4f0dc9fef5a1f55508000013" src="js/bootstrap/track.js"></script>
<script async="" src="js/bootstrap/analytics.js"></script>
<script src="js/bootstrap/ie-emulation-modes-warning.js"></script>
<!--[if lt IE 9]><script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
<!-- <script>!function(e,t,a,n,c,o,s){e.GoogleAnalyticsObject=c,e[c]=e[c]||function(){(e[c].q=e[c].q||[]).push(arguments)},e[c].l=1*new Date,o=t.createElement(a),s=t.getElementsByTagName(a)[0],o.async=1,o.src=n,s.parentNode.insertBefore(o,s)}(window,document,"script","//www.google-analytics.com/analytics.js","ga"),ga("create","UA-146052-10","getbootstrap.com"),ga("send","pageview");</script> -->
<style>
.newsli {
	border-bottom: #e1e1e0 solid 1px;
	padding-top: 15px;
	padding-bottom: 16px;
}

@media ( min-width :768px) {
	.newsli {
		border-bottom: #e1e1e0 solid 1px;
		padding-top: 11px;
		padding-bottom: 11px;
	}
}

@media ( min-width :1201px) {
	.newsli {
		border-bottom: #e1e1e0 solid 1px;
		padding-top: 16px;
		padding-bottom: 16px;
	}
}

.newsli i {
	color: #aaaaaa;
}

.newsli em {
	color: #aaaaaa;
}

.links {
	color: #aaaaaa;
	text-decoration: none;
}

.title {
	font-size: 30px;
	color: #cf1232;
	margin: 0;
}

.content {
	background: #fff;
	padding: 15px 10px 15px 10px;
}

.titleimg {
	padding-top: 10px;
	margin-right: 10px;
}

.content-all {
	float: right;
	color: #8f8f8f;
}

.content-title {
	float: left;
	font-size: 24px;
	color: #cf1232;
}

.content-time {
	float: right;
	padding-top: 10px;
	color: #cf1232;
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
					<span class="sr-only"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="#"><img alt="logo" src="images/logo.png"></a>
			</div>
			<nav id="bs-navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right ztgs">
					<li><a id="index" href="index.jsp"><small class="nav-chinese">首页</small><br><small class="nav-english">HOME</small></a></li>
					<li class="active"><a id="news" href="news.jsp"><small class="nav-chinese">新闻动态</small><br><small class="nav-english">NEWS</small></a></li>
					<li><a id="partform" href="porducts-sjyx.html"><small class="nav-chinese">旗下产品</small><br><small class="nav-english">PORDUCTS</small></a></li>					
					<li><a id="abouts" href="about-us-gsjs.html"><small class="nav-chinese">关于我们</small><br><small class="nav-english">VANGGAME</small></a></li>
					<li><a id="cooperation" href="cooperation.html"><small class="nav-chinese">商务合作</small><br><small class="nav-english">COOPERATION</small></a></li>
					<li><a id="join" href="join-us-shzp.html"><small class="nav-chinese">招贤纳士</small><br><small class="nav-english">JOIN&nbsp;US</small></a></li>
					
				</ul>
			</nav>
		</div>
	</header>
	<div class="col-sm-12 col-sm-12 col-xs-12 nopadding"><img src="images/news/banner-news.png" class="bsimg"></div>
	<div class="bs-docs-featurette" style="background: #f1f1f1">
		<div class="container">
			<div class="row ztgs">
				<div class="col-md-12 col-sm-12 col-xs-12 nopadding">
					<a href="#"><img src="images/news/news.png" class="bsimg tab"></a>
				</div>
				<%
						for (Content news : list) {
							String content = news.getContent();
							if (content.length() > 150) {
								content = content.substring(0, 150) + "...";
							}
					%>
				<div class="col-md-12 col-sm-12 col-xs-12 content">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="col-md-12 col-sm-12 col-xs-12 nopadding">
							<div class="col-md-9 col-sm-9 col-xs-9 nopadding">
								<a href="news-content.jsp?pageindex=<%=pageIndex%>&id=<%=news.getId()%>"target="_blank"><strong class="content-title"><%=news.getTitle()%></strong></a>
							</div>
							<div class="col-md-3 col-sm-3 col-xs-3 nopadding">
								<small class="content-time"><%=news.getTimeStr()%></small>
							</div>
						</div>
						<%=content%>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<a
							href="news-content.jsp?pageindex=<%=pageIndex%>&id=<%=news.getId()%>"
							target="_blank" class="content-all">[阅读全文]</a>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12 nopadding">
						<hr style="margin: 0px;">
					</div>
					
				</div>
				<%
						}
					%>
				<div class="col-md-12 col-sm-12 col-xs-12"
					style="margin-top: 10px;">
					<p style="float: right">
						<%
							String result = PageUtil.initPageQuery("news.jsp", null, count, pageIndex);
						%>
						<%=result%>
					</p>
				</div>
				<div class="col-md-12 col-sm-12 col-xs-12 bottom-hieght"></div>
			</div>
		</div>
	</div>
	<footer class="bs-docs-footer" role="contentinfo">
	<jsp:include page="footer.html"/>
	</footer>
	
	<script	src="/js/bootstrap/jquery.min.js"></script>
	<script	src="/js/bootstrap/bootstrap.min.js"></script>
	<script	src="/js/docs.min.js"></script>
	<script	src="/js/bootstrap/ie10-viewport-bug-workaround.js"></script>
<!-- 	<script>var _gauges=_gauges||[];!function(){var e=document.createElement("script");e.async=!0,e.id="gauges-tracker",e.setAttribute("data-site-id","4f0dc9fef5a1f55508000013"),e.src="//secure.gaug.es/track.js";var t=document.getElementsByTagName("script")[0];t.parentNode.insertBefore(e,t)}();</script> -->
	<script type="text/javascript">
$(function(){
	$('.hot_list .hot_gamebox').hotlist();
})
</script>
</body>
</html>