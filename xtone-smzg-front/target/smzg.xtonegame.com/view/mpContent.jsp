<%@page import="com.xtone.util.CheckLoad"%>
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
	String url = request.getRequestURL().toString();
	int id = StringUtil.getInteger(request.getParameter("id"), 1);
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String exchange = "";
	List<News> list = new ArrayList();
	try{
		//con = ConnConfigMain.getConnection();
		con = ConnectionService.getInstance().getConnectionForLocal();
		String sql = "SELECT `title`,`lastModifyTime`,`catalog`,`content` FROM `tbl_cms_contents` WHERE id=? ";
		
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		
		if(rs.next()){
			News news = new News();
			news.setTitle(rs.getString("title"));
			news.setAddTime(rs.getLong("lastModifyTime"));
			news.setCatalog(rs.getString("catalog"));
			news.setContent(rs.getString("content"));
			list.add(news);
		}
		
		sql = "SELECT * FROM `tbl_exchange_codes`;";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next()){
			exchange = rs.getString("content");
		}
		
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
<!DOCTYPE html>
<html lang="zh-cmn-Hans"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style type="text/css" id="znBdcsStyle">#bdcs-rec{display:none;}</style><meta charset="utf-8">
<title>花千骨手游_花千骨手游官网_攻略_下载_礼包_18183花千骨专区</title>
<!-- 优先使用 IE 最新版本和 Chrome -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 页面描述 -->
<meta name="description" content="2015最酷炫神魔题材手游《神魔战歌》正式登陆。神魔狂战，鹿死谁手!集齐史上各路神魔，养成史上最强神魔英雄。突破传统动作卡牌限制，领地建设给你带来不一样的养成体验~">
<!-- 页面关键词 -->
<meta name="keywords" content="神魔,魔幻,战歌,卡牌,魔兽,刀塔,LOL,养成,RPG,动作,游戏,手游,点控,酷炫特效,美女,英雄,大集合,大乱斗,MOBA,公会战,图腾,女神">
<!-- 为移动设备添加 viewport -->
<meta name="viewport" content="width=device-width,initial-scale=1, minimum-scale=1.0, maximum-scale=1, user-scalable=no">
<link rel="shortcut icon" href="../img/mp_load/ico.png">
<link rel="stylesheet" href="../js-css/mp_news/index.css">
<script charset="gb2312" type="text/javascript" src="../js-css/mp_news/jquery(mixNIE).last.js"></script>
<script charset="gb2312" type="text/javascript" src="../js-css/mp_news/index2.js"></script>
</head>
<body>
<!--<script src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/core_002.php" charset="utf-8" async="" type="text/javascript"></script><script src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/core.php" charset="utf-8" async="" type="text/javascript"></script><script src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/core_003.php" charset="utf-8" async="" type="text/javascript"></script><script src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/js" async="" type="text/javascript"></script><script src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/jquery-2.js" type="text/javascript"></script>
<script src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/hanawa.js" type="text/javascript" charset="utf-8"></script>
<script src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/js.php" type="text/javascript"></script>
<script>newhanawa.createAdBanner("wapqzttBanner",2);</script>-->

