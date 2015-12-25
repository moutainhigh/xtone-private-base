<%@page import="java.util.LinkedHashMap"%>
<%@page import="org.apache.commons.collections.map.LinkedMap"%>
<%@page import="com.system.util.PinYinUtil"%>
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
	String spTroneName = StringUtil.getString(request.getParameter("sp_trone_name2"), "");

	String spTroneName2 = StringUtil.getString(request.getParameter("sp_trone_name"), "");

	String query = request.getQueryString();

	int spId = StringUtil.getInteger(request.getParameter("nameid"), -1);

	int pageIndex = StringUtil.getInteger(request.getParameter("pageindex"), 1);
	
	Map<String, Object> map = new SpTroneServer().loadSpTroneList(pageIndex,spId,spTroneName2);
	
	List<SpModel> spList = new SpServer().loadSp();
	
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

	List<SpTroneModel> list = (List<SpTroneModel>) map.get("list");

	int rowCount = (Integer) map.get("rows");
	
	Map<String,String> params = new HashMap<String,String>();
	params.put("nameid", spId + "");
	params.put("sp_trone_name2", spTroneName);
	
	String pageData = PageUtil.initPageQuery("sptrone.jsp", params, rowCount, pageIndex);
	
	String[] troneTypes = {"点播","包月","IVR"};
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>翔通运营管理平台</title>
<link href="../wel_data/right.css" rel="stylesheet" type="text/css">
<link href="../wel_data/gray.css" rel="stylesheet" type="text/css">
<!-- <link href="../wel_data/main.css" rel="stylesheet" type="text/css"> -->
<script type="text/javascript" src="../sysjs/jquery-1.7.js"></script>
<script type="text/javascript" src="../sysjs/hhDrop.js"></script>
<script type="text/javascript" src="../sysjs/base.js"></script>
<script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function delSpTrone(id) {
    if (confirm('真的要删除吗？')) {
        window.location.href = "sptroneaction.jsp?did=" + id;
    }
}

$(function() {
	$('#nameid').val(<%=spId%>);
	/***********    下拉多选框     ****************/
	$('#SPtrone').on('click',function(){
        var _this = $(this);
        //点击本身显示隐藏
        if(_this.hasClass('boxSearchHover') ){
          _this.removeClass('boxSearchHover');
          _this.children('.btn_search').removeClass('btn_search_current');
          _this.parent().find('.search_form_suggest').hide();

        }else{
          _this.addClass('boxSearchHover');
          _this.children('.btn_search').addClass('btn_search_current');
          _this.parent().find('.search_form_suggest').show();
        }

        _this.next().find('.clr_after a').on('click',function(){
            
        	$('#sp_trone_name').val($(this).text());
        	//alert($(this).find('.nameid').val());
        	$('#nameid').val($(this).find('.nameid').val());
        	_this.removeClass('boxSearchHover');
            _this.children('.btn_search').removeClass('btn_search_current');
            _this.parent().find('.search_form_suggest').hide();
            _this.find('span.key_word b').text($(this).text());

          });

        _this.next().find('.search_city_result a').click(function(){
          
          _this.find('span.key_word b').text($(this).text());

        });
	});		
	/***********    下拉多选框     ****************/
	
});

	function subForm() 
	{
		if (isNullOrEmpty($("#sp_trone_name").val())) 
		{
			$("#nameid").val(-1);
		}
		document.getElementById("formid").submit();
	}
        
</script>
<body>
	<div class="main_content">
		<div class="content">
			<dl>
				
				<dd class="ddbtn">
					<a href="sptroneadd.jsp">增 加</a>
				</dd>
				<div class="clear"></div>
				<form action="sptrone.jsp" method="post" id="formid">
					<dl>
						<dd class="dd01_me">SP</dd>		
						<dd class="dd03_me" id="SPtrone">
							<input name="sp_trone_name2"  value="<%= spTroneName %>" id="sp_trone_name"
								type="text" style="width: 150px" class="boxSearch" autocomplete="off" >
							<input type="hidden" id="nameid" name="nameid" >
						</dd>				
						<dd class="dd04_me">
							<div class="search_form_suggest" style="display:none;top:50px;left:148px;">
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
						
						<dd class="dd01_me">业务</dd>
						<dd class="dd03_me">
							<input name="sp_trone_name" id="input_sp_trone_name" value="<%= spTroneName2 %>"
								type="text" style="width: 150px">
						</dd>
						<dd class="ddbtn" style="margin-left: 10px; margin-top: 0px;">
							<input class="btn_match" name="search" value="查 询" onclick="subForm()">
						</dd>
					</dl>
				</form>
				<div class="clearfloat"></div> 
			</dl>
		</div>
		<table cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td>序号</td>
					<td>SP名称</td>
					<td>运营商</td>
					<td>业务名称</td>
					<td>类型</td>
					<td>结算率</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<%
					int rowNum = 1;
					for (SpTroneModel model : list)
					{
				%>
				<tr>
					<td><%=(pageIndex - 1) * Constant.PAGE_SIZE + rowNum++%></td>
					<td><%=model.getSpName()%></td>
					<td><%=model.getOperatorName()%></td>
					<td><%=model.getSpTroneName()%></td>
					<td><%= troneTypes[model.getTroneType()]%></td>
					<td><%=model.getJieSuanLv()%></td>
					<td><a href="sptroneedit.jsp?<%= query %>&id=<%= model.getId() %>">修改</a>
						<a href="#" onclick="delSpTrone(<%=model.getId()%>)">删除</a></td>
				</tr>
				<%
					}
				%>
			
			<tbody>
				<tr>
					<td colspan="7" class="tfooter" style="text-align: center;"><%=pageData%></td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>