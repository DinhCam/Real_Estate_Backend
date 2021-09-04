package com.gsu21se45.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfiguration {
	
	 @Bean
	    public WebMvcConfigurer corsConfigurer()
	    {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	            	registry.addMapping("/**").allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE").allowedOrigins("*")
                    .allowedHeaders("*").allowCredentials(true).maxAge(3600);
	            }
	        };
	    }

}
