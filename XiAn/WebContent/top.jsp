<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
/* .active1 {
	border-top: #2b2c30 5px solid;
} */

@font-face {
	font-family: pictos;
	src: url('font/fangzheng.ttf');
}

.a-x-axy {
	width: 100%;
}
.border{
	border-bottom: #fff 7px solid;
}

</style>

  <script>
  
  /* $('#turnToCompany').click(function () {
	  $('html, body').animate({
	  scrollTop: $($.attr(this, 'href')).offset().top
	  }, 500);
	  return false;
	  }); */
  
  $(function(){  
	  $('a[href*=#],area[href*=#]').click(function() {
	    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
	      var $target = $(this.hash);
	      $target = $target.length && $target || $('[name=' + this.hash.slice(1) + ']');
	      if ($target.length) {
	        var targetOffset = $target.offset().top;
	        $('html,body').animate({
	          scrollTop: targetOffset
	        },
	        1000);
	        return false;
	      }
	    }
	  });
	})
        </script>
</head>
<body>
<div class="container">
	<div class="navbar-header">
		<button class="navbar-toggle collapsed" type="button"
			data-toggle="collapse" data-target="#bs-navbar"
			aria-controls="bs-navbar" aria-expanded="false">
			<span class="sr-only"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a href="index.jsp"><img alt="logo" src="./images/bg-top-logo.png"
			class="logo-size"></a>
	</div>

	<nav id="bs-navbar" class="collapse navbar-collapse">
		<ul class="nav navbar-nav navbar-right ztgs my-nav" >
			<li id="index" class="active1 a-x-a1 a-x-ax "><a
				href="#" style="padding: 0px;"> <!-- <img id="1"
					class="bsimg-1" onmouseover="this.src='images/top/home2.png'"
					onmouseout="this.src='images/top/home.png'"
					src="images/top/home.png">  -->
					<font style="font-family:pictos;color: #7a7979"; class="nav-chinese ">首页</font><!-- <br><font style="font-size: 12px;font-family:pictos;" class="nav-english">HOME</font> --> </a></li>
			<li id="news" class="active1 a-x-a2 a-x-ax "><a id="turnToCompany" href="#Company"
				style="padding: 0px;"> <!-- <img id="2" class="bsimg-1"
					onmouseover="this.src='images/top/news2.png'"
					onmouseout="this.src='images/top/news.png'"
					src="images/top/news.png"> --> <font style="font-family:pictos;color: #7a7979" class="nav-chinese">公司业务</font><!--<br> <font style="font-size: 12px;font-family:pictos;" class="nav-english">NEWS</font> --></a></li>
			<li id="porducts" class="active1 a-x-a2 a-x-ax "><a
				href="#Appreciation" style="padding: 0px;">
				
				<font style="font-family:pictos;color: #7a7979" class="nav-chinese">作品展示</font><!-- <br><font style="font-size: 12px;font-family:pictos;" class="nav-english">PORDUCTS</font> -->
				<!-- 	<img id="3" class="bsimg-1"
					onmouseover="this.src='images/top/products2.png'"
					onmouseout="this.src='images/top/products.png'"
					src="images/top/products.png"> -->
			</a></li>
			<li id="abouts" class="active1 a-x-a2 a-x-ax "><a
				href="#About_us" style="padding: 0px;">
					<font style="font-family:pictos;color: #7a7979" class="nav-chinese">关于我们</font><!-- <br> <font style="font-size: 12px;font-family:pictos;" class="nav-english">VANGGAME</font> -->
					<!-- <img id="4" class="bsimg-1"
					onmouseover="this.src='images/top/about2.png'"
					onmouseout="this.src='images/top/about.png'"
					src="images/top/about.png"> -->
			</a></li>
			<li id="cooperation" class="active1 a-x-a2 a-x-ax "
				style="margin-top: -2.5px;"><a href="#Contact_us"
				style="padding: 0px;"><!-- <img id="5" class="bsimg-1"
					onmouseover="this.src='images/top/cooperation2.png'"
					onmouseout="this.src='images/top/cooperation.png'"
					src="images/top/cooperation.png"> --> <font style="font-family:pictos;color: #7a7979" class="nav-chinese">联系我们</font><!-- <br> <font style="font-size: 12px;font-family:pictos;" class="nav-english">COOPERATION</font> -->
			</a></li>
			
		</ul>
	</nav>

</div>
<!-- <script type="text/javascript">
	function active() {
		var width = document.documentElement.clientWidth;
		if (width < 768) {
			$("#index").addClass("a-x-axy");
			$("#news").addClass("a-x-axy");
			$("#porducts").addClass("a-x-axy");
			$("#abouts").addClass("a-x-axy");
			$("#cooperation").addClass("a-x-axy");
			$("#join").addClass("a-x-axy");
		}

		var url = this.location.href;
		var htmlName = url.split("/");
		var name = htmlName[htmlName.length - 1].split("?")[0];
		if (name == "index.jsp" || name == "") {
			console.log(index.jsp)
			img1();
			$("#index").removeClass("active1");
			$("#index").addClass("active");
		} else if (name == "news.jsp" || name == "news-content.jsp") {
			$("#news").addClass("active");
			$("#news").removeClass("active1");
			img2();
		} else if (name == "porducts-phone.jsp" || name == "porducts.jsp"
				|| name == "porducts-web.jsp" || name == "porducts-client.jsp") {
			$("#porducts").addClass("active");
			$("#porducts").removeClass("active1");
			img3();
		} else if (name == "about-us-company.jsp"
				|| name == "about-us-enterprise.jsp") {
			$("#abouts").addClass("active");
			$("#abouts").removeClass("active1");
			img4();
		} else if (name == "cooperation.jsp") {
			$("#cooperation").addClass("active");
			$("#cooperation").removeClass("active1");
			img5();
		} else if (name == "join-us-shzp.jsp" || name == "join-us-contact.jsp"
				|| name == "job-content.jsp") {
			$("#join").addClass("active");
			$("#join").removeClass("active1");
			img6();
		}
	}
	/* function img1() {
		document.getElementById("1").src = "images/top/home2.png";
		document.getElementById("1").onmouseover = "";
		document.getElementById("1").onmouseout = "";
	}
	function img2() {
		document.getElementById("2").src = "images/top/news2.png";
		document.getElementById("2").onmouseover = "";
		document.getElementById("2").onmouseout = "";
	}
	function img3() {
		document.getElementById("3").src = "images/top/products2.png";
		document.getElementById("3").onmouseover = "";
		document.getElementById("3").onmouseout = "";
	}
	function img4() {
		document.getElementById("4").src = "images/top/about2.png";
		document.getElementById("4").onmouseover = "";
		document.getElementById("4").onmouseout = "";
	}
	function img5() {
		document.getElementById("5").src = "images/top/cooperation2.png";
		document.getElementById("5").onmouseover = "";
		document.getElementById("5").onmouseout = "";
	}
	function img6() {
		document.getElementById("6").src = "images/top/join2.png";
		document.getElementById("6").onmouseover = "";
		document.getElementById("6").onmouseout = "";
	} */
	window.onload = active;
	active();
</script> -->
</body>
</html>