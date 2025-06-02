# Fleet Service API 🚗

## Description 📖
Le Fleet Service est une API qui gère les véhicules, les trajets et les conducteurs. Elle est construite avec Node.js et Express et utilise Sequelize pour la gestion de la base de données.

## Routes 🛣️

### Véhicules 🚙
- **GET /vehicle** : Récupérer tous les véhicules.
- **GET /vehicle/:id** : Récupérer un véhicule par son ID.
- **POST /vehicle** : Créer un nouveau véhicule.
- **PATCH /vehicle/:id** : Mettre à jour un véhicule par son ID.
- **DELETE /vehicle/:id** : Supprimer un véhicule par son ID.

### Trajets 🚌
- **GET /trip** : Récupérer tous les trajets.
- **GET /trip/:id** : Récupérer un trajet par son ID.
- **POST /trip** : Créer un nouveau trajet.
- **PATCH /trip/:id** : Mettre à jour un trajet par son ID.
- **DELETE /trip/:id** : Supprimer un trajet par son ID.

### Conducteurs 👨‍✈️
- **GET /driver** : Récupérer tous les conducteurs.
- **GET /driver/:id** : Récupérer un conducteur par son ID.
- **POST /driver** : Créer un nouveau conducteur.
- **PATCH /driver/:id** : Mettre à jour un conducteur par son ID.
- **DELETE /driver/:id** : Supprimer un conducteur par son ID.