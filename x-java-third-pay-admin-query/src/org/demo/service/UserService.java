package org.demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import org.apache.log4j.Logger;
import org.common.util.ConnectionService;
import org.demo.info.Apps;
import org.demo.info.Daily;
import org.demo.info.PayRsp;
import org.demo.info.Pays;
import org.demo.info.User;
import org.demo.utils.ConnectionServiceConfig;
import org.demo.utils.StringUtil;

public class UserService {
	
	static final private Logger LOG = Logger.getLogger(UserService.class);
	static final private String USER_TABLE_NAME = "tbl_thirdpay_query_users";
	static final private String APP_INFO_TABLE = " tbl_thirdpay_apps";

	private static Random rnd = new Random();

	private long generateUserId() {
		// perhaps need lock when site become hot
		long result = 0;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		// max loop try time = 5
		for (int i = 0; i < 5; i++) {
			long tmp = System.currentTimeMillis() * 100000L + rnd.nextInt(99999);
			try {
				con = ConnectionService.getInstance().getConnectionForLocal();
				ps = con.prepareStatement("select id from "+USER_TABLE_NAME+" where id = ?");
				int m = 1;
				ps.setLong(m++, tmp);
				rs = ps.executeQuery();
				if (!rs.next()) {
					result = tmp;
					break;
				}
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	public boolean checkAvailableUserName(String userName) {
		boolean result = false;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		// max loop try time = 5
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement("select id from "+USER_TABLE_NAME+" where username = ?");
			int m = 1;
			ps.setString(m++, userName);
			rs = ps.executeQuery();
			if (!rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public long addUser(String pwd, User user) {
		long result = 0;
		long tmpId = generateUserId();
		if (tmpId > 0) {
			PreparedStatement ps = null;
			Connection con = null;
			try {
				con = ConnectionService.getInstance().getConnectionForLocal();
				ps = con.prepareStatement(
						"insert into "+USER_TABLE_NAME+" (id,username,pwd,email,add_time) values (?,?,md5(?),?,?)");
				int m = 1;
				ps.setLong(m++, tmpId);
				ps.setString(m++, user.getUserName());
				ps.setString(m++, pwd);
				ps.setString(m++, user.getEmail());
				ps.setLong(m++, System.currentTimeMillis());
				if (ps.executeUpdate() == 1) {
					result = tmpId;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	public long addAnonymousUser() {
		long result = 0;
		long tmpId = generateUserId();
		if (tmpId > 0) {
			PreparedStatement ps = null;
			Connection con = null;
			try {
				con = ConnectionService.getInstance().getConnectionForLocal();
				ps = con.prepareStatement(
						"insert into "+USER_TABLE_NAME+" (id,username,pwd,email,add_time) values (?,?,md5(?),?,?)");
				int m = 1;
				ps.setLong(m++, tmpId);
				ps.setString(m++, "anonymous-" + tmpId);
				ps.setString(m++, Long.toString(tmpId));
				ps.setString(m++, "a");
				ps.setLong(m++, System.currentTimeMillis());
				if (ps.executeUpdate() == 1) {
					result = tmpId;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	// public long checkLoginUser(User user) {
	// long result = 0;
	// PreparedStatement ps = null;
	// Connection con = null;
	// ResultSet rs = null;
	// try {
	// con = ConnectionService.getInstance().getConnectionForLocal();
	// ps = con.prepareStatement("select id from "+USER_TABLE_NAME+" where
	// username=? and pwd=md5(?) and isAvail=1");
	// int m = 1;
	// ps.setString(m++, user.getUserName());
	// ps.setString(m++, user.getPassword());
	// rs = ps.executeQuery();
	// if (rs.next()) {
	// result = rs.getLong("id");
	// }
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } finally {
	// if (con != null) {
	// try {
	// con.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	// return result;
	// }
	public User checkLoginUser(User user) { // 通过username和password查询User(检查能否登录)
		User result = null;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ConnectionService.getInstance().getConnectionForLocal(); // md5(?)
			ps = con.prepareStatement(
					"select * from "+USER_TABLE_NAME+" where username=? and pwd=md5(?) and isAvail=1");
			int m = 1;
			ps.setString(m++, user.getUserName());
			ps.setString(m++, user.getPassword());
			rs = ps.executeQuery();
			if (rs.next()) {
				result = new User();
				result.setAdmin(rs.getInt("isadmin"));
				result.setCpid(rs.getLong("cpid"));
				result.setEmail(rs.getString("email"));
				result.setId(rs.getLong("id"));
				result.setPassword(rs.getString("pwd"));

				result.setUserName(rs.getString("username"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	public static List<Apps> selectByCpid(User user) { // 通过user中的cpid查询所有的Apps(为了获得Apps表中的appkey)
		ArrayList<Apps> list = new ArrayList<Apps>();
		Apps result = null;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement("select * from tbl_thirdpay_apps where cpid=?");
			int m = 1;
			ps.setLong(m, user.getCpid());

			rs = ps.executeQuery();
			while (rs.next()) {
				result = new Apps();
				result.setAppkey(rs.getString("appkey"));
				result.setAppname(rs.getString("appname"));
				result.setCpid(rs.getLong("cpid"));
				list.add(result);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public static List<Pays> selectByAppkey(Apps apps,PayRsp rsp) { // 通过Apps表中的appkey查询所有的Pays(用于前台显示)
		System.out.println("channel: "+rsp.getChannel());
		System.out.println("appkey: "+apps.getAppkey());
		ArrayList<Pays> list = new ArrayList<Pays>();
		Pays pays = null;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = ConnectionServiceConfig.getInstance().getConnectionForLocal();
			sql = "select FROM_UNIXTIME(id/1000/1000000, '%Y-%m-%d %H:%i:%s') AS id,price,paychannel,ip,'' as payinfo,releasechannel,'' as appkey,ownOrderID,paychannelorderid,cporderid,teststatus from log_success_pays where 1=1 ";
			sql+= " AND appkey=? ";
			if(!rsp.getChannel().equals("")){
				sql +=" AND releaseChannel LIKE ? ";
			}
			sql +=" ORDER BY id DESC LIMIT 500";
			System.out.println("sql :"+sql);
			ps = con.prepareStatement(sql);
			int m = 1;
			ps.setString(m, apps.getAppkey());
			if(!StringUtil.isNullOrEmpty(rsp.getChannel())){
				ps.setString(m+1, "%"+rsp.getChannel()+"%");
			}
			
			rs = ps.executeQuery();
			while (rs.next()) {

				pays = new Pays();

				pays.setId(rs.getString("id"));
				pays.setPrice(rs.getInt("price"));
				pays.setPayChannel(rs.getString("payChannel"));
				pays.setIp(rs.getString("ip"));
				pays.setPayInfo(rs.getString("payInfo"));
				pays.setReleaseChannel(rs.getString("releaseChannel"));
				pays.setAppKey(rs.getString("appKey"));
				pays.setOwnOrderId(rs.getString("ownOrderId"));
				pays.setPayChannelOrderId(rs.getString("payChannelOrderId"));
				pays.setCpOrderId(rs.getString("cpOrderId"));
				pays.setTestStatus(rs.getString("testStatus"));
				list.add(pays);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	/*
	 * 按日期查看统计数据 日期格式为日分秒
	 */
	public static List<Daily> getDailyByAppkeys2(User user) {
		Daily daily;
		List<Daily> listDaily = new ArrayList<Daily>();
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = null;
		try {
			// List<Apps> appList=new ArrayList<Apps>();
			// Apps a=new Apps();
			// a.setAppkey("1");
			// appList.add(a);
			// Apps b=new Apps();
			// b.setAppkey("2");
			// appList.add(b);
			// Apps c=new Apps();
			// c.setAppkey("3");
			// appList.add(c);
			List<Apps> appList = UserService.selectByCpid(user);
			String appkeys = appList.get(0).getAppkey();
			for (int i = 1; i < appList.size(); i++) {
				appkeys += "' or appkey='" + appList.get(i).getAppkey();
			}
			con = ConnectionServiceConfig.getInstance().getConnectionForLocal();
			sql = "select FROM_UNIXTIME(id/1000/1000000, '%Y-%m-%d') AS date,sum(price) as price,GROUP_CONCAT(DISTINCT appkey) AS appkey from log_success_pays where appkey='"
					+ appkeys + "' group by date ORDER BY date ASC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				daily = new Daily();
				daily.setId(rs.getString("date"));
				daily.setAppKey(rs.getString("appKey"));
				daily.setPrice(rs.getFloat("price"));
				listDaily.add(daily);
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
		return listDaily;
	}
	

	/*
	 * 按日期查看统计数据 日期格式为日分秒
	 */
	public static List<Daily> getDailyByAppkeys(User user) {
		Daily daily;
		List<Daily> listDaily = new ArrayList<Daily>();
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = null;
		try {
			// List<Apps> appList=new ArrayList<Apps>();
			// Apps a=new Apps();
			// a.setAppkey("1");
			// appList.add(a);
			// Apps b=new Apps();
			// b.setAppkey("2");
			// appList.add(b);
			// Apps c=new Apps();
			// c.setAppkey("3");
			// appList.add(c);
			List<Apps> appList = UserService.selectByCpid(user);
			String appkeys = appList.get(0).getAppkey();
			for (int i = 1; i < appList.size(); i++) {
				appkeys += "' or appkey='" + appList.get(i).getAppkey();
			}
			con = ConnectionServiceConfig.getInstance().getConnectionForLocal();
			sql = "select FROM_UNIXTIME(id/1000/1000000, '%Y-%m-%d') AS date,sum(price) as price,GROUP_CONCAT(DISTINCT appkey) AS appkey from log_success_pays where appkey='"
					+ appkeys + "' group by date ORDER BY date DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				daily = new Daily();
				daily.setId(rs.getString("date"));
				daily.setAppKey(rs.getString("appKey"));
				daily.setPrice(rs.getFloat("price"));
				listDaily.add(daily);
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
		return listDaily;
	}
	
	
	/*
	 * 按日期查看统计数据 日期格式为日分秒
	 */
	public static List<Daily> getDailyByAppkeys() {
		Daily daily;
		List<Daily> listDaily = new ArrayList<Daily>();
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = null;
		try {			
			
			con = ConnectionServiceConfig.getInstance().getConnectionForLocal();
            //sql = "select FROM_UNIXTIME(id/1000/1000000, '%Y-%m-%d') AS date,sum(price) as price,GROUP_CONCAT(DISTINCT appkey) AS appkey, GROUP_CONCAT(DISTINCT payChannel) AS payChannel from log_success_pays  group by date ORDER BY date DESC";
			sql = "select FROM_UNIXTIME(id/1000/1000000, '%Y-%m-%d') AS date,sum(price) as price,appkey,payChannel from log_success_pays  group by date,appkey,payChannel ORDER BY date DESC LIMIT 1000";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				daily = new Daily();
				daily.setId(rs.getString("date"));
				daily.setAppKey(rs.getString("appKey"));
				daily.setChannel(rs.getString("payChannel"));
				daily.setPrice(rs.getFloat("price"));
				listDaily.add(daily);
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
		return listDaily;
	}
	
	/*
	 * 按日期查看某appkey的统计 日期格式为日分秒
	 */
	public static List<Daily> getDailyByAppkey(String appKey) {
		Daily daily;
		List<Daily> listDaily = new ArrayList<Daily>();
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ConnectionServiceConfig.getInstance().getConnectionForLocal();
			String sql = "select FROM_UNIXTIME(id/1000/1000000, '%Y-%m-%d') AS date,sum(price) as price,GROUP_CONCAT(DISTINCT appkey) AS appkey from log_success_pays where appkey='"
					+ appKey + "' group by date ORDER BY date ASC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				daily = new Daily();
				daily.setId(rs.getString("date"));
				daily.setChannel(rs.getString("appkey"));
				daily.setPrice(rs.getFloat("price"));
				listDaily.add(daily);
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
		return listDaily;
	}

	/*
	 * 按日期查看某appkey的统计 日期格式为日分秒
	 */
	public static List<Daily> getChannelByAppkeys(String appKey) {
		Daily daily;
		List<Daily> listDaily = new ArrayList<Daily>();
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ConnectionServiceConfig.getInstance().getConnectionForLocal();
//			String sql = "select FROM_UNIXTIME(id/1000/1000000, '%Y-%m-%d') AS date,sum(price) as price,GROUP_CONCAT(DISTINCT payChannel) AS payChannel from log_success_pays where appKey='"
//					+ appKey + "' group by date ORDER BY date DESC";
			String sql = "select FROM_UNIXTIME(id/1000/1000000, '%Y-%m-%d') AS date,sum(price) as price,payChannel from log_success_pays where appKey=? group by date,payChannel ORDER BY date DESC LIMIT 1000";			
			ps = con.prepareStatement(sql);
			int m = 1;
			ps.setString(m++, appKey);
			rs = ps.executeQuery();
			while (rs.next()) {
				daily = new Daily();
				daily.setId(rs.getString("date"));
				daily.setChannel(rs.getString("payChannel"));
				daily.setPrice(rs.getFloat("price"));
				listDaily.add(daily);
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
		return listDaily;
	}

	public User getUserById(long userId) {
		User user = null;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement("select username,email from "+USER_TABLE_NAME+" where id=? ");
			int m = 1;
			ps.setLong(m++, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("username"));
				user.setId(userId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return user;
	}
	
	public static Apps getAppNameByAppkey(String appkey) {
		Apps apps = new Apps();
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();			
			ps = con.prepareStatement("select appName from "+APP_INFO_TABLE+" where appkey=? ");
			int m = 1;
			ps.setString(m++, appkey);
			rs = ps.executeQuery();
			if (rs.next()) {				
				apps.setAppname(rs.getString("appName"));
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return apps;
	}

	/**
	 * consume real-time through database
	 * 
	 * @return balance . if balance < 0 consume failure
	 */
	public float consume(Long userId, float consumeValue) {
		float result = 0;
		// User user = null;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement("select balance from "+USER_TABLE_NAME+" where id=? ");
			int m = 1;
			ps.setLong(m++, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				float balance = rs.getFloat("balance");
				if (balance >= consumeValue) {
					ps.execute("update balance = balance - ? from "+USER_TABLE_NAME+" where id = ?");
					// todo:lock and log
					result = balance - consumeValue;
				} else {
					result = -1f;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		UserService.getDailyByAppkeys(null);
	}
}
