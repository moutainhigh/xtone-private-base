package com.thirdpay.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.common.util.ConfigManager;
import org.common.util.ThreadPool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thirdpay.domain.LogInsert;
import com.thirdpay.domain.PayInfoBean;



public class Testmain {
	private static Logger logger = Logger.getLogger(Testmain.class);

	/** 
     * @param args 
     */  
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
    	int n = (int) ((Float.parseFloat("3.52"))*100);
    	
    	
    	System.out.println(n);
    }    
}
