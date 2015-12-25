<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.system.util.model.Partner"%>
<%@page import="com.system.util.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.system.util.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Redirect result = new Redirect();
	String str = result.sendGet("http://gaopen.talkingdata.net/game/product/list", "token=3d5663eacbf64808ae89cd7a5077293f");
	List<Product> list2 = result.gsonToProduct(str);
	List<Partner> list = new ArrayList<Partner>();
	for(Product model : list2){
		str = result.sendGet("http://gaopen.talkingdata.net/game/partner/list", 
				"token=3d5663eacbf64808ae89cd7a5077293f&productId="+model.getProductId());
		List<Partner> templist = result.gsonToPartner(str);
		for(Partner partner : templist){
			list.add(partner);
		}
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
				<form action="app.jsp"  method="post" id="formid">
				<dl>
<!-- 					<dd class="dd01_me">应用名</dd> -->
<!-- 					<dd class="dd03_me"> -->
<%-- 						<input name="appname" id="input_appkey" value="<%=appname %>" type="text" style="width: 150px"> --%>
<!-- 					</dd> -->
<!-- 					<dd class="dd01_me">应用KEY</dd> -->
<!-- 					<dd class="dd03_me"> -->
<%-- 						<input name="appkey" id="input_appkey" value="<%=appkey %>" type="text" style="width: 150px"> --%>
<!-- 					</dd> -->
<!-- 					<dd class="ddbtn" style="margin-left: 10px; margin-top: 0px;"> -->
<!-- 						<input class="btn_match" name="search" value="查 询" type="submit" > -->
<!-- 					</dd> -->
				</dl>
			</form>
			</dl>
		</div>
		<table cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td>序号</td>
					<td>产品ID</td>
					<td>渠道Id</td>
					<td>渠道名称</td>
					<td>渠道别名</td>
					<td>平台</td>
					<td>组渠道ID</td>
					<td>合并渠道列表</td>
					<td>合并后全渠道</td>
					<td>渠道类型</td>
					<td>短渠道推链接</td>
					<td>短渠道链接</td>
					<td>创建时间</td>
					<td>更新时间</td>
					<td>渠道账户</td>
					<td>渠道关注</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<%
					int rowNum = 1;
					for (Partner model : list)
					
					{
						
				%>
				<tr>
					<td><%=rowNum++%></td>
					<td><%=model.getProductId()%></td>
					<td><%=model.getPartnerId()%></td>
					<td><%=model.getPartnerName()%></td>
					<td><%=model.getAliasName()%></td>
					<td><%=model.getPlatformId()%></td>
					<td><%=model.getGroupId()%></td>
					<td><%=model.getCombinedPartnerIds()%></td>
					<td><%=model.getPartnerIds()%></td>
					<td><%=model.getPartnerType()%></td>
					<td><%=model.getSpreadurl()%></td>
					<td><%=model.getDownloadurl()%></td>
					<%
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String time1 = sdf.format(model.getCreateTime());
						String time2 = sdf.format(model.getUpdateTime());
					%>
					<td><%=time1%></td>
					<td><%=time2%></td>
					<td><%=model.isCreate()%></td>
					<td><%=model.isFocus()%></td>
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