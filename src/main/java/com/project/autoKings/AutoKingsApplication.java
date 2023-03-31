package com.project.autoKings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class  AutoKingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoKingsApplication.class, args);
	}

}
