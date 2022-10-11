package com.example.cms9cc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.WebContentGenerator;

@Configuration
public class Config implements WebMvcConfigurer {
    @Override // org.springframework.web.servlet.config.annotation.WebMvcConfigurer
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOriginPatterns("*").allowedMethods("GET", WebContentGenerator.METHOD_HEAD, WebContentGenerator.METHOD_POST, "PUT", "DELETE", "OPTIONS").allowCredentials(true).maxAge(3600L);
    }
}