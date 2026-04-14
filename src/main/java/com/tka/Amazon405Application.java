package com.tka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Amazon405Application {

	public static void main(String[] args) {
	SpringApplication.run(Amazon405Application.class, args);
	org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Amazon405Application.class);
	log.info("Application started: Amazon405Application");
}

}
