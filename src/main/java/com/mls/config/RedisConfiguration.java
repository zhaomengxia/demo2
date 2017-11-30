package com.mls.config;

/**
 * Created by ASUS on 2017/11/27.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

//等价于在XML中配置beans
@Configuration
public class RedisConfiguration {                   //@Value(${要获取application.yml中的配置参数的名称})

    //等价于在XML中配置bean
    @Bean(name= "jedis.pool")                   //
    @Autowired
    public JedisPool jedisPool(@Qualifier("jedis.pool.config") JedisPoolConfig config,
                               @Value("${jedis.pool.host}") String host,
                               @Value("${jedis.pool.port}") Integer port,
                               @Value("${jedis.pool.password}") String password,
                                @Value("${jedis.pool.timeout}") Integer timeout) {
        return new JedisPool(config, host, port,timeout,password);
    }

    @Bean(name= "jedis.pool.config")            //
    public JedisPoolConfig jedisPoolConfig (@Value("${jedis.pool.config.maxTotal}") Integer maxTotal,
                                            @Value("${jedis.pool.config.maxIdle}") Integer maxIdle,
                                            @Value("${jedis.pool.config.maxWaitMillis}") Integer maxWaitMillis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }
}
