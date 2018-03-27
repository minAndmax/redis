package redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("redis.dao")
public class RedisDome {
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RedisDome.class);
		app.run(args);
	}
	

}
