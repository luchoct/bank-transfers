FROM openjdk:11-jre-slim
COPY ./build/libs/statements-processor-1.0.0.jar /usr/src/statements-processor/
WORKDIR /usr/src/statements-processor
EXPOSE 8080
CMD ["java", "-jar", "statements-processor-1.0.0.jar"]
