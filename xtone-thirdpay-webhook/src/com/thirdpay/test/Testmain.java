package com.thirdpay.test;

import java.net.HttpURLConnection;
import java.net.URL;
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



public class Testmain {
//	  private String url = "https://192.168.1.101/";  
//	    private String charset = "utf-8";  
	
    public static void main(String[] args) {
    	
    	
    	
        // System.out.println("This is println message.");  
//
//        // 记录debug级别的信息  
//        logger.debug("This is debug message.");  
//        // 记录info级别的信息  
//        logger.info("This is info message.");  
//        // 记录error级别的信息  
//        logger.error("This is error message.");  
//    	String obj = "{"+
//    				"\""+"channel"+"\""+":"+"\""+"10010"+"\""+","+
//    				"\""+"appkey"+"\""+":"+
//    				"\""+"f17d2fb4eff547c8bebc1e7cc4dcd43c"+"\""+","+
//    				"\""+"platform"+"\""+":"+
//    				"\""+"plugin"+"\""+
//    			"}";
//    	
//         JSONObject json = JSON.parseObject(obj);
//         
//        System.out.println(json.getString("channel"));
    	
    	//System.out.println(ConfigManager.getConfigData("server.id"));
//    	  ThreadPool.mThreadPool.execute(new LogInsert("a","b","c","d","e","f","g","h"));
    	
    //	ThreadPool.mThreadPool.execute(new UnionInfoBean(1,"","","","","","","","", null, 0));
//    	float i = (Float.parseFloat("0.01")*100) ;
    	//int i = Integer.parseInt((Float.parseFloat("0.01"))*100+"");
    	
//    	 List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
//    	 
//		 formparams.add(new BasicNameValuePair("name","XXXXXXXXXXXXXXXXXXX"));
//    	
//    	AlipayCountServlet.post("http://localhost:8080/adTest/AdServlet", formparams);
    	
//    	try {
//    		URL pageUrl = new URL("https://www.baidu.com/");
//
//    		HttpURLConnection httpUrlConn = (HttpURLConnection)pageUrl.openConnection();
//    		int statusCode = httpUrlConn.getResponseCode();
//    		System.out.println(statusCode);
//    		} catch (Exception e) {
//    		e.printStackTrace();
//    		}
    	
    	
//    	ThreadPool.mThreadPool.execute(new PayOperateBean(0,"ShowPayGui"));

//    	test.getinstance().play("321");
    }    
    
}
	class test {
		
		
		static test t = new test();
		
		public test(){
			
		}
		public static test getinstance(){
			System.out.println("test");
			return t ; 
		}
		
		public static String key ="";
		public String tt = tt("+321");
		
		
		public static void play(String kk){
			System.out.println("play");
			key = kk;
		}
		
		public String tt (String key){
			System.out.println("++321");
			return key ;
		}
	}

