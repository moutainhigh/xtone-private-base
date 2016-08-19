package com.thirdpay.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 */
	public static String post(String url, List<BasicNameValuePair> formparams,String ownOrderId) {
		
		String responseContent = "" ;
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列


		try {

			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			
//			System.out.println("executing request = " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
//					System.out.println("--------------------------------------");
					responseContent = EntityUtils.toString(entity, "UTF-8");
					
//					System.out.println("Response content: " + responseContent);
//					System.out.println("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
//			e.printStackTrace();
			//LOG.info("通知地址连接异常,30秒后再次转发...");
			CheckPayInfo.Updata1001Time(ownOrderId,responseContent);
			
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	
	}

	public static String get(String url) {

		String result = "";
		// HttpGet httpRequst = new HttpGet(URI uri);
		// HttpGet httpRequst = new HttpGet(String uri);
		// 创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。
		HttpGet httpRequst = new HttpGet(url);

		// new DefaultHttpClient().execute(HttpUriRequst requst);
		try {
			// 使用DefaultHttpClient类的execute方法发送HTTP GET请求，并返回HttpResponse对象。
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);// 其中HttpGet是HttpUriRequst的子类
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);// 取出应答字符串

				System.out.println("----------------------- result = " + result);
				// 一般来说都要删除多余的字符
				result.replaceAll("\r", "");// 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
			} else
				httpRequst.abort();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage().toString();
		}
		return result;

	}
	public static String getWithHeaders(String url,String referer) {
		
		String result = "";
		// HttpGet httpRequst = new HttpGet(URI uri);
		// HttpGet httpRequst = new HttpGet(String uri);
		// 创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。
		HttpGet httpRequst = new HttpGet(url);
		
		httpRequst.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpRequst.addHeader("Accept-Encoding", "deflate, sdch, br");
		httpRequst.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpRequst.addHeader("Connection", "keep-alive");
		httpRequst.addHeader("Host", "wx.tenpay.com");
		httpRequst.addHeader("Referer", referer);
		httpRequst.addHeader("Upgrade-Insecure-Requests", "1");
		httpRequst.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
		
		// new DefaultHttpClient().execute(HttpUriRequst requst);
		try {
			// 使用DefaultHttpClient类的execute方法发送HTTP GET请求，并返回HttpResponse对象。
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);// 其中HttpGet是HttpUriRequst的子类
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity,"UTF-8");// 取出应答字符串
				
				System.out.println("----------------------- result = " + result);
				// 一般来说都要删除多余的字符
				result.replaceAll("\r", "");// 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
			} else
				httpRequst.abort();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage().toString();
		}
		return result;
		
	}
}
