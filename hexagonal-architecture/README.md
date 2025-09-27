# 🧩 Hexagonal Architecture - Java/Spring Boot

Ce projet illustre la mise en place d’une **architecture hexagonale (Ports & Adapters)** avec **Java** et **Spring Boot**.  
Il s’agit d’une évolution de l’architecture en couches (Layered Architecture) pour mieux séparer les responsabilités et faciliter les tests.

---

## 🚀 Technologies utilisées
- Java 17+
- Spring Boot 3.x
- Maven
- JUnit 5
- MapStruct (pour le mapping DTO ↔ Entity)
- H2 Database (base en mémoire)
- Docker (pour MySQL ou PostgreSQL en local)
- Swagger/OpenAPI (documentation REST)

---

## 📂 Structure du projet

hexagonal-architecture/
├── src/main/java/com/architecturelab/hexagonal
│   ├── domain
│   │   ├── model
│   │   │   └── Product.java         # Entité métier
│   │   ├── port
│   │   │   ├── ProductRepositoryPort.java  # Port (interface)
│   │   │   └── ProductServicePort.java     # Cas d'utilisation (interface)
│   │   └── service
│   │       └── ProductServiceImpl.java     # Implémentation du domaine
│   │
│   ├── application
│   │   ├── dto
│   │   │   └── ProductDTO.java
│   │   ├── mapper
│   │   │   └── ProductMapper.java   # MapStruct
│   │   └── controller
│   │       └── ProductController.java  # REST API (utilise le port)
│   │
│   ├── infrastructure
│   │   ├── repository
│   │   │   └── ProductJpaRepository.java  # Adaptateur Spring Data
│   │   ├── entity
│   │   │   └── ProductEntity.java        # Entité DB
│   │   └── config
│   │       └── PersistenceConfig.java
│   │
│   └── App.java
│
├── src/test/java/com/architecturelab/hexagonal
│   ├── domain
│   │   └── ProductServiceTest.java
│   └── application
│       └── ProductControllerTest.java
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