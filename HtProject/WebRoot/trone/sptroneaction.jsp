<%@page import="com.system.util.PageUtil"%>
<%@page import="com.system.server.SpTroneServer"%>
<%@page import="com.system.model.SpTroneModel"%>
<%@page import="com.system.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String query = request.getQueryString();
	query = PageUtil.queryFilter(query, "id");
	int delId = StringUtil.getInteger(request.getParameter("did"), -1);
	if(delId>0)
	{
		new SpTroneServer().delSpTrone(delId);
		response.sendRedirect("sptrone.jsp");
		return;
	}

	int id = StringUtil.getInteger(request.getParameter("id"), -1);
	int spId = StringUtil.getInteger(request.getParameter("sp_id_1"), -1);
	int operator = StringUtil.getInteger(request.getParameter("operator"), -1);	
	String spTroneName = StringUtil.getString(request.getParameter("sp_trone_name_1"), "");
	float jieSuanLv = StringUtil.getFloat(request.getParameter("jiesuanlv"), 0.0F);
	String provinces = StringUtil.mergerStrings(request.getParameterValues("area[]"), ",");
	int troneType = StringUtil.getInteger(request.getParameter("trone_type"), 0);
	
	SpTroneModel model = new SpTroneModel();
	model.setSpId(spId);
	model.setOperator(operator);
	model.setSpTroneName(spTroneName);
	model.setJieSuanLv(jieSuanLv);
	model.setProvinces(provinces);
	model.setId(id);
	model.setTroneType(troneType);
	
	if(id==-1)
		new SpTroneServer().addSpTrone(model); 
	else 
		new SpTroneServer().updateSpTrone(model);
	
	response.sendRedirect("sptrone.jsp?" + query);
%>
