package redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class RedisDome {
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RedisDome.class);
		app.run(args);
	}
	

}
