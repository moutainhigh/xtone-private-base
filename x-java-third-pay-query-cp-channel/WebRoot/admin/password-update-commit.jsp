<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.demo.service.UserService"%>
<%@page import="org.demo.info.User"%>
<%@page import="org.common.util.ConnectionService"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.demo.json.LoginRsp"%>
<%@page import="com.google.gson.LongSerializationPolicy"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@ include file="inc-receive-body.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	request.getSession(true);
	User user = (User) session.getAttribute("user");

	PreparedStatement ps = null;
	Connection con = null;
	ResultSet rs = null;
	int updates = 0;
	
	try {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
		Gson gson = gsonBuilder.create();
		User userChange = gson.fromJson(info, User.class);
		userChange.setId(user.getId());
		userChange.setUserName(user.getUserName());
		con = ConnectionService.getInstance().getConnectionForLocal();
		String sql = "select id from tbl_thirdpay_cp_users where username=? and pwd=md5(?)";
		ps = con.prepareStatement(sql);
		int m = 1;
	    ps.setString(m++, userChange.getUserName());
	    ps.setString(m++, userChange.getPassword());
	    rs = ps.executeQuery();
	    if (rs.next()) {
	    	if(rs.getLong("id")!=userChange.getId()){
	    		out.print("{\"status\":\"error\"}");
		    	return;
	    	}
	    }else{
	    	out.print("{\"status\":\"errOldPwd\"}");
	    	return;
	    }
		
		sql = "UPDATE tbl_thirdpay_cp_users SET pwd=md5(?) WHERE id=?";
		ps = con.prepareStatement(sql);
		m = 1;
		ps.setString(m++, userChange.getNewPassword());
		ps.setLong(m++, userChange.getId());
		
		updates = ps.executeUpdate();

		if (updates != 0) {
			LoginRsp loginRsp = new LoginRsp();
			loginRsp.setStatus("success");
			loginRsp.setData(userChange);
			String rsp = gson.toJson(loginRsp);
			out.print(rsp);
		} else {
			out.print("{\"status\":\"error\"}");
		}

	} catch (Exception e) {
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