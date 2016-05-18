<%@page import="java.util.List"%>
<%@page import="org.demo.service.UserService"%>
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

<title>单个应用统计</title>

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
</head>
<%String appKey = request.getParameter("appkey"); %>
<body>
	<jsp:include page="menu.jsp" />
	<font style="margin-left: 10px;font-size: 17px">appKey&nbsp;:&nbsp;<%=appKey%></font>
	<table id="table_id" class="display">
		<thead>
			<tr>
				<th>日期</th>
				<th>channel渠道</th>
				<th>总额（元）</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<Daily> listDaily=UserService.getChannelByAppkeys(appKey);
			float sum=0;
			String appKeys[]=null;
			for(Daily daily:listDaily)
			{
				sum+=daily.getPrice();
				%>
			<tr>
				<td><%=daily.getId()%></td>
				<td><%=daily.getChannel()%></td>
				<td><%=daily.getPrice() / 100%></td>
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
	<script type="text/javascript">
		$(document).ready(function() {
			$('#table_id').DataTable({
		        "aaSorting": [
		                      [ 0, "desc" ]
		                  ]
		              } );
		});
	</script>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../js-css/ie10-viewport-bug-workaround.js"></script>
</body>
</html>