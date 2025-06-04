# Étape 1 : Build avec Maven (image sécurisée sans vulnérabilités)
FROM maven:3.9.9-eclipse-temurin-17-alpine AS builder

WORKDIR /app
# Copie séparée pour optimiser le cache Docker
COPY pom.xml .
# Télécharge les dépendances en cache
RUN mvn dependency:go-offline

COPY src src
# Build avec suppression des fichiers temporaires
RUN mvn clean package -DskipTests \
    && rm -rf /root/.m2

# Étape 2 : Image finale légère et sécurisée
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
# Copie uniquement le JAR construit
COPY --from=builder /app/target/reservation-service-*.jar ./app.jar

# Installation des dépendances minimales pour Alpine
RUN apk add --no-cache ttf-dejavu \
    && addgroup -S javauser \
    && adduser -S -G javauser javauser \
    && chown -R javauser:javauser /app

USER javauser

# Configuration JVM optimisée pour conteneurs
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+ExitOnOutOfMemoryError"

EXPOSE 8082
ENTRYPOINT ["sh", "-c", "exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar --spring.profiles.active=docker"]