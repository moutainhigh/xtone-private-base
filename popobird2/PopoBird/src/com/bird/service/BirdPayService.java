package com.bird.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;
import org.common.util.ConnectionService;

import com.bird.bean.Message;
import com.bird.utils.StringUtil;
import com.google.gson.Gson;

/**
 * 用于处理U8的处理结果
 * @author Administrator
 *
 */
@WebServlet("/BirdPayService")
public class BirdPayService extends HttpServlet{
	static private final Logger LOG = Logger.getLogger(BirdPayService.class);
	/**
     * 
     * 外网http://popobird.n8wan.com:29141/BirdPayService
     * 
     * @see HttpServlet#HttpServlet()
     */
    public BirdPayService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @throws Exception 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		doPost(request, response);
        
	}
	
	
	private String convertStreamToString(InputStream is) {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	      while ((line = reader.readLine()) != null) {
	        sb.append(line + "\n");
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        is.close();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	    return sb.toString();
	  }
	
	private String MD5(String stringSignTemp) {
		 MessageDigest md5 = null;  
	        try{  
	            md5 = MessageDigest.getInstance("MD5");  
	        }catch (Exception e){  
	            System.out.println(e.toString());  
	            e.printStackTrace();  
	            return "";  
	        }  
	        char[] charArray = stringSignTemp.toCharArray();  
	        byte[] byteArray = new byte[charArray.length];  
	  
	        for (int i = 0; i < charArray.length; i++)  
	            byteArray[i] = (byte) charArray[i];  
	        byte[] md5Bytes = md5.digest(byteArray);  
	        StringBuffer hexValue = new StringBuffer();  
	        for (int i = 0; i < md5Bytes.length; i++){  
	            int val = ((int) md5Bytes[i]) & 0xff;  
	            if (val < 16)  
	                hexValue.append("0");  
	            hexValue.append(Integer.toHexString(val));  
	        }  
	        return hexValue.toString();
	}
	
	/**
	 * 更新用户的樱桃
	 */
	private String  updateUsercherry(String uid,String cherryNum) {
		
			PreparedStatement ps = null;
			Connection con = null;
			try {
				ConnectionService service = ConnectionService.getInstance();
				con = service.getConnectionForLocal();
				
				Statement st = con.createStatement();
				
				String sql = "SELECT uid,cherryNum FROM `user_cherry` WHERE uid = '"+uid+"'";
				ResultSet rs = st.executeQuery(sql);
				if(rs!=null){
					rs.last();
					int rowCount = rs.getRow();
					
					if(rowCount>0){
						if(cherryNum==null||"".equals(cherryNum)){
						   return rs.getInt("cherryNum")+"";
						}
					}
					
					rs.beforeFirst();
					if(cherryNum!=null&&!"".equals(cherryNum)){
						int num = Integer.valueOf(cherryNum);
						if(rowCount>0){
							String uSql = "UPDATE  `user_cherry` SET `cherryNum` = "+num+" WHERE `uid` = '"+uid+"'";
							int up = st.executeUpdate(uSql);
							System.out.println("更新:"+up);
							return cherryNum;
						}else {
							String iSql = "INSERT INTO `user_cherry` (`uid`, `cherryNum`) VALUES ('"+uid+"', "+num+")";
							int inser = st.executeUpdate(iSql);
							System.out.println("插入:"+inser);
							return cherryNum;
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
	return "-2";
		}
	

	/**
	 * @throws IOException 
	 * @throws Exception 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
		
     // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }

        // 将资料解码
        String reqBody = sb.toString();
        //System.out.println("reqBody: "+reqBody);
        LOG.info("reqBody: "+reqBody);
        Gson gson = new Gson();
        Message msg = gson.fromJson(reqBody, Message.class);
        if(msg.getState()==1){
        	//游戏服务器进行sign验证
        	//更新用户樱桃
        	//int cherryNum = msg.getData().getMoney()*100;
        	//String re = updateUsercherry(msg.getData().getUserID(), cherryNum+"");
        	//游戏服处理成功，直接返回一个"SUCCESS"字符串到U8Server
        	response.getWriter().append("SUCCESS");
        	LOG.info("SUCCESS! ");
        }else {
        	//游戏服处理失败，返回一个"FAIL"字符串到U8Server
        	response.getWriter().append("FAIL");
        	LOG.info("ERRO! ");
		}
        
	}
}
