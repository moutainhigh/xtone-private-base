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
		long curMils = System.currentTimeMillis();
		//System.out.println("JiaBing start open connection");
		con = ConnectionService.getInstance()
				.getConnectionForLocal();
		
		//System.out.println("JiaBing end open connection spend times:" + (System.currentTimeMillis() - curMils));
		
		String sql = "SELECT FROM_UNIXTIME(id/1000/1000000, '%Y-%m-%d') AS dt,price,payChannel,ip,payInfo,releaseChannel,appKey,payChannelOrderId,testStatus"+
				" FROM log_success_pays WHERE 1=1";
		if(!payrsp.getTime().equals("")){
			sql += " AND UNIX_TIMESTAMP('"+payrsp.getTime()+"')*1000*1000000<id AND (UNIX_TIMESTAMP('"+payrsp.getTime()+"')+86400)*1000*1000000>id ";
			
		}
		if(!payrsp.getAppkey().equals("")){
			sql += " AND appKey like '"+payrsp.getAppkey()+"' ";
		}
		if(!payrsp.getChannel().equals("")){
			sql += " AND releaseChannel like '"+payrsp.getChannel()+"' ";
		}
		sql += " ORDER BY id DESC";
		sql += " limit 0,50 ";
		//System.out.println("JiaBing SQL:" + sql);
		curMils = System.currentTimeMillis();
		ps = con.prepareStatement(sql);
		//System.out.println("JiaBing open ps spend times:" + (System.currentTimeMillis() - curMils));
		
		curMils = System.currentTimeMillis();
		
		rs = ps.executeQuery();
		
		//System.out.println("JiaBing executeQuery spend times:" + (System.currentTimeMillis() - curMils));
		
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
			if(rs.getInt("testStatus")==1){
				pays.setTestStatus("测试");
			}else{
				pays.setTestStatus("正常");
			}
			//System.out.print(rs.getString("dt"));
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
		
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
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