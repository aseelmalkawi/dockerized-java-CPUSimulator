FROM openjdk:11
RUN mkdir java
COPY src/ java
WORKDIR java/src/
ENTRYPOINT ["java", "Main.Main"]
