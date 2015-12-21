<%@page import="com.system.util.PageUtil"%>
<%@page import="com.system.server.SpTroneServer"%>
<%@page import="com.system.model.SpTroneModel"%>
<%@page import="com.system.model.ProvinceModel"%>
<%@page import="com.system.server.ProvinceServer"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.system.model.SettleAccountModel"%>
<%@page import="java.util.Map"%>
<%@page import="com.system.server.SettleAccountServer"%>
<%@page import="com.system.server.SpServer"%>
<%@page import="com.system.model.SpModel"%>
<%@page import="java.util.List"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="com.system.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int spTroneId = StringUtil.getInteger(request.getParameter("id"), -1);
	SpTroneModel spTroneModel = new SpTroneServer().loadSpTroneById(spTroneId);
	String query = request.getQueryString();
	query = PageUtil.queryFilter(query, "id");
	if(spTroneModel==null)
	{
		response.sendRedirect("sptrone.jsp?" + query);
		return;
	}
	List<SpModel> spList = new SpServer().loadSp();
	List<ProvinceModel> provinceList = new ProvinceServer().loadProvince();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>翔通运营管理平台</title>
<link href="../wel_data/right.css" rel="stylesheet" type="text/css">
<link href="../wel_data/gray.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../sysjs/jquery-1.7.js"></script>
<script type="text/javascript" src="../sysjs/base.js"></script>
<script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

	function subForm() 
	{
		if ($("#sel_sp").val() == "-1") {
			alert("请选择SP");
			$("#sel_sp").focus();
			return;
		}
		
		if ($("#sel_operator").val() == "-1") {
			alert("请选择运营商");
			$("#sel_operator").focus();
			return;
		}
		
		if ($("#input_sp_trone_name").val() == "") {
			alert("请输入业务名称");
			$("#input_sp_trone_name").focus();
			return;
		}
		
		var jiesuanlv = $("#input_jiesuanlv").val();
		
		if (jiesuanlv == "") {
			alert("请输入结算率");
			$("#input_jesuanlv").focus();
			return;
		}
		
		if (isNum(jiesuanlv))
		{
			alert("结算率不正确");
			$("#input_jesuanlv").focus();
			return;
		}
		
		if(getProvinceCount('area[]')<=0)
		{
			alert("请选择省份");
			return;
		}

		document.getElementById("addform").submit();
	}
	
	$(function() 
	{
		resetForm();
	});
	
	function resetForm()
	{
		$("#sel_sp").val("<%= spTroneModel.getSpId() %>");
		$("#sel_operator").val("<%= spTroneModel.getOperator() %>");
		$("#input_sp_trone_name").val("<%= spTroneModel.getSpTroneName() %>");
		$("#input_jiesuanlv").val("<%= spTroneModel.getJieSuanLv() %>");
		var provinceIds = "<%= spTroneModel.getProvinces() %>";
		var provinces = provinceIds.split(",");
		setRadioCheck("trone_type",<%= spTroneModel.getTroneType() %>);
		unAllCkb();
		$('[name=area[]]:checkbox').each(function() {
			
				for(k=0; k<provinces.length; k++)
				{
					if(provinces[k]==this.value)
					{
						this.checked = true;
						break;
					}
				}
		});
	}
	
	function getProvinceCount(items)
	{
		var i = 0;
		$('[name=' + items + ']:checkbox').each(function() {
			if(this.checked)
				i++;
		});
		return i;
	}
	
	//声明整数的正则表达式
	function isNum(a)
	{
		var reg=/^(([a-z]+[0-9]+)|([0-9]+[a-z]+))[a-z0-9]*$/i;
		return reg.test(a);
	}

	function allCkb(items) {
		$('[name=' + items + ']:checkbox').attr("checked", true);
	}

	function unAllCkb() {
		$('[type=checkbox]:checkbox').attr('checked', false);
	}

	function inverseCkb(items) {
		$('[name=' + items + ']:checkbox').each(function() {
			this.checked = !this.checked;
		});
	}