<%
                    	String catalog="";
                    	for(News news:list){
                			catalog = news.getCatalog();
                			if(catalog.equals("news")){
                				catalog = "新闻中心";
                			}else if(catalog.equals("material")){
                				catalog = "游戏资料";
                			}else if(catalog.equals("forum")){
                				catalog = "官方论坛";
                			}
                    	
                    %>
<!--通用头部-->
<header class="header">
    <div class="top-bar">
        <div class="con-tit"><a href="javascript:;"><%=catalog %></a></div>
        <div class="fr"><span class="mod-tit1" style="background: transparent url('../img/mp_list/bg_m.png') no-repeat scroll center center / auto 25px;"><i class="sign" id="nav_forum" onclick="headerToggle()"></i></span></div>
        <div class="logo fl" style="background: transparent url('../img/mp_news/back.png') no-repeat scroll left center / 20px auto;"><a href="javascript:history.back(-1);" ></a></div>
    </div>
    <nav class="nav-bar" id="j_head_nav" style="display:none;">
    <div class="t">
    	<div style="float:right; width:50%; margin-top:2px;">
		<script type="text/javascript">(function(){document.write(unescape('%3Cdiv id="bdcs"%3E%3C/div%3E'));var bdcs = document.createElement('script');bdcs.type = 'text/javascript';bdcs.async = true;bdcs.src = 'http://znsv.baidu.com/customer_search/api/js?sid=2207325287948193293' + '&plate_url=' + encodeURIComponent(window.location.href) + '&tn=SE_hldp07461_tf7tzn3j&t=' + Math.ceil(new Date()/3600000);var s = document.getElementsByTagName('script')[0];s.parentNode.insertBefore(bdcs, s);setTimeout(function () {var tn_s=document.createElement('input');tn_s.type='hidden';tn_s.value='SE_hldp07461_tf7tzn3j';tn_s.name='tn';if(document.getElementById('bdcs-search-form'))document.getElementById('bdcs-search-form').appendChild(tn_s);},1000);})();</script><div id="bdcs"></div>
        	<div class="clear"></div>
        </div>
        <a href="http://www.18183.com/sitemap.html" style="display:none">站点地图</a>
        <span>频道</span>
    </div>
    <div class="nav">
        <ul class="n_c">
            <li><a href="http://m.18183.com/xin">新游</a></li>
            <li><a href="http://bbs.18183.com/">论坛</a></li>
            <li><a href="http://ka.18183.com/">礼包</a></li>
            <li><a href="http://m.18183.com/yy">娱乐</a></li>
            <li><a href="http://m.18183.com/chanye">产业</a></li>
        </ul>
        <ul class="n_c">
            <li><a href="http://bbs.18183.com/shop">商场</a></li>
            <li><a href="http://hd.18183.com/">活动</a></li>
            <li><a href="http://m.18183.com/android">安卓</a></li>
            <li><a href="http://m.18183.com/iphone">iOS</a></li>
            <li><a href="http://m.18183.com/zhuangqu.html">专区</a></li>
        </ul>
    </div>
</nav>
<script>newhanawa.createAdBanner("topwap",2);</script>
</header>
<!--导航-->
<nav style="position: fixed;" class="nav-mod" id="nav-mod">
	<ul>
    	<!--<li class="l1"><a class="cur" href="http://www.18183.com/hqg"><i>专区</i><span></span></a></li>
        <li class="l2"><a href="http://ku.18183.com/huaqiangu.html"><i>下载</i><span></span></a></li>
        <li class="l3"><a href="http://ka.18183.com/list_game_1922.shtml"><i>礼包</i><span></span></a></li>
        <li class="l4"><a href="http://bbs.18183.com/forum-3931-1.html"><i>论坛</i><span></span></a></li>-->
    </ul>
</nav> 
		
<article class="content">
	<!--专区游戏详情-->
    <!--<section class="re-mod">
    	<div class="gameinfo-mod clear">
			<figure>
						<div class="top-bar2">
							<div class="con-tit"><a href="javascript:;"></a></div>
							<div class="fr"><a class="mod-tit1" href="http://smzg.xtonegame.com/test2"><!--<i class="sign" id="nav_forum" onclick="headerToggle()"></i>--></a></div>
							<!--<div class="logo fl"><a href="javascript:;"></a></div>
						</div>
						<nav class="nav-bar" id="j_head_nav" style="display:none;">
						<div class="t">
							<div style="float:right; width:50%; margin-top:2px;">
							<script type="text/javascript">(function(){document.write(unescape('%3Cdiv id="bdcs"%3E%3C/div%3E'));var bdcs = document.createElement('script');bdcs.type = 'text/javascript';bdcs.async = true;bdcs.src = 'http://znsv.baidu.com/customer_search/api/js?sid=2207325287948193293' + '&plate_url=' + encodeURIComponent(window.location.href) + '&tn=SE_hldp07461_tf7tzn3j&t=' + Math.ceil(new Date()/3600000);var s = document.getElementsByTagName('script')[0];s.parentNode.insertBefore(bdcs, s);setTimeout(function () {var tn_s=document.createElement('input');tn_s.type='hidden';tn_s.value='SE_hldp07461_tf7tzn3j';tn_s.name='tn';if(document.getElementById('bdcs-search-form'))document.getElementById('bdcs-search-form').appendChild(tn_s);},1000);})();</script><div id="bdcs"></div>
								<div class="clear"></div>
							</div>
							<a href="http://www.18183.com/sitemap.html" style="display:none">站点地图</a>
							<span>频道</span>
						</div>
						<div class="nav">
							<ul class="n_c">
								<li><a href="http://m.18183.com/xin">新游</a></li>
								<li><a href="http://bbs.18183.com/">论坛</a></li>
								<li><a href="http://ka.18183.com/">礼包</a></li>
								<li><a href="http://m.18183.com/yy">娱乐</a></li>
								<li><a href="http://m.18183.com/chanye">产业</a></li>
							</ul>
							<ul class="n_c">
								<li><a href="http://bbs.18183.com/shop">商场</a></li>
								<li><a href="http://hd.18183.com/">活动</a></li>
								<li><a href="http://m.18183.com/android">安卓</a></li>
								<li><a href="http://m.18183.com/iphone">iOS</a></li>
								<li><a href="http://m.18183.com/zhuangqu.html">专区</a></li>
							</ul>
						</div>
					</nav>
					<script>newhanawa.createAdBanner("topwap",2);</script>
			</figure>
			<!--<div class="pop" id="pop-android" style="display: none;-background: rgba(231, 218, 218, 0.83) none repeat scroll 0% 0%;">
					  <img src="img/pop_bg_5401efd.jpg" alt="花千骨">
					  <div class="pop-close" style="-background: rgb(0, 0, 0) none repeat scroll 0% 0%;"></div>
					  <div class="pop-title"><span style="color: rgb(122, 17, 17);"></span>魔君！请收下我们的起义资金</span><br>
					  <em style="font-size:24px;">兑换码:</em><span>KF85190502</span><em>(仅限安卓)</em><br>
					  <em>更多的秘宝隐藏在下面的二维码中</em>
					  </div>
					  <div style="-background: white none repeat scroll 0% 0%;width: 75px;height: 75px;margin: auto;background: url(./img/code.jpg) no-repeat scroll left center / 75px auto;">
						 <!--<img src="./img/code.jpg" width="75px" height="75px" alt="" />-->
					  <!--</div>
					  <p class="pop-how2use">
						<b style="color:rgb(255,234,0);">兑换方式：</b>
						<span>首页</span> → 左上角“<span>更多</span>”按钮 → “<span>礼包码</span>”→ 输入礼包码即可。<br>
						<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp【IOS用户关注公众号领取专属福利】</span>
					  </p>

				</div>-->
    	<!--</div>
    </section>-->
    <!--mod-frame-1-->
    <%
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        			String timeStr = sdf.format(news.getAddTime());
     %>
    <section class="re-mod mod-frame-1" style="padding-bottom:0px;background: rgb(255, 255, 255) none repeat scroll 0% 0%;">
         <div id="NIE-art">
                    <h1 class="artTitle"><%=news.getTitle() %></h1>
                    <span class="artDate"><%=timeStr %></span>
                    <div class="artText">
                    	<%=news.getContent() %>
                        
	<div style="text-align: center;"><span style="font-family:微软雅黑;"><span style="font-size:14px;"><span style="color:#000000;"><!-- <img alt="\" src="./img/img_4.png" style="width: 500px; height: 292px;"> --></span></span></span></div>

                    </div> 
                </div>
    </section>
	 <%} %>
	<!--mod-frame-1-->
    <section class="re-mod mod-frame-1">
        <figure>
			<a href="" style="color: #20202C;text-decoration: none;display: block;width: 34%;height: 43px;-top: 70%;background: transparent url(../img/mp_news/btn_download.jpg) no-repeat scroll left center / 98px auto;position: absolute;left: 13%;margin-top: 6%;"></a>
			<a style="color: #20202C;text-decoration: none;display: block;width: 34%;height: 43px;-top: 70%;background: transparent url(../img/mp_news/btn.jpg) no-repeat scroll left center / 98px auto;position: absolute;left: 58%;margin-top: 6%;-bottom: -50%;" href="javascript:openPop('#pop-android');"></a>
			<!--<ul class="re-imgtext-15 clear">
					<li>
						<a href="" style="color: #20202C;text-decoration: none;display: block;width: 34%;height: 43px;-top: 70%;background: transparent url(img/btn_download.jpg) no-repeat scroll left center / 98px auto;position: absolute;left: 13%;margin-top: 6%;"></a>
						<!-- 图片按钮大小为98X32 -->
						<!--<img src="img/btn_download.png" alt="花千骨">-->
					<!--</li>
					<li>
						<a style="color: #20202C;text-decoration: none;display: block;width: 34%;height: 43px;-top: 70%;background: transparent url(img/btn.jpg) no-repeat scroll left center / 98px auto;position: absolute;left: 58%;margin-top: 6%;-bottom: -50%;" href="javascript:openPop('#pop-android');"></a>
					</li>
		   </ul>-->
		  <div class="pop" id="pop-android" style="display: none;-background: rgba(231, 218, 218, 0.83) none repeat scroll 0% 0%;">
					  <img src="../img/mp_load/pop_bg_5401efd.jpg" alt="神魔战歌">
					  <div class="pop-close" style="    background: transparent url('../img/mp_load/pop_close.png') no-repeat scroll 0% 0%;"></div>
					  <div class="pop-title"><span style="color: rgb(122, 17, 17);"></span>魔君！请收下我们的起义资金</span><br>
					  <em style="font-size:24px;">兑换码:</em><span><%=exchange %></span><em>(仅限安卓)</em><br>
					  <em>更多的秘宝隐藏在下面的二维码中</em>
					  </div>
					  <div style="-background: white none repeat scroll 0% 0%;width: 75px;height: 75px;margin: auto;background: url(../img/mp_load/code.jpg) no-repeat scroll left center / 75px auto;">
						 <!--<img src="./img/code.jpg" width="75px" height="75px" alt="" />-->
					  </div>
					  <p class="pop-how2use">
						<b style="color:rgb(255,234,0);">兑换方式：</b>
						<span>首页</span> → 左上角“<span>更多</span>”按钮 → “<span>礼包码</span>”→ 输入礼包码即可。<br>
						<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp【IOS用户关注公众号领取专属福利】</span>
					  </p>

				</div>
		   <a style="color: #20202C;text-decoration: none;display: block;width: 15%;height: 91px;-top: 70%;background: transparent url(img/backtop.png) no-repeat scroll left center / 50px auto;position: absolute;left: 76%;margin-top: 25%;-bottom: -50%;" href="#"></a>
		  <img src="../img/mp_news/bottom.png" alt="花千骨" style="width: 100%;">

		</figure>
    </section>

    <!--mod-frame-11-->
    <!--<section class="re-mod mod-frame-11">
    	<div class="re-tit2"><span class="sty-9"><a href="http://18183.com/hqg/xinwen/">更多&gt;&gt;</a><h4>最新资讯</h4></span></div>
        <div class="hr_20"></div>
        <div class="re-imgtext-1">
        	<figure>
                <a href="http://m.18183.com/hqg/201511/473674.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-15112G60I50-L.jpg"></a>
                <figcaption>
                    <h4>噩梦南弦月3星视频攻略</h4>
                    <p>导语：花千骨手游平民属性3星噩梦南弦月的视频攻略带给大家，南弦月拥有超级高的伤害，还拥有超强控制能力，一旦吸血没吸得...</p>
                    <div class="btn">
                       <a href="http://m.18183.com/hqg/201511/473674.html">查看详情</a>
                    </div>
                </figcaption>
            </figure>

        </div>
        <div class="hr_20"></div>
        <div class="re-x-wire"></div>
        <ul class="re-ultext-1">
        	<li><a href="http://m.18183.com/hqg/201511/473728.html"><span>11-27</span>花千骨手游靠谱助手电脑版下载安装教程</a></li>
