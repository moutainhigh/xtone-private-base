<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+":29141//"+request.getServerName()+":"+request.getServerPort()+path+"/";
  String path = request.getRequestURI();
  path = path.substring(0, path.lastIndexOf("/"));
  System.out.print(path);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=path+"/login.jsp"%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. fjoewjfoewfo<br>
  </body>
</html>
