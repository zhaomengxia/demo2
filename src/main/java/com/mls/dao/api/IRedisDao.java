package com.mls.dao.api;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/11/27.
 */
public interface IRedisDao {

    /**
     * 对key的操作
     */
    public String type(String key);
    public Boolean exists(String key);
    public String del(String key);
    public String rename(String oldkey, String newkey);
    public String expire(String key, Integer seconds);
    public String ttl(String key);
    public List<String> sort(String key);
    public String move(String key, Integer db);

    /**
     * 对type为string的key的操作
     */
    public String get(String key);
    public String set(String key, String value);

    /**
     * 对type为hash的key的操作
     */
    public String hget(String key, String field);
    public Map<String, String> hgetAll(String key);
    public String hset(String key, String field, String value);
    public String hdel(String key, String field);

    /**
     * 对type为list的key的操作
     */
    public String lpop(String key);
    public String rpop(String key);
    public String lset(String key, Long index, String value);
    public String lpush(String key, String value);
    public String rpush(String key, String value);

    /**
     * 对type为set的key的操作
     */
    public String sadd(String key,String value);
    public String spop(String key);

}
