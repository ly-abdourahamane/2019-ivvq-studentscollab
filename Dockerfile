FROM maven:3.5-jdk-8 as maven
WORKDIR /app
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package && cp target/studentscollab-*.jar studentscollab-0.0.1-SNAPSHOT.jar
FROM openjdk:8u171-jre-alpine
WORKDIR /app
COPY --from=maven /app/studentscollab-0.0.1-SNAPSHOT.jar ./studentscollab-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./studentscollab-0.0.1-SNAPSHOT.jar"]

