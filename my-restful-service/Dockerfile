## https://hub.docker.com/layers/openjdk/library/openjdk/17-ea-11-jdk-slim/images/sha256-471694279f4b64a61505047e7ad38f3c6b9306be5b89738f3cb72d2f30143466?context=explore
FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY ./target/my-restful-service-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]


## docker build --platform linux/amd64 --tag my-restful-service:1.0
# 파일명이 Dockerfile 이 아닐 경우 뒤에 -f Dockerfile_new(파일명) 붙임

## docker image ls | grep my-restful-service

## docker images

## docker ps

## docker run -d --name my-restful-service --publish 9000:8080 my-restful-service:1.0

## docker container ls




## 이미지를 다른 환경에서도 사용할 수 있게 하려면,
## hub 에 이미지 push
## docker login
## docker tag my-restful-service:1.0 edowon0623/my-restful-service:1.0
## docker push edowon0623/my-restful-service:1.0



## 다른 환경에서 image 다운로드
## docker image ls
## docker pull edowon0623/my-restful-service:1.0
## docker run -d --name my-restful-service --publish 9000:8080 edowon0623/my-restful-service:1.0

## 오류 발생시 docker container ls 결과 통해 imageid 확인하여 로그 확인
## docker logs c59712e37e5b

## architecture 확인
## docker image inspect edowon0623/my-restful-service:1.0


## docker run --platform linux/amd64 -d --name my-restful-service --publish 9000:8080 edowon0623/my-restful-service:1.0

