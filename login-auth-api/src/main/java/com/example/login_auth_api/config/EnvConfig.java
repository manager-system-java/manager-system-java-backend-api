package com.example.login_auth_api.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
public class EnvConfig {
    // Carrega o .env ANTES de qualquer coisa
    static {
        try {
            Dotenv dotenv = Dotenv.configure()
                    .directory("../")
                    .filename(".env")
                    .ignoreIfMissing()
                    .load();

            if (dotenv != null) {
                String dbUsername = dotenv.get("DB_USERNAME");
                String dbPassword = dotenv.get("DB_PASSWORD");
                String jwtSecret = dotenv.get("JWT_SECRET");

                if (dbUsername != null) {
                    System.setProperty("DB_USERNAME", dbUsername);
                }
                if (dbPassword != null) {
                    System.setProperty("DB_PASSWORD", dbPassword);
                }
                if (jwtSecret != null) {
                    System.setProperty("JWT_SECRET", jwtSecret);
                }
                System.out.println("✓ Variáveis de ambiente carregadas do .env");

                // TEMPORÁRIO - DEBUG
                System.out.println(">>> DB_USERNAME: " + dbUsername);
                System.out.println(">>> DB_PASSWORD: " + (dbPassword != null ? "****" : "NULL"));
            }
        } catch (Exception e) {
            System.out.println("⚠ Aviso: Arquivo .env não encontrado. Usando variáveis de sistema ou application.properties");
        }
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreResourceNotFound(true);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }


}