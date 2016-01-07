<%@page import="com.system.server.AppServer"%>
<%@page import="com.system.model.AppModel"%>
<%@page import="com.system.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	int pageIndex = StringUtil.getInteger(request.getParameter("pageindex"), 1);
	int id = StringUtil.getInteger(request.getParameter("id"), -1);
	String appname = StringUtil.getString(request.getParameter("app_name"), "");
	String appkey = StringUtil.getString(request.getParameter("app_key"), "");
	int hold_percent = StringUtil.getInteger(request.getParameter("hold_persent"), 0);
	String remark = StringUtil.getString(request.getParameter("remark"), "");
	String query = request.getQueryString();
	
	if(remark.equals(""))
	{
		remark = "";
	}
	
	AppModel model = new AppModel();
	model.setId(id);
	model.setAppname(appname);
	model.setAppkey(appkey);
	model.setHold_percent(hold_percent);
	model.setRemark(remark);
	
	if(id>0)
	{
		new AppServer().updataApp(model);
	}
	else
	{
		new AppServer().addApp(model);
	}
	
	//response.sendRedirect("app.jsp?pageindex="+pageIndex+"&appname="+appname+"&appkey="+appkey);
	response.sendRedirect("app.jsp?"+query);
	
%>
