package com.wns.push.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.mysql.jdbc.Statement;
import com.wns.push.bean.LibItem;
import com.wns.push.bean.LibItemChannel;
import com.wns.push.util.WnsBaseDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns.push.util.WnsCommonRowMapper;

public class LibDao extends WnsBaseDao
{
    private static final String TBLPREFIX           = "libs";
    private static final String CACHE_KEY_ALL       = LibDao.class.getName()
                                                            + "_all";
    private static final String CACHE_KEY_ID        = LibDao.class.getName()
                                                            + "_id";
    private static final String CACHE_KEY_NAME      = LibDao.class.getName()
                                                            + "_name";

    private static final String CACHE_KEY_GROUPNAME = LibDao.class.getName()
                                                            + "_groupname";

    public static String table()
    {
        return TBLPREFIX;
    }

    /**
     * 插入
     */
    public int insert(final LibItem item)
    {
        if (item == null)
        {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append(table());
        sb
                .append(" (`name`,`version`,`versioncode`, `crc32`, `url`,`channel`,`phonetype`, `status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
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
                if (item.getVersion() != null)
                {
                    ps.setString(i++, item.getVersion());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getVersioncode() != null)
                {
                    ps.setInt(i++, item.getVersioncode());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getCrc32() != null)
                {
                    ps.setString(i++, item.getCrc32());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getUrl() != null)
                {
                    ps.setString(i++, item.getUrl());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getChannel() != null)
                {
                    ps.setString(i++, item.getChannel());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getPhonetype() != null)
                {
                    ps.setString(i++, item.getPhonetype());
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
            WnsCacheFactory.delete(CACHE_KEY_GROUPNAME);
            WnsCacheFactory.delete(CACHE_KEY_NAME + item.getName() + "_"
                    + item.getChannel());
            if ("-1".equals(item.getChannel()))
            {
            	//将所以渠道的cache清除
            	List<LibItemChannel> channels = findAllChannel();
            	if (channels != null){
            		for (int i=0; i<channels.size(); i++){
            			String channel = channels.get(i).getChannel();
                        WnsCacheFactory.delete(CACHE_KEY_NAME + item.getName() + "_"
                                + channel);
            		}
            	}
            }
        }
        return id;
    }

    /**
     * 更新
     */
    public void update(final LibItem item)
    {
        if (item == null)
        {
            return;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ");
        sb.append(table());
        sb
                .append(" SET `name`=?,`version`=?, `versioncode`=?, `crc32`=?, `url`=?,`channel`=?, `phonetype`=?, `status`=? WHERE `id`=?");
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
                if (item.getVersion() != null)
                {
                    ps.setString(i++, item.getVersion());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getVersioncode() != null)
                {
                    ps.setInt(i++, item.getVersioncode());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getCrc32() != null)
                {
                    ps.setString(i++, item.getCrc32());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getUrl() != null)
                {
                    ps.setString(i++, item.getUrl());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getChannel() != null)
                {
                    ps.setString(i++, item.getChannel());
                }
                else
                {
                    ps.setNull(i++, Types.NULL);
                }
                if (item.getPhonetype() != null)
                {
                    ps.setString(i++, item.getPhonetype());
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
                ps.setInt(i++, item.getId());
            }
        };
        int id = getJdbcTemplate().update(sb.toString(), psc);
        if (id > 0)
        {
            WnsCacheFactory.delete(CACHE_KEY_ALL);
            WnsCacheFactory.delete(CACHE_KEY_GROUPNAME);
            WnsCacheFactory.delete(CACHE_KEY_ID + id);
            WnsCacheFactory.delete(CACHE_KEY_NAME + item.getName() + "_"
                    + item.getChannel());
        }
    }

    @SuppressWarnings("unchecked")
    public LibItem findSingle(int id)
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ID + id);
        if (obj != null && obj instanceof LibItem)
        {
            return (LibItem) obj;
        }
        else
        {
            List<LibItem> list = query("select * from " + table()
                    + " where id=? and status = 0 LIMIT 1",
                    new Object[] { id }, new int[] { Types.INTEGER },
                    new WnsCommonRowMapper(LibItem.class));
            if (list != null && list.size() > 0)
            {
                LibItem item = list.get(0);
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
    public LibItem findByNameAndChannel(String name, String channel)
    {

        Object obj = WnsCacheFactory.get(CACHE_KEY_NAME + name + "_"
                + channel);
        if (obj != null && obj instanceof LibItem)
        {
//        	System.out.println("=====findByNameAndChannel.obj========");
            return (LibItem) obj;
        }
        else
        {
//        	System.out.println("=====findByNameAndChannel.obj=null=======");
            StringBuilder sb = new StringBuilder();
            List<LibItem> list;

            sb.append("select * from " + table() + " where name = ? ");
            if ((channel != null) && !channel.isEmpty())
            {
                sb.append(" and (channel = ? or channel = '-1') ");
                sb.append(" and status = 0 order by versionCode desc limit 1");

                list = query(sb.toString(), new Object[] { name, channel },
                        new int[] { Types.VARCHAR, Types.VARCHAR },
                        new WnsCommonRowMapper(LibItem.class));

            }
            else
            {
                sb.append(" and status = 0 order by versionCode desc limit 1");
                list = query(sb.toString(), new Object[] { name },
                        new int[] { Types.VARCHAR },
                        new WnsCommonRowMapper(LibItem.class));
            }
            if (list != null && list.size() > 0)
            {
                LibItem item = list.get(0);
                WnsCacheFactory.add(CACHE_KEY_NAME + name + "_" + channel,
                        item, WnsCacheFactory.ONE_MONTH);
                return item;
            }
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<LibItem> findAll()
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_ALL);
        if (obj != null && obj instanceof List)
        {
            return (List<LibItem>) obj;
        }
        else
        {
            List<LibItem> list = query("select * from " + table()
                    + " where status = 0 order by id desc", null, null,
                    new WnsCommonRowMapper(LibItem.class));
            if (list != null && list.size() > 0)
            {
                WnsCacheFactory.add(CACHE_KEY_ALL, list,
                        WnsCacheFactory.ONE_MONTH);
            }
            return list;
        }
    }
    
    
    @SuppressWarnings("unchecked")
    public List<LibItem> findAllLibName()
    {
        Object obj = WnsCacheFactory.get(CACHE_KEY_GROUPNAME);
        if (obj != null && obj instanceof List)
        {
            return (List<LibItem>) obj;
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            List<LibItem> list;

            sb.append("select * from " + table()
                    + " where status = 0 group by name");

            list = query(sb.toString(), null, null, new WnsCommonRowMapper(
                    LibItem.class));
            if (list != null && list.size() > 0)
            {
                WnsCacheFactory.add(CACHE_KEY_GROUPNAME, list,
                        WnsCacheFactory.ONE_MONTH);
            }
            return list;
        }
    }
    
    @SuppressWarnings("unchecked")
    private List<LibItemChannel> findAllChannel()
    {
        StringBuilder sb = new StringBuilder();
        List<LibItemChannel> list=null;

        try{
	        sb.append("select DISTINCT(channel) from " + table());
	
	        list = query(sb.toString(), null, null, new WnsCommonRowMapper(
	        		LibItemChannel.class));
        }catch(Exception e){
        	
        }
        return list;
    }
}