<li><a href="http://m.18183.com/hqg/201511/473691.html"><span>11-27</span>花千骨手游跨服战视频 偶遇外挂玩家</a></li>
<li><a href="http://m.18183.com/hqg/201511/473674.html"><span>11-27</span>花千骨手游平民属性3星噩梦南弦月视频</a></li>
<li><a href="http://m.18183.com/hqg/201511/472959.html"><span>11-26</span>玩家原创：花千骨手游从古至今最全攻略</a></li>
<li><a href="http://m.18183.com/hqg/201511/469745.html"><span>11-23</span>花千骨手游家园系统免费互动玩法解析</a></li>

        </ul>
    </section>
  <!--mod-frame-2-->
    		<!--<section class="re-mod mod-frame-2">
    	<div class="re-tit2"><span class="sty-1"><a href="http://ka.18183.com/list_game_1922.shtml">更多&gt;&gt;</a><h4>最新礼包</h4></span></div>
        <ul>        	<li><a href="http://ka.18183.com/card_38463.shtml" title="花千骨18183烟雨凝霜礼包"><span class="st1">领取</span>花千骨18183烟雨凝霜礼包</a></li>        	<li><a href="http://ka.18183.com/card_38462.shtml" title="花千骨iOS正版烟雨凝霜礼包"><span class="st1">领取</span>花千骨iOS正版烟雨凝霜礼包</a></li>        	<li><a href="http://ka.18183.com/card_38444.shtml" title="花千骨18183活动限量礼包2"><span class="st1">领取</span>花千骨18183活动限量礼包2</a></li>        	<li><a href="http://ka.18183.com/card_38443.shtml" title="花千骨18183活动限量礼包2"><span class="st1">领取</span>花千骨18183活动限量礼包2</a></li>        	<li><a href="http://ka.18183.com/card_38442.shtml" title="花千骨iOS香水百合限量礼包2"><span class="st1">领取</span>花千骨iOS香水百合限量礼包2</a></li></ul>
    </section>
      <!--mod-frame-13-->
    <!--<section class="re-mod mod-frame-13">
    	<div class="re-tit2"><span class="sty-11"><h4>游戏资料</h4></span></div>
        <div class="hr_15"></div>
        <div class="re-tab" id="alltab5">
        	<ul class="tab-sel">
            	<li class="cur"><span>新手教程</span></li>
                <li><span>进阶攻略</span></li>
                <li><span>关卡技巧</span></li>
            </ul>
            <div style="display: block;" class="tab-main">
            	<ul class="re-ultext-2 clear">
                    <li><a href="http://www.18183.com/hqg/201505/332208.html"><span>属性科普</span></a></li>
                    <li class="cur"><a href="http://www.18183.com/hqg/201507/362662.html"><span>体力获取</span></a></li>
                    <li class="re-x-wire"></li>
                    <li><a href="http://www.18183.com/hqg/201506/348499.html"><span>金币获取</span></a></li>
                    <li class="cur"><a href="http://www.18183.com/hqg/201506/348969.html"><span>勾玉获取</span></a></li>
                    <li class="re-x-wire"></li>
                    <li><a href="http://www.18183.com/hqg/201506/347636.html"><span>安装问题</span></a></li>
                    <li class="cur"><a href="http://www.18183.com/hqg/201507/354754.html"><span>精铁玩法</span></a></li>
                    <li class="re-x-wire"></li>
                    <li><a href="http://www.18183.com/hqg/201506/348175.html"><span>充值问题</span></a></li>
                    <li class="cur"><a href="http://www.18183.com/hqg/201507/360944.html"><span>礼包领取</span></a></li>
                </ul>
            </div>
            <div style="display: none;" class="tab-main">
            	<ul class="re-ultext-2 clear">
                    <li><a href="http://www.18183.com/hqg/201507/362864.html"><span>灵宠大全</span></a></li>
                    <li class="cur"><a href="http://www.18183.com/hqg/201507/354537.html"><span>灵宠排行</span></a></li>
                    <li class="re-x-wire"></li>
                    <li><a href="http://www.18183.com/hqg/201506/339792.html"><span>高玩心得</span></a></li>
                    <li class="cur"><a href="http://www.18183.com/hqg/201506/335257.html"><span>职业选择</span></a></li><a href="http://www.18183.com/hqg/201506/335257.html">
                    <li class="re-x-wire"></li>
                    </a><li><a href="http://www.18183.com/hqg/201506/335257.html"></a><a href="http://www.18183.com/hqg/201507/360641.html"><span>璀星石</span></a></li>
                    <li class="cur"><a href="http://www.18183.com/hqg/201507/358939.html"><span>千层塔</span></a></li>
                    <li class="re-x-wire"></li>
                    <li><a href="http://www.18183.com/hqg/201506/344434.html"><span>装备搭配</span></a></li>
                    <li class="cur"><a href="http://www.18183.com/hqg/201507/351514.html"><span>红色套装</span></a></li>
                </ul>
            </div>
            <div style="display: none;" class="tab-main">
            	<ul class="re-ultext-2 clear">
                    <li><a href="http://www.18183.com/hqg/201506/343581.html"><span>金币关卡</span></a></li>
                    <li class="cur"><a href="http://www.18183.com/hqg/201507/357216.html"><span>器灵关卡</span></a></li>
                    <li class="re-x-wire"></li>
                    <li><a href="http://www.18183.com/hqg/201507/360759.html"><span>长留校场</span></a></li>
                    <li class="cur"><a href="http://www.18183.com/hqg/201507/351954.html"><span>长留校场2</span></a></li>
                    <li class="re-x-wire"></li>
                    <li><a href="http://www.18183.com/hqg/201507/358790.html"><span>太白大殿</span></a></li>
                    <li class="cur"><a href="http://www.18183.com/hqg/201507/355559.html"><span>花莲幻境</span></a></li>
                    <li class="re-x-wire"></li>
                    <li><a href="http://www.18183.com/hqg/201507/357090.html"><span>勾魂锁灵</span></a></li>
                    <li class="cur"><a href="http://www.18183.com/hqg/201507/356908.html"><span>长留武斗场</span></a></li>
                </ul>
            </div>
        </div>
    </section>
    <!--mod-frame-4-->
    <!---<section class="re-mod mod-frame-4">
    	<div class="re-tit2"><span class="sty-3"><h4>职业介绍</h4></span></div>
