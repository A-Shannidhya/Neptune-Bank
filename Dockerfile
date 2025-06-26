# Use a minimal Java 17 runtime base image
FROM eclipse-temurin:17-jdk-alpine

# Metadata
LABEL author="Ankit"

# Set working directory inside container
WORKDIR /app

# Copy the JAR file into the container
# Adjust the JAR path as needed if you're using Maven or different Gradle output
COPY build/libs/*.jar app.jar

# Expose port 8080 to the host
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
