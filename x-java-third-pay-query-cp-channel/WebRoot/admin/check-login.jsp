<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="org.common.util.ConnectionService"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.demo.service.UserService"%>
<%@page import="org.demo.json.LoginRsp"%>
<%@page import="org.demo.info.User"%>
<%@page import="com.google.gson.LongSerializationPolicy"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<!-- 
<!-- // 	//   String userName = new String(request.getParameter("username").trim()); -->
<!-- // 	//   String pass = new String(request.getParameter("password").trim()); -->
<!-- // 	//   //String roomCode = new String(request.getParameter("roomCode").trim()); -->
<!-- // 	//   UserService userService = new UserService(); -->
<!-- // 	//   //check user name  -->
<!-- // 	//   User user = new User(); -->
<!-- // 	//   user.setUserName(userName); -->
<!-- // 	//   long userId = userService.checkLoginUser(pass, user); -->
<!-- // 	//   //System.out.println(userId); -->
<!-- // 	//   if (userId > 0) { -->
<!-- // 	//     request.getSession(true); -->
<!-- // 	//     //add global cache when begin change -->
<!-- // 	//     user = userService.getUserById(userId); -->
<!-- // 	//     session.setAttribute("user", user); -->
<!-- // 	//     //    response.sendRedirect("control.jsp?roomCode="+roomCode); -->
<!-- // 	//     if (session.getAttribute("lastFileName") != null -->
<!-- // 	//         && session.getAttribute("lastFileName").toString().length() > 0) { -->
<!-- // 	//       response.sendRedirect(session.getAttribute("lastFileName").toString()); -->
<!-- // 	//       session.removeAttribute("lastFileName"); -->
<!-- // 	//       return; -->
<!-- // 	//     } else { -->
<!--        response.sendRedirect("console.jsp"); -->
<!--        return; -->
<!--      } -->
<!--    } else { -->
<!--      out.print("<script>alert('Wrong Username or Password!');location.href='login.jsp';</script>"); -->
<!--     return; -->
<!--  	   } 
-->
<%@ include file="inc-receive-body.jsp"%>
<%
  User user = null;
  PreparedStatement ps = null;
  Connection con = null;
  ResultSet rs = null;

  Date date = new Date();

  try {

    OutputStream os = response.getOutputStream();
    OutputStreamWriter osw = new OutputStreamWriter(os);
    BufferedWriter bw = new BufferedWriter(osw);
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
    Gson gson = gsonBuilder.create();
    user = gson.fromJson(info, User.class);
    UserService userService = new UserService();

    User user2 = userService.checkLoginUser(user);

    if (user2 != null) {
      LoginRsp loginRsp = new LoginRsp();
      loginRsp.setData(user2);
      loginRsp.setStatus("success");
      String rsp = gson.toJson(loginRsp);
      request.getSession(true);
      session.setAttribute("user", user2);

      System.out.println(rsp);
      bw.write(rsp);

      con = ConnectionService.getInstance().getConnectionForLocal();
      String sql = "UPDATE tbl_thirdpay_cp_channel_users SET lastLogin=? WHERE id=?";
      ps = con.prepareStatement(sql);
      ps.setLong(1, date.getTime());
      ps.setLong(2, user2.getId());
      ps.executeUpdate();

    } else {
      bw.write("{\"status\":\"error\"}");
    }

    bw.flush();

    bw.close();

    out.clear();
    out = pageContext.pushBody();

  } catch (Exception e) {
    e.printStackTrace();
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