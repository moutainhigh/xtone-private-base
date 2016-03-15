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
                        <a href="index.php?controller=service&action=faq2" class="current"><b class="l"></b><b>虚拟主机问题</b><b class="r"></b></a>
                        <a href="index.php?controller=service&action=faq3"><b class="l"></b><b>邮局问题</b><b class="r"></b></a>
                        <a href="index.php?controller=service&action=faq4"><b class="l"></b><b>网站建设问题</b><b class="r"></b></a>
                    </div>
                <ul><span></span>上传的文件超过我的磁盘限额会出现什么情况？</ul>
        <li><div>您将得到系统提示，无法再上传任何东西（文件上传后的字节数为零）。</div></li>
                 <ul><span></span>为什么无法上传，提示密码不对？</ul>
        <li><div>请查看您的登陆名密码填写是否正确，因为如果密码是复制的话，可能会复制出空格。另外，我们给您的初始密码都是一个英文一个数字8位数排列的，也许是字母l被认为是数字1。最后，您要看一下您在ftp登陆时选择的登陆类型是否是普通。</div></li>
                 <ul><span></span>为什么无法上传，提示连接时找不到主机？</ul>
        <li><div>首先请您检查一下您的域名是否做过域名解析，检测方法：您可以在DOS提示符下输入ping域名如果可以ping通的话，则您可以在FTP软件中“FTP主机地址处”填写您的域名，如果ping不通的话，则您需要在“FTP主机地址处”填写您主机的IP地址。<br /> 
注意：我们建议您使用IP地址上传页面，同时，某些地区的拨号上网的169对ftp有限制。所以请您最好更换上网方式后在进行测试。</div></li>
                 <ul><span></span>FTP时已经通过身份验证，但总列不出目录？</ul>
        <li><div>问题出现原因：您使用的上传软件的FTP客户端程序不应该选用PASV mode和firewall setting<br />  
处理方法：建议使用Cuteftp4.2软件，在Edit->Setting…->Connection->Firewall去掉”PASV mode”这个选项即可。</div></li>
                 <ul><span></span>无法上传网页，FTP故障－提示“无法连接服务器”错误？</ul>
        <li><div>问题出现原因：FTP客户端程序设置问题，客户上网线路问题，ftp服务器端问题。<br />   
处理方法：建议客户使用CUTPFTP软件来上传客户的网页，在“FTP主机地址处”最好填写IP地址，如果客户上传时提示socket错误的话，请您检查一下您使用软件的编辑菜单下的连接中防火墙里是否有一个使用了pasv模式，如果选中的话，您把此选项取消即可连接主机。</div></li>
               
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
