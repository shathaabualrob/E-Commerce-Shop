package com.ejada.task1namingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Task1NamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Task1NamingServerApplication.class, args);
	}

}
