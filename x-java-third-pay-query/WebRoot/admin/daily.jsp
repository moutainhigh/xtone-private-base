<%@page import="org.demo.info.Apps"%>
<%@page import="java.util.List"%>
<%@page import="org.demo.service.UserService"%>
<%@page import="org.common.util.ConnectionService"%>
<%@page import="org.demo.info.User"%>
<%@page import="org.demo.utils.ConnectionServiceConfig"%>
<%@page import="org.demo.info.Daily"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.common.util.ConfigManager"%>
<%@page import="java.text.DecimalFormat"%>
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
	        		<a href="#" class="dropdown-toggle avatar" data-toggle="dropdown"><img src="images/9.jpg" alt=""/></a>
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
                            <a href="daily.jsp"><i class="fa fa-dashboard fa-fw nav_icon"></i>日统计</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
        <div id="page-wrapper">
        <div class="col-md-12 graphs">
	   <div class="xs">
  	 <h3>Daily statistics</h3>
	<div class="panel panel-warning" data-widget="{&quot;draggable&quot;: &quot;false&quot;}" data-widget-static="">
				<div class="panel-body no-padding">
	<table id="table_id" class="display">
		<thead>
			<tr>
				<th>日期</th>
				<th>应用（appkey）</th>
				<th>总额（元）</th>
				<th>每日总付费用户</th>
				<th>付费APRU值</th>
			</tr>
		</thead>
		<tbody>
			<%
			request.setCharacterEncoding("UTF-8");
			request.getSession(true);
			User user = (User) session.getAttribute("user");
			List<Daily> listDaily=UserService.getDailyByAppkeys(user);
			List<Daily> listDaily2=UserService.getDailyByAppkeys2(user);
			List<Apps> listApp = UserService.selectByCpid(user);
			float sum=0;
			int sumPayUsers =0;
			String appKeys[]=null;
			for(Daily daily:listDaily)
			{
				appKeys= daily.getAppKey().split(",");
				sum+=daily.getPrice();
				sumPayUsers+=daily.getPayUsers();
				
<<<<<<< HEAD
			
=======
>>>>>>> master
				%>
			
			<tr>
				<td><%=daily.getId()%></td>
				<td>
				<%for(int i=0;i<appKeys.length;i++){%>
				<a href='daily-appkey.jsp?appkey=<%=appKeys[i]%>' class='menus' target="_blank" style="font-size: 14px"><%=appKeys[i]%></a>
				<% }%></td>
				<td><%=daily.getPrice()/100%></td>
				<td><%=daily.getPayUsers()%></td>
				<%
				if(daily.getPayUsers() == 0){ %>
					<td><%= ""%></td>
				<%}else{
<<<<<<< HEAD
					 float   scale   =   (float) daily.getPrice()/100/daily.getPayUsers();  
					  DecimalFormat   fnum   =   new   DecimalFormat("##0.00");  
					  String   dd=fnum.format(scale); 
				%><td><%= 
						
						dd%></td>
=======
				%><td><%= daily.getPrice()/100/daily.getPayUsers()%></td>
>>>>>>> master
					
				<%}
				%>
			</tr>
			<%
			}
			
		
			%>
		</tbody>
			<tr>
				<td></td>
				<td></td>
				<td>总金额:<%=sum/100 %>元</td>
				<td>总付费用户:<%=sumPayUsers%>人</td>
				<td><%=""%></td>
			</tr>
	</table>
		</div>
	</div>
  </div>
  <div class="copy_layout">
      <p>Copyright &copy; 2016.Company name All rights reserved. <a href="http://www.vanggame.com/" target="_blank" title="模板之家">万家无线</a> 版权所有 </p>
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
		$(document).ready(function() {
			$('#table_id').DataTable({
		        "aaSorting": [[ 0, "desc" ]] 
			     ,"aLengthMenu": [[100, 50, 25, 10], [100, 50, 25, 10]]
			    /* ,"iDisplayLength":100  */
		              } );
		});
		
		var appKeysDate = new Array();
		<%for(Daily daily:listDaily2)
			{
				appKeys= daily.getAppKey().split(",");
				sum+=daily.getPrice();
				for(int i=0;i<appKeys.length;i++){
				%>
					var bean = new Object();
					bean.time = '<%=daily.getId()%>';
					bean.appkey = '<%=appKeys[i]%>';
					bean.price = <%=daily.getPrice()/100%>;
					appKeysDate.push(bean);
		<%}}%>
		console.log(appKeysDate);
		var appkey = new Array();
		<%for(Apps app:listApp){%>
			appkey.push('<%=app.getAppkey()%>');
		<%}%>
		var time = new Array();
		for(var i=appKeysDate.length-1;i>=0;i--){
			time.push(appKeysDate[i].time);
		}
		
		console.log(time);
		
		var temp = new Array();
		var counter = new Array();
		
		
		<%for(Daily daily:listDaily)
		{
			appKeys= daily.getAppKey().split(",");
			sum+=daily.getPrice();
			%>
				
		<%}%>
		
		<%
				
		%>
		
		
		
		
		
		
		
		// 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
		appkey.length
		
        // 指定图表的配置项和数据
		option = {
		    title: {
		        text: '日统计折线图'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data: appkey
		    },
		    toolbox: {
		        feature: {
		            saveAsImage: {}
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : time
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        
		        {
		            name:'邮件营销',
		            type:'line',
		            stack: '总量',
		            areaStyle: {normal: {}},
		            data:[120, 132, 101, 134, 90, 230, 210]
		        },
		        {
		            name:'联盟广告',
		            type:'line',
		            stack: '总量',
		            areaStyle: {normal: {}},
		            data:[220, 182, 191, 234, 290, 330, 310]
		        },
		        {
		            name:'视频广告',
		            type:'line',
		            stack: '总量',
		            areaStyle: {normal: {}},
		            data:[150, 232, 201, 154, 190, 330, 410]
		        },
		        {
		            name:'直接访问',
		            type:'line',
		            stack: '总量',
		            areaStyle: {normal: {}},
		            data:[320, 332, 301, 334, 390, 330, 320]
		        },
		        {
		            name:'搜索引擎',
		            type:'line',
		            stack: '总量',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'top'
		                }
		            },
		            areaStyle: {normal: {}},
		            data:[820, 932, 901, 934, 1290, 1330, 1320]
		        }
		    ]
		};

        
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
	</script>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../js-css/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
