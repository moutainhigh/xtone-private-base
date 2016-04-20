<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.common.util.ConnectionService"%>
<%@page import="java.text.SimpleDateFormat"%> 
<%@page import="org.common.util.ConnectionService"%>
<%@page import="org.demo.info.Content"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">

<title>所有文章</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.1/css/bootstrap.min.css">
<!-- code.jquery.com -->
<!-- <script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script> -->
<script src="../js-css/jquery-1.7.js"></script>

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

</head>

<body>
	<%--<div class="con tainer">

		<form class="form-signin" role="form">
			from:<input size="10" name="dateFrom" type="text"
				value="<%=dateFrom%>" class="form_datetime"> 00:00:00 to:<input
				size="10" name="dateTo" type="text" value="<%=dateTo%>"
				class="form_datetime"> 23:59:59
			<script type="text/javascript">
				$(".form_datetime").datetimepicker({
					format : 'yyyy-mm-dd',
					minView : 2,
					autoclose : 1
				});
			</script>

			<button type="submit" name="submit" value="1">view</button>
		</form>

	</div> --%>
	<%
// 	  if (request.getParameter("submit") != null
// 						&& request.getParameter("submit").equals("1")) {
	%>
	<input type="hidden" value="" id="list" />	
	<input type="button" style="width: 150px;height: 30px;margin-bottom: 10px;margin-left:10px" value="新增文章" onclick="window.location.href='content-add.jsp'" >
	<table id="table_id" class="display">
		<thead>
			<tr>
				<th>id</th>
				<th>价格</th>
				<th>支付通道</th>
				<th>IP</th>
				<th>内容</th>
				<th>通道ID</th>
				<th>APPKey</th>
				<th>订单号</th> 
				<th>状态</th> 
			</tr>
		</thead>
		<tbody id="list2">
			
			
			
		</tbody>
	</table>
	<script type="text/javascript">
		$(function(){
			$('#table_id').DataTable();
			getData();
		});
		
		function getData(){
			$.ajax({
				type : "post",
				url : "selectPays.jsp",
				async : false,
				data : null,
				dataType : "json",
				success : function(msg) {
										
					if (msg.status == "success") {

						//$("#list").val(msg.data); 
						 var list = eval(msg.data);
						 var listmsg="";
						 for(var i=0;i<list.length;i++){
							 console.log(list[i].id);
							 listmsg += "<tr><td>"+list[i].id+"</td>";
							 listmsg += "<td>"+list[i].price+"</td>";
							 listmsg += "<td>"+list[i].payChannel+"</td>";
							 listmsg += "<td>"+list[i].ip+"</td>";
							 listmsg += "<td>"+list[i].payInfo+"</td>";
							 listmsg += "<td>"+list[i].releaseChannel+"</td>";
							 listmsg += "<td>"+list[i].appKey+"</td>";
							 listmsg += "<td>"+list[i].payChannelOrderId+"</td>";
							 listmsg += "<td>"+list[i].testStatus+"</td></tr>";
						 }
						$("#list2").empty();
						$("#list2").append(listmsg);
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


		
	
	</script>
	<%
// 	  }
	%>

	<!-- /container -->


	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../js-css/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
