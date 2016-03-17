<%@ page language="java" pageEncoding="utf-8"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<title>Smart网络推送后台</title>
<link href="./source/css/common.css" rel="stylesheet" type="text/css" media="all" />
<link href="./source/css/main.css" rel="stylesheet" type="text/css" media="all" />
<link href="./source/css/base.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="./source/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="./source/js/Global.js"></script>
<script type="text/javascript" src="./source/js/common.js"></script>
<script type="text/javascript" src="./source/js/main.js"></script>
<script type="text/javascript" src="./source/js/jquery-json.js"></script>
<script type="text/javascript" src="./source/js/Calendar.js"></script>
<script type="text/javascript" src="./source/js/graphael/raphael-min.js"></script>
<script type="text/javascript" src="./source/js/graphael/g.raphael.js"></script>
<script type="text/javascript" src="./source/js/graphael/g.pie.js"></script>
<script type="text/javascript" src="./source/js/Calendar-two.js"></script>

<script type="text/javascript">
	$(document).ready(function () {
	    initMenu();
	});
</script>
</head>
<body>
<div class="total">
	<div class="tal">
    	<span class="tal_span"></span>
        <div class="tal_right"><span></span><strong><a href="#" onclick="logout()">退出账户</a></strong></div>
    </div>
</div>
<div class="contain">
	<div class="container" >
		<div class="left" id="left"></div>
		<div class="right" id="right">
        	<!--默认-->
            <div class="default">
            	<span class="motel">欢迎登陆，你上次的登陆时间为：<strong></strong></span>
                <span class="welcome"></span>
            </div>
		</div>
		<div class="clear"></div>
	</div>
</div>
</body>
</html>