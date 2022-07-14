package com.matheussilvadev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matheussilvadev.domain.User;

@SpringBootApplication
public class ApiTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTestsApplication.class, args);
		
		User user = new User(1, "Matheus", "matheus@email.com", "1234");
	}

}
