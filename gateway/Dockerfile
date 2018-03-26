FROM openjdk:8-jdk-alpine

COPY ./target/gateway.jar /app/gateway.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "gateway.jar"]
