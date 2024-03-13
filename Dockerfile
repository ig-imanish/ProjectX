# Maven build container 

FROM maven:3.8.5-openjdk-17 AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image

FROM eclipse-temurin:11

#expose port 8181
EXPOSE 8181

#default command
CMD java -jar /data/ProjectX.jar

#copy hello world to docker image from builder image

COPY --from=maven_build /tmp/target/ProjectX.jar /data/ProjectX.jar
