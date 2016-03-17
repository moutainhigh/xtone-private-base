package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.wns.push.bean.StatisMsgQuery;
import com.wns.push.bean.push_history;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;
import com.wns.push.util.WnsDateUtil;
import com.wns2.factory.WnsRedisDbQueue;
import com.wns2.util.WnsUtil;

public class pushHistoryDao extends WnsBaseDao
{
    private static final String TBLPREFIX                  = "push_history";
    private static final String CACHE_KEY_ALL              = pushHistoryDao.class
                                                                   .getName()
                                                                   + "_all";

    private static final String CACHE_KEY_INSERT           = pushHistoryDao.class
                                                                   .getName()
                                                                   + "_redis_insert";

    private static final String CACHE_KEY_CLIENT_ID_ALL    = pushHistoryDao.class
                                                                   .getName()
                                                                   + "_clent_id_all";

    private static final String CACHE_KEY_DATE_PUSH_ID_ALL = pushHistoryDao.class
                                                                   .getName()
                                                                   + "_date_push_id";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 删除所有记录
     */
    public final boolean DeleteAll()
    {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "delete from " + table();
        PreparedStatementCreator psc = new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException
            {
                PreparedStatement ps = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                return ps;
            }
        };
        getJdbcTemplate().update(psc, keyHolder);

