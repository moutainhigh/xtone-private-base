<%@page import="org.demo.info.App"%>
<%@page import="org.demo.json.DataInfo"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="org.demo.service.UserService"%>
<%@page import="org.demo.info.Code"%>
<%@page import="org.common.util.ConnectionService"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.demo.json.CodeRsp"%>
<%@page import="com.google.gson.LongSerializationPolicy"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="org.demo.utils.EscapeUnescape"%>
<%@ include file="inc-receive-body.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	request.getSession(true);
	//User user = (User) session.getAttribute("user");
	//	String info = new String(EscapeUnescape.unescape(request.getParameter("info")));
	GsonBuilder gsonBuilder = new GsonBuilder();
	gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
	Gson gson = gsonBuilder.create();
	App app = gson.fromJson(info, App.class);
	app.setAppName(URLDecoder.decode(app.getAppName(),"UTF-8"));
	PreparedStatement ps = null;
	Connection con = null;
	ResultSet rs = null;
	int updates = 0;
	Date date = new Date();
	String sql = "";
	try {
		
		con = ConnectionService.getInstance().getConnectionForLocal();
		if(app.getType()==2){
			
			sql = "UPDATE `tbl_thirdpay_apps` SET appName=?,notify_url=?,encrypt=?,encrypt_key=?,cpId=? WHERE appKey=?";									   
			ps = con.prepareStatement(sql);
			int m = 1;
			ps.setString(m++, app.getAppName());
			ps.setString(m++, app.getNotify_url());
			ps.setString(m++, app.getEncrypt());
			ps.setString(m++, app.getEncrypt_key());
			ps.setLong(m++, app.getCpid());
			ps.setString(m++, app.getAppkey());
			
			
			updates = ps.executeUpdate();
	
			
			if (updates != 0) {
				CodeRsp codeRsp = new CodeRsp();
				String rsp = gson.toJson(app);
				out.print(rsp);
			} else {
				out.print("{\"status\":\"error\",\"data\":\"" + info + "\"}");
			}
		}else if(app.getType()==1){
			
			sql = "SELECT appKey FROM `tbl_thirdpay_apps` WHERE appKey=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, app.getAppkey());
			rs = ps.executeQuery();
			if(rs.next()){
				DataInfo datainfo = new DataInfo();
				datainfo.setStatue("error");
				datainfo.setData("appKey已存在!");
				String rsp = gson.toJson(datainfo);
				out.print(rsp);
			}else{
				sql = "INSERT INTO `tbl_thirdpay_apps`(appKey,appName,notify_url,encrypt,encrypt_key,cpId) VALUES(?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				
				int m = 1;
				ps.setString(m++, app.getAppkey());
				ps.setString(m++, app.getAppName());
				ps.setString(m++, app.getNotify_url());
				ps.setString(m++, app.getEncrypt());
				ps.setString(m++, app.getEncrypt_key());
				ps.setLong(m++, app.getCpid());
				if(!ps.execute()){
					CodeRsp codeRsp = new CodeRsp();
					String rsp = gson.toJson(app);
					out.print(rsp);
				}else {
					out.print("{\"status\":\"error\",\"data\":\"" + info + "\"}");
				}
			}
		}
		

	} catch (Exception e) {
		// TODO Auto-generated catch block
		out.print("{\"status\":\"error\",\"data\":\"" + info + "\"}");
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