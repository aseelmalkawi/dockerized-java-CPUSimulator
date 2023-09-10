FROM openjdk:11
COPY ./out/production/CPUSimulation/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "Main"]
