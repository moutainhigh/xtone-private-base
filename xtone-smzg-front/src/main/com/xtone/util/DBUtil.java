package com.xtone.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getConnection() throws Exception{
		Connection conn = null;
		try {
			Class.forName(
					"com.mysql.jdbc.Driver");
			conn = DriverManager
			.getConnection(
			"jdbc:mysql://192.168.1.221:3306" +
			"/iap_leo_log?useUnicode=true&characterEncoding=utf8",
			"hongjiabin","");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return conn;
	}
	public static void close(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Connection conn = getConnection();
		System.out.println("conm"+conn);
	}

}
