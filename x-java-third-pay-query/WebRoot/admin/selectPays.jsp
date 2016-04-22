<%@page import="org.demo.info.PayRsp"%>
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
<%@ include file="inc-receive-body.jsp"%>
<%
	Pays pays = null;
	List<Pays> list = new ArrayList<Pays>();
	PayRsp payrsp = null;
	PreparedStatement ps = null;
	Connection con = null;
	ResultSet rs = null;
	try{
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
		Gson gson = gsonBuilder.create();
		payrsp = gson.fromJson(info, PayRsp.class);
		System.out.print(payrsp.getTime()+":"+payrsp.getAppkey()+":"+payrsp.getChanell());
		con = ConnectionService.getInstance()
				.getConnectionForLocal();
		String sql = "SELECT FROM_UNIXTIME(id/1000/1000000, '%Y-%m-%d') AS dt,price,payChannel,ip,payInfo,releaseChannel,appKey,payChannelOrderId,testStatus"+
				" FROM log_success_pays WHERE 1=1";
		if(!payrsp.getTime().equals("")){
			sql += " AND UNIX_TIMESTAMP('"+payrsp.getTime()+"')*1000*1000000<id AND (UNIX_TIMESTAMP('"+payrsp.getTime()+"')+86400)*1000*1000000>id ";
			
		}
		System.out.println(sql);
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()){
			pays = new Pays();
			pays.setId(rs.getString("dt"));
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