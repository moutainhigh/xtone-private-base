package com.thirdpay.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import org.apache.log4j.Logger;
import org.common.util.ConfigManager;
import org.common.util.ConnectionService;
import org.common.util.GenerateIdService;
import org.common.util.ThreadPool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.swiftpass.util.SwiftpassConfig;
import com.thirdpay.domain.ForwardsyncBean;
import com.thirdpay.domain.ImeiBean;
import com.thirdpay.domain.PayInfoBean;
import com.thirdpay.utils.AES;
import com.thirdpay.utils.AppkeyCanv;
import com.thirdpay.utils.CheckCPInfo;
import com.thirdpay.utils.CheckPayInfo;
import com.thirdpay.utils.Forward;
import com.thirdpay.utils.HttpUtils;
import com.thirdpay.utils.payConstants;



public class testInsert {
	static Logger LOG = Logger.getLogger(testInsert.class);
	public static void main(String[] args) throws Exception {

	
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
		//f17d2fb4eff547c8bebc1e7cc4dcd43c
		
		
		
//        String cSrc = "12345678901234561234567890123456123456789012345612345678901234561234567890123456123456789012345612345678901234561234567890123456123456789012345612345678901234561234567890123456123456789012345612345678901234561234567890123456123456789012345612345678901234561234567890123456www.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.so";
//        System.out.println(cSrc.length());
//        String enString = "";
//		HashMap<String, String > map = CheckCPInfo.CheckInfoMap("f17d2fb4eff547c8bebc1e7cc4dcd43c");
//		String notify_url = map.get("notify_url");
//		String encrypt = map.get("encrypt");
//		String encrypt_key = map.get("encrypt_key");
//		
//		System.out.println(notify_url+"\n" +encrypt+"\n"+encrypt_key);
//		
//		
//		if("1".equals(encrypt) && ! "".equals(encrypt_key)){
//			 // 加密
//			enString = AES.Encrypt(cSrc, encrypt_key);
//	        System.out.println("加密后的字串是：" + enString);
//		//	LOG.info("appkey = "+appkey + " ownOrderId = "+ ownOrderId + "加密后的字串是：" + forwardString);
//			
//		}
//        // 解密
//        String DeString = AES.Decrypt(enString, encrypt_key);
//        System.out.println("解密后的字串是：" + DeString);
//        System.out.println("解密后的字串是：" + DeString.length());
    	
        
        
		        // * 此处使用AES-128-ECB加密模式，key需要为16位。
		         
//		        String cKey = "6010401031024102";
//		        // 需要加密的字串
//		        String cSrc = "12345678901234561234567890123456123456789012345612345678901234561234567890123456123456789012345612345678901234561234567890123456123456789012345612345678901234561234567890123456123456789012345612345678901234561234567890123456123456789012345612345678901234561234567890123456www.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowwww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.sowww.gowhere.so";
//		        System.out.println(cSrc);
//		        System.out.println(cSrc.length());
//		        // 加密
//		        String enString = AES.Encrypt(cSrc, cKey);
//		        System.out.println("加密后的字串是：" + enString);
//		        // 解密
//		        String DeString = AES.Decrypt(enString, cKey);
//		        System.out.println("解密后的字串是：" + DeString);
//		        System.out.println("解密后的字串是：" + DeString.length());
		    
        //f17d2fb4eff547c8bebc1e7cc4dcd43c
//        HashMap<String, String > map = CheckCPInfo.CheckInfoMap("zgt");
//		String notify_url = map.get("notify_url");
//		String encrypt = map.get("encrypt");
//		String encrypt_key = map.get("encrypt_key");
//		
//		        if("1".equals(encrypt) && encrypt_key != null && !"".equals(encrypt_key) && encrypt_key.length()==16){
//		        	System.out.println("jiami");
//		        }
//		String tes =null;
//		tes.toString();
//		String a   = tes.length()+"";
//		if( "16"== tes.length()+""){
//			System.out.println(tes);
//		}

		        
		//postPayment("http://pay.vpayplay.com:808/openapi/haotianpay", "1471366514906033671", "0","11cff472487b47069aa8ca239b42d9ad", "");
  
		
//		HashMap<String, String > appkey_map = CheckCPInfo.CheckInfoMap("f17d2fb4eff547c8bebc1e7cc4dcd43c");
		
//		String wxwap = appkey_map.get("wxwap");
		
//		System.out.println(notify_url + "\n"+encrypt+"\n"+encrypt_key);
//		System.out.println(wxwap);
		
//		HashMap<String, String > map = CheckCPInfo.CheckInfoMap("zgt");
//		String notify_url = map.get("notify_url");
//		String encrypt = map.get("encrypt");
//		String encrypt_key = map.get("encrypt_key");
//		System.out.println(notify_url + "\n"+encrypt+"\n"+encrypt_key);
//		
//		
//		
//		int robbit = 100;
//		int money = 100;
//		int x ;
//		int y;
		
		
		
		
//		String payChannelUserID = "";// 支付渠道的付费用户标识
//
//		String IMEIforwardString = CheckPayInfo.CheckInfoIMEI("1474423101595032007");
//		JSONObject IMEIjson = JSON.parseObject(IMEIforwardString); // 解析自定义参数
//		String payUserIMEI = IMEIjson.getString("imei");// 支付用户IMEI //2016/09/20
//		System.out.println(payUserIMEI);
//		
//		ThreadPool.mThreadPool
//				.execute(new PayInfoBean(100, "", "", "", "", "", "",
//						"", "", "", "", 1, payChannelUserID, payUserIMEI));

		
		// 支付回调地址
//		 String notify_url = SwiftpassConfig.WXSwiftPay_notify_url;
//		System.out.println(notify_url);
//		System.out.println("/");
		
		
		
//		String IMEIforwardString = CheckPayInfo.CheckInfoIMEI(ownOrderId);
//		String payUserIMEI ="";
//		if(!"".equals(IMEIforwardString)){
//			LOG.info("IMEIforwardString --------- "+IMEIforwardString);
//			JSONObject IMEIjson = JSON.parseObject(IMEIforwardString); // 解析自定义参数
//			 payUserIMEI = IMEIjson.getString("imei");//支付用户IMEI //2016/09/20
//			LOG.info("payUserIMEI --------- "+payUserIMEI);
//		}
		

//		PreparedStatement ps = null;
//		Connection con = null;
//		try {
//			con = ConnectionService.getInstance().getConnectionForLocal();
//			ps = con.prepareStatement(
////					"insert into `tbl_imei_users` (id) values (?)");
//		"SELECT COUNT(id) FROM tbl_imei_users WHERE id = 123");
//			ResultSet rs = ps.executeQuery();
//			
//	while (rs.next()) {
//		System.out.println(rs.getString("count(id)"));
//		
////				map.put("appKey", rs.getString("appKey") );
////				map.put("notify_url", rs.getString("notify_url") );
////				map.put(rs.getString("payChannelName"), rs.getString("status"));
////				map.put("encrypt",rs.getString("encrypt"));
////				map.put("webOrderid",orederKey);
////				map.put("encrypt_key",rs.getString("encrypt_key"));
//
//			}
//			
////			int m = 1;
////			ps.setString(m++, "312");
////			int i = ps.executeUpdate();
////System.out.println(" i = " + i );
//			
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
//			System.out.println("相同的imei插入失败");
//		} finally {
//			if (ps != null) {
//				try {
//					ps.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//	
		String recentUseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println(recentUseTime);
		
		ThreadPool.mThreadPool.execute(new ImeiBean("123123",123l , recentUseTime));
		
	}
	/**
	 * post转发数据
	 * 
	 * @param notify_url
	 * @param ownOrderId
	 * @throws Exception
	 */
	public static void postPayment(String notify_url, String ownOrderId, String encrypt, String appkey, String encrypt_key)
			throws Exception {

		String forwardString = CheckPayInfo.CheckInfo(ownOrderId);

		LOG.info("appkey = " + appkey + " ownOrderId = " + ownOrderId + " 加密前的字串是：" + forwardString + " 加密的key是: "+encrypt_key);

		if ("1".equals(encrypt) && encrypt_key != null && !"".equals(encrypt_key) && encrypt_key.length() == 16) {
			// 加密
			forwardString = AES.Encrypt(forwardString, encrypt_key);
			LOG.info("appkey = " + appkey + " ownOrderId = " + ownOrderId + "加密后的字串是：" + forwardString);
   
		}

		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("payment", forwardString));

		String responseContent = HttpUtils.post(notify_url, formparams, ownOrderId);

		// 判断返回状态
		if (responseContent.equals("200")) {

			// 更新0为
			LOG.info(ownOrderId + "返回200 , 插入1002数据");
			// 插入1002数据
			CheckPayInfo.Insert1002(ownOrderId, notify_url,forwardString);

		} else {
			// 返回不为200重复发送
			LOG.info(ownOrderId + "返回数据不为200 失败 ");
			// 更新1001的下次转发时间为1分钟
			CheckPayInfo.Updata1001Time(ownOrderId,responseContent);
		}

	}
}
