<%@page import="com.system.server.SpTroneApiServer"%>
<%@page import="com.system.model.SpTroneApiModel"%>
<%@page import="com.system.util.Base64UTF"%>
<%@page import="com.system.util.PageUtil"%>
<%@page import="com.system.server.SpTroneServer"%>
<%@page import="com.system.model.SpTroneModel"%>
<%@page import="com.system.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String query = Base64UTF.decode(StringUtil.getString(request.getParameter("query"), ""));
	
	int id = StringUtil.getInteger(request.getParameter("id"), -1);

	String spTroneApiName = StringUtil.getString(request.getParameter("sp_trone_api_name"), "");
	String apiFields = StringUtil.mergerStrings(request.getParameterValues("api_fields"), ",");
	int locateMatch = StringUtil.getInteger(request.getParameter("locate_match"), 0);
	int matchFiles = StringUtil.getInteger(request.getParameter("match_field"), 0);
	String matchKeyWord = StringUtil.getString(request.getParameter("match_keyword"), "");
	
	float dayLimit = StringUtil.getFloat(request.getParameter("day_limit"), 0);
	float monthLimit = StringUtil.getFloat(request.getParameter("month_limit"), 0);
	float userDayLimit = StringUtil.getFloat(request.getParameter("user_day_limit"), 0);
	float userMonthLimit = StringUtil.getFloat(request.getParameter("user_month_limit"), 0);
	
	SpTroneApiModel model = new SpTroneApiModel();
	
	model.setId(id);
	model.setName(spTroneApiName);
	model.setApiFields(apiFields);
	model.setLocateMatch(locateMatch);
	model.setMatchField(matchFiles);
	model.setMatchKeyword(matchKeyWord);
	model.setDayLimit(dayLimit);
	model.setMonthLimit(monthLimit);
	model.setUserDayLimit(userDayLimit);
	model.setUserMonthLimit(userMonthLimit);
	
	if(id>0)
	{
		new SpTroneApiServer().updateSpTroneApiModel(model);
	}
	else
	{
		new SpTroneApiServer().addSpTroneApiModel(model);
	}
	
	response.sendRedirect("sptroneapi.jsp?" + query);
%>
