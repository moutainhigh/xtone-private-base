<%@page import="org.common.util.ConfigManager"%>
<%@page import="org.demo.info.User"%>
<%@page import="org.demo.info.Content"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.common.util.ConnectionService"%>
<%@page import="java.text.SimpleDateFormat"%>
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
			
        </nav>
        <a style="color: #fff !important;background: transparent;min-width: 1.5em;margin-left:0px" href="cp-users.jsp">CP管理</a>
        <a style="color: #fff !important;background: transparent;min-width: 1.5em;margin-left:20px">渠道管理</a>
        <a style="color: #fff !important;background: transparent;min-width: 1.5em;margin-left:20px" href="apps.jsp">APP管理</a>
        <br/>
        <div id="page-wrapper" style="margin: 0 0 0 0;">
        <div class="col-md-12 graphs">
	   <div class="xs">
  	 <h3>渠道管理</h3>
  	 <a style="color: #333 !important;border: 1px solid #CACACA;background: transparent linear-gradient(to bottom, #FFF 0%, #DCDCDC 100%) repeat scroll 0% 0%;box-sizing: border-box;display: inline-block;min-width: 1.5em;padding: 0.5em 1em;margin-left: 2px;text-align: center;text-decoration: none !important;cursor: pointer;" href="cp-channel-user-add.jsp">添加渠道用户</a>
	<div class="panel panel-warning" data-widget="{&quot;draggable&quot;: &quot;false&quot;}" data-widget-static="">
				<div class="panel-body no-padding">
	<table id="table_id" class="display">
		<thead>
			<tr>
				<th>id</th>
				<th>用户名</th>
				<th>邮箱</th>
				<th>权限</th>
				<th>状态</th>
				<th>最后登录时间</th>
				<th>创建时间</th>
				<th>cpId</th>
				<th>发布渠道</th>
				<th>扣量起始金额</th>
				<th>扣量比例</th>
				<td></td>
			</tr>
		</thead>
		<tbody>
		<%
		request.setCharacterEncoding("UTF-8");
		request.getSession(true);
		User user = (User) session.getAttribute("user");
				PreparedStatement ps = null;
				Connection con = null;
				ResultSet rs = null;	
				try {
					con = ConnectionService.getInstance().getConnectionForLocal();
					String sql = "SELECT id,username,pwd,email,isAvail,isAdmin,lastLogin,ADDTIME,cpid,releaseChannel,reduceBeginAmoumt,reduceScale "+
							" FROM `tbl_thirdpay_cp_channel_users`";
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					while (rs.next()) {
						long id=rs.getLong("id");
						int isAdmin = rs.getInt("isAdmin");
						int status = rs.getInt("isAvail");
						long lastlogin = rs.getLong("lastLogin");
						long addTime = rs.getLong("addTime");
						String lastloginstr = sdf.format(lastlogin);
						String addTimestr = sdf2.format(addTime);
						long cpId = rs.getLong("cpId");
						int reduceBA = rs.getInt("reduceBeginAmoumt");
						int reduceScale = rs.getInt("reduceScale");
			%>	
			<tr>
				<td><%=id%></td>
				<td><%=rs.getString("username") %></td>
				<td><%=rs.getString("email") %></td>
				<td><%=isAdmin>0? "管理员":"用户" %></td>
				<td><%=status>0? "启用":"关闭" %></td>
				<td><%=lastlogin>0? sdf.format(lastlogin):"null" %></td>
				<td><%=addTime>0?  sdf2.format(addTime):"null"%></td>
				<td><%=cpId %></td>
				<td><%=rs.getString("releaseChannel") %></td>
				<td><%=reduceBA %></td>
				<td><%=reduceScale %></td>
				<td> 
				<a href="cp-channel-user-update.jsp?id=<%=id%>">编辑</a>&emsp;
			</tr>
				<%
					}
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (con != null) {
						try {
							con.close();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
	%>
		</tbody>
	</table>
		</div>
	</div>
  </div>
  <div class="copy_layout">
      <p>Copyright &copy; 2015.Company name All rights reserved.More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">万家无线</a> </p>
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
			$('#table_id').DataTable();
		});
	</script>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../js-css/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
