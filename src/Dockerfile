#
# Build stage
#


#
# Package stage
#
FROM openjdk:17
COPY target/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]