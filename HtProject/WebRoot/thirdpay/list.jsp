<%@page import="com.system.model.ThirdPayModel"%>
<%@page import="com.system.server.ThridPayServer"%>
<%@page import="java.util.ArrayList"%>
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
	String defaultEndDate = StringUtil.getMonthEndDate();
	
	String startDate = StringUtil
			.getString(request.getParameter("startdate"), defaultStartDate);
	String endDate = StringUtil
			.getString(request.getParameter("enddate"), defaultEndDate);
	
	int dataType = StringUtil.getInteger(request.getParameter("data_type"), -1);

	Map<String, Object> map =  new ThridPayServer().getThridPayData(startDate, endDate, dataType);
	
	List<SpModel> spList = new SpServer().loadSp();
	List<CpModel> cpList = new CpServer().loadCp();
	List<TroneModel> troneList = new TroneServer().loadTroneList();
	//List<TroneOrderModel> troneOrderList = new TroneOrderServer().loadTroneOrderList();
	
	List<TroneOrderModel> troneOrderList = new ArrayList();
	
	List<ProvinceModel> provinceList = new ProvinceServer().loadProvince();
	List<CityModel> cityList = new CityServer().loadCityList();
	List<SpTroneModel> spTroneList = new SpTroneServer().loadSpTroneList();
		
	List<ThirdPayModel> list = (List<ThirdPayModel>)map.get("list");
	
	int dataRows = (Integer)map.get("datarows");
	int showDataRows = (Integer)map.get("showdatarows");
	float amount = (Float)map.get("amount");
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
<script type="text/javascript" src="../sysjs/MapUtil.js"></script>
<script type="text/javascript" src="../sysjs/pinyin.js"></script>
<script type="text/javascript" src="../sysjs/AndyNamePicker.js"></script>
<script type="text/javascript">

	var spList = new Array();
	<%
	for(SpModel spModel : spList)
	{
		%>
		spList.push(new joSelOption(<%= spModel.getId() %>,1,'<%= spModel.getShortName() %>'));
		<%
	}
	%>
	
	var cpList = new Array();
	<%
	for(CpModel cpModel : cpList)
	{
		%>
		cpList.push(new joSelOption(<%= cpModel.getId() %>,1,'<%= cpModel.getShortName() %>'));
		<%
	}
	%>
	
	function onSpDataSelect(joData)
	{
		$("#sel_sp").val(joData.id);
		troneChange();
	}
	
	function onCpDataSelect(joData)
	{
		$("#sel_cp").val(joData.id);
		troneOrderChange();
	}

	function joCity(id,provinceId,name)
	{
		var obj = {};
		obj.id = id;
		obj.provinceId = provinceId;
		obj.name = name;
		return obj;
	}
	
	function joTrone(id,spId,troneName)
	{
		var obj = {};
		obj.id = id;
		obj.spId = spId;
		obj.troneName = troneName;
		return obj;
	}
	
	function joTroneOrder(id,cpId,troneOrderName)
	{
		var obj = {};
		obj.id = id;
		obj.cpId = cpId;
		obj.troneOrderName = troneOrderName;
		return obj;
	}

	var cityList = new Array();
	<%for(CityModel city : cityList){%>
	cityList.push(new joCity(<%= city.getId() %>,<%= city.getProvinceId() %>,'<%= city.getName() %>'));<%}%>
		
	var troneList = new Array();
	<%for(TroneModel trone : troneList){%>
	troneList.push(new joTrone(<%= trone.getId() %>,<%= trone.getSpId() %>,'<%= trone.getSpShortName() + "-" +trone.getTroneName() %>'));<%}%>
	
	var troneOrderList = new Array();
	<%for(TroneOrderModel troneOrder : troneOrderList){%>
	troneOrderList.push(new joTroneOrder(<%= troneOrder.getId() %>,<%= troneOrder.getCpId() %>,'<%= troneOrder.getCpShortName() + "-" +troneOrder.getOrderTroneName() %>'));<%}%>
	
	var spTroneArray = new Array();
	<%
	for(SpTroneModel spTroneModel : spTroneList)
	{
		%>
	spTroneArray.push(new joBaseObject(<%= spTroneModel.getId() %>,<%=spTroneModel.getSpId() %>,'<%= spTroneModel.getSpTroneName() %>'));	
		<%
	}
	%>
	
	$(function()
	{
		
		//$("#sel_data_type").val(<%= dataType %>);
		
	});
	
	
	var npSpTroneArray = new Array();
	
	<%
	for(SpTroneModel spTroneModel : spTroneList)
	{
		%>
		npSpTroneArray.push(new joSelOption(<%= spTroneModel.getId() %>,<%=spTroneModel.getSpId() %>,'<%= spTroneModel.getSpTroneName() %>'));	
		<%
	}
	%>
	
	function npSpTroneChange(jodata)
	{
		$("#sel_sp_trone").val(jodata.id);
	}
	
	function troneChange()
	{
		var spId = $("#sel_sp").val();
		
		$("#sel_sp_trone").empty(); 
		$("#sel_sp_trone").append("<option value='-1'>全部</option>");
		for(i=0; i<spTroneArray.length; i++)
		{
			if(spTroneArray[i].pid==spId || spId=="-1")
			{
				$("#sel_sp_trone").append("<option value='" + spTroneArray[i].id + "'>" + spTroneArray[i].name + "</option>");
			}
		}
		
		$("#sel_trone").empty(); 
		$("#sel_trone").append("<option value='-1'>全部</option>");
		for(i=0; i<troneList.length; i++)
		{
			if(troneList[i].spId==spId || spId=="-1")
			{
				$("#sel_trone").append("<option value='" + troneList[i].id + "'>" + troneList[i].troneName + "</option>");
			}
		}
	}
	
	function troneOrderChange()
	{
		var cpId = $("#sel_cp").val();
		$("#sel_trone_order").empty(); 
		$("#sel_trone_order").append("<option value='-1'>全部</option>");
		for(i=0; i<troneOrderList.length; i++)
		{
			if(troneOrderList[i].cpId==cpId || cpId=="-1")
			{
				$("#sel_trone_order").append("<option value='" + troneOrderList[i].id + "'>" + troneOrderList[i].troneOrderName + "</option>");
			}
		}
	}
	
	function provinceChange()
	{
		var provinceId = $("#sel_province").val();
		$("#sel_city").empty(); 
		$("#sel_city").append("<option value='-1'>全部</option>");
		for(i=0; i<cityList.length; i++)
		{
			if(cityList[i].provinceId==provinceId || provinceId=="-1")
			{
				$("#sel_city").append("<option value='" + cityList[i].id + "'>" + cityList[i].name + "</option>");
			}
		}
	}
	
	
