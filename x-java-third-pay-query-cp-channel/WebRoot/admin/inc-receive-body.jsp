<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="org.apache.log4j.Logger"%>
<%
// String info = null;
// int len = 0;
// int temp = 0;
// InputStream is = request.getInputStream();
// byte[] b = new byte[1000000];
// while ((temp = is.read()) != -1) {
//   b[len] = (byte) temp;
//   len++;
// }
// is.close();
// info = new String(b, 0, len, "utf-8");
// System.out.println("####notice:\n" + info);
// System.out.println("####end:");
// Logger LOG = Logger.getLogger(this.getClass());

%>
<%
String info = null;
BufferedReader br = null;
InputStreamReader isr = null;
InputStream is = null;
StringBuffer result = null;

try
{
	is = request.getInputStream();
	isr = new InputStreamReader(is);
	br = new BufferedReader(isr);
	result = new StringBuffer();
	String line = "";
	while ((line = br.readLine()) != null)
	{
		result.append(line);
	}
}
catch(Exception ex)
{
	ex.printStackTrace();
}
finally
{
	try{if(br!=null)br.close();}catch(Exception ex){}
	try{if(isr!=null)isr.close();}catch(Exception ex){}
	try{if(is!=null)is.close();}catch(Exception ex){}
}

info = result.toString().trim();
Logger LOG = Logger.getLogger(this.getClass());
LOG.info(info);

%>