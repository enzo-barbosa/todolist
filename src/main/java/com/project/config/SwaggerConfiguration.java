package com.project.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuração do Swagger/OpenAPI para documentação da API
@Configuration
public class SwaggerConfiguration {

    // Configura a API pública agrupada
    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("todolist") // Nome do grupo de APIs
                .pathsToMatch("/**") // Inclui todos os endpoints
                .packagesToScan("com.project.resources") // Pacote onde estão os controllers
                .build();
    }

    // Configura as informações gerais da API
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info().title("To-do List API")
                .description("API para gerenciamento de tarefas e usuários")
                .version("1.0")
                .contact(new Contact().name("Gerenciamento de Tarefas")
                        .url("https://github.com/enzo-barbosa/todolist")
                        .email("enzoalmeida570@gmail.com")));
    }
}