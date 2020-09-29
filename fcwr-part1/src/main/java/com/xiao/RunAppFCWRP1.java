package com.xiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.xiao.mapper")
@EnableTransactionManagement
@EnableEurekaClient
public class RunAppFCWRP1 {

	public static void main(String[] args) {
		SpringApplication.run(RunAppFCWRP1.class, args);
	}

	
}
