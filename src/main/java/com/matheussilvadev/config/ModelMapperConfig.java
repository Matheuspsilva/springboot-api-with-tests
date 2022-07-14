package com.matheussilvadev.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	//Sempre que o projeto subir teremos a classe de configuração Model Mapper
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

}
