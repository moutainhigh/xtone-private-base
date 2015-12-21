<%@page import="com.system.model.UserModel"%>
<%@page import="com.system.server.SpTroneServer"%>
<%@page import="com.system.model.SpTroneModel"%>
<%@page import="com.system.server.CityServer"%>
<%@page import="com.system.model.CityModel"%>
<%@page import="com.system.server.ProvinceServer"%>
<%@page import="com.system.model.ProvinceModel"%>
<%@page import="com.system.server.TroneOrderServer"%>
<%@page import="com.system.model.TroneOrderModel"%>
<%@page import="com.system.server.TroneServer"%>
<%@page import="com.system.model.TroneModel"%>
<%@page import="com.system.model.CpModel"%>
<%@page import="com.system.server.CpServer"%>
<%@page import="com.system.server.SpServer"%>
<%@page import="com.system.model.SpModel"%>
<%@page import="com.system.server.MrServer"%>
<%@page import="com.system.model.MrReportModel"%>
<%@page import="com.system.constant.Constant"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.system.util.PageUtil"%>
<%@page import="java.util.Map"%>
<%@page import="com.system.server.xy.UserServer"%>
<%@page import="com.system.model.xy.XyUserModel"%>
<%@page import="java.util.List"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="com.system.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String defaultStartDate = StringUtil.getMonthHeadDate();
	String defaultEndDate = StringUtil.getDefaultDate();
	
	String startDate = StringUtil
			.getString(request.getParameter("startdate"), defaultStartDate);
	String endDate = StringUtil
			.getString(request.getParameter("enddate"), defaultEndDate);
	
	int sortType = StringUtil.getInteger(request.getParameter("sort_type"), 1);
	
	int spId = StringUtil.getInteger(request.getParameter("sp_id"), -1);
	int cpId = StringUtil.getInteger(request.getParameter("cp_id"), -1);
	int spTroneId = StringUtil.getInteger(request.getParameter("sp_trone"), -1);
	int troneId = StringUtil.getInteger(request.getParameter("trone"), -1);
	int troneOrderId = StringUtil.getInteger(request.getParameter("trone_order"), -1);
	int provinceId = StringUtil.getInteger(request.getParameter("province"), -1);
	int cityId = StringUtil.getInteger(request.getParameter("city"), -1);
	
	cpId = 47;
	
	int userId = ((UserModel)session.getAttribute("user")).getId();
	
	if(userId==154)
		cpId = 47;
	
	if(userId==173)
		cpId = 54;
	
	sortType = 1;

	Map<String, Object> map =  new MrServer().getMrData(startDate,endDate, spId,spTroneId, troneId, cpId, troneOrderId, provinceId, cityId, sortType);
		
	List<MrReportModel> list = (List<MrReportModel>)map.get("list");
	
	int showDataRows = (Integer)map.get("showdatarows");
	float showAmount = (Float)map.get("showamount");
	
	String[] titles = {"日期","周数","月份","SP","CP","通道","CP业务","省份","城市","SP业务"};
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
<body>
	<div class="main_content">
		<div class="content" >
			<form action="mr1.jsp"  method="get" style="margin-top: 10px">
				<dl>
					<dd class="dd01_me">开始日期</dd>
					<dd class="dd03_me">
						<input name="startdate"  type="text" value="<%=startDate%>" style="width: 140px;"
							onclick="WdatePicker({isShowClear:false,readOnly:true})">
					</dd>
					<dd class="dd01_me">结束日期</dd>
					<dd class="dd03_me">
						<input name="enddate" type="text" value="<%=endDate%>" style="width: 138px;"
							onclick="WdatePicker({isShowClear:false,readOnly:true})">
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
					<td><%= titles[sortType-1] %></td>
					<td>数据(条)</td>
					<td>金额(元)</td>
				</tr>
			</thead>
			<tbody>		
				<%
					int index = 1;
					for(MrReportModel model : list)
					{
						%>
				<tr>
					<td><%= index++ %></td>
					<td><%= model.getTitle1() %></td>
					<td><%= model.getShowDataRows() %></td>
					<td><%= model.getShowAmount() %></td>
				</tr>
						<%
					}
				%>
			<tbody>
				<tr>
					<td></td>
					<td></td>
					<td>总数据(条)：<%= showDataRows %></td>
					<td>总金额(元)：<%= showAmount %></td>
				</tr>
			</tbody>
		</table>
	</div>
	
</body>
</html>