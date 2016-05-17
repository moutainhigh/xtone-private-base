<%@page import="org.demo.info.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.common.util.ConnectionService"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.getSession(true);
	User user = (User) session.getAttribute("user");
	PreparedStatement ps = null;
	Connection con = null;
	ResultSet rs = null;
	String sql = "";
	try{
		con = ConnectionService.getInstance().getConnectionForLocal();
		sql = "SELECT isAdmin FROM `tbl_thirdpay_cp_users` WHERE id="+user.getId();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next()){
			user.setAdmin(rs.getInt("isAdmin"));
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
// 		out.print("{\"status\":\"error\",\"data\":\"\"}");
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style>
#navigation ul{
	list-style-type: none;
}
#navigation li{
	float: left;
	font-size: 20px;
}

.menus{
	font-size: 20px; 
	margin-left: 20px;
}

</style>
<div style="margin-top: 5px; margin-left: 10px; margin-bottom: 10px">
	<a href='stat-all.jsp' class='menus' style='margin-left: 0px;'>数据展示</a>
	<a href='daily.jsp' class='menus'>日统计</a>
	<%
			if(user.getAdmin() == 1){
				
				out.write("<a href='user-all.jsp' class='menus'>用户管理</a>");
			}
	%>
	 <a href="password-update.jsp" class="menus">更换密码</a>
</div>
