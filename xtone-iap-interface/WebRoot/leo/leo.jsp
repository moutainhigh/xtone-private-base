<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.xtone.iap.Redirect"%>
<%@ include file="inc-receive-body.jsp"%>
<%
Redirect redirect = new Redirect();
redirect.setPostContent(info);
redirect.setPostUrl("https://sandbox.itunes.apple.com/verifyReceipt");
out.println(redirect.post());
redirect.setHttpGetSendUrl("http://120.24.220.180/");
redirect.sendHttpGetToLeo();
redirect.setHttpGetSendUrl("http://121.40.243.171/charge/iphoneCharge.php");
redirect.sendHttpGetToLeo();
%>