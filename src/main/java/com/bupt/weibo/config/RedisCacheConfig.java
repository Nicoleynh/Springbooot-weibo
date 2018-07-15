package com.bupt.weibo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * Created by shusesshou on 2017/9/23.
 */
@Configuration
@EnableCaching
//todo:如何建立
public class RedisCacheConfig extends CachingConfigurerSupport{
    private Logger logger = LoggerFactory.getLogger(RedisCacheConfig.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private String timeout;

    @Bean
    public JedisPool redisPoolFactory() {
        logger.info("redis: " + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(-1);
        jedisPoolConfig.setMaxWaitMillis(-1);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, 5000);
        return jedisPool;
    }
}