<div class="hr_15"></div>
        <div class="re-tab" id="alltab2">
        	<ul class="tab-sel">
            	<li class="cur"><span>花千骨</span></li>
                <li><span>蓝碎月</span></li>
                <li><span>斗吟锋</span></li>
            </ul>
            <div style="display: block;" class="tab-main">
            	<div class="hr_20"></div>
            	<div class="re-imgtext-3">
                    <figure>
                        <div class="pic"><a href="http://www.18183.com/hqg/201506/333901.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-150H0150923-50.jpg"></a></div>
                        <figcaption>
                            <h4><a href="http://www.18183.com/hqg/201506/333901.html">花千骨</a></h4>
                            <div class="item">背景：<i>上古遗神</i></div>
                            <div class="item">武器：<i>断念剑</i></div>
                            <div class="item">难度：<i>★★★☆☆</i></div>
                            <div class="item">定位：<i>半远程</i></div>
                        </figcaption>
                    </figure>
                </div>
                <div class="re-text1">
                	职业简介：<span>操作简单，攻击不俗，擅长对付中近距离的敌人。</span><a href="http://www.18183.com/hqg/201506/333901.html">查看详情</a>
                </div>
            </div>
            <div style="display: none;" class="tab-main">
            	<div class="hr_20"></div>
            	<div class="re-imgtext-3">
                    <figure>
                        <div class="pic"><a href="http://www.18183.com/hqg/201505/330708.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-150H0150923-51.jpg"></a></div>
                        <figcaption>
                            <h4><a href="http://www.18183.com/hqg/201505/330708.html">蓝碎月</a></h4>
                            <div class="item">背景：<i>上古月尊</i></div>
                            <div class="item">武器：<i>苍月轮</i></div>
                            <div class="item">难度：<i>★★★★☆</i></div>
                            <div class="item">定位：<i>远程射手</i></div>
                        </figcaption>
                    </figure>
                </div>
                <div class="re-text1">
                	职业简介：<span>一弯银轮为武器，优秀的控场能力和持续的输出，成为美与杀的完美代言人。</span><a href="http://www.18183.com/hqg/201505/330708.html">查看详情</a>
                </div>
            </div>
            <div style="display: none;" class="tab-main">
            	<div class="hr_20"></div>
            	<div class="re-imgtext-3">
                    <figure>
                        <div class="pic"><a href="http://www.18183.com/hqg/201505/330700.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-150H0150924.jpg"></a></div>
                        <figcaption>
                            <h4><a href="http://www.18183.com/hqg/201505/330700.html">斗吟锋</a></h4>
                            <div class="item">背景：<i>上古剑尊</i></div>
                            <div class="item">武器：<i>无锋重剑</i></div>
                            <div class="item">难度：<i>★★★★★</i></div>
                            <div class="item">定位：<i>近战</i></div>
                        </figcaption>
                    </figure>
                </div>
                <div class="re-text1">
                	职业简介：<span>擅长对付近距离的敌人，同时对敌人造成成吨的伤害。</span><a href="http://www.18183.com/hqg/201505/330700.html">查看详情</a>
                </div>
            </div>
     </div></section>
      <!--mod-frame-9-->
    <!--<section class="re-mod mod-frame-9">
    	<div class="re-tit2"><span class="sty-7"><a href="http://www.18183.com/hqg/gonglue/">更多&gt;&gt;</a><h4>游戏攻略</h4></span></div>
        <ul class="re-ultext-1">
        <li><a href="http://m.18183.com/hqg/201511/473728.html">花千骨手游靠谱助手电脑版下载安装教程</a></li>
