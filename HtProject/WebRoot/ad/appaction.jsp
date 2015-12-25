<%@page import="com.system.server.AdAppServer"%>
<%@page import="com.system.model.AdAppModel"%>
<%@page import="com.system.server.AppServer"%>
<%@page import="com.system.model.AppModel"%>
<%@page import="com.system.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int pageIndex = StringUtil.getInteger(request.getParameter("pageindex"), 1);
	int id = StringUtil.getInteger(request.getParameter("id"), -1);
	int type = StringUtil.getInteger(request.getParameter("type"), 0);
	String appname = StringUtil.getString(request.getParameter("app_name"), "");
	String appkey = StringUtil.getString(request.getParameter("app_key"), "");
	int hold_percent = StringUtil.getInteger(request.getParameter("hold_persent"), 0);
	int userid = StringUtil.getInteger(request.getParameter("userid"), -1);
	
	
	
	AdAppModel model = new AdAppModel();
	model.setId(id);
	model.setAppname(appname);
	model.setAppkey(appkey);
	model.setHold_percent(hold_percent);
	
	if(id>0)
	{
		new AdAppServer().updataApp(model);
	}
	else
	{
		model.setUser_id(userid);
		new AdAppServer().addApp(model);
	}
	
	if(type>0){
    	if(id>0){
    		new AdAppServer().deletApp(id);
    		response.sendRedirect("app.jsp");
    		return;
    	}
    	response.sendRedirect("app.jsp");
    }
	
	response.sendRedirect("app.jsp?pageindex="+pageIndex);
	
%>
