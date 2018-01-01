package com.unicorn.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author xuantang
 */
@EnableEurekaServer
@SpringBootApplication
public class UnicornServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnicornServerApplication.class, args);
	}
}
