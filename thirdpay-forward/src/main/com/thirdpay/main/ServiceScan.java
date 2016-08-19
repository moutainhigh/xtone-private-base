package com.thirdpay.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.common.util.ConfigManager;
import org.common.util.ConnectionService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thirdpay.utils.AES;
import com.thirdpay.utils.CheckPayInfo;
import com.thirdpay.utils.ConnectionServicethirdpayCount;
import com.thirdpay.utils.Forward;
import com.thirdpay.utils.HttpUtils;

public class ServiceScan {

	private static final Logger LOG = Logger.getLogger(ServiceScan.class);

	private static ServiceScan instance = new ServiceScan();

	public static ServiceScan getInstance() {
		return instance;
	}

	public void scan() {
		String sql = "select * from `log_async_generals` where logId = 1001 AND para02 = 'syncFail'";
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ConnectionServicethirdpayCount.getInstance().getConnectionForLocal();
			ps = con.prepareStatement(sql);

			int m = 0;
			rs = ps.executeQuery();

			while (rs.next()) {
				
				String notify_url = rs.getString("para05"); // 回调地址
				String ownOrderId = rs.getString("para01"); // 自己生成的订单号
				String forwardString = rs.getString("para09");
				String encrypt = rs.getString("para10");
				String encrypt_key = rs.getString("para11");
				String appkey =rs.getString("para07");
				
				// 开始转发
				if (notify_url != null && ownOrderId != null) {
					
					// 异步发送
					new Thread(new Runnable() {
						public void run() {
							LOG.info(ownOrderId + " 数据开始转发,地址为   "+notify_url);
							
								//Forward.forward(notify_url, ownOrderId,forwardString,encrypt,encrypt_key);
								
								try {
									postPayment(notify_url, ownOrderId, encrypt, appkey, encrypt_key,forwardString);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

						}
					}).start();

				}
				m++;
			}

			if (m == 0) {
				LOG.info("scan nothing");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

	private int processToken(ResultSet rs, int m) throws SQLException, Exception {
		String secret = ConfigManager.getConfigData(rs.getString("appId"));
		if (secret != null && secret.length() >= 32) {
			TokenGet token = new TokenGet();
			token.setAppId(rs.getString("appId"));
			token.setSecret(secret);
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
			String rsp = token.get();
			LOG.info("receive from tencent:" + rsp);
			RspToken rspToken = gson.fromJson(rsp, RspToken.class);
			if (rspToken.verify()) {
				rspToken.setAppId(rs.getString("appId"));
				rspToken.updateDb();
			}
			m++;
		}
		return m;
	}

	
	/**
	 * post转发数据
	 * 
	 * @param notify_url
	 * @param ownOrderId
	 * @throws Exception
	 */
	public void postPayment(String notify_url, String ownOrderId, String encrypt, String appkey, String encrypt_key,String forwardString)
			throws Exception {
		String Notencrypt_forwardString = forwardString;//未加密的forwardString
		LOG.info("appkey = " + appkey + " ownOrderId = " + ownOrderId + " 加密前的字串是：" + forwardString + " 加密的key是: "
				+ encrypt_key);

		if ("1".equals(encrypt) && encrypt_key != null && !"".equals(encrypt_key) && encrypt_key.length() == 16) {
			// 加密
			forwardString = AES.Encrypt(forwardString, encrypt_key);
			LOG.info("appkey = " + appkey + " ownOrderId = " + ownOrderId + "加密后的字串是：" + forwardString);

		}

		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("payment", forwardString));

		//返回的数据responseContent
		String responseContent = HttpUtils.post(notify_url, formparams, ownOrderId);

		// 判断返回状态
		if (responseContent.equals("200")) {

			LOG.info(ownOrderId + "返回200 , 更新1001的status为 syncSuccess ");
			// 更新1001的状态并插入1002数据
			CheckPayInfo.Updata1001(ownOrderId, notify_url,Notencrypt_forwardString);
			// CheckPayInfo.InsertInfo(ownOrderId, notify_url);

		} else {
			// 返回不为200重复发送
			LOG.info(ownOrderId + "返回数据不为200 失败 ");
			// 更新1001的下次转发时间为1分钟
			CheckPayInfo.Updata1001Time(ownOrderId,responseContent);
		}

	}
}
