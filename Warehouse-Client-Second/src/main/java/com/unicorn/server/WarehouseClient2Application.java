package com.unicorn.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author xuantang
 */
@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
public class WarehouseClient2Application {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseClient2Application.class, args);
	}
}
