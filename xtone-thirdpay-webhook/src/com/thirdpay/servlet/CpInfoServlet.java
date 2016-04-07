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

import org.common.util.ThreadPool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thirdpay.domain.CpInfoBean;
import com.thirdpay.utils.Canv;
import com.thirdpay.utils.ConnectionServiceCPInfo;

/**
 * Servlet implementation class CpInfoServlet
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
		CheckInfo(response); // 查询数据
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
			// ps = con.prepareStatement("SELECT * FROM thirdpay_cp_information
			// WHERE appkey='cbl'");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				// rs.getString("appKey").equals("");
				// if (rs.getString("appKey").equals("")) {
				// System.out.println("appkey找不到");
				// }

//				System.out.println(rs.getString("appKey") + "\n" + rs.getString("id") + "\n"
//						+ rs.getString("notify_url") + "\n" + rs.getString("alipay") + "\n" + rs.getString("unionpay")
//						+ "\n" + rs.getString("wechatpay") + "\n" + rs.getString("baidupay") + "\n"
//						 + rs.getString("smspay"));

				cpInfoBean.setAlipay(rs.getString("aliPay"));
				cpInfoBean.setUnionpay(rs.getString("unionPay"));
				cpInfoBean.setWechatpay(rs.getString("wechatPay"));
				cpInfoBean.setBaidupay(rs.getString("baiduPay"));
				cpInfoBean.setSmspay(rs.getString("smsPay"));
				cpInfoBean.setProductInfo(rs.getString("productInfo"));
				// 用户组对象转JSON串
				jsonString = JSON.toJSONString(cpInfoBean);

			}

			if (!rs.isAfterLast()) {
				response.getWriter().append("-1");

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
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

		try {
			response.getWriter().append(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 回调json数据格式的数据

	}
}
