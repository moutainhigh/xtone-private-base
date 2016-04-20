<%@page import="com.google.gson.LongSerializationPolicy"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="org.demo.json.PaysData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.common.util.ConnectionService"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="org.demo.info.Pays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Pays pays = null;
	List<Pays> list = new ArrayList<Pays>();
	PreparedStatement ps = null;
	Connection con = null;
	ResultSet rs = null;
	try{
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
		Gson gson = gsonBuilder.create();
		con = ConnectionService.getInstance()
				.getConnectionForLocal();
		String sql = "SELECT * FROM log_success_pays";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()){
			pays = new Pays();
			pays.setId(rs.getLong("id"));
			pays.setPrice(rs.getInt("price"));
			pays.setPayChannel(rs.getString("payChannel"));
			pays.setIp(rs.getString("ip"));
			pays.setPayInfo(rs.getString("payInfo"));
			pays.setReleaseChannel(rs.getString("releaseChannel"));
			pays.setAppKey(rs.getString("appKey"));
			pays.setPayChannelOrderId(rs.getString("payChannelOrderId"));
			pays.setTestStatus(rs.getInt("testStatus"));
			list.add(pays);
		}
		PaysData paysdata = new PaysData();
		paysdata.setStatus("success");
		paysdata.setData(list);
		String rsp = gson.toJson(paysdata);
		System.out.print(rsp);
		out.print(rsp);
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