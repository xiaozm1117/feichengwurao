package com.xiao;

import org.springframework.boot.SpringApplication;

import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringCloudApplication
@EnableFeignClients
public class RunAppFCWR {

	public static void main(String[] args) {
		SpringApplication.run(RunAppFCWR.class, args);
	}

	
}
