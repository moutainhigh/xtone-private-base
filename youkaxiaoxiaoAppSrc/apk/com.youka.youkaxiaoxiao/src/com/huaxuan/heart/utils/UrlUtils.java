package com.huaxuan.heart.utils;

import java.util.HashMap;
import java.util.Map;

public class UrlUtils {
	
//	public static final String BASEURL = "http://192.168.0.111:8080/PopoBird/";
//	
//    public static final String LotterService = BASEURL+"/LotteryService2";
//    
//    public static Map<String, String> getUrlParm(String method,String uid){
//    	Map<String, String> map = new HashMap<String, String>();
//    	map.put("method", method);
//    	map.put("uid", uid);
//    	return map;
//    }
    
    //陈柳生 BASEURL
  //  public static final String BASEURL2 = "http://192.168.1.99:8080/lottery_act/";
    public static final String BASEURL2 = "http://lottery-game-demo.n8wan.com:29141/";
    
    /**
     * 活动
     */
    public static final String activity = BASEURL2+"activity.jsp";
    /**
     * 注册
     */
    public static final String regist = BASEURL2+"regist.jsp";
    /**
     * 登陆
     */
    public static final String login = BASEURL2+"login.jsp";

    /**
     * 我的彩票信息
     */
    public static final String account = BASEURL2+"account.jsp";
    /**
     * 彩票赠送
     */
    public static final String gift = BASEURL2+"gift.jsp";
    
    
    
    
    
	

}
