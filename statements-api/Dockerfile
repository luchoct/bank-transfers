FROM openjdk:11-jre-slim
COPY ./build/libs/statements-api-1.0.0.jar /usr/src/statements-api/
WORKDIR /usr/src/statements-api
EXPOSE 8080
CMD ["java", "-jar", "statements-api-1.0.0.jar"]
