FROM jelastic/maven:3.9.5-openjdk-21 AS build
COPY. .
RUN mvn clean package -DSkipTests


FROM openjdk:21-slim
COPY --from=build /target/QUIZ-0.0.1-SNAPSHOT.jar-0.0.1 QUIZ.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","QUIZ.jar"]