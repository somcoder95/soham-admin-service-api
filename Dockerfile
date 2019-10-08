FROM openjdk:latest

MAINTAINER Soham Gupte "soham.gupt@idexcel.net"

EXPOSE 8080

WORKDIR /usr/local/bin/

COPY ./target/soham-admin-service-0.0.1.jar sohamservice.jar

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "sohamservice.jar"]