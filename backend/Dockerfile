FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# For Java 11, try this
FROM openjdk:11-jre-slim

# Refer to Maven build -> finalName
#ARG JAR_FILE=target/international-phone-number-validator-1.0.0.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
#COPY ${JAR_FILE} app.jar

COPY --from=build /home/app/target/international-phone-number-validator-1.0.0.jar app.jar

ADD sample.db sample.db

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]