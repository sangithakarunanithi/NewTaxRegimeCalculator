# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and src folder to the working directory
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runnable JAR
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/newRegimeTaxCalculator-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port
EXPOSE 9122

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
