package com.thirdpay.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Test2 {
	public static void main(String[] args) {
		
		getPayInfo();
		
	}

	
	/**
	 * 得到所有的参数与参数值
	 */
	public static void getPayInfo() {
		
	     Map<String, String[]> map = new HashMap<String, String[]>();
				String abc[] = { "11" };
				String bl[] = { "321321" };
				map.put("username", abc);
				map.put("password", bl);
				
		
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();

		Iterator<Entry<String, String[]>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String,String[]> entry = (Map.Entry<String, String[]>) iterator
					.next();

			String key = entry.getKey(); // key为参数名称
			String[] value = map.get(key); // value为参数值

			for (int i = 0; i < value.length; i++) {
				
				formparams.add(new BasicNameValuePair(key,value[i]));
			}


		}

		 String other_url = "http://192.168.0.101:8080/thirdpay-webhook/TestServlet";
		 post(other_url, formparams);

	}
	
	
	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 */
	public static void post(String url, List<BasicNameValuePair> formparams) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列

		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request" + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out.println("--------------------------------------");
					System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
					System.out.println("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
