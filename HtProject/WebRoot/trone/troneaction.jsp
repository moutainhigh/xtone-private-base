<%@page import="com.system.util.Base64UTF"%>
<%@page import="com.system.util.PageUtil"%>
<%@page import="com.system.model.TroneModel"%>
<%@page import="com.system.server.TroneServer"%>
<%@page import="com.system.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int delId = StringUtil.getInteger(request.getParameter("did"), -1);

	String query = StringUtil.getString(request.getParameter("query"), "");

	if(delId>0)
	{
		new TroneServer().deleteTrone(delId);
		response.sendRedirect("trone.jsp");
		return;
	}

	int id = StringUtil.getInteger(request.getParameter("id"), -1);
	int spId = StringUtil.getInteger(request.getParameter("sp_id"), -1);
	int spTroneId = StringUtil.getInteger(request.getParameter("sp_trone_id"), -1);	
	int spApiUrlId = StringUtil.getInteger(request.getParameter("api_url_id"), -1);	
	String troneOrder = StringUtil.getString(request.getParameter("trone_order"), "");
	String troneNum = StringUtil.getString(request.getParameter("trone_num"), "");
	String troneName = StringUtil.getString(request.getParameter("trone_name"), "");
	float price = StringUtil.getFloat(request.getParameter("price"), 0.0F);
	int dynamic = StringUtil.getInteger(request.getParameter("dynamic"), -1);
	int matchPrice = StringUtil.getInteger(request.getParameter("match_price"), -1);
	
	int status = StringUtil.getInteger(StringUtil.mergerStrings(request.getParameterValues("status"), ","),0);
	
	TroneModel model = new TroneModel();
	model.setSpId(spId);
	model.setSpTroneId(spTroneId);
	model.setSpApiUrlId(spApiUrlId);
	model.setTroneNum(troneNum);
	model.setOrders(troneOrder);
	model.setPrice(price);
	model.setStatus(status);
	model.setDynamic(dynamic);
	model.setMatchPrice(matchPrice);
	model.setTroneName(troneName);
	
	model.setId(id);
	
	if(id==-1)
		new TroneServer().addTrone(model); 
	else 
		new TroneServer().updateTrone(model);
	
	response.sendRedirect("trone.jsp?" + Base64UTF.decode(query));
%>
