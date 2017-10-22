package com.example.restSpring.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class RestServices {

	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplateBuilder().basicAuthorization("login", "passwd").build();
		return restTemplate;
	}
}
