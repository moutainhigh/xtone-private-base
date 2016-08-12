<%@page import="com.system.server.ActivityServer"%>
<%@page import="com.system.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String email = StringUtil.getString(request.getParameter("email"), "");
	out.print(new ActivityServer().handleActivity(email));
%>