<li><a href="http://m.18183.com/hqg/201511/473691.html">花千骨手游跨服战视频 偶遇外挂玩家</a></li>
<li><a href="http://m.18183.com/hqg/201511/473674.html">花千骨手游平民属性3星噩梦南弦月视频</a></li>
<li><a href="http://m.18183.com/hqg/201511/472959.html">玩家原创：花千骨手游从古至今最全攻略</a></li>
<li><a href="http://m.18183.com/hqg/201511/469745.html">花千骨手游家园系统免费互动玩法解析</a></li>
<li><a href="http://m.18183.com/hqg/201511/469710.html">花千骨手游灵宠培养秘籍 省时又省资源</a></li>
<li><a href="http://m.18183.com/hqg/201511/469056.html">花千骨手游跨服新版本千层塔攻略</a></li>
<li><a href="http://m.18183.com/hqg/201511/468510.html">花千骨手游新版本装备洗炼选择推荐</a></li>

        </ul>
    </section>
    <!--mod-frame-6-->
    <!--<section class="re-mod mod-frame-6">
    	<div class="re-tit2"><span class="sty-5"><h4>灵宠大全</h4></span></div>
        <div class="hr_15"></div>
        <div class="re-tab" id="alltab3">
        	<ul class="tab-sel">
            	<li class="cur"><span>橙色</span></li>
                <li><span>紫色</span></li>
                <li><span>蓝色</span></li>
            </ul>
            <div style="display: block;" class="tab-main">
            	<ul class="re-imgtext-5 clear">
             <li><a href="http://m.18183.com/hqg/201511/457707.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-15111QP342933.jpg" alt="">阡陌老师</a></li>
<li><a href="http://m.18183.com/hqg/201509/417657.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-15092QU450963.jpg" alt="">檀梵</a></li>
<li><a href="http://m.18183.com/hqg/201509/417548.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-15092QH3540-L.jpg" alt="">无垢</a></li>
<li><a href="http://m.18183.com/hqg/201509/417453.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-15092Q629360-L.jpg" alt="">东华</a></li>
<li><a href="http://m.18183.com/hqg/201509/395725.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-150Z2164J5591.jpg" alt="">白子画</a></li>
<li><a href="http://m.18183.com/hqg/201508/374819.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-150P51159290-L.jpg" alt="">灵虫糖宝</a></li>
<li><a href="http://m.18183.com/hqg/201507/370909.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-150I01433440-L.jpg" alt="">杀阡陌</a></li>
<li><a href="http://m.18183.com/hqg/201507/354022.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-150F5200A00-L.jpg" alt="">轻水</a></li>
<li><a href="http://m.18183.com/hqg/201507/352936.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-150F21J2110-L.jpg" alt="">孟玄朗</a></li>
<li><a href="http://m.18183.com/hqg/201506/351212.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-150630144A30-L.jpg" alt="">东方彧卿</a></li>
<li><a href="http://m.18183.com/hqg/201506/339250.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-150609150T30-L.jpg" alt="">墨冰</a></li>
<li><a href="http://m.18183.com/hqg/201506/339217.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-1506091441050-L.jpg" alt="">夏紫熏</a></li>

                </ul>
            </div>
            <div style="display: none;" class="tab-main">
            	  	<ul class="re-imgtext-5 clear">
<li><a href="http://m.18183.com/hqg/201507/353348.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-150F3140F80-L.jpg" alt="">落十一</a></li>
<li><a href="http://m.18183.com/hqg/201507/353297.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-150F31352000-L.jpg" alt="">云翳</a></li>
<li><a href="http://m.18183.com/hqg/201507/352465.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-150F2105ZUb.jpg" alt="">单春秋</a></li>
<li><a href="http://m.18183.com/hqg/201506/351022.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-1506301141210-L.jpg" alt="">尹上漂</a></li>
<li><a href="http://m.18183.com/hqg/201506/343344.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-1506161444200-L.jpg" alt="">霓漫天</a></li>
<li><a href="http://m.18183.com/hqg/201506/339897.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-1506101352270-L.jpg" alt="">清扬道长</a></li>

                </ul>
            </div>
            <div style="display: none;" class="tab-main">
            	  	<ul class="re-imgtext-5 clear">
