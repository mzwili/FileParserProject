package com.eviro.assessment.grad001.ThuthkaniMthiyane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.eviro.assessment.grad001.Controller")
public class ThuthkaniMthiyaneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThuthkaniMthiyaneApplication.class, args);
	}

}
