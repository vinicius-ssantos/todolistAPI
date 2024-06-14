package com.vinicius_santos.todolist.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// A classe OpenAPIConfig é usada para configurar a documentação da API usando o OpenAPI.
// A anotação @Configuration indica que esta classe contém definições de beans que serão gerenciados pelo Spring.
@Configuration
public class OpenAPIConfig {

    // As anotações @Value são usadas para injetar valores de propriedades no código.
    // Aqui, elas estão sendo usadas para injetar os valores das propriedades api.info.title, api.info.description e api.info.version.
    @Value("${api.info.title}")
    private String apiTitle;

    @Value("${api.info.description}")
    private String apiDescription;

    @Value("${api.info.version}")
    private String apiVersion;

    // O método customOpenAPI é anotado com @Bean, indicando que o Spring deve gerenciar este método e usar seu valor de retorno como um bean.
    // Este método retorna um objeto OpenAPI, que é usado para configurar a documentação da API.
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // O método info é usado para configurar as informações gerais da API.
                .info(new Info()
                        // Configurando o título, a descrição e a versão da API.
                        .title(apiTitle)
                        .description(apiDescription)
                        .version(apiVersion)
                        // Configurando o contato da API.
                        .contact(new Contact()
                                .name("Support Team")
                                .email("support@todolist.com")
                                .url("http://todolist.com/support"))
                        // Configurando a licença da API.
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
