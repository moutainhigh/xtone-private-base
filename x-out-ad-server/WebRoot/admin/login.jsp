<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Smart网络推送后台</title>
<link href="./source/css/common.css" rel="stylesheet" type="text/css" media="all" />
<link href="./source/css/main.css" rel="stylesheet" type="text/css" media="all" />
<link href="./source/css/logo.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="./source/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="./source/js/login.js"></script>
</head>
<body class="login_box">
<div>
<script type="text/javascript">
$(document).ready(function () {
   $('.clickchane').bind('click',function(){
         $('.logo_p').find('img').attr('src',"/smspay/imgcode?"+Math.random());
   });
   $('#username').focus();
   $('#username').bind('keyup', function(event){
     if (event.keyCode=="13"){
       $('#passwd').focus();
     }
   });
   $('#passwd').bind('keyup', function(event){
     if (event.keyCode=="13"){
       $('#code').focus();
     }
   });
    $('#code').bind('keyup', function(event){
     if (event.keyCode=="13"){
       login();
     }
   });
});
</script>
<div class="box">
    <span class="box_img4"></span>
<div class="logo_modify_box">
    <span class="box_img5"></span>
    <span class="box_img6"></span>
    <div class="box_img7">
	 <ul class="logo_ul" id = "logo_title">Smart网络推送后台</ul>
   	 <ul class="logo_ul" id="logo_ul">
	  
      <li><span class="input_logo logo_li01"><input id="username" class="modify_logo_input" type="text" value="" /></span></li>
      <li><span class="input_logo logo_li02"><input id="passwd" class="modify_logo_input" type="password" value="" /></span></li>
     </ul>
      
    </div>
    <div class="box_img8">
		<p class="logo_submit"><a href="####" onclick="login();" width="158" height="32">登录</a></p> 
	    <!--
    	<p class="logo_submit"><a href="####" onclick="login();"><img src="${deploy}/images/logo/logo_submit.jpg" width="158" height="32" alt="" /></a></p> -->
    </div>
</div>

    <!--<span class="box_img9"></span>
    <span class="box_img10"></span>-->
</div>
<script language="javascript" type="text/javascript">
	DOM={};
	DOM.hasClass=function(ele,strClass){
		var reg=new RegExp("\\b"+strClass+"\\b");
		if(reg.test(ele.className)){
			return true;
		}else{//不存在
			return false;
		}
	}
	var oul=document.getElementById("logo_ul");
	oul.onclick=function(e){
		e=e || window.event;
		var t=e.target || e.srcElement;
		var bot=t.parentNode.parentNode.parentNode;
		if(DOM.hasClass(bot,"logo_ul")){
			var lis=oul.getElementsByTagName("li");
			for(var i=0;i<lis.length;i++){
				lis[i].className="";
			}
			t.parentNode.parentNode.className="logo_ul_border";
		}
	}
</script>
</div>
</body>
</html>