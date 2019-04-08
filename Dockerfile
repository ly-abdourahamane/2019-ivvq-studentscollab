FROM maven:3.5-jdk-8
WORKDIR /app
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn package && cp target/studentscollab-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]

