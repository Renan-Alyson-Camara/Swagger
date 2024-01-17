# Estágio de compilação
FROM gradle:7.6.1-jdk17 AS build
WORKDIR /home/app
COPY . .
RUN gradle clean build

# Estágio de produção
FROM openjdk:17-jdk-slim
COPY --from=build home/app/build/libs/*.jar ./swagger.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "swagger.jar"]