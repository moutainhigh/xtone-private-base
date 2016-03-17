package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.mysql.jdbc.Statement;
import com.wns.push.admin.bean.AdminMenu;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;

public class AdminMenuDao extends WnsBaseDao
{
    private static final String TBLPREFIX            = "admin_menu";
    private static final String CACHE_KEY_ALL        = AdminMenuDao.class
                                                             .getName()
                                                             + "_all";
    private static final String CACHE_KEY_ID         = AdminMenuDao.class
                                                             .getName()
                                                             + "_id";
    private static final String CACHE_KEY_PID        = AdminMenuDao.class
                                                             .getName()
                                                             + "_pid";

    private static final String CACHE_KEY_PID_ROLEID = AdminMenuDao.class
                                                             .getName()
                                                             + "_pid_roleid";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 插入
     */
    public int insert(final AdminMenu adminMenu)
    {
        if (adminMenu == null)
        {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append(table());
        sb.append(" (`name`,`url`,`pid`,`status`) VALUES (?, ?, ?, ?)");
        final String sql = sb.toString();
        PreparedStatementCreator psc = new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException
            {
                PreparedStatement ps = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++, adminMenu.getName());
                ps.setString(i++, adminMenu.getUrl());
                ps.setInt(i++, adminMenu.getPid());
                ps.setInt(i++, adminMenu.getStatus());
                return ps;
            }
        };
        int id = getJdbcTemplate().update(psc);
        if (id > 0)
        {
            WnsCacheFactory.delete(CACHE_KEY_ALL);
            WnsCacheFactory.delete(CACHE_KEY_PID + adminMenu.getPid());
        }
        return id;
    }

    /**
     * 更新
     */
    public void update(final AdminMenu adminMenu)
    {
        if (adminMenu == null)
        {
            return;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ");
        sb.append(table());
        sb.append(" SET `name`=?,`url`=?,`pid`=?,`status`=? WHERE `id`=?");
        PreparedStatementSetter psc = new PreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps) throws SQLException
            {
                int i = 1;
                ps.setString(i++, adminMenu.getName());
                ps.setString(i++, adminMenu.getUrl());
                ps.setInt(i++, adminMenu.getPid());
                ps.setInt(i++, adminMenu.getStatus());
                ps.setLong(i++, adminMenu.getId());
            }
        };
        int id = getJdbcTemplate().update(sb.toString(), psc);
        if (id > 0)
        {
            WnsCacheFactory.delete(CACHE_KEY_ALL);
            WnsCacheFactory.delete(CACHE_KEY_ID + id);
            WnsCacheFactory.delete(CACHE_KEY_PID + adminMenu.getPid());
        }
    }

    @SuppressWarnings("unchecked")
    public AdminMenu findSingle(int id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
        if (obj != null && obj instanceof AdminMenu)
        {
            return (AdminMenu) obj;
        }
        else
        {
            List<AdminMenu> list = query("select * from " + table()
                    + " where id=? and status = 0 LIMIT 1",
                    new Object[] { id }, new int[] { Types.INTEGER },
                    new WnsCommonRowMapper(AdminMenu.class));
            if (list != null && list.size() > 0)
            {
                AdminMenu model = list.get(0);
                if (model != null)
                {
                    WnsCacheFactory.add(CACHE_KEY_ID + id, model,
                            WnsCacheFactory.ONE_MONTH);
                }
                return model;
            }
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<AdminMenu> findByPid(int pid)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_PID + pid);
        if (obj != null && obj instanceof List)
        {
            return (List<AdminMenu>) obj;
        }
        else
        {
            List<AdminMenu> list = query("select * from " + table()
                    + " where pid=? and status = 0", new Object[] { pid },
                    new int[] { Types.INTEGER }, new WnsCommonRowMapper(
                            AdminMenu.class));
            if (list != null && list.size() > 0)
            {

                WnsCacheFactory.add(CACHE_KEY_PID + pid, list,
                        WnsCacheFactory.ONE_MONTH);
            }
            return list;
        }
    }

    @SuppressWarnings("unchecked")
    public List<AdminMenu> findByPidAndRole(int pid, int role)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_PID_ROLEID + pid + "_" + role);
        if (obj != null && obj instanceof List)
        {
            return (List<AdminMenu>) obj;
        }
        else
        {
            List<AdminMenu> list = query(
                    "select * from "
                            + table()
                            + " where pid=? and status = 0 and id in (select menuid from admin_role_menu where roleid = ?) ",
                    new Object[] { pid, role }, new int[] { Types.INTEGER,
                            Types.INTEGER }, new WnsCommonRowMapper(
                            AdminMenu.class));
            if (list != null && list.size() > 0)
            {

                WnsCacheFactory.add(CACHE_KEY_PID_ROLEID + pid + "_" + role, list,
                        WnsCacheFactory.ONE_MONTH);
            }
            return list;
        }
    }

    @SuppressWarnings("unchecked")
    public List<AdminMenu> find(String sql, Object[] obj, int[] args)
    {
        return query(sql, obj, args,
                new WnsCommonRowMapper(AdminMenu.class));
    }

    @SuppressWarnings("unchecked")
    public AdminMenu find(String sql)
    {
        List list = getJdbcTemplate().query(sql,
                new WnsCommonRowMapper(AdminMenu.class));
        if (list != null && list.size() > 0)
        {
            return (AdminMenu) list.get(0);
        }
        return null;
    }
}
