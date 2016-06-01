<%@page import="com.system.server.UserServer"%>
<%@page import="com.system.util.StringUtil"%>
<%@page import="com.system.model.UserModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserModel model = new UserModel();
	model.setEmail(StringUtil.getString(request.getParameter("email"), ""));
	model.setName(StringUtil.getString(request.getParameter("name"),""));
	model.setPwd(StringUtil.getString(request.getParameter("pwd"), ""));
	out.print(new UserServer().handleUserRegist(model));
%>