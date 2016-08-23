package com.thirdpay.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeixinHttpsUtils {
	
	public static String getWeixin(String html){
		String regex = "weixin://[^[A-Za-z0-9\\._\\?%&+\\-=/#]]*"; 
        Pattern pattern = Pattern.compile(regex); 
        Matcher matcher = pattern.matcher(html); 
        while (matcher.find()) { 
            String urlStr=matcher.group();
            if(urlStr.contains("weixin://wap/pay")){
            	return urlStr;
            }
        } 
        return null;
	}

	
	public static String getWeixinHttps(String html){
		String regex = "(http:|https:)//[^[A-Za-z0-9\\._\\?%&+\\-=/#]]*"; 
        Pattern pattern = Pattern.compile(regex); 
        Matcher matcher = pattern.matcher(html); 
        while (matcher.find()) { 
            String urlStr=matcher.group();
            if(urlStr.contains("unifiedCheck?uuid")){
            	return urlStr;
            }
        } 
        return null;
	}
	
	public static String getCheckmWebHttps(String html){
		String regex = "(http:|https:)//[^[A-Za-z0-9\\._\\?%&+\\-=/#]]*"; 
		Pattern pattern = Pattern.compile(regex); 
		Matcher matcher = pattern.matcher(html); 
		while (matcher.find()) { 
			String urlStr=matcher.group();
			if(urlStr.contains("checkmweb?")){
				return urlStr;
			}
		} 
		return null;
	}
}
