# Use a base image with Ubuntu and manually install JDK 21
FROM ubuntu:latest

# Install necessary dependencies
RUN apt-get update && apt-get install -y wget gnupg

# Download and install JDK 21
RUN wget -qO - https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public | apt-key add -
RUN echo "deb https://adoptopenjdk.jfrog.io/adoptopenjdk/deb/ focal main" | tee /etc/apt/sources.list.d/adoptopenjdk.list
RUN apt-get update && apt-get install -y adoptopenjdk-21-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container at /app
COPY target/projectx-0.0.1-SNAPSHOT.jar /app/projectx.jar

# Expose the port your application runs on
EXPOSE 8080

# Command to run the application when the container starts
CMD ["java", "-jar", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "/app/projectx.jar"]
