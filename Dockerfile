# Usa uma imagem com Maven para build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia tudo do projeto
COPY . .

# Compila o projeto sem testes
RUN mvn clean package -DskipTests

# ---- Fase final: rodando com JDK leve ----
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o JAR gerado da fase de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta (Render vai mapear com $PORT)
EXPOSE 8080

# Start
CMD ["java", "-jar", "app.jar"]
