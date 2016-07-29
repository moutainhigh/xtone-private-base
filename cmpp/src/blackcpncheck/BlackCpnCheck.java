package blackcpncheck;
import java.sql.*;

import org.apache.log4j.Logger;

import com.xiangtone.sql.ConnConfigMain;
public class BlackCpnCheck {
	private final Logger logger = Logger.getLogger("BlackCpnCheck.class");
	public BlackCpnCheck(){
	}
	public boolean checkCpn(String cpn){
		boolean checkFlag = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select cpn from blackcpns where cpn='" + cpn + "'";
		try
		{
			conn = ConnConfigMain.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			logger.debug("finish query sql [ " + sql + " ]");
			if(rs.next()){
				checkFlag = true;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.error("query sql [" + sql + "] error :" + ex.getMessage());
		}
		finally
		{
			try{ if(rs!=null)rs.close(); }catch(Exception ex){}
			try{ if(stmt!=null)stmt.close(); }catch(Exception ex){}
			try{ if(conn!=null)conn.close(); }catch(Exception ex){}
		}
		return checkFlag;
	}
}
