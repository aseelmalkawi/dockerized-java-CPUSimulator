FROM openjdk:17
COPY ./out/production/CPUSimulation/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "Main"]
