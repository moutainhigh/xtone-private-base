package com.bird.utils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class HttpsRequest {
	
	private HttpClient httpClient;
	
	// private static Log log = new Log(LoggerFactory.getLogger(HttpsRequest.class));
	
	public HttpsRequest() {
		init();
	}

	@SuppressWarnings("deprecation")
	private void init() {
		//SSLClient client = new SSLClient();
		this.httpClient = SSLClient.wrapClient(new DefaultHttpClient());
	}

	
	
	/**
     * 通过Https往API post xml数据
     *
     * @param url    API地址
     * @param xmlObj 要提交的XML数据对象
     * @return API回包的实际数据
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */

    public String sendPost(String url, Object xmlObj) throws IOException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException {

        
        String result = null;

        HttpPost httpPost = new HttpPost(url);

        //解决XStream对出现双下划线的bug
       // XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));

        //将要提交给API的数据对象转换成XML格式数据Post给API
        //String postDataXML = xStreamForRequestPostData.toXML(xmlObj);
        String postDataXML = (String) xmlObj;

       // Util.log("API，POST过去的数据是：");
       // Util.log(postDataXML);

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        postEntity.setContentEncoding("utf-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);
        
        
//        StringEntity postEntity = new StringEntity(postDataXML,"iso8859-1");
//        postEntity.setContentEncoding("utf-8");
//        postEntity.setContentType("text/xml");
//        httpPost.setEntity(postEntity);

        //设置请求器的配置
       // httpPost.setConfig(requestConfig);
        

       // Util.log("executing request" + httpPost.getRequestLine());

        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");

        } catch (ConnectionPoolTimeoutException e) {
            //log.e("http get throw ConnectionPoolTimeoutException(wait time out)");

        } catch (ConnectTimeoutException e) {
          //  log.e("http get throw ConnectTimeoutException");

        } catch (SocketTimeoutException e) {
          //  log.e("http get throw SocketTimeoutException");

        } catch (Exception e) {
           // log.e("http get throw Exception");

        } finally {
            httpPost.abort();
        }

        return result;
    }
	
    public String sendGet(String url)throws IOException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException {
    	String result = null;
    	HttpGet get = new HttpGet(url);
        System.out.println("执行get请求:...."+get.getURI());
        try {
        	HttpResponse response = httpClient.execute(get);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");
        } catch (ConnectionPoolTimeoutException e) {
            //log.e("http get throw ConnectionPoolTimeoutException(wait time out)");

        } catch (ConnectTimeoutException e) {
          //  log.e("http get throw ConnectTimeoutException");

        } catch (SocketTimeoutException e) {
          //  log.e("http get throw SocketTimeoutException");

        } catch (Exception e) {
           // log.e("http get throw Exception");

        } finally {
            get.abort();
        }

        return result;
    }
    
	
}
