FROM openjdk:11
COPY ./out/production/DockerHelloWorld/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "Main"]
