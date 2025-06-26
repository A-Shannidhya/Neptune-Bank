FROM openjdk:17-jdk-slim
WORKDIR /app

# Adjust the path based on Gradle output
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 9999  # or whatever port otp-service runs on
