FROM maven:3.6.3-jdk-11 AS builder

WORKDIR /backend

#Copy the src folder and pom.xml to the workspace
COPY pom.xml /backend
COPY . /backend

#Make a jar  but skip tests to save some time, then let Spring add their files
RUN mvn clean package -DskipTests spring-boot:repackage

#Slim file because smallest container possible
FROM openjdk:11-jre-slim

# WORKDIR /app
COPY --from=builder /backend/target app

ENTRYPOINT ["java","-jar","/app/API-1.0-SNAPSHOT.jar"]
# CMD ls -lah /app && whoami
# ENTRYPOINT ["tail", "-f", "/dev/null"]
