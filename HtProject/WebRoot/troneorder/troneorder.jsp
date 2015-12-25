<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.system.util.PinYinUtil"%>
<%@page import="com.system.util.Base64UTF"%>
<%@page import="com.system.server.CpServer"%>
<%@page import="com.system.model.CpModel"%>
<%@page import="com.system.model.TroneOrderModel"%>
<%@page import="com.system.server.TroneOrderServer"%>
<%@page import="com.system.server.SpServer"%>
<%@page import="com.system.model.SpModel"%>
<%@page import="com.system.model.TroneModel"%>
<%@page import="com.system.server.TroneServer"%>
<%@page import="com.system.constant.Constant"%>
<%@page import="com.system.util.PageUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.system.model.SpTroneModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.system.server.SpTroneServer"%>
<%@page import="com.system.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int pageIndex = StringUtil.getInteger(request.getParameter("pageindex"), 1);

	String query = Base64UTF.encode(request.getQueryString());

	String spName = StringUtil.getString(request.getParameter("sp_trone_name2"), "");
	System.out.println("name "+spName);
	
	String cpName = StringUtil.getString(request.getParameter("cp_name"), "");
	
	int spId = StringUtil.getInteger(request.getParameter("nameid"), -1);
	
	int cpId = StringUtil.getInteger(request.getParameter("cp_nameid"), -1);
	
	int spTroneId = StringUtil.getInteger(request.getParameter("sp_trone_id"), -1);
	
	int status = StringUtil.getInteger(request.getParameter("trone_status"), -1);

	Map<String, Object> map =  new TroneOrderServer().loadTroneOrder(spId, spTroneId, cpId,status,pageIndex);
		
	List<TroneOrderModel> list = (List<TroneOrderModel>)map.get("list");
	
	
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
	
	
	List<SpTroneModel> spTroneList = new SpTroneServer().loadSpTroneList();
	
	int rowCount = (Integer)map.get("rows");
	
	Map<String, String> params = new HashMap<String,String>();
	
	params = new HashMap<String,String>();
	params.put("sp_id", spId + "");
	params.put("cp_id", cpId + "");
	params.put("sp_trone_id", spTroneId + "");
	params.put("trone_status",status + "");
	
	String pageData = PageUtil.initPageQuery("troneorder.jsp",params,rowCount,pageIndex);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>翔通运营管理平台</title>
<link href="../wel_data/right.css" rel="stylesheet" type="text/css">
<link href="../wel_data/gray.css" rel="stylesheet" type="text/css">
<link href="../wel_data/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../sysjs/jquery-1.7.js"></script>
<script type="text/javascript" src="../sysjs/hhDrop.js"></script>
<script type="text/javascript" src="../sysjs/base.js"></script>
<script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function joSpTrone(id,spId,name)
	{
		var obj = {};
		obj.id = id ;
		obj.spId = spId;
		obj.name = name;
		return obj;
	}
	
	var spTroneArray = new Array();
	<%
		for(SpTroneModel spTrone : spTroneList)
		{
			%>
	spTroneArray.push(new joSpTrone(<%= spTrone.getId() %>,<%= spTrone.getSpId() %>,'<%= spTrone.getSpTroneName() %>'));	
			<%
		}
	%>
	
	$(function(){
		$('#sp_trone_name').val('<%=spName%>');
		$("#nameid").val(<%= spId %>);
		$("#cp_nameid").val(<%= cpId %>);
		$("#nameid").change(spChange);
	});
	
	function delTrone(id)
	{
		if(confirm('真的要删除吗？'))
		{
			window.location.href = "troneaction.jsp?did=" + id;	
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
        	spChange();
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

          });
        //event.stopPropagation();
        obj.next().find('.search_city_result a').click(function(){
          
        	obj.find('span.key_word b').text($(this).text());
		
        });
	}
	
	function spChange()
	{
		var spId = $("#nameid").val();
		$("#sel_sp_trone_id").empty(); 
		$("#sel_sp_trone_id").append("<option value='-1'>请选择</option>");
		for(i=0; i<spTroneArray.length; i++)
		{
			if(spTroneArray[i].spId==spId || spId == "-1")
			{
				$("#sel_sp_trone_id").append("<option value='" + spTroneArray[i].id + "'>" + spTroneArray[i].name + "</option>");
			}
		}
	}
	
	function subForm() 
	{
		
		if (isNullOrEmpty($("#sp_trone_name").val())) 
		{
			$("#nameid").val(-1);
		}
		if (isNullOrEmpty($("#cp_name").val())) 
		{
			$("#cp_nameid").val(-1);
		}
		document.getElementById("formid").submit();
	}
	
