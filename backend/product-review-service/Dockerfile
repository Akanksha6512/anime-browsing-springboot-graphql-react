FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar review-service.jar
COPY src/main/resources/secure-connect-product-review.zip src/main/resources/secure-connect-product-review.zip
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "review-service.jar"]