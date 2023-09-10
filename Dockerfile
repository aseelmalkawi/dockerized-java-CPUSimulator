FROM openjdk:11
COPY src/ .
WORKDIR src
ENTRYPOINT ["java", "Main.Main"]
