<%@page import="org.demo.info.User"%>
<%@page import="org.demo.info.Content"%>
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

<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">

<title>用户列表</title>

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

<body>
	<jsp:include page="menu.jsp"/>
	
	<a style="color: #333 !important;border: 1px solid #CACACA;background: transparent linear-gradient(to bottom, #FFF 0%, #DCDCDC 100%) repeat scroll 0% 0%;box-sizing: border-box;display: inline-block;min-width: 1.5em;padding: 0.5em 1em;margin-left: 2px;text-align: center;text-decoration: none !important;cursor: pointer;" href="user-add.jsp">添加用户</a>
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
					String sql = "SELECT id,username,pwd,email,isAvail,isAdmin,lastLogin,ADDTIME "+
							" FROM `tbl_thirdpay_cp_channel_users` WHERE cpId="+user.getCpid()+" and releaseChannel='"+user.getReleaseChannel()+"'";
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
			%>	
			<tr>
				<td><%=id%></td>
				<td><%=rs.getString("username") %></td>
				<td><%=rs.getString("email") %></td>
				<td><%=isAdmin>0? "管理员":"用户" %></td>
				<td><%=status>0? "启用":"关闭" %></td>
				<td><%=lastloginstr %></td>
				<td><%=addTimestr %></td>
				<td> 
				<a href="user-update.jsp?id=<%=id%>">编辑</a>&emsp;
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
	<script type="text/javascript">
		$(document).ready(function() {
			$('#table_id').DataTable();
		});
	</script>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../js-css/ie10-viewport-bug-workaround.js"></script>
</body>
</html>