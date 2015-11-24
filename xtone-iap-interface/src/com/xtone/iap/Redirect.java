package com.xtone.iap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.xml.crypto.Data;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xtone.server.ClientServer;

public class Redirect {
  
  private final static Logger LOG = Logger.getLogger("Redirect.class");

  private String postContent;
  private String postUrl;
  private String resultFromPost;
  private String httpGetSendUrl;
  private String clientUrl;
  private String mdecodedString;
  private int msgId;
  
public int getMsgId() {
	return msgId;
}

public void setMsgId(int msgId) {
	this.msgId = msgId;
}

public String getClientUrl() {
	return clientUrl;
}

public String getMdecodedString() {
	return mdecodedString;
}

public void setMdecodedString(String mdecodedString) {
	this.mdecodedString = mdecodedString;
}

public void setClientUrl(String clientUrl) {
	this.clientUrl = clientUrl;
}

private RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000)
      .setSocketTimeout(5000).build();
  private ContentSendToApple mContentSendToApple = new ContentSendToApple();
  private ReceiveFromClient mReceiveFromClient;
  private ReceiveFromMsg mReceiveFromMsg ;
  

  public String getHttpGetSendUrl() {
    return httpGetSendUrl;
  }

  public void setHttpGetSendUrl(String httpGetSendUrl) {
    this.httpGetSendUrl = httpGetSendUrl;
  }

  public String getPostUrl() {
    return postUrl;
  }

  public void setPostUrl(String postUrl) {
    this.postUrl = postUrl;
  }

  public String getPostContent() {
    return postContent;
  }

  public void setPostContent(String postContent) {
    this.postContent = postContent;
  }

  public String post()  {
    HttpClient client = new DefaultHttpClient();
    HttpPost request = new HttpPost(postUrl);
    String result = null;
    
    request.setConfig(requestConfig);
    
    Gson gson = new Gson();
    System.out.println("post:"+postContent);
    mReceiveFromClient = gson.fromJson(postContent, ReceiveFromClient.class);
    mContentSendToApple.setContent(mReceiveFromClient.getContent());
    try {
    	HttpEntity entity = new ByteArrayEntity(gson.toJson(mContentSendToApple).getBytes("UTF-8"));
        
        request.setEntity(entity);
        HttpResponse response = client.execute(request);
        result = EntityUtils.toString(response.getEntity());
        resultFromPost = result;
	} catch (Exception e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
		this.downloadAppleMsg(e.getMessage(), getMsgId());
	}
    
    //byte[] decodedBytes = Base64.decodeBase64(mReceiveFromClient.getContent());
    //todo split sandbox 
    //this.setMdecodedString(new String(decodedBytes));
//    System.out.println("decodeString:"+decodedString);
//    redirect.dowloadMsg(decodedString);
    System.out.println("post result:"+result);
    System.out.println("post id:"+getMsgId());
    this.downloadAppleMsg(result, getMsgId());
    return result;
  }
  
  

  public String sendHttpGetToLeo(){
    CloseableHttpClient client = HttpClientBuilder.create().build();

    byte[] encodedBytes = Base64.encodeBase64(resultFromPost.getBytes());
    String encodedString = new String(encodedBytes);
    String getUrl = httpGetSendUrl + "?VerifyStr=" + 
    	encodedString+"&WorldID="+mReceiveFromClient.getWorldID()+"&Uin="+mReceiveFromClient.getuIn();
    HttpGet request = new HttpGet(getUrl);
    LOG.debug("get begin:" + getUrl);
    request.setConfig(requestConfig);
    try {
    	CloseableHttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
          InputStream instream = entity.getContent();
          String str = convertStreamToString(instream);
          LOG.debug(str);
          
          // Do not need the rest
          request.abort();
          return str;
        }
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("error: "+e.getMessage());
		e.printStackTrace();
		return e.getMessage();
	}
    return null;
    
  }

  private String convertStreamToString(InputStream is) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();

    String line = null;
    try {
      while ((line = reader.readLine()) != null) {
        sb.append(line + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return sb.toString();
  }
  
  public void dowloadMsg(String msg) throws UnsupportedEncodingException
  {
	  Gson gson = new Gson();
	  //byte[] decodedBytes = Base64.decodeBase64(mReceiveFromClient.getContent());
	    //todo split sandbox 
	  //this.setMdecodedString(new String(decodedBytes));
	 // mReceiveFromClient = gson.fromJson(postContent, ReceiveFromClient.class);
	  msg = msg.substring(0, msg.lastIndexOf(";"));
	  msg = msg+"}";
	  System.out.println("msg3"+msg);
//	  msg = "{\"signature\" = \"Ak3kpGLSXPxWs72jqJfTRNw42NxRsryu42Nt5iQMSWtVrvGME8uWAxbXZEj3wVACxHHn85sPXGOH68bT5rNes4SBiTcilw9uthaxvWZGusooexkVqn+ee0Mq9Xcyq8NfCvoyeAQqE3JUfJDqD90T2xf9Myjs0Bj6YLJVEZivUvr4AAADVzCCA1MwggI7oAMCAQICCBup4+PAhm/LMA0GCSqGSIb3DQEBBQUAMH8xCzAJBgNVBAYTAlVTMRMwEQYDVQQKDApBcHBsZSBJbmMuMSYwJAYDVQQLDB1BcHBsZSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTEzMDEGA1UEAwwqQXBwbGUgaVR1bmVzIFN0b3JlIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MB4XDTE0MDYwNzAwMDIyMVoXDTE2MDUxODE4MzEzMFowZDEjMCEGA1UEAwwaUHVyY2hhc2VSZWNlaXB0Q2VydGlmaWNhdGUxGzAZBgNVBAsMEkFwcGxlIGlUdW5lcyBTdG9yZTETMBEGA1UECgwKQXBwbGUgSW5jLjELMAkGA1UEBhMCVVMwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAMmTEuLgjimLwRJxy1oEf0esUNDVEIe6wDsnnal14hNBt1v195X6n93YO7gi3orPSux9D554SkMp+Sayg84lTc362UtmYLpWnb34nqyGx9KBVTy5OGV4ljE1OwC+oTnRM+QLRCmeNxMbPZhS47T+eZtDEhVB9usk3+JM2Cogfwo7AgMBAAGjcjBwMB0GA1UdDgQWBBSJaEeNuq9Df6ZfN68Fe+I2u22ssDAMBgNVHRMBAf8EAjAAMB8GA1UdIwQYMBaAFDYd6OKdgtIBGLUyaw7XQwuRWEM6MA4GA1UdDwEB/wQEAwIHgDAQBgoqhkiG92NkBgUBBAIFADANBgkqhkiG9w0BAQUFAAOCAQEAeaJV2U51rxfcqAAe5C2/fEW8KUl4iO4lMuta7N6XzP1pZIz1NkkCtIIweyNj5URYHK+HjRKSU9RLguNl0nkfxqObiMckwRudKSq69NInrZyCD66R4K77nb9lMTABSSYlsKt8oNtlhgR/1kjSSRQcHktsDcSiQGKMdkSlp4AyXf7vnHPBe4yCwYV2PpSN04kboiJ3pBlxsGwV/ZlL26M2ueYHKYCuXhdqFwxVgm52h3oeJOOt/vY4EcQq7eqHm6m03Z9b7PRzYM2KGXHDmOMk7vDpeMVlLDPSGYz1+U3sDxJzebSpbaJmT7imzUKfggEY7xxf4czfH0yj5wNzSGTOvQ==\";\"purchase-info\" = \"ewoJIm9yaWdpbmFsLXB1cmNoYXNlLWRhdGUtcHN0IiA9ICIyMDE1LTExLTEyIDIyOjQ4OjA3IEFtZXJpY2EvTG9zX0FuZ2VsZXMiOwoJInVuaXF1ZS1pZGVudGlmaWVyIiA9ICI5ZTczMzA1OTY0YzIxOTU3ZDQzYmY3MjhhODQwYTc0MmU1ODEwMDA2IjsKCSJvcmlnaW5hbC10cmFuc2FjdGlvbi1pZCIgPSAiMTAwMDAwMDE4MDA2NzYzOSI7CgkiYnZycyIgPSAiMS4wIjsKCSJ0cmFuc2FjdGlvbi1pZCIgPSAiMTAwMDAwMDE4MDA2NzYzOSI7CgkicXVhbnRpdHkiID0gIjEiOwoJIm9yaWdpbmFsLXB1cmNoYXNlLWRhdGUtbXMiID0gIjE0NDczOTcyODcwNjgiOwoJInVuaXF1ZS12ZW5kb3ItaWRlbnRpZmllciIgPSAiREVDNUJDNkUtMjAyRi00MjUyLTk4NkYtOTMyMjgzODBCQjIyIjsKCSJwcm9kdWN0LWlkIiA9ICJjb20ueHRvbmVnYW1lLnB1enpsZWJsYXN0LjEyODBnb2xkY29pbnMiOwoJIml0ZW0taWQiID0gIjEwNTgxOTYxMzUiOwoJImJpZCIgPSAiY29tLnh0b25lZ2FtZS5wdXp6bGVibGFzdCI7CgkicHVyY2hhc2UtZGF0ZS1tcyIgPSAiMTQ0NzM5NzI4NzA2OCI7CgkicHVyY2hhc2UtZGF0ZSIgPSAiMjAxNS0xMS0xMyAwNjo0ODowNyBFdGMvR01UIjsKCSJwdXJjaGFzZS1kYXRlLXBzdCIgPSAiMjAxNS0xMS0xMiAyMjo0ODowNyBBbWVyaWNhL0xvc19BbmdlbGVzIjsKCSJvcmlnaW5hbC1wdXJjaGFzZS1kYXRlIiA9ICIyMDE1LTExLTEzIDA2OjQ4OjA3IEV0Yy9HTVQiOwp9\";\"environment\" = \"Sandbox\";\"pod\" = \"100\";\"signing-status\" = \"0\"}";
	  mReceiveFromMsg = gson.fromJson(msg, ReceiveFromMsg.class);
	  System.out.println("signature:"+mReceiveFromMsg.getSignature());
	  byte[] mpurchaseInfo = Base64.decodeBase64(mReceiveFromMsg.getPurchaseInfo());
	  String purchaseInfo = new String(mpurchaseInfo);
	  LOG.debug(purchaseInfo);
	  mReceiveFromMsg.setPurchaseInfo(purchaseInfo);
	  Date date = new Date();
	  long time = date.getTime();
	  mReceiveFromMsg.setIp(this.getClientUrl());
	  System.out.println(new ClientServer().insertMsg(mReceiveFromMsg, time));
	  
//	  LOG.debug(time);
//	  LOG.debug(signature+";"+purchaseInfo+";"+environment+";"+pod+";"+signingStatus);
	  
  }
  
  public String postApple() throws Exception {
	    HttpClient client = new DefaultHttpClient();
	    HttpPost request = new HttpPost(postUrl);
	    
	    request.setConfig(requestConfig);
	    
	    Gson gson = new Gson();
	    System.out.println("post:"+postContent);
	    mReceiveFromClient = gson.fromJson(postContent, ReceiveFromClient.class);
	    mContentSendToApple.setContent(mReceiveFromClient.getContent());
	    HttpEntity entity = new ByteArrayEntity(gson.toJson(mContentSendToApple).getBytes("UTF-8"));
	    
	    request.setEntity(entity);
	    HttpResponse response = client.execute(request);
	    String result = EntityUtils.toString(response.getEntity());
	    resultFromPost = result;
	    
	    
	    byte[] decodedBytes = Base64.decodeBase64(mReceiveFromClient.getContent());
	    //todo split sandbox 
	    this.setMdecodedString(new String(decodedBytes));
//	    System.out.println("decodeString:"+decodedString);
//	    redirect.dowloadMsg(decodedString);
	    return result;
	  }
  
  public Integer dowloadMsgTest() 
  {
	  System.out.println("download");
	  Gson gson = new Gson();
	  System.out.println("postContent:"+postContent);
	  mReceiveFromClient = gson.fromJson(postContent, ReceiveFromClient.class);
	  System.out.println("mReceiveFromClient: "+mReceiveFromClient);
	  byte[] decodedBytes = Base64.decodeBase64(mReceiveFromClient.getContent());
	    //todo split sandbox 
	  String msg = new String(decodedBytes);
	  msg = msg.substring(0, msg.lastIndexOf(";"));
	  msg = msg+"}";
	  System.out.println("msg3"+msg);
//	  msg = "{\"signature\" = \"Ak3kpGLSXPxWs72jqJfTRNw42NxRsryu42Nt5iQMSWtVrvGME8uWAxbXZEj3wVACxHHn85sPXGOH68bT5rNes4SBiTcilw9uthaxvWZGusooexkVqn+ee0Mq9Xcyq8NfCvoyeAQqE3JUfJDqD90T2xf9Myjs0Bj6YLJVEZivUvr4AAADVzCCA1MwggI7oAMCAQICCBup4+PAhm/LMA0GCSqGSIb3DQEBBQUAMH8xCzAJBgNVBAYTAlVTMRMwEQYDVQQKDApBcHBsZSBJbmMuMSYwJAYDVQQLDB1BcHBsZSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTEzMDEGA1UEAwwqQXBwbGUgaVR1bmVzIFN0b3JlIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MB4XDTE0MDYwNzAwMDIyMVoXDTE2MDUxODE4MzEzMFowZDEjMCEGA1UEAwwaUHVyY2hhc2VSZWNlaXB0Q2VydGlmaWNhdGUxGzAZBgNVBAsMEkFwcGxlIGlUdW5lcyBTdG9yZTETMBEGA1UECgwKQXBwbGUgSW5jLjELMAkGA1UEBhMCVVMwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAMmTEuLgjimLwRJxy1oEf0esUNDVEIe6wDsnnal14hNBt1v195X6n93YO7gi3orPSux9D554SkMp+Sayg84lTc362UtmYLpWnb34nqyGx9KBVTy5OGV4ljE1OwC+oTnRM+QLRCmeNxMbPZhS47T+eZtDEhVB9usk3+JM2Cogfwo7AgMBAAGjcjBwMB0GA1UdDgQWBBSJaEeNuq9Df6ZfN68Fe+I2u22ssDAMBgNVHRMBAf8EAjAAMB8GA1UdIwQYMBaAFDYd6OKdgtIBGLUyaw7XQwuRWEM6MA4GA1UdDwEB/wQEAwIHgDAQBgoqhkiG92NkBgUBBAIFADANBgkqhkiG9w0BAQUFAAOCAQEAeaJV2U51rxfcqAAe5C2/fEW8KUl4iO4lMuta7N6XzP1pZIz1NkkCtIIweyNj5URYHK+HjRKSU9RLguNl0nkfxqObiMckwRudKSq69NInrZyCD66R4K77nb9lMTABSSYlsKt8oNtlhgR/1kjSSRQcHktsDcSiQGKMdkSlp4AyXf7vnHPBe4yCwYV2PpSN04kboiJ3pBlxsGwV/ZlL26M2ueYHKYCuXhdqFwxVgm52h3oeJOOt/vY4EcQq7eqHm6m03Z9b7PRzYM2KGXHDmOMk7vDpeMVlLDPSGYz1+U3sDxJzebSpbaJmT7imzUKfggEY7xxf4czfH0yj5wNzSGTOvQ==\";\"purchase-info\" = \"ewoJIm9yaWdpbmFsLXB1cmNoYXNlLWRhdGUtcHN0IiA9ICIyMDE1LTExLTEyIDIyOjQ4OjA3IEFtZXJpY2EvTG9zX0FuZ2VsZXMiOwoJInVuaXF1ZS1pZGVudGlmaWVyIiA9ICI5ZTczMzA1OTY0YzIxOTU3ZDQzYmY3MjhhODQwYTc0MmU1ODEwMDA2IjsKCSJvcmlnaW5hbC10cmFuc2FjdGlvbi1pZCIgPSAiMTAwMDAwMDE4MDA2NzYzOSI7CgkiYnZycyIgPSAiMS4wIjsKCSJ0cmFuc2FjdGlvbi1pZCIgPSAiMTAwMDAwMDE4MDA2NzYzOSI7CgkicXVhbnRpdHkiID0gIjEiOwoJIm9yaWdpbmFsLXB1cmNoYXNlLWRhdGUtbXMiID0gIjE0NDczOTcyODcwNjgiOwoJInVuaXF1ZS12ZW5kb3ItaWRlbnRpZmllciIgPSAiREVDNUJDNkUtMjAyRi00MjUyLTk4NkYtOTMyMjgzODBCQjIyIjsKCSJwcm9kdWN0LWlkIiA9ICJjb20ueHRvbmVnYW1lLnB1enpsZWJsYXN0LjEyODBnb2xkY29pbnMiOwoJIml0ZW0taWQiID0gIjEwNTgxOTYxMzUiOwoJImJpZCIgPSAiY29tLnh0b25lZ2FtZS5wdXp6bGVibGFzdCI7CgkicHVyY2hhc2UtZGF0ZS1tcyIgPSAiMTQ0NzM5NzI4NzA2OCI7CgkicHVyY2hhc2UtZGF0ZSIgPSAiMjAxNS0xMS0xMyAwNjo0ODowNyBFdGMvR01UIjsKCSJwdXJjaGFzZS1kYXRlLXBzdCIgPSAiMjAxNS0xMS0xMiAyMjo0ODowNyBBbWVyaWNhL0xvc19BbmdlbGVzIjsKCSJvcmlnaW5hbC1wdXJjaGFzZS1kYXRlIiA9ICIyMDE1LTExLTEzIDA2OjQ4OjA3IEV0Yy9HTVQiOwp9\";\"environment\" = \"Sandbox\";\"pod\" = \"100\";\"signing-status\" = \"0\"}";
	  mReceiveFromMsg = gson.fromJson(msg, ReceiveFromMsg.class);
	  System.out.println("signature:"+mReceiveFromMsg.getSignature());
	  byte[] mpurchaseInfo = Base64.decodeBase64(mReceiveFromMsg.getPurchaseInfo());
	  String purchaseInfo = new String(mpurchaseInfo);
	  LOG.debug(purchaseInfo);
	  mReceiveFromMsg.setPurchaseInfo(purchaseInfo);
	  Date date = new Date();
	  long time = date.getTime();
	  mReceiveFromMsg.setIp(this.getClientUrl());
	  ClientServer server = new ClientServer();
	  System.out.println(server.insertMsg(mReceiveFromMsg, time));
	  return server.getLastInsertId();
//	  LOG.debug(time);
//	  LOG.debug(signature+";"+purchaseInfo+";"+environment+";"+pod+";"+signingStatus);
	  
  }
  
  public void downloadAppleMsg(String appleMsg,int id)
  {
	  Date date = new Date();
	  long time = date.getTime();
	  System.out.println("update="+new ClientServer().updateAppleMsg(id, appleMsg, time));
  }
  
  public void downloadResponse(String result)
  {
	  Date date = new Date();
	  long time = date.getTime();
	  System.out.println("response: "+new ClientServer().updateResponse(result, getMsgId(), getHttpGetSendUrl(),time));
  }

  public static void main(String[] args) {
    try {
      Redirect redirect = new Redirect();
      //貌似需要转个码才好测试...
      redirect
          .setPostContent("{\"receipt-data\":\"ewoJInNpZ25hdHVyZSIgPSAiQWsza3BHTFNYUHhXczcyanFKZlRSTnc0Mk54UnNyeXU0Mk50NWlRTVNXdFZydkdNRTh1V0F4YlhaRWozd1ZBQ3hISG44NXNQWEdPSDY4YlQ1ck5lczRTQmlUY2lsdzl1dGhheHZXWkd1c29vZXhrVnFuK2VlME1xOVhjeXE4TmZDdm95ZUFRcUUzSlVmSkRxRDkwVDJ4ZjlNeWpzMEJqNllMSlZFWml2VXZyNEFBQURWekNDQTFNd2dnSTdvQU1DQVFJQ0NCdXA0K1BBaG0vTE1BMEdDU3FHU0liM0RRRUJCUVVBTUg4eEN6QUpCZ05WQkFZVEFsVlRNUk13RVFZRFZRUUtEQXBCY0hCc1pTQkpibU11TVNZd0pBWURWUVFMREIxQmNIQnNaU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVURXpNREVHQTFVRUF3d3FRWEJ3YkdVZ2FWUjFibVZ6SUZOMGIzSmxJRU5sY25ScFptbGpZWFJwYjI0Z1FYVjBhRzl5YVhSNU1CNFhEVEUwTURZd056QXdNREl5TVZvWERURTJNRFV4T0RFNE16RXpNRm93WkRFak1DRUdBMVVFQXd3YVVIVnlZMmhoYzJWU1pXTmxhWEIwUTJWeWRHbG1hV05oZEdVeEd6QVpCZ05WQkFzTUVrRndjR3hsSUdsVWRXNWxjeUJUZEc5eVpURVRNQkVHQTFVRUNnd0tRWEJ3YkdVZ1NXNWpMakVMTUFrR0ExVUVCaE1DVlZNd2daOHdEUVlKS29aSWh2Y05BUUVCQlFBRGdZMEFNSUdKQW9HQkFNbVRFdUxnamltTHdSSnh5MW9FZjBlc1VORFZFSWU2d0Rzbm5hbDE0aE5CdDF2MTk1WDZuOTNZTzdnaTNvclBTdXg5RDU1NFNrTXArU2F5Zzg0bFRjMzYyVXRtWUxwV25iMzRucXlHeDlLQlZUeTVPR1Y0bGpFMU93QytvVG5STStRTFJDbWVOeE1iUFpoUzQ3VCtlWnRERWhWQjl1c2szK0pNMkNvZ2Z3bzdBZ01CQUFHamNqQndNQjBHQTFVZERnUVdCQlNKYUVlTnVxOURmNlpmTjY4RmUrSTJ1MjJzc0RBTUJnTlZIUk1CQWY4RUFqQUFNQjhHQTFVZEl3UVlNQmFBRkRZZDZPS2RndElCR0xVeWF3N1hRd3VSV0VNNk1BNEdBMVVkRHdFQi93UUVBd0lIZ0RBUUJnb3Foa2lHOTJOa0JnVUJCQUlGQURBTkJna3Foa2lHOXcwQkFRVUZBQU9DQVFFQWVhSlYyVTUxcnhmY3FBQWU1QzIvZkVXOEtVbDRpTzRsTXV0YTdONlh6UDFwWkl6MU5ra0N0SUl3ZXlOajVVUllISytIalJLU1U5UkxndU5sMG5rZnhxT2JpTWNrd1J1ZEtTcTY5Tkluclp5Q0Q2NlI0Szc3bmI5bE1UQUJTU1lsc0t0OG9OdGxoZ1IvMWtqU1NSUWNIa3RzRGNTaVFHS01ka1NscDRBeVhmN3ZuSFBCZTR5Q3dZVjJQcFNOMDRrYm9pSjNwQmx4c0d3Vi9abEwyNk0ydWVZSEtZQ3VYaGRxRnd4VmdtNTJoM29lSk9PdC92WTRFY1FxN2VxSG02bTAzWjliN1BSellNMktHWEhEbU9Nazd2RHBlTVZsTERQU0dZejErVTNzRHhKemViU3BiYUptVDdpbXpVS2ZnZ0VZN3h4ZjRjemZIMHlqNXdOelNHVE92UT09IjsKCSJwdXJjaGFzZS1pbmZvIiA9ICJld29KSW05eWFXZHBibUZzTFhCMWNtTm9ZWE5sTFdSaGRHVXRjSE4wSWlBOUlDSXlNREUxTFRFeExURXlJREl5T2pRNE9qQTNJRUZ0WlhKcFkyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkluVnVhWEYxWlMxcFpHVnVkR2xtYVdWeUlpQTlJQ0k1WlRjek16QTFPVFkwWXpJeE9UVTNaRFF6WW1ZM01qaGhPRFF3WVRjME1tVTFPREV3TURBMklqc0tDU0p2Y21sbmFXNWhiQzEwY21GdWMyRmpkR2x2YmkxcFpDSWdQU0FpTVRBd01EQXdNREU0TURBMk56WXpPU0k3Q2draVluWnljeUlnUFNBaU1TNHdJanNLQ1NKMGNtRnVjMkZqZEdsdmJpMXBaQ0lnUFNBaU1UQXdNREF3TURFNE1EQTJOell6T1NJN0Nna2ljWFZoYm5ScGRIa2lJRDBnSWpFaU93b0pJbTl5YVdkcGJtRnNMWEIxY21Ob1lYTmxMV1JoZEdVdGJYTWlJRDBnSWpFME5EY3pPVGN5T0Rjd05qZ2lPd29KSW5WdWFYRjFaUzEyWlc1a2IzSXRhV1JsYm5ScFptbGxjaUlnUFNBaVJFVkROVUpETmtVdE1qQXlSaTAwTWpVeUxUazROa1l0T1RNeU1qZ3pPREJDUWpJeUlqc0tDU0p3Y205a2RXTjBMV2xrSWlBOUlDSmpiMjB1ZUhSdmJtVm5ZVzFsTG5CMWVucHNaV0pzWVhOMExqRXlPREJuYjJ4a1kyOXBibk1pT3dvSkltbDBaVzB0YVdRaUlEMGdJakV3TlRneE9UWXhNelVpT3dvSkltSnBaQ0lnUFNBaVkyOXRMbmgwYjI1bFoyRnRaUzV3ZFhwNmJHVmliR0Z6ZENJN0Nna2ljSFZ5WTJoaGMyVXRaR0YwWlMxdGN5SWdQU0FpTVRRME56TTVOekk0TnpBMk9DSTdDZ2tpY0hWeVkyaGhjMlV0WkdGMFpTSWdQU0FpTWpBeE5TMHhNUzB4TXlBd05qbzBPRG93TnlCRmRHTXZSMDFVSWpzS0NTSndkWEpqYUdGelpTMWtZWFJsTFhCemRDSWdQU0FpTWpBeE5TMHhNUzB4TWlBeU1qbzBPRG93TnlCQmJXVnlhV05oTDB4dmMxOUJibWRsYkdWeklqc0tDU0p2Y21sbmFXNWhiQzF3ZFhKamFHRnpaUzFrWVhSbElpQTlJQ0l5TURFMUxURXhMVEV6SURBMk9qUTRPakEzSUVWMFl5OUhUVlFpT3dwOSI7CgkiZW52aXJvbm1lbnQiID0gIlNhbmRib3giOwoJInBvZCIgPSAiMTAwIjsKCSJzaWduaW5nLXN0YXR1cyIgPSAiMCI7Cn0=\";Uin:1032;WorldID:11}");
      redirect.setPostUrl("http://iap.n8wan.com:29141/leo/leo.jsp");

      //redirect.setPostUrl("http://localhost:8080/demo-interface/leo/leo.jsp");
      redirect.setPostUrl("http://localhost:8080/demo-interface/leo/leo.jsp");
      String msg = redirect.post();
//      System.out.println("msg2: "+msg);
//      redirect.dowloadMsg(msg);
//      redirect.setHttpGetSendUrl("http://121.40.243.171/charge/iphoneCharge.php");
//      redirect.sendHttpGetToLeo();

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
