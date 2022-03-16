package com.priva.tango.redis.test;

import java.util.List;
import java.util.Map.Entry;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.Transaction;

public class JedisUtil {
	static Jedis jedis = new Jedis("localhost");
	public static void main(String[] args) {
		jedis.flushDB();
		System.out.println(jedis.ping());
		jedis.watch("ss2");//只有其他线程修改才会取消
		Transaction multi = jedis.multi();
		try {
			multi.set("ss", "1");
			multi.set("ss2", "ff");
			multi.incr("ss2");
			List<Object> exec = multi.exec();
//			exec.forEach(str->{System.out.println(str);});
		} catch (Exception e) {
			e.printStackTrace();
			multi.discard();
		} finally {
			System.out.println(jedis.get("ss"));
			System.out.println(jedis.get("ss2"));
			jedis.close();
		}
	}
	
	/**
	 * 
	 */
	public void brpop() {
//		jedis.lpush(null, null)
		jedis.brpop("");
	}
	
	/**
	 * 
	 */
	private void stream() {
//		Entry<String, StreamEntryID> entry = new 
//		jedis.xadd(null, null, null)
//		jedis.xgroupCreate(null, null, null, false)
//		jedis.xread(5, 1000, null);
	}
	
	public void multi() {
//		jedis.watch("");
		Transaction multi = jedis.multi();
		multi.exec();
	}
	
}
