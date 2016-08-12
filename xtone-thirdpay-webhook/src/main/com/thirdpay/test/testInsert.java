package com.thirdpay.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.message.BasicNameValuePair;
import org.common.util.ConfigManager;
import org.common.util.ConnectionService;
import org.common.util.GenerateIdService;
import org.common.util.ThreadPool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thirdpay.domain.ForwardsyncBean;
import com.thirdpay.domain.PayInfoBean;
import com.thirdpay.utils.CheckCPInfo;
import com.thirdpay.utils.Forward;
import com.thirdpay.utils.HttpUtils;
import com.thirdpay.utils.payConstants;



public class testInsert {

	public static void main(String[] args) {

		// while(true){
//		 ThreadPool.mThreadPool.execute(new PayInfoBean(0, "cbl", "cbl", "cbl", "cbl", "cbl", "cbl","cbl", "cbl", "cbl","cbl", 0));
		// }

		// ThreadPool.mThreadPool.execute(new PayInfoBean(0, "cbl", "cbl",
		// "cbl", "cbl", "cbl", "cbl", "cbl", "cbl", "cbl", "cbl", 0));

		// PreparedStatement ps = null;
		// Connection con = null;
		// con = ConnectionService.getInstance().getConnectionForLocal();
		// try {
		// ps = con.prepareStatement("SELECT * FROM tbl_thirdpay_cp_information
		// WHERE appKey=" + "'" + "zgt" + "'");
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// ThreadPool.mThreadPool.execute(new ForwardsyncBeanTest(1001,
		// "123456", "0","3000", "0", "url", "200", "zgt", "appkey"));

		// StringBuilder MyStringBuilder = new StringBuilder("Hello World!");
		// MyStringBuilder.append(" What a beautiful day.");
		// MyStringBuilder.insert(0, "cbl");
		// MyStringBuilder.deleteCharAt(MyStringBuilder.length()-1);
		// System.out.println(MyStringBuilder);

		// ����������
		// String payInfo = "";
		// String[] cbl = { "aa" };
		// String[] zgt = { "cc" };
		// String[] cdf = { "ee" };
		// Map<String, String[]> map = new HashMap<String, String[]>();
		// map.put("cbl", cbl);
		// map.put("zgt", zgt);
		// map.put("cdf", cdf);
		// List<BasicNameValuePair> formparams = new
		// ArrayList<BasicNameValuePair>();
		//
		// Iterator<Entry<String, String[]>> iterator =
		// map.entrySet().iterator();
		//
		// while (iterator.hasNext()) {
		// Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>)
		// iterator.next();
		//
		// String key = entry.getKey(); // keyΪ��������
		// if (key != "cbl") {
		// String[] value = map.get(key); // valueΪ����ֵ
		//
		// for (int i = 0; i < value.length; i++) {
		//
		// payInfo += "\"" + key + "\"" + ":" + "\"" + value[i] + "\"" + ",";
		//
		// }
		// }
		//
		// }
		// System.out.println("payInfo = " + payInfo);
		// StringBuilder builder = new StringBuilder(payInfo);
		// builder.deleteCharAt(builder.length() - 1);
		// builder.insert(0, "{");
		// builder.append("}");
		// System.out.println(builder);
		
//		String url = CheckCPInfo.CheckInfo("zgt").getNotify_url();
//		ThreadPool.mThreadPool
//				.execute(new ForwardsyncBeanTest(1001, "123456", "0", "3000", "0", "url", "200", "zgt", "appkey"));

//		String str = "{\"buyNum\":0,\"coinNum\":0,\"price\":1,\"productDesc\":\"��Ʒ������ƻ����ƷidΪ123456\",\"productId\":\"12345\",\"productName\":\"ƻ��\",\"ratio\":0,\"roleLevel\":0,\"uid\":\"7d07ccb3-8d83-4ebc-b2e9-2f37b120fa0d\",\"webOrderid\":\"1462867117426032111\"}";
		
//		System.out.println(str.replace("\\", ""));
		
//		JSONObject payParamsjson =  JSON.parseObject(str.replace("\\", "")); 
		
//		Forward.wj_forward("cbl", "cbl", "1", "testbl1", "cbl", "cbl", "cbltest");
//		
////		String url = "http://h1.n8wan.com:2109/xyapp/app.ashx?createdate=2016-05-20 13:51:59&appkey=tt&channelid=bl&amount=1&orderid=test2&imei=cbl&imsi=cbl&userorderid=cbl&status=0";
//		String url = "http://h1.n8wan.com:2109/xyapp/app.ashx?createdate=2016-05-20%14:18:59&appkey=tt&channelid=bl&amount=1&orderid=test4&imei=cbl&imsi=cbl&userorderid=cbl&status=0";
////		String urltest = "http://192.168.0.101:8080/thirdpay-webhook/TestServlet?appkey=cbl";
//		HttpUtils.get(url);
		
		
//		String createdate = new SimpleDateFormat("yyyy-MM-dd%HH:mm:ss").format(new Date());
//		System.out.println(createdate);
		
		
//		StringBuilder builder = new StringBuilder(payConstants.wj_url);
//
//		builder.append("?createdate=" + "BL");
//		builder.append("&oprator=" +"BL"); // 2016-06-12����֧����������
//		builder.append("&appkey=" + "BL");
//		builder.append("&channelid=" + "BL");
//		builder.append("&amount=" + "BL");
//		builder.append("&orderid=" + "BL");
//		builder.append("&imei=" + "");
//		builder.append("&imsi=" + "");
//		builder.append("&userorderid=" +"BL");
//		builder.append("&status=" + "0");
//		//LOG.info("--------------------------builder = " + builder.toString());
//		
//		System.out.println( "--------------------------builder = "+builder.toString());
		
		
		// 转发插入日志表
//		ThreadPool.mThreadPool.execute(new ForwardsyncBean(1001, "orderid", "0", "0", "0",
//				"notify_url", "200", "cblappkey", "appkey"));
		
//		HashMap<String, String > map = new HashMap<String, String>();
//		  map.put("username", "zhangsan");
//		  map.put("age", "24");
//		  map.put("sex", "男");
//		String a = JSON.toJSONString(map);
//		System.out.println(a);
		HashMap<String, String > map = CheckCPInfo.CheckInfoMap("zgt");
		String notify_url = map.get("notify_url");
		String encrypt = map.get("encrypt");
		System.out.println(notify_url+"\n" +encrypt);
	}

}
