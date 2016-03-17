package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.mysql.jdbc.Statement;
import com.wns.push.bean.WnsCountry;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;

public class WnsCountryDao extends WnsBaseDao
{
    private static final String TBLPREFIX         = "country";
    private static final String CACHE_KEY_ALL     = WnsCountryDao.class
                                                          .getName()
                                                          + "_all";
    private static final String CACHE_KEY_ID      = WnsCountryDao.class
                                                          .getName()
                                                          + "_id";
    private static final String CACHE_KEY_COUNTRY = WnsCountryDao.class
                                                          .getName()
                                                          + "_country";

    private static final String CACHE_KEY_MCC     = WnsCountryDao.class
                                                          .getName()
                                                          + "_mcc";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 插入
     */
    public int insert(final WnsCountry item)
    {
        if (item == null)
        {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append(table());
        sb.append(" (`mcc`,`mnc`,`country`, `unit`, `unitname`,"
                + "`timezone`, `ccode`, `language`, `name`,`ename`,`localname`, `remark`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        final String sql = sb.toString();
        PreparedStatementCreator psc = new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException
            {
                PreparedStatement ps = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                if (item.getMcc() != null)
                {
                    ps.setString(i++, item.getName());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getMnc() != null)
                {
                    ps.setString(i++, item.getMnc());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getCountry() != null)
                {
                    ps.setString(i++, item.getCountry());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getUnit() != null)
                {
                    ps.setString(i++, item.getUnit());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getUnitname() != null)
                {
                    ps.setString(i++, item.getUnitname());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getTimezone() != null)
                {
                    ps.setString(i++, item.getTimezone());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getCcode() != null)
                {
                    ps.setString(i++, item.getCcode());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getLanguage() != null)
                {
                    ps.setString(i++, item.getLanguage());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getName() != null)
                {
                    ps.setString(i++, item.getName());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getEname() != null)
                {
                    ps.setString(i++, item.getEname());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getLocalname() != null)
                {
                    ps.setString(i++, item.getLocalname());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getRemark() != null)
                {
                    ps.setString(i++, item.getRemark());
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
            WnsCacheFactory.delete(CACHE_KEY_MCC + item.getMcc());
            WnsCacheFactory.delete(CACHE_KEY_COUNTRY + item.getCountry());
        }
        return id;
    }

    /**
     * 更新
     */
    public void update(final WnsCountry item)
    {
        if (item == null)
        {
            return;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ");
        sb.append(table());
        sb.append(" SET `mcc`=?,`mnc`=?, `country`=?, `unit`=?, `unitname`=?,"
                + "`timezone` = ?, `ccode`=?, `language`=?, `name`=?, `ename`=?,`localname`=?, `remark`=? WHERE `id`=?");
        PreparedStatementSetter psc = new PreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps) throws SQLException
            {
                int i = 1;
                if (item.getMcc() != null)
                {
                    ps.setString(i++, item.getMcc());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getMnc() != null)
                {
                    ps.setString(i++, item.getMnc());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getCountry() != null)
                {
                    ps.setString(i++, item.getCountry());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getUnit() != null)
                {
                    ps.setString(i++, item.getUnit());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getUnitname() != null)
                {
                    ps.setString(i++, item.getUnitname());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getTimezone() != null)
                {
                    ps.setString(i++, item.getTimezone());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getCcode() != null)
                {
                    ps.setString(i++, item.getCcode());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getLanguage() != null)
                {
                    ps.setString(i++, item.getLanguage());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getName() != null)
                {
                    ps.setString(i++, item.getName());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getEname() != null)
                {
                    ps.setString(i++, item.getEname());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getLocalname() != null)
                {
                    ps.setString(i++, item.getLocalname());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getRemark() != null)
                {
                    ps.setString(i++, item.getRemark());
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
            WnsCacheFactory.delete(CACHE_KEY_MCC + item.getMcc());
            WnsCacheFactory.delete(CACHE_KEY_COUNTRY + item.getCountry());
        }
    }

    @SuppressWarnings("unchecked")
    public WnsCountry findSingle(long id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
        if (obj != null && obj instanceof WnsCountry)
        {
            return (WnsCountry) obj;
        }
        else
        {
            List<WnsCountry> list = query("select * from " + table()
                    + " where id=? LIMIT 1", new Object[] { id },
                    new int[] { Types.BIGINT }, new WnsCommonRowMapper(
                            WnsCountry.class));
            if (list != null && list.size() > 0)
            {
                WnsCountry item = list.get(0);
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
    public WnsCountry findByCountry(String country)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_COUNTRY + country);
        if (obj != null && obj instanceof WnsCountry)
        {
            return (WnsCountry) obj;
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            List<WnsCountry> list;

            sb
                    .append("select * from " + table()
                            + " where country = ? limit 1");

            list = query(sb.toString(), new Object[] { country },
                    new int[] { Types.VARCHAR }, new WnsCommonRowMapper(
                            WnsCountry.class));

            if (list != null && list.size() > 0)
            {
                WnsCountry item = list.get(0);
                WnsCacheFactory.add(CACHE_KEY_COUNTRY + country, item,
                        WnsCacheFactory.ONE_MONTH);
                return item;
            }
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public WnsCountry findByMcc(String mcc)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_MCC + mcc);
        if (obj != null && obj instanceof WnsCountry)
        {
            WnsCountry item = (WnsCountry) obj;
            if (item != null && item.getMcc() != null)
            {
                return item;
            }
            else
            {
                return null;
            }
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            List<WnsCountry> list;
            sb.append("select * from " + table() + " where mcc = ? limit 1");
            list = query(sb.toString(), new Object[] { mcc },
                    new int[] { Types.VARCHAR }, new WnsCommonRowMapper(
                            WnsCountry.class));

            if (list != null && list.size() > 0)
            {
                WnsCountry item = list.get(0);
                WnsCacheFactory.add(CACHE_KEY_MCC + mcc, item,
                        WnsCacheFactory.ONE_MONTH);
                return item;
            }
            else
            {
                WnsCountry item  = new WnsCountry();
                item.setMcc(null);
                WnsCacheFactory.add(CACHE_KEY_MCC + mcc, item,
                        WnsCacheFactory.ONE_MONTH);
                return null;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<WnsCountry> findAll()
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ALL);
        if (obj != null && obj instanceof List)
        {
            return (List<WnsCountry>) obj;
        }
        else
        {
            List<WnsCountry> list = query("select * from " + table() + " ",
                    null, null,
                    new WnsCommonRowMapper(WnsCountry.class));
            if (list != null && list.size() > 0)
            {
                WnsCacheFactory.add(CACHE_KEY_ALL, list,
                        WnsCacheFactory.ONE_MONTH);
            }
            return list;
        }
    }
}
