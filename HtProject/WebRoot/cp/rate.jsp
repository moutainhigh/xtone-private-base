<%@page import="com.system.util.PinYinUtil"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.system.server.CpServer"%>
<%@page import="com.system.model.CpModel"%>
<%@page import="com.system.model.CpJieSuanLvModel"%>
<%@page import="com.system.server.CpJieSuanLvServer"%>
<%@page import="com.system.server.SpServer"%>
<%@page import="com.system.model.SpModel"%>
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

	String spname = StringUtil.getString(request.getParameter("sp_trone_name"), "");
	
	String cpname = StringUtil.getString(request.getParameter("cp_name"), "");

	int spId = StringUtil.getInteger(request.getParameter("nameid"), -1);
	
	int cpId = StringUtil.getInteger(request.getParameter("cp_nameid"), -1);

	Map<String, Object> map =  new CpJieSuanLvServer().loadJieSuanLv(cpId, spId, pageIndex);
		
	List<CpJieSuanLvModel> list = (List<CpJieSuanLvModel>)map.get("list");
	
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
	
	
	int rowCount = (Integer)map.get("rows");
	
	Map<String, String> params = new HashMap<String,String>();
	
	params = new HashMap<String,String>();
	params.put("sp_trone_name", spname);
	params.put("cp_name", cpname);
	params.put("nameid", spId + "");
	params.put("cp_nameid", cpId + "");
	
	String pageData = PageUtil.initPageQuery("jiesuan.jsp",params,rowCount,pageIndex);
	
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
	
	function delTrone(id)
	{
		if(confirm('真的要删除吗？'))
		{
			window.location.href = "troneaction.jsp?did=" + id;	
		}
	}
	
	$(function()
	{
		$("#nameid").val(<%= spId %>);
		$("#cp_nameid").val(<%= cpId %>);
	});
	
	//声明整数的正则表达式
	function isNum(a)
	{
		var reg=/^(([a-z]+[0-9]+)|([0-9]+[a-z]+))[a-z0-9]*$/i;
		return reg.test(a);
	}

	function editShowData(editId)
	{
		var curShowRows = $("#hid_" + editId).val();
		
		var newHtml = "<input type='text' id='myput_" + editId + "' style='width:30px;background-color:#CDC5BF;text-align:center;' value='"+ curShowRows +"' />";
		
		newHtml += "<input type='button' value='更新' style='margin-left: 10px' onclick='updateShowData(" + editId + ")'/>";
		 
		newHtml += "<input type='button' value='取消' style='margin-left: 10px' onclick='cancelShowData(" + editId + ")'/>";
		
		$("#span_" + editId).html(newHtml);
	}
	
	function updateShowData(editId)
	{
		var newShowRows = $("#myput_" + editId).val();
		
		if (isNum(newShowRows))
		{
			alert("输入数据不正确");
			return;
		}
		
		updateDbData(editId,newShowRows);
	}
	
	function updateDbData(editId,newShowRows)
	{
		$.post("jiesuanaction.jsp", 
		{
			type : 1,
			value : newShowRows,
			id :editId 
		}, 
		function(data) 
		{
			data = $.trim(data);
			if ("OK" == data) 
			{
				$("#hid_" + editId).val(newShowRows);		
				$("#span_" + editId).html(newShowRows);
			} 
			else 
			{
				alert("修改失败！请联系管理员");
				$("#span_" + editId).html($("#hid_" + editId).val());
			}
		});
	}
	
	function cancelShowData(editId)
	{
		$("#span_" + editId).html($("#hid_" + editId).val());
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
	
	function subForm() 
	{
		if (isNullOrEmpty($("#cp_name").val())) 
		{
			$("#cp_nameid").val(-1);
		}
		if (isNullOrEmpty($("#sp_trone_name").val())) 
		{
			$("#nameid").val(-1);
		}
		document.getElementById("addform").submit();
	}
	
</script>

<body>
	<div class="main_content">
		<div class="content" >
			<dl>
				<dd class="ddbtn" ><a href="jiesuanadd.jsp">增  加</a></dd>
			</dl>
			<form action="jiesuan.jsp"  method="post" style="margin-top: 10px" id="addform">
				<dl>
				<dd class="dd01_me">CP</dd>		
						<dd class="dd03_me" id="CPtrone">
							<input name="cp_name"  value="<%=cpname %>" id="cp_name"
								type="text" style="width: 150px" class="boxSearch" autocomplete="off" onclick="CP()">
							<input type="hidden" id="cp_nameid" name="cp_nameid" >
						</dd>				
						<dd class="dd04_me">
							<div class="search_form_suggest" id="cp_search_form_suggest" style="display:none;top:74px;left:34px;">
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
							<input name="sp_trone_name2"  value="<%=spname %>" id="sp_trone_name"
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
					<dd class="ddbtn" style="margin-left: 10px; margin-top: 0px;">
						<input class="btn_match" name="search" value="查     询" onclick="subForm()" />
					</dd>
				</dl>
			</form>
		</div>
		<table cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td>序号</td>
					<td>CP名称</td>
					<td>SP名称</td>
					<td>SP业务名称</td>
					<td>结算率</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<%
					int rowNum = 1;
					for (CpJieSuanLvModel model : list)
					{
				%>
				<tr>
					<td>
						<%=(pageIndex-1)*Constant.PAGE_SIZE + rowNum++ %>
						<input type="hidden" id="hid_<%= model.getId() %>" value="<%= model.getJieSuanLv() %>" />
					</td>
					<td><%=model.getCpName()%></td>
					<td><%=model.getSpName()%></td>
					<td><%=model.getSpTroneName()%></td>
					<td ondblclick="editShowData('<%= model.getId() %>')">
						<span id="span_<%= model.getId() %>"><%= model.getJieSuanLv() %></span>
					</td>
					<td>
						<a href="jiesuanedit.jsp?id=<%= model.getId() %>">修改</a>
						<a href="#" onclick="delTrone(<%= model.getId() %>)">删除</a>
					</td>
				</tr>
				<%
					}
				%>
				
			<tbody>
				<tr>
					<td colspan="9" class="tfooter" style="text-align: center;"><%= pageData %></td>
				</tr>
			</tbody>
		</table>
	</div>
	
</body>
</html>