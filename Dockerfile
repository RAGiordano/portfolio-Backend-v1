FROM amazoncorretto:11-alpine-jdk
MAINTAINER RAG
COPY target/RAG-0.0.1-SNAPSHOT.jar portfolio-RAG.jar
ENTRYPOINT ["java","-jar","/portfolio-RAG.jar"]

