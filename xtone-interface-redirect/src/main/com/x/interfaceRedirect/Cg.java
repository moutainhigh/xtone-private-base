package com.x.interfaceRedirect;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.common.util.ThreadPool;

/**
 * Servlet implementation class Umeng
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Cg" })
public class Cg extends HttpServlet {

  private static final Logger LOG = Logger.getLogger(Cg.class);

  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Cg() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      doRedirct(request, response);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      doRedirct(request, response);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void doRedirct(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String targetUrl = "http://vanggame.com";
    if (request.getHeader("user-agent") != null
        && (request.getHeader("user-agent").matches("(.*)iPhone(.*)") || request.getHeader("user-agent").matches(
            "(.*)iPod(.*)"))) {
      targetUrl = CacheConfig.getInstance().getNameLoadingCache("apple");
    }else{
      targetUrl = CacheConfig.getInstance().getNameLoadingCache("android");
    }
    ThreadPool.mThreadPool.execute(new LogInsert(request.getParameter("f"), request.getHeader("user-agent"), targetUrl, request.getHeader("X-Real-IP")!=null?request.getHeader("X-Real-IP"):request.getRemoteAddr()));
    response.sendRedirect(targetUrl);
  }

  @SuppressWarnings("rawtypes")
  private void printHeader(HttpServletRequest request) {
    Enumeration names = request.getHeaderNames();
    StringBuilder sb = new StringBuilder("headerInfo---");
    while (names.hasMoreElements()) {
      String name = names.nextElement().toString();
      Enumeration headers = request.getHeaders(name);
      sb.append(name).append(":");
      while (headers.hasMoreElements()) {
        sb.append(headers.nextElement()).append(" ");
      }
      sb.append("\n");
    }
    LOG.debug(sb.toString());
  }

}
