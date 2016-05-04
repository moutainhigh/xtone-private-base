package com.thirdpay.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.common.util.ConfigManager;
import org.common.util.GenerateIdService;
import org.common.util.ThreadPool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thirdpay.domain.CpInfoBean;
import com.thirdpay.utils.Canv;
import com.thirdpay.utils.ConnectionServiceCPInfo;

/**
 * 支付渠道查询Servlet , 返回json数据
 */
@WebServlet("/CpInfoServlet")
public class CpInfoServlet extends HttpServlet {
	String appKey = "";

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
		// TODO Auto-generated method stub
		appKey = request.getParameter("Appkey");
		System.out.println("appKey = " + appKey);
		if (appKey != null) {
			CheckInfo(response); // 查询数据
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

	private void CheckInfo(HttpServletResponse response) {
		// TODO Auto-generated method stub
		CpInfoBean cpInfoBean = new CpInfoBean();
		String jsonString = "";
		PreparedStatement ps = null;
		Connection con = null;
		try {
			// DbKey 选择使用的数据库
			con = ConnectionServiceCPInfo.getInstance().getConnectionForLocal(); // DbKey选择使用config.properties
			ps = con.prepareStatement("SELECT * FROM tbl_thirdpay_cp_information WHERE appKey=" + "'" + appKey + "'");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				cpInfoBean.setAlipay(rs.getString("aliPay"));
				cpInfoBean.setUnionpay(rs.getString("unionPay"));
				cpInfoBean.setWechatpay(rs.getString("wechatPay"));
				cpInfoBean.setBaidupay(rs.getString("baiduPay"));
				cpInfoBean.setSmspay(rs.getString("smsPay"));
				cpInfoBean.setProductInfo(rs.getString("productInfo"));

				// (网游)
				Long key = GenerateIdService.getInstance()
						.generateNew(Integer.parseInt(ConfigManager.getConfigData("server.id").trim()), "clicks", 1);
				String orederKey = key + "";

				cpInfoBean.setWebOrderid(orederKey);

				// 用户组对象转JSON串
				jsonString = JSON.toJSONString(cpInfoBean);
				System.out.println("jsonString = " + jsonString);

			}

			if (!rs.isAfterLast()) {
				response.getWriter().append("-1");

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

		try {
			response.getWriter().append(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 回调json数据格式的数据

	}
}
