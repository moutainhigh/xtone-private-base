package com.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;

import org.common.util.ConfigManager;
import org.common.util.GenerateIdService;
import org.demo.utils.ConnectionServiceConfig;


public class mainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// JSONObject json = JSON.; // 解析自定义参数
		// String url =
		// "http://thirdpay-cs.n8wan.com:29141/CpInfoServlet?Appkey=zgt";
		// String str = getJsonContent(url, "utf-8");
		// JSONObject json = JSON.parseObject(str); // 解析自定义参数
		// System.out.println(json.getString("baidupay"));

		// String test = null;

		// if(test != null && !test.equals("")){
		// System.out.println("is null");
		// }

		// if("".equals(test)){

		// if(test != null){
		//
		// }
		// }

		// String forwardString = CheckPayInfo.CheckInfo("87web1469686110398");
		//
		//
		// System.out.println(forwardString);
		// TODO Auto-generated method stub
		// CpInfoBean cpInfoBean = new CpInfoBean();
//		PreparedStatement ps = null;
//		Connection con = null;
//		try {
//			
//			
//			
//			///////------------------
//			// DbKey 选择使用的数据库
//			con = ConnectionServiceConfig.getInstance().getConnectionForLocal(); // DbKey选择使用config.properties
////			ps = con.prepareStatement("SELECT * FROM tbl_thirdpay_cp_information WHERE appKey=" +  "'zgt'" );
////			ps = con.prepareStatement("SELECT * FROM 'tbl_thirdpay_apps left join' course on tbl_thirdpay_apps left.appKey = tbl_thirdpay_app_pay_channel_relations.appKey WHERE appKey=" +  "'zgt'"  );
////			ps = con.prepareStatement("SELECT * FROM tbl_thirdpay_apps,tbl_thirdpay_app_pay_channel_relations,tbl_thirdpay_pay_channels WHERE tbl_thirdpay_app_pay_channel_relations.`payChannelName` = tbl_thirdpay_pay_channels.`payChannelName` AND tbl_thirdpay_app_pay_channel_relations.appKey = '3aa1d5b4e2474f0da4d129b985924f9b' AND tbl_thirdpay_apps.`appKey`='3aa1d5b4e2474f0da4d129b985924f9b' ");
//			ps = con.prepareStatement("select FROM_UNIXTIME(id/1000/1000000, '%Y-%m-%d') AS date,sum(price) as price,GROUP_CONCAT(DISTINCT appkey) AS appkey, COUNT(DISTINCT payUserIMEI) as payUserIMEI from log_success_pays where appkey='"
//					+"f17d2fb4eff547c8bebc1e7cc4dcd43c' group by date ORDER BY date DESC");
//			
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				
////				System.out.println("date = "+ rs.getString("date"));
//				System.out.println("imei = "+ rs.getString("payUserIMEI"));
//				
////				System.out.println("aliPay = " + rs.getString("aliPay"));
////				System.out.println("aliPay = " + rs.getString("aliPay"));
////				System.out.println("unionPay = " + rs.getString("unionPay"));
////				System.out.println("wechatPay = " + rs.getString("wechatPay"));
////				System.out.println("baiduPay = " + rs.getString("baiduPay"));
////				System.out.println("smsPay = " + rs.getString("smsPay"));
////				System.out.println("productInfo = " + rs.getString("productInfo"));
////				System.out.println("notify_url = " + rs.getString("notify_url"));
////				System.out.println("WXwap = " + rs.getString("WXwap"));
////				System.out.println("encrypt = " + rs.getString("encrypt"));
//
//			}
/////////////////----------------------
//			
////			con = ConnectionServicethirdpayCount.getInstance().getConnectionForLocal(); // DbKey选择使用config.properties
////			ps = con.prepareStatement("SELECT * FROM log_async_generals WHERE para05 = " + "'"+"88888888"+"'");
////			ResultSet rs = ps.executeQuery();
////			String jsonString = "";
////			while (rs.next()) {
////				jsonString = rs.getString("para04");
////			}
////			
////			System.out.println(jsonString);
//			
////			JSON.toJSONString(cpInfoBean);
//		
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
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
		float   scale   =   (float) 34.236323;  
		  DecimalFormat   fnum   =   new   DecimalFormat("##0.00");  
		  String   dd=fnum.format(scale);      
		  System.out.println(dd); 
		PreparedStatement ps = null;
		Connection con = null;
		try {
			
			
			
			///////------------------
			// DbKey 选择使用的数据库
			con = ConnectionServiceConfig.getInstance().getConnectionForLocal(); // DbKey选择使用config.properties
//			ps = con.prepareStatement("SELECT * FROM tbl_thirdpay_cp_information WHERE appKey=" +  "'zgt'" );
//			ps = con.prepareStatement("SELECT * FROM 'tbl_thirdpay_apps left join' course on tbl_thirdpay_apps left.appKey = tbl_thirdpay_app_pay_channel_relations.appKey WHERE appKey=" +  "'zgt'"  );
//			ps = con.prepareStatement("SELECT * FROM tbl_thirdpay_apps,tbl_thirdpay_app_pay_channel_relations,tbl_thirdpay_pay_channels WHERE tbl_thirdpay_app_pay_channel_relations.`payChannelName` = tbl_thirdpay_pay_channels.`payChannelName` AND tbl_thirdpay_app_pay_channel_relations.appKey = '3aa1d5b4e2474f0da4d129b985924f9b' AND tbl_thirdpay_apps.`appKey`='3aa1d5b4e2474f0da4d129b985924f9b' ");
			ps = con.prepareStatement("select FROM_UNIXTIME(id/1000/1000000, '%Y-%m-%d') AS date,sum(price) as price,GROUP_CONCAT(DISTINCT appkey) AS appkey, COUNT(DISTINCT payUserIMEI) as payUserIMEI from log_success_pays where appkey='"
					+"f17d2fb4eff547c8bebc1e7cc4dcd43c' group by date ORDER BY date DESC");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
//				System.out.println("date = "+ rs.getString("date"));
				System.out.println("imei = "+ rs.getString("payUserIMEI"));
				
//				System.out.println("aliPay = " + rs.getString("aliPay"));
//				System.out.println("aliPay = " + rs.getString("aliPay"));
//				System.out.println("unionPay = " + rs.getString("unionPay"));
//				System.out.println("wechatPay = " + rs.getString("wechatPay"));
//				System.out.println("baiduPay = " + rs.getString("baiduPay"));
//				System.out.println("smsPay = " + rs.getString("smsPay"));
//				System.out.println("productInfo = " + rs.getString("productInfo"));
//				System.out.println("notify_url = " + rs.getString("notify_url"));
//				System.out.println("WXwap = " + rs.getString("WXwap"));
//				System.out.println("encrypt = " + rs.getString("encrypt"));

			}
