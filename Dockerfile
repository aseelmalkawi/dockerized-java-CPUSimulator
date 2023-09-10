FROM openjdk:18
COPY ./out/production/CPUSimulation/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "Main"]
