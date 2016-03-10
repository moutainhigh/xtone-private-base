package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.wns.push.bean.push_policy;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;
import com.wns.push.util.WnsDateUtil;
import com.wns.push.util.WnsHttp;
import com.wns.push.util.WnsStringUtil;

public class pushPolicyDao extends WnsBaseDao
{
    private static final String TBLPREFIX      = "push_policy";
    private static final String CACHE_KEY_ALL  = pushPolicyDao.class.getName()
                                                       + "_all";
    private static final String CACHE_KEY_ID   = pushPolicyDao.class.getName()
                                                       + "_id";
    private static final String CACHE_KEY_DATE = pushPolicyDao.class.getName()
                                                       + "_date";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 增加一条数据
     */
    public final int Insert(final push_policy model)
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
                .append(" (`type`,`subtype`, `res_id`, `content`, `updatedate`, `begintime`, "
                        + "`endtime`, `model`, `push_status`, `title`, `link`, "
                        + "`all_push`, `network`, `channel`, `open`,`closeh_nam`,`cesu`,"
                        + "`state`, `sex`, `age`, `size`, `area`, "
                        + "`img_link`, `downcount`, `close`, `name`, `res_loc`,"
                        + "`rom`, `down_direct`, `channelname`,`canal_switch`, `push_count`,`weight`, `status`) "
                        + "VALUES (?, ?, ?, ?, ?, ?,"
                        + "?, ?, ?, ?, ?, ?,"
                        + "?, ?, ?, ?, ?,"
                        + "?, ?, ?, ?, ?, "
                        + "?, ?, ?, ?, ?," + "?, ?, ?, ?, ?, ?, ?)");
        final String sql = sb.toString();
        PreparedStatementCreator psc = new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException
            {
                PreparedStatement ps = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                if (model.getType() != null)
                {
                    ps.setInt(i++, model.getType());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getSubtype() != null)
                {
                    ps.setInt(i++, model.getSubtype());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getRes_id() != null)
                {
                    ps.setInt(i++, model.getRes_id());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getContent() != null)
                {
                    ps.setString(i++, model.getContent());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getUpdatedate() != null)
                {
                    ps.setTimestamp(i++, WnsDateUtil
                            .JavaDateToTimestamp(model.getUpdatedate()));
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getBegintime() != null)
                {
                    ps.setTimestamp(i++, WnsDateUtil
                            .JavaDateToTimestamp(model.getBegintime()));
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getEndtime() != null)
                {
                    ps.setTimestamp(i++, WnsDateUtil
                            .JavaDateToTimestamp(model.getEndtime()));
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getModel() != null)
                {
                    ps.setString(i++, model.getModel());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getStatus() != null)
                {
                    ps.setInt(i++, model.getStatus());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getTitle() != null)
                {
                    ps.setString(i++, model.getTitle());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getLink() != null)
                {
                    ps.setString(i++, model.getLink());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getAll_push() != null)
                {
                    ps.setBoolean(i++, model.getAll_push());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getNetwork() != null)
                {
                    ps.setInt(i++, model.getNetwork());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getChannel() != null)
                {
                    ps.setString(i++, model.getChannel());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getOpen() != null)
                {
                    ps.setString(i++, model.getOpen());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getCloseh_nam() != null)
                {
                    ps.setString(i++, model.getCloseh_nam());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getCesu() != null)
                {
                    ps.setString(i++, model.getCesu());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getState() != null)
                {
                    ps.setString(i++, model.getState());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getSex() != null)
                {
                    ps.setString(i++, model.getSex());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getAge() != null)
                {
                    ps.setString(i++, model.getAge());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getSize() != null)
                {
                    ps.setString(i++, model.getSize());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getArea() != null)
                {
                    ps.setString(i++, model.getArea());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getImg_link() != null)
                {
                    ps.setString(i++, model.getImg_link());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getDowncount() != null)
                {
                    ps.setInt(i++, model.getDowncount());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getClose() != null)
                {
                    ps.setString(i++, model.getClose());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getName() != null)
                {
                    ps.setString(i++, model.getName());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getRes_loc() != null)
                {
                    ps.setInt(i++, model.getRes_loc());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getRom() != null)
                {
                    ps.setString(i++, model.getRom());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getDown_direct() != null)
                {
                    ps.setInt(i++, model.getDown_direct());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getChannelname() != null)
                {
                    ps.setString(i++, model.getChannelname());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getCanal_switch() != null)
                {
                    ps.setInt(i++, model.getCanal_switch());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getPush_count() != null)
                {
                    ps.setInt(i++, model.getPush_count());
                }
                else
                {
                    ps.setInt(i++, 10000);
                }
                if (model.getWeight() != null)
                {
                    ps.setInt(i++, model.getWeight());
                }
                else
                {
                    ps.setInt(i++, 100);
                }
                if (model.getStatus() != null)
                {
                    ps.setInt(i++, model.getStatus());
                }
                else
                {
                    ps.setInt(i++, 0);
                }
                return ps;
            }
        };
        getJdbcTemplate().update(psc, keyHolder);
        int id = keyHolder.getKey().intValue();
        if (id > 0)
        {
            WnsCacheFactory.delete(CACHE_KEY_ALL);
            WnsHttp.sendCleanTask();
        }

        return id;
    }

    /**
     * 更新一条数据
     */
    public final boolean Update(final push_policy model)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ");
        sb.append(table());
        sb
                .append(" SET `type`=?, `subtype`=?, `res_id`=?, `content` = ?, `updatedate`=?, `begintime`=?, "
                        + "`endtime`=?, `model`=?,`push_status`=?, `title`=?, `link`=?, "
                        + "`all_push`=?, `network`=?, `channel`=?, `open`=?, `closeh_nam`=?, `cesu`=?,"
                        + "`state`=?, `sex`=?, `age`=?, `size`=?, `area`=?,"
                        + "`img_link`=?, `downcount`=?, `close`=?, `name`=?, `res_loc`=?,"
                        + "`rom`=?, `down_direct`=?, `channelname`=?,`canal_switch`=?,`push_count`=?, `weight`=?,"
                        + "`status`=?  WHERE `id`=?");
        PreparedStatementSetter psc = new PreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps) throws SQLException
            {
                int i = 1;
                if (model.getType() != null)
                {
                    ps.setInt(i++, model.getType());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getSubtype() != null)
                {
                    ps.setInt(i++, model.getSubtype());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getRes_id() != null)
                {
                    ps.setInt(i++, model.getRes_id());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getContent() != null)
                {
                    ps.setString(i++, model.getContent());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getUpdatedate() != null)
                {
                    ps.setTimestamp(i++, WnsDateUtil
                            .JavaDateToTimestamp(model.getUpdatedate()));
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getBegintime() != null)
                {
                    ps.setTimestamp(i++, WnsDateUtil
                            .JavaDateToTimestamp(model.getBegintime()));
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getEndtime() != null)
                {
                    ps.setTimestamp(i++, WnsDateUtil
                            .JavaDateToTimestamp(model.getEndtime()));
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getModel() != null)
                {
                    ps.setString(i++, model.getModel());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getStatus() != null)
                {
                    ps.setInt(i++, model.getStatus());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getTitle() != null)
                {
                    ps.setString(i++, model.getTitle());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getLink() != null)
                {
                    ps.setString(i++, model.getLink());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getAll_push() != null)
                {
                    ps.setBoolean(i++, model.getAll_push());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getNetwork() != null)
                {
                    ps.setInt(i++, model.getNetwork());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getChannel() != null)
                {
                    ps.setString(i++, model.getChannel());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getOpen() != null)
                {
                    ps.setString(i++, model.getOpen());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getCloseh_nam() != null)
                {
                    ps.setString(i++, model.getCloseh_nam());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getCesu() != null)
                {
                    ps.setString(i++, model.getCesu());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getState() != null)
                {
                    ps.setString(i++, model.getState());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getSex() != null)
                {
                    ps.setString(i++, model.getSex());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getAge() != null)
                {
                    ps.setString(i++, model.getAge());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getSize() != null)
                {
                    ps.setString(i++, model.getSize());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getArea() != null)
                {
                    ps.setString(i++, model.getArea());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getImg_link() != null)
                {
                    ps.setString(i++, model.getImg_link());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getDowncount() != null)
                {
                    ps.setInt(i++, model.getDowncount());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getClose() != null)
                {
                    ps.setString(i++, model.getClose());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getName() != null)
                {
                    ps.setString(i++, model.getName());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getRes_loc() != null)
                {
                    ps.setInt(i++, model.getRes_loc());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getRom() != null)
                {
                    ps.setString(i++, model.getRom());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getDown_direct() != null)
                {
                    ps.setInt(i++, model.getDown_direct());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getChannelname() != null)
                {
                    ps.setString(i++, model.getChannelname());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getCanal_switch() != null)
                {
                    ps.setInt(i++, model.getCanal_switch());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (model.getPush_count() != null)
                {
                    ps.setInt(i++, model.getPush_count());
                }
                else
                {
                    ps.setInt(i++, 10000);
                }
                if (model.getWeight() != null)
                {
                    ps.setInt(i++, model.getWeight());
                }
                else
                {
                    ps.setInt(i++, 100);
                }
                if (model.getStatus() != null)
                {
                    ps.setInt(i++, model.getStatus());
                }
                else
                {
                    ps.setInt(i++, 0);
                }
                ps.setLong(i++, model.getId());
            }
        };
        int id = getJdbcTemplate().update(sb.toString(), psc);
        if (id > 0)
        {
            WnsCacheFactory.delete(CACHE_KEY_ALL);
            WnsCacheFactory.delete(CACHE_KEY_ID + model.getId());
            WnsHttp.sendCleanTask();
        }
        return id > 0 ? true : false;
    }

    /**
     * 得到一个对象实体
     */
    @SuppressWarnings("unchecked")
    public final push_policy findSingle(int id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
        if (obj != null && obj instanceof push_policy)
        {
            return (push_policy) obj;
        }
        else
        {
            List<push_policy> list = query("select * from " + table()
                    + " where id=? and status = 0 LIMIT 1",
                    new Object[] { id }, new int[] { Types.INTEGER },
                    new WnsCommonRowMapper(push_policy.class));
            if (list != null && list.size() > 0)
            {
                push_policy app = list.get(0);
                if (app != null)
                {
                    WnsCacheFactory.add(CACHE_KEY_ID + id, app,
                            WnsCacheFactory.ONE_MONTH);
                }
                return app;
            }
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public final push_policy findSingleByStatis(int id)
    {
        // Object obj = NgsteamCacheFactory.get(CACHE_KEY_ID + PUSH_POLICY_ID);
        // if (obj != null && obj instanceof push_policy)
        // {
        // return (push_policy) obj;
        // }
        // else
        // {
        List<push_policy> list = query("select * from " + table()
                + " where id=? LIMIT 1", new Object[] { id },
                new int[] { Types.INTEGER }, new WnsCommonRowMapper(
                        push_policy.class));
        if (list != null && list.size() > 0)
        {
            push_policy app = list.get(0);
            // if (app != null)
            // {
            // NgsteamCacheFactory.add(CACHE_KEY_ID + PUSH_POLICY_ID, app,
            // NgsteamCacheFactory.ONE_MONTH);
            // }
            return app;
        }
        return null;
        // }
    }

    /**
     * 分页获取数据列表
     */
    @SuppressWarnings("unchecked")
    public final List<push_policy> findPushByType(Integer type)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from " + table());
        sb.append(" where type = " + type);
        sb.append(" and status = 0 order by id desc");

        List<push_policy> list = query(sb.toString(), null, null,
                new WnsCommonRowMapper(push_policy.class));
        if (list != null)
        {
            // NgsteamCacheFactory.add(CACHE_KEY_ALL, list,
            // NgsteamCacheFactory.ONE_MONTH);
        }
        return list;
    }

    /**
     * 分页获取数据列表
     */
    @SuppressWarnings("unchecked")
    public final List<push_policy> findPushByType(String start, String end,
            String channel, Integer type)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from " + table());
        sb.append(" where type = " + type);
        sb.append(" and begintime < '" + end + "' and endtime > '" + start
                + "'");
        if ((channel == null) || WnsStringUtil.isBlank(channel))
        {
            sb.append(" and status = 0 order by id desc ");
        }
        else
        {
            sb.append(" and channel = '" + channel
                    + "' and status = 0 order by id desc ");
        }
        // sb.append(pager.getStartRow());
        // sb.append(", " + pager.getPageSize());

        List<push_policy> list = query(sb.toString(), null, null,
                new WnsCommonRowMapper(push_policy.class));
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
    public final int findPushCountByType(String channel, Integer type)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(1) from " + table());
        if ((channel == null) || WnsStringUtil.isBlank(channel))
        {
            sb.append(" where type = " + type);
        }
        else
        {
            sb.append(" where channel ='" + channel + "' and type = " + type);
        }
        sb.append(" and status = 0  ");

        int num = this.queryForInt(sb.toString(), null, null);
        return num;
    }

    /**
     * 获得前几行数据
     */
    @SuppressWarnings("unchecked")
    public final List<push_policy> findAllByTime(String startTime,
            String endTime)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from " + table());
        sb.append(" where endtime > '" + startTime + "' and begintime < '"
                + endTime);
        sb.append("' and status = 0");
        System.out.println("findAllByTime.sql==="+sb.toString());
        List<push_policy> list = query(sb.toString(), null, null,
                new WnsCommonRowMapper(push_policy.class));
        // if (list != null)
        // {
        // // NgsteamCacheFactory.add(CACHE_KEY_ALL, list,
        // // NgsteamCacheFactory.ONE_MONTH);
        // }
        return list;
    }

    @SuppressWarnings("unchecked")
    public final List<push_policy> findAllByDate(String start, String end)
    {
        Object obj = WnsCacheFactory
                .get(CACHE_KEY_DATE + start + "_" + end);
        if (obj != null && obj instanceof List)
        {
            return (List<push_policy>) obj;
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            sb.append("select * from " + table());
            sb.append(" where endtime > '" + start + "' and begintime < '"
                    + end);
            sb.append("' ");
            List<push_policy> list = query(sb.toString(), null, null,
                    new WnsCommonRowMapper(push_policy.class));
            if (list != null)
            {
                WnsCacheFactory.add(CACHE_KEY_DATE + start + "_" + end,
                        list, WnsCacheFactory.ONE_MONTH);
            }
            return list;
        }
    }
}