<li><a href="http://m.18183.com/hqg/201507/356088.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-150FQ431480-L.jpg" alt="">朔风</a></li>
<li><a href="http://m.18183.com/hqg/201506/343955.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-15061G039570-L.jpg" alt="">机关守卫</a></li>
<li><a href="http://m.18183.com/hqg/201506/343897.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-15061G03612606.jpg" alt="">牛头判官</a></li>
<li><a href="http://m.18183.com/hqg/201506/339974.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-1506101422460-L.jpg" alt="">桃花精</a></li>
<li><a href="http://m.18183.com/hqg/201506/339347.html"><img src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/102-1506091536400-L.jpg" alt="">重明</a></li>

                </ul>
            </div>
        </div>
        </section>
        <!--mod-frame-14-->
    <!--<section class="re-mod mod-frame-14">
    	<div class="re-tit2"><span class="sty-12"><h4>装备百科</h4></span></div>
        <ul class="re-ultext-2 clear">
            <li><a href="http://www.18183.com/hqg/201508/379027.html"><span>红色套装<i class="i1"></i></span></a></li>
            <li class="cur"><a href="http://www.18183.com/hqg/201507/368204.html"><span>进阶费用<i class="i2"></i></span></a></li>
            <li class="re-x-wire"></li>
            <li><a href="http://www.18183.com/hqg/201507/365640.html"><span>花千骨搭配</span></a></li>
            <li class="cur"><a href="http://www.18183.com/hqg/201507/365145.html"><span>高暴套装</span></a></li>
            <li class="re-x-wire"></li>
            <li><a href="http://www.18183.com/hqg/201507/364340.html"><span>平民搭配</span></a></li>
            <li class="cur"><a href="http://www.18183.com/hqg/201507/363807.html"><span>贪婪手镯</span></a></li>
            <li class="re-x-wire"></li>
            <li><a href="http://www.18183.com/hqg/201507/363089.html"><span>人皇祭坛</span></a></li>
            <li class="cur"><a href="http://www.18183.com/hqg/201507/356401.html"><span>装备搭配</span></a></li>
            <li class="re-x-wire"></li>
            <li><a href="http://www.18183.com/hqg/201507/351514.html"><span>红色装备</span></a></li>
            <li class="cur"><a href="http://www.18183.com/hqg/201506/344434.html"><span>装备强化</span></a></li>
            <li class="re-x-wire"></li>
            <li><a href="http://www.18183.com/hqg/201506/342374.html"><span>异朽套装</span></a></li>
            <li class="cur"><a href="http://www.18183.com/hqg/201506/342344.html"><span>妖神套装</span></a></li>
            <li class="re-x-wire"></li>
            <li><a href="http://www.18183.com/hqg/201506/342314.html"><span>人皇套装</span></a></li>
            <li class="cur"><a href="http://www.18183.com/hqg/201506/337691.html"><span>最强装备</span></a></li>
            <li class="re-x-wire"></li>
            <li><a href="http://www.18183.com/hqg/201506/336448.html"><span>套装搭配</span></a></li>
            <li class="cur"><a href="http://www.18183.com/hqg/201505/330672.html"><span>器灵攻略</span></a></li>
        </ul>
        </section>
        <!--mod-frame-9-->
    <!--<section class="re-mod mod-frame-9">
    	<div class="re-tit2"><span class="sty-7"><a href="http://www.18183.com/hqg/shipin/">更多&gt;&gt;</a><h4>游戏视频</h4></span></div>
        <ul class="re-ultext-1">
        <li><a href="http://m.18183.com/hqg/201510/433793.html">花千骨手游千层塔一天52层视频攻略</a></li>
<li><a href="http://m.18183.com/hqg/201510/424383.html">花千骨手游千层塔解说：风筝流吊打对手</a></li>
<li><a href="http://m.18183.com/hqg/201510/423587.html">花千骨手游妖神boss17亿伤害输出视频</a></li>
<li><a href="http://m.18183.com/hqg/201510/422873.html">剑尊职业师徒副本16秒通关演示视频</a></li>
<li><a href="http://m.18183.com/hqg/201510/422862.html">月尊职业师徒副本16秒通关演示视频</a></li>
<li><a href="http://m.18183.com/hqg/201510/422808.html">花千骨职业师徒副本16秒通关视频</a></li>
<li><a href="http://m.18183.com/hqg/201510/420919.html">花千骨手游灵宠东华超详细视频解说</a></li>
<li><a href="http://m.18183.com/hqg/201509/419189.html">18183原创视频：解读花千骨1.40新版</a></li>

        </ul>
    </section>
    <!--mod-frame-9-->
    <!--<section class="re-mod mod-frame-9">
    	<div class="re-tit2"><span class="sty-7"><a href="http://www.18183.com/hqg/dianshiju/">全集&gt;&gt;</a><h4>电视剧</h4></span></div>
        <ul class="re-ultext-1">
        <li><a href="http://m.18183.com/hqg/201509/398540.html">花千骨电视剧58集：仙侠版的大结局</a></li>
<li><a href="http://m.18183.com/hqg/201509/398537.html">花千骨电视剧57集：咬脖子陪睡被删</a></li>
<li><a href="http://m.18183.com/hqg/201509/397442.html">花千骨电视剧56集：白子画当小骨性奴</a></li>
<li><a href="http://m.18183.com/hqg/201509/397441.html">花千骨电视剧55集：妖神小骨正式降临</a></li>
<li><a href="http://m.18183.com/hqg/201509/393675.html">花千骨电视剧54集：妖神变身前的铺垫</a></li>
<li><a href="http://m.18183.com/hqg/201509/393674.html">花千骨电视剧53集：东方彧卿打盒饭</a></li>
<li><a href="http://m.18183.com/hqg/201508/392472.html">花千骨电视剧52集：各派齐聚长留山</a></li>
<li><a href="http://m.18183.com/hqg/201508/392471.html">花千骨电视剧51集：白子画被小骨打了</a></li>

        </ul>
    </section>
