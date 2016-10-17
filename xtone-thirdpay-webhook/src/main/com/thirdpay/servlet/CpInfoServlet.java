package com.thirdpay.servlet;

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
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.thirdpay.domain.CpInfoBean;
import com.thirdpay.utils.Canv;
import com.thirdpay.utils.CheckCPInfo;
import com.thirdpay.utils.CheckPayInfo;
import com.thirdpay.utils.ConnectionServiceCPInfo;

/**
 * 支付渠道查询Servlet , 返回json数据
 */
@WebServlet("/CpInfoServlet")
public class CpInfoServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(CpInfoServlet.class);

	private static LoadingCache<String, String> cahceBuilder = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CpInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String appKey = request.getParameter("Appkey");

		if (appKey != null) {

			LoadingCache<String, String> cahceBuilder = getcahceBuilder(appKey, response);
			
			try {
				String guavaJsonString = cahceBuilder.get(appKey);
				if("".equals(guavaJsonString)){
					response.getWriter().append("-1");
				}
				
				//添加webOrderID
				guavaJsonString = addOrderIDintoJsonString(guavaJsonString);
				
				response.getWriter().append(guavaJsonString);
//				System.out.println(guavaJsonString);
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().append("-1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		// if (appKey != null) {
		//// CheckInfo(appKey, response); // 查询数据
		// CheckInfoMap(appKey, response); // 查询cpinfo新版
		// } else {
		// response.getWriter().append("fail");
		// }
		
		
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

//	private void CheckInfo(String appKey, HttpServletResponse response) {
//
//		CpInfoBean cpInfoBean = CheckCPInfo.CheckInfo(appKey); // 得到cpInfobean对象
//		String key = cpInfoBean.getAppkey();
//
//		if (key == null) {
//			try {
//				response.getWriter().append("-1");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else {
//			String jsonString = JSON.toJSONString(cpInfoBean);
//			LOG.info("open jsonString = " + jsonString);
//			LOG.info("-------------------------------------");
//
//			try {
//				response.getWriter().append(jsonString);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} // 回调json数据格式的数据
//		}
//
//	}

	private String CheckInfoMap(String appKey, HttpServletResponse response) {
		String jsonString = "";
		HashMap<String, String> map = CheckCPInfo.CheckInfoMap(appKey);
		String key = map.get("appKey");

		if (key == null) {
			 try {
			 response.getWriter().append("-1");
			 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
		} else {
			jsonString = JSON.toJSONString(map);
			LOG.info("open jsonString = " + jsonString);
			LOG.info("-------------------------------------");
			

			// try {
			// response.getWriter().append(jsonString);
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// } // 回调json数据格式的数据

		}
		return jsonString;
	}

	public LoadingCache<String, String> getcahceBuilder(final String appKey, final HttpServletResponse response) {

		if (cahceBuilder == null) {
			cahceBuilder = CacheBuilder.newBuilder().maximumSize(10)// 最大缓存5000个对象
//					.expireAfterAccess(5, TimeUnit.SECONDS) // 5秒无访问缓存失效
					.refreshAfterWrite(10, TimeUnit.SECONDS) //10秒后自动刷新缓存
					// 设置缓存的移除通知
					.removalListener(new RemovalListener<Object, Object>() {
						@Override
						public void onRemoval(RemovalNotification<Object, Object> notification) {
							System.out.println(
									notification.getKey() + " was removed, cause is " + notification.getCause());
						}
					})

					// build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
					.build(new CacheLoader<String, String>() {
						@Override
						public String load(String key) throws Exception {
							// load a new TradeAccount not exists in cache
							// 创造一个新的对象 如果String对象不存在
							return createExpensiveGraph(key);
						}

						private String createExpensiveGraph(String key) {

//							System.out.println("缓存不存在,正自动加载缓存" + " key = " + key);

							return CheckInfoMap(key, response);// 查询cpinfo新版

						}

					});
		}

		return cahceBuilder;
	}
	
	/**
	 * 得到OrderID
	 */
	public String getOrderID(){
		// (网游)
		 Long key = GenerateIdService.getInstance()
		 .generateNew(Integer.parseInt(ConfigManager.getConfigData("server.id").trim()),
		 "clicks", 1);
		 
		 String orederKey = key + "";
		return orederKey;
	}
	/**
	 * 在guavaJsonString上增加webOrderid
	 * @param guavaJsonString
	 * @return
	 */
	public String addOrderIDintoJsonString(String guavaJsonString){
		
		guavaJsonString=guavaJsonString.substring(0,guavaJsonString.length()-1);
		StringBuilder builder = new StringBuilder(guavaJsonString);
		// "productInfo": "0"
		builder.append(",\"webOrderid\":"+"\""+getOrderID()+"\""+"}");
		guavaJsonString = builder.toString();
		
		return guavaJsonString;
	}
}
