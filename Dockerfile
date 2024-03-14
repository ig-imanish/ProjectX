# Use a base image with Ubuntu
FROM ubuntu:latest

# Install necessary dependencies
RUN apt-get update && apt-get install -y wget gnupg openjdk-17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container at /app
COPY target/projectx-0.0.1-SNAPSHOT.jar /app/projectx.jar

# Expose the port your application runs on
EXPOSE 8181

# Command to run the application when the container starts
CMD ["java", "-jar", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "/app/projectx.jar"]
