package com.bamboo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCaching
//@MapperScan("com.bamboo.mapper")
public class GrowApplication {
	//新電腦啓動不了這個項目啊
	public static void main(String[] args) {
		SpringApplication.run(GrowApplication.class, args);
	}
}
