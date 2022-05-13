package com.helpduck.helpducktickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HelpDuckTicketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpDuckTicketsApplication.class, args);
	}
}
