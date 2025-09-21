# 🏗️ Layered Architecture - Spring Boot

Ce projet est une implémentation **CRUD** simple en **Java 21 / Spring Boot 3** utilisant l’**architecture en couches** (Controller → Service → Repository → Model/DTO).

---

## 🚀 Stack technique
- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Web** (API REST)
- **Spring Data JPA** (ORM Hibernate)
- **MySQL** (base de données)
- **Lombok** (réduction du boilerplate)
- **MapStruct** (mappage Entity ↔ DTO)
- **Jakarta Validation** (validation des entrées)

---

## 📂 Structure du projet
layered-architecture/
├── controller/ # API REST (ProductController)
├── dto/ # Data Transfer Objects + Mapper
├── exception/ # Gestion des exceptions
├── model/ # Entités JPA (Product)
├── repository/ # Interfaces JPA
├── service/ # Logique métier
└── LayeredArchitectureApplication.java

---

## ▶️ Lancer le projet

### 1️⃣ Configuration MySQL
Créer une base de données MySQL locale, par exemple :
```sql
CREATE DATABASE architecture_lab;
Configurer src/main/resources/application.properties :
spring.datasource.url=jdbc:mysql://localhost:3306/architecture_lab
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
2️⃣ Lancer avec Maven
mvn clean install
mvn spring-boot:run
L’application démarre sur http://localhost:8080

📌 Endpoints API

Base URL : http://localhost:8080

Méthode	Endpoint	Description
GET	/products	Liste des produits
GET	/products/{id}	Récupérer un produit par ID
POST	/products	Créer un produit
PUT	/products/{id}	Modifier un produit
DELETE	/products/{id}	Supprimer un produit

Exemple de création (POST /products)
{
  "name": "Samsung S24",
  "price": 399
}

✅ Objectif pédagogique

Ce projet illustre :

La séparation des responsabilités dans une architecture en couches

L’utilisation des DTO + Mapper (MapStruct)

La validation des entrées via Jakarta Validation

La gestion d’exceptions centralisée avec @RestControllerAdvice

📖 À venir

Tests unitaires avec JUnit + Mockito

Documentation API avec SpringDoc / Swagger

Dockerisation du projet