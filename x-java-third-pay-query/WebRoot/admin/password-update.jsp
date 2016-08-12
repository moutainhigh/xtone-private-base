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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改密码</title>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script src="../js-css/jquery-2.1.3.min.js"></script>
<link href="../js-css/edit.css" rel="stylesheet">
<script language="JavaScript">
	function updateAjax() {
		// 		if (document.getElementById("content").value.trim() == "") {
		// 			alert("兑换码为空！");
		// 			return false;
		// 		}
		if($("#newPassword").val()!=$("#checkPassword").val()){
			alert("新密码与确认密码不一样！");
			return;
		}
		var oriData = {
			password : $("#password").val(),
			newPassword : $("#newPassword").val()
		};
		var action="修改密码";
		$.ajax({
			type : "post",
			url : "password-update-commit.jsp",
			async : false,
			data : JSON.stringify(oriData),
			dataType : "json",
			success : function(msg) {

				if (msg.status == "success") {

					alert(action+'成功!');
					window.history.back();

				} else if(msg.status == "errOldPwd"){
					alert('旧密码输入错误!');
				} else {
					alert(action+'失败!');
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				
				var tip=action+"失败!";
				switch (XMLHttpRequest.status)
				{
					case 404:
				  		break;
					default:
						tip="网络异常，请稍后再试。";
						break;
				}
				alert(tip);
			}
		});
	}
</script>
</head>
<body>
	<div style="text-align: center; margin-top: 200px">
		<form id="form1" name="form1">

			<input id="password" placeholder="旧的密码" type="password"
				class="input1"><br><br>
				<input id="newPassword" placeholder="新的密码" type="password"
				class="input1"><br><br>
				<input id="checkPassword" placeholder="确认密码" type="password"
				class="input1"><br><br>
				
				<input class="btn1" type="button"
				value="确认修改" onclick="updateAjax()"> 
				<input class="btn1" type="button"
				value="取消修改" onclick="window.location.href='stat-all.jsp'">

		</form>
	</div>

</body>
</html>