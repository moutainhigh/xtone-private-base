package com.thirdpay.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.thirdpay.domain.PayInfoBean;

/**
 * Servlet implementation class TestReceiverServlet
 */
@WebServlet("/TestReceiverServlet")
public class TestReceiverServlet extends HttpServlet {
	
private static LoadingCache<String,String> cahceBuilder = null;
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(TestReceiverServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestReceiverServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// String payment = request.getParameter("payment");
		// if(!payment.equals("")){
		// System.out.println("recivier-payment = " + payment);
		// response.getWriter().append("200");
		// }
		
//		String payinfo = getPayInfo(request);
//		response.getWriter().append("200");
//		LOG.info("----------------------payinfo = " + payinfo);
		
		
		// String payment = request.getParameter("appkey");
		// System.out.println("appkey = " + payment);
		// response.setCharacterEncoding("gbk");
		// response.setContentType("text/html;charset=gbk");
		//
		// response.getWriter().append("小心我锋哥打你 ");

//		 LoadingCache<String,String> cahceBuilder=CacheBuilder  
//		   	       .newBuilder()
//		   	       .maximumSize(50)//最大缓存5000个对象
//		   	       .expireAfterAccess(10, TimeUnit.MINUTES)  //5分钟后缓存失效
//		   	    //设置缓存的移除通知
//	               .removalListener(new RemovalListener<Object, Object>() {
//	                   @Override
//	                   public void onRemoval(RemovalNotification<Object, Object> notification) {
//	                       System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
//	                   }
//	               })
//	               
//	               //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
//		   	       .build(new CacheLoader<String, String>(){  
//		   	           @Override  
//		   	           public String load(String key) throws Exception {   
//		   	        	// load a new TradeAccount not exists in cache
//		   	        	   //创造一个新的对象 如果String对象不存在
//		   	               return createExpensiveGraph(key);  
//		   	           }  
//		   	  
//		   	   private String createExpensiveGraph(String key) {  
//		   	    System.out.println("缓存不存在,正自动加载缓存"+" key = "+key);  
//		   	    
//		   	    return "hello "+key+"!";  
//		   	    }  
//		   	             
//		   	       }); 
	      
	   	       
//	   	       cahceBuilder.put("hh", "123");
//	   	       cahceBuilder.put("aa", "321");
//	   	       cahceBuilder.put("bb", "123456");
	   	       
//	   	        try {
//					System.out.println( "cahceBuilder = " + cahceBuilder.get("hh") );
//		   	        System.out.println( "cahceBuilder = " + cahceBuilder.get("aa") );
//		   	        System.out.println( "cahceBuilder = " + cahceBuilder.get("bb") );
//		   	        System.out.println( "cahceBuilder = " + cahceBuilder.get("bb") );
//		   	        System.out.println( "cahceBuilder = " + cahceBuilder.get("bb") );
//		   	        System.out.println( "cahceBuilder = " + cahceBuilder.get("bb") );
//		   	        System.out.println( "cahceBuilder = " + cahceBuilder.get("bb") );
//				} catch (ExecutionException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
	      
		 LoadingCache<String,String> cahceBuilder = getcahceBuilder();
		 
	   	   String bb = request.getParameter("bb");
	   	        if("clean".equals(bb)){
	   	        	cahceBuilder.cleanUp();//清空缓存
	   	        	System.out.println("清除缓存数据");
	   	        }
	   	     if("123456".equals(bb)){
	   	    	 cahceBuilder.put("bb", "123456");
	   	    	System.out.println("put了参数bb值为123456");
	   			try {
					System.out.println( "cahceBuilder = " + cahceBuilder.get("bb") );
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

	   	        }
	   	     if("321".equals(bb)){
	   	      try {
					System.out.println( "cahceBuilder = " + cahceBuilder.get("bb") );
					System.out.println( "cahceBuilder = " + cahceBuilder.get("bb") );
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   	     }
	   	  if("456".equals(bb)){
	   		  
	   	    	 cahceBuilder.put("bb", "123456");
	   	    	System.out.println("put了参数bb值为123456");

	   	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public LoadingCache<String,String> getcahceBuilder(){
		
		if(cahceBuilder == null){
			cahceBuilder=CacheBuilder  
			   	       .newBuilder()
			   	       .maximumSize(50)//最大缓存5000个对象
			   	       .expireAfterAccess(10, TimeUnit.MINUTES)  //5分钟后缓存失效
			   	    //设置缓存的移除通知
		               .removalListener(new RemovalListener<Object, Object>() {
		                   @Override
		                   public void onRemoval(RemovalNotification<Object, Object> notification) {
		                       System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
		                   }
		               })
		               
		               //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
			   	       .build(new CacheLoader<String, String>(){  
			   	           @Override  
			   	           public String load(String key) throws Exception {   
			   	        	// load a new TradeAccount not exists in cache
			   	        	   //创造一个新的对象 如果String对象不存在
			   	               return createExpensiveGraph(key);  
			   	           }  
			   	  
			   	   private String createExpensiveGraph(String key) {  
			   		   
			   	    System.out.println("缓存不存在,正自动加载缓存"+" key = "+key);  
			   	    
			   	    return "hello "+key+"!";  
			   	    
			   	    }  
			   	             
			   	       }); 
		}
		
		return cahceBuilder;
	}
	
	
	/**
	 * 得到所有的参数与参数值
	 * 
	 * @param request
	 * @return
	 */
	public static String getPayInfo(HttpServletRequest request) {
		String payInfo = "";
		// 测试用数据
		Map<String, String[]> map = request.getParameterMap();

		// List<BasicNameValuePair> formparams = new
		// ArrayList<BasicNameValuePair>();

		Iterator<Entry<String, String[]>> iterator = map.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();

			String key = entry.getKey(); // key为参数名称
			String[] value = map.get(key); // value为参数值

			for (int i = 0; i < value.length; i++) {

				payInfo += "\"" + key + "\"" + ":" + "\"" + value[i] + "\"" + ",";

			}

		}
		return payInfo;
	}
}

