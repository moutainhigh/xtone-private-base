package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.mysql.jdbc.Statement;
import com.wns.push.bean.WhiteChannel;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;

public class WhiteChannelDao extends WnsBaseDao
{
    private static final String TBLPREFIX               = "white_channel";
    private static final String CACHE_KEY_ALL           = WhiteChannelDao.class
                                                                .getName()
                                                                + "_all";
    private static final String CACHE_KEY_ALL_BY_ADMIN  = WhiteChannelDao.class
                                                                .getName()
                                                                + "_all_by_admin";
    private static final String CACHE_KEY_ID            = WhiteChannelDao.class
                                                                .getName()
                                                                + "_id";
    private static final String CACHE_KEY_ID_BY_ADMIN   = WhiteChannelDao.class
                                                                .getName()
                                                                + "_id_by_admin";
    private static final String CACHE_KEY_ID_BY_CHANNEL = WhiteChannelDao.class
                                                                .getName()
                                                                + "_id_by_channel";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 插入
     */
    public int insert(final WhiteChannel item)
    {
        if (item == null)
        {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append(table());
        sb.append(" (`channel`,`status`)" + " VALUES (?, ?)");
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
        int id = getJdbcTemplate().update(psc);
        if (id > 0)
        {
            WnsCacheFactory.delete(CACHE_KEY_ALL);
            WnsCacheFactory.delete(CACHE_KEY_ALL_BY_ADMIN);
        }
        return id;
    }

    /**
     * 更新
     */
    public void update(final WhiteChannel item)
    {
        if (item == null)
        {
            return;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ");
        sb.append(table());
        sb.append(" SET `channel`=?, `status`=? WHERE `id`=?");
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
                if (item.getStatus() != null)
                {
                    ps.setInt(i++, item.getStatus());
                }
                else
                {
                    ps.setInt(i++, 0);
                }
                ps.setInt(i++, item.getId());
            }
        };
        int id = getJdbcTemplate().update(sb.toString(), psc);
        if (id > 0)
        {
            WnsCacheFactory.delete(CACHE_KEY_ALL);
            WnsCacheFactory.delete(CACHE_KEY_ALL_BY_ADMIN);
            WnsCacheFactory.delete(CACHE_KEY_ID + id);
            WnsCacheFactory.delete(CACHE_KEY_ID_BY_ADMIN + id);
            WnsCacheFactory.delete(CACHE_KEY_ID_BY_CHANNEL + item.getChannel());
        }
    }

    @SuppressWarnings("unchecked")
    public WhiteChannel findSingle(long id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
        if (obj != null && obj instanceof WhiteChannel)
        {
            return (WhiteChannel) obj;
        }
        else
        {
            List<WhiteChannel> list = query("select * from " + table()
                    + " where id=? and status = 0 LIMIT 1",
                    new Object[] { id }, new int[] { Types.INTEGER },
                    new WnsCommonRowMapper(WhiteChannel.class));
            if (list != null && list.size() > 0)
            {
                WhiteChannel item = list.get(0);
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
    public List<WhiteChannel> findAll()
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ALL);
        if (obj != null && obj instanceof List)
        {
            return (List<WhiteChannel>) obj;
        }
        else
        {
            List<WhiteChannel> list = query("select * from " + table()
                    + " where status = 0 order by id desc", null, null,
                    new WnsCommonRowMapper(WhiteChannel.class));
            if (list != null && list.size() > 0)
            {
                WnsCacheFactory.add(CACHE_KEY_ALL, list,
                        WnsCacheFactory.ONE_MONTH);
            }
            return list;
        }
    }

    @SuppressWarnings("unchecked")
    public WhiteChannel findSingleByAdmin(long id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID_BY_ADMIN + id);
        if (obj != null && obj instanceof WhiteChannel)
        {
            return (WhiteChannel) obj;
        }
        else
        {
            List<WhiteChannel> list = query("select * from " + table()
                    + " where id=? LIMIT 1", new Object[] { id },
                    new int[] { Types.INTEGER }, new WnsCommonRowMapper(
                            WhiteChannel.class));
            if (list != null && list.size() > 0)
            {
                WhiteChannel item = list.get(0);
                if (item != null)
                {
                    WnsCacheFactory.add(CACHE_KEY_ID_BY_ADMIN + id, item,
                            WnsCacheFactory.ONE_MONTH);
                }
                return item;
            }
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public WhiteChannel findSingleByChannel(String channel)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID_BY_CHANNEL + channel);
        if (obj != null && obj instanceof WhiteChannel)
        {
            return (WhiteChannel) obj;
        }
        else
        {
            List<WhiteChannel> list = query("select * from " + table()
                    + " where channel=? LIMIT 1", new Object[] { channel },
                    new int[] { Types.VARCHAR }, new WnsCommonRowMapper(
                            WhiteChannel.class));
            if (list != null && list.size() > 0)
            {
                WhiteChannel item = list.get(0);
                if (item != null)
                {
                    WnsCacheFactory.add(CACHE_KEY_ID_BY_CHANNEL + channel, item,
                            WnsCacheFactory.ONE_MONTH);
                }
                return item;
            }
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<WhiteChannel> findAllByAdmin()
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ALL_BY_ADMIN);
        if (obj != null && obj instanceof List)
        {
            return (List<WhiteChannel>) obj;
        }
        else
        {
            List<WhiteChannel> list = query("select * from " + table() + "",
                    null, null, new WnsCommonRowMapper(WhiteChannel.class));
            if (list != null && list.size() > 0)
            {
                WnsCacheFactory.add(CACHE_KEY_ALL_BY_ADMIN, list,
                        WnsCacheFactory.ONE_MONTH);
            }
            return list;
        }
    }
}
