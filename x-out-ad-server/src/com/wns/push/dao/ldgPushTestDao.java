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

import com.wns.push.bean.PushTestPhone;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;

public class ldgPushTestDao extends WnsBaseDao
{
    private static final String TBLPREFIX               = "ldg_PUSH_test";
    private static final String CACHE_KEY_ALL           = ldgPushTestDao.class
                                                                .getName()
                                                                + "_all";
    private static final String CACHE_KEY_ID            = ldgPushTestDao.class
                                                                .getName()
                                                                + "_id";

    private static final String CACHE_KEY_CHANNEL_COUNT = ldgPushTestDao.class
                                                                .getName()
                                                                + "_channel_count";

    private static final String CACHE_KEY_CHANNEL       = ldgPushTestDao.class
                                                                .getName()
                                                                + "_channel";
    private static final String CACHE_KEY_IMSI_IMEI     = ldgPushTestDao.class
                                                                .getName()
                                                                + "_imsi_imei";

    public static String table()
    {
        return TBLPREFIX;
    }

    /*
     * 得到最大ID
     */
    public final int GetMaxId()
    {
        int myMaxId = this.queryForInt("select max(id) from" + table(), null,
                null);
        return myMaxId;
    }

    /**
     * 是否存在该记录
     */
    public final boolean Exists(int id)
    {
        int num = this.queryForInt("select count(1) from" + table()
                + " where id=" + id, null, null);
        return num > 0 ? true : false;
    }

    /**
     * 增加一条数据
     */
    public final int Add(final PushTestPhone model)
    {
        if (model == null)
        {
            return 0;
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append(table());
        sb.append(" (`IMSI`,`IMEI`,`User_id`) " + "VALUES (?, ?, ?)");
        final String sql = sb.toString();
        PreparedStatementCreator psc = new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException
            {
                PreparedStatement ps = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                if (model.getIMSI() != null)
                {
                    ps.setString(i++, model.getIMSI());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getIMEI() != null)
                {
                    ps.setString(i++, model.getIMEI());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getUser_id() != null)
                {
                    ps.setString(i++, model.getUser_id());
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
            WnsCacheFactory.delete(CACHE_KEY_CHANNEL_COUNT
                    + model.getChannel());
            WnsCacheFactory.delete(CACHE_KEY_CHANNEL + model.getChannel());
        }
        return id;
    }

    // /**
    // * 更新一条数据
    // */
    // public final boolean Update(PushTestPhone model)
    // {
    // StringBuffer sb = new StringBuffer();
    // sb.append("UPDATE ");
    // sb.append(table());
    // sb.append(" SET `IMSI`=?,`IMEI`=?, `User_id`=?" + " where `id`=? ");
    // int result = getJdbcTemplate().update(
    // sb.toString(),
    // new Object[] { model.getIMSI(), model.getIMEI(),
    // model.getUser_id(), model.getid() },
    // new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
    // Types.INTEGER });
    // if (result > 0)
    // {
    // NgsteamCacheFactory.delete(CACHE_KEY_ALL);
    // }
    // return result > 0 ? true : false;
    // }

    // /**
    // * 删除一条数据
    // */
    // public final boolean Delete(int id)
    // {
    // StringBuilder sb = new StringBuilder();
    // sb.append("delete from " + table() + " ");
    // sb.append(" where id=" + id + "");
    // int result = getJdbcTemplate().update(sb.toString());
    // return result > 0 ? true : false;
    // }
    //
    // /**
    // * 批量删除数据
    // */
    // public final boolean DeleteList(String IDlist)
    // {
    // StringBuilder sb = new StringBuilder();
    // sb.append("delete from " + table());
    // sb.append(" where id in (" + IDlist + ")  ");
    // int result = getJdbcTemplate().update(sb.toString());
    // return result > 0 ? true : false;
    // }

    /**
     * 得到一个对象实体
     */
    @SuppressWarnings("unchecked")
    public final Integer findChannelCount(String channel)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_CHANNEL_COUNT + channel);
        if (obj != null && obj instanceof Integer)
        {
            return (Integer) obj;
        }
        else
        {
            List<PushTestPhone> list = query("select * from " + table()
                    + " where User_id=?", new Object[] { channel },
                    new int[] { Types.VARCHAR }, new WnsCommonRowMapper(
                            PushTestPhone.class));
            Integer count = list.size();
            WnsCacheFactory.add(CACHE_KEY_CHANNEL_COUNT + channel, count,
                    WnsCacheFactory.ONE_MONTH);
            return count;
        }
    }

