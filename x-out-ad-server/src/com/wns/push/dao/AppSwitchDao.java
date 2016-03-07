package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.mysql.jdbc.Statement;
import com.wns.push.bean.AppSwitch;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;
import com.wns2.factory.WnsEnum;

public class AppSwitchDao extends WnsBaseDao
{
    private static final String TBLPREFIX              = "app_switch";
    private static final String CACHE_KEY_ALL          = AppSwitchDao.class
                                                               .getName()
                                                               + "_all";
    private static final String CACHE_KEY_ID           = AppSwitchDao.class
                                                               .getName()
                                                               + "_id";
    private static final String CACHE_KEY_ALL_BY_ADMIN = AppSwitchDao.class
                                                               .getName()
                                                               + "_all_by_admin";
    private static final String CACHE_KEY_ID_BY_ADMIN  = AppSwitchDao.class
                                                               .getName()
                                                               + "_id_by_admin";
    private static final String CACHE_KEY_APPID        = AppSwitchDao.class
                                                               .getName()
                                                               + "_appid";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 插入
     */
    public int insert(final AppSwitch item)
    {
        if (item == null)
        {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append(table());
        sb.append(" (`app_id`,`appname`, `status`)" + " VALUES (?, ?, ?)");
        final String sql = sb.toString();
        PreparedStatementCreator psc = new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException
            {
                PreparedStatement ps = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                if (item.getApp_id() != null)
                {
                    ps.setString(i++, item.getApp_id());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getAppname() != null)
                {
                    ps.setString(i++, item.getAppname());
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
            WnsCacheFactory.delete(CACHE_KEY_APPID + item.getApp_id());
        }
        return id;
    }

    /**
     * 更新
     */
    public void update(final AppSwitch item)
    {
        if (item == null)
        {
            return;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ");
        sb.append(table());
        sb.append(" SET `status`=? WHERE `id`=?");
        PreparedStatementSetter psc = new PreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps) throws SQLException
            {
                int i = 1;
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
            WnsCacheFactory.delete(CACHE_KEY_ALL_BY_ADMIN);
            WnsCacheFactory.delete(CACHE_KEY_ID + id);
            WnsCacheFactory.delete(CACHE_KEY_ID_BY_ADMIN + id);
            WnsCacheFactory.delete(CACHE_KEY_APPID + item.getApp_id());
        }
    }

    @SuppressWarnings("unchecked")
    public AppSwitch findSingle(long id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
        if (obj != null && obj instanceof AppSwitch)
        {
            return (AppSwitch) obj;
        }
        else
        {
            List<AppSwitch> list = query("select * from " + table()
                    + " where id=? and status = 0 LIMIT 1",
                    new Object[] { id }, new int[] { Types.INTEGER },
                    new WnsCommonRowMapper(AppSwitch.class));
            if (list != null && list.size() > 0)
            {
                AppSwitch item = list.get(0);
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
    public AppSwitch findSingleByAdmin(long id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID_BY_ADMIN + id);
        if (obj != null && obj instanceof AppSwitch)
        {
            return (AppSwitch) obj;
        }
        else
        {
            List<AppSwitch> list = query("select * from " + table()
                    + " where id=? LIMIT 1", new Object[] { id },
                    new int[] { Types.INTEGER }, new WnsCommonRowMapper(
                            AppSwitch.class));
            if (list != null && list.size() > 0)
            {
                AppSwitch item = list.get(0);
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
    public AppSwitch findSingleByAppid(String appid)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_APPID + appid);
        if (obj != null && obj instanceof AppSwitch)
        {
            return (AppSwitch) obj;
        }
        else
        {
            List<AppSwitch> list = query("select * from " + table()
                    + " where app_id =?  LIMIT 1",
                    new Object[] { appid }, new int[] { Types.VARCHAR },
                    new WnsCommonRowMapper(AppSwitch.class));
            AppSwitch item = null;
            if (list != null && list.size() > 0)
            {
                item = list.get(0);
            }
            else
            {
                item = new AppSwitch();
                item.setApp_id(appid);
                item.setStatus(WnsEnum.ON);
            }
            if (item != null)
            {
                WnsCacheFactory.add(CACHE_KEY_APPID + appid, item,
                        WnsCacheFactory.ONE_MONTH);
            }
            return item;
        }
    }

    @SuppressWarnings("unchecked")
    public List<AppSwitch> findAll()
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ALL);
        if (obj != null && obj instanceof List)
        {
            return (List<AppSwitch>) obj;
        }
        else
        {
            List<AppSwitch> list = query("select * from " + table()
                    + " where status = 0 order by id desc", null, null,
                    new WnsCommonRowMapper(AppSwitch.class));
            if (list != null && list.size() > 0)
            {
                WnsCacheFactory.add(CACHE_KEY_ALL, list,
                        WnsCacheFactory.ONE_MONTH);
            }
            return list;
        }
    }

    @SuppressWarnings("unchecked")
    public List<AppSwitch> findAllByAdmin()
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ALL_BY_ADMIN);
        if (obj != null && obj instanceof List)
        {
            return (List<AppSwitch>) obj;
        }
        else
        {
            List<AppSwitch> list = query("select * from " + table()
                    + "", null, null,
                    new WnsCommonRowMapper(AppSwitch.class));
            if (list != null && list.size() > 0)
            {
                WnsCacheFactory.add(CACHE_KEY_ALL_BY_ADMIN, list,
                        WnsCacheFactory.ONE_MONTH);
            }
            return list;
        }
    }
}