</script>
<body>
	<div class="main_content">
		<div class="content" style="margin-top: 10px">
			<dl>
				<form action="sptroneaction.jsp?<%= query %>" method="post" id="addform">
					<table>
						<thead>
							<td style="text-align: left">修改SP业务</td>
							<input type="hidden" value="<%= spTroneModel.getId() %>" name="id" />
						</thead>
					</table>
					<dd class="dd01_me">SP名称</dd>
					<dd class="dd04_me">
						<select name="sp_id_1" id="sel_sp" title="选择SP" style="width: 200px">
							<option value="-1">请选择SP名称</option>
							<%
								for (SpModel sp : spList)
								{
							%>
							<option value="<%=sp.getId()%>"><%=sp.getShortName()%></option>
							<%
								}
							%>
						</select>
					</dd>

					<br />
					<br />
					<br />
					<dd class="dd00_me"></dd>
					<dd class="dd01_me">运营商</dd>
					<dd class="dd04_me">
						<select name="operator" id="sel_operator" title="选择运营商"
							style="width: 200px">
							<option value="-1">请选择</option>
							<option value="1">联通</option>
							<option value="2">电信</option>
							<option value="3">移动</option>
						</select>
					</dd>

					<br />
					<br />
					<br />
					<dd class="dd00_me"></dd>
					<dd class="dd01_me">业务名称</dd>
					<dd class="dd03_me">
						<input type="text" name="sp_trone_name_1" title="业务名称" id="input_sp_trone_name"
							style="width: 200px">
					</dd>
					
					<br />
					<br />
					<br />
					<dd class="dd00_me"></dd>
					<dd class="dd01_me">业务类型</dd>
					<dd class="dd03_me">
						<input type="radio" name="trone_type" style="width: 35px;float:left" value="0" checked="checked" >
						<label style="font-size: 14px;float:left">普通</label>
						<input type="radio" name="trone_type" style="width: 35px;float:left" value="1" >
						<label style="font-size: 14px;float:left">包月</label>
						<input type="radio" name="trone_type" style="width: 35px;float:left" value="2" >
						<label style="font-size: 14px;float:left">IVR</label>
					</dd>

					<br />
					<br />
					<br />
					<dd class="dd00_me"></dd>
					<dd class="dd01_me">结算率</dd>
					<dd class="dd03_me">
						<input type="text" name="jiesuanlv" title="结算率" id="input_jiesuanlv"
							style="width: 200px">
					</dd>

					<br />
					<br />
					<br />
					<dd class="dd00_me"></dd>
					<dd class="dd01_me">省份</dd>
					<div style="margin-left: 95px; width: 580px;" id="pro">

						<%
							for (ProvinceModel province : provinceList)
							{
						%>
						<dd class="dd01"><%=province.getName()%>
							<input style="" type="checkbox" class="chpro" name="area[]"
								value="<%=province.getId()%>">
						</dd>
						<%
							}
						%>
						<input type="button" onclick="allCkb('area[]')"
							style="horve: point;" value="全　选" /> <input type="button"
							onclick="unAllCkb()" style="padding-top: 10px;" value="全不选" /> <input
							type="button" onclick="inverseCkb('area[]')"
							style="padding-top: 10px;" value="反　选" />
					</div>

					<br />
					<br />
					<br />
					<dd class="dd00"></dd>
					<dd class="dd00_me"></dd>
					<dd class="ddbtn" style="margin-left: 100px; margin-top: 10px">
						<input type="button" value="提 交" onclick="subForm()">
					</dd>
					<dd class="ddbtn" style="margin-left: 32px; margin-top: 10px">
						<input type="button" value="重 置" onclick="resetForm()">
					</dd>
					<dd class="ddbtn" style="margin-left: 32px; margin-top: 10px">
						<input type="button" value="返 回" onclick="history.go(-1)">
					</dd>
				</form>
			</dl>
		</div>

	</div>
</body>
</html>