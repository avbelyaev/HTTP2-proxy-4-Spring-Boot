FROM openjdk:8-jdk-alpine

COPY ./target/boot.jar /app/boot.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "boot.jar"]
