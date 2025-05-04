package com.swiftcraves.onlinefood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.swiftcraves.onlinefood.model")
@EnableJpaRepositories(basePackages = "com.swiftcraves.onlinefood.repository")
public class OnlinefoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinefoodApplication.class, args);
	}

}
