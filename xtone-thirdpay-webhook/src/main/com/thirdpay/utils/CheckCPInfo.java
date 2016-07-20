package com.thirdpay.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.common.util.ConfigManager;
import org.common.util.GenerateIdService;

import com.alibaba.fastjson.JSON;
import com.thirdpay.domain.CpInfoBean;

/**
 * 查询cp信息 返回cpInfoBean对象
 * 
 * @author 28518
 *
 */
public class CheckCPInfo {

	public static CpInfoBean CheckInfo(String appKey) {
		// TODO Auto-generated method stub
		CpInfoBean cpInfoBean = new CpInfoBean();
		PreparedStatement ps = null;
		Connection con = null;
		try {
			// DbKey 选择使用的数据库
			con = ConnectionServiceCPInfo.getInstance().getConnectionForLocal(); // DbKey选择使用config.properties
			ps = con.prepareStatement("SELECT * FROM tbl_thirdpay_cp_information WHERE appKey=" + "'" + appKey + "'");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				cpInfoBean.setAppkey(rs.getString("appKey"));
				cpInfoBean.setAlipay(rs.getString("aliPay"));
				cpInfoBean.setUnionpay(rs.getString("unionPay"));
				cpInfoBean.setWechatpay(rs.getString("wechatPay"));
				cpInfoBean.setBaidupay(rs.getString("baiduPay"));
				cpInfoBean.setSmspay(rs.getString("smsPay"));
				cpInfoBean.setProductInfo(rs.getString("productInfo"));
				cpInfoBean.setNotify_url(rs.getString("notify_url"));
				cpInfoBean.setWxwap(rs.getString("WXwap"));
				
				// (网游)
				Long key = GenerateIdService.getInstance()
						.generateNew(Integer.parseInt(ConfigManager.getConfigData("server.id").trim()), "clicks", 1);
				String orederKey = key + "";

				cpInfoBean.setWebOrderid(orederKey);
				
				cpInfoBean.setEncrypt(rs.getString("encrypt"));
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

		return cpInfoBean;
	}
}
