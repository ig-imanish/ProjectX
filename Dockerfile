# Maven build container
FROM maven:3.8.5-openjdk-11 AS maven_build

COPY pom.xml /tmp/
COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

# Final container
FROM ubuntu:latest

# Install necessary dependencies
RUN apt-get update && apt-get install -y wget gnupg openjdk-17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file from the Maven build container
COPY --from=maven_build /tmp/target/projectx-0.0.1-SNAPSHOT.jar /app/projectx.jar

# Expose the port your application runs on
EXPOSE 8181

# Command to run the application when the container starts
CMD ["java", "-jar", "/app/projectx.jar"]
