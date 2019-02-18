package com.yitian.sq.cache;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

                                    //mybatis 的 cache 接口
public class RedisCache implements Cache {
    //日志生成器
    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
    //对 redis 操作
    private static JedisConnectionFactory jedisConnectionFactory;
    //mybatis 在产生二级缓存对象时，自动给 id 赋值
    private final String id;

    /**
     * 读写分离锁
     * The {@code ReadWriteLock}.
     */
    //可重用读写分离锁，防止同一个sql语句并发访问，
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.debug("MybatisRedisCache:id=" + id);
        this.id = id;
    }
    //清空缓存
    public void clear()
    {
        RedisConnection connection = null;
        try
        {
            connection = jedisConnectionFactory.getConnection();
            //connection 就是一个jedis对象
            connection.flushDb();
            connection.flushAll();
        }
        catch (JedisConnectionException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null) {
                connection.close();
            }
        }
    }


    public String getId()
    {
        return this.id;
    }


    public Object getObject(Object key)
    {
        Object result = null;
        RedisConnection connection = null;
        try
        {
            connection = jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            result = serializer.deserialize(connection.get(serializer.serialize(key)));
        }
        catch (JedisConnectionException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }


    public ReadWriteLock getReadWriteLock()
    {
        return this.readWriteLock;
    }


    public int getSize()
    {
        int result = 0;
        RedisConnection connection = null;
        try
        {
            connection = jedisConnectionFactory.getConnection();
            result = Integer.valueOf(connection.dbSize().toString());
        }
        catch (JedisConnectionException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }


    public void putObject(Object key, Object value)
    {
        RedisConnection connection = null;
        try
        {
            connection =  jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            connection.set(serializer.serialize(key), serializer.serialize(value));
        }
        catch (JedisConnectionException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null) {
                connection.close();
            }
        }
    }


    public Object removeObject(Object key)
    {
        RedisConnection connection = null;
        Object result = null;
        try
        {
            connection =  jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            result =connection.expire(serializer.serialize(key), 0);
        }
        catch (JedisConnectionException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }

}
