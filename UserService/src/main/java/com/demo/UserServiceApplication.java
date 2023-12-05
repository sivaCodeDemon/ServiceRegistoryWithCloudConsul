package com.demo;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.validation.Path.ReturnValueNode;

/**
 * Service discovery with the Consul
 * by using discoveryClient can access the instance of the new Microservice
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class UserServiceApplication {


	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/accessApi")
	public String invokeGreeting() {
		URI temp= discoveryClient.getInstances("Greeting-service").stream().
				map(si->si.getUri()).findFirst().map(s->s.resolve
						("/greeting")).orElseThrow();
		return template().getForObject(temp, String.class);
	}
	
	
	@GetMapping("/accessIntApi")
	public Integer invokeount() {
		URI tmpUri=discoveryClient.getInstances("Greeting-service").
				stream().map(si->si.getUri()).findFirst().map(k->k.resolve("/count")).orElseThrow();
		return template().getForObject(tmpUri, Integer.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}

}
