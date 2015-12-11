<%@page import="org.common.util.ConnectionService"%>
<%@page import="com.system.database.ConnConfigMain"%>
<%@page import="com.xtone.util.StringUtil"%>
<%@page import="com.xtone.util.PageUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.demo.info.News"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int count = 0;
	int pageIndex = StringUtil.getInteger(request.getParameter("pageindex"), 1);
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	System.out.println("news load...");
	List<News> list = new ArrayList();
	try{
		System.out.println("try load...");
		//con = ConnConfigMain.getConnection();
		con = ConnectionService.getInstance().getConnectionForLocal();
		System.out.println("get Connection..");
		String limit = " limit "+12*(pageIndex-1) + "," + 12;
		String sql = "SELECT id,`title`,`lastModifyTime`,`catalog` FROM `tbl_cms_contents` WHERE `catalog` LIKE '%material%' AND `status`=1 " +limit;
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()){
			News news = new News();
			news.setId(rs.getInt("id"));
			news.setTitle(rs.getString("title"));
			news.setAddTime(rs.getLong("lastModifyTime"));
			news.setCatalog(rs.getString("catalog"));
			list.add(news);
		}
		
		sql = "SELECT count(*) count FROM `tbl_cms_contents` WHERE `catalog` LIKE '%material%' AND `status`=1 ";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next())
			count = rs.getInt(1);
	}catch(Exception e){
		e.printStackTrace();
	}finally{
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
    <title>神魔战歌详情</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content="">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta name="description" content="">
     <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

    <!-- PM提取meta start -->


    <!-- PM提取meta end -->

    <link rel="dns-prefetch" href="http://res.nie.netease.com">
    <!--[if lt IE 9]>

    <![endif]-->

    <!-- /* 统一应用文章页公共样式 */ -->
    <link rel="stylesheet" href="../js-css/material/art.com.css">
    <!-- 引用本页css -->
    <!-- jquery mix NIE (最新版本）-->
    <script charset="utf-8" src="../js-css/material/jquery(mixNIE).last.js"></script>
    <!--lib中的文件，自动合并为一个文件-->

    <link type="text/css" rel="stylesheet" href="../js-css/material/newDetail_79f84a9.css">
<script defer="true" charset="gbk" src=""></script><style type="text/css">#NIE-copyRight a{text-decoration: none;}#NIE-copyRight a:hover{text-decoration: underline;}</style><style type="text/css">.adbase-ctn{background:url(http://res.nie.netease.com/comm/js/nie/util/img/loading.png) center center no-repeat;}</style><style type="text/css">#NIE-topBar .hide,#NIE-topBar-include .hide{display:none!important}.NIE-topBar-logo,.NIE-topBar-arrIcon i,#NIE-topBar-menu span,.NIE-topBar-hot,.NIE-topBar-1st,.NIE-topBar-new,.NIE-topBar-btn{background:url(http://res.nie.netease.com/comm/nie.topBar/images/sprite-w_7fc7459.png) no-repeat}.NIE-topBar-arrIcon{position:relative;top:4px;width:16px;height:16px;display:inline-block;background-color:#fff;overflow:hidden}.NIE-topBar-arrIcon i{width:9px;height:4px;float:left;margin:6px 0 0 4px;background-position:-103px -101px;display:inline-block;-webkit-transition:all .2s ease 0s;transition:all .2s ease 0s}#NIE-topBar,#NIE-topBar-include{position:relative;z-index:9999;min-width:1000px;height:40px;background:#333;line-height:24px;font-family:"Microsoft YaHei",simSun,"Lucida Grande","Lucida Sans Unicode",Arial}#NIE-topBar *,#NIE-topBar-include *{font-size:12px;font-style:normal}#NIE-topBar a,#NIE-topBar-include a{color:#999;text-decoration:none}#NIE-topBar a:hover,#NIE-topBar-include a:hover{color:#fff}#NIE-topBar ul,#NIE-topBar li,#NIE-topBar-include ul,#NIE-topBar-include li{margin:0;padding:0;float:left}.NIE-topBar-main{position:relative;z-index:9999;width:1000px;margin:auto}.NIE-topBar-logo{width:133px;height:44px;display:block;float:left;margin-top:6px;background:url(http://res.nie.netease.com/comm/nie.topBar/images/logo_b5525b7.png) no-repeat}#NIE-topBar-news{position:absolute;z-index:13;width:420px;height:54px;left:125px;padding-left:18px;padding-top:0;overflow:hidden;display:block}#NIE-topBar-news.NIE-topBar-news-hover{height:250px;overflow:visible;border-color:#1f1f1f}#NIE-topBar-news a{width:960px;display:block;height:0;overflow:hidden;z-index:1;-webkit-box-shadow:0 2px 4px rgba(0,0,0,.2);-o-box-shadow:0 2px 4px rgba(0,0,0,.2);-moz-box-shadow:0 2px 4px rgba(0,0,0,.2);box-shadow:0 2px 4px rgba(0,0,0,.2)}#NIE-topBar-news-close{position:absolute;top:63px;right:-380px;display:block;width:32px;height:32px;overflow:hidden;background:url(http://res.nie.netease.com/comm/nie.topBar/images/sprite-w_7fc7459.png) no-repeat -9999px -9999px;text-indent:-9999px;z-index:2}#NIE-topBar-news.NIE-topBar-news-hover .NIE-topBar-abc-s{visibility:hidden}#NIE-topBar-news.NIE-topBar-news-hover a{position:absolute;z-index:2;left:-132px;top:55px;height:auto}#NIE-topBar-news.NIE-topBar-news-hover a img{display:block}.NIE-topBar-mid{position:absolute;z-index:2;right:230px;width:385px;text-align:right}#NIE-topBar .NIE-topBar-mid a,#NIE-topBar-include .NIE-topBar-mid a{line-height:40px;padding:0 12px;color:#fff;display:inline-block}#NIE-topBar .NIE-topBar-mid a:hover,#NIE-topBar-include .NIE-topBar-mid a:hover{color:#ca0b0b}#NIE-topBar-menu{position:absolute;z-index:1;top:0;right:0;color:#999;width:1000px;height:40px;overflow:hidden;-webkit-transition:height .3s ease 0s;-o-transition:height .3s ease 0s;-moz-transition:height .3s ease 0s;transition:height .3s ease 0s}#NIE-topBar-menu span{position:absolute;width:108px;padding:8px 0 12px;height:20px;line-height:14px;right:106px;background-position:0 -28px;background-repeat:repeat-x;padding-left:15px;color:#fff;display:inline-block;overflow:hidden}#NIE-topBar-menu .NIE-table{position:absolute;top:40px;left:0;background:#262626;border-collapse:collapse;overflow:hidden;width:100%}#NIE-topBar-menu .NIE-table a{width:100px;margin-left:6px;line-height:24px;display:inline-block}#NIE-topBar-menu .NIE-table a.long{width:125px}#NIE-topBar-menu .NIE-table ul{list-style:none}#NIE-topBar-menu .NIE-table .NIE-nav{width:100%;position:relative}#NIE-topBar-menu .NIE-table .NIE-nav li{width:110px;float:left;border-right:1px solid #eee;vertical-align:top}#NIE-topBar-menu .NIE-table .NIE-nav .mobi{position:absolute;left:387px;-webkit-transition:all .3s ease-out}#NIE-topBar-menu .NIE-table b{line-height:39px;color:#bc2e2e;display:block;width:100%;height:39px;font-size:14px;text-indent:20px;background:#f7f7f8;overflow:hidden;clear:both}#NIE-topBar-menu b i{margin-left:5px;padding:2px 4px;color:#9b9b9b;font-style:normal;background:#111;display:inline}#NIE-topBar-menu .NIE-list{float:left;overflow:hidden;width:100%;height:400px}.w1680 #NIE-topBar-menu .NIE-list{height:450px}#NIE-topBar-menu .NIE-list li{border-right:1px solid #eee;height:100%;float:left;padding:10px 0 0 14px}#NIE-topBar-menu .NIE-list .news{position:absolute;background:#fdfefe;left:545px;-webkit-transition:all .3s ease-out;top:39px}#NIE-topBar-menu .NIE-list .hot{position:absolute;background:#fdfefe;left:387px;-webkit-transition:all .3s ease-out;top:39px}#NIE-topBar-menu .NIE-list .hot a{width:125px}.w1680 #NIE-topBar-menu .NIE-list .news div div{margin-right:8px}#NIE-topBar-menu .NIE-list .news .long-news{width:133px;float:left}#NIE-topBar-menu .NIE-list .news .long-news a{width:125px}#NIE-topBar-menu .NIE-list .news .short-news{float:left;width:106px}#NIE-topBar-menu .moreAni .NIE-list .news{left:313px;width:524px!important;-webkit-transition:all .3s ease-out}#NIE-topBar-menu .moreAni .NIE-list .hot{left:155px;border-left:1px solid #eee;box-shadow:-1px 0 1px #eee;-webkit-transition:all .3s ease-out}#NIE-topBar-menu .moreAni .NIE-nav .mobi{left:155px;width:696px!important;border-left:1px solid #eee;box-shadow:-1px 0 1px #eee;-webkit-transition:all .3s ease-out}#NIE-topBar-menu .NIE-list .NIE-topBar-more{width:20px;height:95px;height:92px\9;display:block;position:absolute;right:0;top:124px;font-size:12px;color:#fff;background:#bc2e2e;padding-left:10px;line-height:14px;padding-top:7px;border-top-left-radius:2px;border-bottom-left-radius:2px}#NIE-topBar-menu .NIE-list .NIE-topBar-more:hover{opacity:.8}#NIE-topBar-menu .moreAni .NIE-topBar-more{width:21px;height:23px;border-radius:2px;right:9px;top:163px;padding-left:4px;padding-top:2px;height:20px;height:18px\9}.w1680 #NIE-topBar-menu .NIE-list .NIE-topBar-more{display:none}#NIE-topBar-menu .NIE-table a em{font-weight:400;display:block;line-height:24px}.NIE-topBar-1st{background-position:right -267px}.NIE-topBar-hot{background-position:right -144px}.NIE-topBar-new{background-position:right -206px}#NIE-topBar-menu.NIE-topBar-menu-hover .NIE-table{display:block}#NIE-topBar-menu.NIE-topBar-menu-hover{height:505px;opacity:.9;-webkit-transition:height .4s ease 0s;-o-transition:height .4s ease 0s;-moz-transition:height .4s ease 0s;transition:height .4s ease 0s}a.NIE-topBar-btn{width:105px!important;height:18px;line-height:18px!important;margin:5px 0;display:inline-block;padding-left:0!important;background-position:0 -338px;text-indent:25px;color:#bc3030!important}a.NIE-topBar-btn:hover{}.NIE-topBar-ad{height:40px;left:50%}.NIE-topBar-main.w1680{width:1260px}.NIE-topBar-main.w1680 #NIE-topBar-menu{width:1260px}#NIE-topBar-news{height:54px}#NIE-topBar .NIE-topBar-menu-hover,#NIE-topBar-include .NIE-topBar-menu-hover{z-index:8}.NIE-topBar-ad-big{position:absolute;top:0;left:0;z-index:10;height:0;overflow:hidden;text-align:center}#NIE-topBar-menu{z-index:7;height:55px}.NIE-topBar-mid{z-index:12;width:250px;right:230px;width:536px}.NIE-topBar-logo{position:relative;z-index:13!important}#NIE-topBar,#NIE-topBar-include{height:55px;background:#fbfbfb}.NIE-topBar-logo{margin-top:5px}#NIE-topBar-menu .NIE-table{top:55px;background:#fdfefe;border-top:1px solid #ececec;margin:0}#NIE-topBar-menu span{background:0 0;color:#bc2e2e}#NIE-topBar-menu .NIE-table{display:none}#NIE-topBar-menu .NIE-table .last_td,#NIE-topBar-menu .NIE-table .last_th{border-right:0}#NIE-topBar a,#NIE-topBar-include a{color:#626262}#NIE-topBar-menu b i{background-color:#fbfbfb;color:#bc2e2e}#NIE-topBar-menu p{padding:0;margin:0;margin-left:6px;line-height:24px;margin-bottom:4px;font-weight:700;color:#626262}#NIE-topBar a:hover,#NIE-topBar-include a:hover{color:#bc2e2e}#NIE-topBar-menu.NIE-topBar-menu-hover{opacity:.95}#NIE-topBar-menu .NIE-table *{font-size:12px}#NIE-topBar-menu.NIE-topBar-menu-hover span{border-bottom:3px solid #bc2e2e;background-color:#F3F3F3;background-image:-webkit-linear-gradient(top,#F3F3F3 0,#FEFEFE 52px);background-image:linear-gradient(top,#F3F3F3 0,#FEFEFE 52px)}#NIE-topBar-menu span{height:25px;height:26px\0;width:108px;padding-top:15px;padding-top:14px\0;transition:background ease-in-out .33s;z-index:20}#NIE-topBar .NIE-topBar-mid a,#NIE-topBar-include .NIE-topBar-mid a{color:#333;line-height:52px;transition:background ease-in-out .33s;*line-height:55px}#NIE-topBar .NIE-topBar-mid a:hover,#NIE-topBar-include .NIE-topBar-mid a:hover{color:#bc2e2e!important;border-bottom:3px solid #bc2e2e;background-color:#F3F3F3;background-image:-webkit-linear-gradient(top,#F3F3F3 0,#FEFEFE 52px);background-image:linear-gradient(top,#F3F3F3 0,#FEFEFE 52px);*border-bottom:0}.NIE-topBar-right{position:absolute;right:0;top:0;display:block;width:100px;height:55px;overflow:hidden;z-index:9}.NIE-topBar-right a{color:#333!important;width:100%;text-align:center;display:inline-block;float:left;line-height:52px;transition:background ease-in-out .33s;-webkit-transition:background ease-in-out .33s}.NIE-topBar-right a:hover{color:#bc2e2e!important;border-bottom:3px solid #bc2e2e;background-color:#F3F3F3;background-image:-webkit-linear-gradient(top,#F3F3F3 0,#FEFEFE 52px);background-image:linear-gradient(top,#F3F3F3 0,#FEFEFE 52px);*border-bottom:0}.NIE-topBar-right a.fadeUp{opacity:0;-webkit-transform:translate3d(0,-70%,0);-webkit-transition:all ease-in-out .5s;transform:translate3d(0,-70%,0);transition:all ease-in-out .5s}.NIE-topBar-right a.none{opacity:0;-webkit-transform:translate3d(0,70%,0);transform:translate3d(0,70%,0)}.NIE-topBar-right a.fadeUp2{opacity:1;-webkit-transform:translate3d(0,0,0);-webkit-transition:all ease-in-out .5s;transform:translate3d(0,0,0);transition:all ease-in-out .5s}
		 #news{background:url("../img/material/2_2.png");}
		 #news:hover{background:url("../img/material/2_1.png");}
		 #material{background:url("../img/material/3_2.png");}
		 #material:hover{background:url("../img/material/3_1.png");}
		 #forum{background:url("../img/material/4_2.png");}
		 #forum:hover{background:url("../img/material/4_1.png");}
		 #home{background:url("../img/material/1_2.png");}
		 #home:hover{background:url("../img/material/1_1.png");}

		 #first{display: block;width: 100%;height: 100%;color: #6E6E6E;}
		 #first:hover{display: block;width: 100%;height: 100%;color: #A99359;border-bottom: 2px solid #A99359;}
		 #news2{display: block;width: 100%;height: 100%;color: #6E6E6E;}
		 #news2:hover{display: block;width: 100%;height: 100%;color: #A99359;border-bottom: 2px solid #A99359;}
		 #forum2{display: block;width: 100%;height: 100%;color: #6E6E6E;}
		 #forum2:hover{display: block;width: 100%;height: 100%;color: #A99359;border-bottom: 2px solid #A99359;}
		 #home{background:url("../img/material/1_2.png");}
		 #home:hover{background:url("../img/material/1_1.png");}

		.nav{width:100%;height:81px;background:url(../img/material/bg_Navigator.png) no-repeat center 0}
		.menu li{width:163px;height:81px;float:left;margin:5px 13px;}
	    .menu a{display:block;width:163px;height:81px;text-indent:100px;white-space:nowrap;overflow:hidden;}
		.menu,.menu li{padding:0;margin: 0px ; }
	    .menu{width:1300px;-margin:0 ;list-style-type:none;-float: right;margin-left:39% }
		.test-top{height: 55px;background: #FBFBFB none repeat scroll 0% 0%;}
		#NIE-topBar *, #NIE-topBar-include * {font-size: 12px;font-style: normal;}
		.test-top-main {position: relative;z-index: 9999;width: 1165px;margin: auto;}
		.test-top-logo {width: 158px;height: 64px;display: block;float: left;margin-top: 7px;margin-left: 0%;background: transparent url(../img/material/top_corp.png) no-repeat scroll 0% 0%;}
		.test-topBar-mid {z-index: 12;right: 230px;width: 536px;position: absolute;text-align:right;-font-size: 12px;font-style: normal;line-height: 52px;-color: #333;-transition: background 0.33s ease-in-out 0s;}
		.test-topBar-mid a{line-height: 52px;color: #333;transition: background 0.33s ease-in-out 0s;display: inline-block;padding: 0px 12px;text-decoration: none;}
		.test-foot{font-size: 12px;text-decoration: none;color: #A0A0A0;padding: 35px 0px 60px;}

		body{color:rgb(248, 244, 245);}
		body {width:100%;min-height:1305px!important;font-family:"微软雅黑";color:#737C86;background-color:#2C0524!important}
		*{margin:0px;padding:0px;}
</style></head>


<body>

    




    <!-- 将临天下新闻详情 start -->
<div class="nav">
	  <div class="test-top-main" id="NIE-topBar-main">
        <a class="test-top-logo" href="#" ></a>
      </div>
      <ul class="menu">
        <li ><a href="main.jsp" id="home"></a></li>
		<li><a href="news.jsp" id="news"></a></li>
		<li><a href="material.jsp" id="material"></a></li>
		<li ><a href="forum.jsp" id="forum"></a></li>
      </ul>
</div>
<!--This is Git Project-->
    <div class="wrap" style="    background: transparent url(../img/material/bg_1.jpg) no-repeat scroll center 0px;">
        <div class="right-wrap">
            <div class="content-wrap" style="margin-left: 0%;">
                <div class="content_title" style="background: url(../img/material/title_bg.png) 50% 0px no-repeat;">
                    <div class="title" style="background: url(../img/material/material.png) 50% 0px no-repeat;"></div>
                    <span>当前位置：<a href="main.jsp">官网首页</a> &gt; 游戏资料</span>
                </div>
                <div class="content_nav" style="width: 92%;height: 68px;margin-left: 33px;border-bottom: 1px solid #EBEDEE;">
                    <ul class="navList" style="width:761px;height:67px;margin_left:33px;boder_bootom:1px solid rgb(235,237,238);">
                        <li class="active" style="float: left;width: 116px;height: 66px;text-align: center;line-height: 80px;font-size: 18px;"><a id="first" href="first.jsp" >最新</a></li>
                        <li style="float: left;width: 116px;height: 66px;text-align: center;line-height: 80px;font-size: 18px;"><a id="news2" href="news.jsp" >新闻</a></li>
                        <li style="border-bottom: 2px solid #A99359;float: left;width: 116px;height: 66px;text-align: center;line-height: 80px;font-size: 18px;"><a id="material2" href="material.jsp" style="color: rgb(169, 147, 89);display: block;width: 100%;height: 100%;">资料</a></li>
                        <li style="float: left;width: 116px;height: 66px;text-align: center;line-height: 80px;font-size: 18px;"><a id="forum2" href="forum.jsp" >论坛</a></li>
                        <div style="clear: both;"></div>
                    </ul>
                </div>
                <div class="content_list_wrap">
                    <ul>
                    	<%
                    		for(News news : list){
                    			
                    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    			String timeStr = sdf.format(news.getAddTime());
                    	%>
                    	<li class="content_list">
                        <a href="content.jsp?id=<%=news.getId() %>" ><em>【资料】 </em><%=news.getTitle() %></a>
                        <span class="content_date"><%=timeStr%></span>
                    	</li>
                    	<%} %>
                    	
                        

                    </ul>
                </div>


                <div class="pageNum">
                    <p>


	                   <%
							PageUtil util = new PageUtil();
							String result = util.initPageQuery("material.jsp", null , count, pageIndex);
							
						%>
						<%=result%>

                    </p>
                </div>
            </div>
            <!-- 分享 start -->
            <div id="NIE-share" class="jltx-share"></div>
            <!-- 分享 end   -->
	<div class="" style="background: transparent url(../img/material/top.png) no-repeat scroll 0% 0%;width:43px;height:133px;right:9%;top:62%;position:fixed;" >
		<a href="#" style="width:43px;height:133px;display: block;"></a>
	</div>
            <!--此乃底部-->
            </div>
	<div id="" style="background-color: black;" class="test-foot">
		<p id="NIE-copyRight-corp" style="width:100%;text-align:center;margin:0 auto;padding:15px 0 0 0;font-size:12px;font-family:&#39;宋体&#39;;line-height:20px;display:block;position:relative;">
			<span style="position:relative;vertical-align:top;top:4px;display:inline-block;position:relative;">
			<a href="http://www.nomo.cn"  style="width:224px;height:28px;padding-right:15px;display:inline-block;cursor:pointer;background:url(../img/news/corp.png) no-repeat"></a>
			</span>
			<span style="text-align:left;display:inline-block;padding-left:6px;">
				<span id="ncp-l1">
					<a href="javascript:alert('公司简介！');"  style="color: #A0A0A0;">相关法律</a>
					-
					<a href="javascript:alert('公司简介！');" style="color: #A0A0A0;">翔通游戏</a>
					-
					<a href="javascript:alert('公司简介！');" style="color: #A0A0A0;">联系我们</a>
					-
					<a href="javascript:alert('公司简介！');" style="color: #A0A0A0;">商务合作</a>
				</span>
				<br>
				<span id="ncp-l2">翔通游戏 创造精彩世界 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://www.uupark.com/jzjh_xtonegroup/supervise.html" target="_blank" style="color: #A0A0A0;">家长监护</a></span>
				<br>
				<span id="ncp-l2">粤网文（2015）1397-182号&nbsp;<a href="http://www.miitbeian.gov.cn/state/outPortal/loginPortal.action" target="_blank" style="color: #A0A0A0;">粤ICP备12058124号-20</a></span>
				</span></p></div>
    </div>
    <!-- 将临天下新闻详情 end -->








</body></html>