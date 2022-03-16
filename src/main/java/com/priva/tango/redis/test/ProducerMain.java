package com.priva.tango.redis.test;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.priva.tango.redis.RedisUtil;

import redis.clients.jedis.JedisPoolConfig;

public class ProducerMain {
	public static void main(String[] args) {
		JedisPoolConfig poolConfig=new JedisPoolConfig();
        //最大空闲数
        poolConfig.setMaxIdle(30);
        //最大连接数
        poolConfig.setMaxTotal(50);
        //最大等待毫秒数
        poolConfig.setMaxWaitMillis(2000);
        JedisConnectionFactory connectionFactory=new JedisConnectionFactory(poolConfig);

		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(connectionFactory);
		
		//定义序列化
		StringRedisSerializer serializer = new StringRedisSerializer();
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        
        /**必须执行这个函数,初始化RedisTemplate*/
        redisTemplate.afterPropertiesSet();
        
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.setRedisTemplate(redisTemplate);
		
		for(int i = 0;i<10;i++) {
			redisUtil.lRightPush("judge", i+"");
		}
	}
}
