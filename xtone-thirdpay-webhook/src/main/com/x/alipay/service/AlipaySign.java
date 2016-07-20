package com.x.alipay.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.x.alipay.utils.AlipayUtils;

/**
 * Servlet implementation class AlipaySign
 */
@WebServlet("/AlipaySign")
public class AlipaySign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlipaySign() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
		
        String subject = request.getParameter("subject");
		String body = request.getParameter("body");
		String price = request.getParameter("price");
		
		String xx_notifyData = request.getParameter("xx_notifyData");
		
		AlipayUtils alipay = new AlipayUtils();
		String sign = alipay.pay(subject, body, price,xx_notifyData);
		
		response.getWriter().append(sign);
		
		System.out.println(sign);
        
	}

}
