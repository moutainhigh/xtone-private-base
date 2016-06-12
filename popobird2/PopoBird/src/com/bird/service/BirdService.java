package com.bird.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.common.util.ConnectionService;

import com.bird.utils.FileUtils;

/**
 * Servlet implementation class BirdService
 */
@WebServlet("/BirdService")
public class BirdService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * 
     * 外网http://popobird.n8wan.com:29141/
     * 
     * @see HttpServlet#HttpServlet()
     */
    public BirdService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
		
        String uid = request.getParameter("uid");
        String cherryNum = request.getParameter("cherryNum");
       // String value = FileUtils.updateCherryNum(uid, cherryNum);
        
//        System.out.println("uid : "+uid);
//        System.out.println("cherryNum : "+cherryNum);
//        System.out.println("value : "+value);
        //response.getWriter().append(value); 
        //INSERT INTO `popobird_demo`.`user_cherry` (`uid`, `cherryNum`) VALUES ('tt', '11'); 
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        
        
        String re = updateUsercherry(uid, cherryNum);
        System.out.println(":"+re);
        
        response.getWriter().append(re); 
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
