#
# Build stage
#
FROM maven:3.8.5-openjdk-17-slim AS build

ADD pom.xml /home/app/

RUN mvn -f /home/app/pom.xml verify clean

COPY src /home/app/src

RUN mvn -f /home/app/pom.xml package -Dmaven.test.skip spring-boot:repackage

EXPOSE 8080

#
# Package stage
#
FROM openjdk:17-alpine
COPY --from=build /home/app/target/LR7-1.0-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]