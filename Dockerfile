# our base build image
FROM maven:3.6-jdk-8 as maven

WORKDIR /app

ENV CODECOV_TOKEN 7cdcd83f-a9a5-4f1e-b2d5-12346279a0c1

# copy the project files
COPY ./pom.xml ./pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src
COPY ./.git ./.git

# build for release
RUN curl -s https://codecov.io/bash > codecov.sh \
    && mvn package -Dskip.unit.tests=true

RUN find -name "*.jar" -exec sh -c 'f="{}"; mv -- "$f" studentscollab.jar' \;

# set the startup command to run your binary
CMD ["java", "-Xmx800m", "-jar", "studentscollab.jar", "--server.port=${PORT}"]
