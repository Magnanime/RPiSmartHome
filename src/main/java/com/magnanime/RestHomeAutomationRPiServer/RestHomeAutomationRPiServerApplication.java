package com.magnanime.RestHomeAutomationRPiServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RestHomeAutomationRPiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestHomeAutomationRPiServerApplication.class, args);
	}
}
