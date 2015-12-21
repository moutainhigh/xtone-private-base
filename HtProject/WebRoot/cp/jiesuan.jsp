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

	int spId = StringUtil.getInteger(request.getParameter("sp_id"), -1);
	
	int cpId = StringUtil.getInteger(request.getParameter("cp_id"), -1);

	Map<String, Object> map =  new CpJieSuanLvServer().loadJieSuanLv(cpId, spId, pageIndex);
		
	List<CpJieSuanLvModel> list = (List<CpJieSuanLvModel>)map.get("list");
	
	List<SpModel> spList = new SpServer().loadSp();
	
	List<CpModel> cpList = new CpServer().loadCp();
	
	int rowCount = (Integer)map.get("rows");
	
	Map<String, String> params = new HashMap<String,String>();
	
	params = new HashMap<String,String>();
	params.put("sp_id", spId + "");
	params.put("cp_id", cpId + "");
	
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
		$("#sel_sp").val(<%= spId %>);
		$("#sel_cp").val(<%= cpId %>);
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
	
</script>

<body>
	<div class="main_content">
		<div class="content" >
			<dl>
				<dd class="ddbtn" ><a href="jiesuanadd.jsp">增  加</a></dd>
			</dl>
			<form action="jiesuan.jsp"  method="get" style="margin-top: 10px">
				<dl>
				<dd class="dd01_me">CP</dd>
					<dd class="dd04_me">
						<select name="cp_id" id="sel_cp" >
							<option value="-1">全部</option>
							<%
							for(CpModel cp : cpList)
							{
								%>
							<option value="<%= cp.getId() %>"><%= cp.getShortName() %></option>	
								<%
							}
							%>
						</select>
					</dd>
					<dd class="dd01_me">SP</dd>
					<dd class="dd04_me">
						<select name="sp_id" id="sel_sp" title="选择SP">
							<option value="-1">全部</option>
							<%
							for(SpModel sp : spList)
							{
								%>
							<option value="<%= sp.getId() %>"><%= sp.getShortName() %></option>	
								<%
							}
							%>
						</select>
					</dd>
					<dd class="ddbtn" style="margin-left: 10px; margin-top: 0px;">
						<input class="btn_match" name="search" value="查     询" type="submit" />
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