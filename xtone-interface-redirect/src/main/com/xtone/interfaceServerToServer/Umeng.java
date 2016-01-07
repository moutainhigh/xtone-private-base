package com.xtone.interfaceServerToServer;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class Umeng
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Umeng" })
public class Umeng extends HttpServlet {

  private static final Logger LOG = Logger.getLogger(Umeng.class);

  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Umeng() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String info = null;
    int len = 0;
    int temp = 0;
    InputStream is = request.getInputStream();
    byte[] b = new byte[1000000];
    while ((temp = is.read()) != -1) {
      b[len] = (byte) temp;
      len++;
    }
    is.close();
    info = new String(b, 0, len, "utf-8");
    if (info != null & info.length() > 0) {
      LOG.info("#### Umeng s2s receive post:\n" + info);
      LOG.info("####end:");
    }
  }

}
