<%@page import="java.util.ArrayList"%>
<%@page import="com.system.server.MrDetailServer"%>
<%@page import="com.system.vo.DetailDataVo"%>
<%@page import="java.util.List"%>
<%@page import="com.system.util.StringUtil"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	
	String defaultMonth = sdf.format(new Date());
					
	String keyWord = StringUtil.getString(request.getParameter("keyword"), "");
	
	String month = StringUtil.getString(request.getParameter("month"),defaultMonth);
	
	List<DetailDataVo> list = null;
	
	if(!StringUtil.isNullOrEmpty(keyWord))
	{
		list = new MrDetailServer().loadDetailDataByPhoneMsg(keyWord, month);
	}
	
	if(list==null)
		list = new ArrayList<DetailDataVo>();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>翔通运营管理平台</title>
<link href="../wel_data/right.css" rel="stylesheet" type="text/css">
<link href="../wel_data/gray.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../sysjs/jquery-1.7.js"></script>
<script type="text/javascript" src="../sysjs/base.js"></script>
<script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	
	$(function()
	{
		$("#input_keyword").val(<%= keyWord %>);
	});
	
</script>
<body>
	<div class="main_content">
		<div class="content" >
			<form action="detail.jsp"  method="get" style="margin-top: 10px">
				<dl>
					<dd class="dd01_me">月份</dd>
					<dd class="dd03_me">
						<input name="month"  type="text" value="<%=month%>" 
							onclick="WdatePicker({ dateFmt: 'yyyyMM', isShowToday:false, isShowClear:false,readOnly:true })" style="width: 100px;">
					</dd>
					<dd class="dd01_me">关键字</dd>
					<dd class="dd03_me">
						<input name="keyword" type="text" value="<%= keyWord %>" />
					</dd>
					<dd class="ddbtn" style="margin-left: 10px; margin-top: 0px;">
						<input class="btn_match" name="search" value="查 询" type="submit" />
					</dd>
				</dl>
			</form>
		</div>
		<table cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td>序号</td>
					<td>IMEI</td>
					<td>IMSI</td>
					<td>PHONE</td>
					<td>时间</td>
					<td>省份</td>
					<td>城市</td>
					<td>SP</td>
					<td>业务</td>
					<td>价格</td>
					<td>CP</td>
					<td>同步</td>
					
				</tr>
			</thead>
			<tbody>		
				<%
					int index = 1;
					for(DetailDataVo model : list)
					{
						%>
				<tr>
					<td><%= index++ %></td>
					<td><%= model.getImei() %></td>
					<td><%= model.getImsi() %></td>
					<td><%= model.getMobile()  %></td>
					<td><%= model.getCreateDate() %></td>
					<td><%= model.getProvinceName() %></td>
					<td><%= model.getCityName() %></td>
					<td><%= model.getSpName() %></td>
					<td><%= model.getSpTroneName() %></td>
					<td><%= model.getPrice() %></td>
					<td><%= model.getCpName() %></td>
					<td><%= model.getSynFlag()==1 ? "已同步" : "未同步" %></td>
				</tr>
						<%
					}
				%>
			<tbody>
		</table>
	</div>
	
</body>
</html>