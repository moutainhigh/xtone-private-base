package com.thirdpay.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.common.util.ConfigManager;
import org.common.util.GenerateIdService;
import org.common.util.ThreadPool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.thirdpay.domain.CpInfoBean;
import com.thirdpay.utils.Canv;
import com.thirdpay.utils.CheckCPInfo;
import com.thirdpay.utils.ConnectionServiceCPInfo;

/**
 * 支付渠道查询Servlet , 返回json数据
 */
@WebServlet("/TestCpInfoServlet")
public class TestCpInfoServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(TestCpInfoServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestCpInfoServlet() {
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
		String appKey = request.getParameter("Appkey");
		if (appKey != null) {
//			CheckInfo(appKey, response); // 查询数据
			CheckInfoMap(appKey, response); // 查询cpinfo新版
		} else {
			response.getWriter().append("fail");
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

	private void CheckInfo(String appKey, HttpServletResponse response) {
		CpInfoBean cpInfoBean = CheckCPInfo.CheckInfo(appKey); //得到cpInfobean对象
		String key = cpInfoBean.getAppkey();

		if (key == null) {
			try {
				response.getWriter().append("-1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String jsonString = JSON.toJSONString(cpInfoBean);
			LOG.info("open jsonString = " + jsonString);
			LOG.info("-------------------------------------");

			try {
				response.getWriter().append(jsonString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 回调json数据格式的数据
		}

	}
	
	
	private void CheckInfoMap(String appKey, HttpServletResponse response) {
		
		HashMap<String, String > map = CheckCPInfo.CheckInfoMap(appKey);
		String key = map.get("appKey");

		if (key == null) {
			try {
				response.getWriter().append("-1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String jsonString = JSON.toJSONString(map);
			LOG.info("open jsonString = " + jsonString);
			LOG.info("-------------------------------------");

			try {
				response.getWriter().append(jsonString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 回调json数据格式的数据
		}

	}
	
	
	public void LoadingCache() throws ExecutionException{

		  
	       LoadingCache<String,String> cahceBuilder=CacheBuilder  
	       .newBuilder().maximumSize(10000).expireAfterAccess(10, TimeUnit.MINUTES)  
	       .build(new CacheLoader<String, String>(){  
	           @Override  
	           public String load(String key) throws Exception {          
	               return createExpensiveGraph(key);  
	           }  
	  
	   private String createExpensiveGraph(String key) {  
	    System.out.println("load into cache!");  
	    System.out.println("key = "+key);  
	    return "hello "+key+"!";  
	    }  
	             
	       });          
//	       cahceBuilder.put("hh", "123");
//	       cahceBuilder.put("aa", "321");
//	       cahceBuilder.put("bb", "123456");
	       
	        System.out.println( "cahceBuilder = " + cahceBuilder.get("hh") );
	        System.out.println( "cahceBuilder = " + cahceBuilder.get("aa") );
	        System.out.println( "cahceBuilder = " + cahceBuilder.get("bb") );
//	       cahceBuilder.get("2");  
//	       cahceBuilder.get("2");  
//	       cahceBuilder.get("3");  
	       //第二次就直接从缓存中取出  
//	       cahceBuilder.get("2");  
		
	
	}
}
