package com.tka.amz_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tka.amz_project", "com.tka.controller"})
public class AmzProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmzProjectApplication.class, args);
		System.out.println("Welcome to Amz Application");
	}

}
