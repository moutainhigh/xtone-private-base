package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.wns.push.bean.IPTABLE;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;
import com.wns.push.util.WnsStringUtil;


public class ipTableDao extends WnsBaseDao
{
    private static final String TBLPREFIX     = "IPTABLE";
    private static final String CACHE_KEY_ALL = ipTableDao.class.getName()
                                                      + "_all";
    private static final String CACHE_KEY_ID  = ipTableDao.class.getName()
                                                      + "_id";

    private static final String CACHE_KEY_IP  = ipTableDao.class.getName()
                                                      + "_ip";

    public static String table()
    {
        return TBLPREFIX;
    }

    /*
     * 得到最大ID
     */
    public final int GetMaxId()
    {
        int myMaxId = this.queryForInt("select max(ID) from" + table(), null,
                null);
        return myMaxId;
    }

    /**
     * 是否存在该记录
     */
    public final boolean Exists(int ID)
    {
        int num = this.queryForInt("select count(1) from" + table()
                + " where ID=" + ID, null, null);
        return num > 0 ? true : false;
    }

    /**
     * 增加一条数据
     */
    public final int Add(final IPTABLE model)
    {
        if (model == null)
        {
            return 0;
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append(table());
        sb
                .append(" (`StartIPNum`,`StartIPText`,`EndIPNum`,`EndIPText`, `Country`, `Local`) "
                        + "VALUES (?, ?, ?, ?, ?, ?)");
        final String sql = sb.toString();
        PreparedStatementCreator psc = new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException
            {
                PreparedStatement ps = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                if (model.getStartIPNum() != null)
                {
                    ps.setLong(i++, model.getStartIPNum());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getStartIPText() != null)
                {
                    ps.setString(i++, model.getStartIPText());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getEndIPNum() != null)
                {
                    ps.setLong(i++, model.getEndIPNum());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getEndIPText() != null)
                {
                    ps.setString(i++, model.getEndIPText());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getCountry() != null)
                {
                    ps.setString(i++, model.getCountry());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getLocal() != null)
                {
                    ps.setString(i++, model.getLocal());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                return ps;
            }
        };
        getJdbcTemplate().update(psc, keyHolder);
        int id = keyHolder.getKey().intValue();
        if (id > 0)
        {
            WnsCacheFactory.delete(CACHE_KEY_ALL);
        }
        return id;
    }

    /**
     * 更新一条数据
     */
    public final boolean Update(IPTABLE model)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ");
        sb.append(table());
        sb
                .append(" SET `StartIPNum`=?, `StartIPText`=?, `EndIPNum`=?,`EndIPText`=?,`Country`=?, `Local`=?"
                        + " where `ID`=? ");
        int result = getJdbcTemplate().update(
                sb.toString(),
                new Object[] { model.getStartIPNum(), model.getStartIPText(),
                        model.getEndIPNum(), model.getEndIPText(),
                        model.getCountry(), model.getLocal() },
                new int[] { Types.DECIMAL, Types.VARCHAR, Types.DECIMAL,
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.INTEGER });
        if (result > 0)
        {
            WnsCacheFactory.delete(CACHE_KEY_ALL);
        }
        return result > 0 ? true : false;
    }

