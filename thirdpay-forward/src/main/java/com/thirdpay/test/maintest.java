package com.thirdpay.test;

import java.util.Scanner;

import org.apache.log4j.net.SyslogAppender;
import org.common.util.ThreadPool;

import com.thirdpay.bean.ForwardLogBean;
import com.thirdpay.utils.CheckPayInfo;

public class maintest {
	
	public static void main(String[] args) {
		// String str =
		// "{\"buyNum\":0,\"coinNum\":0,\"price\":1,\"productDesc\":\"商品名称是苹果商品id为123456\",\"productId\":\"12345\",\"productName\":\"苹果\",\"ratio\":0,\"roleLevel\":0,\"uid\":\"7d07ccb3-8d83-4ebc-b2e9-2f37b120fa0d\",\"webOrderid\":\"1462867117426032111\"}";
		// JSONObject payParamsjson = JSON.parseObject(str);
		//
//		String str = CheckPayInfo.CheckInfo("1462873186869035811");
//		System.out.println(str);
		
		
//		ThreadPool.mThreadPool.execute(new ForwardLogBean(1002, "bl", "bl", "success", "result_status",
//				"send_time", "rsp_time", "send_url", "send_header", "body", "rsp_status","rsp_header", "rsp_body", "task_id",
//				"key_type", "id_type"));
//		
		Scanner sc = new Scanner(System.in);
		
		String control = sc.next();
		System.out.println(control);
		
		
		while(true){
			
			System.out.println("kkkkkkkkkk");
			
			if(control == "stop"){
				break;
			}
		}
	}

}