</script>
<body>
	<div class="main_content">
		<div class="content" >
			<form action="list.jsp"  method="get" style="margin-top: 10px">
				<dl>
					<dd class="dd01_me">开始日期</dd>
					<dd class="dd03_me">
						<input name="startdate"  type="text" value="<%=startDate%>" 
							onclick="WdatePicker({isShowClear:false,readOnly:true})" style="width: 100px;">
					</dd>
					<dd class="dd01_me">结束日期</dd>
					<dd class="dd03_me">
						<input name="enddate" type="text" value="<%=endDate%>" 
							onclick="WdatePicker({isShowClear:false,readOnly:true})" style="width: 100px;">
					</dd>
					<dd class="dd01_me">支付类型</dd>
						<dd class="dd04_me">
						<select name="data_type" id="sel_data_type" style="width: 100px;">
							<option value="-1">全部</option>
							<option value="0">支付宝</option>
							<option value="1">银联</option>
							<option value="2">微信</option>
						</select>
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
					<td>时间</td>
					<td>价格(分)</td>
					<td>订单号</td>
					<td>支付类型</td>
					<td>Ip地址</td>
					<td>发行通道ID</td>
					<td>AppKey</td>
				</tr>
			</thead>
			
			<tbody>		
				<%
					int index = 1;
					for(ThirdPayModel model : list)
					{
						%>
				<tr>
				<td><%= index++ %></td>
					<td><%= model.getTimeId() %></td>
					<td><%= model.getPrice() %></td>
					<td><%= model.getPayChannelOrderId() %></td>
					<td><%= model.getPayChannel() %></td>
					<td><%= model.getIp() %></td>
					<td><%= model.getReleaseChannel() %></td>
					<td><%= model.getAppKey() %></td>
				</tr>
						<%
					}
				%>
			<tbody>
				<tr>
					<td></td>
					<td></td>
					<td>总金额：<%= dataRows %></td>
				</tr>
			</tbody>
		</table>
	</div>
	
</body>
</html>