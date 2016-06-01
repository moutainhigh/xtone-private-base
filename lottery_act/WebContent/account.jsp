<%@page import="com.system.server.AccountServer"%>
<%@page import="com.system.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = StringUtil.getString(request.getParameter("name"), "");
	out.print(new AccountServer().handleAccount(name));
%>