package com.thirdpay.domain;

/**
 * 支付信息写入
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.common.util.ConfigManager;
import org.common.util.ConnectionService;
import org.common.util.GenerateIdService;
import org.common.util.ThreadPool;

import com.swiftpass.util.SwiftpassConfig;
import com.thirdpay.servlet.AlipayCountServlet;
import com.thirdpay.utils.AppkeyCanv;
import com.thirdpay.utils.CheckCPInfo;
import com.thirdpay.utils.Forward;
import com.thirdpay.utils.HttpUtils;
import com.thirdpay.utils.payConstants;

public class PayInfoBean implements Runnable {
	private static final Logger LOG = Logger.getLogger(PayInfoBean.class);
	private Long id;
	private int price; // 价格，单位人民币，分
	private String payChannel;// 支付通道
	private String ip;// 来源ip
	private String payInfo;// 从支付通道获取的原始内容
	private String releaseChannel;// 发行通道ID，一般从payInfo中解析出
	private String appKey;// CP方ID，一般从payInfo中解析出
	private String payChannelOrderId;// 支付通道的订单号，一般从payInfo中解析出
	private String ownUserId;// 付费用户ID，待用
	private String ownItemId;// 购买道具ID，待用
	private String ownOrderId;// 原始订单号ID
	private String cpOrderId;// pc方订单号ID
	private int testStatus;// 是否是测试信息

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(int testStatus) {
		this.testStatus = testStatus;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public String getReleaseChannel() {
		return releaseChannel;
	}

	public void setReleaseChannel(String releaseChannel) {
		this.releaseChannel = releaseChannel;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getPayChannelOrderId() {
		return payChannelOrderId;
	}

	public void setPayChannelOrderId(String payChannelOrderId) {
		this.payChannelOrderId = payChannelOrderId;
	}

	public String getOwnUserId() {
		return ownUserId;
	}

	public void setOwnUserId(String ownUserId) {
		this.ownUserId = ownUserId;
	}

	public String getOwnItemId() {
		return ownItemId;
	}

	public void setOwnItemId(String ownItemId) {
		this.ownItemId = ownItemId;
	}

	public String getOwnOrderId() {
		return ownOrderId;
	}

	public String getCpOrderId() {
		return cpOrderId;
	}

	public void setCpOrderId(String cpOrderId) {
		this.cpOrderId = cpOrderId;
	}

	public void setOwnOrderId(String ownOrderId) {
		this.ownOrderId = ownOrderId;
	}

	public PayInfoBean(int price, String payChannel, String ip, String payInfo, String releaseChannel, String appKey,
			String payChannelOrderId, String ownUserId, String ownItemId, String ownOrderId, String cpOrderId,
			int testStatus) {
		super();
		this.price = price;
		this.payChannel = payChannel;
		this.ip = ip;
		this.payInfo = payInfo;
		this.releaseChannel = releaseChannel;
		this.appKey = appKey;
		this.payChannelOrderId = payChannelOrderId;
		this.ownUserId = ownUserId;
		this.ownItemId = ownItemId;
		this.ownOrderId = ownOrderId;
		this.cpOrderId = cpOrderId;
		this.testStatus = testStatus;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		setId(GenerateIdService.getInstance()
				.generateNew(Integer.parseInt(ConfigManager.getConfigData("server.id").trim()), "clicks", 1));

		if (this.id > 0) {
			PreparedStatement ps = null;
			Connection con = null;
			try {
				con = ConnectionService.getInstance().getConnectionForLocal();
				ps = con.prepareStatement(
						"insert into `log_success_pays` (id,price,payChannel,ip,payInfo,releaseChannel,appKey,payChannelOrderId,ownUserId,ownItemId,ownOrderId,cpOrderId,testStatus) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
				int m = 1;
				ps.setLong(m++, this.getId());
				ps.setInt(m++, this.getPrice());
				ps.setString(m++, this.getPayChannel());
				ps.setString(m++, this.getIp());
				ps.setString(m++, this.getPayInfo());
				ps.setString(m++, this.getReleaseChannel());
				ps.setString(m++, this.getAppKey());
				ps.setString(m++, this.getPayChannelOrderId());
				ps.setString(m++, this.getOwnUserId());
				ps.setString(m++, this.getOwnItemId());
				ps.setString(m++, this.getOwnOrderId());
				ps.setString(m++, this.getCpOrderId());
				ps.setInt(m++, this.getTestStatus());

				int i = ps.executeUpdate();
				/**
				 * 数据同步状态码 订单号状态0表示等待同步；1表示同步成功 计划下次处理时间，毫秒数 已经处理次数 目标地址 成功判定条件
				 * appkey or channelId 填入配置的id值 id_type数据库字段对应
				 */
				if ((i + "").equals("1")) {
					String notify_url = CheckCPInfo.CheckInfo(this.getAppKey()).getNotify_url();// 通过appkey得到转发url
					String Wj_notify_url ="";
					LOG.info("notify_url  == " + notify_url);
					LOG.info("apppppkey  == " + this.getAppKey());

					if (this.getAppKey().equals("ae03d9d6e0444bb08af1f1098b2afafc")) {
						// 根据appkey转发数据
						appkeyFroward(this.getAppKey(), this.getPrice() + "", this.getPayChannel(), this.getIp(),
								this.getReleaseChannel(), this.getPayChannelOrderId(), this.getCpOrderId());
					}

					// 转发数据到wj_url
					// 从配置文件得到转发地址wj_url
					try {
						 Wj_notify_url = SwiftpassConfig.wj_notify_url;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if (Wj_notify_url.equals(payConstants.wj_url)) {
						String oprator = getOprator(this.getPayChannel());
						String createdate = new SimpleDateFormat("yyyy-MM-dd%20HH:mm:ss").format(new Date());
						// StringBuilder builder = new
						// StringBuilder(payConstants.wj_url);
						StringBuilder builder = new StringBuilder(SwiftpassConfig.wj_notify_url);

						builder.append("?createdate=" + createdate);
						builder.append("&oprator=" + oprator); // 2016-06-12增加支付渠道参数
						builder.append("&appkey=" + this.getAppKey());
						builder.append("&channelid=" + this.getReleaseChannel());
						builder.append("&amount=" + this.getPrice() + "");
						builder.append("&orderid=" + this.getPayChannelOrderId());
						builder.append("&imei=" + "");
						builder.append("&imsi=" + "");
						builder.append("&userorderid=" + this.getCpOrderId());
						builder.append("&status=" + "0");
						LOG.info("--------------------------builder = " + builder.toString());
						String responseStr = HttpUtils.get(builder.toString());
						if (responseStr.equals("ok")) {
							LOG.info("插入DawuxianpingTai成功");
						} else {
							LOG.info("responseStr = " + responseStr);
						}
					}

					// 转发插入日志表
					ThreadPool.mThreadPool.execute(new ForwardsyncBean(1001, this.getOwnOrderId(), "0", "0", "0",
							notify_url, "200", this.getAppKey(), "appkey"));

				}

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
	}

	public static String getOprator(String payChannel) {

		String oprator = "";
		if (payChannel.equals("wx")) {
			oprator = "4";
		} else if (payChannel.equals("alipay")) {
			oprator = "5";
			// LOG.info("------------ oprator = " + oprator);
		} else if (payChannel.equals("unionpay")) {
			oprator = "6";
		} else if (payChannel.equals("baidu")) {
			oprator = "7";
		} else if (payChannel.equals("wxWap")) {
			oprator = "8";
		} else {
			oprator = "otherpay";

		}
		return oprator;
	}

	public void appkeyFroward(String appKey, String price, String payChannel, String ip, String releaseChannel,
			String payChannelOrderId, String cpOrderId) {
		String forward_url = AppkeyCanv.parm.get(this.getAppKey());
		StringBuilder builder = new StringBuilder(forward_url);

		builder.append("?price=" + price);
		builder.append("&payChannel=" + payChannel); // 2016-06-12增加支付渠道参数
		builder.append("&ip=" + ip);
		builder.append("&releaseChannel=" + releaseChannel);
		builder.append("&appKey=" + appKey);
		builder.append("&payChannelOrderId=" + payChannelOrderId);
		builder.append("&cpOrderId=" + cpOrderId);
		LOG.info("--------------------------bingfenggu builder = " + builder.toString());
		String responseStr = HttpUtils.get(builder.toString());
		if (responseStr.equals("ok")) {
			LOG.info("插入bingfenggu成功");
		} else {
			LOG.info("responseStr = " + responseStr);
		}
	}

}
