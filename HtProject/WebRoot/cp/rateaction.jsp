<%@page import="com.system.server.CpSpTroneRateServer"%>
<%@page import="com.system.server.CpJieSuanLvServer"%>
<%@page import="com.system.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int type = StringUtil.getInteger(request.getParameter("type"), -1);

	if(type==1)
	{
		int id = StringUtil.getInteger(request.getParameter("id"), -1);
		float rate = StringUtil.getFloat(request.getParameter("rate"), 0);
		new CpSpTroneRateServer().updateCpSpTroneRate(id, rate);
		out.println("OK");
	}

%>