FROM openjdk:17-oracle

ARG JAR_PATH=./target
ARG JAR_NAME=electric-vehicle-charging-station-management
ARG JAR_VERSION=1.0.0-SNAPSHOT
ARG TARGET_PATH=/app
ENV APPLICATION=${TARGET_PATH}/application.jar
ENV PORT=2022

CMD mkdir ${TARGET_PATH}

ADD ${JAR_PATH}/${JAR_NAME}-${JAR_VERSION}.jar ${TARGET_PATH}/application.jar

EXPOSE ${PORT}
ENTRYPOINT java -jar ${APPLICATION}
