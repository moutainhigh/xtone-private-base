package com.system.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


import com.google.gson.Gson;
import com.oracle.jrockit.jfr.Producer;
import com.sun.istack.internal.logging.Logger;
import com.system.util.model.Partner;
import com.system.util.model.Player;
import com.system.util.model.Product;
import com.system.util.model.Retention;


/**
 * 该类为工具类，用于给leo.jsp提供相关方法
 * 
 * @author        hongjiabin
 * @ClassName:    Redirect.java
 * @data          2015-11-24
 * @version       1.0  2015-11-24
 * @company:      xtone           
 */
public class Redirect {
  
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
    /**
     * 将访问产品API接口返回的[{...},{...}]字符串转换为list<JavaBean>的工具方法
     * @param string 
     * @return List<Product>
     */
    public static List<Product> gsonToProduct(String string){
    	//去除外围的[]
    	string = string.substring(1, string.lastIndexOf("]"));
    	System.out.println(string);
    	//将字符串按照{}间“,”的形式拆开并存入一个字符串数组中
        String[]arr = StringUtil.stringToStringArray(string);
        //创建Gson
        Gson gson = new Gson();
        //创建List<Product>
        List<Product> list = new ArrayList<Product>();
        for(int i=0;i<arr.length;i++){
        	//利用GSON将json字符串转换成Product对象
        	System.out.println(i+" : "+arr[i]);
        	Product model = gson.fromJson(arr[i], Product.class);
        	list.add(model);
        }
        return list;
    }
    
    /**
     * 将访问渠道API接口返回的[{...},{...}]字符串转换为list<JavaBean>的工具方法
     * @param string 
     * @return List<Partner>
     */
    public static List<Partner> gsonToPartner(String string){
    	//去除外围的[]
    	string = string.substring(1, string.lastIndexOf("]"));
    	System.out.println(string);
    	//将字符串按照{}间“,”的形式拆开并存入一个字符串数组中
        String[]arr = StringUtil.stringToStringArray(string);
        //创建Gson
        Gson gson = new Gson();
        //创建List<Partner>
        List<Partner> list = new ArrayList<Partner>();
        for(int i=0;i<arr.length;i++){
        	//利用GSON将json字符串转换成Partner对象
        	System.out.println(i+" : "+arr[i]);
        	Partner model = gson.fromJson(arr[i], Partner.class);
        	list.add(model);
        }
        return list;
    }
    
    /**
     * 将访问玩家API接口返回的[{...},{...}]字符串转换为list<JavaBean>的工具方法
     * @param string 
     * @return List<Player>
     */
    public static List<Player> gsonToPlayer(String string){
    	//去除外围的[]
    	string = string.substring(1, string.lastIndexOf("]"));
    	System.out.println(string);
    	//将字符串按照{}间“,”的形式拆开并存入一个字符串数组中
        String[]arr = StringUtil.stringToStringArray(string);
        //创建Gson
        Gson gson = new Gson();
        //创建List<Player>
        List<Player> list = new ArrayList<Player>();
        for(int i=0;i<arr.length;i++){
        	//利用GSON将json字符串转换成Player对象
        	System.out.println(i+" : "+arr[i]);
        	Player model = gson.fromJson(arr[i], Player.class);
        	list.add(model);
        }
        return list;
    }
    
    
    /**
     * 将访问留存率API接口返回的[{...},{...}]字符串转换为list<JavaBean>的工具方法
     * @param string  
     * @return List<Retention>
     */
    public static List<Retention> gsonToRetention(String string){
    	//去除外围的[]
    	string = string.substring(1, string.lastIndexOf("]"));
    	//将字符串按照{}间“,”的形式拆开并存入一个字符串数组中
        String[]arr = StringUtil.stringToStringArray(string);
        //创建Gson
        Gson gson = new Gson();
        //创建List<Retention>
        List<Retention> list = new ArrayList<Retention>();
        for(int i=0;i<arr.length;i++){
        	//利用GSON将json字符串转换成Retention对象
        	System.out.println(i+" : "+arr[i]);
        	Retention model = gson.fromJson(arr[i], Retention.class);
        	list.add(model);
        }
        return list;
    }
    
    
    public static void main(String[] args) {
    	Redirect http = new Redirect();
    	//发送 GET 请求
        String s=http.sendGet("http://gaopen.talkingdata.net/game/product/list", "token=3d5663eacbf64808ae89cd7a5077293f");
        //System.out.println(s);
        s = s.substring(1, s.lastIndexOf("]"));
        //System.out.println(s);
        String[]arr = StringUtil.stringToStringArray(s);
        Gson gson = new Gson();
        List<Product> list = new ArrayList<Product>();
        for(int i=0;i<arr.length;i++){
        	//arr[i]+="}";
        	//System.out.println(arr[i]);
        	Product model = gson.fromJson(arr[i], Product.class);
        	list.add(model);
        }
        for (Product product : list) {
			System.out.println(product.getProductName());
		}
        
        
		
//		String sr=http.sendPost("http://gaopen.talkingdata.net/game/product/list?token=3d5663eacbf64808ae89cd7a5077293f", "");
//        System.out.println(sr);
	}

}
