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
<!DOCTYPE html>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.SimpleDateFormat"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">

<title>日统计</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.1/css/bootstrap.min.css">
<!-- code.jquery.com -->
<script src="../js-css/jquery-1.7.js"></script>
<script src="../js-css/ie-emulation-modes-warning.js"></script>

<link href="../js-css/bootstrap-datetimepicker.min.css" rel="stylesheet"
	media="screen">
<script type="text/javascript"
	src="../js-css/bootstrap-datetimepicker.js" charset="UTF-8"></script>

<!-- DataTables -->
<link rel="stylesheet" type="text/css"
	href="http://cdn.datatables.net/1.10.4/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="http://cdn.datatables.net/1.10.4/js/jquery.dataTables.js"></script>
	
<!-- echarts  -->
<script src="../js-css/echarts.common.min.js"></script>
</head>

<body>
	<jsp:include page="menu.jsp" />
	<table id="table_id" class="display">
		<thead>
			<tr>
				<th>日期</th>
				<th>应用（appkey）</th>
				<th>总额（元）</th>
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
			String appKeys[]=null;
			for(Daily daily:listDaily)
			{
				appKeys= daily.getAppKey().split(",");
				sum+=daily.getPrice();
				%>
			
			<tr>
				<td><%=daily.getId()%></td>
				<td>
				<%for(int i=0;i<appKeys.length;i++){%>
				<a href='daily-appkey.jsp?appkey=<%=appKeys[i]%>' class='menus' target="_blank" style="font-size: 14px"><%=appKeys[i]%></a>
				<% }%></td>
				<td><%=daily.getPrice()/100%></td>
			</tr>
			<%
			}
			%>
		</tbody>
			<tr>
				<td></td>
				<td></td>
				<td>总金额:<%=sum/100 %>元</td>
			</tr>
	</table>
	
	<div id="main" style="width: 80%;height:600px;"></div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#table_id').DataTable({
		        "aaSorting": [
		                      [ 0, "desc" ]
		                  ]
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