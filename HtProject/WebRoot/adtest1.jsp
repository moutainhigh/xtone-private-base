<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.MalformedURLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	try{
		URL url = new URL("http://gaopen.talkingdata.net/game/product/list");
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		//发送域消息
		OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream(),"8859_1");
		os.write("token=3d5663eacbf64808ae89cd7a5077293f");
		os.flush();
		os.close();
		//获取返回数据
		InputStream is = conn.getInputStream();
		while(is!=null){
			System.out.println(is.toString());
		}
	}catch (MalformedURLException   e) {
		
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional
//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>