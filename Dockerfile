FROM java:8
MAINTAINER zoe

RUN pwd
RUN ls /var/lib/docker/tmp

ADD salary-service.jar salary-service.jar
EXPOSE 8081


ENTRYPOINT ["java","-jar","salary-service.jar"]