package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.mysql.jdbc.Statement;
import com.wns.push.bean.AdminUser;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;

public class AdminUserDao extends WnsBaseDao
{
    private static final String TBLPREFIX      = "admin_user";
    private static final String CACHE_KEY_ALL  = AdminUserDao.class.getName()
                                                       + "_all";
    private static final String CACHE_KEY_ID   = AdminUserDao.class.getName()
                                                       + "_id";
    private static final String CACHE_KEY_NAME = AdminUserDao.class.getName()
                                                       + "_name";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 插入
     */
    public int insert(final AdminUser item)
    {
        if (item == null)
        {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append(table());
        sb.append(" (`name`,`password`, `role`, `createdate`, `updatedate`, `status`)"
                + " VALUES (?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)");
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
                if (item.getPassword() != null)
                {
                    ps.setString(i++, item.getPassword());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getRole() != null)
                {
                    ps.setLong(i++, item.getRole());
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
        }
        return id;
    }

    /**
     * 更新
     */
    public void update(final AdminUser item)
    {
        if (item == null)
        {
            return;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ");
        sb.append(table());
        sb
                .append(" SET `name`=?, `password`=?, `role`=?, `updatedate` = CURRENT_TIMESTAMP, `status`=? WHERE `id`=?");
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
                if (item.getPassword() != null)
                {
                    ps.setString(i++, item.getPassword());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getRole() != null)
                {
                    ps.setLong(i++, item.getRole());
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
        }
    }

    @SuppressWarnings("unchecked")
    public AdminUser findSingle(Long id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
        if (obj != null && obj instanceof AdminUser)
        {
            return (AdminUser) obj;
        }
        else
        {
            List<AdminUser> list = query("select * from " + table()
                    + " where id=? and status = 0 LIMIT 1",
                    new Object[] { id }, new int[] { Types.INTEGER },
                    new WnsCommonRowMapper(AdminUser.class));
            if (list != null && list.size() > 0)
            {
                AdminUser item = list.get(0);
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
    public AdminUser findByName(String name)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_NAME + name);

        if (obj != null && obj instanceof AdminUser)
        {
        	
        	System.out.println("-------------if-----------");
            return (AdminUser) obj;
        }
        else
        {
        	System.out.println("-------------else-----------");
            StringBuilder sb = new StringBuilder();
            List<AdminUser> list;

            sb.append("select * from " + table()
                    + " where name = ? and status = 0 limit 1 ");
            System.out.println("sqlString====="+sb.toString());
            list = query(sb.toString(), new Object[] { name },
                    new int[] { Types.VARCHAR }, new WnsCommonRowMapper(
                            AdminUser.class));
            
            if (list != null && list.size() > 0)
            {
                AdminUser item = list.get(0);
                WnsCacheFactory.add(CACHE_KEY_NAME + name, item,
                        WnsCacheFactory.ONE_MONTH);
                return item;
            }
            return null;
        }
    }

}
