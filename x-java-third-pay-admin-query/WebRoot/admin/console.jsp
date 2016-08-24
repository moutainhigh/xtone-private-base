<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
  /* response.sendRedirect("stat-all.jsp"); */
  String path = request.getRequestURI();
  path = path.substring(0, path.lastIndexOf("/"));
  System.out.print(path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../js-css/jquery-1.7.js"></script>
<title></title>
</head>
<body>
</body>
<script language="javascript" type="text/javascript">
	$(function(){
		window.location = "<%=path%>/daily.jsp";
	});    
</script>
</html>