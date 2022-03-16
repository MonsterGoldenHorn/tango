package com.priva.tango;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {RedisAutoConfiguration.class,
		RedisRepositoriesAutoConfiguration.class})
@ComponentScan(basePackages = {"com.priva.tango.mvc"})
//@ForestScan(basePackages = "com.priva.tango.forest")
public class TangoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TangoApplication.class, new String[] {});
	}

}
