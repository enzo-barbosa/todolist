package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Configuração principal de segurança do Spring Security
@Configuration
@EnableWebSecurity // Habilita a segurança web
public class SecurityConfig {

    // Define a cadeia de filtros de segurança
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para APIs REST
                .authorizeHttpRequests(auth -> auth
                        // Permite acesso público a Swagger e console H2
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**").permitAll()
                        .anyRequest().authenticated() // Todas outras requisições exigem autenticação
                )
                .httpBasic(Customizer.withDefaults()); // Usa autenticação Basic HTTP

        return http.build();
    }

    // Bean para criptografia de senhas usando BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}