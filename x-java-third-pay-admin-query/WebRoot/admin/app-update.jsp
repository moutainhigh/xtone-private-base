<%@page import="org.apache.log4j.Logger"%>
<%@page import="org.apache.log4j.xml.Log4jEntityResolver"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.common.util.ConnectionService"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.demo.json.CodeRsp"%>
<%@page import="org.demo.info.Code"%>
<%@page import="com.google.gson.LongSerializationPolicy"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%
	 Logger log = Logger.getLogger("userupdate.class");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改APP</title>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="../js-css/base.js"></script>
<script src="../js-css/jquery-1.7.js"></script>
<link href="../js-css/edit.css" rel="stylesheet">


<script language="javascript">
	function commin(){
		var appKey = $("#appKey").val().trim();
		var appName = $("#appName").val();
		var cpid = $("#cpId").val().trim();
		if(isNullOrEmpty(appKey)){
			alert("appKey不能为空");
			return;
		}
		if(isNullOrEmpty(appName)){
			alert("appName不能为空");
			return;
		}
		if(isNullOrEmpty(cpid)||isNaN(cpid)){
			alert("cpid不能为空且必须为数字")
			return;
		}
		updateAjax();
	}
	/*****去除前后空格*******/
	 function Trim(str){ 
         return str.replace(/(^\s*)|(\s*$)/g, ""); 
	 }
	 /*****去除前后空格*******/
	function updateAjax() {
		var action="修改APP信息";
		//var email = $("#email").val()=="null"?"" : $("#email").val();
		//var date = '{"id":'+$("#id").val()+',"type":'+$("#type").val()+',"user":"'+Trim($("#user").val())+'","pwd":"'+Trim($("#pwd").val())+'","email":"'+email+'","admin":'+$("input[name='admin']:checked").val()+',"status":'+$("input[name='status']:checked").val()+',"cpId":'+$("#cpId").val()'}';
		var date = {
				appKey : $("#appKey").val(),
				appName : encodeURI($("#appName").val()),
				type : $("#type").val(),
				notify_url : $("#notify_url").val()=="null"?"" : $("#notify_url").val(),
				encrypt : $("input[name='encrypt']:checked").val(),
				encrypt_key : $("#encrypt_key").val()=="null"?"" : $("#encrypt_key").val(),
				cpId : $("#cpId").val()
			};
		$.ajax({
			type : "post",
			contentType: "application/json; charset=utf-8", 
			url : "app-update-commit.jsp",
			async : true,
			data : JSON.stringify(date),
			dataType : "json",
			success : function(msg) {
				alert(action+'成功!');
				location.href = 'apps.jsp';
			},
			error : function(msg) {
				alert(action+'失败!');
				console.log("in error!");
			}
		});
	}
</script>
</head>
<body>
	<%
		String appKey = request.getParameter("appKey").trim();
		log.debug("appKey:"+appKey);
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			String sql = "SELECT appKey,appName,notify_url,encrypt,encrypt_key,cpId FROM `tbl_thirdpay_apps` WHERE appKey=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, appKey);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				
	%>
	<script type="text/javascript">
		$(function(){
			setRadioCheck("encrypt",<%=rs.getInt("encrypt") %>);
		});
	</script>
	<div style="text-align: center; margin-top: 200px">
	
		<form id="form1" name="form1">
			<span>APP修改</span><br></br>
			<span>appKey：</span><input type="text"
				name="appKey" id="appKey" value="<%=rs.getString("appKey")%>" class="input1" readonly="readonly">
				<input type="hidden"name="type" id="type" value="2"><br></br>
			<span>appName： </span><input type="text"
				name="appName" id="appName" value="<%=rs.getString("appName")%>" class="input1"><br></br>
			<span>支付回调地址： </span><input type="text"
				name="notify_url" id="notify_url" value="<%=rs.getString("notify_url")%>" class="input1"><br></br>
			<span>是否加密数据： </span><input type="radio" name="encrypt" style="width: 35px;-float:left" value="1" >
					<label style="font-size: 14px;-float:left">加密</label>
					<input type="radio" name="encrypt" style="width: 35px;-float:left" value="0" >
					<label style="font-size: 14px;-float:left">不加密</label><br></br>
			<span>AES加密key： </span><input id="encrypt_key" name="title" type="text"
				class="input1" value="<%=rs.getString("encrypt_key")%>"><br></br>
			<span>CPID： </span><input type="text"
				name="cpId" id="cpId" value="<%=rs.getLong("cpId")%>" class="input1"><br></br>
				<input
				class="btn1" type="button"
				value="确认修改" onclick="commin()"> <input
				class="btn1" type="button"
				value="取消修改" onclick="window.location.href='apps.jsp'">

		</form>
		<%
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.print("{\"status\":\"error\",\"data\":\"无法编辑\"}");
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		%>
	</div>

</body>
</html>