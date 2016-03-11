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

import com.wns.push.bean.DelCmd;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;
import com.wns.push.util.WnsStringUtil;

public class UninstallationLogDao extends WnsBaseDao
{
    private static final String TBLPREFIX           = "Uninstallation_log";
    private static final String CACHE_KEY_ALL       = UninstallationLogDao.class
                                                            .getName()
                                                            + "_all";
    private static final String CACHE_KEY_ID        = UninstallationLogDao.class
                                                            .getName()
                                                            + "_id";

    private static final String CACHE_KEY_IMEI_IMSI = UninstallationLogDao.class
                                                            .getName()
                                                            + "_imei_imsi";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 得到最大ID
     */
    public final int GetMaxId()
    {
        int myMaxId = this.queryForInt("select max(PUSH_HISTORY_ID) from"
                + table(), null, null);
        return myMaxId;
    }

    /**
     * 是否存在该记录
     */
    public final boolean Exists(int PUSH_HISTORY_ID)
    {
        int num = this.queryForInt("select count(1) from" + table()
                + " where PUSH_HISTORY_ID=" + PUSH_HISTORY_ID, null, null);
        return num > 0 ? true : false;
    }

    /**
     * 增加一条数据
     */
    public final int Add(final DelCmd model)
    {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append(table());
        sb
                .append(" push_record_ID,Apply_ID,app_name,package_name,version,User_id,BeginningDate,FinishDate,PUSH_POLICY_STATUS,state) "
                        + "VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?)");
        final String sql = sb.toString();
        PreparedStatementCreator psc = new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException
            {
                PreparedStatement ps = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                if (model.getpush_record_ID() != null)
                {
                    ps.setInt(i++, model.getpush_record_ID());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getApply_ID() != null)
                {
                    ps.setInt(i++, model.getApply_ID());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }

                if (model.getapp_name() != null)
                {
                    ps.setString(i++, model.getapp_name());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }

                if (model.getpackage_name() != null)
                {
                    ps.setString(i++, model.getpackage_name());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }

                if (model.getversion() != null)
                {
                    ps.setString(i++, model.getversion());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getUser_id() != null)
                {
                    ps.setInt(i++, model.getUser_id());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }

                if (model.getBeginningDate() != null)
                {
                    ps.setDate(i++, new java.sql.Date(model.getBeginningDate()
                            .getTime()));
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }

                if (model.getFinishDate() != null)
                {
                    ps.setDate(i++, new java.sql.Date(model.getFinishDate()
                            .getTime()));
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }

                if (model.getPUSH_POLICY_STATUS() != null)
                {
                    ps.setInt(i++, model.getPUSH_POLICY_STATUS());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }

                if (model.getstate() != null)
                {
                    ps.setString(i++, model.getstate());
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
    public final boolean Update(final com.wns.push.bean.DelCmd model)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ");
        sb.append(table());
        sb
                .append(" SET `push_record_ID`=?,`Apply_ID`=?, `app_name`=?, `package_name`=?,`version`=? "
                        + ",`User_id`=?,`BeginningDate`=?, `FinishDate`=?, `PUSH_POLICY_STATUS`=?,`state`=? "
                        + " where `PUSH_HISTORY_ID`=? ");
        int result = getJdbcTemplate().update(
                sb.toString(),
                new Object[] { model.getpush_record_ID(), model.getApply_ID(),
                        model.getapp_name(), model.getpackage_name(),
                        model.getversion(), model.getUser_id(),
                        model.getBeginningDate(), model.getFinishDate(),
                        model.getPUSH_POLICY_STATUS(), model.getstate() },
                new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER,
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.INTEGER, Types.DATE, Types.DATE, Types.INTEGER,
                        Types.VARCHAR });
        if (result > 0)
        {
            WnsCacheFactory.delete(CACHE_KEY_ALL);
        }
        return result > 0 ? true : false;
    }

    /**
     * 删除一条数据
     */
    public final boolean Delete(int PUSH_HISTORY_ID)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from " + table() + " ");
        sb.append(" where PUSH_HISTORY_ID=" + PUSH_HISTORY_ID + "");
        int result = getJdbcTemplate().update(sb.toString());
        return result > 0 ? true : false;
    } // / <summary>

    /**
     * 批量删除数据
     */
    public final boolean DeleteList(String PUSH_HISTORY_IDlist)
    {

        StringBuilder sb = new StringBuilder();
        sb.append("delete from " + table());
        sb.append(" where PUSH_HISTORY_IDlist in (" + PUSH_HISTORY_IDlist
                + ")  ");
        int result = getJdbcTemplate().update(sb.toString());
        return result > 0 ? true : false;

    }

