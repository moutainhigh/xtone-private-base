package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.mysql.jdbc.Statement;
import com.wns.push.bean.WnsChannelOrg;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;

public class WnsChannelOrgDao extends WnsBaseDao {
	private static final String TBLPREFIX = "o_channelorg";
	private static final String CACHE_KEY_ALL = WnsChannelOrgDao.class
			.getName() + "_all";
	private static final String CACHE_KEY_ALL_BY_ADMIN = WnsChannelOrgDao.class
			.getName() + "_all_by_admin";
	private static final String CACHE_KEY_ID = WnsChannelOrgDao.class.getName()
			+ "_id";
	private static final String CACHE_KEY_ID_BY_ADMIN = WnsChannelOrgDao.class
			.getName() + "_id_by_admin";
	private static final String CACHE_KEY_ID_BY_CHANNEL = WnsChannelOrgDao.class
			.getName() + "_id_by_channel";
	private static final String CACHE_KEY_ID_BY_ORGID = WnsChannelOrgDao.class
			.getName() + "_id_by_orgid";

	public static String table() {
		return TBLPREFIX;
	}

	/**
	 * 插入
	 */
	public int insert(final WnsChannelOrg item) {
		if (item == null) {
			return 0;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ");
		sb.append(table());
		sb.append(" (`channel`,`status`,`channelname`,`orgid`,`key`,`createdate`, `updatedate`)"
				+ " VALUES (?,?,?,?,?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");
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
				if (item.getStatus() != null) {
					ps.setInt(i++, item.getStatus());
				} else {
					ps.setInt(i++, 0);
				}

				if (item.getChannelname() != null) {
					ps.setString(i++, item.getChannelname());
				} else {
					ps.setInt(i++, 0);
				}
				if (item.getOrgid() != null) {
					ps.setLong(i++, item.getOrgid());
				} else {
					ps.setInt(i++, 0);
				}
				if (item.getKey() != null) {
					ps.setString(i++, item.getKey());
				} else {
					ps.setInt(i++, 0);
				}

				return ps;
			}
		};
		int id = getJdbcTemplate().update(psc);
		if (id > 0) {
			WnsCacheFactory.delete(CACHE_KEY_ALL);
			WnsCacheFactory.delete(CACHE_KEY_ALL_BY_ADMIN);
			WnsCacheFactory.delete(CACHE_KEY_ID_BY_CHANNEL + item.getChannel());
		}
		return id;
	}

	/**
	 * 更新
	 */
	public void update(final WnsChannelOrg item) {
		if (item == null) {
			return;
		}

		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE ");
		sb.append(table());
		sb.append(" SET `channel`=?, `status`=?,`channelname`=?,`key`=?,`orgid`=?, `updatedate`= CURRENT_TIMESTAMP WHERE `id`=?");
		PreparedStatementSetter psc = new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
				if (item.getChannel() != null) {
					ps.setString(i++, item.getChannel());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getStatus() != null) {
					ps.setInt(i++, item.getStatus());
				} else {
					ps.setInt(i++, 0);
				}

				if (item.getChannelname() != null) {
					ps.setString(i++, item.getChannelname());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getKey() != null) {
					ps.setString(i++, item.getKey());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (item.getOrgid() != null) {
					ps.setLong(i++, item.getOrgid());
				} else {
					ps.setNull(i++, Types.NULL);
				}

				ps.setLong(i++, item.getId());
			}
		};
		int id = getJdbcTemplate().update(sb.toString(), psc);
		if (id > 0) {
			WnsCacheFactory.delete(CACHE_KEY_ALL);
			WnsCacheFactory.delete(CACHE_KEY_ALL_BY_ADMIN);
			WnsCacheFactory.delete(CACHE_KEY_ID + id);
			WnsCacheFactory.delete(CACHE_KEY_ID_BY_ADMIN + id);
			WnsCacheFactory.delete(CACHE_KEY_ID_BY_CHANNEL + item.getChannel());
		}
	}

	@SuppressWarnings("unchecked")
	public WnsChannelOrg findSingle(long id) {
		Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
		if (obj != null && obj instanceof WnsChannelOrg) {
			return (WnsChannelOrg) obj;
		} else {
			List<WnsChannelOrg> list = query("select * from " + table()
					+ " where id=? LIMIT 1", new Object[] { id },
					new int[] { Types.INTEGER }, new WnsCommonRowMapper(
							WnsChannelOrg.class));
			if (list != null && list.size() > 0) {
				WnsChannelOrg item = list.get(0);
				if (item != null) {
					WnsCacheFactory.add(CACHE_KEY_ID + id, item,
							WnsCacheFactory.ONE_MONTH);
				}
				return item;
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<WnsChannelOrg> findAll() {
		Object obj = WnsCacheFactory.get(CACHE_KEY_ALL);
		if (obj != null && obj instanceof List) {
			System.out.println("-------WnsChannelOrgDao.cache----------");
			return (List<WnsChannelOrg>) obj;
		} else {
		
			List<WnsChannelOrg> list = query("select * from " + table()
					+ " where status = 0 order by id desc", null, null,
					new WnsCommonRowMapper(WnsChannelOrg.class));
			System.out.println("-------WnsChannelOrgDao.list----------"+list.size());
			if (list != null && list.size() > 0) {
				WnsCacheFactory.add(CACHE_KEY_ALL, list,
						WnsCacheFactory.ONE_MONTH);
			}
			return list;
		}
	}

	@SuppressWarnings("unchecked")
	public WnsChannelOrg findSingleByAdmin(long id) {
		Object obj = WnsCacheFactory.get(CACHE_KEY_ID_BY_ADMIN + id);
		if (obj != null && obj instanceof WnsChannelOrg) {
			return (WnsChannelOrg) obj;
		} else {
			List<WnsChannelOrg> list = query("select * from " + table()
					+ " where id=? LIMIT 1", new Object[] { id },
					new int[] { Types.BIGINT }, new WnsCommonRowMapper(
							WnsChannelOrg.class));
			if (list != null && list.size() > 0) {
				WnsChannelOrg item = list.get(0);
				if (item != null) {
					WnsCacheFactory.add(CACHE_KEY_ID_BY_ADMIN + id, item,
							WnsCacheFactory.ONE_MONTH);
				}
				return item;
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public WnsChannelOrg findSingleByChannel(String channel) {
		Object obj = WnsCacheFactory.get(CACHE_KEY_ID_BY_CHANNEL + channel);
		if (obj != null && obj instanceof WnsChannelOrg) {
			WnsChannelOrg item = (WnsChannelOrg) obj;
			if (item != null && item.getChannel() != null) {
				return item;
			} else {
				return null;
			}
		} else {
			WnsChannelOrg item, ret;
			List<WnsChannelOrg> list = query("select * from " + table()
					+ " where channel=? and status = 0 LIMIT 1",
					new Object[] { channel }, new int[] { Types.VARCHAR },
					new WnsCommonRowMapper(WnsChannelOrg.class));
			if (list != null && list.size() > 0) {
				item = list.get(0);
				ret = item;
			} else {
				item = new WnsChannelOrg();
				item.setChannel(null);
				ret = null;
			}
			WnsCacheFactory.add(CACHE_KEY_ID_BY_CHANNEL + channel, item,
					WnsCacheFactory.ONE_MONTH);

			return ret;
		}
	}

	@SuppressWarnings("unchecked")
	public List<WnsChannelOrg> findAllByAdmin() {
		Object obj = WnsCacheFactory.get(CACHE_KEY_ALL_BY_ADMIN);
		if (obj != null && obj instanceof List) {
			return (List<WnsChannelOrg>) obj;
		} else {
			List<WnsChannelOrg> list = query("select * from " + table() + "",
					null, null, new WnsCommonRowMapper(WnsChannelOrg.class));
			if (list != null && list.size() > 0) {
				WnsCacheFactory.add(CACHE_KEY_ALL_BY_ADMIN, list,
						WnsCacheFactory.ONE_MONTH);
			}
			return list;
		}
	}

	@SuppressWarnings("unchecked")
	public List<WnsChannelOrg> findByOrgid(Long orgId) {
		String key = CACHE_KEY_ID_BY_ORGID + "_" + orgId;
		Object obj = WnsCacheFactory.get(key);
		if (obj != null && obj instanceof List) {
			return (List<WnsChannelOrg>) obj;
		} else {
			StringBuffer sb = new StringBuffer("select * from ");
			sb.append(table()).append("  where `status` = 0  and orgid=")
					.append(orgId);
			logger.info(sb.toString());
			List<WnsChannelOrg> list = query(sb.toString(), null, null,
					new WnsCommonRowMapper(WnsChannelOrg.class));
			if (list != null && list.size() > 0) {
				WnsCacheFactory.add(key, list, WnsCacheFactory.ONE_MONTH);
			}
			return list;
		}
	}

}