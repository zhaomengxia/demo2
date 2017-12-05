package com.mls.controller;

import com.mls.dao.impl.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/11/20.
 */
//处理http请求。返回json型数据给前端：@ResponseBody配合@Controller
@RestController
//配置url映射
@RequestMapping(value = "/springboot", method = RequestMethod.GET)
public class Demo2Controller {

    @Autowired
    private RedisDao redisDao;

    /**
     * 对key的操作
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello()
    {
        return "nihao";
    }


    //配置url映射
    //url：http://localhost:8088/springboot/type/key的值
    @RequestMapping(value = "/type/{key}", method = RequestMethod.GET)
    public String type(@PathVariable("key") String key) {
        return redisDao.type(key);
    }

    //url：http://localhost:8088/springboot/exists/key的值
    @RequestMapping(value = "/exists/{key}", method = RequestMethod.GET)
    public Boolean exists(@PathVariable("key") String key) {
        return redisDao.exists(key);
    }

    //url：http://localhost:8088/springboot/del/key的值
    @RequestMapping(value = "/del/{key}", method = RequestMethod.DELETE)
    public String del(@PathVariable("key") String key) {
        return redisDao.del(key);
    }

    //url：http://localhost:8088/springboot/rename?oldkey=oldkey的值&newkey=newkey的值
    @RequestMapping(value = "/rename", method = RequestMethod.PUT)
    public String rename(@RequestParam("oldkey") String oldkey, @RequestParam("newkey") String newkey) {
        return redisDao.rename(oldkey, newkey);
    }

    //url：http://localhost:8088/springboot/expire?key=key的值&seconds=seconds的值
    @RequestMapping(value = "/expire", method = RequestMethod.PUT)
    public String expire(@RequestParam("key") String key, @RequestParam("seconds") Integer seconds) {
        return redisDao.expire(key, seconds);
    }

    //url：http://localhost:8088/springboot/ttl/key的值
    @RequestMapping(value = "/ttl/{key}", method = RequestMethod.GET)
    public String ttl(@PathVariable("key") String key) {
        return redisDao.ttl(key);
    }

    //url：http://localhost:8088/springboot/sort/key的值
    @RequestMapping(value = "/sort/{key}", method = RequestMethod.GET)
    public List<String> sort(@PathVariable("key") String key) {
        return redisDao.sort(key);
    }

    //url：http://localhost:8088/springboot/move?key=key的值&db=db的值
    @RequestMapping(value = "/move", method = RequestMethod.PUT)
    public String move(@RequestParam("key") String key, @RequestParam("db") Integer db) {
        return redisDao.move(key, db);
    }

    /**
     * 对type为string的key的操作
     */

    //url：http://localhost:8088/springboot/get/key的值
    @RequestMapping(value = "/get/{key}", method = RequestMethod.GET)
    public String get(@PathVariable("key") String key) {
        return redisDao.get(key);
    }

    //url：http://localhost:8088/springboot/set?key=key的值&value=value的值
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public String set(@RequestParam("key") String key, @RequestParam("value") String value) {
        return redisDao.set(key, value);
    }

    /**
     * 对type为hash的key的操作
     */

    //url：http://localhost:8088/springboot/hget?key=key的值&field=field的值
    @RequestMapping(value = "/hget", method = RequestMethod.GET)
    public String hget(@RequestParam("key") String key, @RequestParam("field") String field) {
        return redisDao.hget(key, field);
    }

    //url：http://localhost:8088/springboot/hgetAll/key的值
    @RequestMapping(value = "/hgetAll/{key}", method = RequestMethod.GET)
    public Map<String, String> hgetAll(@PathVariable("key") String key) {
        return redisDao.hgetAll(key);
    }

    //url：http://localhost:8088/springboot/hset?key=key的值&field=field的值&value=value的值
    @RequestMapping(value = "/hset", method = RequestMethod.POST)
    public String hset(@RequestParam("key") String key, @RequestParam("field") String field, @RequestParam("value") String value) {
        return redisDao.hset(key, field, value);
    }

    //url：http://localhost:8088/springboot/hdel?key=key的值&field=field的值
    @RequestMapping(value = "/hdel", method = RequestMethod.DELETE)
    public String hdel(@RequestParam("key") String key, @RequestParam("field") String field) {
        return redisDao.hdel(key, field);
    }

    /**
     * 对type为list的key的操作
     */

    //url：http://localhost:8088/springboot/lpop/key的值
    @RequestMapping(value = "/lpop/{key}", method = RequestMethod.DELETE)
    public String lpop(@PathVariable("key") String key) {
        return redisDao.lpop(key);
    }

    //url：http://localhost:8088/springboot/rpop/key的值
    @RequestMapping(value = "/rpop/{key}", method = RequestMethod.DELETE)
    public String rpop(@PathVariable("key") String key) {
        return redisDao.rpop(key);
    }

    //url：http://localhost:8088/springboot/lset?key=key的值&row=row的值&value=value的值
    @RequestMapping(value = "/lset", method = RequestMethod.PUT)
    public String lset(@RequestParam("key") String key, @RequestParam("row") Long row, @RequestParam("value") String value) {
        return redisDao.lset(key, row-1, value);
    }

    //url：http://localhost:8088/springboot/lpush?key=key的值&value=value的值
    @RequestMapping(value = "/lpush", method = RequestMethod.POST)
    public String lpush(@RequestParam("key") String key, @RequestParam("value") String value) {
        return redisDao.lpush(key, value);
    }

    //url：http://localhost:8088/springboot/rpush?key=key的值&value=value的值
    @RequestMapping(value = "/rpush", method = RequestMethod.POST)
    public String rpush(@RequestParam("key") String key, @RequestParam("value") String value) {
        return redisDao.rpush(key, value);
    }

    /**
     * 对type为set的key的操作
     */

    //url：http://localhost:8088/springboot/sadd?key=key的值&value=value的值
    @RequestMapping(value = "/sadd", method = RequestMethod.POST)
    public String sadd(@RequestParam("key") String key, @RequestParam("value") String value) {
        return redisDao.sadd(key, value);
    }

    //url：http://localhost:8088/springboot/spop/key的值
    @RequestMapping(value = "/spop/{key}", method = RequestMethod.DELETE)
    public String spop(@PathVariable("key") String key) {
        return redisDao.spop(key);
    }
}
