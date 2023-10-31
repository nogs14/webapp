package com.openclassrooms.webapp.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("com.openclassrooms.webapp")
public class CustomProperties {
	
	private String apiUrl;

}
