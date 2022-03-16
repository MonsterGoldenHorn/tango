package com.priva.tango.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisApplicationTest {
	@Autowired
	StringRedisTemplate template;
	
	@Test
	public void conn() {
		System.out.println(template.getConnectionFactory().getConnection().ping());
	}
}
