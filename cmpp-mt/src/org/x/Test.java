package org.x;

import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.common.util.ConnectionService;

import comsd.commerceware.cmpp.CMPP;
import comsd.commerceware.cmpp.OutOfBoundsException;

public class Test {
	// private static Logger logger = Logger.getLogger(.class);
	private static Logger logger = Logger.getLogger(Test.class);

	public static void main(String[] args) {
		String strSql = null;
		String strSql1 = null;
		String strSql2 = null;
		Connection con = null;
		PreparedStatement ps = null;
		int rs;
		try {
			strSql = "update tbl_base_users set name ='x1' where id=5";
			strSql1 = "update tbl_base_users set name ='y1' where id=6";
			strSql2 = "update tbl_base_users set name ='z1' where id=7";
			logger.debug(strSql);
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps=con.prepareStatement(strSql);
			rs=ps.executeUpdate();
			rs=ps.executeUpdate(strSql1);
			rs=ps.executeUpdate(strSql2);
			System.out.println(rs);
		} catch (Exception e) {
			logger.error(strSql, e);
		}finally {
			try {
				con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}		
		System.out.println(ps);
		byte[] b = { 1, 2 };
		System.out.println(Arrays.toString(b));

		try {
			
//			Socket socket = new Socket("12", 21);
//			logger.debug(socket);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		logger.error("e");
		logger.info("i");
		logger.debug("d");
		logger.warn("w");
		// logger.error("e");
		// logger.error("e");
		// logger.error("e");
	}
}
