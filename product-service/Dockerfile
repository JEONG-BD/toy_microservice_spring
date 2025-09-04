FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/product-service-0.0.1-SNAPSHOT.jar product-service.jar
ENTRYPOINT ["java", "-jar", "product-service.jar"]
# ENTRYPOINT ["sh", "-c", "java -Dserver.port=$SERVER_PORT -jar product-service.jar"]