package com.example.One_For_All;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OneForAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneForAllApplication.class, args);
	}

}
