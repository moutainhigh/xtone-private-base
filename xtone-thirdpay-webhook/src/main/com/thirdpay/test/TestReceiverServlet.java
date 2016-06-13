package com.thirdpay.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestReceiverServlet
 */
@WebServlet("/TestReceiverServlet")
public class TestReceiverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestReceiverServlet() {
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
//		String payment = request.getParameter("payment");
//		if(!payment.equals("")){
//			System.out.println("recivier-payment = " + payment);
//			response.getWriter().append("200");
//		}
		
		response.getWriter().append("200");
//		String payment = request.getParameter("appkey");
//		System.out.println("appkey = " + payment);
//		response.setCharacterEncoding("gbk");
//		response.setContentType("text/html;charset=gbk");
//		
//		response.getWriter().append("小心我锋哥打你 ");
		
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
