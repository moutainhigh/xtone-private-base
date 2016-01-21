package com.x.interfaceRedirect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

public class DaoConfig {
  
  private final static Logger LOG = Logger.getLogger(DaoConfig.class);

  public String getTargetUrlByTitle(String title) {
    String result = "";
    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    try {
      con = ConnectionServiceConfig.getInstance().getConnectionForLocal();
      String sql = "SELECT targetUrl FROM tbl_zuoyao_redirect_targets where title=?;";
      ps = con.prepareStatement(sql);
      int m = 1;
      ps.setString(m++, title);
      rs = ps.executeQuery();
      while (rs.next()) {
        result = rs.getString("targetUrl");
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      if (con != null) {
        try {
          con.close();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    return result;
  }
  
}
