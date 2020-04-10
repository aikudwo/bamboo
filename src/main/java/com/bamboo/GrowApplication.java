package com.bamboo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCaching
//@MapperScan("com.bamboo.mapper")
public class GrowApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrowApplication.class, args);
	}
}
