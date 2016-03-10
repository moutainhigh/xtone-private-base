package com.wns2.factory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.wns2.util.WnsSpringHelper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

public class WnsRedisFactory
{
    /**
     * 1分钟过期
     * */
    public static final int               ONE_MINUTE       = 60;
    /**
     * 1小时过期
     * */
    public static final int               ONE_HOUR         = 60 * 60;
    /**
     * 1天过期
     * */
    public static final int               ONE_DAY          = 60 * 60 * 24;
    /**
     * 1周过期
     * */
    public static final int               ONE_WEEK         = 60 * 60 * 24 * 7;
    /**
     * 1月过期
     * */
    public static final int               ONE_MONTH        = 60 * 60 * 24 * 30; // 最大不能超过30天
    /**
     * 永不过期
     * */
    public static final int               ALWAYS           = 0;

    private static JedisConnectionFactory redisConnFactory = null;
//    private static Jedis                  redisClient      = null;
    private static JedisPool pool = null;

    private static Jedis getInstance()
    {
        if (pool == null)
        {
            redisConnFactory = (JedisConnectionFactory) WnsSpringHelper
                    .getBean("redisConnectionFactory");
            pool = new JedisPool(redisConnFactory.getPoolConfig(), redisConnFactory.getHostName(), redisConnFactory.getPort()); 
//            redisClient = redisConnFactory.getShardInfo().createResource();
        }
        return pool.getResource();
    }

//    public static void close(Jedis instance)
//    {
//        pool.returnResource(instance)
//        if (redisClient != null)
//        {
//            redisClient.close();
//            redisClient = null;
//        }
//    }

    public static void setRedisConnFactory(
            JedisConnectionFactory redisConnFactory)
    {
        if (redisConnFactory == null)
        {
            WnsRedisFactory.redisConnFactory = redisConnFactory;
        }
    }

    /**
     * 从指定的列表右边出队,添加到目的列表中
     * 
     * @param srckey
     *            源列表
     * @param dstkey
     *            　目的列表
     * @return
     */
    public static String rpoppush(String srckey, String dstkey)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.rpoplpush(srckey, dstkey);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    /**
     * 从队列的左边取出一条数据
     * 
     * @param key
     *            　列表名
     * @return
     */
    public static String lpop(String key)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.lpop(key);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    /**
     * 从队列的右边取出一条数据
     * 
     * @param key
     *            列表名
     * @return
     */
    public static String rpop(String key)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.rpop(key);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    /**
     * 把一个值添加到对应列表中
     * 
     * @param key
     *            列表名
     * @param index
     *            　添加的位置
     * @param value
     *            　数据
     * @return
     */
    public static String lset(String key, long index, String value)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.lset(key, index, value);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    public static Long lpush(String key, String value)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            String[] strs = new String[1];
            strs[0] = value;
            return redis.lpush(key, strs);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    /**
     * 把所有数据添加到一个列表中
     * 
     * @param key
     *            列表名
     * @param values
     *            　数据
     * @return
     */
    public static Long lpush(String key, String[] values)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.lpush(key, values);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    /**
     * 把所有数据添加到一个列表中,并且设置列表的存活时间
     * 
     * @param key
     *            列表名
     * @param values
     *            数据
     * @param liveTime
     *            存活时间--单位(秒)
     * @return
     */
    public static Long lpush(String key, String[] values, int liveTime)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            Long result = redis.lpush(key, values);
            redis.expire(key, liveTime);
            return result;
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    /**
     * 删除列表中对应值的元素
     * 
     * @param key
     *            列表名
     * @param count
     *            删除多少个相同的元素
     * @param value
     *            数据
     * @return
     */
    public static Long lrem(String key, long count, String value)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.lrem(key, count, value);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    /**
     * 返回指定列表范围内的元素
     * 
     * @param key
     *            列表名
     * @param start
     *            开始位置
     * @param end
     *            结束位置
     * @return
     */
    public static List<String> lrange(String key, long start, long end)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.lrange(key, start, end);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    public static Long llen(String key)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.llen(key);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    /**
     * 通过key删除（字节）
     * 
     * @param key
     */
    public static void del(byte[] key)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            redis.del(key);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
    }

    /**
     * 通过key删除
     * 
     * @param key
     */
    public static void del(String key)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            redis.del(key);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
    }

    /**
     * 添加key value 并且设置存活时间(byte)
     * 
     * @param key
     * @param value
     * @param liveTime
     */
    public static void set(byte[] key, byte[] value, int liveTime)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            redis.set(key, value);
            redis.expire(key, liveTime);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
    }

    /**
     * 添加key value 并且设置存活时间
     * 
     * @param key
     * @param value
     * @param liveTime
     */
    public static void set(String key, String value, int liveTime)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            redis.set(key, value);
            redis.expire(key, liveTime);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
    }

    /**
     * 添加key value
     * 
     * @param key
     * @param value
     */
    public static void set(String key, String value)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            redis.set(key, value);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
    }

    /**
     * 添加key value (字节)(序列化)
     * 
     * @param key
     * @param value
     */
    public static void set(byte[] key, byte[] value)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            redis.set(key, value);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
    }

    /**
     * 获取redis value (String)
     * 
     * @param key
     * @return
     */
    public static String get(String key)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            String value = redis.get(key);
            return value;
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    /**
     * 获取redis value (byte [] )(反序列化)
     * 
     * @param key
     * @return
     */
    public static byte[] get(byte[] key)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.get(key);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    public static String set(String key, Map<String, String> map)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.hmset(key, map);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    public static List<String> get(String key, String... fields)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.hmget(key, fields);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    /**
     * 通过正则匹配keys
     * 
     * @param pattern
     * @return
     */
    public static Set<String> keys(String pattern)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.keys(pattern);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    public static Long publish(String channel, String message)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.publish(channel, message);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    public static void psubscribe(JedisPubSub listener, String... patterns)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            redis.psubscribe(listener, patterns);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
    }

    public static void subscribe(JedisPubSub listener, String... patterns)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            redis.subscribe(listener, patterns);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
    }

    public static void incr(String key)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            redis.incr(key);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
    }
    
    public static void incr(String key, int liveTime)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            redis.incr(key);
            redis.expire(key, liveTime);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
    }
   
    /**
     * 添加key value 并且设置存活时间
     * 
     * @param key
     * @param value
     * @param liveTime
     */
    public static void expire(String key, int liveTime)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            redis.expire(key, liveTime);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
    }
    
    /**
     * 检查key是否已经存在
     * 
     * @param key
     * @return
     */
    public static Boolean exists(String key)
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.exists(key);
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    /**
     * 清空redis 所有数据
     * 
     * @return
     */
    public static String flushDB()
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.flushDB();
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;

    }

    /**
     * 查看redis里有多少数据
     */
    public static Long dbSize()
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.dbSize();
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }

    /**
     * 检查是否连接成功
     * 
     * @return
     */
    public static String ping()
    {
        Jedis redis = null;
        try
        {
            redis = getInstance();
            return redis.ping();
        }
        catch (Exception e)
        {
            pool.returnBrokenResource(redis);
            e.printStackTrace();
        }
        finally
        {
            pool.returnResource(redis);
        }
        return null;
    }
}
