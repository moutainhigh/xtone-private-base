<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.system.cache.CacheConfigMgr"%>
<%@page import="com.system.util.ServiceUtil"%>
<%@page import="com.system.model.ApiOrderModel"%>
<%@page import="com.system.server.RequestServerV1"%>
<%@page import="com.system.util.StringUtil"%>
<%@page import="com.system.model.BaseRequestModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	out.print("恭喜，贺喜，刷新成功！[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]");
	
	CacheConfigMgr.refreshAllCache();
%>