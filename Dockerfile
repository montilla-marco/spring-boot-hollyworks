FROM openjdk:11
VOLUME /tmp
COPY build/libs/hollyworkdays-1.1.12.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]