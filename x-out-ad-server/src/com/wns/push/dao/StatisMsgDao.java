package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.mysql.jdbc.Statement;
import com.wns.push.bean.StatisMsg;
import com.wns.push.bean.StatisMsgQuery;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;
import com.wns.push.util.WnsDateUtil;
import com.wns.push.util.WnsStringUtil;
import com.wns2.tran.WnsPager;
import com.wns2.util.WnsUtil;

public class StatisMsgDao extends WnsBaseDao
{
    private static final String TBLPREFIX          = "statis_msg";
    private static final String CACHE_KEY_ALL      = StatisMsgDao.class
                                                           .getName()
                                                           + "_all";
    private static final String CACHE_KEY_ID       = StatisMsgDao.class
                                                           .getName()
                                                           + "_id";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 插入
     */
    public int insert(final StatisMsg item)
    {
        if (item == null)
        {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append(table());
        sb.append(" (`msgid`, `msgname`, `channel`," +
        		" `count`,`createdate`,"
                + "`updatedate`, `status`)"
                + " VALUES (?, ?, ?,  ?, ?, "
                + "CURRENT_TIMESTAMP, ?)");
        final String sql = sb.toString();
        PreparedStatementCreator psc = new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException
            {
                PreparedStatement ps = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                if (item.getMsgId()!= null)
                {
                    ps.setLong(i++, item.getMsgId());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getMsgName() != null)
                {
                    ps.setString(i++, item.getMsgName());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getChannel() != null)
                {
                    ps.setString(i++, item.getChannel());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                
                if (item.getCount() != null)
                {
                    ps.setInt(i++, item.getCount());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getCreatedate() != null)
                {
                    ps.setTimestamp(i++, WnsDateUtil.JavaDateToTimestamp(item.getCreatedate()));
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

                return ps;
            }
        };
        int id = getJdbcTemplate().update(psc);
        if (id > 0)
        {
            WnsCacheFactory.delete(CACHE_KEY_ALL);
        }
        return id;
    }

    public void insertBatch(final List<StatisMsg> itemList)
    {
        final int batchSize = 1000;

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(table());
        sb.append(" (`msgid`, `msgname`, `channel`," +
                " `count`, `createdate`,"
                + "`updatedate`, `status`)"
                + " VALUES (?, ?, ?, ?, ?,"
                + "CURRENT_TIMESTAMP, ?)");

        for (int j = 0; j < itemList.size(); j += batchSize)
        {

            final List<StatisMsg> batchList = itemList.subList(j, j
                    + batchSize > itemList.size() ? itemList.size() : j
                    + batchSize);

            getJdbcTemplate().batchUpdate(sb.toString(),
                    new BatchPreparedStatementSetter()
                    {
                        public void setValues(PreparedStatement ps, int index)
                                throws SQLException
                        {
                            StatisMsg item = batchList.get(index);
                            int i = 1;

                            if (item.getMsgId()!= null)
                            {
                                ps.setLong(i++, item.getMsgId());
                            }
                            else
                            {
                                ps.setNull(i++, Types.NULL);
                            }
                            if (item.getMsgName() != null)
                            {
                                ps.setString(i++, item.getMsgName());
                            }
                            else
                            {
                                ps.setNull(i++, Types.NULL);
                            }
                            if (item.getChannel() != null)
                            {
                                ps.setString(i++, item.getChannel());
                            }
                            else
                            {
                                ps.setNull(i++, Types.NULL);
                            }
                            
                            if (item.getCount() != null)
                            {
                                ps.setInt(i++, item.getCount());
                            }
                            else
                            {
                                ps.setNull(i++, Types.NULL);
                            }
                            if (item.getCreatedate() != null)
                            {
                                ps.setTimestamp(i++, WnsDateUtil.JavaDateToTimestamp(item.getCreatedate()));
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

                        }

                        public int getBatchSize()
                        {
                            return batchList.size();
                        }
                    });

        }
    }

    // /**
    // * 更新
    // */
    // public void update(final Client item)
    // {
    // if (item == null)
    // {
    // return;
    // }
    //
    // StringBuffer sb = new StringBuffer();
    // sb.append("UPDATE ");
    // sb.append(table());
    // sb
    // .append(" SET `channel`=?, `client_id`=?, `area`=?, `imei`=?, `imsi`=?, "
    // + " `wifi`=?, `model`=?, `phone_num`=?, `desity`=?, `width`=?, "
    // + " `height`=?, `scaled_density`=?, `xdpi`=?, `ydpi`=?, `ramsize`=?, "
    // +
    // " `leftramsize`=?, `romsize`=?, `leftromsize`=?, `sd1size` = ?, `leftsd1size`=?,"
    // +
    // " `sd2size`=?, `leftsd2size`=?, `age`=?, `sex`=?, `createdate`=?,`updatedate`=CURRENT_TIMESTAMP "
    // + " WHERE `id`=?");
    // PreparedStatementSetter psc = new PreparedStatementSetter()
    // {
    // public void setValues(PreparedStatement ps) throws SQLException
    // {
    // int i = 1;
    // if (item.getChannel() != null)
    // {
    // ps.setString(i++, item.getChannel());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getClient_id() != null)
    // {
    // ps.setString(i++, item.getClient_id());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getArea() != null)
    // {
    // ps.setString(i++, item.getArea());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getImei() != null)
    // {
    // ps.setString(i++, item.getImei());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getImsi() != null)
    // {
    // ps.setString(i++, item.getImsi());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getWifi() != null)
    // {
    // ps.setString(i++, item.getWifi());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getModel() != null)
    // {
    // ps.setString(i++, item.getModel());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getPhone_num() != null)
    // {
    // ps.setString(i++, item.getPhone_num());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getDesity() != null)
    // {
    // ps.setString(i++, item.getDesity());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getWidth() != null)
    // {
    // ps.setString(i++, item.getWidth());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getHeight() != null)
    // {
    // ps.setString(i++, item.getHeight());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getScaled_density() != null)
    // {
    // ps.setString(i++, item.getScaled_density());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getXdpi() != null)
    // {
    // ps.setString(i++, item.getXdpi());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getYdpi() != null)
    // {
    // ps.setString(i++, item.getYdpi());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getRamsize() != null)
    // {
    // ps.setString(i++, item.getRamsize());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getLeftramsize() != null)
    // {
    // ps.setString(i++, item.getLeftramsize());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getRomsize() != null)
    // {
    // ps.setString(i++, item.getRomsize());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getLeftromsize() != null)
    // {
    // ps.setString(i++, item.getLeftromsize());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getSd1size() != null)
    // {
    // ps.setString(i++, item.getSd1size());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getLeftsd1size() != null)
    // {
    // ps.setString(i++, item.getLeftsd1size());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getSd2size() != null)
    // {
    // ps.setString(i++, item.getSd2size());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getLeftsd2size() != null)
    // {
    // ps.setString(i++, item.getLeftsd2size());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getAge() != null)
    // {
    // ps.setString(i++, item.getAge());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getSex() != null)
    // {
    // ps.setString(i++, item.getSex());
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // if (item.getCreatedate() != null)
    // {
    // ps.setTimestamp(i++, NgsteamDateUtil
    // .JavaDateToTimestamp(item.getCreatedate()));
    // }
    // else
    // {
    // ps.setNull(i++, Types.NULL);
    // }
    // ps.setInt(i++, item.getId());
    // }
    // };
    // int id = getJdbcTemplate().update(sb.toString(), psc);
    // if (id > 0)
    // {
    // NgsteamCacheFactory.delete(CACHE_KEY_ALL);
    // NgsteamCacheFactory.delete(CACHE_KEY_ID + id);
    // NgsteamCacheFactory.delete(CACHE_KEY_CLIENTID + item.getClient_id());
    // }
    // }

    @SuppressWarnings("unchecked")
    public StatisMsg findSingle(int id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
        if (obj != null && obj instanceof StatisMsg)
        {
            return (StatisMsg) obj;
        }
        else
        {
            List<StatisMsg> list = query("select * from " + table()
                    + " where id=? and status = 0 LIMIT 1",
                    new Object[] { id }, new int[] { Types.INTEGER },
                    new WnsCommonRowMapper(StatisMsg.class));
            if (list != null && list.size() > 0)
            {
                StatisMsg item = list.get(0);
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
    public List<StatisMsgQuery> findMsgQuery(String channel,
            String model, String area, Date start, Date end, WnsPager pager)
    {
        List<Object> paramList = new ArrayList<Object>();
        List<Integer> typeList = new ArrayList<Integer>();
        StringBuilder sb = new StringBuilder();
        sb.append("select DATE_FORMAT(createdate , '%Y-%m-%d') date ");
        sb.append(", msgid, msgname, channel, " +
                " sum(count) count from  " + table());
        sb.append(" where createdate >= ? ");
        paramList.add(WnsDateUtil.NgsteamToString(start));
        typeList.add(Types.DATE);

        if (end != null)
        {
            sb.append(" and createdate < ?");
            paramList.add(WnsDateUtil.NgsteamToString(end));
            typeList.add(Types.DATE);
        }

        if ((channel != null) 
                && !(WnsStringUtil.isBlank(channel))
                && !"ALL".equals(channel))
        {
            sb.append(" and channel =? ");
            paramList.add(channel);
            typeList.add(Types.VARCHAR);
        }

        sb.append(" group by date, msgid ");
        if ("ALL".equals(channel))
        {
            sb.append(", channel");
        }
        
        sb.append(" order by date desc");
        if (pager != null)
        {
            sb.append(" limit " + pager.getStartRow() + ","
                    + pager.getPageSize());
        }
        List<StatisMsgQuery> list = query(sb.toString(),
                paramList.toArray(), WnsUtil.NgsteamIntegerToInt(typeList),
                new WnsCommonRowMapper(StatisMsgQuery.class));
        return list;
    }
    
    @SuppressWarnings("unchecked")
    public int findMsgQueryCount(String channel,
            String model, String area, Date start, Date end)
    {
        List<Object> paramList = new ArrayList<Object>();
        List<Integer> typeList = new ArrayList<Integer>();
        StringBuilder sb = new StringBuilder();
        sb.append("select DATE_FORMAT(createdate , '%Y-%m-%d') date ");
        sb.append(" , msgid, msgname, channel," +
                " sum(count) count from " + table());
        sb.append(" where createdate >= ? ");
        paramList.add(WnsDateUtil.NgsteamToString(start));
        typeList.add(Types.DATE);

        if (end != null)
        {
            sb.append(" and createdate < ?");
            paramList.add(WnsDateUtil.NgsteamToString(end));
            typeList.add(Types.DATE);
        }

        if ((channel != null) 
                && !(WnsStringUtil.isBlank(channel))
                && !"ALL".equals(channel))
        {
            sb.append(" and channel =? ");
            paramList.add(channel);
            typeList.add(Types.VARCHAR);
        }

        sb.append(" group by date, msgid");
        if ("ALL".equals(channel))
        {
            sb.append(", channel");
        }

        sb.append(" order by date desc");

        List<StatisMsgQuery> list = query(sb.toString(),
                paramList.toArray(), WnsUtil.NgsteamIntegerToInt(typeList),
                new WnsCommonRowMapper(StatisMsgQuery.class));
        return list.size();
    }
}
