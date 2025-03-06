#systax=docker/dockerfile:1

FROM mirror.gcr.io/library/openjdk:17-alpine

COPY target/*.jar analytics-service.jar

EXPOSE 18083

CMD exec java $JAVA_OPTS  -jar analytics-service.jar
