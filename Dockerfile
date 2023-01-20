FROM openjdk:11
EXPOSE  8080

WORKDIR /app

ARG pass
ARG db
ENV MONGO_PASS=$pass
ENV MONGO_DB=$db

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app/api.jar

ENTRYPOINT ["java","-jar","/app/api.jar"]
