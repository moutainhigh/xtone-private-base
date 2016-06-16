<%@page import="org.common.util.ConfigManager"%>
<%
    String name = ConfigManager.getConfigData("servicename");
%>
<!DOCTYPE HTML>
<html>
<head>
<title><%=name%>中控</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
 <!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- jQuery -->
<script src="js/jquery.min.js"></script>
<!----webfonts--->
<link href='http://fonts.useso.com/css?family=Roboto:400,100,300,500,700,900' rel='stylesheet' type='text/css'>
<!---//webfonts--->  
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../js-css/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<link href="../js-css/bootstrap-datetimepicker.min.css" rel="stylesheet"
	media="screen">
<script type="text/javascript"
	src="../js-css/bootstrap-datetimepicker.js" charset="UTF-8"></script>

<!-- DataTables -->
<link rel="stylesheet" type="text/css"
	href="http://cdn.datatables.net/1.10.4/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="http://cdn.datatables.net/1.10.4/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js-css/DatePicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="../My97DatePicker/skin/default/datepicker.css">
<link rel="stylesheet" type="text/css" media="screen" href="../js-css/css-table.css" />
<script type="text/javascript" src="../js-css/style-table.js"></script>
</head>
<body>
<div id="wrapper">
     <!-- Navigation -->
        <nav class="top1 navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div class="vg_logo"></div>
                <div class="vg_title"><%=name%>平台</div>
            </div>
            <!-- /.navbar-header -->
            <ul class="nav navbar-nav navbar-right">
				
			    <li class="dropdown">
	        		<a href="#" class="dropdown-toggle avatar" data-toggle="dropdown"><img src="images/002.png" alt=""/></a>
	        		<ul class="dropdown-menu">
						<li class="dropdown-menu-header text-center">
							<strong>Settings</strong>
						</li>
						<li class="m_2"><a href="user-all.jsp"><i class="fa fa-user"></i> User</a></li>
						<li class="m_2"><a href="password-update.jsp"><i class="fa fa-wrench"></i> Update_PWD</a></li>
						<li class="m_2"><a href="login.jsp"><i class="fa fa-lock"></i> Logout</a></li>
	        		</ul>
	      		</li>
			</ul>
			<!-- <form class="navbar-form navbar-right">
              <input type="text" class="form-control" value="Search..." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search...';}">
            </form> -->
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="stat-all.jsp"><i class="fa fa-table nav_icon"></i>实时数据</a>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="daily.jsp"><i class="fa fa-dashboard fa-fw nav_icon"></i>日统计 </a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
        <div id="page-wrapper" style="min-height: 286px; width: 86%;">
        <div class="col-md-12 graphs">
	   <div class="xs">
  	 <h3>Date Table</h3>
  	<dl style="height: 41px; margin-top: 35px; margin-bottom: 28px;">
  			
			<dd class="dd01_me" style="width: 80px; float: left; margin-left: 10px; color: rgb(101, 124, 149); line-height: 25px; text-align: center; background: rgb(193, 216, 222) none repeat scroll 0% 0%;-moz-border-radius: 3.5px; -webkit-border-radius: 3.5px; border-radius:3.5px;">开始日期</dd>
			<dd class="dd03_me" style='width: 100px; -background: transparent url("../img/member_input.gif") no-repeat scroll right top; padding-right: 5px; margin-left: 10px; float: left;'>
				<input  class="tableinput"
						 id="starttime" type="text" onfocus="setday(this,'yyyy-MM-dd','2010-01-01','2010-12-30',1)" readonly="readonly"	/>
			</dd>
			
			<dd class="dd01_me" style="width: 80px; float: left; margin-left: 10px; color: rgb(101, 124, 149); line-height: 25px; text-align: center; background: rgb(193, 216, 222) none repeat scroll 0% 0%;-moz-border-radius: 3.5px; -webkit-border-radius: 3.5px; border-radius:3.5px;">结束日期</dd>
			<dd class="dd03_me" style='width: 100px; -background: transparent url("../img/member_input.gif") no-repeat scroll right top; padding-right: 5px; margin-left: 10px; float: left;'>
				<input  class="tableinput"
						 id="endtime" type="text" onfocus="setday(this,'yyyy-MM-dd','2010-01-01','2010-12-30',1)" readonly="readonly"	/>
			</dd>
			<dd class="dd01_me" style="width: 80px; float: left; margin-left: 10px; color: rgb(101, 124, 149); line-height: 25px; text-align: center; background: rgb(193, 216, 222) none repeat scroll 0% 0%;-moz-border-radius: 3.5px; -webkit-border-radius: 3.5px; border-radius:3.5px;">APPKey</dd>
			<dd class="dd03_me" style='width: 150px; background: transparent url("../img/member_input.gif") no-repeat scroll right top; padding-right: 5px; margin-left: 10px; float: left;'>
				<input name="appname" id="input_appkey" value="" type="text" class="tableinput2">
			</dd>
			<dd class="dd01_me" style="width: 80px; float: left; margin-left: 10px; color: rgb(101, 124, 149); line-height: 25px; text-align: center; background: rgb(193, 216, 222) none repeat scroll 0% 0%;-moz-border-radius: 3.5px; -webkit-border-radius: 3.5px; border-radius:3.5px;">Channel</dd>
			<dd class="dd03_me" style='width: 150px; background: transparent url("../img/member_input.gif") no-repeat scroll right top; padding-right: 5px; margin-left: 10px; float: left;'>
				<input name="appkey" id="input_channel" value="" type="text" class="tableinput2">
			</dd>
			<dd class="dd01_me" style="width: 80px; float: left; margin-left: 10px; color: rgb(101, 124, 149); line-height: 25px; text-align: center; background: rgb(193, 216, 222) none repeat scroll 0% 0%;-moz-border-radius: 3.5px; -webkit-border-radius: 3.5px; border-radius:3.5px;">条目数</dd>
			<dd class="dd03_me" style='width: 85px; padding-right: 5px; margin-left: 10px; float: left;'>
				<input name="entries" id="entries" value="" type="text" class="tableinput2">
			</dd>
			<dd class="ddbtn" style="margin-left: 20px; width: 60px; height: 25px; float: left;margin-top: -4px; margin-left: 76px;">
				<input class="btn_match" name="search" value="" type="button" onclick="getDate();" style='width: 60px; height: 28px; background: transparent url("images/btn001.png") no-repeat scroll center center; text-align: center; line-height: 27px; color: rgb(255, 255, 255); font-weight: bold; cursor: pointer;'>
			</dd>
	</dl>
	<div class="panel panel-warning" data-widget="{&quot;draggable&quot;: &quot;false&quot;}" data-widget-static="">
				<div class="panel-body no-padding">
	<table id="table_id" class="display">
		<thead>
			<tr>
				<th>id</th>
				<th>价格（分）</th>
				<th>支付通道</th>
				<th>IP</th>
				<!--  <th>内容</th> -->
				<th>通道ID</th>
				<!-- <th>APPKey</th> -->
				<th>原始订单号</th> 
				<th>渠道订单号</th> 
				<th>CP订单号</th> 
				<th>状态</th> 
			</tr>
		</thead>
		<tbody id="list2">
			
			
			
		</tbody>
		<tfoot>
	    	<tr>
	        	<th scope="row" >总金额</th>
	            
	            <td id="money" colspan="8"></td>
	            
	        </tr>
	    </tfoot>
	</table>
		</div>
	</div>
  </div>
  <div class="copy_layout">
      <p>Copyright &copy; 2016.Company name All rights reserved. <%=name %>平台 </p>
  </div>
   </div>
      </div>
      <!-- /#page-wrapper -->
   </div>
    <!-- /#wrapper -->
