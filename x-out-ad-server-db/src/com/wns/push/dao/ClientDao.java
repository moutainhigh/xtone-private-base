package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.jdbc.Statement;
import com.wns.push.bean.Client;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;

public class ClientDao extends WnsBaseDao {
    public static final String CACHE_KEY_INSERT       = ClientDao.class
            .getName()
            + "_redis_insert";
    
	private static final String TBLPREFIX = "client";
	private static final String CACHE_KEY_ALL = ClientDao.class.getName()
			+ "_all";
	private static final String CACHE_KEY_CLIENTID_DB = ClientDao.class.getName()
			+ "_clientid_db";
	private static final String CACHE_KEY_CLIENTID = ClientDao.class.getName()
			+ "_clientid";
    
	public static String table() {
		return TBLPREFIX;
	}

	/**
	 * 插入
	 */
	public Long insert(final Client item) {
		if (item == null) {
			return 0L;
		}

		KeyHolder idKey = new GeneratedKeyHolder();
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ");
		sb.append(table());
            sb
                    .append(" (`channel`,`appid`,`client_id`, `area`, `imei`, `imsi`,"
                            + " `wifi`, `model`, `phone_num`, `desity`, `width`, "
                            + "`height`, `scaled_density`, `xdpi`, `ydpi`, `ramsize`,"
                            + "`leftramsize`, `romsize`, `leftromsize`,`sd1size`,`leftsd1size`,"
                            + "`sd2size`, `leftsd2size`, `age`, `sex`, `createdate`,"
                            + "`updatedate`)" + " VALUES (?, ?, ?, ?, ?, ?, "
                            + "?, ?, ?, ?, ?," + "?, ?, ?, ?, ?,"
                            + "?, ?, ?, ?, ?,"
                            + "?, ?, ?, ?, CURRENT_TIMESTAMP,"
                            + "CURRENT_TIMESTAMP)");
		final String sql = sb.toString();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				int i = 1;

				if (item.getChannel() != null) {
					ps.setString(i++, item.getChannel());
				} else {
					ps.setNull(i++, Types.NULL);
				}
                    if (item.getAppid() != null)
                    {
                        ps.setString(i++, item.getAppid());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
				if (item.getClient_id() != null) {
					ps.setString(i++, item.getClient_id());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getArea() != null) {
					ps.setString(i++, item.getArea());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getImei() != null) {
					ps.setString(i++, item.getImei());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getImsi() != null) {
					ps.setString(i++, item.getImsi());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getWifi() != null) {
					ps.setString(i++, item.getWifi());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getModel() != null) {
					ps.setString(i++, item.getModel());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getPhone_num() != null) {
					ps.setString(i++, item.getPhone_num());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getDesity() != null) {
					ps.setString(i++, item.getDesity());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getWidth() != null) {
					ps.setString(i++, item.getWidth());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getHeight() != null) {
					ps.setString(i++, item.getHeight());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getScaled_density() != null) {
					ps.setString(i++, item.getScaled_density());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getXdpi() != null) {
					ps.setString(i++, item.getXdpi());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getYdpi() != null) {
					ps.setString(i++, item.getYdpi());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getRamsize() != null) {
					ps.setString(i++, item.getRamsize());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getLeftramsize() != null) {
					ps.setString(i++, item.getLeftramsize());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getRomsize() != null) {
					ps.setString(i++, item.getRomsize());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getLeftromsize() != null) {
					ps.setString(i++, item.getLeftromsize());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getSd1size() != null) {
					ps.setString(i++, item.getSd1size());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getLeftsd1size() != null) {
					ps.setString(i++, item.getLeftsd1size());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getSd2size() != null) {
					ps.setString(i++, item.getSd2size());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getLeftsd2size() != null) {
					ps.setString(i++, item.getLeftsd2size());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getAge() != null) {
					ps.setString(i++, item.getAge());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getSex() != null) {
					ps.setString(i++, item.getSex());
				} else {
					ps.setNull(i++, Types.NULL);
				}

				return ps;
			}
		};
		long id = getJdbcTemplate().update(psc, idKey);
		if (id > 0) {
			item.setId(idKey.getKey().longValue());
			
			WnsCacheFactory.delete(CACHE_KEY_ALL);
			
			String key = CACHE_KEY_CLIENTID + item.getClient_id();
			WnsCacheFactory.delete(key);
			WnsCacheFactory.add(key, item, WnsCacheFactory.ONE_WEEK);
			
			String dbkey = CACHE_KEY_CLIENTID_DB + item.getClient_id();
			WnsCacheFactory.delete(dbkey);
			WnsCacheFactory.add(dbkey, item, WnsCacheFactory.ONE_WEEK);
		}
		return idKey.getKey().longValue();
	}

//    @SuppressWarnings("unchecked")
    public Client findSingleByClient(String clientId)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_CLIENTID_DB + clientId);
        if (obj != null && obj instanceof Client)
        {
            return (Client) obj;
        }
        return null;

//        else
//        {
//            List<Client> list = query("select * from " + table()
//                    + " where client_id=?  LIMIT 1", new Object[] { clientId },
//                    new int[] { Types.VARCHAR }, new WnsCommonRowMapper(
//                            Client.class));
//            if (list != null && list.size() > 0)
//            {
//                Client item = list.get(0);
//                if (item != null)
//                {
//                    WnsCacheFactory.add(CACHE_KEY_CLIENTID_DB + clientId,
//                            item, WnsCacheFactory.ONE_MONTH);
//                }
//                return item;
//            }
//            return null;
//        }
    }
}
