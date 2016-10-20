package com.thirdpay.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class guavaTest {

	public static void main(String[] args) throws Exception {
		  
	       LoadingCache<String,String> cahceBuilder=CacheBuilder  
	       .newBuilder()
	       .maximumSize(5000)//最大缓存5000个对象
	       .expireAfterAccess(10, TimeUnit.MINUTES)  //5分钟后缓存失效
	       .build(new CacheLoader<String, String>(){  
	           @Override  
	           public String load(String key) throws Exception {   
	        	// load a new TradeAccount not exists in cache
	        	   //创造一个新的对象 如果String对象不存在
	               return createExpensiveGraph(key);  
	           }  
	  
	   private String createExpensiveGraph(String key) {  
	    System.out.println("load into cache!");  
	    System.out.println("key = "+key);  
	    return "hello "+key+"!";  
	    }  
	             
	       });   
	       
	       cahceBuilder.put("hh", "123");
	       cahceBuilder.put("aa", "321");
	       cahceBuilder.put("bb", "123456");
	       
	        System.out.println( "cahceBuilder = " + cahceBuilder.get("hh") );
	        System.out.println( "cahceBuilder = " + cahceBuilder.get("aa") );
	        System.out.println( "cahceBuilder = " + cahceBuilder.get("bb") );
//	       cahceBuilder.get("2");  
//	       cahceBuilder.get("2");  
//	       cahceBuilder.get("3");  
	       //第二次就直接从缓存中取出  
//	       cahceBuilder.get("2");  
		
	}
	
	   public void LoadingCache() throws Exception{  
	       LoadingCache<String,String> cahceBuilder=CacheBuilder  
	       .newBuilder().maximumSize(10000).expireAfterAccess(10, TimeUnit.MINUTES)  
	       .build(new CacheLoader<String, String>(){  
	           @Override  
	           public String load(String key) throws Exception {          
	               return createExpensiveGraph(key);  
	           }  
	  
	   private String createExpensiveGraph(String key) {  
	    System.out.println("load into cache!");  
	    return "hello "+key+"!";  
	    }  
	             
	       });          
	         
	       cahceBuilder.get("2");  
	       cahceBuilder.get("3");  
	       //第二次就直接从缓存中取出  
	       cahceBuilder.get("2");  
	   } 
	   
	   public void testcallableCache()throws Exception{
	        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000).build();  
	        String resultVal = cache.get("jerry", new Callable<String>() {  
	            public String call() {  
	                String strProValue="hello "+"jerry"+"!";                
	                return strProValue;
	            }  
	        });  
	        System.out.println("jerry value : " + resultVal);
	        
	        resultVal = cache.get("peida", new Callable<String>() {  
	            public String call() {  
	                String strProValue="hello "+"peida"+"!";                
	                return strProValue;
	            }  
	        });  
	        System.out.println("peida value : " + resultVal);  
	    }
	   
	   
//public void ifind(){
//	LoadingCache<String, TradeAccount> traLoadingCache =
//			CacheBuilder.newBuilder()
//				.expireAfterAccess(5L, TimeUnit.MINUTES) //5分钟后缓存失效
//				.maximumSize(5000L) //最大缓存5000个对象
//				.removalListener(new TradeAccountRemovalListener()) //注册缓存对象移除监听器
//				.ticker(Ticker.systemTicker()) //定义缓存对象失效的时间精度为纳秒级
//				.build(new CacheLoader<String, TradeAccount>(){ 
//					@Override
//					public TradeAccount load(String key) throws Exception {
//						// load a new TradeAccount not exists in cache
//						return null;
//					}
//				});
//}
	   
}