    /**
     * 得到一个对象实体
     */
    @SuppressWarnings("unchecked")
    public final List<PushTestPhone> findAllChannel(String channel)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_CHANNEL + channel);
        if (obj != null && obj instanceof List)
        {
            return (List<PushTestPhone>) obj;
        }
        else
        {
            List<PushTestPhone> list = query("select * from " + table()
                    + " where User_id=?", new Object[] { channel },
                    new int[] { Types.VARCHAR }, new WnsCommonRowMapper(
                            PushTestPhone.class));
            if ((list != null) && list.size() > 0)
            {
                WnsCacheFactory.add(CACHE_KEY_CHANNEL + channel, list,
                        WnsCacheFactory.ONE_MONTH);
                return list;
            }
            return null;
        }
    }

    /**
     * 得到一个对象实体
     */
    @SuppressWarnings("unchecked")
    public final PushTestPhone findByImsiImei(String imsi, String imei)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_IMSI_IMEI + imsi + "_"
                + imei);
        if (obj != null && obj instanceof PushTestPhone)
        {
            return (PushTestPhone) obj;
        }
        else
        {
            List<PushTestPhone> list = query("select * from " + table()
                    + " where IMSI=? and IMEI = ? LIMIT 1", new Object[] {
                    imsi, imei }, new int[] { Types.VARCHAR, Types.VARCHAR },
                    new WnsCommonRowMapper(PushTestPhone.class));
            if (list != null && list.size() > 0)
            {
                PushTestPhone model = list.get(0);
                if (model != null)
                {
                    WnsCacheFactory.add(CACHE_KEY_IMSI_IMEI + imsi + "_"
                            + imei, model, WnsCacheFactory.ONE_MONTH);
                }
                return model;
            }
            return null;
        }
    }

    /**
     * 得到一个对象实体
     */
    @SuppressWarnings("unchecked")
    public final PushTestPhone GetModel(int id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
        if (obj != null && obj instanceof com.wns.push.bean.PushTestPhone)
        {
            return (PushTestPhone) obj;
        }
        else
        {
            List<PushTestPhone> list = query("select * from " + table()
                    + " where id=? LIMIT 1", new Object[] { id },
                    new int[] { Types.INTEGER }, new WnsCommonRowMapper(
                            com.wns.push.bean.PushTestPhone.class));
            if (list != null && list.size() > 0)
            {
                PushTestPhone model = list.get(0);
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

    // /**
    // * 获得数据列表
    // */
    // @SuppressWarnings("unchecked")
    // public final List<PushTestPhone> GetList(String strWhere)
    // {
    // Object obj = NgsteamCacheFactory.get(CACHE_KEY_ALL);
    // if (obj != null)
    // {
    // return (List<PushTestPhone>) obj;
    // }
    // else
    // {
    // StringBuilder sb = new StringBuilder();
    // sb.append("select * from " + table());
    // if (NgsteamStringUtil.isBlank(strWhere))
    // {
    // sb.append(" where = " + strWhere);
    // }
    // List<com.ngsteam.push.bean.PushTestPhone> list = query(sb
    // .toString(), null, null, new NgsteamCommonRowMapper(
    // com.ngsteam.push.bean.PushTestPhone.class));
    // if (list != null)
    // {
    // NgsteamCacheFactory.add(CACHE_KEY_ALL, list,
    // NgsteamCacheFactory.ONE_MONTH);
    // }
    // return list;
    // }
    // }
    //
    // /**
    // * 获得前几行数据
    // */
    // @SuppressWarnings("unchecked")
    // public final List<com.ngsteam.push.bean.PushTestPhone> GetList(int Top,
    // String strWhere, String filedOrder)
    // {
    // StringBuilder sb = new StringBuilder();
    // sb.append("select * from " + table());
    // if (!NgsteamStringUtil.isBlank(strWhere))
    // {
    // sb.append(" where = " + strWhere);
    // }
    // if (!NgsteamStringUtil.isBlank(filedOrder))
    // {
    // sb.append(" order by " + filedOrder);
    // }
    // if (Top > 0)
    // {
    // sb.append(" limit " + Top);
    // }
    // List<com.ngsteam.push.bean.PushTestPhone> list = query(sb.toString(),
    // null, null, new NgsteamCommonRowMapper(
    // com.ngsteam.push.bean.PushTestPhone.class));
    // if (list != null)
    // {
    // // NgsteamCacheFactory.add(CACHE_KEY_ALL, list,
    // // NgsteamCacheFactory.ONE_MONTH);
    // }
    // return list;
    // }
    //
    // /**
    // * 获取记录总数
    // */
    // public final int GetRecordCount(String strWhere)
    // {
    // StringBuilder strSql = new StringBuilder();
    // strSql.append("select count(1) FROM " + table() + " ");
    // if (!strWhere.trim().equals(""))
    // {
    // strSql.append(" where " + strWhere);
    // }
    // int num = this.queryForInt(strSql.toString(), null, null);
    // return num;
    // }
    //
    // /**
    // * 分页获取数据列表
    // */
    // @SuppressWarnings("unchecked")
    // public final List<com.ngsteam.push.bean.PushTestPhone> GetListByPage(
    // String strWhere, String orderby, int startIndex, int endIndex)
    // {
    // StringBuilder sb = new StringBuilder();
    // sb.append("select * from " + table());
    // if (!NgsteamStringUtil.isBlank(strWhere))
    // {
    // sb.append(" where = " + strWhere);
    // }
    // if (!NgsteamStringUtil.isBlank(orderby))
    // {
    // sb.append(" order by " + orderby);
    // }
    // else
    // {
    // sb.append(" order by BGM_FUNCTIONID desc");
    // }
    // sb.append(" limit " + startIndex + "," + endIndex);
    //
    // List<com.ngsteam.push.bean.PushTestPhone> list = query(sb.toString(),
    // null, null, new NgsteamCommonRowMapper(
    // com.ngsteam.push.bean.PushTestPhone.class));
    // if (list != null)
    // {
    // // NgsteamCacheFactory.add(CACHE_KEY_ALL, list,
    // // NgsteamCacheFactory.ONE_MONTH);
    // }
    // return list;
    // }

    /**
     * 是否存在该记录
     */
    public final boolean Exists(String strWhere)
    {
        int num = this.queryForInt("select count(1) from " + table()
                + " where " + strWhere, null, null);
        return num > 0 ? true : false;
    }

    /**
     * 是否存在该记录
     * 
     * @param strWhere
     * @param user_id
     * @return
     */
    public final boolean Exists(String strWhere, String user_id)
    {
        int num = this.queryForInt("select count(1) from" + table() + " where "
                + strWhere + " and User_id=" + user_id, null, null);
        return num > 0 ? true : false;
    }
}
