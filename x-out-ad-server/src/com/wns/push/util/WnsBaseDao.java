package com.wns.push.util;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class WnsBaseDao extends JdbcDaoSupport
{
    public WnsBaseDao()
    {
    }

    @SuppressWarnings("unchecked")
    public List query(String sql, Object[] args, int[] argTypes,
            RowMapper mapper)
    {
    	System.out.println("sql=====>"+sql);
        return getJdbcTemplate().query(sql, args, argTypes, mapper);
    }

    @SuppressWarnings("unchecked")
    public List query(String sql, RowMapper mapper)
    {
        return getJdbcTemplate().query(sql, mapper);
    }

    public List<Map<String, Object>> query(String sql, Object[] args,
            int[] argTypes)
    {
        return getJdbcTemplate().queryForList(sql, args, argTypes);
    }

    public int queryForInt(String sql, Object[] args, int[] argTypes)
    {
        return getJdbcTemplate().queryForInt(sql, args, argTypes);
    }

    public long queryForLong(String sql, Object[] args, int[] argTypes)
    {
        return getJdbcTemplate().queryForLong(sql, args, argTypes);
    }
}
