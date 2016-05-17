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
<title>修改用户</title>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="../js-css/base.js"></script>
<script src="../js-css/jquery-1.7.js"></script>
<link href="../js-css/edit.css" rel="stylesheet">
<script language="JavaScript">
	function commin(){
		var user = Trim($("#user").val());
		var pwd =  Trim($("#pwd").val());
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
	/*****去除前后空格*******/
	 function Trim(str){ 
         return str.replace(/(^\s*)|(\s*$)/g, ""); 
	 }
	 /*****去除前后空格*******/
	function updateAjax() {
		var action="修改用户信息";
		var email = $("#email").val()=="null"?"" : $("#email").val();
		var date = '{"id":'+$("#id").val()+',"type":'+$("#type").val()+',"user":"'+Trim($("#user").val())+'","pwd":"'+Trim($("#pwd").val())+'","email":"'+email+'","admin":'+$("input[name='admin']:checked").val()+',"status":'+$("input[name='status']:checked").val()+'}';
		
		$.ajax({
			type : "post",
			contentType: "application/json; charset=utf-8", 
			url : "user-update-commit.jsp",
			async : true,
			data : date,
			dataType : "json",
			success : function(msg) {
				alert(action+'成功!');
				location.href = 'user-all.jsp';
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
		int id = Integer.parseInt(new String(request.getParameter("id").trim()));
		log.debug("id:"+id);
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			String sql = "SELECT username,pwd,email,isAvail,isAdmin,lastLogin,ADDTIME FROM `tbl_thirdpay_cp_users` WHERE id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				
	%>
	<script type="text/javascript">
		$(function(){
			setRadioCheck("admin",<%=rs.getInt("isAdmin") %>);
			setRadioCheck("status",<%=rs.getInt("isAvail") %>);
		});
	</script>
	<div style="text-align: center; margin-top: 200px">
	
		<form id="form1" name="form1">
			<span>用户修改</span><br></br>
			<span>用户名：</span><input id="user" placeholder="在此编辑兑换码"
				value="<%=rs.getString("username")%>" name="title" type="text"
				class="input1"> <input type="hidden"
				name="id" id="id" value="<%=id%>"><input type="hidden"name="type" id="type" value="2"> <br></br>
			<span>密码： </span><input type="password"
				name="pwd" id="pwd" value="<%=rs.getString("pwd")%>" class="input1"><br></br>
			<span>邮箱： </span><input type="text"
				name="email" id="email" value="<%=rs.getString("email")%>" class="input1"><br></br>
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
				value="确认修改" onclick="commin()"> <input
				class="btn1" type="button"
				value="取消修改" onclick="window.location.href='user-all.jsp'">

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