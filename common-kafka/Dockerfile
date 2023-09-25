FROM maven:3-openjdk-17
COPY target/common-kafka-0.0.1-SNAPSHOT.jar common-kafka-0.0.1.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/common-kafka-0.0.1.jar"]