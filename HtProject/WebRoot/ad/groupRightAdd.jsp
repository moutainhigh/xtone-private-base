<%@page import="com.system.server.GroupServer"%>
<%@page import="com.system.model.GroupModel"%>
<%@page import="com.system.server.GroupRightServer"%>
<%@page import="com.system.model.GroupRightModel"%>
<%@page import="com.system.dao.GroupRightDao"%>
<%@page import="java.util.List"%>
<%@page import="com.system.server.CpServer"%>
<%@page import="com.system.model.CpModel"%>
<%@page import="com.system.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<GroupRightModel> list = new GroupRightServer().loadGroup();
	List<GroupModel> groupList = new GroupServer().loadAllGroup();
	int pageIndex = StringUtil.getInteger(request.getParameter("pageindex"), 1);
	
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
    //声明整数的正则表达式
    function isNum(a)
	{
		//var reg=/^(([a-z]+[0-9]+)|([0-9]+[a-z]+))[a-z0-9]*$/i;
		var reg = /^[0-9]*[1-9][0-9]*$/i;
		return reg.test(a);
	}
	function subForm() 
	{
		
		
		if($("#appname").val()<0)
		{
			alert("请选择角色");
			$("#appname").focus();
			return;
		}
		
		/*if (isNullOrEmpty($("#group_list").val())) 
		{
			alert("请输入授权角色");
			$("#group_list").focus();
			return;
		}*/
		
		console.log($("#remark").val());
		
		if(isNullOrEmpty($("#remark").val()))
		{
			alert("请输入备注！");
			$("#remark").focus();
			return;
		}
		
		
		document.getElementById("addform").submit();
	}
	
</script>
<body>
	<div class="main_content">
		<div class="content" style="margin-top: 10px">
			<dl>
				<dd class="ddbtn" >
				<label>添加角色</label>
				</dd>
			</dl>
			<br />	<br />		
			<dl>
				<form action="groupRightAction.jsp?pageindex=<%=pageIndex %>" method="post" id="addform">
				
					<dd class="dd00_me"></dd>
					<dd class="dd01_me">角色</dd>
					<dd class="dd04_me">
						<select name="group" id="group" style="width: 200px;" title="选择group">
							<option value="-1">请选择角色名</option>
							<%
							
							for(GroupRightModel group : list)
							{
								%>
							<option value="<%= group.getGroupId() %>"><%= group.getName() %></option>	
								<%
							}
							%>
						</select>
					</dd>

					<br />
					<br />
					<br />
					<dd class="dd00_me"></dd>
					<dd class="dd01_me">授权角色</dd><br />
					<br />
					<%
					for (GroupModel group : groupList)
					{
					%>
					<dd class="dd01_me"><%=group.getName()%></dd>
					<dd class="dd03_me">
						<input type="checkbox" name="groupid" style="width: 25px;float:left" value="<%= group.getId() %>"  id="groupid_<%= group.getId() %>" >
					</dd>
					<br /><br />
					<%
					}
					%>

					<br />
					<br />
					<br />
					<dd class="dd00_me"></dd>
					<dd class="dd01_me">备注</dd>
					<dd class="dd03_me">
						<input type="text" name="remark" id="remark"
							style="width: 200px">
					</dd>
					
					

					<br />
					<br />
					<br />
					<dd class="dd00"></dd>
					<dd class="dd00_me"></dd>
					<dd class="ddbtn" style="margin-left: 100px; margin-top: 10px">
						<input type="button" value="提 交" onclick="subForm()">
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