<!-- Nav CSS -->
<link href="css/custom.css" rel="stylesheet">
<!-- Metis Menu Plugin JavaScript -->
<script src="js/metisMenu.min.js"></script>
<script src="js/custom.js"></script>
<script type="text/javascript">
		function isNum(a)
		{
			//var reg=/^(([a-z]+[0-9]+)|([0-9]+[a-z]+))[a-z0-9]*$/i;
			var reg = /^[0-9]*[1-9][0-9]*$/i;
			return reg.test(a);
		}
		
		var totalData = 0;
		
		$(function(){
			$('#table_id').DataTable({
				"bLengthChange": false,
				"bFilter": false,
				"bAutoWidth": false,
				"ordering":false
				
			});
			getData();
			
		});
		
		function getData(){
			var date = '{"starttime":"","endtime":"","appkey":"","channel":"","entries":""}';
			$.ajax({
				type : "post",
				url : "selectPays.jsp",
				async : false,
				data : date,
				dataType : "json",
				success : function(msg) {
										
					if (msg.status == "success") {

						//$("#list").val(msg.data); 
						var list = eval(msg.data);
						console.log(list);
						 var listmsg="";
						 totalData=0;
						 for(var i=0;i<list.length;i++){
							 listmsg += "<tr><td>"+list[i].id+"</td>";
							 listmsg += "<td>"+list[i].price+"</td>";
							 listmsg += "<td>"+list[i].payChannel+"</td>";
							 listmsg += "<td>"+list[i].ip+"</td>";
							 //listmsg += "<td>"+list[i].payInfo+"</td>";
							 listmsg += "<td>"+list[i].releaseChannel+"</td>";
							 //listmsg += "<td>"+list[i].appKey+"</td>";
							 listmsg += "<td class='maxtd'>"+list[i].ownOrderId+"</td>";
							 listmsg += "<td class='maxtd'>"+list[i].payChannelOrderId+"</td>";
							 listmsg += "<td class='maxtd'>"+list[i].cpOrderId+"</td>";
							 if(list[i].testStatus==1){
								 listmsg += "<td>正常</td></tr>";
							 }else{
								 listmsg += "<td>测试</td></tr>";
							 }
							 
							 
							 totalData += list[i].price;
							 
						 }
						$("#list2").empty();
						$("#list2").append(listmsg);
						$("#money").text(totalData/100+"元");
						$('#list2 tr').hover(function() {
							  $(this).addClass('odd');
							}, function() {
							  $(this).removeClass('odd');
							});
					} else {
						alert('邮箱或密码错误!');
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					
					var tip="登录失败!";
					switch (XMLHttpRequest.status)
					{
						case 404:
							tip="登录失败!请检查用户名和密码是否正确。";
					  		break;
						default:
							tip="网络异常，请稍后再试。";
							break;
					  			
					}
					alert(tip);
				}
			});
		}
		
		function getDate(){
			if($("#entries").val()<0){
				alert("条目数不能小于0");
				$("#entries").focus();
				return;
			}else if(!(isNum($("#entries").val()+1))){
				alert("请输入整数！");
				$("#entries").focus();
				return;
			}else if($("#entries").val()>1000){
				alert("条目数过大！");
				$("#entries").focus();
				return;
			}else{
				var date = '{"starttime":"'+$("#starttime").val()+'","endtime":"'+$("#endtime").val()+'","appkey":"'+$("#input_appkey").val()+'","channel":"'+$("#input_channel").val()+'","entries":"'+$("#entries").val()+'"}';
				console.log(date);
				$.ajax({
					type : "post",
					url : "selectPays.jsp",
					async : false,
					data : date,
					dataType : "json",
					success : function(msg) {
											
						if (msg.status == "success") {

							//$("#list").val(msg.data); 
							var list = eval(msg.data);
							 var listmsg="";
							 totalData=0;
							 for(var i=0;i<list.length;i++){
								 listmsg += "<tr><td>"+list[i].id+"</td>";
								 listmsg += "<td>"+list[i].price+"</td>";
								 listmsg += "<td>"+list[i].payChannel+"</td>";
								 listmsg += "<td>"+list[i].ip+"</td>";
								 listmsg += "<td>"+list[i].releaseChannel+"</td>";
								 listmsg += "<td class='maxtd'>"+list[i].ownOrderId+"</td>";
								 listmsg += "<td class='maxtd'>"+list[i].payChannelOrderId+"</td>";
								 listmsg += "<td class='maxtd'>"+list[i].cpOrderId+"</td>";
								 if(list[i].testStatus==1){
									 listmsg += "<td>正常</td></tr>";
								 }else{
									 listmsg += "<td>测试</td></tr>";
								 }
								 
								 totalData += list[i].price;
							 }
							$("#list2").empty();
							$("#list2").append(listmsg);
							$("#money").text(totalData/100+"元");
							$('#list2 tr').hover(function() {
								  $(this).addClass('odd');
								}, function() {
								  $(this).removeClass('odd');
								});
						} else {
							alert('邮箱或密码错误!');
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						
						var tip="登录失败!";
						switch (XMLHttpRequest.status)
						{
							case 404:
								tip="登录失败!请检查用户名和密码是否正确。";
						  		break;
							default:
								tip="网络异常，请稍后再试。";
								break;
						  			
						}
						alert(tip);
					}
				});
			}
			
		}


		
	
	</script>
</body>
</html>
