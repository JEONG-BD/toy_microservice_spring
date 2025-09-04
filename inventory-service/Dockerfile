FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/inventory-service-1.0-SNAPSHOT.jar inventory-service.jar
ENTRYPOINT ["java", "-jar", "inventory-service.jar"]
# ENTRYPOINT ["sh", "-c", "java -Dserver.port=$SERVER