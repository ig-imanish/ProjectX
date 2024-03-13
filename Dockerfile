# Maven build container
FROM maven:3.8.5-openjdk-17 AS maven_build

COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package && \
    rm -rf /root/.m2/repository  # Clean up Maven dependencies

# Final container
FROM eclipse-temurin:11

# Expose port 8181
EXPOSE 8181

# Default command
CMD java -jar /data/ProjectX.jar

# Copy JAR from build stage
COPY --from=maven_build /tmp/target/ProjectX.jar /data/ProjectX.jar