///////////////----------------------
			
//			con = ConnectionServicethirdpayCount.getInstance().getConnectionForLocal(); // DbKey选择使用config.properties
//			ps = con.prepareStatement("SELECT * FROM log_async_generals WHERE para05 = " + "'"+"88888888"+"'");
//			ResultSet rs = ps.executeQuery();
//			String jsonString = "";
//			while (rs.next()) {
//				jsonString = rs.getString("para04");
//			}
//			
//			System.out.println(jsonString);
			
//			JSON.toJSONString(cpInfoBean);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static String getJsonContent(String url_path, String encode) {

		String jsonString = "";
		try {
			URL url = new URL(url_path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setConnectTimeout(3000);
			connection.setRequestMethod("GET");
			connection.setDoInput(true); // 从服务器获得数据

			int responseCode = connection.getResponseCode();

			if (200 == responseCode) {
				jsonString = changeInputStream(connection.getInputStream(), encode);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		//
		return jsonString;
	}

	private static String changeInputStream(InputStream inputStream, String encode) throws IOException {
		// TODO Auto-generated method stub
		String jsonString = null;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(data)) != -1) {
			outputStream.write(data, 0, len);
		}

		jsonString = new String(outputStream.toByteArray(), encode);

		inputStream.close();

		return jsonString;
	}
}
