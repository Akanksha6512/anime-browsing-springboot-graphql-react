FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar graphql-gateway-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "graphql-gateway-service.jar"]