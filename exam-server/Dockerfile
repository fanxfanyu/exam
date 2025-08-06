FROM openjdk:8-jdk-alpine
LABEL authors="fanxfan"

COPY target/exam-server-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "jar","app.jar"]