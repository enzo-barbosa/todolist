package com.project.config;

import com.project.services.DBService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// Configuração específica para ambiente de desenvolvimento
@Configuration
@Profile("dev") // Só é ativada quando o perfil "dev" estiver ativo
public class DevConfig {

    @Autowired
    private DBService dbService; // Serviço responsável por popular o banco

    // Método executado após a construção do bean para inicializar o banco
    @PostConstruct
    public void initDB() {
        this.dbService.initDB(); // Chama o serviço para criar dados iniciais
    }
}