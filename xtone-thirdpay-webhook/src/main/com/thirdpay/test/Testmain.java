package com.thirdpay.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.common.util.ConfigManager;
import org.common.util.ConnectionService;
import org.common.util.ThreadPool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thirdpay.domain.LogInsert;
import com.thirdpay.domain.PayInfoBean;
import com.thirdpay.domain.PayOperateBean;
import com.thirdpay.servlet.AlipayCountServlet;
import com.thirdpay.utils.ConnectionServiceCPInfo;
import com.thirdpay.utils.ConnectionServicethirdpayCount;

public class Testmain {

	public static void main(String[] args) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", "admin");
		params.put("password", "123");
		System.out.println(params.entrySet());

//		 作为StringBuffer初始化的字符串
		 StringBuffer buffer = new StringBuffer();
		 if (params != null && !params.isEmpty()) {
		 for (Map.Entry<String, String> entry : params.entrySet()) {
		 // 完成转码操作
		 buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(),
		 "utf-8"))
		 .append("&");
		 }
		 buffer.deleteCharAt(buffer.length() - 1);
		 }
		// System.out.println(buffer.toString());
		// 删除掉最有一个&

		// httpUrlConnection(params, "utf-8");

	}

	private static void httpUrlConnection(Map<String, String> params, String encode) {

		try {
			String pathUrl = "http://192.168.0.101:8080/thirdpay-webhook/TestServlet";
			// 建立连接
			URL url = new URL(pathUrl);

			// 作为StringBuffer初始化的字符串
			StringBuffer buffer = new StringBuffer();
			if (params != null && !params.isEmpty()) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					// 完成转码操作
					buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), encode))
							.append("&");
				}
				buffer.deleteCharAt(buffer.length() - 1);
			}
			// System.out.println(buffer.toString());
			// 删除掉最有一个&

			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

			// //设置连接属性
			httpConn.setConnectTimeout(3000);
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出
			httpConn.setDoInput(true);// 使用 URL 连接进行输入
			httpConn.setUseCaches(false);// 忽略缓存
			httpConn.setRequestMethod("POST");// 设置URL请求方法

			String requestString = "客服端要以以流方式发送到服务端的数据...";

			// 设置请求属性
			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
			byte[] requestStringBytes = requestString.getBytes();
			httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);
			httpConn.setRequestProperty("Content-Type", "application/octet-stream");
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpConn.setRequestProperty("Charset", "UTF-8");
			//
			String name = URLEncoder.encode("黄武艺", "utf-8");
			httpConn.setRequestProperty("name", name);

			// 建立输出流，并写入数据
			OutputStream outputStream = httpConn.getOutputStream();
			outputStream.write(requestStringBytes);
			outputStream.close();
			// 获得响应状态
			int responseCode = httpConn.getResponseCode();

			if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
				// 当正确响应时处理数据
				StringBuffer sb = new StringBuffer();
				String readLine;
				BufferedReader responseReader;
				// 处理响应流，必须与服务器响应流输出的编码一致
				responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
				responseReader.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}