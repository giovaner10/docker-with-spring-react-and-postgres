FROM openjdk:11-jre-slim
WORKDIR /app
COPY racha/target/racha-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "/app/racha-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080


