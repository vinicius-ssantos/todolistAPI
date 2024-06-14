package com.vinicius_santos.todolist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// A classe WebConfig é usada para configurar aspectos do Spring MVC.
// A anotação @Configuration indica que esta classe contém definições de beans que serão gerenciados pelo Spring.
@Configuration
@EnableWebMvc // A anotação @EnableWebMvc habilita o suporte ao Spring MVC.
public class WebConfig implements WebMvcConfigurer { // A classe implementa a interface WebMvcConfigurer para personalizar a configuração padrão do Spring MVC.

    // O método addCorsMappings é usado para configurar o CORS (Cross-Origin Resource Sharing) da aplicação.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Libera o acesso a todos os endpoints da aplicação.
                .allowedOrigins("*") // Permite que qualquer domínio acesse a aplicação.
                .allowedHeaders("*") // Permite que qualquer header seja enviado na requisição.
                .allowedMethods("*") // Permite que qualquer método HTTP seja usado na requisição (GET, POST, PUT, DELETE, etc.).
                .exposedHeaders("*"); // Permite que qualquer header seja exposto na resposta.
    }
}