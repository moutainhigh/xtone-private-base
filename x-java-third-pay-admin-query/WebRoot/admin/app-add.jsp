<%@page import="org.apache.log4j.Logger"%>
<%@page import="org.apache.log4j.xml.Log4jEntityResolver"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>增加APP</title>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="../js-css/base.js"></script>
<script src="../js-css/jquery-2.1.3.min.js"></script>
<link href="../js-css/edit.css" rel="stylesheet">
<script language="JavaScript">
	function commin(){
		var appKey = $("#appKey").val().trim();
		var appName = $("#appName").val().trim();
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
	function updateAjax() {
		// 		if (document.getElementById("content").value.trim() == "") {
		// 			alert("兑换码为空！");
		// 			return false;
		// 		}
		var action="新增APP信息";
		var oriData = {
			appKey : $("#appKey").val(),
			appName : encodeURI($("#appName").val()),
			type : $("#type").val(),
			notify_url : $("#notify_url").val()=="null"?"" : $("#notify_url").val(),
			encrypt : $("input[name='encrypt']:checked").val(),
			encrypt_key : $("#encrypt_key").val(),
			cpId : $("#cpId").val()
		};
		
		$.ajax({
			type : "post",
			contentType: "application/json; charset=utf-8", 
			url : "app-update-commit.jsp",
			async : true,
			data : JSON.stringify(oriData),
			dataType : "json",
			success : function(msg) {
				if(msg.statue=="error"){
					alert(action+'失败!  '+msg.data);
				}else{
					alert(action+'成功!');
					location.href = 'apps.jsp';
				}
			},
			error : function(msg) {
				alert(action+'失败!');
			}
		});
	}
</script>
</head>
<body>
	
	<div style="text-align: center; margin-top: 200px">
	
		<form id="form1" name="form1">
			<span>新增APP</span><br></br>
			<span>appKey：</span><input id="appKey" name="title" type="text"
				class="input1"> <input type="hidden"name="type" id="type" value="1">
				<input type="hidden"name="type" id="type" value="1"><br></br>
			<span>appName： </span><input type="text"
				name="appName" id="appName" value="" class="input1"><br></br>
			<span>支付回调地址： </span><input type="text"
				name="notify_url" id="notify_url" value="" class="input1"><br></br>
			<span>是否加密数据： </span><input type="radio" name="encrypt" style="width: 35px;-float:left" value="1" >
					<label style="font-size: 14px;-float:left">加密</label>
					<input type="radio" name="admin" style="width: 35px;-float:left" value="0" >
					<label style="font-size: 14px;-float:left">不加密</label><br></br>
			<span>AES加密key： </span><input id="encrypt_key" name="title" type="text"
				class="input1"><br></br>
			<span>CPID： </span><input type="text"
				name="cpId" id="cpId" value="" class="input1"><br></br>
				<input
				class="btn1" type="button"
				value="确认增加" onclick="commin()"> <input
				class="btn1" type="button"
				value="取消增加" onclick="window.location.href='apps.jsp'">

		</form>
	</div>

</body>
</html>