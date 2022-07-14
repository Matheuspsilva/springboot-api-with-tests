package com.matheussilvadev.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.matheussilvadev.domain.User;
import com.matheussilvadev.repositories.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {
	
	@Autowired
	UserRepository userRepository;
	
	@Bean
	public void startDB() {
		User u1 = new User(null, "Matheus", "matheus@email.com", "1234");
		User u2 = new User(null, "João", "joao@email.com", "1234");
		
		userRepository.saveAll(List.of(u1,u2));
	}

}
