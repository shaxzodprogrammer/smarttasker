FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/user-service-0.0.1-SNAPSHOT.jar user-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "user-service.jar"]
