<%@page import="com.system.util.PinYinUtil"%>
<%@page import="java.util.LinkedHashMap"%>
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
	
	int sortType = StringUtil.getInteger(request.getParameter("sort_type"), 6);
	String spname = StringUtil.getString(request.getParameter("sp_trone_name2"), "");
	String cpname = StringUtil.getString(request.getParameter("cp_name"), "");
	int spId = StringUtil.getInteger(request.getParameter("nameid"), -1);
	int cpId = StringUtil.getInteger(request.getParameter("cp_nameid"), -1);
	int spTroneId = StringUtil.getInteger(request.getParameter("sp_trone"), -1);
	int troneId = StringUtil.getInteger(request.getParameter("trone"), -1);
	int troneOrderId = StringUtil.getInteger(request.getParameter("trone_order"), -1);
	int provinceId = StringUtil.getInteger(request.getParameter("province"), -1);
	int cityId = StringUtil.getInteger(request.getParameter("city"), -1);

	Map<String, Object> map =  new MrServer().getMrTodayData(spId, spTroneId,troneId, cpId, troneOrderId, provinceId, cityId, sortType);
	
	List<SpModel> spList = new SpServer().loadSp();
	/****************   SP  ********************/
	Map<Integer,String> aToG = new LinkedHashMap<Integer,String>();
	Map<Integer,String> hToL = new LinkedHashMap<Integer,String>();
	Map<Integer,String> mToT = new LinkedHashMap<Integer,String>();
	Map<Integer,String> wToZ = new LinkedHashMap<Integer,String>();
	PinYinUtil util = new PinYinUtil();
	String str = "";
	int temp;
	for(SpModel model : spList){
		str = util.getPinYinHeadChar(model.getShortName().substring(0,1));
		temp = StringUtil.getInteger(StringUtil.stringToAscii(str), 0);
		
		if(temp>64&&temp<=71){
			aToG.put(model.getId(), model.getShortName());
		}else if(temp>71&&temp<=76){
			hToL.put(model.getId(), model.getShortName());
		}else if(temp>76&&temp<=84){
			mToT.put(model.getId(), model.getShortName());
		}else if(temp>84&&temp<=90){
			wToZ.put(model.getId(), model.getShortName());
		}
	}
	/****************   SP  ********************/
	
	List<CpModel> cpList = new CpServer().loadCp();
	/****************   CP  ********************/
	Map<Integer,String> aToG2 = new LinkedHashMap<Integer,String>();
	Map<Integer,String> hToL2 = new LinkedHashMap<Integer,String>();
	Map<Integer,String> mToT2 = new LinkedHashMap<Integer,String>();
	Map<Integer,String> wToZ2 = new LinkedHashMap<Integer,String>();
	util = new PinYinUtil();
	str = "";
	for(CpModel model : cpList){
		str = util.getPinYinHeadChar(model.getShortName().substring(0,1));
		temp = StringUtil.getInteger(StringUtil.stringToAscii(str), 0);
		
		if(temp>64&&temp<=71){
			aToG2.put(model.getId(), model.getShortName());
		}else if(temp>71&&temp<=76){
			hToL2.put(model.getId(), model.getShortName());
		}else if(temp>76&&temp<=84){
			mToT2.put(model.getId(), model.getShortName());
		}else if(temp>84&&temp<=90){
			wToZ2.put(model.getId(), model.getShortName());
		}
	}
	/****************   CP  ********************/
	List<TroneModel> troneList = new TroneServer().loadTroneList();
	//List<TroneOrderModel> troneOrderList = new TroneOrderServer().loadTroneOrderList();
	
	List<TroneOrderModel> troneOrderList = new ArrayList();
	
	List<ProvinceModel> provinceList = new ProvinceServer().loadProvince();
	List<CityModel> cityList = new CityServer().loadCityList();
	List<SpTroneModel> spTroneList = new SpTroneServer().loadSpTroneList();
		
	List<MrReportModel> list = (List<MrReportModel>)map.get("list");
	
	int dataRows = (Integer)map.get("datarows");
	int showDataRows = (Integer)map.get("showdatarows");
	float amount = (Float)map.get("amount");
	float showAmount = (Float)map.get("showamount");
	
	String[] titles = {"日期","周数","月份","SP","CP","通道","CP业务","省份","城市","SP业务","小时"};
	
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
		$("#sel_sort_type").val(<%= sortType %>);
		
		//SP的二级联动
		$("#nameid").val(<%= spId %>);
		$("#sp_trone_name").change(troneChange);
		troneChange();
		$("#sel_sp_trone").val(<%= spTroneId %>);
		$("#sel_trone").val(<%= troneId %>);
		
		//CP的二级联动
		$("#sel_cp").val(<%= cpId %>);	
		$("#cp_name").change(troneOrderChange);
		troneOrderChange();
		$("#sel_trone_order").val(<%= troneOrderId %>);
		
		//省份的二级联动
		$("#sel_province").val(<%= provinceId %>);
		$("#sel_province").change(provinceChange);
		provinceChange();		
		$("#sel_city").val(<%= cityId %>);
		
	});
	
	function troneChange()
	{
		if (isNullOrEmpty($("#sp_trone_name").val())) 
		{
			$("#nameid").val(-1);
		}
		var spId = $("#nameid").val();
		
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
		if (isNullOrEmpty($("#cp_name").val())) 
		{
			$("#cp_nameid").val(-1);
		}
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
	
	function SP()
	{
		var obj = $("#SPtrone");
		
		var ss = obj.parent().find('.search_form_suggest');
		
		//点击本身显示隐藏
	    
		if(obj.hasClass('boxSearchHover') )
	    {
	    	obj.removeClass('boxSearchHover');
	    	obj.children('.btn_search').removeClass('btn_search_current');
	    	obj.parent().find('#sp_search_form_suggest').hide();
	    }
	    else
	    {
	    	obj.addClass('boxSearchHover');
	    	obj.children('.btn_search').addClass('btn_search_current');
	    	obj.parent().find('#sp_search_form_suggest').show();
	    }
		obj.next().find('.clr_after a').on('click',function(){
	        
	    	$('#sp_trone_name').val($(this).text());
	    	//alert($(this).find('.nameid').val());
	    	$('#nameid').val($(this).find('.nameid').val());
	    	obj.removeClass('boxSearchHover');
	    	obj.children('.btn_search').removeClass('btn_search_current');
	    	obj.parent().find('#sp_search_form_suggest').hide();
	    	obj.find('span.key_word b').text($(this).text());
	    	troneChange();
	      });
	    //event.stopPropagation();
	    obj.next().find('.search_city_result a').click(function(){
	      
	    	obj.find('span.key_word b').text($(this).text());
	    });
	    
	}
	
	function CP(){
		var obj = $("#CPtrone");
		
		var ss = obj.parent().find('.search_form_suggest');
		
		//点击本身显示隐藏
	    
		if(obj.hasClass('boxSearchHover') )
	    {
	    	obj.removeClass('boxSearchHover');
	    	obj.children('.btn_search').removeClass('btn_search_current');
	    	obj.parent().find('#cp_search_form_suggest').hide();
	    }
	    else
	    {
	    	obj.addClass('boxSearchHover');
	    	obj.children('.btn_search').addClass('btn_search_current');
	    	obj.parent().find('#cp_search_form_suggest').show();
	    }
		obj.next().find('.clr_after a').on('click',function(){
	        
	    	$('#cp_name').val($(this).text());
	    	//alert($(this).find('.nameid').val());
	    	$('#cp_nameid').val($(this).find('.nameid').val());
	    	obj.removeClass('boxSearchHover');
	    	obj.children('.btn_search').removeClass('btn_search_current');
	    	obj.parent().find('#cp_search_form_suggest').hide();
	    	obj.find('span.key_word b').text($(this).text());
	    	troneOrderChange();
	      });
	    //event.stopPropagation();
	    obj.next().find('.search_city_result a').click(function(){
	      
	    	obj.find('span.key_word b').text($(this).text());
		
	    });
	    
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
			<form action="mr2.jsp"  method="post">
				<dl>
					<dd class="dd01_me"s >SP</dd>		
						<dd class="dd03_me" id="SPtrone">
							<input name="sp_trone_name2"  value="<%=spname %>" id="sp_trone_name"
								type="text" style="width: 150px" class="boxSearch" autocomplete="off" onclick="SP()" >
							<input type="hidden" id="nameid" name="nameid" >
						</dd>				
						<dd class="dd04_me">
							<div class="search_form_suggest" id="sp_search_form_suggest" style="display:none;top:25px;left:34px;">
								 <div class="thLeft thPadT5 tab_select">
						            <dl class="clrfix">
						              <dt>ABCDEFG</dt>
						              	<dd class="clr_after">
						              	<%
						              		for(Map.Entry entry : aToG.entrySet()){
						              	%>
							                <a href="#"><%=entry.getValue().toString() %><input type="hidden" value="<%=entry.getKey() %>" class="nameid"></a>
							                
							             <%
						              		}
							             %>
							             </dd>
						              </dl>
						             <dl class="clrfix">
						              <dt>HIJKL</dt>
						              <dd class="clr_after">
						                <%
						              		for(Map.Entry entry : hToL.entrySet()){
						              	%>
							                <a href="#"><%=entry.getValue().toString() %><input type="hidden" value="<%=entry.getKey() %>" class="nameid"></a>
							                
							             <%
						              		}
							             %>
						              </dd>
						            </dl>
						            <dl class="clrfix">
						              <dt>MNOPQRST</dt>
						              <dd class="clr_after">
						                <%
						              		for(Map.Entry entry : mToT.entrySet()){
						              	%>
							                <a href="#"><%=entry.getValue().toString() %><input type="hidden" value="<%=entry.getKey() %>" class="nameid"></a>
							                
							             <%
						              		}
							             %>
						              </dd>
						            </dl>
						            <dl class="clrfix">
						              <dt>WSYZ</dt>
						              <dd class="clr_after">
						                <%
						              		for(Map.Entry entry : wToZ.entrySet()){
						              	%>
							                <a href="#"><%=entry.getValue().toString() %><input type="hidden" value="<%=entry.getKey() %>" class="nameid"></a>
							                
							             <%
						              		}
							             %>
						              </dd>
						            </dl>
						            </div>
							</div>
						</dd>
					<dd class="dd01_me">SP业务</dd>
						<dd class="dd04_me">
						<select name="sp_trone" id="sel_sp_trone" ></select>
					</dd>
					<dd class="dd01_me">SP通道</dd>
						<dd class="dd04_me">
						<select name="trone" id="sel_trone" title="请选择通道"></select>
					</dd>
				</dl>
				<br /><br /><br />
				<dl>
					<dd class="dd01_me">CP</dd>		
						<dd class="dd03_me" id="CPtrone">
							<input name="cp_name"  value="<%=cpname %>" id="cp_name"
								type="text" style="width: 150px" class="boxSearch" autocomplete="off" onclick="CP()">
							<input type="hidden" id="cp_nameid" name="cp_nameid" >
						</dd>				
						<dd class="dd04_me">
							<div class="search_form_suggest" id="cp_search_form_suggest" style="display:none;top:69px;left:33px;">
								 <div class="thLeft thPadT5 tab_select">
						            <dl class="clrfix">
						              <dt>ABCDEFG</dt>
						              	<dd class="clr_after">
						              	<%
						              		for(Map.Entry entry : aToG2.entrySet()){
						              	%>
							                <a href="#"><%=entry.getValue().toString() %><input type="hidden" value="<%=entry.getKey() %>" class="nameid"></a>
							                
							             <%
						              		}
							             %>
							             </dd>
						              </dl>
						             <dl class="clrfix">
						              <dt>HIJKL</dt>
						              <dd class="clr_after">
						                <%
						              		for(Map.Entry entry : hToL2.entrySet()){
						              	%>
							                <a href="#"><%=entry.getValue().toString() %><input type="hidden" value="<%=entry.getKey() %>" class="nameid"></a>
							                
							             <%
						              		}
							             %>
						              </dd>
						            </dl>
						            <dl class="clrfix">
						              <dt>MNOPQRST</dt>
						              <dd class="clr_after">
						                <%
						              		for(Map.Entry entry : mToT2.entrySet()){
						              	%>
							                <a href="#"><%=entry.getValue().toString() %><input type="hidden" value="<%=entry.getKey() %>" class="nameid"></a>
							                
							             <%
						              		}
							             %>
						              </dd>
						            </dl>
						            <dl class="clrfix">
						              <dt>WSYZ</dt>
						              <dd class="clr_after">
						                <%
						              		for(Map.Entry entry : wToZ2.entrySet()){
						              	%>
							                <a href="#"><%=entry.getValue().toString() %><input type="hidden" value="<%=entry.getKey() %>" class="nameid"></a>
							                
							             <%
						              		}
							             %>
						              </dd>
						            </dl>
						            </div>
							</div>
						</dd>
					<!--
					<dd>
						<dd class="dd01_me">CP业务</dd>
						<dd class="dd04_me">
						<select name="trone_order" id="sel_trone_order" title="请选择业务"></select>
					</dd>
					-->
					<!-- 暂时先隐藏 -->
					<!-- 
					<dd>
						<dd class="dd01_me">省份</dd>
						<dd class="dd04_me">
						<select name="province" id="sel_province" title="请选择省份">
							<option value="-1">全部</option>
							<%
							for(ProvinceModel province : provinceList)
							{
								%>
							<option value="<%= province.getId() %>"><%= province.getName() %></option>	
								<%
							}
							%>
						</select>
					</dd>
					<dd>
						<dd class="dd01_me">城市</dd>
						<dd class="dd04_me">
						<select name="city" id="sel_city" title="请选择城市">
							
						</select>
					</dd>
					-->
					<dd class="dd01_me">展示方式</dd>
					<dd class="dd04_me">
						<select name="sort_type" id="sel_sort_type" title="展示方式">
							<option value="11">小时</option>
							<option value="4">SP</option>
							<option value="10">SP业务</option>
							<option value="6">SP通道</option>
							<option value="5">CP</option>
							<option value="7">CP业务</option>
							<!-- 暂时先隐藏 -->
							<!--
							<option value="2">周数</option>
							<option value="3">月份</option>
							-->
							<option value="8">省份</option>
							<option value="9">城市</option>
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
					<td><%= titles[sortType-1] %></td>
					<td>数据量(条)</td>
					<td>失败量(条)</td>
					<td>推送量(条)</td>
					<td>金额(元)</td>
					<td>失败金额(元 )</td>
					<td>推送金额(元)</td>
					<td>失败率</td>
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
					<td><%= model.getDataRows() %></td>
					<td><%= model.getDataRows() - model.getShowDataRows()  %></td>
					<td><%= model.getShowDataRows() %></td>
					<td><%= model.getAmount() %></td>
					<td><%= model.getAmount() - model.getShowAmount() %></td>
					<td><%= model.getShowAmount() %></td>
					<td><%= StringUtil.getPercent(model.getDataRows() - model.getShowDataRows(), model.getDataRows()) %></td>
				</tr>
						<%
					}
				%>
			<tbody>
				<tr>
					<td></td>
					<td></td>
					<td>总数据量(条)：<%= dataRows %></td>
					<td>总失败量(条)：<%= dataRows - showDataRows  %> </td>
					<td>总推送量(条)：<%= showDataRows %></td>
					<td>总金额(元)：<%= amount %></td>
					<td>总失败金额(元 )：<%= amount - showAmount %></td>
					<td>总推送金额(元)：<%= showAmount %></td>
					<td>总失败率：<%= StringUtil.getPercent(dataRows - showDataRows, dataRows) %></td>
				</tr>
			</tbody>
		</table>
	</div>
	
</body>
</html>