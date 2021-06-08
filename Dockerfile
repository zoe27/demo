FROM java:8
MAINTAINER zoe

RUN sleep 10
RUN pwd
RUN ls /var/lib/

ADD salary-service.jar salary-service.jar
EXPOSE 8081


ENTRYPOINT ["java","-jar","salary-service.jar"]