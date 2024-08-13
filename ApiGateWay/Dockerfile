FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/ApiGateWay-0.0.1-SNAPSHOT.jar ApiGateWay.jar
ENTRYPOINT ["java","-jar","/ApiGateWay.jar"]
