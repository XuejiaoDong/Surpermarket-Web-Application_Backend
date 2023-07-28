package com.mercury.SpringBootRestDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@SpringBootApplication
@EnableCaching
@EnableWebMvc
public class SpringBootRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestDemoApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//						.allowedOrigins("*")
//						.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
//						.allowedHeaders("*")
//						.allowCredentials(true) // 允许携带cookie
//						.maxAge(3600);
//			}
@Override
public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/**")
			.allowedOrigins("http://eatwhat.s3-website.us-east-2.amazonaws.com")
			.allowedMethods("*")
			.allowedHeaders("*")
			.allowCredentials(true);
}

		};
	}
}
