package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.jdbc.Statement;
import com.wns.push.bean.SmsCenter;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;

public class SmsCenterDao extends WnsBaseDao
{
    private static final String TBLPREFIX           = "smscenter";
    private static final String CACHE_KEY_ALL       = SmsCenterDao.class
                                                            .getName()
                                                            + "_all";
    private static final String CACHE_KEY_ID        = SmsCenterDao.class
                                                            .getName()
                                                            + "_id";
    private static final String CACHE_KEY_SMSCENTER = SmsCenterDao.class
                                                            .getName()
                                                            + "_smscenter";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 插入
     */
    public Long insert(final SmsCenter item)
    {
        if (item == null)
        {
            return 0L;
        }

        KeyHolder idKey = new GeneratedKeyHolder();
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append(table());
        sb
                .append(" (`smscenter`,`city`, `province`, `type`, `status`, `createdate`,"
                        + "`updatedate`)"
                        + " VALUES (?,"
                        + "?, ?, ?, ?, CURRENT_TIMESTAMP,"
                        + "CURRENT_TIMESTAMP)");
        final String sql = sb.toString();
        PreparedStatementCreator psc = new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException
            {
                PreparedStatement ps = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                int i = 1;

                if (item.getSmscenter() != null)
                {
                    ps.setString(i++, item.getSmscenter());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getCity() != null)
                {
                    ps.setString(i++, item.getCity());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getProvince() != null)
                {
                    ps.setString(i++, item.getProvince());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getType() != null)
                {
                    ps.setInt(i++, item.getType());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getStatus() != null)
                {
                    ps.setInt(i++, item.getStatus());
                }
                else
                {
                    ps.setInt(i++, 0);
                }
                return ps;
            }
        };
        int id = getJdbcTemplate().update(psc, idKey);
        if (id > 0)
        {
            WnsCacheFactory.delete(CACHE_KEY_ALL);
        }
        return idKey.getKey().longValue();
    }

    /**
     * 更新
     */
    public void update(final SmsCenter item)
    {
        if (item == null)
        {
            return;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ");
        sb.append(table());
        sb
                .append(" SET `smscenter`=?, `city`=?, `province`=?, `type`=?, `status`=?, "
                        + " `updatedate`=CURRENT_TIMESTAMP " + " WHERE `id`=?");
        PreparedStatementSetter psc = new PreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps) throws SQLException
            {
                int i = 1;
                if (item.getSmscenter() != null)
                {
                    ps.setString(i++, item.getSmscenter());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getCity() != null)
                {
                    ps.setString(i++, item.getCity());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getProvince() != null)
                {
                    ps.setString(i++, item.getProvince());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getType() != null)
                {
                    ps.setInt(i++, item.getType());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getStatus() != null)
                {
                    ps.setInt(i++, item.getStatus());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                ps.setLong(i++, item.getId());
            }
        };
        int id = getJdbcTemplate().update(sb.toString(), psc);
        if (id > 0)
        {
            WnsCacheFactory.delete(CACHE_KEY_ALL);
            WnsCacheFactory.delete(CACHE_KEY_ID + id);
        }
    }

    @SuppressWarnings("unchecked")
    public SmsCenter findSingle(Long id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
        if (obj != null && obj instanceof SmsCenter)
        {
            return (SmsCenter) obj;
        }
        else
        {
            List<SmsCenter> list = query("select * from " + table()
                    + " where id=? and status = 0 LIMIT 1",
                    new Object[] { id }, new int[] { Types.BIGINT },
                    new WnsCommonRowMapper(SmsCenter.class));
            if (list != null && list.size() > 0)
            {
                SmsCenter item = list.get(0);
                if (item != null)
                {
                    WnsCacheFactory.add(CACHE_KEY_ID + id, item,
                            WnsCacheFactory.ONE_MONTH);
                }
                return item;
            }
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public SmsCenter findSingleBySmsCenter(String smsCenter)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_SMSCENTER + smsCenter);
        if (obj != null && obj instanceof SmsCenter)
        {
            return (SmsCenter) obj;
        }
        else
        {
            List<SmsCenter> list = query("select * from " + table()
                    + " where smscenter=?  LIMIT 1",
                    new Object[] { smsCenter }, new int[] { Types.VARCHAR },
                    new WnsCommonRowMapper(SmsCenter.class));
            if (list != null && list.size() > 0)
            {
                SmsCenter item = list.get(0);
                if (item != null)
                {
                    WnsCacheFactory.add(CACHE_KEY_SMSCENTER + smsCenter,
                            item, WnsCacheFactory.ONE_MONTH);
                }
                return item;
            }
            return null;
        }
    }

}