</article>
<script type="text/javascript" src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/ranking.htm"></script><script>var top18183type="hot";var top18183rank="12";var top18183score="14459";</script>
<script src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/lazyload_wap.js"></script>
<script src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/index.js"></script>
<script type="text/javascript">
if(top18183type=="hot"){
	document.getElementById("re-medal-box").innerHTML="<a href='http://top.18183.com'><div class='re-medal medal-ry'><i>"+top18183rank+"</i>名</div></a>";
        if(parseInt(top18183rank)>100)document.getElementById("re-medal-box").innerHTML="<a href='http://top.18183.com'><div class='re-medal medal-ph'></div></a>";
}else if(top18183type=="new"){
	document.getElementById("re-medal-box").innerHTML="<a href='http://top.18183.com'><div class='re-medal medal-qd'><i>"+top18183rank+"</i>名</div></a>";
        if(parseInt(top18183rank)>50)document.getElementById("re-medal-box").innerHTML="<a href='http://top.18183.com'><div class='re-medal medal-ph'></div></a>";
}else{
	document.getElementById("re-medal-box").innerHTML="<a href='http://top.18183.com'><div class='re-medal medal-ph'></div></a>";
}
/* ==== start script ==== */
if(document.getElementById("slithershow1")) slitherShow.init("slithershow1",10);
if(document.getElementById("slithershow2")) slitherShow.init("slithershow2",10);
if(document.getElementById("slithershow3")) slitherShow.init("slithershow3",10);
if(document.getElementById("slithershow4")) slitherShow.init("slithershow4",10);
if(document.getElementById("slithershow5")) slitherShow.init("slithershow5",10);
if(document.getElementById("slithershow6")) slitherShow.init("slithershow6",10);
if(document.getElementById("slithershow7")) slitherShow.init("slithershow7",10);
if(document.getElementById("slithershow8")) slitherShow.init("slithershow8",10);
if(document.getElementById("slithershow9")) slitherShow.init("slithershow9",10);
if(document.getElementById("slithershow10")) slitherShow.init("slithershow10",10);
if(document.getElementById("alltab1")) allTab.init("alltab1");
if(document.getElementById("alltab2")) allTab.init("alltab2");
if(document.getElementById("alltab3")) allTab.init("alltab3");
if(document.getElementById("alltab4")) allTab.init("alltab4");
if(document.getElementById("alltab5")) allTab.init("alltab5");
if(document.getElementById("alltab6")) allTab.init("alltab6");
if(document.getElementById("alltab7")) allTab.init("alltab7");
if(document.getElementById("re-handbook")) SHTab.init("re-handbook",160);
if(document.getElementById("re-handbook1")) SHTab.init("re-handbook1",204);
lazyLoad.init();
</script>
<div style="display:none">
<script src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/c_003.php" language="JavaScript"></script>
<script src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/c.php" language="JavaScript"></script>
</div>

