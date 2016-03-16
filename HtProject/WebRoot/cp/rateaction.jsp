<%@page import="com.system.server.CpJieSuanLvServer"%>
<%@page import="com.system.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int type = StringUtil.getInteger(request.getParameter("type"), -1);

	if(type==1)
	{
		int id = StringUtil.getInteger(request.getParameter("id"), -1);
		float value = StringUtil.getFloat(request.getParameter("value"), 0);
		new CpJieSuanLvServer().updateJieSuandLv(id, value);
		out.println("OK");
	}

%>