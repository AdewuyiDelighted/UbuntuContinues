FROM maven:3.8.5 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
COPY --from=build /target/*.jar UbuntuContinues.jar
EXPOSE 8080

ARG socket_port

ENV socket_port=${socket_port}

# Expose the port for your Socket.IO server
EXPOSE $socket_port

ENTRYPOINT ["java", "-jar", "UbuntuContinues.jar"]
