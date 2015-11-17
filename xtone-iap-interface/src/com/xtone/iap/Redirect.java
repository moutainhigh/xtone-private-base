package com.xtone.iap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

public class Redirect {
  
  private final static Logger LOG = Logger.getLogger("Redirect.class");

  private String postContent;
  private String postUrl;
  private String resultFromPost;
  private String httpGetSendUrl;
  private RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000)
      .setSocketTimeout(5000).build();
  private ContentSendToApple mContentSendToApple = new ContentSendToApple();
  private ReceiveFromClient mReceiveFromClient;

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

  public String post() throws Exception {
    HttpClient client = new DefaultHttpClient();
    HttpPost request = new HttpPost(postUrl);
    
    request.setConfig(requestConfig);
    
    Gson gson = new Gson();
    mReceiveFromClient = gson.fromJson(postContent, ReceiveFromClient.class);
    mContentSendToApple.setContent(mReceiveFromClient.getContent());
    HttpEntity entity = new ByteArrayEntity(gson.toJson(mContentSendToApple).getBytes("UTF-8"));
    
    request.setEntity(entity);
    HttpResponse response = client.execute(request);
    String result = EntityUtils.toString(response.getEntity());
    resultFromPost = result;
    
    byte[] decodedBytes = Base64.decodeBase64(mReceiveFromClient.getContent());
    //todo split sandbox 
    String decodedString = new String(decodedBytes);
    LOG.debug(decodedString);
    return result;
  }

  public void sendHttpGetToLeo() throws Exception {
    CloseableHttpClient client = HttpClientBuilder.create().build();

    byte[] encodedBytes = Base64.encodeBase64(resultFromPost.getBytes());
    String encodedString = new String(encodedBytes);
    String getUrl = httpGetSendUrl + "?VerifyStr=" + encodedString+"&WorldID="+mReceiveFromClient.getWorldID()+"&Uin="+mReceiveFromClient.getuIn();
    HttpGet request = new HttpGet(getUrl);
    LOG.debug("get begin:" + getUrl);
    request.setConfig(requestConfig);
    CloseableHttpResponse response = client.execute(request);
    HttpEntity entity = response.getEntity();
    if (entity != null) {
      InputStream instream = entity.getContent();
      String str = convertStreamToString(instream);
      LOG.debug(str);
      // Do not need the rest
      request.abort();
    }
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

  public static void main(String[] args) {
    try {
      Redirect redirect = new Redirect();
      //貌似需要转个码才好测试...
      redirect
          .setPostContent("{\"receipt-data\":\"ewoJInNpZ25hdHVyZSIgPSAiQWsza3BHTFNYUHhXczcyanFKZlRSTnc0Mk54UnNyeXU0Mk50NWlRTVNXdFZydkdNRTh1V0F4YlhaRWozd1ZBQ3hISG44NXNQWEdPSDY4YlQ1ck5lczRTQmlUY2lsdzl1dGhheHZXWkd1c29vZXhrVnFuK2VlME1xOVhjeXE4TmZDdm95ZUFRcUUzSlVmSkRxRDkwVDJ4ZjlNeWpzMEJqNllMSlZFWml2VXZyNEFBQURWekNDQTFNd2dnSTdvQU1DQVFJQ0NCdXA0K1BBaG0vTE1BMEdDU3FHU0liM0RRRUJCUVVBTUg4eEN6QUpCZ05WQkFZVEFsVlRNUk13RVFZRFZRUUtEQXBCY0hCc1pTQkpibU11TVNZd0pBWURWUVFMREIxQmNIQnNaU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVURXpNREVHQTFVRUF3d3FRWEJ3YkdVZ2FWUjFibVZ6SUZOMGIzSmxJRU5sY25ScFptbGpZWFJwYjI0Z1FYVjBhRzl5YVhSNU1CNFhEVEUwTURZd056QXdNREl5TVZvWERURTJNRFV4T0RFNE16RXpNRm93WkRFak1DRUdBMVVFQXd3YVVIVnlZMmhoYzJWU1pXTmxhWEIwUTJWeWRHbG1hV05oZEdVeEd6QVpCZ05WQkFzTUVrRndjR3hsSUdsVWRXNWxjeUJUZEc5eVpURVRNQkVHQTFVRUNnd0tRWEJ3YkdVZ1NXNWpMakVMTUFrR0ExVUVCaE1DVlZNd2daOHdEUVlKS29aSWh2Y05BUUVCQlFBRGdZMEFNSUdKQW9HQkFNbVRFdUxnamltTHdSSnh5MW9FZjBlc1VORFZFSWU2d0Rzbm5hbDE0aE5CdDF2MTk1WDZuOTNZTzdnaTNvclBTdXg5RDU1NFNrTXArU2F5Zzg0bFRjMzYyVXRtWUxwV25iMzRucXlHeDlLQlZUeTVPR1Y0bGpFMU93QytvVG5STStRTFJDbWVOeE1iUFpoUzQ3VCtlWnRERWhWQjl1c2szK0pNMkNvZ2Z3bzdBZ01CQUFHamNqQndNQjBHQTFVZERnUVdCQlNKYUVlTnVxOURmNlpmTjY4RmUrSTJ1MjJzc0RBTUJnTlZIUk1CQWY4RUFqQUFNQjhHQTFVZEl3UVlNQmFBRkRZZDZPS2RndElCR0xVeWF3N1hRd3VSV0VNNk1BNEdBMVVkRHdFQi93UUVBd0lIZ0RBUUJnb3Foa2lHOTJOa0JnVUJCQUlGQURBTkJna3Foa2lHOXcwQkFRVUZBQU9DQVFFQWVhSlYyVTUxcnhmY3FBQWU1QzIvZkVXOEtVbDRpTzRsTXV0YTdONlh6UDFwWkl6MU5ra0N0SUl3ZXlOajVVUllISytIalJLU1U5UkxndU5sMG5rZnhxT2JpTWNrd1J1ZEtTcTY5Tkluclp5Q0Q2NlI0Szc3bmI5bE1UQUJTU1lsc0t0OG9OdGxoZ1IvMWtqU1NSUWNIa3RzRGNTaVFHS01ka1NscDRBeVhmN3ZuSFBCZTR5Q3dZVjJQcFNOMDRrYm9pSjNwQmx4c0d3Vi9abEwyNk0ydWVZSEtZQ3VYaGRxRnd4VmdtNTJoM29lSk9PdC92WTRFY1FxN2VxSG02bTAzWjliN1BSellNMktHWEhEbU9Nazd2RHBlTVZsTERQU0dZejErVTNzRHhKemViU3BiYUptVDdpbXpVS2ZnZ0VZN3h4ZjRjemZIMHlqNXdOelNHVE92UT09IjsKCSJwdXJjaGFzZS1pbmZvIiA9ICJld29KSW05eWFXZHBibUZzTFhCMWNtTm9ZWE5sTFdSaGRHVXRjSE4wSWlBOUlDSXlNREUxTFRFeExURXlJREl5T2pRNE9qQTNJRUZ0WlhKcFkyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkluVnVhWEYxWlMxcFpHVnVkR2xtYVdWeUlpQTlJQ0k1WlRjek16QTFPVFkwWXpJeE9UVTNaRFF6WW1ZM01qaGhPRFF3WVRjME1tVTFPREV3TURBMklqc0tDU0p2Y21sbmFXNWhiQzEwY21GdWMyRmpkR2x2YmkxcFpDSWdQU0FpTVRBd01EQXdNREU0TURBMk56WXpPU0k3Q2draVluWnljeUlnUFNBaU1TNHdJanNLQ1NKMGNtRnVjMkZqZEdsdmJpMXBaQ0lnUFNBaU1UQXdNREF3TURFNE1EQTJOell6T1NJN0Nna2ljWFZoYm5ScGRIa2lJRDBnSWpFaU93b0pJbTl5YVdkcGJtRnNMWEIxY21Ob1lYTmxMV1JoZEdVdGJYTWlJRDBnSWpFME5EY3pPVGN5T0Rjd05qZ2lPd29KSW5WdWFYRjFaUzEyWlc1a2IzSXRhV1JsYm5ScFptbGxjaUlnUFNBaVJFVkROVUpETmtVdE1qQXlSaTAwTWpVeUxUazROa1l0T1RNeU1qZ3pPREJDUWpJeUlqc0tDU0p3Y205a2RXTjBMV2xrSWlBOUlDSmpiMjB1ZUhSdmJtVm5ZVzFsTG5CMWVucHNaV0pzWVhOMExqRXlPREJuYjJ4a1kyOXBibk1pT3dvSkltbDBaVzB0YVdRaUlEMGdJakV3TlRneE9UWXhNelVpT3dvSkltSnBaQ0lnUFNBaVkyOXRMbmgwYjI1bFoyRnRaUzV3ZFhwNmJHVmliR0Z6ZENJN0Nna2ljSFZ5WTJoaGMyVXRaR0YwWlMxdGN5SWdQU0FpTVRRME56TTVOekk0TnpBMk9DSTdDZ2tpY0hWeVkyaGhjMlV0WkdGMFpTSWdQU0FpTWpBeE5TMHhNUzB4TXlBd05qbzBPRG93TnlCRmRHTXZSMDFVSWpzS0NTSndkWEpqYUdGelpTMWtZWFJsTFhCemRDSWdQU0FpTWpBeE5TMHhNUzB4TWlBeU1qbzBPRG93TnlCQmJXVnlhV05oTDB4dmMxOUJibWRsYkdWeklqc0tDU0p2Y21sbmFXNWhiQzF3ZFhKamFHRnpaUzFrWVhSbElpQTlJQ0l5TURFMUxURXhMVEV6SURBMk9qUTRPakEzSUVWMFl5OUhUVlFpT3dwOSI7CgkiZW52aXJvbm1lbnQiID0gIlNhbmRib3giOwoJInBvZCIgPSAiMTAwIjsKCSJzaWduaW5nLXN0YXR1cyIgPSAiMCI7Cn0=\";Uin:1032;WorldID:11}");
      redirect.setPostUrl("http://iap.n8wan.com:29141/leo/leo.jsp");
      redirect.setPostUrl("http://localhost:8080/demo-interface/leo/leo.jsp");
      System.out.println(redirect.post());
      redirect.setHttpGetSendUrl("http://121.40.243.171/charge/iphoneCharge.php");
      redirect.sendHttpGetToLeo();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
