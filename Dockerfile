# Stage 1: Build the application
FROM maven:3.8.4-openjdk-21 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .

# Download dependencies
RUN mvn -B dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
RUN mvn -B clean package

# Stage 2: Create the final image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=builder /app/target/ProjectX.jar .

# Expose port 8080
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "ProjectX.jar"]
