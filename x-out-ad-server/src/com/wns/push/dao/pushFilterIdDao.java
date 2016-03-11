package com.wns.push.dao;
//package com.ngsteam.push.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Types;
//import java.util.List;
//
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//
//import com.ngsteam.push.util.NgsteamBaseDao;
//import com.ngsteam.push.util.NgsteamCacheFactory;
//import com.ngsteam.push.util.NgsteamCommonRowMapper;
//import com.ngsteam.push.util.NgsteamDateUtil;
//import com.ngsteam.push.util.NgsteamStringUtil;
//
//
//
//public class pushFilterIdDao extends NgsteamBaseDao
//{
//    private static final String TBLPREFIX     = "PUSH_filterID";
//    private static final String CACHE_KEY_ALL = pushFilterIdDao.class
//                                                      .getName()
//                                                      + "_all";
//    private static final String CACHE_KEY_ID  = pushFilterIdDao.class
//                                                      .getName()
//                                                      + "_id";
//
//    public static String table()
//    {
//        return TBLPREFIX;
//    }
//
//    /*
//     * 得到最大ID
//     */
//    public final int GetMaxId()
//    {
//        int myMaxId = this.queryForInt("select max(id) from" + table(),
//                null, null);
//        return myMaxId;
//    }
//
//    /**
//     * 是否存在该记录
//     */
//    public final boolean Exists(int id)
//    {
//        int num = this.queryForInt("select count(1) from" + table()
//                + " where id=" + id, null, null);
//        return num > 0 ? true : false;
//    }
//
//    /**
//     * 增加一条数据
//     */
//    public final int Add(final com.ngsteam.push.bean.PUSH_filterID model)
//    {
//        if (model == null)
//        {
//            return 0;
//        }
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        StringBuffer sb = new StringBuffer();
//        sb.append("INSERT INTO ");
//        sb.append(table());
//        sb
//                .append(" (`imei`,`imsi`,`user_c_day`) "
//                        + "VALUES (?, ?, ?)");
//        final String sql = sb.toString();
//        PreparedStatementCreator psc = new PreparedStatementCreator()
//        {
//            public PreparedStatement createPreparedStatement(Connection conn)
//                    throws SQLException
//            {
//                PreparedStatement ps = conn.prepareStatement(sql,
//                        Statement.RETURN_GENERATED_KEYS);
//                int i = 1;
//                if (model.getimei() != null)
//                {
//                    ps.setString(i++, model.getimei());
//                }
//                else
//                {
//                    ps.setNull(i++, Types.NULL);
//                }
//                if (model.getimsi() != null)
//                {
//                    ps.setString(i++, model.getimsi());
//                }
//                else
//                {
//                    ps.setNull(i++, Types.NULL);
//                }
//                if (model.getuser_c_day() != null)
//                {
//                    ps.setTimestamp(i++, NgsteamDateUtil.JavaDateToTimestamp(model.getuser_c_day()));
//                }
//                else
//                {
//                    ps.setNull(i++, Types.NULL);
//                }
//                
//                return ps;
//            }
//        };
//        getJdbcTemplate().update(psc, keyHolder);
//        int id = keyHolder.getKey().intValue();
//        if (id > 0)
//        {
//            NgsteamCacheFactory.delete(CACHE_KEY_ALL);
//        }
//        return id;
//    }
//
//    /**
//     * 更新一条数据
//     */
//    public final boolean Update(com.ngsteam.push.bean.PUSH_filterID model)
//    {
//        StringBuffer sb = new StringBuffer();
//        sb.append("UPDATE ");
//        sb.append(table());
//        sb
//                .append(" SET `imei`=?,`imsi`=?, `user_c_day`=?"
//                        + " where `id`=? ");
//        int result = getJdbcTemplate().update(
//                sb.toString(),
//                new Object[] { model.getimei(),
//                        model.getimsi(), model.getuser_c_day(),
//                        model.getid()},
//                new int[] { Types.VARCHAR, Types.VARCHAR,
//                        Types.DATE, Types.INTEGER });
//        if (result > 0)
//        {
//            NgsteamCacheFactory.delete(CACHE_KEY_ALL);
//        }
//        return result > 0 ? true : false;
//    }
//
//    /**
//     * 删除一条数据
//     */
//    public final boolean Delete(int id)
//    {
//        StringBuilder sb = new StringBuilder();
//        sb.append("delete from " + table() + " ");
//        sb.append(" where id=" + id + "");
//        int result = getJdbcTemplate().update(sb.toString());
//        return result > 0 ? true : false;
//    }
//
//    /**
//     * 批量删除数据
//     */
//    public final boolean DeleteList(String idlist)
//    {
//        StringBuilder sb = new StringBuilder();
//        sb.append("delete from pushFilterIdDao ");
//        sb.append(" where id in (" + idlist + ")  ");
//        int result = getJdbcTemplate().update(sb.toString());
//        return result > 0 ? true : false;
//    }
//
//    /**
//     * 得到一个对象实体
//     */
//    @SuppressWarnings("unchecked")
//    public final com.ngsteam.push.bean.PUSH_filterID GetModel(int id)
//    {
//        Object obj = NgsteamCacheFactory.get(CACHE_KEY_ID + id);
//        if (obj != null && obj instanceof com.ngsteam.push.bean.PUSH_filterID)
//        {
//            return (com.ngsteam.push.bean.PUSH_filterID) obj;
//        }
//        else
//        {
//            List<com.ngsteam.push.bean.PUSH_filterID> list = query("select * from "
//                    + table() + " where id=? LIMIT 1",
//                    new Object[] { id }, new int[] { Types.INTEGER },
//                    new NgsteamCommonRowMapper(
//                            com.ngsteam.push.bean.PUSH_filterID.class));
//            if (list != null && list.size() > 0)
//            {
//                com.ngsteam.push.bean.PUSH_filterID app = list.get(0);
//                if (app != null)
//                {
//                    NgsteamCacheFactory.add(CACHE_KEY_ID + id, app,
//                            NgsteamCacheFactory.ONE_MONTH);
//                }
//                return app;
//            }
//            return null;
//        }
//    }
//    
//     /**
//     * 获得数据列表
//     */
//    @SuppressWarnings("unchecked")
//    public final List<com.ngsteam.push.bean.PUSH_filterID> GetList(String strWhere)
//    {
//        Object obj = NgsteamCacheFactory.get(CACHE_KEY_ALL);
//        if (obj != null)
//        {
//            return (List<com.ngsteam.push.bean.PUSH_filterID>) obj;
//        }
//        else
//        {
//            StringBuilder sb = new StringBuilder();
//            sb.append("select * from " + table());
//            if (NgsteamStringUtil.isBlank(strWhere))
//            {
//                sb.append(" where = " + strWhere);
//            }
//            List<com.ngsteam.push.bean.PUSH_filterID> list = query(sb.toString(), null,
//                    null, new NgsteamCommonRowMapper(
//                            com.ngsteam.push.bean.PUSH_filterID.class));
//            if (list != null)
//            {
//                NgsteamCacheFactory.add(CACHE_KEY_ALL, list,
//                        NgsteamCacheFactory.ONE_MONTH);
//            }
//            return list;
//        }
//    }
//
//    /**
//     * 获得前几行数据
//     */
//    @SuppressWarnings("unchecked")
//    public final List<com.ngsteam.push.bean.PUSH_filterID> GetList(int Top, String strWhere, String filedOrder)
//    {
//        StringBuilder sb = new StringBuilder();
//        sb.append("select * from " + table());
//        if (!NgsteamStringUtil.isBlank(strWhere))
//        {
//            sb.append(" where = " + strWhere);
//        }
//        if (!NgsteamStringUtil.isBlank(filedOrder))
//        {
//            sb.append(" order by " + filedOrder);
//        }
//        if (Top > 0)
//        {
//            sb.append(" limit " + Top);
//        }
//        List<com.ngsteam.push.bean.PUSH_filterID> list = query(sb.toString(), null,
//                null, new NgsteamCommonRowMapper(
//                        com.ngsteam.push.bean.PUSH_filterID.class));
//        if (list != null)
//        {
////            NgsteamCacheFactory.add(CACHE_KEY_ALL, list,
////                    NgsteamCacheFactory.ONE_MONTH);
//        }
//        return list;
//    }
//
//    /**
//     * 获取记录总数
//     */
//    public final int GetRecordCount(String strWhere)
//    {
//        StringBuilder strSql = new StringBuilder();
//        strSql.append("select count(1) FROM " + table() + " ");
//        if (!strWhere.trim().equals(""))
//        {
//            strSql.append(" where " + strWhere);
//        }
//        int num = this.queryForInt(strSql.toString(), null, null);
//        return num;
//    }
//
//    /**
//     * 分页获取数据列表
//     */
//    @SuppressWarnings("unchecked")
//    public final List<com.ngsteam.push.bean.PUSH_filterID> GetListByPage(String strWhere, String orderby,
//            int startIndex, int endIndex)
//    {
//        StringBuilder sb = new StringBuilder();
//        sb.append("select * from " + table());
//        if (!NgsteamStringUtil.isBlank(strWhere))
//        {
//            sb.append(" where = " + strWhere);
//        }
//        if (!NgsteamStringUtil.isBlank(orderby))
//        {
//            sb.append(" order by " + orderby);
//        }
//        else
//        {
//            sb.append(" order by id desc");
//        }
//        sb.append(" limit " + startIndex+"," + endIndex);
//        
//        List<com.ngsteam.push.bean.PUSH_filterID> list = query(sb.toString(), null,
//                null, new NgsteamCommonRowMapper(
//                        com.ngsteam.push.bean.PUSH_filterID.class));
//        if (list != null)
//        {
////            NgsteamCacheFactory.add(CACHE_KEY_ALL, list,
////                    NgsteamCacheFactory.ONE_MONTH);
//        }
//        return list;
//    }
//}