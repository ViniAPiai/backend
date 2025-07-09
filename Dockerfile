# Etapa 1: build da aplicação
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: imagem de execução
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}

# Expõe a porta 8080
EXPOSE 8080

# Comando para rodar o app com o perfil "hml"
ENTRYPOINT ["java", "-jar", "app.jar"]