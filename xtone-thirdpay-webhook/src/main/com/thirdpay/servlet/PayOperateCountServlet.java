package com.thirdpay.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.common.util.ThreadPool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.UTF8Decoder;
import com.thirdpay.domain.PayOperateBean;
import com.thirdpay.utils.Canv;
import com.thirdpay.utils.EncodeUtils;

/**
 * 支付操作统计Servlet
 */
@WebServlet("/PayOperateCountServlet")
public class PayOperateCountServlet extends HttpServlet {

	private String releaseChannel;

	private String appKey;

	private String payChannel;

	private String payParams;

	private String ownOrderid;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayOperateCountServlet() {
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
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String str = "";
		String payOperateCode = request.getParameter("payOperateCode"); // 支付操作状态码
		String op_notifyData = request.getParameter("op_notifyData");
		
		op_notifyData = EncodeUtils.encode(op_notifyData);

		System.out.println("op_notifyData = " + op_notifyData);

		if (op_notifyData != null) {
			JSONObject json = JSON.parseObject(op_notifyData); // 解析自定义参数
			// releaseChannel = json.getString("channel");
			appKey = json.getString("appkey");
			payParams = json.getString("payParams"); // 传给cp的信息
			
			if (payParams != null) {

				str = payParams.replace("\\", "");

				JSONObject payParamsjson = JSON.parseObject(str); //

				ownOrderid = payParamsjson.getString("webOrderid");

			}
			if (ownOrderid == null) {

				ownOrderid = "88888888";

			}
			// 添加sql查询语句

			payChannel = json.getString("platform");
			ThreadPool.mThreadPool.execute(new PayOperateBean(Integer.parseInt(payOperateCode),
					Canv.parm.get(payOperateCode), appKey, op_notifyData, str, ownOrderid));

		}

		response.getWriter().append("success");

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

}
