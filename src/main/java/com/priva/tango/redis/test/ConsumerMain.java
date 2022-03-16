package com.priva.tango.redis.test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.priva.tango.redis.RedisUtil;

import redis.clients.jedis.JedisPoolConfig;

public class ConsumerMain {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		JedisPoolConfig poolConfig=new JedisPoolConfig();
        //最大空闲数
        poolConfig.setMaxIdle(30);
        //最大连接数
        poolConfig.setMaxTotal(50);
        //最大等待毫秒数
        poolConfig.setMaxWaitMillis(2000);
        JedisConnectionFactory connectionFactory=new JedisConnectionFactory(poolConfig);
        connectionFactory.setHostName("127.0.0.1");
        connectionFactory.setPort(6379);

		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(connectionFactory);
		
		StringRedisSerializer serializer = new StringRedisSerializer();
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        
        /**必须执行这个函数,初始化RedisTemplate*/
        redisTemplate.afterPropertiesSet();
        
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.setRedisTemplate(redisTemplate);
		
		Executor e = Executors.newFixedThreadPool(20);
//			e.execute(()->{System.out.println(redisUtil.lBRightPop("judge", 20, TimeUnit.SECONDS));});
//			e.execute(()->{System.out.println(redisUtil.lRightPop("judge"));});
		while(1==1) {
			System.out.println(redisUtil.lBRightPop("judge", 20, TimeUnit.SECONDS));
		}
		
	}
}
