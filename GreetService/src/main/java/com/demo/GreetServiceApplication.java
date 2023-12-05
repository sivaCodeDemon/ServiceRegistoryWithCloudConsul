package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@SpringBootApplication
@RestController
public class GreetServiceApplication {

	
	@GetMapping("/greeting")
	public String greetAll() {
		return "Hello All This is the ConsulDemo";
	}
	
	@GetMapping("/count")
	public Integer count() {
		int a=10;
		return a;
	}
	public static void main(String[] args) {
		SpringApplication.run(GreetServiceApplication.class, args);
	}

}
