package com.xk.product.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConf {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Bean
    public RedisTemplate<String,Object> stringRedisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        DefaultSerializer defaultSerializer = new DefaultSerializer();
        redisTemplate.setKeySerializer(defaultSerializer);
        redisTemplate.setValueSerializer(defaultSerializer);
        redisTemplate.setHashKeySerializer(defaultSerializer);
        redisTemplate.setHashValueSerializer(defaultSerializer);
        /**
         *  redisTemplate.setKeySerializer(new StringRedisSerializer());
         *  redisTemplate.setHashKeySerializer(new StringRedisSerializer());
         *  redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
         *  redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
         */
        return redisTemplate;
    }


}
