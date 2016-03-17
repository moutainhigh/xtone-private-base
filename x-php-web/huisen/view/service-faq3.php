<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<meta name="keywords" content="荟森数码,手机网站,微信营销" />
<meta name="description" content="荟森数码" />
<meta name="author" content="荟森数码" />
<title>荟森数码--FAQ</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.easing.js" type="text/javascript"></script>
<script src="js/jquery.navpart.js" type="text/javascript"></script>
<script src="js/jquery.faq.js" type="text/javascript"></script>
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
        <li class="hover current-cat"><a href="index.php?controller=service&action=faq">FAQ</a></li>
        <li><a href="index.php?controller=service&action=feedback" class="last">提交反馈</a></li>
    </ul>
</div>

<div id="content">
	<div id="faq_list">
        <div class="title">常见问题(FAQ)</div>
        <div class="types">
        	            <a href="index.php?controller=service&action=faq"><b class="l"></b><b>域名问题</b><b class="r"></b></a>
                        <a href="index.php?controller=service&action=faq2"><b class="l"></b><b>虚拟主机问题</b><b class="r"></b></a>
                        <a href="index.php?controller=service&action=faq3" class="current"><b class="l"></b><b>邮局问题</b><b class="r"></b></a>
                        <a href="index.php?controller=service&action=faq4"><b class="l"></b><b>网站建设问题</b><b class="r"></b></a>
                    </div>
                <ul><span></span>企业邮局信箱收到大量垃圾邮件，怎么办？</ul>
        <li><div>防止垃圾邮件的办法如下： <br />
A.不要随便公开自己的电子邮件地址 <br />
B.充分利用Web邮箱的垃圾邮件过滤功能<br />
     a)在反垃圾级别设置中将反垃圾级别设置为“中级” <br />
     b)在收件箱中举报垃圾邮件 <br />
     c)在信箱“反垃圾过滤器”设置中拒收发送垃圾邮件的发件人。 <br />
     d)在信箱“反垃圾过滤器”设置中过滤器，过滤垃圾邮件<br />
C.充分利用OE/OUTLOOK/FOXMAIL等客户端软件的的垃圾邮件过滤功能<br /> 
D.将垃圾邮件作为附件转发给域管理员，由域管理员进行域级垃圾邮件过滤。<br /> 
E.将垃圾邮件转发（做为附件转发）给我们，我们将根据情况在系统中屏蔽垃圾邮件的来源。</div></li>
                 <ul><span></span>用WEBMAIL发邮件时，是否可以添加签名信息？</ul>
        <li><div>可以，在服务设置区的“设置”－》“签名档”中可以添加自己的签名信息。在发送邮件时可以选择添加某一个签名信息。
一共可以设置10个签名信息。</div></li>
                 <ul><span></span>如何实现自动回复功能？</ul>
        <li><div>263企业邮局用户在进入WEBMAIL界面后，点击＜配置＞，选择＜自动回复＞，在文本框中输入想要自动回复的内容， 在下面的＜回复生效＞后打勾就可以了。</div></li>
                 <ul><span></span>企业邮局添加地址薄时对数量是否有限制？</ul>
        <li><div>1000条</div></li>
                 <ul><span></span>企业邮局管理员密码丢失怎么办？</ul>
        <li><div>请用户给我们发传真确认身份，传真内容包括：域名、公司名称、用户需求、用户姓名、联系方式、加盖公章，传真至020-89206892，由客服接到用户传真后进行相关操作后回复用户。</div></li>
                 <ul><span></span>263企业邮局系统中，超级域管理员与域管理员的区别？</ul>
        <li><div>所谓超级域管理员，就是在263企业邮局中，由系统管理员创建域的时候，所创建的对这个域实施管理的管理员账户。所谓域管理员，是在263企业邮局中，由超级域管理员创建的域管理员，其管理权限由超级域管理员赋予。因此，263企业邮局系统的域管理员分为超级域管理员和域管理员两种，均可以进行管理和维护本企业邮局域的工作。</div></li>
                 <ul><span></span>域名是由其他服务商提供注册的，如何做MX记录的解析？</ul>
        <li><div>申请服务商将企业域名的MX记录指向mx01.263xmail.com。</div></li>
                 <ul><span></span>如何判断用户所用的邮箱是否是263企业邮局？</ul>
        <li><div>用nslookup查看用户的MX解析是否是指向mx01.263xmail.com</div></li>
                 <ul><span></span>我的企业邮局不够用了，想进行升级，如何操作？</ul>
        <li><div>以系统管理员身份登录后，在263企业邮局管理功能中选择“升降级”功能即可对您所购买的邮局产品方便的进行升级，操作后即时生效，降级操作与此类似。但请注意的是：邮局规格每月最多变更（升/降级）一次。</div></li>
                 <ul><span></span>263企业邮局包括哪些服务？</ul>
        <li><div>标准版产品服务：WEB形式的企业邮局域管理，WEB邮件收发，WEB信箱管理，客户端（SMTP/POP3）邮件收发，系统级反垃圾邮件，系统级查杀邮件病毒，超大邮件，WAPMAIL，安全邮件（邮件签名与邮件加密）。<br />
大用户版产品服务：WEB形式的企业邮局域管理，WEB邮件收发，WEB信箱管理，客户端（SMTP/POP3）邮件收发，系统级反垃圾邮件，系统级查杀邮件病毒。可付费提供WAPMAIL，安全邮件（邮件签名与邮件加密）服务。</div></li>
         		<div id="page">
			<a href="javascript:void(0);" title="已经是第一页了!" class="prveNO">&nbsp;</a><a href="�page=1.html" class="current">1</a><a href="javascript:void(0);" title="已经是最后一页了!" class="nextNO">&nbsp;</a>        </div>
    </div>    
</div>   
<script type="text/javascript">var faq=new accordion.faq("faq");faq.init("faq_list",-1,"open");</script>


<?php include('footer.php'); ?>
<script src="js/jquery.brand.js" type="text/javascript"></script>
<script src="js/menu.js" type="text/javascript"></script>
</body>
</html>
