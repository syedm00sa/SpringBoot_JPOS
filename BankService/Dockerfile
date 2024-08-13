FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/BankService-0.0.1-SNAPSHOT.jar BankService.jar
ENTRYPOINT ["java","-jar","/BankService.jar"]
