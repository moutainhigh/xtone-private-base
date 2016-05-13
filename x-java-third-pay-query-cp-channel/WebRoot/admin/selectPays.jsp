<%@page import="org.demo.utils.StringUtil"%>
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
<%@page import="org.demo.info.User"%>
<%@page import="org.demo.info.Apps"%>
<%@page import="org.demo.service.UserService"%>
<%@page import="org.demo.utils.ConnectionServiceConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="inc-receive-body.jsp"%>
<%
  Pays pays = null;
  User user = null;
  Apps apps = null;
  List<Apps> appsList = new ArrayList<Apps>();
  List<Pays> paysList = new ArrayList<Pays>();
  List<Pays> list = new ArrayList<Pays>();
  List<Pays> list2 = new ArrayList<Pays>();
  PayRsp payrsp = null;
  PreparedStatement ps = null;
  Connection con = null;
  ResultSet rs = null;
  String limit = " limit 0,50 ";
  int n = 1;
  try {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
    Gson gson = gsonBuilder.create();
    payrsp = gson.fromJson(info, PayRsp.class);
    long curMils = System.currentTimeMillis(); //获得当前时间的毫秒数
    //System.out.println("JiaBing start open connection");
    con = ConnectionServiceConfig.getInstance().getConnectionForLocal();

    //System.out.println("JiaBing end open connection spend times:" + (System.currentTimeMillis() - curMils));

    user = (User) session.getAttribute("user");
    appsList = UserService.selectByCpid(user);
    for (Apps apps2 : appsList) {
      paysList = UserService.selectByAppkeyAndReleaseChannel(apps2,user.getReleaseChannel());

      for (Pays pays2 : paysList) {
        list.add(pays2);
      }
    }

    if (!StringUtil.isNullOrEmpty(payrsp.getStarttime()) && !StringUtil.isNullOrEmpty(payrsp.getEndtime())) {

      int start = Integer.parseInt(payrsp.getStarttime().substring(0, 4) + payrsp.getStarttime().substring(5, 7)
          + payrsp.getStarttime().substring(8, 10));
      int end = Integer.parseInt(payrsp.getEndtime().substring(0, 4) + payrsp.getEndtime().substring(5, 7)
          + payrsp.getEndtime().substring(8, 10));
      for (Pays pays3 : list) {
        int pays3id = Integer.parseInt(pays3.getId().substring(0, 4) + pays3.getId().substring(5, 7)
            + pays3.getId().substring(8, 10));
        if (pays3id < start || pays3id > end) {

          list2.add(pays3);
        }
      }

    } else {
      if (!StringUtil.isNullOrEmpty(payrsp.getStarttime())) {
        int start = Integer.parseInt(payrsp.getStarttime().substring(0, 4) + payrsp.getStarttime().substring(5, 7)
            + payrsp.getStarttime().substring(8, 10));
        for (Pays pays3 : list) {
          int pays3id = Integer.parseInt(pays3.getId().substring(0, 4) + pays3.getId().substring(5, 7)
              + pays3.getId().substring(8, 10));
          if (pays3id < start) {

            list2.add(pays3);
          }
        }
      }
      if (!StringUtil.isNullOrEmpty(payrsp.getEndtime())) {
        int end = Integer.parseInt(payrsp.getEndtime().substring(0, 4) + payrsp.getEndtime().substring(5, 7)
            + payrsp.getEndtime().substring(8, 10));
        for (Pays pays3 : list) {
          int pays3id = Integer.parseInt(pays3.getId().substring(0, 4) + pays3.getId().substring(5, 7)
              + pays3.getId().substring(8, 10));
          if (pays3id > end) {

            list2.add(pays3);
          }
        }
      }
    }
    if (!payrsp.getAppkey().equals("")) {
      try {
        for (Pays pays3 : list) {

          if (!pays3.getAppKey().substring(0, payrsp.getAppkey().length()).equalsIgnoreCase(payrsp.getAppkey())) {

            list2.add(pays3);
          }
        }
      } catch (Exception ex) {
        for (Pays payss : list) {
          list2.add(payss);
        }
      }
    }

    if (!payrsp.getChannel().equals("")) {
      try {
        for (Pays pays3 : list) {

          if (!pays3.getPayChannel().substring(0, payrsp.getChannel().length())
              .equalsIgnoreCase(payrsp.getChannel())) {

            list2.add(pays3);
          }
        }
      } catch (Exception ex) {
        for (Pays payss : list) {
          list2.add(payss);
        }
      }
    }

    // 	 		if(!payrsp.getEntries().equals("")){
    // 	 			limit =  " limit 0,"+payrsp.getEntries();
    // 	 		}
    // 		String sql = "SELECT FROM_UNIXTIME(id/1000/1000000, '%Y-%m-%d') AS dt,price,payChannel,ip,payInfo,releaseChannel,appKey,ownOrderId,payChannelOrderId,cpOrderId,testStatus"+
    // 				" FROM log_success_pays WHERE 1=1 AND appKey='ea7e48e69d1149b2a5e2ef8d3a5d9e7a' ";
    // 		if(!StringUtil.isNullOrEmpty(payrsp.getStarttime())&&!StringUtil.isNullOrEmpty(payrsp.getEndtime())){
    // 			sql += " AND UNIX_TIMESTAMP('"+payrsp.getStarttime()+"')*1000*1000000<id AND (UNIX_TIMESTAMP('"+payrsp.getEndtime()+"')+86400)*1000*1000000>id ";
    // 		}else{
    // 			if(!StringUtil.isNullOrEmpty(payrsp.getStarttime())){
    // 				sql += " AND UNIX_TIMESTAMP('"+payrsp.getStarttime()+"')*1000*1000000<id AND (UNIX_TIMESTAMP('"+payrsp.getStarttime()+"')+86400)*1000*1000000>id ";
    // 			}
    // 			if(!StringUtil.isNullOrEmpty(payrsp.getEndtime())){
    // 				sql += " AND UNIX_TIMESTAMP('"+payrsp.getEndtime()+"')*1000*1000000<id AND (UNIX_TIMESTAMP('"+payrsp.getEndtime()+"')+86400)*1000*1000000>id ";
    // 			}
    // 		}
    // 		if(!payrsp.getAppkey().equals("")){
    // 			//sql += " AND appKey like 'ea7e48e69d1149b2a5e2ef8d3a5d9e7a' ";
    // 		}
    // 		if(!payrsp.getChannel().equals("")){
    // 			sql += " AND releaseChannel like '%"+payrsp.getChannel()+"%' ";
    // 		}
    // 		if(!payrsp.getEntries().equals("")){
    // 			limit =  " limit 0,"+payrsp.getEntries();
    // 		}
    // 		sql += " ORDER BY id DESC";
    // 		sql += limit;
    // 		System.out.println("JiaBing SQL:" + sql);
    // 		curMils = System.currentTimeMillis();
    // 		ps = con.prepareStatement(sql);
    // 		//System.out.println("JiaBing open ps spend times:" + (System.currentTimeMillis() - curMils));

    // 		curMils = System.currentTimeMillis();

    // 		rs = ps.executeQuery();

    // 		//System.out.println("JiaBing executeQuery spend times:" + (System.currentTimeMillis() - curMils));

    // 		while(rs.next()){
    // 			pays = new Pays();
    // 			pays.setId(rs.getString("dt"));
    // 			pays.setPrice(rs.getInt("price"));
    // 			pays.setPayChannel(rs.getString("payChannel"));
    // 			pays.setIp(rs.getString("ip"));
    // 			pays.setPayInfo(rs.getString("payInfo"));
    // 			pays.setReleaseChannel(rs.getString("releaseChannel"));
    // 			pays.setAppKey(rs.getString("appKey"));
    // 			pays.setOwnOrderId(rs.getString("ownOrderId"));
    // 			pays.setPayChannelOrderId(rs.getString("payChannelOrderId"));
    // 			pays.setCpOrderId(rs.getString("cpOrderId"));
    // 			if(rs.getInt("testStatus")==1){
    // 				pays.setTestStatus("测试");
    // 			}else{
    // 				pays.setTestStatus("正常");
    // 			}
    // 			//System.out.println(""+n+++":"+rs.getString("dt"));
    // 			list.add(pays);
    // 		}
    //  	 		if(!payrsp.getEntries().equals("")){

    //                 int num =Integer.parseInt(payrsp.getEntries());
    //                 int s = 1;
    //                 for(Pays pays5:list){
    //                	 if(s>num)
    //                		 list2.add(pays5);
    //                	 s++;
    //                 }
    // 	 		}
    PaysData paysdata = new PaysData();
    paysdata.setStatus("success");
    for (Pays pays4 : list2) {
      list.remove(pays4);
    }
    if (!payrsp.getEntries().equals("")) {

      int num = Integer.parseInt(payrsp.getEntries());
      int s = 1;
      for (Pays pays5 : list) {
        if (s > num)
          list2.add(pays5);
        s++;
      }
    }
    for (Pays pays5 : list2) {
      list.remove(pays5);
    }
    paysdata.setData(list);
    String rsp = gson.toJson(paysdata);

    out.print(rsp);
  } catch (Exception e) {
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