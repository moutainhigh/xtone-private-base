package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.mysql.jdbc.Statement;
import com.wns.push.bean.ApkItem;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;

public class ApkDao extends WnsBaseDao
{
    private static final String TBLPREFIX                     = "apk";
    private static final String CACHE_KEY_ALL                 = ApkDao.class
                                                                      .getName()
                                                                      + "_all";
    private static final String CACHE_KEY_ID                  = ApkDao.class
                                                                      .getName()
                                                                      + "_id";
    private static final String CACHE_KEY_PKGNAME_AND_CHANNEL = ApkDao.class
                                                                      .getName()
                                                                      + "_pkgname";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 插入
     */
    public int insert(final ApkItem item)
    {
        if (item == null)
        {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append(table());
        sb.append(" (`name`,`pkgname`, `sign`, `size`, `version`,"
                + "`versioncode`, `url`,`channel`,`phonetype`, `status`)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        final String sql = sb.toString();
        PreparedStatementCreator psc = new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException
            {
                PreparedStatement ps = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                if (item.getName() != null)
                {
                    ps.setString(i++, item.getName());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getPkgname() != null)
                {
                    ps.setString(i++, item.getPkgname());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getSign() != null)
                {
                    ps.setString(i++, item.getSign());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getSize() != null)
                {
                    ps.setInt(i++, item.getSize());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getVersion() != null)
                {
                    ps.setString(i++, item.getVersion());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getVersioncode() != null)
                {
                    ps.setInt(i++, item.getVersioncode());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getUrl() != null)
                {
                    ps.setString(i++, item.getUrl());
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
                if (item.getPhonetype() != null)
                {
                    ps.setString(i++, item.getPhonetype());
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
            WnsCacheFactory.delete(CACHE_KEY_PKGNAME_AND_CHANNEL
                    + item.getPkgname() + "_" + item.getChannel());
        }
        return id;
    }

    /**
     * 更新
     */
    public void update(final ApkItem item)
    {
        if (item == null)
        {
            return;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ");
        sb.append(table());
        sb
                .append(" SET `name`=?, `pkgname`=?, `sign` = ?, `size`=?, `version`=?, "
                        + "`versioncode`=?, `url`=?,`channel`=?, `phonetype`=?, `status`=? WHERE `id`=?");
        PreparedStatementSetter psc = new PreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps) throws SQLException
            {
                int i = 1;
                if (item.getName() != null)
                {
                    ps.setString(i++, item.getName());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getPkgname() != null)
                {
                    ps.setString(i++, item.getPkgname());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getSign() != null)
                {
                    ps.setString(i++, item.getSign());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getSize() != null)
                {
                    ps.setInt(i++, item.getSize());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getVersion() != null)
                {
                    ps.setString(i++, item.getVersion());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getVersioncode() != null)
                {
                    ps.setInt(i++, item.getVersioncode());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getUrl() != null)
                {
                    ps.setString(i++, item.getUrl());
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
                if (item.getPhonetype() != null)
                {
                    ps.setString(i++, item.getPhonetype());
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
            WnsCacheFactory.delete(CACHE_KEY_ID + id);
            WnsCacheFactory.delete(CACHE_KEY_PKGNAME_AND_CHANNEL
                    + item.getPkgname() + "_" + item.getChannel());
        }
    }

    @SuppressWarnings("unchecked")
    public ApkItem findSingle(int id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
        if (obj != null && obj instanceof ApkItem)
        {
            return (ApkItem) obj;
        }
        else
        {
            List<ApkItem> list = query("select * from " + table()
                    + " where id=? and status = 0 LIMIT 1",
                    new Object[] { id }, new int[] { Types.INTEGER },
                    new WnsCommonRowMapper(ApkItem.class));
            if (list != null && list.size() > 0)
            {
                ApkItem item = list.get(0);
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
    public ApkItem findByPkgNameAndChannel(String name, String channel)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_PKGNAME_AND_CHANNEL
                + name + "_" + channel);
        if (obj != null && obj instanceof ApkItem)
        {
            return (ApkItem) obj;
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            List<ApkItem> list;

            sb.append("select * from " + table() + " where pkgname = ? ");
            if ((channel != null) && !channel.isEmpty())
            {
                sb.append(" and channel = ? ");
                sb.append(" and status = 0 order by versionCode desc limit 1");

                list = query(sb.toString(), new Object[] { name, channel },
                        new int[] { Types.VARCHAR, Types.VARCHAR },
                        new WnsCommonRowMapper(ApkItem.class));

            }
            else
            {
                sb.append(" and status = 0 order by versionCode desc limit 1");
                list = query(sb.toString(), new Object[] { name },
                        new int[] { Types.VARCHAR },
                        new WnsCommonRowMapper(ApkItem.class));
            }
            if (list != null && list.size() > 0)
            {
                ApkItem item = list.get(0);
                WnsCacheFactory.add(CACHE_KEY_PKGNAME_AND_CHANNEL + name
                        + "_" + channel, item, WnsCacheFactory.ONE_MONTH);
                return item;
            }
            else 
            {
                ApkItem item = new ApkItem();
                item.setPkgname(name);
                item.setChannel(channel);
                item.setVersion("0.0.0");
                item.setVersioncode(0);
                WnsCacheFactory.add(CACHE_KEY_PKGNAME_AND_CHANNEL + name
                        + "_" + channel, item, WnsCacheFactory.ONE_MONTH);
                return item;
            }
//            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<ApkItem> findAll()
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ALL);
        if (obj != null && obj instanceof List)
        {
            return (List<ApkItem>) obj;
        }
        else
        {
            List<ApkItem> list = query("select * from " + table()
                    + " where status = 0 order by id desc", null, null,
                    new WnsCommonRowMapper(ApkItem.class));
            if (list != null && list.size() > 0)
            {
                WnsCacheFactory.add(CACHE_KEY_ALL, list,
                        WnsCacheFactory.ONE_MONTH);
            }
            return list;
        }
    }
}
