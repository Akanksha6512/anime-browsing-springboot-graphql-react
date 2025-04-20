FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar product-catalog-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "product-catalog-service.jar"]