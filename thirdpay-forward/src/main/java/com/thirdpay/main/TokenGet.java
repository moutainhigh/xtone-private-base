package com.thirdpay.main;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class TokenGet {

  private String postContent;
  private String url;
  private String appId;
  private String secret;
  private String httpGetSendUrl;
  private RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000)
      .setConnectTimeout(5000).setSocketTimeout(5000).build();

  public String getHttpGetSendUrl() {
    return httpGetSendUrl;
  }

  public void setHttpGetSendUrl(String httpGetSendUrl) {
    this.httpGetSendUrl = httpGetSendUrl;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String postUrl) {
    this.url = postUrl;
  }

  public String getPostContent() {
    return postContent;
  }

  public void setPostContent(String postContent) {
    this.postContent = postContent;
  }

  public String post() throws Exception {
    HttpClient client = new DefaultHttpClient();
    HttpPost request = new HttpPost(url);
    HttpEntity entity = new ByteArrayEntity(postContent.getBytes("UTF-8"));
    request.setConfig(requestConfig);
    request.setEntity(entity);
    HttpResponse response = client.execute(request);
    String result = EntityUtils.toString(response.getEntity());
    return result;
  }

  public String get() throws Exception {
    HttpClient client = new DefaultHttpClient();
    this.url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret="
        + secret;
    HttpGet request = new HttpGet(url);
    request.setConfig(requestConfig);
    HttpResponse response = client.execute(request);
    String result = EntityUtils.toString(response.getEntity());
    return result;
  }

  public static void main(String[] args) {
    try {
      TokenGet token = new TokenGet();
      token.setAppId("xxxxxxx");
      token.setSecret("yyyyyyyyy");
      System.out.println(token.get());

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
