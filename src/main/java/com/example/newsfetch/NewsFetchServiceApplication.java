package com.example.newsfetch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling		// needed for scheduling
public class NewsFetchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsFetchServiceApplication.class, args);
	}

}
