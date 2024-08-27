#
# Build stage
#


#
# Package stage
#
FROM openjdk:17
ADD target/klsight-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]