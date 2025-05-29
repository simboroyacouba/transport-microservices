FROM maven:3.9.9-eclipse-temurin-21-alpine AS base
RUN echo "pulling..."

FROM base AS builder

WORKDIR /app

COPY . .
RUN  mvn clean install -DskipTests

FROM base AS deployer

WORKDIR /app
COPY --from=builder /app/target/gateway-service-0.0.1-SNAPSHOT.jar /app/gateway-service-0.0.1.jar

EXPOSE 8888

CMD [ "java", "-jar", "gateway-service-0.0.1.jar" ]
