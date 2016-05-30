package org.piyush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration 
@Configuration 
@ComponentScan 
public class ShoppingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingSystemApplication.class, args);
	}
}
