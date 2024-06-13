package com.vinicius_santos.todolist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**") // Libera acesso a todos os endpoints
              .allowedOrigins("*") // Libera acesso a todos os domínios
              .allowedHeaders("*") // Libera acesso a todos os headers
              .allowedMethods("*") // Libera acesso a todos os métodos
              .exposedHeaders("*"); // Libera acesso a todos os headers expostos
    }
}