        return true;
    }

    /**
     * 增加一条数据
     */
    @SuppressWarnings("unchecked")
    public final Long Insert(final push_history model)
    {
        if (model == null)
        {
            return 0L;
        }
        String key = CACHE_KEY_CLIENT_ID_ALL + "_" + WnsDateUtil.startTime() + "_" + model.getClient_id();
        Object obj = WnsCacheFactory.get(key);
        List<push_history> list = null;
        if (obj != null && obj instanceof List)
        {
            list = (List<push_history>) obj;
            list.add(model);
            WnsCacheFactory.delete(key);
        }
        else
        {
            list = new ArrayList<push_history>();
            list.add(model);
        }

        WnsCacheFactory.add(key, list, WnsCacheFactory.ONE_DAY);
        Long ret = WnsRedisDbQueue.getInstance().push(CACHE_KEY_INSERT,
                model);
        if (ret != null)
        {
            return ret;
        }
        else
        {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            StringBuffer sb = new StringBuffer();
            sb.append("INSERT INTO ");
            sb.append(table());
            sb
                    .append(" (`IMEI`,`IMSI`,`client_id`,`cmd_id`, `push_id`,`area`, `PHONETYPE_NAME`,`PUSH_HISTORY_CREATEDATE`, `PUSH_POLICY_ID`, "
                            + "`push_record_ID`, `User_id`, `apk_type`, `kou_money`, `baike_money`, "
                            + "`RES_sp_money`, `sp_id`, `RES_ID`, `download_ok`, `history_type`,"
                            + "`download`, `mount`, `activate`, `time` ) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            final String sql = sb.toString();
            PreparedStatementCreator psc = new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection conn)
                        throws SQLException
                {
                    PreparedStatement ps = conn.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    int i = 1;
                    if (model.getIMEI() != null)
                    {
                        ps.setString(i++, model.getIMEI());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getIMSI() != null)
                    {
                        ps.setString(i++, model.getIMSI());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getClient_id() != null)
                    {
                        ps.setString(i++, model.getClient_id());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getCmd_id() != null)
                    {
                        ps.setString(i++, model.getCmd_id());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getPush_id() != null)
                    {
                        ps.setString(i++, model.getPush_id());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getArea() != null)
                    {
                        ps.setString(i++, model.getArea());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getPHONETYPE_NAME() != null)
                    {
                        ps.setString(i++, model.getPHONETYPE_NAME());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getPUSH_HISTORY_CREATEDATE() != null)
                    {
                        ps.setTimestamp(i++, WnsDateUtil
                                .JavaDateToTimestamp(model
                                        .getPUSH_HISTORY_CREATEDATE()));
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getPUSH_POLICY_ID() != null)
                    {
                        ps.setInt(i++, model.getPUSH_POLICY_ID());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getpush_record_ID() != null)
                    {
                        ps.setLong(i++, model.getpush_record_ID());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getUser_id() != null)
                    {
                        ps.setString(i++, model.getUser_id());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getapk_type() != null)
                    {
                        ps.setString(i++, model.getapk_type());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getkou_money() != null)
                    {
                        ps.setBigDecimal(i++, model.getkou_money());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getbaike_money() != null)
                    {
                        ps.setBigDecimal(i++, model.getbaike_money());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getRES_sp_money() != null)
                    {
                        ps.setBigDecimal(i++, model.getRES_sp_money());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getsp_id() != null)
                    {
                        ps.setInt(i++, model.getsp_id());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getRES_ID() != null)
                    {
                        ps.setInt(i++, model.getRES_ID());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getdownload_ok() != null)
                    {
                        ps.setBoolean(i++, model.getdownload_ok());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.gethistory_type() != null)
                    {
                        ps.setInt(i++, model.gethistory_type());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getdownload() != null)
                    {
                        ps.setInt(i++, model.getdownload());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getmount() != null)
                    {
                        ps.setInt(i++, model.getmount());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getactivate() != null)
                    {
                        ps.setInt(i++, model.getactivate());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (model.getTime() != null)
                    {
                        ps.setLong(i++, model.getTime());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    return ps;
                }
            };
            getJdbcTemplate().update(psc, keyHolder);
            int id = keyHolder.getKey().intValue();
            if (id > 0)
            {
                WnsCacheFactory.delete(CACHE_KEY_ALL);
                // NgsteamCacheFactory.delete(CACHE_KEY_CLIENT_ID_ALL
                // + model.getClient_id());
            }
            return keyHolder.getKey().longValue();
        }
    }

    /**
     * 获得前几行数据
     */
    @SuppressWarnings("unchecked")
    public final List<push_history> findTodayByClientId(String client_id)
    {
        Long time = WnsDateUtil.startTime();
        String key = CACHE_KEY_CLIENT_ID_ALL + "_" + time + "_" + client_id;
        
        Object obj = WnsCacheFactory.get(key);
        if (obj != null && obj instanceof List)
        {
            return (List<push_history>) obj;
        }
        else
        {
            List<push_history> list = query(
                    "select * from "
                            + table()
                            + " where client_id = ? and time = ?",
                    new Object[] { client_id, time }, new int[] { Types.VARCHAR, Types.BIGINT },
                    new WnsCommonRowMapper(push_history.class));
            if (list != null && list.size() > 0)
            {
                WnsCacheFactory.add(key,
                        list, WnsCacheFactory.ONE_DAY);
            }
            return list;
        }
    }

    /**
     * 获得前几行数据
     */
  
    public final int findCountByDate(String start, String end, Long pushid)
    {
        String key = CACHE_KEY_DATE_PUSH_ID_ALL + start + "_" + end + "_"
                + pushid;
        Object obj = WnsCacheFactory.get(key);
        if (obj != null && obj instanceof Integer)
        {
            return (Integer) obj;
        }
        else
        {
            Integer count = queryForInt(
                    "select count(*) from "
                            + table()
                            + " where PUSH_HISTORY_CREATEDATE > ? and PUSH_HISTORY_CREATEDATE < ? and push_id LIKE '%" + pushid + "%'",
                    new Object[] { start, end}, new int[] {
                            Types.VARCHAR, Types.VARCHAR});
            WnsCacheFactory.add(key, count, WnsCacheFactory.ONE_DAY);
            return count;
        }
    }

    @SuppressWarnings("unchecked")
    public List<StatisMsgQuery> statisMsg(Date start, Date end, int startrow,
            int rownum)
    {
        List<Object> paramList = new ArrayList<Object>();
        List<Integer> typeList = new ArrayList<Integer>();
        StringBuilder sb = new StringBuilder();
        sb
                .append("select DATE_FORMAT(PUSH_HISTORY_CREATEDATE , '%Y-%m-%d') date ");
        sb
                .append(",push_id as msgid, User_id as channel, count(PUSH_HISTORY_ID) count from  "
                        + table());
        sb.append(" where PUSH_HISTORY_CREATEDATE >= ? ");
        paramList.add(WnsDateUtil.NgsteamToString(start));
        typeList.add(Types.DATE);

        if (end != null)
        {
            sb.append(" and PUSH_HISTORY_CREATEDATE < ?");
            paramList.add(WnsDateUtil.NgsteamToString(end));
            typeList.add(Types.DATE);
        }
        sb.append(" group by date, msgid, channel");
        sb.append(" limit " + startrow + "," + rownum);
        List<StatisMsgQuery> list = query(sb.toString(), paramList.toArray(),
                WnsUtil.NgsteamIntegerToInt(typeList),
                new WnsCommonRowMapper(StatisMsgQuery.class));
        return list;
    }
}
