package com.priva.tango.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

public class RedisLock {
    public static void main(String[] args) {
        tryLock("123","223");

    }
    @Autowired
    StringRedisTemplate template;
    /**
     * 分布式锁
     * 1.互斥
     * 2.不会死锁
     * 3.容错性
     * 4.解锁必须是加锁的人
     */
    private static boolean tryLock(String key, String value){
        Jedis client = new Jedis("127.0.0.1", 6379);
        SetParams p = new SetParams();
        p.nx();
        p.px(10000l);
        String ret = client.set(key, value, p);
        System.out.println(ret);
        return false;
    }

    /**
     * lua里面判断加锁的人是不是自己
     * @param key
     * @param value
     * @return
     */
    private boolean tryRelease(String key, String value){
        Jedis client = new Jedis();
        String script = "";
        Object re = client.eval(script, Collections.singletonList(key), Collections.singletonList(value));
//        if(RELEASE_SUCC.equals(re)){
//            return true;
//        }
        return false;
    }
}
