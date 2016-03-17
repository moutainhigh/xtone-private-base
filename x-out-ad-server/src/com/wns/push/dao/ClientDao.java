package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.jdbc.Statement;
import com.wns.push.bean.Client;
import com.wns.push.bean.StatisClientQuery;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;
import com.wns.push.util.WnsDateUtil;
import com.wns2.factory.WnsRedisDbQueue;
import com.wns2.util.WnsUtil;

public class ClientDao extends WnsBaseDao
{
    private static final String TBLPREFIX              = "client";
    private static final String CACHE_KEY_ALL          = ClientDao.class
                                                               .getName()
                                                               + "_all";
    private static final String CACHE_KEY_ID           = ClientDao.class
                                                               .getName()
                                                               + "_id";
    private static final String CACHE_KEY_CLIENTID     = ClientDao.class
                                                               .getName()
                                                               + "_clientid";

    private static final String CACHE_KEY_INSERT       = ClientDao.class
                                                               .getName()
                                                               + "_redis_insert";

    private static final String CACHE_KEY_QUERY_CLIENT = ClientDao.class
                                                               .getName()
                                                               + "_query";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 插入
     */
    public Long insert(final Client item)
    {
        if (item == null)
        {
            return 0L;
        }
        String key = CACHE_KEY_CLIENTID + item.getClient_id();
//        NgsteamCacheFactory.delete(key);
        WnsCacheFactory.add(key, item, WnsCacheFactory.ONE_DAY);
        Long ret = WnsRedisDbQueue.getInstance().push(CACHE_KEY_INSERT,
                item);
    //    if (ret != null)
    //    {
    //        return ret;
    //    }
    //    else
        {
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
                    if (item.getAppid() != null)
                    {
                        ps.setString(i++, item.getAppid());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    
                    if (item.getClient_id() != null)
                    {
                        ps.setString(i++, item.getClient_id());
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
                    if (item.getImei() != null)
                    {
                        ps.setString(i++, item.getImei());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getImsi() != null)
                    {
                        ps.setString(i++, item.getImsi());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getWifi() != null)
                    {
                        ps.setString(i++, item.getWifi());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getModel() != null)
                    {
                        ps.setString(i++, item.getModel());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getPhone_num() != null)
                    {
                        ps.setString(i++, item.getPhone_num());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getDesity() != null)
                    {
                        ps.setString(i++, item.getDesity());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getWidth() != null)
                    {
                        ps.setString(i++, item.getWidth());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getHeight() != null)
                    {
                        ps.setString(i++, item.getHeight());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getScaled_density() != null)
                    {
                        ps.setString(i++, item.getScaled_density());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getXdpi() != null)
                    {
                        ps.setString(i++, item.getXdpi());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getYdpi() != null)
                    {
                        ps.setString(i++, item.getYdpi());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getRamsize() != null)
                    {
                        ps.setString(i++, item.getRamsize());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getLeftramsize() != null)
                    {
                        ps.setString(i++, item.getLeftramsize());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getRomsize() != null)
                    {
                        ps.setString(i++, item.getRomsize());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getLeftromsize() != null)
                    {
                        ps.setString(i++, item.getLeftromsize());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getSd1size() != null)
                    {
                        ps.setString(i++, item.getSd1size());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getLeftsd1size() != null)
                    {
                        ps.setString(i++, item.getLeftsd1size());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getSd2size() != null)
                    {
                        ps.setString(i++, item.getSd2size());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getLeftsd2size() != null)
                    {
                        ps.setString(i++, item.getLeftsd2size());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getAge() != null)
                    {
                        ps.setString(i++, item.getAge());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }
                    if (item.getSex() != null)
                    {
                        ps.setString(i++, item.getSex());
                    }
                    else
                    {
                        ps.setNull(i++, Types.NULL);
                    }

                    return ps;
                }
            };
            int id = getJdbcTemplate().update(psc, idKey);
            if (id > 0)
            {
                WnsCacheFactory.delete(CACHE_KEY_ALL);
                WnsCacheFactory.delete(key);
                item.setId(idKey.getKey().longValue());
                WnsCacheFactory.add(key, item,
                        WnsCacheFactory.ONE_WEEK);
            }
            return idKey.getKey().longValue();
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
    // NgsteamCacheFactory
    // .delete(CACHE_KEY_CLIENTID + item.getClient_id());
    //            
    // NgsteamCacheFactory.add(CACHE_KEY_CLIENTID + item.getClient_id(),
    // item, NgsteamCacheFactory.ONE_MONTH);
    // }
    // }

    @SuppressWarnings("unchecked")
    public Client findSingle(int id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
        if (obj != null && obj instanceof Client)
        {
            return (Client) obj;
        }
        else
        {
            List<Client> list = query("select * from " + table()
                    + " where id=? and status = 0 LIMIT 1",
                    new Object[] { id }, new int[] { Types.INTEGER },
                    new WnsCommonRowMapper(Client.class));
            if (list != null && list.size() > 0)
            {
                Client item = list.get(0);
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
    public Client findSingleByClient(String clientId)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_CLIENTID + clientId);
        if (obj != null && obj instanceof Client)
        {
            return (Client) obj;
        }
        else
        {
            List<Client> list = query("select * from " + table()
                    + " where client_id=?  LIMIT 1", new Object[] { clientId },
                    new int[] { Types.VARCHAR }, new WnsCommonRowMapper(
                            Client.class));
            if (list != null && list.size() > 0)
            {
                Client item = list.get(0);
                if (item != null)
                {
                    WnsCacheFactory.add(CACHE_KEY_CLIENTID + clientId,
                            item, WnsCacheFactory.ONE_MONTH);
                }
                return item;
            }
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<StatisClientQuery> statisClient(Date start, Date end,
            int startrow, int rownum)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_QUERY_CLIENT + start
                + "_" + end + "_" + startrow + "_" + rownum);
        if (obj != null && obj instanceof List)
        {
        	System.out.println("statisClient.obj---->null");
            return (List<StatisClientQuery>) obj;
        }
        else
        {
        
            List<Object> paramList = new ArrayList<Object>();
            List<Integer> typeList = new ArrayList<Integer>();
            StringBuilder sb = new StringBuilder();
            sb.append("select DATE_FORMAT(createdate , '%Y-%m-%d') date ");
            sb.append(", channel, count(id) count from  "
                    + table());
            sb.append(" where createdate >= ? ");
        	System.out.println("statisClient.sb---->"+sb);
            paramList.add(WnsDateUtil.NgsteamToString(start));
            typeList.add(Types.DATE);

            if (end != null)
            {
                sb.append(" and createdate < ?");
                paramList.add(WnsDateUtil.NgsteamToString(end));
                typeList.add(Types.DATE);
            }
            sb.append(" group by date, channel ");
            sb.append(" limit " + startrow + "," + rownum);
            List<StatisClientQuery> list = query(sb.toString(), paramList
                    .toArray(), WnsUtil.NgsteamIntegerToInt(typeList),
                    new WnsCommonRowMapper(StatisClientQuery.class));
            if (list.size() > 0)
            {
                WnsCacheFactory.add(CACHE_KEY_QUERY_CLIENT + start + "_"
                        + end + "_" + startrow + "_" + rownum, list,
                        WnsCacheFactory.ONE_HOUR);
            }
            return list;
        }
    }

    @SuppressWarnings("unchecked")
    public int statisClientNum(Date start, Date end)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_QUERY_CLIENT + start
                + "_" + end);
        if (obj != null && obj instanceof List)
        {
            return ((List<StatisClientQuery>) obj).size();
        }
        List<Object> paramList = new ArrayList<Object>();
        List<Integer> typeList = new ArrayList<Integer>();
        StringBuilder sb = new StringBuilder();
        sb.append("select DATE_FORMAT(createdate , '%Y-%m-%d') date ");
        sb.append(", channel,  count(id) count from  " + table());
        sb.append(" where createdate >= ? ");
        paramList.add(WnsDateUtil.NgsteamToString(start));
        typeList.add(Types.DATE);

        if (end != null)
        {
            sb.append(" and createdate < ?");
            paramList.add(WnsDateUtil.NgsteamToString(end));
            typeList.add(Types.DATE);
        }
        sb.append(" group by date, channel ");
        List<StatisClientQuery> list = query(sb.toString(),
                paramList.toArray(), WnsUtil.NgsteamIntegerToInt(typeList),
                new WnsCommonRowMapper(StatisClientQuery.class));
        if (list.size() > 0)
        {
            WnsCacheFactory.add(CACHE_KEY_QUERY_CLIENT + start + "_" + end,
                    list, WnsCacheFactory.ONE_HOUR);
        }
        return list.size();
    }

    // @SuppressWarnings("unchecked")
    // public Client findByPkgNameAndChannel(String name, String channel)
    // {
    // Object obj = NgsteamCacheFactory.get(CACHE_KEY_PKGNAME_AND_CHANNEL
    // + name + "_" + channel);
    // if (obj != null && obj instanceof Client)
    // {
    // return (Client) obj;
    // }
    // else
    // {
    // StringBuilder sb = new StringBuilder();
    // List<Client> list;
    //
    // sb.append("select * from " + table() + " where pkgname = ? ");
    // if ((channel != null) && !channel.isEmpty())
    // {
    // sb.append(" and channel = ? ");
    // sb.append(" and status = 0 order by versionCode desc limit 1");
    //
    // list = query(sb.toString(), new Object[] { name, channel },
    // new int[] { Types.VARCHAR, Types.VARCHAR },
    // new NgsteamCommonRowMapper(Client.class));
    //
    // }
    // else
    // {
    // sb.append(" and status = 0 order by versionCode desc limit 1");
    // list = query(sb.toString(), new Object[] { name },
    // new int[] { Types.VARCHAR },
    // new NgsteamCommonRowMapper(ApkItem.class));
    // }
    // if (list != null && list.size() > 0)
    // {
    // Client item = list.get(0);
    // NgsteamCacheFactory.add(CACHE_KEY_PKGNAME_AND_CHANNEL + name
    // + "_" + channel, item, NgsteamCacheFactory.ONE_MONTH);
    // return item;
    // }
    // return null;
    // }
    // }
    //
    // @SuppressWarnings("unchecked")
    // public List<Client> findAll()
    // {
    // Object obj = NgsteamCacheFactory.get(CACHE_KEY_ALL);
    // if (obj != null && obj instanceof List)
    // {
    // return (List<Client>) obj;
    // }
    // else
    // {
    // List<Client> list = query("select * from " + table()
    // + " where status = 0 order by id desc", null, null,
    // new NgsteamCommonRowMapper(Client.class));
    // if (list != null && list.size() > 0)
    // {
    // NgsteamCacheFactory.add(CACHE_KEY_ALL, list,
    // NgsteamCacheFactory.ONE_MONTH);
    // }
    // return list;
    // }
    // }

}
