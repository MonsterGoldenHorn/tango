package com.priva.tango.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;

public class JedisTemplate {
	@Autowired
	RedisTemplate<String, Object> redisTemplate;//原生byte
	@Autowired
	StringRedisTemplate stringRedisTemplate;//可视化
	//String
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	public void set(String key, Object obj) {
		redisTemplate.opsForValue().set(key, obj);
	}
	
	public void getAndSet(String key, Object obj) {
		redisTemplate.opsForValue().getAndSet(key, obj);
	}
	
	//批量get set
	public List<String> multiGet(Collection<String> keys) {
		return stringRedisTemplate.opsForValue().multiGet(keys);
	}
	
	public void multiSet(Map<String, String> maps) {
		redisTemplate.opsForValue().multiSet(maps);
	}
	//过去
	public void setEx(String key, String value, long timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, value, timeout, unit);
	}
	
	public boolean setIfAbsent(String key, String value) {
		return redisTemplate.opsForValue().setIfAbsent(key, value);
	}
	
	//覆盖offset
	public void setRange(String key, String value, long offset) {
		redisTemplate.opsForValue().set(key, value, offset);
	}
	//Set value for key, only if key does not exist.
	public void setNx(String key) {
		stringRedisTemplate.getConnectionFactory().getConnection().setNX(key.getBytes(), key.getBytes());
		redisTemplate.getConnectionFactory().getConnection().setNX(key.getBytes(), key.getBytes());
	}
	
	public Object hGet(String key, String field) {
		return redisTemplate.opsForHash().get(key, field);
	}
	
	public Map<Object, Object> hGetAll(String key) {
		return redisTemplate.opsForHash().entries(key);
	}
	//zset
//	public Object get(String key) {
//		return redisTemplate.opsForValue().get(key);
//	}
//	
//	public void set(String key, Object obj) {
//		redisTemplate.opsForValue().set(key, obj);
//	}
	
	public Long lua() {
		//下面是lua脚本
        String luaScript ="local buyNum = ARGV[1]\n" +
                "local goodsKey = KEYS[1]  \n" +
                "local goodsNum = redis.call('get',goodsKey) \n" +
                "if goodsNum >= buyNum \n" +
                "then redis.call('decrby',goodsKey,buyNum) \n" +
                "return buyNum \n" +
                "else \n" +
                "return '0'\n" +
                "end\n" +
                "\n" ;

        DefaultRedisScript<String> re = new DefaultRedisScript<String>();
        //设置脚本
        re.setScriptText(luaScript);
        //定义返回值类型，注意，如果没有这个定义，Spring不会返回结果
        re.setResultType(String.class);
        RedisSerializer<String> stringRedisSerializer = stringRedisTemplate.getStringSerializer();
        //执行LUA脚本
        String result = (String) stringRedisTemplate.execute(re, stringRedisSerializer, stringRedisSerializer, null);
        return Long.valueOf(result);

	}
	
	public Long mutil(String skuCode, int num) {
		SessionCallback<Long> sessionCallback = new SessionCallback<Long>() {
            @Override
            public Long execute(RedisOperations operations) throws DataAccessException {
                int result = num;
                //redis 乐观锁
                //我们观察商品编码是否发生改变
                operations.watch(skuCode);
                ValueOperations<String, String> valueOperations = operations.opsForValue();
                String goodsNumStr = valueOperations.get(skuCode);
                Integer goodsNum = Integer.valueOf(goodsNumStr);
                //标记一个事务块的开始。
                //事务块内的多条命令会按照先后顺序被放进一个队列当中，
                //最后由 EXEC 命令原子性(atomic)地执行。
                operations.multi();
                if (goodsNum >= num) {
                    valueOperations.increment(skuCode, 0 - num);
                } else {
                    result = 0;
                }
                //多条命令执行的结果集合
                List exec = operations.exec();
                if(exec.size()>0){
                    System.out.println(exec);
                }
                return (long) result;
            }
        };
        return stringRedisTemplate.execute(sessionCallback);
	}
}
