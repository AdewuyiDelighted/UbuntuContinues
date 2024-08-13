FROM maven:3.8.5 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
COPY --from=build /target/*.jar UbuntuContinues.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "UbuntuContinues.jar"]
