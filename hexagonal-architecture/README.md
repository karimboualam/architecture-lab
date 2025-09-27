# 🧩 Hexagonal Architecture - Java/Spring Boot + Spring Batch

Ce projet illustre la mise en place d’une **architecture hexagonale (Ports & Adapters)** avec **Java**, **Spring Boot** et **Spring Batch**.  
Il s’agit d’une évolution de l’architecture en couches (Layered Architecture) pour mieux séparer les responsabilités, faciliter les tests et intégrer des traitements batch.

---

## 🚀 Technologies utilisées
- Java 17+ / 21
- Spring Boot 3.x
- Spring Batch
- Maven
- JUnit 5
- MapStruct (pour le mapping DTO ↔ Entity)
- H2 Database (base en mémoire)
- MySQL (en local ou via Docker)
- Docker & Docker Compose
- Swagger/OpenAPI (documentation REST)

---
## 📂 Structure du projet

hexagonal-architecture/
├── src/main/java/com/architecturelab/hexagonal
│ ├── domain
│ │ ├── model
│ │ │ └── Product.java # Entité métier
│ │ ├── port
│ │ │ ├── ProductRepositoryPort.java # Port (interface)
│ │ │ └── ProductServicePort.java # Cas d'utilisation (interface)
│ │ └── service
│ │ └── ProductServiceImpl.java # Implémentation du domaine
│ │
│ ├── application
│ │ ├── dto
│ │ │ └── ProductDTO.java
│ │ ├── mapper
│ │ │ └── ProductMapper.java # MapStruct
│ │ └── controller
│ │ └── ProductController.java # REST API (utilise le port)
│ │
│ ├── infrastructure
│ │ ├── repository
│ │ │ ├── ProductJpaRepository.java # Adaptateur Spring Data
│ │ │ └── ProductRepositoryAdapter.java
│ │ ├── entity
│ │ │ └── ProductEntity.java # Entité DB
│ │ ├── config
│ │ │ ├── PersistenceConfig.java
│ │ │ ├── BatchConfig.java # Config Spring Batch
│ │ │ └── SwaggerConfig.java # Config Swagger/OpenAPI
│ │ └── controller
│ │ └── BatchController.java # Endpoint pour lancer le batch
│ │
│ └── App.java # Classe principale
│
├── src/main/resources
│ ├── application.properties # Configuration (DB, Swagger…)
│ ├── schema-mysql.sql # Script SQL init
│ └── products.csv # Données d’entrée du batch
│
├── src/test/java/com/architecturelab/hexagonal
│ ├── domain
│ │ └── ProductServiceTest.java
│ └── application
│ └── ProductControllerTest.java
│
├── pom.xml
└── README.md


---

## ▶️ Lancer le projet

### En mode développement

mvn spring-boot:run

### En mode test

mvn test

### Avec Docker

Un fichier docker-compose.yml permet de lancer rapidement une base de données :
docker-compose up -d
📖 API Documentation

Une fois lancé, accéder à :

Swagger UI → http://localhost:8080/swagger-ui/index.html

OpenAPI JSON → http://localhost:8080/v3/api-docs

### ✅ Objectifs pédagogiques

Comprendre la différence entre architecture en couches et architecture hexagonale

Distinguer le domaine métier des adaptateurs techniques

Faciliter les tests unitaires grâce à l’isolation des ports/adapters

Déployer facilement grâce à Docker et CI/CD

### 📌 Prochaines étapes

Ajouter un exemple métier (ex : gestion de produits ou commandes)

Implémenter des ports (interfaces) et adapters (REST, DB)

Couvrir les cas d’usage avec des tests MockMvc + JUnit
👨‍💻 Auteur : BOUALAM Karim