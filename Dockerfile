FROM openjdk:11-jre-slim

MAINTAINER Jhony Dias

WORKDIR /app

COPY ./associados-votacao.jar /app/associados-votacao.jar

CMD ["java", "-jar", "associados-votacao.jar"]
