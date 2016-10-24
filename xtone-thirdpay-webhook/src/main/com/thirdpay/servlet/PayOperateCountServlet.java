package com.thirdpay.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.alibaba.fastjson.util.UTF8Decoder;
import com.thirdpay.domain.ImeiBean;
import com.thirdpay.domain.PayOperateBean;
import com.thirdpay.utils.Canv;
import com.thirdpay.utils.CheckPayInfo;
import com.thirdpay.utils.EncodeUtils;

/**
 * 支付操作统计Servlet
 */
@WebServlet("/PayOperateCountServlet")
public class PayOperateCountServlet extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(PayOperateCountServlet.class);

	private static final Long Long = null;

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
			
			Long timeid= GenerateIdService.getInstance().generateNew(Integer.parseInt(ConfigManager.getConfigData("server.id").trim()), "clicks", 1);

			ThreadPool.mThreadPool.execute(new PayOperateBean(timeid ,Integer.parseInt(payOperateCode),
					Canv.parm.get(payOperateCode), appKey, op_notifyData, str, ownOrderid));

			
		String imei = getImei(op_notifyData);
		ThreadPool.mThreadPool.execute(new ImeiBean(imei, timeid, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));

		
		
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

	public String getImei(String op_notifyData) { 
		String IMEIforwardString = op_notifyData;
		String payUserIMEI = "";
		if (!"".equals(IMEIforwardString)) {
			LOG.info("IMEIforwardString --------- " + IMEIforwardString);
			JSONObject IMEIjson = JSON.parseObject(IMEIforwardString); // 解析自定义参数
			payUserIMEI = IMEIjson.getString("imei");// 支付用户IMEI //2016/09/20
			LOG.info("payUserIMEI --------- " + payUserIMEI);
		}
		return payUserIMEI;
	}
}
