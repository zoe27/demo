FROM java:8
MAINTAINER zoe
# RUN ls /var/lib/
# RUN who am i
# RUN pwd
# RUN ls /var/lib/

ADD /var/lib/salary-service.jar salary-service.jar
EXPOSE 8081


ENTRYPOINT ["java","-jar","salary-service.jar"]