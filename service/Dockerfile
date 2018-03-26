FROM openjdk:8-jdk-alpine

COPY ./target/uploader.jar /app/uploader.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "uploader.jar"]
