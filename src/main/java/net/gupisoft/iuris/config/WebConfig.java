package net.gupisoft.iuris.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.gupisoft.iuris.config.thymeleaf.GurugoDialect;


@Configuration
public class WebConfig {
	
	@Bean
	public GurugoDialect gurugoDialect() {
		return new GurugoDialect();
	}

}
