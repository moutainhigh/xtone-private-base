package com.xtone.iap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
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

import com.google.gson.Gson;
import com.xtone.server.ClientServer;

/**
 * 该类为工具类，用于给leo.jsp提供相关方法
 * 
 * @author hongjiabin
 * @ClassName: Redirect.java
 * @data 2015-11-24
 * @version 1.0 2015-11-24
 * @company: xtone
 */
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

  private RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000)
      .setConnectTimeout(5000).setSocketTimeout(5000).build();
  private ContentSendToApple mContentSendToApple = new ContentSendToApple();
  private ReceiveFromClient mReceiveFromClient;
  private ReceiveFromMsg mReceiveFromMsg;

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

  /**
   * 该方法用于对苹果验证网址发出post请求，并 将苹果返回的数据以字符串的形式返回给调用者
   * ，在post请求发出后会调用Redirect的downloadAppleMsg 将苹果返还的相关数据进行持久化
   * 
   * @return 苹果返还的相关信息
   */
  public String post() {
    HttpClient client = new DefaultHttpClient();
    HttpPost request = new HttpPost(postUrl);
    String result = null;

    request.setConfig(requestConfig);

    Gson gson = new Gson();
    System.out.println("post:" + postContent);
    mReceiveFromClient = gson.fromJson(postContent, ReceiveFromClient.class);
    mContentSendToApple.setContent(mReceiveFromClient.getContent());
    try {
      HttpEntity entity = new ByteArrayEntity(gson.toJson(mContentSendToApple).getBytes("UTF-8"));

      request.setEntity(entity);
      HttpResponse response = client.execute(request);
      result = EntityUtils.toString(response.getEntity());
      resultFromPost = result;
    } catch (Exception e) {
      e.printStackTrace();
      this.downloadAppleMsg(e.getMessage(), getMsgId());
    }

    // byte[] decodedBytes =
    // Base64.decodeBase64(mReceiveFromClient.getContent());
    // todo split sandbox
    // this.setMdecodedString(new String(decodedBytes));
    // System.out.println("decodeString:"+decodedString);
    // redirect.dowloadMsg(decodedString);
    System.out.println("post result:" + result);
    System.out.println("post id:" + getMsgId());
    this.downloadAppleMsg(result, getMsgId());
    return result;
  }

  /**
   * 该类用于像CP服务端发送请求， 将服务端返还的数据以字符串的 形式返还给调用方
   * 
   * @return CP服务端反馈信息
   */
  public String sendHttpGetToLeo() {
    CloseableHttpClient client = HttpClientBuilder.create().build();

    byte[] encodedBytes = Base64.encodeBase64(resultFromPost.getBytes());
    String encodedString = new String(encodedBytes);
    String getUrl = httpGetSendUrl + "?VerifyStr=" + encodedString + "&WorldID=" + mReceiveFromClient.getWorldID()
        + "&Uin=" + mReceiveFromClient.getuIn();
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
      System.out.println("error: " + e.getMessage());
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

  public void dowloadMsg(String msg) throws UnsupportedEncodingException {
    Gson gson = new Gson();
    msg = msg.substring(0, msg.lastIndexOf(";"));
    msg = msg + "}";
    mReceiveFromMsg = gson.fromJson(msg, ReceiveFromMsg.class);
    byte[] mpurchaseInfo = Base64.decodeBase64(mReceiveFromMsg.getPurchaseInfo());
    String purchaseInfo = new String(mpurchaseInfo);
    LOG.debug(purchaseInfo);
    mReceiveFromMsg.setPurchaseInfo(purchaseInfo);
    Date date = new Date();
    long time = date.getTime();

    // if()
    //
    // this.setPostUrl(mReceiveFromMsg.getEnvironment());
    new ClientServer().insertMsg(mReceiveFromMsg, time);

    // LOG.debug(time);
    // LOG.debug(signature+";"+purchaseInfo+";"+environment+";"+pod+";"+signingStatus);

  }

  public String postApple() throws Exception {
    HttpClient client = new DefaultHttpClient();
    HttpPost request = new HttpPost(postUrl);

    request.setConfig(requestConfig);

    Gson gson = new Gson();
    System.out.println("post:" + postContent);
    mReceiveFromClient = gson.fromJson(postContent, ReceiveFromClient.class);
    mContentSendToApple.setContent(mReceiveFromClient.getContent());
    HttpEntity entity = new ByteArrayEntity(gson.toJson(mContentSendToApple).getBytes("UTF-8"));

    request.setEntity(entity);
    HttpResponse response = client.execute(request);
    String result = EntityUtils.toString(response.getEntity());
    resultFromPost = result;

    byte[] decodedBytes = Base64.decodeBase64(mReceiveFromClient.getContent());
    // todo split sandbox
    this.setMdecodedString(new String(decodedBytes));
    // System.out.println("decodeString:"+decodedString);
    // redirect.dowloadMsg(decodedString);
    return result;
  }

  /**
   * 该方法用于将客户端的验证信息进行 解码后，转化成ReceiveFromClient对象 并将对象中的信息存入数据库
   * 
   * @return 该用户数据的ID
   */
  public Integer dowloadMsgTest() {
    Gson gson = new Gson();
    mReceiveFromClient = gson.fromJson(postContent, ReceiveFromClient.class);
    byte[] decodedBytes = Base64.decodeBase64(mReceiveFromClient.getContent());
    // todo split sandbox
    String msg = new String(decodedBytes);
    msg = msg.substring(0, msg.lastIndexOf(";"));
    msg = msg + "}";
    mReceiveFromMsg = gson.fromJson(msg, ReceiveFromMsg.class);
    byte[] mpurchaseInfo = Base64.decodeBase64(mReceiveFromMsg.getPurchaseInfo());
    String purchaseInfo = new String(mpurchaseInfo);
    LOG.debug(purchaseInfo);
    mReceiveFromMsg.setPurchaseInfo(purchaseInfo);
    Date date = new Date();
    long time = date.getTime();
    mReceiveFromMsg.setIp(this.getClientUrl());
    mReceiveFromMsg.setIp(this.getClientUrl());
    String enviroment = mReceiveFromMsg.getEnvironment();
    if (enviroment != null && enviroment.equals("Sandbox")) {
      this.setPostUrl("https://sandbox.itunes.apple.com/verifyReceipt");
    } else {
      this.setPostUrl("https://buy.itunes.apple.com/verifyReceipt");
    }
    ClientServer server = new ClientServer();
    return server.insertMsg(mReceiveFromMsg, time);
    // System.out.println("get id:"+server.getLastInsertId());
    // return server.getLastInsertId();

  }

  /**
   * 该方法用于将苹果验证网址返还的数据 进行持久化，该方法有两个参数，applMsg 是post返还的验证字符串，id则为dowloadMsgTest
   * 在存储信息时产生的用户ID
   * 
   * @param appleMsg
   * @param id
   */
  public void downloadAppleMsg(String appleMsg, int id) {
    Date date = new Date();
    long time = date.getTime();
    new ClientServer().updateAppleMsg(id, appleMsg, time);
  }

  /**
   * 该方法用于将CP服务端返还的数据 进行持久化
   * 
   * @param result
   *          在给
   */
  public void downloadResponse(String result) {
    Date date = new Date();
    long time = date.getTime();
    new ClientServer().updateResponse(result, getMsgId(), getHttpGetSendUrl(), time);
  }

  public static void main(String[] args) {
    try {
      Redirect redirect = new Redirect();
      // 貌似需要转个码才好测试...
      redirect
          .setPostContent("{\"receipt-data\":\"ewoJInNpZ25hdHVyZSIgPSAiQWsza3BHTFNYUHhXczcyanFKZlRSTnc0Mk54UnNyeXU0Mk50NWlRTVNXdFZydkdNRTh1V0F4YlhaRWozd1ZBQ3hISG44NXNQWEdPSDY4YlQ1ck5lczRTQmlUY2lsdzl1dGhheHZXWkd1c29vZXhrVnFuK2VlME1xOVhjeXE4TmZDdm95ZUFRcUUzSlVmSkRxRDkwVDJ4ZjlNeWpzMEJqNllMSlZFWml2VXZyNEFBQURWekNDQTFNd2dnSTdvQU1DQVFJQ0NCdXA0K1BBaG0vTE1BMEdDU3FHU0liM0RRRUJCUVVBTUg4eEN6QUpCZ05WQkFZVEFsVlRNUk13RVFZRFZRUUtEQXBCY0hCc1pTQkpibU11TVNZd0pBWURWUVFMREIxQmNIQnNaU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVURXpNREVHQTFVRUF3d3FRWEJ3YkdVZ2FWUjFibVZ6SUZOMGIzSmxJRU5sY25ScFptbGpZWFJwYjI0Z1FYVjBhRzl5YVhSNU1CNFhEVEUwTURZd056QXdNREl5TVZvWERURTJNRFV4T0RFNE16RXpNRm93WkRFak1DRUdBMVVFQXd3YVVIVnlZMmhoYzJWU1pXTmxhWEIwUTJWeWRHbG1hV05oZEdVeEd6QVpCZ05WQkFzTUVrRndjR3hsSUdsVWRXNWxjeUJUZEc5eVpURVRNQkVHQTFVRUNnd0tRWEJ3YkdVZ1NXNWpMakVMTUFrR0ExVUVCaE1DVlZNd2daOHdEUVlKS29aSWh2Y05BUUVCQlFBRGdZMEFNSUdKQW9HQkFNbVRFdUxnamltTHdSSnh5MW9FZjBlc1VORFZFSWU2d0Rzbm5hbDE0aE5CdDF2MTk1WDZuOTNZTzdnaTNvclBTdXg5RDU1NFNrTXArU2F5Zzg0bFRjMzYyVXRtWUxwV25iMzRucXlHeDlLQlZUeTVPR1Y0bGpFMU93QytvVG5STStRTFJDbWVOeE1iUFpoUzQ3VCtlWnRERWhWQjl1c2szK0pNMkNvZ2Z3bzdBZ01CQUFHamNqQndNQjBHQTFVZERnUVdCQlNKYUVlTnVxOURmNlpmTjY4RmUrSTJ1MjJzc0RBTUJnTlZIUk1CQWY4RUFqQUFNQjhHQTFVZEl3UVlNQmFBRkRZZDZPS2RndElCR0xVeWF3N1hRd3VSV0VNNk1BNEdBMVVkRHdFQi93UUVBd0lIZ0RBUUJnb3Foa2lHOTJOa0JnVUJCQUlGQURBTkJna3Foa2lHOXcwQkFRVUZBQU9DQVFFQWVhSlYyVTUxcnhmY3FBQWU1QzIvZkVXOEtVbDRpTzRsTXV0YTdONlh6UDFwWkl6MU5ra0N0SUl3ZXlOajVVUllISytIalJLU1U5UkxndU5sMG5rZnhxT2JpTWNrd1J1ZEtTcTY5Tkluclp5Q0Q2NlI0Szc3bmI5bE1UQUJTU1lsc0t0OG9OdGxoZ1IvMWtqU1NSUWNIa3RzRGNTaVFHS01ka1NscDRBeVhmN3ZuSFBCZTR5Q3dZVjJQcFNOMDRrYm9pSjNwQmx4c0d3Vi9abEwyNk0ydWVZSEtZQ3VYaGRxRnd4VmdtNTJoM29lSk9PdC92WTRFY1FxN2VxSG02bTAzWjliN1BSellNMktHWEhEbU9Nazd2RHBlTVZsTERQU0dZejErVTNzRHhKemViU3BiYUptVDdpbXpVS2ZnZ0VZN3h4ZjRjemZIMHlqNXdOelNHVE92UT09IjsKCSJwdXJjaGFzZS1pbmZvIiA9ICJld29KSW05eWFXZHBibUZzTFhCMWNtTm9ZWE5sTFdSaGRHVXRjSE4wSWlBOUlDSXlNREUxTFRFeExURXlJREl5T2pRNE9qQTNJRUZ0WlhKcFkyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkluVnVhWEYxWlMxcFpHVnVkR2xtYVdWeUlpQTlJQ0k1WlRjek16QTFPVFkwWXpJeE9UVTNaRFF6WW1ZM01qaGhPRFF3WVRjME1tVTFPREV3TURBMklqc0tDU0p2Y21sbmFXNWhiQzEwY21GdWMyRmpkR2x2YmkxcFpDSWdQU0FpTVRBd01EQXdNREU0TURBMk56WXpPU0k3Q2draVluWnljeUlnUFNBaU1TNHdJanNLQ1NKMGNtRnVjMkZqZEdsdmJpMXBaQ0lnUFNBaU1UQXdNREF3TURFNE1EQTJOell6T1NJN0Nna2ljWFZoYm5ScGRIa2lJRDBnSWpFaU93b0pJbTl5YVdkcGJtRnNMWEIxY21Ob1lYTmxMV1JoZEdVdGJYTWlJRDBnSWpFME5EY3pPVGN5T0Rjd05qZ2lPd29KSW5WdWFYRjFaUzEyWlc1a2IzSXRhV1JsYm5ScFptbGxjaUlnUFNBaVJFVkROVUpETmtVdE1qQXlSaTAwTWpVeUxUazROa1l0T1RNeU1qZ3pPREJDUWpJeUlqc0tDU0p3Y205a2RXTjBMV2xrSWlBOUlDSmpiMjB1ZUhSdmJtVm5ZVzFsTG5CMWVucHNaV0pzWVhOMExqRXlPREJuYjJ4a1kyOXBibk1pT3dvSkltbDBaVzB0YVdRaUlEMGdJakV3TlRneE9UWXhNelVpT3dvSkltSnBaQ0lnUFNBaVkyOXRMbmgwYjI1bFoyRnRaUzV3ZFhwNmJHVmliR0Z6ZENJN0Nna2ljSFZ5WTJoaGMyVXRaR0YwWlMxdGN5SWdQU0FpTVRRME56TTVOekk0TnpBMk9DSTdDZ2tpY0hWeVkyaGhjMlV0WkdGMFpTSWdQU0FpTWpBeE5TMHhNUzB4TXlBd05qbzBPRG93TnlCRmRHTXZSMDFVSWpzS0NTSndkWEpqYUdGelpTMWtZWFJsTFhCemRDSWdQU0FpTWpBeE5TMHhNUzB4TWlBeU1qbzBPRG93TnlCQmJXVnlhV05oTDB4dmMxOUJibWRsYkdWeklqc0tDU0p2Y21sbmFXNWhiQzF3ZFhKamFHRnpaUzFrWVhSbElpQTlJQ0l5TURFMUxURXhMVEV6SURBMk9qUTRPakEzSUVWMFl5OUhUVlFpT3dwOSI7CgkiZW52aXJvbm1lbnQiID0gIlNhbmRib3giOwoJInBvZCIgPSAiMTAwIjsKCSJzaWduaW5nLXN0YXR1cyIgPSAiMCI7Cn0=\";Uin:1032;WorldID:11}");
      redirect.setPostUrl("http://iap.n8wan.com:29141/leo/leo.jsp");

      // redirect.setPostUrl("http://localhost:8080/demo-interface/leo/leo.jsp");
      // redirect.setPostUrl("http://localhost:8080/demo-interface/leo/leo.jsp");
      String msg = redirect.post();
      // System.out.println("msg2: "+msg);
      // redirect.dowloadMsg(msg);
      // redirect.setHttpGetSendUrl("http://121.40.243.171/charge/iphoneCharge.php");
      // redirect.sendHttpGetToLeo();

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