<script language="javascript">
(function(){
	/*设置神马搜索关键字*/
	var smquery = $(".mod_download_sup .tit").size()>0?$(".mod_download_sup .tit").html():"";
	var handlesmHtml = function(hot,lang){
		var j_hide_empty=lang>0?"j_hide_empty":" ";
		var html1 = "<section class='col-box "+j_hide_empty+"'><header class='col-hd'><h2 class='title'>相关推荐</h2></header><ul class='mod-news-list'>";
		var html2 = "<section class='re-mod mod-frame-9 "+j_hide_empty+"'><div class='re-tit2'><span class='sty-13'><h4>相关推荐</h4></span></div><ul class='re-ultext-1'>";
		$.each(hot,function(key,val){
			html1 += "<li style='float:left; width:50%;min-width:0;line-height:52px;height:52px;padding:0 10px 0 0;'><a target=_blank href=http://m.sa.sm.cn/s?q="+ val.word +"&from=wh10028>"+ val.word +"</a></li>";
			html2 += "<li style='float:left; width:48%;min-width:0;line-height:52px;height:52px;padding:0 2% 0 0;'><a href='http://m.sa.sm.cn/s?q="+ val.word +"&from=wh10028'  target='_blank' >"+ val.word +"</a></li>";
		})
		html1 += "</ul></section>";
		html2 += "</ul></section>";
		if($('.news-detail').size()>0) return html1;
		if($('.mod-frame-9').size()>0) return html2;
	};
	var getsmJsonData = function(query,title,url){
		if(!title) title='';
		if(!url) url='';
		var url = "http://api.18183.com/cs/sm?query="+encodeURI(query)+"&title="+title+"&url="+url;
		$.ajax({
			url: url,
			type:"get",
			dataType:"jsonp",
			jsonp:"callback",
			success: function(data) {
				if(data.error=='succ'){
					var hits = data.hits;
					var lang = data.hits.lenght
					var html = handlesmHtml(hits,lang);
					if($('.news-detail').size()>0)$('.news-detail').after(html);
                    if($('.mod-frame-9').size()>0)$('.mod-frame-9').after(html);
				}
			}
		});
	};
	var refStr = document.referrer;
	var refarr= refStr.match(/(\w+):\/\/([^\:|\/]+)(\:\d*)?(.*\/)([^#|\?|\n]+)?(#.*)?(\?.*)?/i);
	//getsmJsonData(smquery);
	if(refarr && refarr[2].indexOf('sm.cn')>=0){
		//获取api数据
		if(!smquery && refStr.indexOf("?")!=-1){
			var qpos= refStr.indexOf("q=")+2;
			refarg  = refStr.substr(qpos);
			var qpos= refarg.indexOf("&");
			if(qpos!=-1){
				refarg  = refarg.substr(0,qpos);
			}
			smquery = refarg;
		}
		getsmJsonData(smquery);
	}
})();
newhanawa.createAdBanner("bottomwap",2);
newhanawa.createAdBanner("xfwap",2);
newhanawa.createAdBanner("xtwap",2);
</script>
<footer class="footer" id="j_foot_nav">
    <div class="foot_u">
        <ul class="n_c">
            <li><a href="http://www.18183.com/">18183</a></li>
            <li><a href="http://bbs.18183.com/">论坛</a></li>
            <li><a href="http://ka.18183.com/">礼包</a></li>
            <li><a href="http://hd.18183.com/">活动</a></li>
            <li><a href="http://m.18183.com/zqgamelist/index.html">专区</a></li>
        </ul>
    </div>
    <menu class="d-menu" style="display:none;"></menu>
    <div class="ft-copy">
        <p><a href="http://m.18183.com/">触屏版</a>|<a href="http://m.18183.com/box/">客户端</a></p>
        <div class="btn-top" style="display:none;"><a id="j_back_top" data-pvtag="foot.back" data-nogo="1"></a></div>
    </div>
</footer>
<div style="display:none">
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_30070880'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/c.php%3Fid%3D30070880' type='text/javascript'%3E%3C/script%3E"));</script><span id="cnzz_stat_icon_30070880"></span><script src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/c_002.php" type="text/javascript"></script>
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F3782151c2b39bc6837a4913c89752cbc' type='text/javascript'%3E%3C/script%3E"));
</script><script src="%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8_%E8%8A%B1%E5%8D%83%E9%AA%A8%E6%89%8B%E6%B8%B8%E5%AE%98%E7%BD%91_%E6%94%BB%E7%95%A5_%E4%B8%8B%E8%BD%BD_%E7%A4%BC%E5%8C%85_18183%E8%8A%B1%E5%8D%83%E9%AA%A8%E4%B8%93%E5%8C%BA_files/h.js" type="text/javascript"></script>
</div>-->
<script type="text/javascript">
	function AutoResizeImage(maxWidth, maxHeight, objImg) {
        var img = new Image();
        img.src = objImg.src;
        var hRatio;
        var wRatio;
        var Ratio = 1;
        var w = img.width;
        var h = img.height;
        wRatio = maxWidth / w;
        hRatio = maxHeight / h;
        if (maxWidth == 0 && maxHeight == 0) {
            Ratio = 1;
        } else if (maxWidth == 0) { //
            if (hRatio < 1) Ratio = hRatio;
        } else if (maxHeight == 0) {
            if (wRatio < 1) Ratio = wRatio;
        } else if (wRatio < 1 || hRatio < 1) {
            Ratio = (wRatio <= hRatio ? wRatio : hRatio);
        }
        if (Ratio < 1) {
            w = w * Ratio;
            h = h * Ratio;
        }
        objImg.height = h;
        objImg.width = w;
    }
</script>
<script charset="gb2312" type="text/javascript">
nie.config.copyRight.setWhite();
//分享
var shareTxt = " 神魔战歌";
var shareImg = "";
nie.use(["nie.util.shareV3"],function(){
	nie.util.share({
		fat : ".ent-top-share",
		type : 2,
		defShow : [22, 2, 1, 3],
		img : shareImg,
		title : shareTxt
	});
});

//卖点切换
$(".md-ul li").mouseover(function(){
	$(".md-ul li.on").removeClass("on");
	$(this).addClass("on");
}).eq(0).mouseover();


//弹层
function openPop(popID){
	closePop();
	$("#overlay,"+popID).show();
}
function closePop(){
	$(".videoPop .pop-content").empty();
	$("#overlay, .pop").hide();
}
$("#overlay, .pop-close").click(closePop);

//视频弹层
var movieUrl = 'http://v.crazynote.netease.com/2015/0805/4cbcca0d33486262946324a9448e42e7qt.f4v';
var mp4_movieUrl = 'http://v.crazynote.netease.com/2015/0805/4cbcca0d33486262946324a9448e42e7qt.mp4';
function openVideo(){
	nie.use(["nie.util.video"],function(){
		var video=nie.util.video($(".videoPop .pop-content").empty(), {
			movieUrl:movieUrl,
			mp4_movieUrl:mp4_movieUrl,
			width:800,
			height:450,
			volume:0.8,
			wmode:"opaque",
			autoPlay:true
		});
	});
	openPop("#pop-video");
}

 function mutilChannel(){
    //多渠道处理
    var url = window.location.href,
        $iosBtn = $(".download-ios"),
        $androidBtn = $(".download-android"),
        $codeImg = $(".download-qrcode"),
        ua = window.navigator.userAgent,
        needJump = !!ua.match(/mobile/i) && !url.match(/\/m\//);

    if(/fab/.test(url)){
        //fab
        $iosBtn.attr("href","javascript:alert('安卓没的下！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","");

        if (needJump) {
            window.location = 'http://jianglin.163.com/m/fab/';
        }

    }else if(/shenma/.test(url)){
        //神马
        $iosBtn.attr("href","javascript:alert('安卓没的下！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","./qrcode_9536f25.png");

        if (needJump) {
            window.location = 'http://jianglin.163.com/m/shenma/';
        }

    }else if(/baidu/.test(url)){
        //百度sem
        $iosBtn.attr("href","javascript:alert('安卓没的下！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","./qrcode_9536f25.png");

        if (needJump) {
            window.location = 'http://jianglin.163.com/m/baidu/';
        }

    }else if(/pinpai/.test(url)){
    	//百度品牌
        $iosBtn.attr("href","javascript:alert('安卓没的下！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","./qrcode_9536f25.png");

        if (needJump) {
            window.location = 'http://jianglin.163.com/m/pinpai/';
        }

    }else if(/weix/.test(url)){
    	//微信
        $iosBtn.attr("href","javascript:alert('安卓没的下！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","./qrcode_9536f25.png");

        if (needJump) {
            window.location = 'http://jianglin.163.com/m/weix/';
        }

    }else if(/tieb/.test(url)){
    	//贴吧
        $iosBtn.attr("href","javascript:alert('安卓没的下！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","./qrcode_9536f25.png");

        if (needJump) {
            window.location = 'http://jianglin.163.com/m/tieb/';
        }

    }else if(/weib/.test(url)){
    	//微博
        $iosBtn.attr("href","javascript:alert('安卓没的下！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","./qrcode_9536f25.png");

        if (needJump) {
            window.location = 'http://jianglin.163.com/m/weib/';
        }

    }else{
        //官网
        $iosBtn.attr("href","javascript:alert('安卓没的下！');");
        $androidBtn.attr("href","javascript:alert('安卓没的下！');");
        $codeImg.attr("src","");
    }
}
mutilChannel();

</script>

</body></html>