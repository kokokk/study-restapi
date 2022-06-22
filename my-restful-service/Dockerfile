## https://hub.docker.com/layers/openjdk/library/openjdk/17-ea-11-jdk-slim/images/sha256-471694279f4b64a61505047e7ad38f3c6b9306be5b89738f3cb72d2f30143466?context=explore
FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY ./target/my-restful-service-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]


