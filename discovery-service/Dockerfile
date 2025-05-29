FROM maven:3.9.9-eclipse-temurin-21-alpine AS base
RUN echo "pulling..."

FROM base AS builder

WORKDIR /app

COPY . .
RUN  mvn clean install -DskipTests

FROM base AS deployer

WORKDIR /app
COPY --from=builder /app/target/discovery-service-0.0.1-SNAPSHOT.jar /app/discovery-service-0.0.1.jar

EXPOSE 8761

CMD [ "java", "-jar", "discovery-service-0.0.1.jar" ]
