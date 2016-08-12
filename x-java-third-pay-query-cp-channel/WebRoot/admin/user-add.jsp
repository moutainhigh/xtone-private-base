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
<title>增加用户</title>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="../js-css/base.js"></script>
<script src="../js-css/jquery-2.1.3.min.js"></script>
<link href="../js-css/edit.css" rel="stylesheet">
<script language="JavaScript">
	function commin(){
		var user = $("#user").val().trim();
		var pwd = $("#pwd").val().trim();
		if(isNullOrEmpty(user)){
			alert("用户名不能为空");
			return;
		}
		if(isNullOrEmpty(pwd)){
			alert("密码不能为空");
			return;
		}
		updateAjax();
	}
	function updateAjax() {
		// 		if (document.getElementById("content").value.trim() == "") {
		// 			alert("兑换码为空！");
		// 			return false;
		// 		}
		var action="修改用户信息";
		var oriData = {
			id : $("#id").val(),
			type : $("#type").val(),
			user : $("#user").val(),
			pwd : $("#pwd").val().trim(),
			email : $("#email").val()=="null"?"" : $("#email").val(),
			admin : $("input[name='admin']:checked").val(),
			status : $("input[name='status']:checked").val()
		};
		
		$.ajax({
			type : "post",
			contentType: "application/json; charset=utf-8", 
			url : "user-update-commit.jsp",
			async : true,
			data : JSON.stringify(oriData),
			dataType : "json",
			success : function(msg) {
				alert(action+'成功!');
				location.href = 'user-all.jsp';
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
			<span>用户修改</span><br></br>
			<span>用户名：</span><input id="user" name="title" type="text"
				class="input1"> <input type="hidden"name="type" id="type" value="1"> <br></br>
			<span>密码： </span><input type="password"
				name="pwd" id="pwd" value="" class="input1"><br></br>
			<span>邮箱： </span><input type="text"
				name="email" id="email" value="" class="input1"><br></br>
			<span>权限： </span><input type="radio" name="admin" style="width: 35px;-float:left" value="1" >
					<label style="font-size: 14px;-float:left">管理员</label>
					<input type="radio" name="admin" style="width: 35px;-float:left" value="0" >
					<label style="font-size: 14px;-float:left">用户</label><br></br>
			<span>状态： </span><input type="radio" name="status" style="width: 35px;-float:left" value="1" >
					<label style="font-size: 14px;-float:left">启用</label>
					<input type="radio" name="status" style="width: 35px;-float:left" value="0" >
					<label style="font-size: 14px;-float:left">关闭</label><br></br>
				<input
				class="btn1" type="button"
				value="确认增加" onclick="commin()"> <input
				class="btn1" type="button"
				value="取消增加" onclick="window.location.href='user-all.jsp'">

		</form>
	</div>

</body>
</html>