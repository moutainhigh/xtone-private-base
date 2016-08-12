package com.bird.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginBirdService_youka")
public class BirdLoginService_youka extends HttpServlet{
    
    /**
     * 
     * @see HttpServlet#HttpServlet()
     */
    public BirdLoginService_youka() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws UnsupportedEncodingException 
	 * @throws Exception 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException  {
		response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
		
        String userID = request.getParameter("userID");
        String token = request.getParameter("token");
        String md5 = request.getParameter("sign");
        //String inputStr = "userID="+userID+"token="+token+"287becd50f924e39aeb4fb288ccb4f7f";
        //System.out.println("inputStr: "+inputStr);
		try {
			//String md5 = MD5(inputStr).toUpperCase();
			System.out.println("md5: "+md5);
	        String url = "http://nine.youkala.com/user/verifyAccount?"+"userID="+userID+"&token="+token+"&sign="+md5;
	        System.out.println("login_url: "+url);
	        com.bird.utils.HttpsRequest httpsRequest = new com.bird.utils.HttpsRequest();
	        String msg = httpsRequest.sendGet(url);
	        System.out.println("msg: "+msg);
	        //Gson gson = new Gson();
	        //LoginMessage usermsg = gson.fromJson(msg, LoginMessage.class);
	        /*if(usermsg.getState()!=1){
	        	 response.getWriter().append("erro!");
	        }else{
	        	 response.getWriter().append(gson.toJson(usermsg.getData()));
	        }*/
	        response.getWriter().append(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
	 * @throws UnsupportedEncodingException 
	 * @throws Exception 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException  {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

