FROM openjdk:11.0-jre-buster
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 10012
ENTRYPOINT ["java", "-jar", "/app.jar"]
