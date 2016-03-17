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
import com.wns.push.bean.ChannelArea;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;

public class ChannelAreaDao extends WnsBaseDao
{
    private static final String TBLPREFIX         = "channel_area";
    private static final String CACHE_KEY_ALL     = ChannelAreaDao.class
                                                          .getName()
                                                          + "_all";
    private static final String CACHE_KEY_ID      = ChannelAreaDao.class
                                                          .getName()
                                                          + "_id";
    private static final String CACHE_KEY_CHANNEL = ChannelAreaDao.class
                                                          .getName()
                                                          + "_channel";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 插入
     */
    public Long insert(final ChannelArea item)
    {
        if (item == null)
        {
            return 0L;
        }
        KeyHolder idKey = new GeneratedKeyHolder();
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append(table());
        sb.append(" (`channel`, `area`, `status`, `createdate`,"
                + "`updatedate`)" + " VALUES (?, ?, ?, CURRENT_TIMESTAMP,"
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

                if (item.getChannel() != null)
                {
                    ps.setString(i++, item.getChannel());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getArea() != null)
                {
                    ps.setString(i++, item.getArea());
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
    public void update(final ChannelArea item)
    {
        if (item == null)
        {
            return;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ");
        sb.append(table());
        sb
                .append(" SET `channel`=?,`area`=?, `status`=?, `updatedate`=CURRENT_TIMESTAMP "
                        + " WHERE `id`=?");
        PreparedStatementSetter psc = new PreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps) throws SQLException
            {
                int i = 1;
                if (item.getChannel() != null)
                {
                    ps.setString(i++, item.getChannel());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getArea() != null)
                {
                    ps.setString(i++, item.getArea());
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
                ps.setLong(i++, item.getId());
            }
        };

        int id = getJdbcTemplate().update(sb.toString(), psc);
        if (id > 0)
        {
            WnsCacheFactory.delete(CACHE_KEY_ALL);
            WnsCacheFactory.delete(CACHE_KEY_ID + id);
            WnsCacheFactory.delete(CACHE_KEY_CHANNEL + item.getChannel());
        }
    }

    @SuppressWarnings("unchecked")
    public ChannelArea findSingle(Long id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
        if (obj != null && obj instanceof ChannelArea)
        {
            return (ChannelArea) obj;
        }
        else
        {
            List<ChannelArea> list = query("select * from " + table()
                    + " where id=? and status = 0 LIMIT 1",
                    new Object[] { id }, new int[] { Types.INTEGER },
                    new WnsCommonRowMapper(ChannelArea.class));
            if (list != null && list.size() > 0)
            {
                ChannelArea item = list.get(0);
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
    public ChannelArea findSingleByChannel(String channel)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_CHANNEL + channel);
        if (obj != null && obj instanceof ChannelArea)
        {
            return (ChannelArea) obj;
        }
        else
        {
            List<ChannelArea> list = query("select * from " + table()
                    + " where channel=?  LIMIT 1", new Object[] { channel },
                    new int[] { Types.VARCHAR }, new WnsCommonRowMapper(
                            ChannelArea.class));
            ChannelArea item = null;
            if (list != null && list.size() > 0)
            {
                item = list.get(0);
            }
            if (item == null)
            {
                item = new ChannelArea();
                item.setChannel(channel);
            }
            WnsCacheFactory.add(CACHE_KEY_CHANNEL + channel, item,
                    WnsCacheFactory.ONE_MONTH);
            return item;
        }
    }

    @SuppressWarnings("unchecked")
    public List<ChannelArea> findAll()
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ALL);
        if (obj != null && obj instanceof List)
        {
            return (List<ChannelArea>) obj;
        }
        else
        {
            List<ChannelArea> list = query("select * from " + table()
                    + " where status = 0", null, null,
                    new WnsCommonRowMapper(ChannelArea.class));
            WnsCacheFactory.add(CACHE_KEY_ALL, list,
                    WnsCacheFactory.ONE_MONTH);
            
            return list;
        }
    }
}
