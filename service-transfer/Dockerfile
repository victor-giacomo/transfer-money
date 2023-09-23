FROM maven:3-openjdk-17
COPY target/transfer-0.0.1-SNAPSHOT.jar transfer-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/transfer-0.0.1.jar"]