    /**
     * 删除一条数据
     */
    public final boolean Delete(int ID)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from " + table() + " ");
        sb.append(" where ID=" + ID + "");
        int result = getJdbcTemplate().update(sb.toString());
        return result > 0 ? true : false;
    }

    /**
     * 批量删除数据
     */
    public final boolean DeleteList(String IDlist)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from " + table());
        sb.append(" where ID in (" + IDlist + ")  ");
        int result = getJdbcTemplate().update(sb.toString());
        return result > 0 ? true : false;
    }

    /**
     * 得到一个对象实体
     */
    @SuppressWarnings("unchecked")
    // / <summary>
    // / 得到一个对象实体
    // / </summary>
    public IPTABLE fingByIp(Long ip)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_IP + ip);
        if (obj != null && obj instanceof IPTABLE)
        {
            return (IPTABLE) obj;
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            sb.append("select * from " + table());
            if (ip != null && (ip > 0))
            {
                sb.append(" where " + ip +" >=StartIPNum and " + ip + "<=EndIPNum");
            }
            sb.append(" LIMIT 1");

            List<IPTABLE> list = query(sb.toString(), null, null,
                    new WnsCommonRowMapper(IPTABLE.class));
            if (list != null && list.size() > 0)
            {
                IPTABLE model = list.get(0);
                if (model != null)
                {
                    WnsCacheFactory.add(CACHE_KEY_IP + ip, model,
                            WnsCacheFactory.ONE_MONTH);
                }
                return model;
            }
            return null;
        }

    }

    @SuppressWarnings("unchecked")
    public final IPTABLE GetModel(int ID)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + ID);
        if (obj != null && obj instanceof IPTABLE)
        {
            return (IPTABLE) obj;
        }
        else
        {
            List<IPTABLE> list = query("select * from " + table()
                    + " where ID=? LIMIT 1", new Object[] { ID },
                    new int[] { Types.INTEGER }, new WnsCommonRowMapper(
                            IPTABLE.class));
            if (list != null && list.size() > 0)
            {
                IPTABLE model = list.get(0);
                if (model != null)
                {
                    WnsCacheFactory.add(CACHE_KEY_ID + ID, model,
                            WnsCacheFactory.ONE_MONTH);
                }
                return model;
            }
            return null;
        }
    }

    /**
     * 获得数据列表
     */
    @SuppressWarnings("unchecked")
    public final List<IPTABLE> GetList(String strWhere)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ALL);
        if (obj != null)
        {
            return (List<IPTABLE>) obj;
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            sb.append("select * from " + table());
            if (WnsStringUtil.isBlank(strWhere))
            {
                sb.append(" where = " + strWhere);
            }
            List<IPTABLE> list = query(sb.toString(), null, null,
                    new WnsCommonRowMapper(IPTABLE.class));
            if (list != null)
            {
                WnsCacheFactory.add(CACHE_KEY_ALL, list,
                        WnsCacheFactory.ONE_MONTH);
            }
            return list;
        }
    }

    /**
     * 获得前几行数据
     */
    @SuppressWarnings("unchecked")
    public final List<IPTABLE> GetList(int Top, String strWhere,
            String filedOrder)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from " + table());
        if (!WnsStringUtil.isBlank(strWhere))
        {
            sb.append(" where = " + strWhere);
        }
        if (!WnsStringUtil.isBlank(filedOrder))
        {
            sb.append(" order by " + filedOrder);
        }
        if (Top > 0)
        {
            sb.append(" limit " + Top);
        }
        List<IPTABLE> list = query(sb.toString(), null,
                null, new WnsCommonRowMapper(IPTABLE.class));
        if (list != null)
        {
            // NgsteamCacheFactory.add(CACHE_KEY_ALL, list,
            // NgsteamCacheFactory.ONE_MONTH);
        }
        return list;
    }

    /**
     * 获取记录总数
     */
    public final int GetRecordCount(String strWhere)
    {
        StringBuilder strSql = new StringBuilder();
        strSql.append("select count(1) FROM " + table() + " ");
        if (!strWhere.trim().equals(""))
        {
            strSql.append(" where " + strWhere);
        }
        int num = this.queryForInt(strSql.toString(), null, null);
        return num;
    }

    /**
     * 分页获取数据列表
     */
    @SuppressWarnings("unchecked")
    public final List<IPTABLE> GetListByPage(
            String strWhere, String orderby, int startIndex, int endIndex)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from " + table());
        if (!WnsStringUtil.isBlank(strWhere))
        {
            sb.append(" where = " + strWhere);
        }
        if (!WnsStringUtil.isBlank(orderby))
        {
            sb.append(" order by " + orderby);
        }
        else
        {
            sb.append(" order by BGM_FUNCTIONID desc");
        }
        sb.append(" limit " + startIndex + "," + endIndex);

        List<IPTABLE> list = query(sb.toString(), null,
                null, new WnsCommonRowMapper(
                        IPTABLE.class));
        if (list != null)
        {
            // NgsteamCacheFactory.add(CACHE_KEY_ALL, list,
            // NgsteamCacheFactory.ONE_MONTH);
        }
        return list;
    }

}
