FROM openjdk:17.0.2-jdk AS exam-server
LABEL authors="fanxfan"


RUN mkdir -p "/exam-server"
WORKDIR "/exam-server"
COPY ./target/exam-server-1.0.0.jar ./

CMD java -jar exam-server-1.0.0.jar