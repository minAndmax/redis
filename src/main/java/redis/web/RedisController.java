package redis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.service.redis.RedisTest;

@RestController
@RequestMapping("redis")
public class RedisController {
	
	@Autowired
	private RedisTest redisTest;
	
	@RequestMapping("test")
	public String getRedis() {
		
		String value = redisTest.getCache();
		
		return value; 
	}

}
