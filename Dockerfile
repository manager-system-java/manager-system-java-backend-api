FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY login-auth-api/pom.xml .
COPY login-auth-api/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]