# 🧩 Microservices Architecture avec Spring Boot et Docker
Ce projet met en œuvre une architecture microservices basée sur Spring Boot et Docker, avec les services suivants :

## discovery-service : Service d’enregistrement (Eureka)

## gateway-service : API Gateway

## paiement-service : Service de gestion des paiements

# 📦 Technologies utilisées
Java / Spring Boot

Spring Cloud Netflix Eureka

Spring Cloud Gateway

Docker

Docker Compose

# 🏗️ Structure du projet
```bash
.
├── discovery-service/
├── gateway-service/
├── paiement-service/
├── docker-compose.yml
└── README.md
```
# ⚙️ Pré-requis
Assurez-vous d’avoir installé :
Docker

Docker Compose

# 🚀 Lancer le projet
Ouvrir un terminal à la racine du projet.

Construire les images Docker de chaque service :

```bash
docker build -t discovery-service ./discovery-service
docker build -t gateway-service ./gateway-service
docker build -t paiement-service ./paiement-service
```

Démarrer les services avec Docker Compose :
```bash
docker-compose up -d
```
# 🔍 Accès aux services
Service	URL
Discovery Service	http://localhost:8761

Gateway Service	http://localhost:8888

Paiement Service	http://localhost:8888/paiement (via Gateway)

# 🛑 Arrêter le projet
```bash
docker-compose down
```

# 📚 À venir

Intégration d’autres services (utilisateurs, réservations, notifications…)
Sécurité avec Spring Security et OAuth2
Observabilité avec Prometheus / Grafana

