FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar projectx.jar
ENTRYPOINT ["java","-jar","/projectx.jar"]
EXPOSE 8080
