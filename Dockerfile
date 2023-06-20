FROM amazoncorretto:11-alpine-jdk //de qué imagen partimos
MAINTAINER RAG //quién es el dueño
COPY target/RAG-0.0.1-SNAPSHOT.jar portfolio-RAG.jar //va a copiar el empaquetado a GitHub
ENTRYPOINT ["java","-jar","/portfolio-RAG.jar"] //es la instrucción que se va a ejecutar primero

