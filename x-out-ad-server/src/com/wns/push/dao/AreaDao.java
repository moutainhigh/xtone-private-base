package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.jdbc.Statement;
import com.wns.push.bean.Area;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;

public class AreaDao extends WnsBaseDao
{
    private static final String TBLPREFIX     = "area";
    private static final String CACHE_KEY_ALL = AreaDao.class.getName()
                                                      + "_all";
    private static final String CACHE_KEY_ID  = AreaDao.class.getName() + "_id";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 插入
     */
    public Long insert(final Area item)
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
                .append(" (`city`,`province`, `country`, `area`,  `status`, `createdate`,"
                        + "`updatedate`)"
                        + " VALUES ( "
                        + "?, ?, ?, ?, ?, CURRENT_TIMESTAMP,"
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
                if (item.getCountry() != null)
                {
                    ps.setString(i++, item.getCountry());
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

    @SuppressWarnings("unchecked")
    public Area findSingle(int id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
        if (obj != null && obj instanceof Area)
        {
            return (Area) obj;
        }
        else
        {
            List<Area> list = query("select * from " + table()
                    + " where id=? and status = 0 LIMIT 1",
                    new Object[] { id }, new int[] { Types.INTEGER },
                    new WnsCommonRowMapper(Area.class));
            if (list != null && list.size() > 0)
            {
                Area item = list.get(0);
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
    public List<Area> findAll()
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ALL);
        if (obj != null && obj instanceof List)
        {
            return (List<Area>) obj;
        }
        else
        {
            List<Area> list = query("select * from " + table()
                    + " where status = 0", null, null,
                    new WnsCommonRowMapper(Area.class));
            System.out.println("area.list====>"+list.size());
            for(Area area:list){
            	 System.out.println("dao.area.city====>"+area.getCity());
            }
            if (list != null && list.size() > 0)
            {
                WnsCacheFactory.add(CACHE_KEY_ALL, list,
                        WnsCacheFactory.ONE_MONTH);
            }
            return list;
        }
    }

}
