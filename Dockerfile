FROM java:8
MAINTAINER zoe
ADD salary-service.jar salary-service.jar
EXPOSE 8081

RUN pwd
RUN ls
ENTRYPOINT ["java","-jar","salary-service.jar"]