package com.ejada.task1configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class Task1ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Task1ConfigServerApplication.class, args);
	}

}
