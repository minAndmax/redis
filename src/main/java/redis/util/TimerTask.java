package redis.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TimerTask implements CommandLineRunner{

	Logger log = LoggerFactory.getLogger(TimerTask.class);
	@Override
	public void run(String... arg0) throws Exception {

		log.info("程序运行:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
	}

}
