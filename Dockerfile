# Use official Java 21 runtime
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy jar from target
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
