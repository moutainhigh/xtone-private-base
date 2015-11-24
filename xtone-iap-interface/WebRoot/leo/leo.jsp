<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.xtone.iap.Redirect"%>
<%@ include file="inc-receive-body.jsp"%>
<%
Redirect redirect = new Redirect();
redirect.setPostContent(info);
if(request.getHeader("X-Real-IP")!=null){
	redirect.setClientUrl(request.getHeader("X-Real-IP"));
	System.out.println("IP");
}else{
	redirect.setClientUrl(request.getRemoteAddr());
	System.out.println("IP");
}
redirect.setMsgId(redirect.dowloadMsgTest());
/*  
   对苹果地址判断的方法 
*/

String appleResult = redirect.post();
System.out.println("result:"+appleResult);

redirect.setHttpGetSendUrl("http://120.24.220.180/");
redirect.downloadResponse(redirect.sendHttpGetToLeo());

redirect.setHttpGetSendUrl("http://121.40.243.171/charge/iphoneCharge.php");
redirect.sendHttpGetToLeo();
%>