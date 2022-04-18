FROM dockerhub.ir/alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/bm-vip/electric-vehicle-charging-station-management.git

FROM dockerhub.ir/maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY --from=clone /app/electric-vehicle-charging-station-management /app
#COPY src /app/src
#COPY pom.xml /app
RUN mvn clean install

FROM dockerhub.ir/openjdk:17-oracle
WORKDIR /app
COPY --from=build /app/target/electric-vehicle-charging-station-management-1.0.0-SNAPSHOT.jar /app
EXPOSE 2022
ENTRYPOINT ["java", "-jar", "electric-vehicle-charging-station-management-1.0.0-SNAPSHOT.jar"]