# 🚀 Rocket Manager — Backend
 
API REST do sistema de gestão de projetos Rocket Manager, desenvolvida com Spring Boot.
 
## 🛠️ Tecnologias
 
- Java com Spring Boot
- Spring Security + JWT
- Spring Data JPA + Hibernate
- PostgreSQL (Supabase)
## ▶️ Como rodar
 
Configure as variáveis de ambiente no `application.properties` (Copie o arquivo `application.example.properties` e renomeie para `application.properties`, depois preencha com suas credenciais do Supabase.)
 
```properties
spring.application.name=login-auth-api
 
# Banco de dados - configure com suas credenciais do Supabase
spring.datasource.url=jdbc:postgresql://SEU_HOST.supabase.com:5432/postgres?sslmode=require
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
 
# JPA
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
 
# JWT
api.security.token.secret=SUA_CHAVE_SECRETA
 
# HikariCP
spring.datasource.hikari.maximum-pool-size=3
spring.datasource.hikari.minimum-idle=1
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000
spring.datasource.hikari.validation-timeout=5000
spring.datasource.hikari.connection-test-query=SELECT 1
spring.datasource.hikari.connection-init-sql=DEALLOCATE ALL
```
 
Depois rode:
 
```bash
./mvnw spring-boot:run
```
 
API disponível em `http://localhost:8080`
 
## 📄 Documentação completa
 
[Clique aqui para acessar](https://github.com/manager-system-java/project-docs)
