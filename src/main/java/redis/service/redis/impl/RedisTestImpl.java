package redis.service.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisCluster;
import redis.service.redis.RedisTest;

@Service("redisTest")
public class RedisTestImpl implements RedisTest {

	@Autowired
	private JedisCluster jedisCluster;
	
	@Override
	public String getCache() {
		
		jedisCluster.set("userName", "张三");
		String value = jedisCluster.get("userName");
		
		return value;
	}

}
