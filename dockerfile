# Etapa de build
FROM ubuntu:latest AS build
# Atualizar pacotes e instalar dependências
RUN apt-get update && apt-get install -y openjdk-21-jdk maven

# Copiar o código para o contêiner
COPY . .

# Compilar a aplicação com o Maven
RUN mvn clean install

# Etapa de runtime
FROM openjdk:21-jdk-slim
# Expor a porta 8080
EXPOSE 8080

# Copiar o arquivo JAR gerado na etapa de build para o contêiner final
COPY --from=build /target/investLab-0.0.1-SNAPSHOT.jar app.jar

# Definir o entrypoint para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
