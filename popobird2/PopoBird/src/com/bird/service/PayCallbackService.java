package com.bird.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.common.util.ConfigManager;
import org.common.util.ConnectionService;
import org.common.util.GenerateIdService;

import com.alibaba.fastjson.JSON;
import com.bird.bean.PayParams;

/**
 * 
 * @author zgt
 *
 */
@WebServlet("/PayCallbackService")
public class PayCallbackService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PayCallbackService() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
		
        Map<String, String[]> maps = request.getParameterMap();
        java.util.Iterator<String> inter = maps.keySet().iterator();
        StringBuilder builder = new StringBuilder();
        while (inter.hasNext()) {
			String key =  inter.next();
			String[] value = maps.get(key);
			builder.append(key+"="+value[0]);
			response.getWriter().append(key+" = "+value[0]);
        }
        
        System.out.println(builder.toString());
        //
        
        String string = request.getParameter("payment");
        System.out.println(string);
        
        PayParams p = new PayParams(50, "productId", "productName", "productDesc");
        p.setWebOrderid("webOrderid");
        p.setUid("uid");
        
        insertTodata(p,string);
        
        response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	public void insertTodata(PayParams p,String param){
		//INSERT INTO `bird_callback` (`price`, `uid`, `webOrderid`, `productId`, `productName`, `productDesc`, `timeid`) VALUES ('dd', 'dd', 'dd', 'dd', 'dd', 'd', '111'); 
		  long timeid=GenerateIdService.getInstance().generateNew(Integer.parseInt(ConfigManager.getConfigData("server.id")), "clicks", 1);
	 
	      PreparedStatement ps = null;
	      Connection con = null;
	      try{
	    	//ConfigManager.getResourceAsStream("config.properties");
	    	ConnectionService service =  ConnectionService.getInstance();
	        con =service.getConnectionForLocal();
	        ps = con.prepareStatement("INSERT INTO `bird_callback` (`price`, `uid`, `webOrderid`, `productId`, `productName`, `productDesc`, `timeid`, `param`) VALUES (?, ?, ?, ?, ?, ?, ?,?)");
	        int m = 1;
	        
	        ps.setString(m++, p.getPrice()+"");
	        ps.setString(m++, p.getUid());
	        ps.setString(m++, p.getWebOrderid());
	        ps.setString(m++, p.getProductId());
	        ps.setString(m++, p.getProductName());
	        ps.setString(m++, p.getProductDesc());
	        ps.setLong(m++, timeid);
	        ps.setString(m++, param);
	        ps.executeUpdate();
	      }catch(Exception e){
	        e.printStackTrace();
	      }finally{
	        if(con != null){
	          try{
	            con.close();
	          }catch(SQLException e){
	            e.printStackTrace();
	          }
	        }
	      }
	    }
}
