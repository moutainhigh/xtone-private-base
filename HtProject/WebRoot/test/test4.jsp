<%@page import="com.system.util.StringUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.system.util.model.Retention"%>
<%@page import="com.system.util.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.system.util.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String type = StringUtil.getString(request.getParameter("type"), "DAY1");
	String defaultStartDate = StringUtil.getPreDayOfMonth2();/* 获取之前一个随机的时间 */
	String defaultEndDate = StringUtil.getMonthEndDate2();    /* 获得当前系统时间 */
	String startDate = StringUtil
			.getString(request.getParameter("startdate"), defaultStartDate);  /* 获取用户输入开始时间 */
	String endDate = StringUtil
			.getString(request.getParameter("enddate"), defaultEndDate);  /* 获取用户输入的结束时间 */
	startDate = StringUtil.dateTimeToDateTime(startDate);
	endDate = StringUtil.dateTimeToDateTime(endDate);
	Redirect result = new Redirect();
	String str = result.sendGet("http://gaopen.talkingdata.net/game/product/list", "token=3d5663eacbf64808ae89cd7a5077293f");
	List<Product> list2 = result.gsonToProduct(str);
	List<Retention> list = new ArrayList<Retention>();
	for(Product model : list2){
		str = result.sendGet("http://gaopen.talkingdata.net/game/retention/list", 
				"token=3d5663eacbf64808ae89cd7a5077293f&productId="+model.getProductId()+"&"+
				"startTime="+startDate+"&endTime="+endDate+"&retentionDay="+type);
		List<Retention> templist = result.gsonToRetention(str);
		for(Retention partner : templist){
			list.add(partner);
		}
		Thread.sleep(5000);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>翔通运营管理平台</title>
    <link href="../wel_data/right.css" rel="stylesheet" type="text/css">
    <link href="../wel_data/gray.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../sysjs/jquery-1.7.js"></script>
    <script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
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
				<form action="test4.jsp"  method="post" id="formid">
				<dl>
						<dd class="dd01_me" style="width:80px;">开始日期</dd>
						<dd class="dd03_me" style="width:100px;">
						<input name="startdate" type="text" value="<%=startDate%>"
							onclick="WdatePicker({isShowClear:false,readOnly:true})">
						</dd>
						<dd class="dd01_me" style="width:80px;">结束日期</dd>
						<dd class="dd03_me" style="width:100px;">
							<input name="enddate" type="text" value="<%=endDate%>"
							onclick="WdatePicker({isShowClear:false,readOnly:true})">
						</dd>
						<dd class="dd01_me">类型</dd>
					<dd class="dd04_me">
						<select name="type" id="type" style="width: 150px;" title="选择SP" >
							<option value="DAY1">请选择样本类型</option>
							<option value="DAY1">次</option>	
							<option value="DAY7">7</option>
							<option value="DAY30">30</option>
						</select>
					</dd>
					<dd class="ddbtn" style="margin-left: 10px; margin-top: 0px;">
						<input class="btn_match" name="search" value="查 询" type="submit" >
					</dd>
				</dl>
			</form>
			</dl>
		</div>
		<table cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td>序号</td>
					<td>item</td>
					<td>指标</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<%
					int rowNum = 1;
					for (Retention model : list)
					
					{
						
				%>
				<tr>
					<td><%=rowNum++%></td>
					<td><%=model.getItem()%></td>
					<td><%=model.getValue()%></td>
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