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

<title>数据查询</title>

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
<script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js-css/DatePicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="../My97DatePicker/skin/default/datepicker.css">
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
	<jsp:include page="menu.jsp"/>
	<input type="hidden" value="" id="list" />	
	<dl style="height: 41px; margin-top: 35px; margin-bottom: 28px;">
			<dd class="dd01_me" style="width: 80px; float: left; margin-left: 10px; color: rgb(102, 102, 102); line-height: 25px; text-align: center; background: rgb(192, 192, 192) none repeat scroll 0% 0%;">开始日期</dd>
			<dd class="dd03_me" style='width: 100px; background: transparent url("../img/member_input.gif") no-repeat scroll right top; padding-right: 5px; margin-left: 10px; float: left;'>
				<input  style='width: 90px; background: transparent url("../img/member_input.gif") no-repeat scroll left top; text-align: left; padding-left: 6px; line-height: 25px; height: 25px; color: rgb(102, 102, 102);'
						 id="starttime" type="text" onfocus="setday(this,'yyyy-MM-dd','2010-01-01','2010-12-30',1)" readonly="readonly"	/>
			</dd>
			<dd class="dd01_me" style="width: 80px; float: left; margin-left: 10px; color: rgb(102, 102, 102); line-height: 25px; text-align: center; background: rgb(192, 192, 192) none repeat scroll 0% 0%;">结束日期</dd>
			<dd class="dd03_me" style='width: 100px; background: transparent url("../img/member_input.gif") no-repeat scroll right top; padding-right: 5px; margin-left: 10px; float: left;'>
				<input  style='width: 90px; background: transparent url("../img/member_input.gif") no-repeat scroll left top; text-align: left; padding-left: 6px; line-height: 25px; height: 25px; color: rgb(102, 102, 102);'
						 id="endtime" type="text" onfocus="setday(this,'yyyy-MM-dd','2010-01-01','2010-12-30',1)" readonly="readonly"	/>
			</dd>
			<dd class="dd01_me" style="width: 80px; float: left; margin-left: 10px; color: rgb(102, 102, 102); line-height: 25px; text-align: center; background: rgb(192, 192, 192) none repeat scroll 0% 0%;">APPKey</dd>
			<dd class="dd03_me" style='width: 150px; background: transparent url("../img/member_input.gif") no-repeat scroll right top; padding-right: 5px; margin-left: 10px; float: left;'>
				<input name="appname" id="input_appkey" value="" type="text" style='width: 150px; background: transparent url("../img/member_input.gif") no-repeat scroll left top; text-align: left; padding-left: 6px; line-height: 25px; height: 25px; color: rgb(102, 102, 102);'>
			</dd>
			<dd class="dd01_me" style="width: 80px; float: left; margin-left: 10px; color: rgb(102, 102, 102); line-height: 25px; text-align: center; background: rgb(192, 192, 192) none repeat scroll 0% 0%;">Channel</dd>
			<dd class="dd03_me" style='width: 150px; background: transparent url("../img/member_input.gif") no-repeat scroll right top; padding-right: 5px; margin-left: 10px; float: left;'>
				<input name="appkey" id="input_channel" value="" type="text" style='width: 150px; background: transparent url("../img/member_input.gif") no-repeat scroll left top; text-align: left; padding-left: 6px; line-height: 25px; height: 25px; color: rgb(102, 102, 102);'>
			</dd>
			<dd class="dd01_me" style="width: 80px; float: left; margin-left: 10px; color: rgb(102, 102, 102); line-height: 25px; text-align: center; background: rgb(192, 192, 192) none repeat scroll 0% 0%;">条目数</dd>
			<dd class="dd03_me" style='width: 85px; background: transparent url("../img/member_input.gif") no-repeat scroll right top; padding-right: 5px; margin-left: 10px; float: left;'>
				<input name="entries" id="entries" value="" type="text" style='width: 150px; background: transparent url("../img/member_input.gif") no-repeat scroll left top; text-align: left; padding-left: 6px; line-height: 25px; height: 25px; color: rgb(102, 102, 102);'>
			</dd>
			<dd class="ddbtn" style="margin-left: 20px; width: 60px; height: 25px; float: left; margin-top: 0px;">
				<input class="btn_match" name="search" value="查 询" type="button" onclick="getDate();" style='width: 60px; height: 28px; background: transparent url("../img/botton_099.gif") no-repeat scroll center center; text-align: center; line-height: 27px; color: rgb(255, 255, 255); font-weight: bold; cursor: pointer;'>
			</dd>
	</dl>
	<table id="table_id" class="display">
		<thead>
			<tr>
				<th>id</th>
				<th>价格（分）</th>
				<th>支付通道</th>
				<th>IP</th>
				<th>内容</th>
				<th>通道ID</th>
				<th>APPKey</th>
				<th>原始订单号</th> 
				<th>渠道订单号</th> 
				<th>CP订单号</th> 
				<th>状态</th> 
			</tr>
		</thead>
		<tbody id="list2">
			
			
			
		</tbody>
	</table>
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
							 listmsg += "<td>"+list[i].payInfo+"</td>";
							 listmsg += "<td>"+list[i].releaseChannel+"</td>";
							 listmsg += "<td>"+list[i].appKey+"</td>";
							 listmsg += "<td>"+list[i].ownOrderId+"</td>";
							 listmsg += "<td>"+list[i].payChannelOrderId+"</td>";
							 listmsg += "<td>"+list[i].cpOrderId+"</td>";
							 listmsg += "<td>"+list[i].testStatus+"</td></tr>";
							 
							 totalData += list[i].price;
							 
						 }
						$("#list2").empty();
						listmsg += "<tr><td></td><td>总金额:"+totalData/100+"元</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";
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
								 listmsg += "<td>"+list[i].payInfo+"</td>";
								 listmsg += "<td>"+list[i].releaseChannel+"</td>";
								 listmsg += "<td>"+list[i].appKey+"</td>";
								 listmsg += "<td>"+list[i].ownOrderId+"</td>";
								 listmsg += "<td>"+list[i].payChannelOrderId+"</td>";
								 listmsg += "<td>"+list[i].cpOrderId+"</td>";
								 listmsg += "<td>"+list[i].testStatus+"</td></tr>";
								 
								 totalData += list[i].price;
							 }
							$("#list2").empty();
							listmsg += "<tr><td></td><td>总金额:"+totalData/100+"元</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";
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