    /**
     * 得到一个对象实体
     */
    @SuppressWarnings("unchecked")
    public final DelCmd GetModel(int PUSH_HISTORY_ID)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + PUSH_HISTORY_ID);
        if (obj != null && obj instanceof DelCmd)
        {
            return (DelCmd) obj;
        }
        else
        {
            List<DelCmd> list = query("select * from "
                    + table() + " where PUSH_HISTORY_ID=? LIMIT 1",
                    new Object[] { PUSH_HISTORY_ID },
                    new int[] { Types.INTEGER }, new WnsCommonRowMapper(
                            DelCmd.class));
            if (list != null && list.size() > 0)
            {
                DelCmd model = list.get(0);
                if (model != null)
                {
                    WnsCacheFactory.add(CACHE_KEY_ID + PUSH_HISTORY_ID,
                            model, WnsCacheFactory.ONE_MONTH);
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
    public final List<DelCmd> GetList(String strWhere)
    {
        StringBuilder strSql = new StringBuilder();
        strSql
                .append("select PUSH_HISTORY_ID,push_record_ID,Apply_ID,app_name,package_name,version,User_id,BeginningDate,FinishDate,PUSH_POLICY_STATUS,state ");
        strSql.append(" FROM " + table());
        if (!strWhere.trim().equals(""))
        {
            strSql.append(" where " + strWhere);
        }

        List<DelCmd> list = query(strSql.toString(),
                null, null, new WnsCommonRowMapper(
                        DelCmd.class));
        if (list != null)
        {
        }
        return list;
    }

    /**
     * 获得前几行数据
     */
    @SuppressWarnings("unchecked")
    public final List<DelCmd> GetList(int Top,
            String strWhere, String filedOrder)
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

        List<DelCmd> list = query(sb.toString(), null,
                null, new WnsCommonRowMapper(
                        DelCmd.class));
        if (list != null)
        {
        }
        return list;
    }

    /**
     * 获取记录总数
     */
    public final int GetRecordCount(String strWhere)
    {
        StringBuilder strSql = new StringBuilder();
        strSql.append("select count(1) FROM " + table());
        if (!strWhere.trim().equals(""))
        {
            strSql.append(" where " + strWhere);
        }
        return this.getJdbcTemplate().queryForInt(strSql.toString());
    }

    /**
     * 分页获取数据列表
     */
    @SuppressWarnings("unchecked")
    public final List<DelCmd> GetListByPage(
            String strWhere, String orderby, int startIndex, int endIndex)
    {
        StringBuilder strSql = new StringBuilder();
        strSql.append("SELECT * FROM " + table());
        if (!WnsStringUtil.isBlank(strWhere))
        {
            strSql.append(" where = " + strWhere);
        }

        if (!WnsStringUtil.isBlank(orderby))
        {
            strSql.append(" order by " + orderby);
        }

        strSql.append(" limit " + startIndex + "," + endIndex);
        List<DelCmd> list = query(strSql.toString(),
                null, null, new WnsCommonRowMapper(
                        DelCmd.class));
        return list;
    }

    /**
     * 修改状态为0（不显示）
     * 
     * @param PUSH_HISTORY_ID
     * @return
     */
    public final boolean Update_Uninstallation_log(int PUSH_HISTORY_ID)
    {
        StringBuilder sql = new StringBuilder();
        sql.append("update " + table()
                + " set state='0' where state='1' and PUSH_HISTORY_ID="
                + PUSH_HISTORY_ID + " ");
        int rowsAffected = this.getJdbcTemplate().update(sql.toString());
        if (rowsAffected > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // 静默卸载数据下发

    @SuppressWarnings("unchecked")
    public final List<DelCmd> GetList(String imei,
            String imsi, String user_id)
    {
        StringBuilder strSql = new StringBuilder();
        // 多机型数据
        strSql
                .append(" select * from UninstallationLogDao where PUSH_HISTORY_ID not in (select  PUSH_HISTORY_ID  from SilentDischargeDao");
        strSql.append(" where imei='" + imei + "' and imsi='" + imsi
                + "' and PUSH_HISTORY_ID is not null)  ");
        strSql
                .append("and User_id="
                        + user_id
                        + "and  PUSH_POLICY_STATUS=1 and state=1 and getdate() BETWEEN  BeginningDate and  FinishDate");
        List<DelCmd> list = query(strSql.toString(),
                null, null, new WnsCommonRowMapper(
                        DelCmd.class));
        return list;
    }

    @SuppressWarnings("unchecked")
    public final List<DelCmd> GetListadmin(String imei, String imsi)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_IMEI_IMSI + imei + "_"
                + imsi);
        if (obj != null && obj instanceof List)
        {
            return (List<DelCmd>) obj;
        }
        else
        {
            StringBuilder strSql = new StringBuilder();
            strSql.append(" select  * from " + table() + " where ");
            strSql
                    .append("  PUSH_POLICY_STATUS=1 and state=1 and now() BETWEEN  BeginningDate and  FinishDate limit 2");
            List<DelCmd> list = query(strSql.toString(), null, null,
                    new WnsCommonRowMapper(DelCmd.class));
            if (list != null)
            {

                WnsCacheFactory.add(
                        CACHE_KEY_IMEI_IMSI + imei + "_" + imsi, list,
                        WnsCacheFactory.ONE_DAY);
            }
            return list;
        }
    }

}
