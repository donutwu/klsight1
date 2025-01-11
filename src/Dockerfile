#
# Build stage
#


#
# Package stage
#
FROM openjdk:17


COPY target/app.jar app.jar
COPY target/opentelemetry-javaagent.jar opentelemetry-javaagent.jar
EXPOSE 8080
ENTRYPOINT ["java","-javaagent:opentelemetry-javaagent.jar","-jar","app.jar"]