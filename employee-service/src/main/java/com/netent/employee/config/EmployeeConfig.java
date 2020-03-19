package com.netent.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@ComponentScan("com.netent.employee.config")
@Configuration
public class EmployeeConfig {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
