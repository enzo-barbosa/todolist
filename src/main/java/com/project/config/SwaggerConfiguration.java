package com.project.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("todolist")
                .pathsToMatch("/**")
                .packagesToScan("com.project.resources")
                .build();
    }

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