</script>

<body>
	<div class="main_content">
		<div class="content" >
			<dl>
				<dd class="ddbtn" ><a href="troneorderadd.jsp">增  加</a></dd>
			</dl>
			<form action="troneorder.jsp"  method="post" style="margin-top: 10px" id="formid">
				<dl>
					<dd class="dd01_me">CP</dd>		
						<dd class="dd03_me" id="CPtrone">
							<input name="cp_name"  value="<%=cpName %>" id="cp_name"
								type="text" style="width: 150px" class="boxSearch" autocomplete="off" onclick="CP()">
							<input type="hidden" id="cp_nameid" name="cp_nameid" >
						</dd>				
						<dd class="dd04_me">
							<div class="search_form_suggest" id="cp_search_form_suggest" style="display:none;top:74px;left:33px;">
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
					<dd class="dd01_me"s >SP</dd>		
						<dd class="dd03_me" id="SPtrone">
							<input name="sp_trone_name2"  value="<%=spName %>" id="sp_trone_name"
								type="text" style="width: 150px" class="boxSearch" autocomplete="off" onclick="SP()" >
							<input type="hidden" id="nameid" name="nameid" >
						</dd>				
						<dd class="dd04_me">
							<div class="search_form_suggest" id="sp_search_form_suggest" style="display:none;top:74px;left:295px;">
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
						<select name="sp_trone_id" id="sel_sp_trone_id" ></select>
					</dd>
					<dd class="dd01_me">状态</dd>
					<dd class="dd04_me">
						<select name="trone_status" id="sel_trone_status" >
							<option value="-1">全部</option>
							<option value="0">启用</option>
							<option value="1">停用</option>
						</select>
					</dd>
					<dd class="ddbtn" style="margin-left: 10px; margin-top: 0px;">
						<input class="btn_match" name="search" value="查 询" onclick="subForm()">
					</dd>
				</dl>
			</form>
		</div>
		<table cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td>序号</td>
					<td>CP</td>
					<td>SP名称</td>
					<td>SP业务名称</td>
					<td>通道名称</td>
					<td>指令</td>
					<td>扣量设置</td>
					<td>扣量比</td>
					<td>同步金额</td>
					<td>动态</td>
					<td>启用</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<%
					int rowNum = 1;
					for (TroneOrderModel model : list)
					{
				%>
				<tr>
					<td><%=(pageIndex-1)*Constant.PAGE_SIZE + rowNum++ %></td>
					<td><%=model.getCpShortName()%></td>
					<td><%=model.getSpShortName() %></td>
					<td><%=model.getSpTroneName()%></td>
					<td><%=model.getTroneName() %></td>
					<td><%=model.getOrderNum() %></td>
					<td><%=model.getIsHoldCustom()==0 ? "URL" : "当前" %></td>
					<td><%=model.getHoldPercent() %></td>
					<td><%=model.getHoldAmount() %></td>
					<td><%=model.getDynamic()==1 ? "是" : "否" %></td>
					<td><%=model.getDisable() ==0 ? "是" : "否" %></td>
					<td>
						<a href="troneorderedit.jsp?query=<%= query %>&id=<%= model.getId() %>">修改</a>
					</td>
				</tr>
				<%
					}
				%>
				
			<tbody>
				<tr>
					<td colspan="13" class="tfooter" style="text-align: center;"><%= pageData %></td>
				</tr>
			</tbody>
		</table>
	</div>
	
</body>
</html>