FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ./surya1/target/surya1-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app.jar"]