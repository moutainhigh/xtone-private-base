package com.bird.utils;

import java.io.IOException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpGetUtil {
	public String executeGet(String url) throws Exception { 
		HttpClient client = new HttpClient();
        // 设置代理服务器地址和端口
        // client.getHostConfiguration().setProxy( "172.26.184.189", 80 );
        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
        HttpMethod method = new GetMethod( "http://www.jsjtt.com/e/action/ShowInfo.php?classid=22&id=14" );
 
        // 这里设置字符编码，避免乱码
        method.setRequestHeader( "Content-Type", "text/html;charset=utf-8" );
 
        client.executeMethod( method );
        // 打印服务器返回的状态
        System.out.println( method.getStatusLine() );
 
        // 获取返回的html页面
        byte[] body = method.getResponseBody();
        // 打印返回的信息
        System.out.println( new String( body, "utf-8" ) );
        // 释放连接
        method.releaseConnection();
		return new String( body, "utf-8" );
	}
}
