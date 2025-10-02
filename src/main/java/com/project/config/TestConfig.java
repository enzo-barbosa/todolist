package com.project.config;

import com.project.services.DBService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// Configuração específica para ambiente de teste
@Configuration
@Profile("test") // Só é ativada quando o perfil "test" estiver ativo
public class TestConfig {

    @Autowired
    private DBService dbService;

    // Inicializa o banco com dados de teste
    @PostConstruct
    public void initDB() {
        this.dbService.initDB();
    }
}