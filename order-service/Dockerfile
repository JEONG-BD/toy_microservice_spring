FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/order-service-1.0-SNAPSHOT.jar order-service.jar
ENTRYPOINT ["java", "-jar", "order-service.jar"]
# ENTRYPOINT ["sh", "-c", "java -Dserver.port=$SERVER