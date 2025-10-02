package com.project.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// Configurações de escaneamento de componentes
@ComponentScan(basePackages = "com.project") // Escaneia todos os componentes do projeto
@EntityScan (basePackages = {"com.project.domains", "com.curso.domains.enums"}) // Escaneia entidades JPA
@EnableJpaRepositories(basePackages = "com.project.repositories") // Habilita repositórios JPA

// Anotação principal que marca esta classe como aplicação Spring Boot
@SpringBootApplication
public class TodolistApplication {

    // Método main que inicia a aplicação Spring Boot
    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }
}