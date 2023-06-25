package com.ejada.task2shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories("com.ejada")
@EntityScan("com.ejada")
@EnableFeignClients
@SpringBootApplication
public class Task2ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(Task2ShopApplication.class, args);
	}

}
