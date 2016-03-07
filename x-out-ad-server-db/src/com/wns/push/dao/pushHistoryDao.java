package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.wns.push.bean.push_history;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsDateUtil;

public class pushHistoryDao extends WnsBaseDao {
    public static final String CACHE_KEY_INSERT           = pushHistoryDao.class
            .getName()
            + "_redis_insert";
    
	private static final String TBLPREFIX = "push_history";
	private static final String CACHE_KEY_ALL = pushHistoryDao.class.getName()
			+ "_all";

	public static String table() {
		return TBLPREFIX;
	}

	/**
	 * 增加一条数据
	 */
	public final Long Insert(final push_history model) {
		if (model == null) {
			return 0L;
		}
		KeyHolder keyHolder = new GeneratedKeyHolder();
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ");
		sb.append(table());
		sb.append(" (`IMEI`,`IMSI`,`client_id`,`cmd_id`, `push_id`,`area`, `PHONETYPE_NAME`,`PUSH_HISTORY_CREATEDATE`, `PUSH_POLICY_ID`, "
				+ "`push_record_ID`, `User_id`, `apk_type`, `kou_money`, `baike_money`, "
				+ "`RES_sp_money`, `sp_id`, `RES_ID`, `download_ok`, `history_type`,"
				+ "`download`, `mount`, `activate`, `time` ) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		final String sql = sb.toString();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				if (model.getIMEI() != null) {
					ps.setString(i++, model.getIMEI());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getIMSI() != null) {
					ps.setString(i++, model.getIMSI());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getClient_id() != null) {
					ps.setString(i++, model.getClient_id());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getCmd_id() != null) {
					ps.setString(i++, model.getCmd_id());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getPush_id() != null) {
					ps.setString(i++, model.getPush_id());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getArea() != null) {
					ps.setString(i++, model.getArea());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getPHONETYPE_NAME() != null) {
					ps.setString(i++, model.getPHONETYPE_NAME());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getPUSH_HISTORY_CREATEDATE() != null) {
					ps.setTimestamp(i++, WnsDateUtil.JavaDateToTimestamp(model
							.getPUSH_HISTORY_CREATEDATE()));
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getPUSH_POLICY_ID() != null) {
					ps.setInt(i++, model.getPUSH_POLICY_ID());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getpush_record_ID() != null) {
					ps.setLong(i++, model.getpush_record_ID());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getUser_id() != null) {
					ps.setString(i++, model.getUser_id());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getapk_type() != null) {
					ps.setString(i++, model.getapk_type());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getkou_money() != null) {
					ps.setBigDecimal(i++, model.getkou_money());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getbaike_money() != null) {
					ps.setBigDecimal(i++, model.getbaike_money());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getRES_sp_money() != null) {
					ps.setBigDecimal(i++, model.getRES_sp_money());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getsp_id() != null) {
					ps.setInt(i++, model.getsp_id());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getRES_ID() != null) {
					ps.setInt(i++, model.getRES_ID());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getdownload_ok() != null) {
					ps.setBoolean(i++, model.getdownload_ok());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.gethistory_type() != null) {
					ps.setInt(i++, model.gethistory_type());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getdownload() != null) {
					ps.setInt(i++, model.getdownload());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getmount() != null) {
					ps.setInt(i++, model.getmount());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getactivate() != null) {
					ps.setInt(i++, model.getactivate());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				if (model.getTime() != null) {
					ps.setLong(i++, model.getTime());
				} else {
					ps.setNull(i++, Types.NULL);
				}
				return ps;
			}
		};
		getJdbcTemplate().update(psc, keyHolder);
		int id = keyHolder.getKey().intValue();
		if (id > 0) {
			WnsCacheFactory.delete(CACHE_KEY_ALL);
		}

		return keyHolder.getKey().longValue();
	}
}
