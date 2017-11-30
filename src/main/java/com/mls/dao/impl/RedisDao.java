package com.mls.dao.impl;

import com.mls.dao.api.IRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/11/27.
 */
@Repository
public class RedisDao implements IRedisDao {

    //自动注入需要一个RedisConfiguration类
    @Autowired
    private JedisPool jedisPool;

    @Override
    public String type(String key) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String result = "";
        if(jedis.exists(key) == true) {
            result = jedis.type(key);
        }
        else {
            result = "不存在这个key";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public Boolean exists(String key) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        Boolean result = jedis.exists(key);

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public String del(String key) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        Long result1 = jedis.del(key);
        String result2 = "";
        if(result1 == 1) {
            result2 = "删除成功";
        }
        else if(result1 == 0) {
            result2 = "删除失败";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result2;
    }

    @Override
    public String rename(String oldkey, String newkey) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String result = "";
        if(jedis.exists(oldkey) == true) {
            result = jedis.rename(oldkey, newkey);
        }
        else {
            result = "不存在这个key";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public String expire(String key, Integer seconds) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        Long result1 = jedis.expire(key, seconds);
        String result2 = "";
        if(result1 == 1) {
            result2 = "设置成功";
        }
        else if(result1 == 0) {
            result2 = "设置失败";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result2;
    }

    @Override
    public String ttl(String key) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        Long result1 = jedis.ttl(key);
        String result2 = "";
        if(result1 == -2) {
            result2 = "不存在这个key";
        }
        else if(result1 == -1) {
            result2 = "这个key没有生存时间限制";
        }
        else {
            result2 = "这个key的生存时间还剩" + result1 + "秒";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result2;
    }

    @Override
    public List<String> sort(String key) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String type = jedis.type(key);
        List<String> result = new ArrayList<String>();
        if("list".equals(type) || "set".equals(type)) {
            result = jedis.sort(key);
        }
        else {
            //出错提示...
            result.add("错误：排序的key的type必须为list或set");
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public String move(String key, Integer db) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        Long result1 = jedis.move(key, db);
        String result2 = "";
        if(result1 == 1) {
            result2 = "移动成功";
        }
        else {
            result2 = "移动失败";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result2;
    }

    @Override
    public String get(String key) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String result = "";
        if("string".equals(jedis.type(key))) {
            result = jedis.get(key);
        }
        else {
            result = "key的type必须为string";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public String set(String key, String value) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String result = "";
        if("string".equals(jedis.type(key))) {
            result = jedis.set(key, value);
        }
        else {
            result = "key的type必须为string";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public String hget(String key, String field) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String result = "";
        if("hash".equals(jedis.type(key))) {
            result = jedis.hget(key, field);
        }
        else {
            result = "key的type必须为hash";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        Map<String, String> result = new HashMap<String, String>();
        if("hash".equals(jedis.type(key))) {
            result = jedis.hgetAll(key);
        }
        else {
            //出错提示...
            result.put("出错了","key的type必须为hash");
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public String hset(String key, String field, String value) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String result = "";
        if("hash".equals(jedis.type(key))) {
            jedis.hset(key, field, value);
            result = "OK";
        }
        else {
            result = "key的type必须为hash";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public String hdel(String key, String field) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String result = "";
        if("hash".equals(jedis.type(key))) {
            jedis.hdel(key, field);
            result = "OK";
        }
        else {
            result = "key的type必须为hash";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public String lpop(String key) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String result = "";
        if("list".equals(jedis.type(key))) {
            result = jedis.lpop(key);
        }
        else {
            result = "key的type必须为list";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public String rpop(String key) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String result = "";
        if("list".equals(jedis.type(key))) {
            result = jedis.rpop(key);
        }
        else {
            result = "key的type必须为list";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public String lset(String key, Long index, String value) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String result = "";
        if("list".equals(jedis.type(key))) {
            result = jedis.lset(key, index, value);
        }
        else {
            result = "key的type必须为list";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public String lpush(String key, String value) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String result = "";
        if("list".equals(jedis.type(key))) {
            jedis.lpush(key, value);
            result = "OK";
        }
        else {
            result = "key的type必须为list";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public String rpush(String key, String value) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String result = "";
        if("list".equals(jedis.type(key))) {
            jedis.rpush(key, value);
            result = "OK";
        }
        else {
            result = "key的type必须为list";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public String sadd(String key, String value) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String result = "";
        if("set".equals(jedis.type(key))) {
            jedis.sadd(key, value);
            result = "OK";
        }
        else {
            result = "key的type必须为set";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

    @Override
    public String spop(String key) {
        //从jedis的连接池中取出一个jedis连接
        Jedis jedis = jedisPool.getResource();

        String result = "";
        if("set".equals(jedis.type(key))) {
            result = jedis.spop(key);
        }
        else {
            result = "key的type必须为set";
        }

        //归还jedis到jedis连接池
        jedis.close();

        return result;
    }

}
