<%@page import="com.system.constant.Constant"%>
<%@page import="com.system.util.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.system.util.Redirect"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.MalformedURLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Redirect result = new Redirect();
	String str = result.sendGet("http://gaopen.talkingdata.net/game/product/list", "token=3d5663eacbf64808ae89cd7a5077293f");
	List<Product> list = result.gsonToProduct(str);		
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>翔通运营管理平台</title>
    <link href="../wel_data/right.css" rel="stylesheet" type="text/css">
    <link href="../wel_data/gray.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../sysjs/jquery-1.7.js"></script>
    <script type="text/javascript">
	
	  function delTrone(id)
	  {
		  if(confirm('真的要删除吗？'))
		  {
			  window.location.href = "appaction.jsp?id=" + id+"&type=1";	
		  }
	  }
	
    </script>
  </head>
  <body>
    <div class="main_content">
		<div class="content" >
			<dl>
				<dd class="ddbtn" ><a href="appadd.jsp?userid=">增  加</a></dd>
				<form action="app.jsp"  method="post" id="formid">
				<dl>
<!-- 					<dd class="dd01_me">应用名</dd> -->
<!-- 					<dd class="dd03_me"> -->
<%-- 						<input name="appname" id="input_appkey" value="<%=appname %>" type="text" style="width: 150px"> --%>
<!-- 					</dd> -->
<!-- 					<dd class="dd01_me">应用KEY</dd> -->
<!-- 					<dd class="dd03_me"> -->
<%-- 						<input name="appkey" id="input_appkey" value="<%=appkey %>" type="text" style="width: 150px"> --%>
<!-- 					</dd> -->
<!-- 					<dd class="ddbtn" style="margin-left: 10px; margin-top: 0px;"> -->
<!-- 						<input class="btn_match" name="search" value="查 询" type="submit" > -->
<!-- 					</dd> -->
				</dl>
			</form>
			</dl>
		</div>
		<table cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td>序号</td>
					<td>产品ID</td>
					<td>AppId</td>
					<td>产品名称</td>
					<td>创建者ID</td>
					<td>产品类型ID</td>
					<td>产品类型名</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<%
					int rowNum = 1;
					for (Product model : list)
					
					{
						
				%>
				<tr>
					<td><%=rowNum++%></td>
					<td><%=model.getProductId()%></td>
					<td><%=model.getSequenceNumber()%></td>
					<td><%=model.getProductName()%></td>
					<td><%=model.getCreatorId()%></td>
					<td><%=model.getProductType()%></td>
					<td><%=model.getProductTypeName()%></td>
					<td>
						<a href="appedit.jsp?id=
						&pageindex=">修改</a>
						<a onclick="delTrone()">删除</a>
					</td>
				</tr>
				<%
					}
				%>
				
			<tbody>
				<tr>
					<td colspan="6" class="tfooter" style="text-align: center;"></td>
				</tr>
			</tbody>
		</table>
	</div>
  </body>
</html>