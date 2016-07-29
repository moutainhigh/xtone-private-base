package com.thirdpay.test;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thirdpay.utils.CheckPayInfo;

public class mainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		JSONObject json = JSON.; // 解析自定义参数
//		String url = "http://thirdpay-cs.n8wan.com:29141/CpInfoServlet?Appkey=zgt";
//		String str = getJsonContent(url, "utf-8");
//		JSONObject json = JSON.parseObject(str); // 解析自定义参数
//		System.out.println(json.getString("baidupay"));
		
		
//		String test = null;
		
//		if(test != null && !test.equals("")){
//			System.out.println("is null");
//		}
		
//		if("".equals(test)){
			
//			if(test != null){
//				
//			}
//		}
		
		String forwardString = CheckPayInfo.CheckInfo("87web1469686110398");
		
		
		System.out.println(forwardString);
		
	}
	
	 public static String getJsonContent(String url_path ,String encode){  
         
	        String jsonString = "";  
	        try {  
	            URL url = new URL(url_path);              
	            HttpURLConnection connection = (HttpURLConnection)url.openConnection();  
	              
	            connection.setConnectTimeout(3000);  
	            connection.setRequestMethod("GET");  
	            connection.setDoInput(true);  //从服务器获得数据  
	            
	            int responseCode = connection.getResponseCode();              
	            
	            if (200 == responseCode) {  
	                jsonString = changeInputStream(connection.getInputStream(),encode);  
	                  
	            }  
	              
	          
	              
	        } catch (Exception e) {  
	            // TODO: handle exception  
	        }  
	          
	    //  
	        return jsonString;  
	    }  
	 private static String changeInputStream(InputStream inputStream , String encode) throws IOException {  
	        // TODO Auto-generated method stub  
	        String  jsonString = null;  
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();  
	        byte[] data = new byte[1024];  
	        int len = 0;  
	        while((len=inputStream.read(data))!=-1){  
	            outputStream.write(data, 0, len);  
	        }  
	          
	        jsonString = new String(outputStream.toByteArray(), encode);  
	  
	        inputStream.close();  
	          
	          
	        return jsonString;  
	    }  
}
