package com.unicorn.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author xuantang
 */
@EnableEurekaServer
@SpringBootApplication
public class WarehouseServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseServerApplication.class, args);
	}
}
