<%@page import="com.system.server.xy.FeeServer"%>
<%@page import="com.system.server.xy.UserServer"%>
<%@page import="com.system.util.StringUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	int id = StringUtil.getInteger(request.getParameter("id"), 0);
	int dataRows = StringUtil.getInteger(request.getParameter("datarows"), 0);
	int type = StringUtil.getInteger(request.getParameter("type"), 0);
	double dataFee = StringUtil.getDouble(request.getParameter("datafee"), 0);
	
	if(id==0)
	{
		out.print("NO");
		return;
	}
	
	boolean result = false;
	
	if(type==0)
		result = new UserServer().updateQdData(id, dataRows);
	
	if(type==1)
		result = new FeeServer().updateQdFee(id,dataRows);
	
	if(type==2){
		result = new UserServer().updateQdData(id, dataFee);
	}
	
	out.print(result ? "OK" : "NO");
	
 %>



