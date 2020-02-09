FROM openjdk:8-jdk-alpine
LABEL MAINTAINER Georgi Vasilski

RUN set -x \
	&& apk update && apk upgrade && apk add --no-cache ttf-dejavu fontconfig

WORKDIR /opt/
COPY /build/libs/demo-numbers-operations-0.0.1-SNAPSHOT.jar /opt/demo.jar

EXPOSE 7890
CMD ["java", "-jar", "/opt/demo.jar"]
